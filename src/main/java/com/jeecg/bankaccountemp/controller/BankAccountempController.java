package com.jeecg.bankaccountemp.controller;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.bankaccountemp.entity.BankAccountempEntity;
import com.jeecg.bankaccountemp.service.BankAccountempServiceI;
import com.jeecg.bankcode.entity.BankCodeEntity;
import com.jeecg.bankcode.service.BankCodeServiceI;
import com.jeecg.productcode.entity.ProductCodeEntity;
import com.jeecg.productcode.service.ProductCodeServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 银行账户信息临时表
 * @author onlineGenerator
 * @date 2018-09-06 16:41:02
 * @version V1.0   
 *
 */
@Api(value="BankAccountemp",description="银行账户信息临时表",tags="bankAccountempController")
@Controller
@RequestMapping("/bankAccountempController")
public class BankAccountempController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankAccountempController.class);

	@Autowired
	private BankAccountempServiceI bankAccountempService;
	
	@Autowired
	private BankCodeServiceI BankCodeService;
	
	@Autowired
	private ProductCodeServiceI ProductCodeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 获取银行账户临时信息实体
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getBankAccountempEntity")
	@ResponseBody
	public AjaxJson getBankAccountempEntity(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		j.setAttributes(attrMap);
		String id = req.getParameter("id");
		BankAccountempEntity bankAccountempSave= systemService.getEntity(BankAccountempEntity.class, id);
		BankAccountempEntity bankAccountemp =new BankAccountempEntity();//用来显示页面的对象
		//对象拷贝
		BankAccountempEntity BankAccountempMap = new BankAccountempEntity();
		if(bankAccountemp!=null){
			try {
				if(bankAccountempSave!=null){
					MyBeanUtils.copyBeanNotNull2Bean(bankAccountempSave,bankAccountemp);
				}
				MyBeanUtils.copyBeanNotNull2Bean(bankAccountemp,BankAccountempMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		attrMap.put("bankAccountemp2", BankAccountempMap);//银行账户临时表实体
		if(bankAccountemp!=null){
		//开户银行简称5
		String shortname =  bankAccountemp.getAcountShortname();
		BankCodeEntity BankCode= BankCodeService.findUniqueByProperty(BankCodeEntity.class, "bankcode", shortname);
		bankAccountemp.setAcountShortname(BankCode!=null?BankCode.getBankname():shortname);
		//在用产品
		//String onlineProduct =bankAccountemp.getOnlineProduct();
		//ProductCodeEntity productCode = ProductCodeService.findUniqueByProperty(ProductCodeEntity.class, "productcode", onlineProduct);
		//bankAccountemp.setOnlineProduct(productCode!=null?productCode.getProductname():onlineProduct);
		
		String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg"
				+ " on t.typegroupid=tg.id where tg.typegroupname  = ?  and t.typecode = ? ";
		//账户类型
		Map<String,Object> AcountType = bankAccountempService.findOneForJdbc(sql, "账户类型",bankAccountemp.getAcountType());
		bankAccountemp.setAcountType(AcountType!=null?(String) AcountType.get("typename"):null);
		//账户状态
		Map<String,Object> AcountStatus = bankAccountempService.findOneForJdbc(sql, "账户状态",bankAccountemp.getAcountStatus());
		bankAccountemp.setAcountStatus(AcountStatus!=null?(String) AcountStatus.get("typename"):"募集在用");
		//是否流水户
		String  Liushui = bankAccountemp.getIsLiushui();
		bankAccountemp.setIsLiushui("1".equals(Liushui)?"是":"否");
		//是否可以提前结息
		String  isPreEnd = bankAccountemp.getIsPreEnd();
		bankAccountemp.setIsPreEnd("1".equals(isPreEnd)?"是":"否");
		BankAccountEntity bankAccount= systemService.getEntity(BankAccountEntity.class, id);

			attrMap.put("bankAccountemp", bankAccountemp);//银行账户临时表实体,这是页面显示的对象，但它不能为持久化对象，会影响数据库
			attrMap.put("bankAccount", bankAccount);//银行账户正式表
			
		}else {
			j.setSuccess(false);
			
		}
		return j;
	}
	
	
	
	

	/**
	 * 银行账户信息临时表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/bankaccountemp/bankAccountempList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BankAccountempEntity bankAccountemp,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankAccountempEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccountemp, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankAccountempService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除银行账户信息临时表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BankAccountempEntity bankAccountemp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		bankAccountemp = systemService.getEntity(BankAccountempEntity.class, bankAccountemp.getId());
		message = "银行账户信息临时表删除成功";
		try{
			bankAccountempService.delete(bankAccountemp);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息临时表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除银行账户信息临时表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行账户信息临时表删除成功";
		try{
			for(String id:ids.split(",")){
				BankAccountempEntity bankAccountemp = systemService.getEntity(BankAccountempEntity.class, 
				id
				);
				bankAccountempService.delete(bankAccountemp);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息临时表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加银行账户信息临时表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BankAccountempEntity bankAccountemp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行账户信息临时表添加成功";
		try{
			bankAccountempService.save(bankAccountemp);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息临时表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新银行账户信息临时表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BankAccountempEntity bankAccountemp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行账户信息临时表更新成功";
		BankAccountempEntity t = bankAccountempService.get(BankAccountempEntity.class, bankAccountemp.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bankAccountemp, t);
			bankAccountempService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "银行账户信息临时表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 银行账户信息临时表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BankAccountempEntity bankAccountemp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankAccountemp.getId())) {
			bankAccountemp = bankAccountempService.getEntity(BankAccountempEntity.class, bankAccountemp.getId());
			req.setAttribute("bankAccountempPage", bankAccountemp);
		}
		return new ModelAndView("com/jeecg/bankaccountemp/bankAccountemp-add");
	}
	/**
	 * 银行账户信息临时表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BankAccountempEntity bankAccountemp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankAccountemp.getId())) {
			bankAccountemp = bankAccountempService.getEntity(BankAccountempEntity.class, bankAccountemp.getId());
			req.setAttribute("bankAccountempPage", bankAccountemp);
		}
		return new ModelAndView("com/jeecg/bankaccountemp/bankAccountemp-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","bankAccountempController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BankAccountempEntity bankAccountemp,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BankAccountempEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccountemp, request.getParameterMap());
		List<BankAccountempEntity> bankAccountemps = this.bankAccountempService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"银行账户信息临时表");
		modelMap.put(NormalExcelConstants.CLASS,BankAccountempEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("银行账户信息临时表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,bankAccountemps);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BankAccountempEntity bankAccountemp,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"银行账户信息临时表");
    	modelMap.put(NormalExcelConstants.CLASS,BankAccountempEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("银行账户信息临时表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BankAccountempEntity> listBankAccountempEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BankAccountempEntity.class,params);
				for (BankAccountempEntity bankAccountemp : listBankAccountempEntitys) {
					bankAccountempService.save(bankAccountemp);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="银行账户信息临时表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BankAccountempEntity>> list() {
		List<BankAccountempEntity> listBankAccountemps=bankAccountempService.getList(BankAccountempEntity.class);
		return Result.success(listBankAccountemps);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取银行账户信息临时表信息",notes="根据ID获取银行账户信息临时表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BankAccountempEntity task = bankAccountempService.get(BankAccountempEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取银行账户信息临时表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建银行账户信息临时表")
	public ResponseMessage<?> create(@ApiParam(name="银行账户信息临时表对象")@RequestBody BankAccountempEntity bankAccountemp, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankAccountempEntity>> failures = validator.validate(bankAccountemp);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			bankAccountempService.save(bankAccountemp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("银行账户信息临时表信息保存失败");
		}
		return Result.success(bankAccountemp);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新银行账户信息临时表",notes="更新银行账户信息临时表")
	public ResponseMessage<?> update(@ApiParam(name="银行账户信息临时表对象")@RequestBody BankAccountempEntity bankAccountemp) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankAccountempEntity>> failures = validator.validate(bankAccountemp);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			bankAccountempService.saveOrUpdate(bankAccountemp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新银行账户信息临时表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新银行账户信息临时表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除银行账户信息临时表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			bankAccountempService.deleteEntityById(BankAccountempEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("银行账户信息临时表删除失败");
		}

		return Result.success();
	}
}

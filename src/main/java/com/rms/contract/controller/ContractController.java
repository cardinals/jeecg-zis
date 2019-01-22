package com.rms.contract.controller;
import com.rms.contract.entity.ContractEntity;
import com.rms.contract.service.ContractServiceI;
import java.util.ArrayList;
import java.util.Date;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import java.math.BigDecimal;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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

import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import java.util.HashMap;
/**   
 * @Title: Controller  
 * @Description: RMS_合同管理
 * @author onlineGenerator
 * @date 2018-11-03 09:53:08
 * @version V1.0   
 *
 */
@Api(value="Contract",description="RMS_合同管理",tags="contractController")
@Controller
@RequestMapping("/contractController")
public class ContractController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContractController.class);

	@Autowired
	private ContractServiceI contractService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * RMS_合同管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/contract/contractList");
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
	public void datagrid(ContractEntity contract,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ContractEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contract, request.getParameterMap());
		
		TSUser loginUser = ResourceUtil.getSessionUser();
		String realname = loginUser.getRealName();
		
		String typecode ="";
		boolean isTypecode = false;
		String sql ="select  t.typecode, t.typename from t_s_type t "
					+ "LEFT JOIN  t_s_typegroup tg " 
					+  " on t.typegroupid=tg.id where tg.typegroupcode='user' and t.typename = ? ";
		if(StringUtil.isNotEmpty(realname)) {
			List<Map<String, Object>>  maps = contractService.findForJdbc(sql, realname);
			if( maps!= null && maps.size()>0 ) {
				typecode =(String) maps.get(0).get("typecode");
				isTypecode = true;
			}
		}
		 
		try{
		//自定义追加查询条件
			/*public void or(Criterion c1, Criterion c2) {
				this.detachedCriteria.add(Restrictions.or(c1, c2));
			}*/
		//注释掉 数据权限 20190107
		/*if(isTypecode) {
		
			Criterion c = Restrictions.eq("contractedBy", typecode);
			Criterion c2 = Restrictions.eq("agent", typecode);
    			cq.or(c, c2);
		}*/
			
		String query_totalAmount_begin = request.getParameter("totalAmount_begin");
		String query_totalAmount_end = request.getParameter("totalAmount_end");
		if(StringUtil.isNotEmpty(query_totalAmount_begin)){
			cq.ge("totalAmount", Integer.parseInt(query_totalAmount_begin));
		}
		if(StringUtil.isNotEmpty(query_totalAmount_end)){
			cq.le("totalAmount", Integer.parseInt(query_totalAmount_end));
		}
		String query_contractDate_begin = request.getParameter("contractDate_begin");
		String query_contractDate_end = request.getParameter("contractDate_end");
		if(StringUtil.isNotEmpty(query_contractDate_begin)){
			cq.ge("contractDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_contractDate_begin));
		}
		if(StringUtil.isNotEmpty(query_contractDate_end)){
			cq.le("contractDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_contractDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.contractService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除RMS_合同管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ContractEntity contract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		contract = systemService.getEntity(ContractEntity.class, contract.getId());
		message = "RMS_合同管理删除成功";
		try{
			contractService.delete(contract);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_合同管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_合同管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_合同管理删除成功";
		try{
			for(String id:ids.split(",")){
				ContractEntity contract = systemService.getEntity(ContractEntity.class, 
				id
				);
				contractService.delete(contract);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_合同管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加RMS_合同管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ContractEntity contract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_合同管理添加成功";
		try{
			
			  String maxField = "contract_id";//带有日期的字段
			  String tableName = "rms_contract_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      contract.setContractId(dateFormatId);
			
			contractService.save(contract);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_合同管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(contract);
		return j;
	}
	
	
	
	
	
	@RequestMapping(params = "setProxy")
	@ResponseBody
	public AjaxJson setProxy(ContractEntity contract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "设置代理人成功";
		try{
			ContractEntity t = contractService.get(ContractEntity.class, contract.getId());
			//t.setContractedBy(contract.getContractedBy());//签订人
			t.setAgent(contract.getAgent());//设置代理人
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "设置代理人失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(contract);
		return j;
	}
	
	
	/**
	 * 更新RMS_合同管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ContractEntity contract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_合同管理更新成功";
		ContractEntity t = contractService.get(ContractEntity.class, contract.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(contract, t);
			contractService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_合同管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * RMS_合同管理--增加付款信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddPayment")
	public ModelAndView goPaygoAddPaymentments(ContractEntity contract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contract.getId())) {
			contract = contractService.getEntity(ContractEntity.class, contract.getId());
			req.setAttribute("contractPage", contract);
		}
		return new ModelAndView("com/rms/contract/contract-addPayment");
	}
	
	
	
	/**
	 * RMS_合同管理--付款信息列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "goPayments")
	public ModelAndView goPayments(ContractEntity contract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contract.getId())) {
			contract = contractService.getEntity(ContractEntity.class, contract.getId());
			req.setAttribute("contractPage", contract);
		}
		return new ModelAndView("com/rms/contract/contract-payment");
	}
	
	//代理人
	@RequestMapping(params = "goSetProxy")
	public ModelAndView goSetProxy(ContractEntity contract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contract.getId())) {
			contract = contractService.getEntity(ContractEntity.class, contract.getId());
			req.setAttribute("contractPage", contract);
		}
		return new ModelAndView("com/rms/contract/contract-proxy");
	}
	
	
	/**
	 * RMS_合同管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ContractEntity contract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contract.getId())) {
			contract = contractService.getEntity(ContractEntity.class, contract.getId());
			req.setAttribute("contractPage", contract);
		}
		return new ModelAndView("com/rms/contract/contract-add");
	}
	/**
	 * RMS_合同管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ContractEntity contract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contract.getId())) {
			contract = contractService.getEntity(ContractEntity.class, contract.getId());
			req.setAttribute("contractPage", contract);
		}
		return new ModelAndView("com/rms/contract/contract-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","contractController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ContractEntity contract,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ContractEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contract, request.getParameterMap());
		List<ContractEntity> contracts = this.contractService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_合同管理");
		modelMap.put(NormalExcelConstants.CLASS,ContractEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_合同管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,contracts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ContractEntity contract,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_合同管理");
    	modelMap.put(NormalExcelConstants.CLASS,ContractEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_合同管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ContractEntity> listContractEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ContractEntity.class,params);
				for (ContractEntity contract : listContractEntitys) {
					contractService.save(contract);
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
	
	/**
	 * 获取文件附件信息
	 * 
	 * @param id contract主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="RMS_合同管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ContractEntity>> list() {
		List<ContractEntity> listContracts=contractService.getList(ContractEntity.class);
		return Result.success(listContracts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_合同管理信息",notes="根据ID获取RMS_合同管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ContractEntity task = contractService.get(ContractEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_合同管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_合同管理")
	public ResponseMessage<?> create(@ApiParam(name="RMS_合同管理对象")@RequestBody ContractEntity contract, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractEntity>> failures = validator.validate(contract);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractService.save(contract);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_合同管理信息保存失败");
		}
		return Result.success(contract);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_合同管理",notes="更新RMS_合同管理")
	public ResponseMessage<?> update(@ApiParam(name="RMS_合同管理对象")@RequestBody ContractEntity contract) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractEntity>> failures = validator.validate(contract);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractService.saveOrUpdate(contract);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_合同管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_合同管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_合同管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			contractService.deleteEntityById(ContractEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_合同管理删除失败");
		}

		return Result.success();
	}
}

package com.jeecg.bankcode.controller;
import com.jeecg.bankcode.entity.BankCodeEntity;
import com.jeecg.bankcode.service.BankCodeServiceI;
import com.jeecg.weekrep.entity.OrgptFucmodCustomerEntity;

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
 * @Description: 银行编码
 * @author onlineGenerator
 * @date 2018-07-04 10:02:10
 * @version V1.0   
 *
 */
@Api(value="BankCode",description="银行编码",tags="bankCodeController")
@Controller
@RequestMapping("/bankCodeController")
public class BankCodeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankCodeController.class);

	@Autowired
	private BankCodeServiceI bankCodeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 获取银行实体
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getBankEntity")
	@ResponseBody
	public AjaxJson getBankEntity(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		List<BankCodeEntity> bank = null;
		j.setAttributes(attrMap);
		String hql = "from BankCodeEntity";
		bank = systemService.findByQueryString(hql);
		if(bank != null){
			attrMap.put("bank", bank);
		}else {
			j.setSuccess(false);
			
		}
		return j;
	}

	/**
	 * 银行编码列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/bankcode/bankCodeList");
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
	public void datagrid(BankCodeEntity bankCode,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankCodeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankCode, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankCodeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除银行编码
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BankCodeEntity bankCode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		bankCode = systemService.getEntity(BankCodeEntity.class, bankCode.getId());
		message = "银行编码删除成功";
		try{
			bankCodeService.delete(bankCode);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行编码删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除银行编码
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行编码删除成功";
		try{
			for(String id:ids.split(",")){
				BankCodeEntity bankCode = systemService.getEntity(BankCodeEntity.class, 
				id
				);
				bankCodeService.delete(bankCode);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "银行编码删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加银行编码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BankCodeEntity bankCode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行编码添加成功";
		try{
			bankCodeService.save(bankCode);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行编码添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新银行编码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BankCodeEntity bankCode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "银行编码更新成功";
		BankCodeEntity t = bankCodeService.get(BankCodeEntity.class, bankCode.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bankCode, t);
			bankCodeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "银行编码更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 银行编码新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BankCodeEntity bankCode, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankCode.getId())) {
			bankCode = bankCodeService.getEntity(BankCodeEntity.class, bankCode.getId());
			req.setAttribute("bankCodePage", bankCode);
		}
		return new ModelAndView("com/jeecg/bankcode/bankCode-add");
	}
	/**
	 * 银行编码编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BankCodeEntity bankCode, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankCode.getId())) {
			bankCode = bankCodeService.getEntity(BankCodeEntity.class, bankCode.getId());
			req.setAttribute("bankCodePage", bankCode);
		}
		return new ModelAndView("com/jeecg/bankcode/bankCode-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","bankCodeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BankCodeEntity bankCode,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BankCodeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankCode, request.getParameterMap());
		List<BankCodeEntity> bankCodes = this.bankCodeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"银行编码");
		modelMap.put(NormalExcelConstants.CLASS,BankCodeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("银行编码列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,bankCodes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BankCodeEntity bankCode,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"银行编码");
    	modelMap.put(NormalExcelConstants.CLASS,BankCodeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("银行编码列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BankCodeEntity> listBankCodeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BankCodeEntity.class,params);
				for (BankCodeEntity bankCode : listBankCodeEntitys) {
					bankCodeService.save(bankCode);
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
	@ApiOperation(value="银行编码列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BankCodeEntity>> list() {
		List<BankCodeEntity> listBankCodes=bankCodeService.getList(BankCodeEntity.class);
		return Result.success(listBankCodes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取银行编码信息",notes="根据ID获取银行编码信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BankCodeEntity task = bankCodeService.get(BankCodeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取银行编码信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建银行编码")
	public ResponseMessage<?> create(@ApiParam(name="银行编码对象")@RequestBody BankCodeEntity bankCode, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankCodeEntity>> failures = validator.validate(bankCode);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			bankCodeService.save(bankCode);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("银行编码信息保存失败");
		}
		return Result.success(bankCode);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新银行编码",notes="更新银行编码")
	public ResponseMessage<?> update(@ApiParam(name="银行编码对象")@RequestBody BankCodeEntity bankCode) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankCodeEntity>> failures = validator.validate(bankCode);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			bankCodeService.saveOrUpdate(bankCode);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新银行编码信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新银行编码信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除银行编码")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			bankCodeService.deleteEntityById(BankCodeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("银行编码删除失败");
		}

		return Result.success();
	}
}

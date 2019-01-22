package com.jeecg.departmentfee.controller;
import com.jeecg.departmentfee.entity.DepartmentFeeEntity;
import com.jeecg.departmentfee.service.DepartmentFeeServiceI;
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
 * @Description: 部门支出
 * @author onlineGenerator
 * @date 2018-08-17 17:32:31
 * @version V1.0   
 *
 */
@Api(value="DepartmentFee",description="部门支出",tags="departmentFeeController")
@Controller
@RequestMapping("/departmentFeeController")
public class DepartmentFeeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DepartmentFeeController.class);

	@Autowired
	private DepartmentFeeServiceI departmentFeeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 部门支出列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/departmentfee/departmentFeeList");
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
	public void datagrid(DepartmentFeeEntity departmentFee,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DepartmentFeeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, departmentFee, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_feeDate_begin = request.getParameter("feeDate_begin");
		String query_feeDate_end = request.getParameter("feeDate_end");
		if(StringUtil.isNotEmpty(query_feeDate_begin)){
			cq.ge("feeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_feeDate_begin));
		}
		if(StringUtil.isNotEmpty(query_feeDate_end)){
			cq.le("feeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_feeDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.departmentFeeService.getDataGridReturn(cq, true);
		
		dataGrid.setFooter("feeAmount,feeDate:合计");
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除部门支出
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DepartmentFeeEntity departmentFee, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		departmentFee = systemService.getEntity(DepartmentFeeEntity.class, departmentFee.getId());
		message = "部门支出删除成功";
		try{
			departmentFeeService.delete(departmentFee);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "部门支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除部门支出
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门支出删除成功";
		try{
			for(String id:ids.split(",")){
				DepartmentFeeEntity departmentFee = systemService.getEntity(DepartmentFeeEntity.class, 
				id
				);
				departmentFeeService.delete(departmentFee);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "部门支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加部门支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(DepartmentFeeEntity departmentFee, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门支出添加成功";
		try{
			departmentFeeService.save(departmentFee);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "部门支出添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新部门支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(DepartmentFeeEntity departmentFee, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门支出更新成功";
		DepartmentFeeEntity t = departmentFeeService.get(DepartmentFeeEntity.class, departmentFee.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(departmentFee, t);
			departmentFeeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "部门支出更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 部门支出新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DepartmentFeeEntity departmentFee, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(departmentFee.getId())) {
			departmentFee = departmentFeeService.getEntity(DepartmentFeeEntity.class, departmentFee.getId());
			req.setAttribute("departmentFeePage", departmentFee);
		}
		return new ModelAndView("com/jeecg/departmentfee/departmentFee-add");
	}
	/**
	 * 部门支出编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DepartmentFeeEntity departmentFee, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(departmentFee.getId())) {
			departmentFee = departmentFeeService.getEntity(DepartmentFeeEntity.class, departmentFee.getId());
			req.setAttribute("departmentFeePage", departmentFee);
		}
		return new ModelAndView("com/jeecg/departmentfee/departmentFee-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","departmentFeeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DepartmentFeeEntity departmentFee,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DepartmentFeeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, departmentFee, request.getParameterMap());
		List<DepartmentFeeEntity> departmentFees = this.departmentFeeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"部门支出");
		modelMap.put(NormalExcelConstants.CLASS,DepartmentFeeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("部门支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,departmentFees);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DepartmentFeeEntity departmentFee,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"部门支出");
    	modelMap.put(NormalExcelConstants.CLASS,DepartmentFeeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("部门支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<DepartmentFeeEntity> listDepartmentFeeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DepartmentFeeEntity.class,params);
				for (DepartmentFeeEntity departmentFee : listDepartmentFeeEntitys) {
					departmentFeeService.save(departmentFee);
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
	@ApiOperation(value="部门支出列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<DepartmentFeeEntity>> list() {
		List<DepartmentFeeEntity> listDepartmentFees=departmentFeeService.getList(DepartmentFeeEntity.class);
		return Result.success(listDepartmentFees);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取部门支出信息",notes="根据ID获取部门支出信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		DepartmentFeeEntity task = departmentFeeService.get(DepartmentFeeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取部门支出信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建部门支出")
	public ResponseMessage<?> create(@ApiParam(name="部门支出对象")@RequestBody DepartmentFeeEntity departmentFee, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DepartmentFeeEntity>> failures = validator.validate(departmentFee);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			departmentFeeService.save(departmentFee);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("部门支出信息保存失败");
		}
		return Result.success(departmentFee);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新部门支出",notes="更新部门支出")
	public ResponseMessage<?> update(@ApiParam(name="部门支出对象")@RequestBody DepartmentFeeEntity departmentFee) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DepartmentFeeEntity>> failures = validator.validate(departmentFee);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			departmentFeeService.saveOrUpdate(departmentFee);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新部门支出信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新部门支出信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除部门支出")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			departmentFeeService.deleteEntityById(DepartmentFeeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("部门支出删除失败");
		}

		return Result.success();
	}
}

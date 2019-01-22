package com.jeecg.feeroutine.controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.jeecg.feeroutine.entity.FeeRoutineEntity;
import com.jeecg.feeroutine.service.FeeRoutineServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 部门日常费用
 * @author onlineGenerator
 * @date 2018-08-17 17:33:23
 * @version V1.0   
 *
 */
@Api(value="FeeRoutine",description="部门日常费用",tags="feeRoutineController")
@Controller
@RequestMapping("/feeRoutineController")
public class FeeRoutineController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FeeRoutineController.class);

	@Autowired
	private FeeRoutineServiceI feeRoutineService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 部门日常费用列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/feeroutine/feeRoutineList");
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
	public void datagrid(FeeRoutineEntity feeRoutine,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FeeRoutineEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, feeRoutine, request.getParameterMap());
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
		String query_amount_begin = request.getParameter("amount_begin");
		String query_amount_end = request.getParameter("amount_end");
		if(StringUtil.isNotEmpty(query_amount_begin)){
			cq.ge("amount", BigDecimal.valueOf(Double.valueOf(query_amount_begin)));
		}
		if(StringUtil.isNotEmpty(query_amount_end)){
			cq.le("amount", BigDecimal.valueOf(Double.valueOf(query_amount_end)));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.feeRoutineService.getDataGridReturn(cq, true);
		dataGrid.setFooter("amount,name:合计");
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除部门日常费用
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FeeRoutineEntity feeRoutine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		feeRoutine = systemService.getEntity(FeeRoutineEntity.class, feeRoutine.getId());
		message = "部门日常费用删除成功";
		try{
			feeRoutineService.delete(feeRoutine);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "部门日常费用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除部门日常费用
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门日常费用删除成功";
		try{
			for(String id:ids.split(",")){
				FeeRoutineEntity feeRoutine = systemService.getEntity(FeeRoutineEntity.class, 
				id
				);
				feeRoutineService.delete(feeRoutine);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "部门日常费用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加部门日常费用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FeeRoutineEntity feeRoutine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门日常费用添加成功";
		try{
			feeRoutineService.save(feeRoutine);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "部门日常费用添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新部门日常费用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FeeRoutineEntity feeRoutine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部门日常费用更新成功";
		FeeRoutineEntity t = feeRoutineService.get(FeeRoutineEntity.class, feeRoutine.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(feeRoutine, t);
			feeRoutineService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "部门日常费用更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
 	/**
	 * 自定义按钮-[复制一行]业务
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doLine_copy")
	@ResponseBody
	public AjaxJson doLine_copy(FeeRoutineEntity feeRoutine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "复制一行成功";
		FeeRoutineEntity t = feeRoutineService.get(FeeRoutineEntity.class, feeRoutine.getId());
		try{
			feeRoutineService.doLine_copyBus(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "复制一行失败";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 部门日常费用新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FeeRoutineEntity feeRoutine, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(feeRoutine.getId())) {
			feeRoutine = feeRoutineService.getEntity(FeeRoutineEntity.class, feeRoutine.getId());
			req.setAttribute("feeRoutinePage", feeRoutine);
		}
		return new ModelAndView("com/jeecg/feeroutine/feeRoutine-add");
	}
	/**
	 * 部门日常费用编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FeeRoutineEntity feeRoutine, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(feeRoutine.getId())) {
			feeRoutine = feeRoutineService.getEntity(FeeRoutineEntity.class, feeRoutine.getId());
			req.setAttribute("feeRoutinePage", feeRoutine);
		}
		return new ModelAndView("com/jeecg/feeroutine/feeRoutine-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","feeRoutineController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FeeRoutineEntity feeRoutine,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FeeRoutineEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, feeRoutine, request.getParameterMap());
		List<FeeRoutineEntity> feeRoutines = this.feeRoutineService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"部门日常费用");
		modelMap.put(NormalExcelConstants.CLASS,FeeRoutineEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("部门日常费用列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,feeRoutines);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FeeRoutineEntity feeRoutine,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"部门日常费用");
    	modelMap.put(NormalExcelConstants.CLASS,FeeRoutineEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("部门日常费用列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FeeRoutineEntity> listFeeRoutineEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FeeRoutineEntity.class,params);
				for (FeeRoutineEntity feeRoutine : listFeeRoutineEntitys) {
					feeRoutineService.save(feeRoutine);
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
	@ApiOperation(value="部门日常费用列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<FeeRoutineEntity>> list() {
		List<FeeRoutineEntity> listFeeRoutines=feeRoutineService.getList(FeeRoutineEntity.class);
		return Result.success(listFeeRoutines);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取部门日常费用信息",notes="根据ID获取部门日常费用信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		FeeRoutineEntity task = feeRoutineService.get(FeeRoutineEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取部门日常费用信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建部门日常费用")
	public ResponseMessage<?> create(@ApiParam(name="部门日常费用对象")@RequestBody FeeRoutineEntity feeRoutine, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FeeRoutineEntity>> failures = validator.validate(feeRoutine);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			feeRoutineService.save(feeRoutine);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("部门日常费用信息保存失败");
		}
		return Result.success(feeRoutine);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新部门日常费用",notes="更新部门日常费用")
	public ResponseMessage<?> update(@ApiParam(name="部门日常费用对象")@RequestBody FeeRoutineEntity feeRoutine) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FeeRoutineEntity>> failures = validator.validate(feeRoutine);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			feeRoutineService.saveOrUpdate(feeRoutine);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新部门日常费用信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新部门日常费用信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除部门日常费用")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			feeRoutineService.deleteEntityById(FeeRoutineEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("部门日常费用删除失败");
		}

		return Result.success();
	}
}

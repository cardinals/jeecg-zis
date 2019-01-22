package com.jeecg.rms_hardware.controller;
import com.jeecg.rms_hardware.entity.RmsHardwareEntity;
import com.jeecg.rms_hardware.service.RmsHardwareServiceI;
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
 * @Description: rms_hardware
 * @author onlineGenerator
 * @date 2018-03-28 15:05:05
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/rmsHardwareController")
@Api(value="RmsHardware",description="rms_hardware",tags="rmsHardwareController")
public class RmsHardwareController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RmsHardwareController.class);

	@Autowired
	private RmsHardwareServiceI rmsHardwareService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * rms_hardware列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/rms_hardware/rmsHardwareList");
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
	public void datagrid(RmsHardwareEntity rmsHardware,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RmsHardwareEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsHardware, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_purchaseDate_begin = request.getParameter("purchaseDate_begin");
		String query_purchaseDate_end = request.getParameter("purchaseDate_end");
		if(StringUtil.isNotEmpty(query_purchaseDate_begin)){
			cq.ge("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_begin));
		}
		if(StringUtil.isNotEmpty(query_purchaseDate_end)){
			cq.le("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.rmsHardwareService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除rms_hardware
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(RmsHardwareEntity rmsHardware, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		rmsHardware = systemService.getEntity(RmsHardwareEntity.class, rmsHardware.getId());
		message = "rms_hardware删除成功";
		try{
			rmsHardwareService.delete(rmsHardware);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "rms_hardware删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除rms_hardware
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "rms_hardware删除成功";
		try{
			for(String id:ids.split(",")){
				RmsHardwareEntity rmsHardware = systemService.getEntity(RmsHardwareEntity.class, 
				Integer.parseInt(id)
				);
				rmsHardwareService.delete(rmsHardware);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "rms_hardware删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加rms_hardware
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(RmsHardwareEntity rmsHardware, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "rms_hardware添加成功";
		try{
			rmsHardwareService.save(rmsHardware);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "rms_hardware添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新rms_hardware
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(RmsHardwareEntity rmsHardware, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "rms_hardware更新成功";
		RmsHardwareEntity t = rmsHardwareService.get(RmsHardwareEntity.class, rmsHardware.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(rmsHardware, t);
			rmsHardwareService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "rms_hardware更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * rms_hardware新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(RmsHardwareEntity rmsHardware, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsHardware.getId())) {
			rmsHardware = rmsHardwareService.getEntity(RmsHardwareEntity.class, rmsHardware.getId());
			req.setAttribute("rmsHardwarePage", rmsHardware);
		}
		return new ModelAndView("com/jeecg/rms_hardware/rmsHardware-add");
	}
	/**
	 * rms_hardware编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(RmsHardwareEntity rmsHardware, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsHardware.getId())) {
			rmsHardware = rmsHardwareService.getEntity(RmsHardwareEntity.class, rmsHardware.getId());
			req.setAttribute("rmsHardwarePage", rmsHardware);
		}
		return new ModelAndView("com/jeecg/rms_hardware/rmsHardware-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","rmsHardwareController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(RmsHardwareEntity rmsHardware,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(RmsHardwareEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsHardware, request.getParameterMap());
		List<RmsHardwareEntity> rmsHardwares = this.rmsHardwareService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"rms_hardware");
		modelMap.put(NormalExcelConstants.CLASS,RmsHardwareEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("rms_hardware列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,rmsHardwares);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(RmsHardwareEntity rmsHardware,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"rms_hardware");
    	modelMap.put(NormalExcelConstants.CLASS,RmsHardwareEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("rms_hardware列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<RmsHardwareEntity> listRmsHardwareEntitys = ExcelImportUtil.importExcel(file.getInputStream(),RmsHardwareEntity.class,params);
				for (RmsHardwareEntity rmsHardware : listRmsHardwareEntitys) {
					rmsHardwareService.save(rmsHardware);
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
	@ApiOperation(value="rms_hardware列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<RmsHardwareEntity>> list() {
		List<RmsHardwareEntity> listRmsHardwares=rmsHardwareService.getList(RmsHardwareEntity.class);
		return Result.success(listRmsHardwares);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取rms_hardware信息",notes="根据ID获取rms_hardware信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		RmsHardwareEntity task = rmsHardwareService.get(RmsHardwareEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取rms_hardware信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建rms_hardware")
	public ResponseMessage<?> create(@ApiParam(name="rms_hardware对象")@RequestBody RmsHardwareEntity rmsHardware, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsHardwareEntity>> failures = validator.validate(rmsHardware);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsHardwareService.save(rmsHardware);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("rms_hardware信息保存失败");
		}
		return Result.success(rmsHardware);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新rms_hardware",notes="更新rms_hardware")
	public ResponseMessage<?> update(@ApiParam(name="rms_hardware对象")@RequestBody RmsHardwareEntity rmsHardware) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsHardwareEntity>> failures = validator.validate(rmsHardware);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsHardwareService.saveOrUpdate(rmsHardware);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新rms_hardware信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新rms_hardware信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除rms_hardware")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			rmsHardwareService.deleteEntityById(RmsHardwareEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("rms_hardware删除失败");
		}

		return Result.success();
	}
}

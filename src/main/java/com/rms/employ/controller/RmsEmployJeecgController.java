package com.rms.employ.controller;
import com.rms.employ.entity.RmsEmployJeecgEntity;
import com.rms.employ.service.RmsEmployJeecgServiceI;

import generator.pas.onstaff.entity.PasOnStaffEntity;

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
import org.jeecgframework.core.util.DynamicDBUtil;
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
 * @Description: RMS_员工管理
 * @author onlineGenerator
 * @date 2018-10-15 11:30:09
 * @version V1.0   
 *
 */
@Api(value="RmsEmployJeecg",description="RMS_员工管理",tags="rmsEmployJeecgController")
@Controller
@RequestMapping("/rmsEmployJeecgController")
public class RmsEmployJeecgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RmsEmployJeecgController.class);

	@Autowired
	private RmsEmployJeecgServiceI rmsEmployJeecgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	private final static  String dbKey = "OA";


	/**
	 * RMS_员工管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/employ/rmsEmployJeecgList");
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
	public void datagrid(RmsEmployJeecgEntity rmsEmployJeecg,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RmsEmployJeecgEntity.class, dataGrid);
		//查询条件组装器
	
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsEmployJeecg, request.getParameterMap());
		//String sql="select * from V3XUSER.ORG_MEMBER WHERE ORG_DEPARTMENT_ID!=-1";
		String sql="select m.*,o.name orgname  from V3XUSER.ORG_MEMBER m left join V3XUSER.ORG_UNIT o on o.id=m.org_department_id WHERE ORG_DEPARTMENT_ID!=-1";
		
		List<Map<String,Object>> maps = (List<Map<String,Object>>)DynamicDBUtil.findList(dbKey, sql, null);
		
		List<RmsEmployJeecgEntity> result = rmsEmployJeecgService.getEmployeeDataGridList(maps);
		dataGrid.setResults(result);
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除RMS_员工管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(RmsEmployJeecgEntity rmsEmployJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		rmsEmployJeecg = systemService.getEntity(RmsEmployJeecgEntity.class, rmsEmployJeecg.getId());
		message = "RMS_员工管理删除成功";
		try{
			rmsEmployJeecgService.delete(rmsEmployJeecg);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_员工管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_员工管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_员工管理删除成功";
		try{
			for(String id:ids.split(",")){
				RmsEmployJeecgEntity rmsEmployJeecg = systemService.getEntity(RmsEmployJeecgEntity.class, 
				id
				);
				rmsEmployJeecgService.delete(rmsEmployJeecg);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_员工管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加RMS_员工管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(RmsEmployJeecgEntity rmsEmployJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_员工管理添加成功";
		try{
			rmsEmployJeecgService.save(rmsEmployJeecg);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_员工管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新RMS_员工管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(RmsEmployJeecgEntity rmsEmployJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_员工管理更新成功";
		RmsEmployJeecgEntity t = rmsEmployJeecgService.get(RmsEmployJeecgEntity.class, rmsEmployJeecg.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(rmsEmployJeecg, t);
			rmsEmployJeecgService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_员工管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * RMS_员工管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(RmsEmployJeecgEntity rmsEmployJeecg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsEmployJeecg.getId())) {
			rmsEmployJeecg = rmsEmployJeecgService.getEntity(RmsEmployJeecgEntity.class, rmsEmployJeecg.getId());
			req.setAttribute("rmsEmployJeecgPage", rmsEmployJeecg);
		}
		return new ModelAndView("com/rms/employ/rmsEmployJeecg-add");
	}
	/**
	 * RMS_员工管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(RmsEmployJeecgEntity rmsEmployJeecg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsEmployJeecg.getId())) {
			rmsEmployJeecg = rmsEmployJeecgService.getEntity(RmsEmployJeecgEntity.class, rmsEmployJeecg.getId());
			req.setAttribute("rmsEmployJeecgPage", rmsEmployJeecg);
		}
		return new ModelAndView("com/rms/employ/rmsEmployJeecg-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","rmsEmployJeecgController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(RmsEmployJeecgEntity rmsEmployJeecg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(RmsEmployJeecgEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsEmployJeecg, request.getParameterMap());
		List<RmsEmployJeecgEntity> rmsEmployJeecgs = this.rmsEmployJeecgService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_员工管理");
		modelMap.put(NormalExcelConstants.CLASS,RmsEmployJeecgEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_员工管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,rmsEmployJeecgs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(RmsEmployJeecgEntity rmsEmployJeecg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_员工管理");
    	modelMap.put(NormalExcelConstants.CLASS,RmsEmployJeecgEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_员工管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<RmsEmployJeecgEntity> listRmsEmployJeecgEntitys = ExcelImportUtil.importExcel(file.getInputStream(),RmsEmployJeecgEntity.class,params);
				for (RmsEmployJeecgEntity rmsEmployJeecg : listRmsEmployJeecgEntitys) {
					rmsEmployJeecgService.save(rmsEmployJeecg);
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
	@ApiOperation(value="RMS_员工管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<RmsEmployJeecgEntity>> list() {
		List<RmsEmployJeecgEntity> listRmsEmployJeecgs=rmsEmployJeecgService.getList(RmsEmployJeecgEntity.class);
		return Result.success(listRmsEmployJeecgs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_员工管理信息",notes="根据ID获取RMS_员工管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		RmsEmployJeecgEntity task = rmsEmployJeecgService.get(RmsEmployJeecgEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_员工管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_员工管理")
	public ResponseMessage<?> create(@ApiParam(name="RMS_员工管理对象")@RequestBody RmsEmployJeecgEntity rmsEmployJeecg, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsEmployJeecgEntity>> failures = validator.validate(rmsEmployJeecg);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsEmployJeecgService.save(rmsEmployJeecg);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_员工管理信息保存失败");
		}
		return Result.success(rmsEmployJeecg);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_员工管理",notes="更新RMS_员工管理")
	public ResponseMessage<?> update(@ApiParam(name="RMS_员工管理对象")@RequestBody RmsEmployJeecgEntity rmsEmployJeecg) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsEmployJeecgEntity>> failures = validator.validate(rmsEmployJeecg);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsEmployJeecgService.saveOrUpdate(rmsEmployJeecg);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_员工管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_员工管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_员工管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			rmsEmployJeecgService.deleteEntityById(RmsEmployJeecgEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_员工管理删除失败");
		}

		return Result.success();
	}
}

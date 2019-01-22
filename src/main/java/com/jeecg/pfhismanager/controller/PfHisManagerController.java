package com.jeecg.pfhismanager.controller;
import com.jeecg.pfhismanager.entity.PfHisManagerEntity;
import com.jeecg.pfhismanager.service.PfHisManagerServiceI;
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
import org.jeecgframework.tag.vo.datatable.SortDirection;
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
 * @Description: 历任基金经理
 * @author onlineGenerator
 * @date 2019-01-17 15:51:31
 * @version V1.0   
 *
 */
@Api(value="PfHisManager",description="历任基金经理",tags="pfHisManagerController")
@Controller
@RequestMapping("/pfHisManagerController")
public class PfHisManagerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PfHisManagerController.class);

	@Autowired
	private PfHisManagerServiceI pfHisManagerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 历任基金经理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/pfhismanager/pfHisManagerList");
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
	public void datagrid(PfHisManagerEntity pfHisManager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PfHisManagerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pfHisManager, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.pfHisManagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "datagridHis")
	public void datagridHis(PfHisManagerEntity pfHisManager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PfHisManagerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pfHisManager, request.getParameterMap());
		String paramId = request.getParameter("paramId");
		try{
		if(!"".equals(paramId)) {
			cq.eq("foreignKey", paramId);
		}
		cq.addOrder("emdTime", SortDirection.desc);
		cq.addOrder("startTime", SortDirection.desc);
		
		//cq.isNotNull("emdTime");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.pfHisManagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除历任基金经理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PfHisManagerEntity pfHisManager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pfHisManager = systemService.getEntity(PfHisManagerEntity.class, pfHisManager.getId());
		message = "历任基金经理删除成功";
		try{
			pfHisManagerService.delete(pfHisManager);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "历任基金经理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除历任基金经理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "历任基金经理删除成功";
		try{
			for(String id:ids.split(",")){
				PfHisManagerEntity pfHisManager = systemService.getEntity(PfHisManagerEntity.class, 
				id
				);
				pfHisManagerService.delete(pfHisManager);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "历任基金经理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加历任基金经理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PfHisManagerEntity pfHisManager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "历任基金经理添加成功";
		try{
			pfHisManagerService.save(pfHisManager);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "历任基金经理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新历任基金经理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PfHisManagerEntity pfHisManager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "历任基金经理更新成功";
		PfHisManagerEntity t = pfHisManagerService.get(PfHisManagerEntity.class, pfHisManager.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pfHisManager, t);
			pfHisManagerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "历任基金经理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 历任基金经理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PfHisManagerEntity pfHisManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pfHisManager.getId())) {
			pfHisManager = pfHisManagerService.getEntity(PfHisManagerEntity.class, pfHisManager.getId());
			req.setAttribute("pfHisManagerPage", pfHisManager);
		}
		return new ModelAndView("com/jeecg/pfhismanager/pfHisManager-add");
	}
	/**
	 * 历任基金经理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PfHisManagerEntity pfHisManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pfHisManager.getId())) {
			pfHisManager = pfHisManagerService.getEntity(PfHisManagerEntity.class, pfHisManager.getId());
			req.setAttribute("pfHisManagerPage", pfHisManager);
		}
		return new ModelAndView("com/jeecg/pfhismanager/pfHisManager-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pfHisManagerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PfHisManagerEntity pfHisManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PfHisManagerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pfHisManager, request.getParameterMap());
		List<PfHisManagerEntity> pfHisManagers = this.pfHisManagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"历任基金经理");
		modelMap.put(NormalExcelConstants.CLASS,PfHisManagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("历任基金经理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pfHisManagers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PfHisManagerEntity pfHisManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"历任基金经理");
    	modelMap.put(NormalExcelConstants.CLASS,PfHisManagerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("历任基金经理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PfHisManagerEntity> listPfHisManagerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PfHisManagerEntity.class,params);
				for (PfHisManagerEntity pfHisManager : listPfHisManagerEntitys) {
					pfHisManagerService.save(pfHisManager);
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
	@ApiOperation(value="历任基金经理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PfHisManagerEntity>> list() {
		List<PfHisManagerEntity> listPfHisManagers=pfHisManagerService.getList(PfHisManagerEntity.class);
		return Result.success(listPfHisManagers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取历任基金经理信息",notes="根据ID获取历任基金经理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PfHisManagerEntity task = pfHisManagerService.get(PfHisManagerEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取历任基金经理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建历任基金经理")
	public ResponseMessage<?> create(@ApiParam(name="历任基金经理对象")@RequestBody PfHisManagerEntity pfHisManager, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PfHisManagerEntity>> failures = validator.validate(pfHisManager);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pfHisManagerService.save(pfHisManager);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("历任基金经理信息保存失败");
		}
		return Result.success(pfHisManager);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新历任基金经理",notes="更新历任基金经理")
	public ResponseMessage<?> update(@ApiParam(name="历任基金经理对象")@RequestBody PfHisManagerEntity pfHisManager) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PfHisManagerEntity>> failures = validator.validate(pfHisManager);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pfHisManagerService.saveOrUpdate(pfHisManager);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新历任基金经理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新历任基金经理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除历任基金经理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pfHisManagerService.deleteEntityById(PfHisManagerEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("历任基金经理删除失败");
		}

		return Result.success();
	}
}

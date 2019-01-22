package com.jeecg.manager.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.mana.entity.ManagEntity;
import com.jeecg.manager.entity.AddmanagerEntity;
import com.jeecg.manager.entity.Cons;
import com.jeecg.manager.service.AddmanagerServiceI;
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
import org.springframework.web.bind.annotation.RequestParam;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 动态增加属性
 * @author onlineGenerator
 * @date 2019-01-04 14:07:36
 * @version V1.0   
 *
 */
@Api(value="Addmanager",description="动态增加属性",tags="addmanagerController")
@Controller
@RequestMapping("/addmanagerController")
public class AddmanagerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AddmanagerController.class);

	@Autowired
	private AddmanagerServiceI addmanagerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 动态增加属性列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/manager/addmanagerList");
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
	public void datagrid(AddmanagerEntity addmanager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AddmanagerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, addmanager, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.addmanagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除动态增加属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AddmanagerEntity addmanager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		addmanager = systemService.getEntity(AddmanagerEntity.class, addmanager.getId());
		message = "动态增加属性删除成功";
		try{
			addmanagerService.delete(addmanager);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "动态增加属性删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除动态增加属性
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "动态增加属性删除成功";
		try{
			for(String id:ids.split(",")){
				AddmanagerEntity addmanager = systemService.getEntity(AddmanagerEntity.class, 
				id
				);
				addmanagerService.delete(addmanager);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "动态增加属性删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加动态增加属性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(@RequestParam(value = "_sqlbuilder" ) String managers,  AddmanagerEntity addmanager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "动态增加属性添加成功";
		try{
			List<ManagEntity> manas = JSONArray.parseArray(managers, ManagEntity.class);
			addmanagerService.addMain(addmanager, manas);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "动态增加属性添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新动态增加属性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(@RequestParam(value = "_sqlbuilder" ) String managers,  AddmanagerEntity addmanager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "动态增加属性更新成功";
		AddmanagerEntity t = addmanagerService.get(AddmanagerEntity.class, addmanager.getId());
		try {
			List<ManagEntity> manas = JSONArray.parseArray(managers, ManagEntity.class);
			MyBeanUtils.copyBeanNotNull2Bean(addmanager, t);
			//addmanagerService.saveOrUpdate(t);
			addmanagerService.updateMain(t,manas);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "动态增加属性更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 动态增加属性新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AddmanagerEntity addmanager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(addmanager.getId())) {
			addmanager = addmanagerService.getEntity(AddmanagerEntity.class, addmanager.getId());
			//查询附表信息
			req.setAttribute("addmanagerPage", addmanager);
		}
		return new ModelAndView("com/jeecg/manager/addmanager-add");
	}
	/**
	 * 动态增加属性编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AddmanagerEntity addmanager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(addmanager.getId())) {
			addmanager = addmanagerService.getEntity(AddmanagerEntity.class, addmanager.getId());
		    List<ManagEntity> manas = addmanagerService.findByProperty(ManagEntity.class, "foreignKey", addmanager.getId());
		    //将集合转化为 json对象
		    
		    //1、使用JSONObject
	        String listObject=JSONObject.toJSONString(manas);
		    req.setAttribute("addmanagerPage", addmanager);
			req.setAttribute("manas", listObject);
		}
		return new ModelAndView("com/jeecg/manager/addmanager-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","addmanagerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AddmanagerEntity addmanager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AddmanagerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, addmanager, request.getParameterMap());
		List<AddmanagerEntity> addmanagers = this.addmanagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"动态增加属性");
		modelMap.put(NormalExcelConstants.CLASS,AddmanagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("动态增加属性列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,addmanagers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AddmanagerEntity addmanager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"动态增加属性");
    	modelMap.put(NormalExcelConstants.CLASS,AddmanagerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("动态增加属性列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<AddmanagerEntity> listAddmanagerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AddmanagerEntity.class,params);
				for (AddmanagerEntity addmanager : listAddmanagerEntitys) {
					addmanagerService.save(addmanager);
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
	@ApiOperation(value="动态增加属性列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<AddmanagerEntity>> list() {
		List<AddmanagerEntity> listAddmanagers=addmanagerService.getList(AddmanagerEntity.class);
		return Result.success(listAddmanagers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取动态增加属性信息",notes="根据ID获取动态增加属性信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		AddmanagerEntity task = addmanagerService.get(AddmanagerEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取动态增加属性信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建动态增加属性")
	public ResponseMessage<?> create(@ApiParam(name="动态增加属性对象")@RequestBody AddmanagerEntity addmanager, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AddmanagerEntity>> failures = validator.validate(addmanager);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			addmanagerService.save(addmanager);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("动态增加属性信息保存失败");
		}
		return Result.success(addmanager);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新动态增加属性",notes="更新动态增加属性")
	public ResponseMessage<?> update(@ApiParam(name="动态增加属性对象")@RequestBody AddmanagerEntity addmanager) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AddmanagerEntity>> failures = validator.validate(addmanager);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			addmanagerService.saveOrUpdate(addmanager);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新动态增加属性信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新动态增加属性信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除动态增加属性")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			addmanagerService.deleteEntityById(AddmanagerEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("动态增加属性删除失败");
		}

		return Result.success();
	}
}

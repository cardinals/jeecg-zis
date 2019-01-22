package com.rms.torms.controller;
import com.rms.torms.entity.ToRmsEntity;
import com.rms.torms.service.ToRmsServiceI;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.util.EncryptUtil;
import org.jeecgframework.web.system.util.ZrjjdanganConstant;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;

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
 * @Description: 连接到rms
 * @author onlineGenerator
 * @date 2018-12-14 11:21:29
 * @version V1.0   
 *
 */
@Api(value="ToRms",description="连接到rms",tags="toRmsController")
@Controller
@RequestMapping("/toRmsController")
public class ToRmsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ToRmsController.class);

	@Autowired
	private ToRmsServiceI toRmsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 连接到rms列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/torms/toRmsList");
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
	public void datagrid(ToRmsEntity toRms,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ToRmsEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, toRms, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.toRmsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除连接到rms
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ToRmsEntity toRms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		toRms = systemService.getEntity(ToRmsEntity.class, toRms.getId());
		message = "连接到rms删除成功";
		try{
			toRmsService.delete(toRms);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "连接到rms删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除连接到rms
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "连接到rms删除成功";
		try{
			for(String id:ids.split(",")){
				ToRmsEntity toRms = systemService.getEntity(ToRmsEntity.class, 
				id
				);
				toRmsService.delete(toRms);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "连接到rms删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加连接到rms
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ToRmsEntity toRms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "连接到rms添加成功";
		try{
			toRmsService.save(toRms);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "连接到rms添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新连接到rms
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ToRmsEntity toRms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "连接到rms更新成功";
		ToRmsEntity t = toRmsService.get(ToRmsEntity.class, toRms.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(toRms, t);
			toRmsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "连接到rms更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 跳转到资源管理系统
	 * @param toRms
	 * @param req
	 * @return
	 */
	
	@RequestMapping(params = "goRMS")
	public String  goRMS(ToRmsEntity toRms, HttpServletRequest req) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		String username = loginUser.getUserName();
		String paw = loginUser.getPassword();
		
		String namejiami = null;
		//String namejiemi = null;
		try {
			namejiami = EncryptUtil.encrypt(username,ZrjjdanganConstant.pwd_sso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//String mingwenPaw = PasswordUtil.decrypt(paw,"123456", PasswordUtil.getStaticSalt());
		System.out.println(namejiami);
		
	/*	try {
			namejiemi = EncryptUtil.decrypt(namejiami,ZrjjdanganConstant.pwd_sso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(namejiemi);*/
		//正式环境
		//return "redirect:http://172.16.105.118:8080/outIndex.xhtml?name="+namejiami;
		//测试环境
		return "redirect:http://172.16.10.58:8080/outIndex.xhtml?name="+namejiami;
		
		
	}
	/**
	 * 连接到rms新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ToRmsEntity toRms, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(toRms.getId())) {
			toRms = toRmsService.getEntity(ToRmsEntity.class, toRms.getId());
			req.setAttribute("toRmsPage", toRms);
		}
		return new ModelAndView("com/rms/torms/toRms-add");
	}
	/**
	 * 连接到rms编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ToRmsEntity toRms, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(toRms.getId())) {
			toRms = toRmsService.getEntity(ToRmsEntity.class, toRms.getId());
			req.setAttribute("toRmsPage", toRms);
		}
		return new ModelAndView("com/rms/torms/toRms-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","toRmsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ToRmsEntity toRms,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ToRmsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, toRms, request.getParameterMap());
		List<ToRmsEntity> toRmss = this.toRmsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"连接到rms");
		modelMap.put(NormalExcelConstants.CLASS,ToRmsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("连接到rms列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,toRmss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ToRmsEntity toRms,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"连接到rms");
    	modelMap.put(NormalExcelConstants.CLASS,ToRmsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("连接到rms列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ToRmsEntity> listToRmsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ToRmsEntity.class,params);
				for (ToRmsEntity toRms : listToRmsEntitys) {
					toRmsService.save(toRms);
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
	@ApiOperation(value="连接到rms列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ToRmsEntity>> list() {
		List<ToRmsEntity> listToRmss=toRmsService.getList(ToRmsEntity.class);
		return Result.success(listToRmss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取连接到rms信息",notes="根据ID获取连接到rms信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ToRmsEntity task = toRmsService.get(ToRmsEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取连接到rms信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建连接到rms")
	public ResponseMessage<?> create(@ApiParam(name="连接到rms对象")@RequestBody ToRmsEntity toRms, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ToRmsEntity>> failures = validator.validate(toRms);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			toRmsService.save(toRms);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("连接到rms信息保存失败");
		}
		return Result.success(toRms);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新连接到rms",notes="更新连接到rms")
	public ResponseMessage<?> update(@ApiParam(name="连接到rms对象")@RequestBody ToRmsEntity toRms) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ToRmsEntity>> failures = validator.validate(toRms);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			toRmsService.saveOrUpdate(toRms);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新连接到rms信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新连接到rms信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除连接到rms")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			toRmsService.deleteEntityById(ToRmsEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("连接到rms删除失败");
		}

		return Result.success();
	}
}

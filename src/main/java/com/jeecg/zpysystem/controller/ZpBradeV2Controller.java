package com.jeecg.zpysystem.controller;
import com.jeecg.zpysystem.entity.ZpBradeV2Entity;
import com.jeecg.zpysystem.service.ZpBradeV2ServiceI;
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
 * @Description: 面试评分表
 * @author onlineGenerator
 * @date 2018-06-05 18:09:42
 * @version V1.0   
 *
 */
@Api(value="ZpBradeV2",description="面试评分表",tags="zpBradeV2Controller")
@Controller
@RequestMapping("/zpBradeV2Controller")
public class ZpBradeV2Controller extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZpBradeV2Controller.class);

	@Autowired
	private ZpBradeV2ServiceI zpBradeV2Service;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 面试评分表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/zpysystem/zpBradeV2List");
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
	public void datagrid(ZpBradeV2Entity zpBradeV2,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZpBradeV2Entity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zpBradeV2, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zpBradeV2Service.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除面试评分表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZpBradeV2Entity zpBradeV2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zpBradeV2 = systemService.getEntity(ZpBradeV2Entity.class, zpBradeV2.getId());
		message = "面试评分表删除成功";
		try{
			zpBradeV2Service.delete(zpBradeV2);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "面试评分表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除面试评分表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "面试评分表删除成功";
		try{
			for(String id:ids.split(",")){
				ZpBradeV2Entity zpBradeV2 = systemService.getEntity(ZpBradeV2Entity.class, 
				id
				);
				zpBradeV2Service.delete(zpBradeV2);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "面试评分表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加面试评分表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZpBradeV2Entity zpBradeV2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "面试评分表添加成功";
		try{
			zpBradeV2Service.save(zpBradeV2);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "面试评分表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新面试评分表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZpBradeV2Entity zpBradeV2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "面试评分表更新成功";
		ZpBradeV2Entity t = zpBradeV2Service.get(ZpBradeV2Entity.class, zpBradeV2.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zpBradeV2, t);
			
			int interview_jishu = zpBradeV2.getGrade1()==null?0:zpBradeV2.getGrade1();
			int interview_zonghe = zpBradeV2.getGrade2()==null?0:zpBradeV2.getGrade2();
			gradeHandle(t,interview_jishu,interview_zonghe);
			
			zpBradeV2Service.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "面试评分表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	private void gradeHandle(ZpBradeV2Entity t,int interview_jishu,int interview_zonghe){
		if(interview_jishu<60&&interview_jishu!=0){
			t.setJpStatus(10);
			return;
		}else if(interview_jishu>=60&&interview_jishu!=0){
			t.setJpStatus(2);
			return;
		}
		if(interview_zonghe<60&&interview_zonghe!=0){
			t.setJpStatus(10);
			return;
		}else if(interview_zonghe>=60&&interview_zonghe!=0){
			t.setJpStatus(3);
			return;
		}
	}

	/**
	 * 面试评分表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZpBradeV2Entity zpBradeV2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpBradeV2.getId())) {
			zpBradeV2 = zpBradeV2Service.getEntity(ZpBradeV2Entity.class, zpBradeV2.getId());
			req.setAttribute("zpBradeV2Page", zpBradeV2);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpBradeV2-add");
	}
	/**
	 * 面试评分表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZpBradeV2Entity zpBradeV2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpBradeV2.getId())) {
			zpBradeV2 = zpBradeV2Service.getEntity(ZpBradeV2Entity.class, zpBradeV2.getId());
			req.setAttribute("zpBradeV2Page", zpBradeV2);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpBradeV2-update");
	}
	
	@RequestMapping(params = "goUpdate_js")
	public ModelAndView goUpdate_js(ZpBradeV2Entity zpBradeV2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpBradeV2.getId())) {
			zpBradeV2 = zpBradeV2Service.getEntity(ZpBradeV2Entity.class, zpBradeV2.getId());
			req.setAttribute("zpBradeV2Page", zpBradeV2);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpBradeV2-update_js");
	}
	
	@RequestMapping(params = "goUpdate_zh")
	public ModelAndView goUpdate_zh(ZpBradeV2Entity zpBradeV2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpBradeV2.getId())) {
			zpBradeV2 = zpBradeV2Service.getEntity(ZpBradeV2Entity.class, zpBradeV2.getId());
			req.setAttribute("zpBradeV2Page", zpBradeV2);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpBradeV2-update_zh");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zpBradeV2Controller");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZpBradeV2Entity zpBradeV2,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZpBradeV2Entity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zpBradeV2, request.getParameterMap());
		List<ZpBradeV2Entity> zpBradeV2s = this.zpBradeV2Service.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"面试评分表");
		modelMap.put(NormalExcelConstants.CLASS,ZpBradeV2Entity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("面试评分表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zpBradeV2s);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZpBradeV2Entity zpBradeV2,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"面试评分表");
    	modelMap.put(NormalExcelConstants.CLASS,ZpBradeV2Entity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("面试评分表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ZpBradeV2Entity> listZpBradeV2Entitys = ExcelImportUtil.importExcel(file.getInputStream(),ZpBradeV2Entity.class,params);
				for (ZpBradeV2Entity zpBradeV2 : listZpBradeV2Entitys) {
					zpBradeV2Service.save(zpBradeV2);
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
	@ApiOperation(value="面试评分表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ZpBradeV2Entity>> list() {
		List<ZpBradeV2Entity> listZpBradeV2s=zpBradeV2Service.getList(ZpBradeV2Entity.class);
		return Result.success(listZpBradeV2s);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取面试评分表信息",notes="根据ID获取面试评分表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ZpBradeV2Entity task = zpBradeV2Service.get(ZpBradeV2Entity.class, id);
		if (task == null) {
			return Result.error("根据ID获取面试评分表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建面试评分表")
	public ResponseMessage<?> create(@ApiParam(name="面试评分表对象")@RequestBody ZpBradeV2Entity zpBradeV2, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZpBradeV2Entity>> failures = validator.validate(zpBradeV2);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			zpBradeV2Service.save(zpBradeV2);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("面试评分表信息保存失败");
		}
		return Result.success(zpBradeV2);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新面试评分表",notes="更新面试评分表")
	public ResponseMessage<?> update(@ApiParam(name="面试评分表对象")@RequestBody ZpBradeV2Entity zpBradeV2) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZpBradeV2Entity>> failures = validator.validate(zpBradeV2);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			zpBradeV2Service.saveOrUpdate(zpBradeV2);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新面试评分表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新面试评分表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除面试评分表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			zpBradeV2Service.deleteEntityById(ZpBradeV2Entity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("面试评分表删除失败");
		}

		return Result.success();
	}
}

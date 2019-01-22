package com.jeecg.zpysystem.controller;
import com.jeecg.zpysystem.entity.ZpGradeEntity;
import com.jeecg.zpysystem.service.ZpGradeServiceI;
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
 * @date 2018-06-05 17:05:30
 * @version V1.0   
 *
 */
@Api(value="ZpGrade",description="面试评分表",tags="zpGradeController")
@Controller
@RequestMapping("/zpGradeController")
public class ZpGradeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZpGradeController.class);

	@Autowired
	private ZpGradeServiceI zpGradeService;
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
	public ModelAndView lis(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/zpysystem/zpGradeList");
	}

	/**
	 * 面试评分表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list_js")//技术面试
	public ModelAndView list_js(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/zpysystem/zpGradeList_js");
	}

	@RequestMapping(params = "list_zh")//综合面试
	public ModelAndView list_zh(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/zpysystem/zpGradeList_zh");
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
	public void datagrid(ZpGradeEntity zpGrade,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZpGradeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zpGrade, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zpGradeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除面试评分表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZpGradeEntity zpGrade, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zpGrade = systemService.getEntity(ZpGradeEntity.class, zpGrade.getId());
		message = "面试评分表删除成功";
		try{
			zpGradeService.delete(zpGrade);
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
				ZpGradeEntity zpGrade = systemService.getEntity(ZpGradeEntity.class, 
				id
				);
				zpGradeService.delete(zpGrade);
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
	public AjaxJson doAdd(ZpGradeEntity zpGrade, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "面试评分表添加成功";
		try{
			zpGradeService.save(zpGrade);
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
	public AjaxJson doUpdate(ZpGradeEntity zpGrade, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "面试评分表更新成功";
		ZpGradeEntity t = zpGradeService.get(ZpGradeEntity.class, zpGrade.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zpGrade, t);
			zpGradeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "面试评分表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 面试评分表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZpGradeEntity zpGrade, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpGrade.getId())) {
			zpGrade = zpGradeService.getEntity(ZpGradeEntity.class, zpGrade.getId());
			req.setAttribute("zpGradePage", zpGrade);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpGrade-add");
	}
	/**
	 * 面试评分表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZpGradeEntity zpGrade, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpGrade.getId())) {
			zpGrade = zpGradeService.getEntity(ZpGradeEntity.class, zpGrade.getId());
			req.setAttribute("zpGradePage", zpGrade);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpGrade-update");
	}
	/**
	 * 技术面试
	 * @param zpGrade
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goUpdate_js")
	public ModelAndView goUpdate_js(ZpGradeEntity zpGrade, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpGrade.getId())) {
			zpGrade = zpGradeService.getEntity(ZpGradeEntity.class, zpGrade.getId());
			req.setAttribute("zpGradePage", zpGrade);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpGrade-update_js");
	}
	/**
	 * 综合面试
	 * @param zpGrade
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goUpdate_zh")
	public ModelAndView goUpdate_zh(ZpGradeEntity zpGrade, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zpGrade.getId())) {
			zpGrade = zpGradeService.getEntity(ZpGradeEntity.class, zpGrade.getId());
			req.setAttribute("zpGradePage", zpGrade);
		}
		return new ModelAndView("com/jeecg/zpysystem/zpGrade-update_zh");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zpGradeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZpGradeEntity zpGrade,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZpGradeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zpGrade, request.getParameterMap());
		List<ZpGradeEntity> zpGrades = this.zpGradeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"面试评分表");
		modelMap.put(NormalExcelConstants.CLASS,ZpGradeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("面试评分表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zpGrades);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZpGradeEntity zpGrade,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"面试评分表");
    	modelMap.put(NormalExcelConstants.CLASS,ZpGradeEntity.class);
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
				List<ZpGradeEntity> listZpGradeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZpGradeEntity.class,params);
				for (ZpGradeEntity zpGrade : listZpGradeEntitys) {
					zpGradeService.save(zpGrade);
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
	public ResponseMessage<List<ZpGradeEntity>> list() {
		List<ZpGradeEntity> listZpGrades=zpGradeService.getList(ZpGradeEntity.class);
		return Result.success(listZpGrades);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取面试评分表信息",notes="根据ID获取面试评分表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ZpGradeEntity task = zpGradeService.get(ZpGradeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取面试评分表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建面试评分表")
	public ResponseMessage<?> create(@ApiParam(name="面试评分表对象")@RequestBody ZpGradeEntity zpGrade, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZpGradeEntity>> failures = validator.validate(zpGrade);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			zpGradeService.save(zpGrade);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("面试评分表信息保存失败");
		}
		return Result.success(zpGrade);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新面试评分表",notes="更新面试评分表")
	public ResponseMessage<?> update(@ApiParam(name="面试评分表对象")@RequestBody ZpGradeEntity zpGrade) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZpGradeEntity>> failures = validator.validate(zpGrade);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			zpGradeService.saveOrUpdate(zpGrade);
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
			zpGradeService.deleteEntityById(ZpGradeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("面试评分表删除失败");
		}

		return Result.success();
	}
}

package com.jeecg.pf.controller;
import com.jeecg.mana.entity.ManagEntity;
import com.jeecg.pf.entity.PfBaseEntity;
import com.jeecg.pf.service.PfBaseServiceI;
import com.jeecg.pfbamana.entity.PfBaseManagerEntity;
import com.jeecg.pfjijincode.entity.PfJijinCodeEntity;
import com.rms.asset.entity.AssetEntity;

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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 公募基本表
 * @author onlineGenerator
 * @date 2019-01-14 15:54:11
 * @version V1.0   
 *
 */
@Api(value="PfBase",description="公募基本表",tags="pfBaseController")
@Controller
@RequestMapping("/pfBaseController")
public class PfBaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PfBaseController.class);

	@Autowired
	private PfBaseServiceI pfBaseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 公募基本表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/pf/pfBaseList");
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
	public void datagrid(PfBaseEntity pfBase,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PfBaseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pfBase, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.pfBaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除公募基本表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PfBaseEntity pfBase, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pfBase = systemService.getEntity(PfBaseEntity.class, pfBase.getId());
		message = "公募基本表删除成功";
		try{
			pfBaseService.delete(pfBase);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公募基本表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除公募基本表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公募基本表删除成功";
		try{
			for(String id:ids.split(",")){
				PfBaseEntity pfBase = systemService.getEntity(PfBaseEntity.class, 
				id
				); 
				pfBaseService.delete(pfBase);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "公募基本表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公募基本表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(@RequestParam(value = "_sqlbuilder" ) String managers, @RequestParam(value = "_sqlbuilder2" ) String codes,   PfBaseEntity pfBase, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公募基本表添加成功";
		try{
			//1.基金经理
			List<PfBaseManagerEntity> manas = JSONArray.parseArray(managers, PfBaseManagerEntity.class);
			//2.基金代码
			List<PfJijinCodeEntity> codess = JSONArray.parseArray(codes, PfJijinCodeEntity.class);
		    
			pfBaseService.addMain(pfBase, manas,codess);
			//pfBaseService.save(pfBase);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公募基本表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "goHisManager")
	public ModelAndView goHisAsset(PfBaseEntity manager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(manager.getId())) {
			manager = pfBaseService.getEntity(PfBaseEntity.class, manager.getId());
			req.setAttribute("HisManagerPage", manager);
		}
		return new ModelAndView("com/jeecg/pf/manager-his");
	}
	
	/**
	 * 更新公募基本表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(@RequestParam(value = "_sqlbuilder" ) String managers, @RequestParam(value = "_sqlbuilder2" ) String codes,    PfBaseEntity pfBase, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公募基本表更新成功";
		PfBaseEntity t = pfBaseService.get(PfBaseEntity.class, pfBase.getId());
		try {
			//1.基金经理
			List<PfBaseManagerEntity> manas = JSONArray.parseArray(managers, PfBaseManagerEntity.class);
			//2.基金代码
			List<PfJijinCodeEntity>  codess = JSONArray.parseArray(codes, PfJijinCodeEntity.class);
			MyBeanUtils.copyBeanNotNull2Bean(pfBase, t);
			pfBaseService.updateMain(t,manas,codess);
			
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "公募基本表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 公募基本表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PfBaseEntity pfBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pfBase.getId())) {
			pfBase = pfBaseService.getEntity(PfBaseEntity.class, pfBase.getId());
			req.setAttribute("pfBasePage", pfBase);
		}
		return new ModelAndView("com/jeecg/pf/pfBase-add");
	}
	/**
	 * 公募基本表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PfBaseEntity pfBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pfBase.getId())) {
			pfBase = pfBaseService.getEntity(PfBaseEntity.class, pfBase.getId());
		    List<PfBaseManagerEntity> manas = pfBaseService.findByProperty(PfBaseManagerEntity.class, "foreignKey", pfBase.getId());
		    List<PfJijinCodeEntity> codes = pfBaseService.findByProperty(PfJijinCodeEntity.class, "foreignKey", pfBase.getId());
		    //将集合转化为 json对象
	        String listObject=JSONObject.toJSONString(manas);
	        String codess=JSONObject.toJSONString(codes);
	        
			req.setAttribute("manas", listObject);
			req.setAttribute("codes", codess);
			req.setAttribute("pfBasePage", pfBase);
		}
		return new ModelAndView("com/jeecg/pf/pfBase-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pfBaseController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PfBaseEntity pfBase,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PfBaseEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pfBase, request.getParameterMap());
		List<PfBaseEntity> pfBases = this.pfBaseService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"公募基本表");
		modelMap.put(NormalExcelConstants.CLASS,PfBaseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公募基本表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pfBases);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PfBaseEntity pfBase,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"公募基本表");
    	modelMap.put(NormalExcelConstants.CLASS,PfBaseEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公募基本表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PfBaseEntity> listPfBaseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PfBaseEntity.class,params);
				pfBaseService.saveByExcel(listPfBaseEntitys);
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
	@ApiOperation(value="公募基本表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PfBaseEntity>> list() {
		List<PfBaseEntity> listPfBases=pfBaseService.getList(PfBaseEntity.class);
		return Result.success(listPfBases);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取公募基本表信息",notes="根据ID获取公募基本表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PfBaseEntity task = pfBaseService.get(PfBaseEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取公募基本表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建公募基本表")
	public ResponseMessage<?> create(@ApiParam(name="公募基本表对象")@RequestBody PfBaseEntity pfBase, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PfBaseEntity>> failures = validator.validate(pfBase);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pfBaseService.save(pfBase);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("公募基本表信息保存失败");
		}
		return Result.success(pfBase);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新公募基本表",notes="更新公募基本表")
	public ResponseMessage<?> update(@ApiParam(name="公募基本表对象")@RequestBody PfBaseEntity pfBase) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PfBaseEntity>> failures = validator.validate(pfBase);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pfBaseService.saveOrUpdate(pfBase);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新公募基本表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新公募基本表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除公募基本表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pfBaseService.deleteEntityById(PfBaseEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("公募基本表删除失败");
		}

		return Result.success();
	}
}

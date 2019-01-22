package com.rms.database.controller;
import com.rms.application.entity.RmsApplicationJeecgEntity;
import com.rms.database.entity.DatabaseEntity;
import com.rms.database.service.DatabaseServiceI;
import com.rms.server.entity.ServerEntity;

import java.util.ArrayList;
import java.util.Date;
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
import java.math.BigDecimal;

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
import com.jeecg.demo.dao.JeecgMinidaoDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: RMS_数据库管理
 * @author onlineGenerator
 * @date 2018-11-01 16:26:11
 * @version V1.0   
 *
 */
@Api(value="Database",description="RMS_数据库管理",tags="databaseController")
@Controller
@RequestMapping("/databaseController")
public class DatabaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DatabaseController.class);

	@Autowired
	private DatabaseServiceI databaseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;


	/**
	 * RMS_数据库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/database/databaseList");
	}

	//os下面查询数据库信息
	@RequestMapping(params = "datagridForData")
	public void datagridForData(DatabaseEntity database,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DatabaseEntity.class, dataGrid);
		String paramId = request.getParameter("paramId");//这里代表server类的Id
		//1,通过server类的id查询到server类的serverId
		ServerEntity serverEntity = systemService.get(ServerEntity.class, paramId);
		cq.eq("serverId",serverEntity.getServerId());
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		parameterMap.remove("paramId");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, database, request.getParameterMap());
		cq.add();
		this.databaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(DatabaseEntity database,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DatabaseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, database, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_launchDate_begin = request.getParameter("launchDate_begin");
		String query_launchDate_end = request.getParameter("launchDate_end");
		if(StringUtil.isNotEmpty(query_launchDate_begin)){
			cq.ge("launchDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_launchDate_begin));
		}
		if(StringUtil.isNotEmpty(query_launchDate_end)){
			cq.le("launchDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_launchDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.databaseService.getDataGridReturn(cq, true);
		
		//================start
		List<DatabaseEntity> list = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();
		for(DatabaseEntity temp:list){
		        //此为针对原来的行数据，拓展的新字段
		        Map m = new HashMap();
		        m.put("OSInfo",this.jeecgMinidaoDao.getServerNameForOS(temp.getServerId()));
		        m.put("ipInfo",this.jeecgMinidaoDao.getIPNameForOS(temp.getServerId()));
		        extMap.put(temp.getServerId(), m);
		}
		String serverId="serverId";//页面传递上来的关联字段
		TagUtil.datagridExt(response, dataGrid,extMap,serverId);
		//===================end
	}
	
	/**
	 * 删除RMS_数据库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DatabaseEntity database, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		database = systemService.getEntity(DatabaseEntity.class, database.getId());
		message = "RMS_数据库管理删除成功";
		try{
			databaseService.delete(database);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_数据库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_数据库管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_数据库管理删除成功";
		try{
			for(String id:ids.split(",")){
				DatabaseEntity database = systemService.getEntity(DatabaseEntity.class, 
				id
				);
				databaseService.delete(database);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_数据库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加RMS_数据库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(DatabaseEntity database, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_数据库管理添加成功";
		try{
			

			  String maxField = "DATABASE_ID";//带有日期的字段
			  String tableName = "rms_database_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      database.setDatabaseId(dateFormatId);
		      
		      String serverId =database.getServerId();
		      if(StringUtil.isNotEmpty(serverId)) {
		    	  List<ServerEntity>  ServerEntitys = databaseService.findByProperty(ServerEntity.class, "serverName", serverId);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverId2 = ServerEntitys.get(0).getServerId();
						 if(StringUtil.isNotEmpty(serverId2)) {
							 database.setServerId(serverId2);
						 }
					 }
		      }
		      
		      
			databaseService.save(database);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_数据库管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新RMS_数据库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(DatabaseEntity database, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_数据库管理更新成功";
		
		String serverId =database.getServerId();
	      if(StringUtil.isNotEmpty(serverId)) {
	    	  List<ServerEntity>  ServerEntitys = databaseService.findByProperty(ServerEntity.class, "serverName", serverId);
				 if(ServerEntitys != null && ServerEntitys.size()>0) {
					 String serverId2 = ServerEntitys.get(0).getServerId();
					 if(StringUtil.isNotEmpty(serverId2)) {
						 database.setServerId(serverId2);
					 }
				 }
	      }
	      
		DatabaseEntity t = databaseService.get(DatabaseEntity.class, database.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(database, t);
			databaseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_数据库管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * RMS_数据库管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DatabaseEntity database, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(database.getId())) {
			database = databaseService.getEntity(DatabaseEntity.class, database.getId());
			req.setAttribute("databasePage", database);
		}
		return new ModelAndView("com/rms/database/database-add");
	}
	/**
	 * RMS_数据库管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DatabaseEntity database, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(database.getId())) {
			database = databaseService.getEntity(DatabaseEntity.class, database.getId());
			
			String serverId =database.getServerId();
		      if(StringUtil.isNotEmpty(serverId)) {
		    	  List<ServerEntity>  ServerEntitys = databaseService.findByProperty(ServerEntity.class, "serverId", serverId);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverName = ServerEntitys.get(0).getServerName();
						 if(StringUtil.isNotEmpty(serverName)) {
							 database.setServerId(serverName);
						 }
					 }
		      }
		      
			req.setAttribute("databasePage", database);
		}
		return new ModelAndView("com/rms/database/database-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","databaseController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DatabaseEntity database,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DatabaseEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, database, request.getParameterMap());
		List<DatabaseEntity> databases = this.databaseService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_数据库管理");
		modelMap.put(NormalExcelConstants.CLASS,DatabaseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_数据库管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,databases);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DatabaseEntity database,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_数据库管理");
    	modelMap.put(NormalExcelConstants.CLASS,DatabaseEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_数据库管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<DatabaseEntity> listDatabaseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DatabaseEntity.class,params);
				for (DatabaseEntity database : listDatabaseEntitys) {
					databaseService.save(database);
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
	@ApiOperation(value="RMS_数据库管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<DatabaseEntity>> list() {
		List<DatabaseEntity> listDatabases=databaseService.getList(DatabaseEntity.class);
		return Result.success(listDatabases);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_数据库管理信息",notes="根据ID获取RMS_数据库管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		DatabaseEntity task = databaseService.get(DatabaseEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_数据库管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_数据库管理")
	public ResponseMessage<?> create(@ApiParam(name="RMS_数据库管理对象")@RequestBody DatabaseEntity database, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DatabaseEntity>> failures = validator.validate(database);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			databaseService.save(database);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_数据库管理信息保存失败");
		}
		return Result.success(database);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_数据库管理",notes="更新RMS_数据库管理")
	public ResponseMessage<?> update(@ApiParam(name="RMS_数据库管理对象")@RequestBody DatabaseEntity database) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DatabaseEntity>> failures = validator.validate(database);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			databaseService.saveOrUpdate(database);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_数据库管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_数据库管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_数据库管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			databaseService.deleteEntityById(DatabaseEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_数据库管理删除失败");
		}

		return Result.success();
	}
}

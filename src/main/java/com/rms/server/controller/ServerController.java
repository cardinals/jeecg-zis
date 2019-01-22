package com.rms.server.controller;
import com.rms.hardware.entity.HardwareEntity;
import com.rms.server.entity.ServerEntity;
import com.rms.server.service.ServerServiceI;

import generator.pas.onstaff.entity.PasOnStaffEntity;

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
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
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
import com.jeecg.bankaccount.entity.BankAccountEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: OS信息
 * @author onlineGenerator
 * @date 2018-09-05 10:49:38
 * @version V1.0   
 *
 */
@Api(value="server",description="OS信息",tags="serverController")
@Controller
@RequestMapping("/serverController")
public class ServerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerController.class);

	@Autowired
	private ServerServiceI serverService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/*@RequestMapping(params = "getVservers")
	@ResponseBody
	public String getVservers(HttpServletRequest req) {
		JSONArray jsonArray=null;
  		String sql = "SELECT t.SERVER_NAME from rms_server_jeecg t";
  		List<Map<String, Object>> maps = serverService.findForJdbc(sql, null);
  		jsonArray=new JSONArray();
  		for (int i = 0; i < maps.size(); i++) {
			Map<String, Object> map=(HashMap<String, Object>) maps.get(i);
			 jsonArray.put(map);
		}
		return jsonArray.toString();
	}*/
	
	
	/**
	 *============================获取所有的虚拟机。===================================
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getVservers_bak")
	@ResponseBody
	public AjaxJson getVservers_bak(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		j.setAttributes(attrMap);
		attrMap.put("entityName", "ServerEntity");//类名
		attrMap.put("filedName", "serverName");//字段名称
			
		
		return j;
	}
	

	/**
	 * OS信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/server/serverList");
	}

	/**
	 * ====================================通过硬件记录查询硬件下面的os信息===============================================
	 * @param server
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridForOS")
	public void datagridForOS(ServerEntity server,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ServerEntity.class, dataGrid);
		String paramId = request.getParameter("paramId");
		cq.eq("container",paramId);//通过paramId的值是 hardwareId字段
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		parameterMap.remove("paramId");
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, server, parameterMap);
		cq.add();
		this.serverService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/*@RequestMapping(params = "datagrid")
	public void datagrid(ServerEntity server,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		HqlQuery hql = new HqlQuery(ServerEntity.class, "select * from rms_server_jeecg", dataGrid);
		PageList list  =  serverService.getPageListBySql(hql,true);
		System.out.println(list.getCount());
		//result = list.getResultList();
		List<ServerEntity> list = serverService.findByQueryString("from ServerEntity");
		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
	}*/
	
	 
	@RequestMapping(params = "datagrid")
	public void datagrid(ServerEntity server,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ServerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, server, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_createDate_begin = request.getParameter("createDate_begin");
		String query_createDate_end = request.getParameter("createDate_end");
		if(StringUtil.isNotEmpty(query_createDate_begin)){
			cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_begin));
		}
		if(StringUtil.isNotEmpty(query_createDate_end)){
			cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.serverService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除OS信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ServerEntity server, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		server = systemService.getEntity(ServerEntity.class, server.getId());
		message = "OS信息删除成功";
		try{
			serverService.delete(server);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "OS信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除OS信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "OS信息删除成功";
		try{
			for(String id:ids.split(",")){
				ServerEntity server = systemService.getEntity(ServerEntity.class, 
				id
				);
				serverService.delete(server);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "OS信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加OS信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ServerEntity server, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "OS信息添加成功";
		try{
			  if("0".equals(server.getVirtural())) {//如果不是虚拟机
				  String container = server.getContainer();
				  if(StringUtil.isNotEmpty(container)) {
					 List<HardwareEntity>  HardwareEntitys = serverService.findByProperty(HardwareEntity.class, "name", container);
					 if(HardwareEntitys != null && HardwareEntitys.size()>0) {
						 String hardwareId = HardwareEntitys.get(0).getHardwareId();
						 if(StringUtil.isNotEmpty(hardwareId)) {
							 server.setContainer(hardwareId);
						 }
					 }
				  }
				  server.setVcontainer(null);
			  }else {//如果是虚拟机
				  String vcontainer = server.getVcontainer();
				  if(StringUtil.isNotEmpty(vcontainer)) {
						 List<ServerEntity>  ServerEntitys = serverService.findByProperty(ServerEntity.class, "serverName", vcontainer);
						 if(ServerEntitys != null && ServerEntitys.size()>0) {
							 String serverId = ServerEntitys.get(0).getServerId();
							 if(StringUtil.isNotEmpty(serverId)) {
								 server.setVcontainer(serverId);
							 }
						 }
					  }
				  server.setContainer(null);
			  }
			  
			  String maxField = "SERVER_ID";//带有日期的字段
			  String tableName = "rms_server_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      server.setServerId(dateFormatId);
		      
			serverService.save(server);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "OS信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新OS信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ServerEntity server, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "OS信息更新成功";
		//start
		 if("0".equals(server.getVirtural())) {//如果不是虚拟机
			  String container = server.getContainer();
			  if(StringUtil.isNotEmpty(container)) {
				 List<HardwareEntity>  HardwareEntitys = serverService.findByProperty(HardwareEntity.class, "name", container);
				 if(HardwareEntitys != null && HardwareEntitys.size()>0) {
					 String hardwareId = HardwareEntitys.get(0).getHardwareId();
					 if(StringUtil.isNotEmpty(hardwareId)) {
						 server.setContainer(hardwareId);
					 }
				 }
			  }
			  server.setVcontainer("");
		  }else {//如果是虚拟机
			  String vcontainer = server.getVcontainer();
			  if(StringUtil.isNotEmpty(vcontainer)) {
					 List<ServerEntity>  ServerEntitys = serverService.findByProperty(ServerEntity.class, "serverName", vcontainer);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverId = ServerEntitys.get(0).getServerId();
						 if(StringUtil.isNotEmpty(serverId)) {
							 server.setVcontainer(serverId);
						 }
					 }
				  }
			  server.setContainer("");
		  }
	//end
		ServerEntity t = serverService.get(ServerEntity.class, server.getId());
		try {
	
			MyBeanUtils.copyBeanNotNull2Bean(server, t);
			serverService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "OS信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	//查看os下的应用信息
	@RequestMapping(params = "goApplication")
	public ModelAndView goApplication(ServerEntity server, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(server.getId())) {
			server = serverService.getEntity(ServerEntity.class, server.getId());
			req.setAttribute("serverPage", server);
		}
		return new ModelAndView("com/rms/server/server-apply");
	}
	
	//查看os下的数据库信息
	@RequestMapping(params = "goDatabase")
	public ModelAndView goDatabase(ServerEntity server, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(server.getId())) {
			server = serverService.getEntity(ServerEntity.class, server.getId());
			req.setAttribute("serverPage", server);
		}
		return new ModelAndView("com/rms/server/server-data");
	}
	
	/**
	 * OS信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ServerEntity server, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(server.getId())) {
			server = serverService.getEntity(ServerEntity.class, server.getId());
			req.setAttribute("serverPage", server);
		}
	
		return new ModelAndView("com/rms/server/server-add");
	}
	/**
	 * OS信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ServerEntity server, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(server.getId())) {
			server = serverService.getEntity(ServerEntity.class, server.getId());
			String container = server.getContainer();//硬件容器
			String vcontainer = server.getVcontainer();//虚拟机容器
			if(StringUtil.isNotEmpty(container)) {
				 List<HardwareEntity>  HardwareEntitys = serverService.findByProperty(HardwareEntity.class, "hardwareId", container);
				 if(HardwareEntitys != null && HardwareEntitys.size()>0) {
					 String hardwarName = HardwareEntitys.get(0).getName();
					 if(StringUtil.isNotEmpty(hardwarName)) {
						 server.setContainer(hardwarName);
					 }
				 }
			}
			
			  if(StringUtil.isNotEmpty(vcontainer)) {
					 List<ServerEntity>  ServerEntitys = serverService.findByProperty(ServerEntity.class, "serverId", vcontainer);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverName = ServerEntitys.get(0).getServerName();
						 if(StringUtil.isNotEmpty(serverName)) {
							 server.setVcontainer(serverName);
						 }
					 }
				  }
			req.setAttribute("serverPage", server);
		}
		return new ModelAndView("com/rms/server/server-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","serverController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ServerEntity server,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ServerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, server, request.getParameterMap());
		List<ServerEntity> servers = this.serverService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"OS信息");
		modelMap.put(NormalExcelConstants.CLASS,ServerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("OS信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,servers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ServerEntity server,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"OS信息");
    	modelMap.put(NormalExcelConstants.CLASS,ServerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("OS信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ServerEntity> listServerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ServerEntity.class,params);
				for (ServerEntity server : listServerEntitys) {
					serverService.save(server);
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
	@ApiOperation(value="OS信息列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ServerEntity>> list() {
		List<ServerEntity> listservers=serverService.getList(ServerEntity.class);
		return Result.success(listservers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取OS信息信息",notes="根据ID获取OS信息信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ServerEntity task = serverService.get(ServerEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取OS信息信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建OS信息")
	public ResponseMessage<?> create(@ApiParam(name="OS信息对象")@RequestBody ServerEntity server, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ServerEntity>> failures = validator.validate(server);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			serverService.save(server);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("OS信息信息保存失败");
		}
		return Result.success(server);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新OS信息",notes="更新OS信息")
	public ResponseMessage<?> update(@ApiParam(name="OS信息对象")@RequestBody ServerEntity server) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ServerEntity>> failures = validator.validate(server);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			serverService.saveOrUpdate(server);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新OS信息信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新OS信息信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除OS信息")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			serverService.deleteEntityById(ServerEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("OS信息删除失败");
		}

		return Result.success();
	}
}

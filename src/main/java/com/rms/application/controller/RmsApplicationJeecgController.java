package com.rms.application.controller;
import com.rms.application.entity.RmsApplicationJeecgEntity;
import com.rms.application.service.RmsApplicationJeecgServiceI;
import com.rms.hardware.entity.HardwareEntity;
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
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import java.math.BigDecimal;

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
import java.sql.Timestamp;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import com.jeecg.demo.dao.JeecgMinidaoDao;
import com.jeecg.demo.entity.JeecgDemoEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 应用管理
 * @author onlineGenerator
 * @date 2018-10-08 10:26:11
 * @version V1.0   
 *
 */
@Api(value="RmsApplicationJeecg",description="应用管理",tags="rmsApplicationJeecgController")
@Controller
@RequestMapping("/rmsApplicationJeecgController")
public class RmsApplicationJeecgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RmsApplicationJeecgController.class);

	@Autowired
	private RmsApplicationJeecgServiceI rmsApplicationJeecgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;

	private final static  String dbKey = "SMSTest";
	
	private final static String sql = "SELECT  t.VIRTURAL  from rms_server_jeecg t where t.SERVER_ID = ? ";

	/**
	 * 应用管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/application/rmsApplicationJeecgList");
	}

	//查询应用信息
	@RequestMapping(params = "datagridForApply")
	public void datagridForApply(RmsApplicationJeecgEntity rmsApplicationJeecg,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RmsApplicationJeecgEntity.class, dataGrid);
		String paramId = request.getParameter("paramId");//这里代表server类的Id
		//1,通过server类的id查询到server类的serverId
		ServerEntity serverEntity = systemService.get(ServerEntity.class, paramId);
		cq.eq("serverId",serverEntity.getServerId());
		
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		parameterMap.remove("paramId");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsApplicationJeecg, parameterMap);
		cq.add();
		this.rmsApplicationJeecgService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(RmsApplicationJeecgEntity rmsApplicationJeecg,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RmsApplicationJeecgEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsApplicationJeecg, request.getParameterMap());
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
		this.rmsApplicationJeecgService.getDataGridReturn(cq, true);
		//================start
		List<RmsApplicationJeecgEntity> list = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();
		for(RmsApplicationJeecgEntity temp:list){
		        //此为针对原来的行数据，拓展的新字段
			     String serverId = temp.getServerId();
			     //通过serverId 查询 server对象
			     boolean VirturalFlag = false;//默认不是虚拟机
			     if(StringUtil.isNotEmpty(serverId)) {
			    	  // List<ServerEntity> serverEntitys =  rmsApplicationJeecgService.findByProperty(ServerEntity.class, "serverId", serverId);
				       //start
			    	try {
						   List<Map<String,Object>> serverObj = DynamicDBUtil.findList(dbKey, sql, serverId);
						   if(serverObj!=null && serverObj.size()>0) {
							   	String  Isvirtural = serverObj.get(0).get("VIRTURAL").toString();
							   	if(StringUtil.isNotEmpty(Isvirtural) && "1".equals(Isvirtural)){//是虚拟机
									  VirturalFlag = true;
							   	}
						   }
						   
						} catch (Exception e) {
							e.printStackTrace();
						}finally {
							DynamicDBUtil.closeDBkey2(dbKey);
						}
				       
			     }
			    Map m = new HashMap();
			    if(!VirturalFlag) {//不是虚拟机
			    	 m.put("hardWareName",this.jeecgMinidaoDao.getHardWareName(temp.getServerId()));//硬件信息
			    }else {//是虚拟机
			    	 m.put("hardWareName",this.jeecgMinidaoDao.getVirturalName(temp.getServerId()));//虚拟机信息
			    }
		       
		        m.put("OSInfo",this.jeecgMinidaoDao.getServerName(temp.getServerId()));//os信息
		        m.put("ipInfo",this.jeecgMinidaoDao.getIPName(temp.getServerId()));//IP信息
		        extMap.put(temp.getServerId(), m);
		}
		String serverId="serverId";//页面传递上来的关联字段
		TagUtil.datagridExt(response, dataGrid,extMap,serverId);
		//===================end
	}
	
	/**
	 * 删除应用管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(RmsApplicationJeecgEntity rmsApplicationJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		rmsApplicationJeecg = systemService.getEntity(RmsApplicationJeecgEntity.class, rmsApplicationJeecg.getId());
		message = "应用管理删除成功";
		try{
			rmsApplicationJeecgService.delete(rmsApplicationJeecg);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "应用管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除应用管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应用管理删除成功";
		try{
			for(String id:ids.split(",")){
				RmsApplicationJeecgEntity rmsApplicationJeecg = systemService.getEntity(RmsApplicationJeecgEntity.class, 
				id
				);
				rmsApplicationJeecgService.delete(rmsApplicationJeecg);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "应用管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加应用管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(RmsApplicationJeecgEntity rmsApplicationJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应用管理添加成功";
		try{

		      String maxField = "APPLICATION_ID";//带有日期的字段
			  String tableName = "rms_application_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String contractId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      rmsApplicationJeecg.setApplicationId(contractId);
		      
		      String serverId =rmsApplicationJeecg.getServerId();
		      if(StringUtil.isNotEmpty(serverId)) {
		    	  List<ServerEntity>  ServerEntitys = rmsApplicationJeecgService.findByProperty(ServerEntity.class, "serverName", serverId);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverId2 = ServerEntitys.get(0).getServerId();
						 if(StringUtil.isNotEmpty(serverId2)) {
							 rmsApplicationJeecg.setServerId(serverId2);
						 }
					 }
		      }
		      
			/*	List<TSBaseUser>  tSBaseUsers = rmsApplicationJeecgService.findByProperty(TSBaseUser.class, "realName", rmsApplicationJeecg.getAdministrator());
				if(tSBaseUsers != null && tSBaseUsers.size()>0) {
					rmsApplicationJeecg.setAdministrator(tSBaseUsers.get(0).getId());
				}*/
			
	        rmsApplicationJeecgService.save(rmsApplicationJeecg);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "应用管理添加失败";
			throw new BusinessException(e.getMessage());
		} 
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新应用管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(RmsApplicationJeecgEntity rmsApplicationJeecg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应用管理更新成功";
		
		String serverId =rmsApplicationJeecg.getServerId();
	      if(StringUtil.isNotEmpty(serverId)) {
	    	  List<ServerEntity>  ServerEntitys = rmsApplicationJeecgService.findByProperty(ServerEntity.class, "serverName", serverId);
				 if(ServerEntitys != null && ServerEntitys.size()>0) {
					 String serverId2 = ServerEntitys.get(0).getServerId();
					 if(StringUtil.isNotEmpty(serverId2)) {
						 rmsApplicationJeecg.setServerId(serverId2);
					 }
				 }
	      }
		
		
		RmsApplicationJeecgEntity t = rmsApplicationJeecgService.get(RmsApplicationJeecgEntity.class, rmsApplicationJeecg.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(rmsApplicationJeecg, t);
			
		    		/*List<TSBaseUser> tSBaseUsers = rmsApplicationJeecgService.findByProperty(TSBaseUser.class, "id", rmsApplicationJeecg.getAdministrator());
					if(tSBaseUsers.size()==0) {
						List<TSBaseUser>  tSBaseUsers2 = rmsApplicationJeecgService.findByProperty(TSBaseUser.class, "realName", rmsApplicationJeecg.getAdministrator());
						if(tSBaseUsers2.size()>0 ) {
							t.setAdministrator(tSBaseUsers2.get(0).getId());
						}
					}*/
			
			rmsApplicationJeecgService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "应用管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 应用管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(RmsApplicationJeecgEntity rmsApplicationJeecg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsApplicationJeecg.getId())) {
			rmsApplicationJeecg = rmsApplicationJeecgService.getEntity(RmsApplicationJeecgEntity.class, rmsApplicationJeecg.getId());
			req.setAttribute("rmsApplicationJeecgPage", rmsApplicationJeecg);
		}
		
		TSUser user = ResourceUtil.getSessionUser();
		if(user != null) {
			rmsApplicationJeecg.setAdministrator(user.getId());
			req.setAttribute("userName", user.getRealName());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		
		req.setAttribute("launchDate", date);
		return new ModelAndView("com/rms/application/rmsApplicationJeecg-add");
	}
	/**
	 * 应用管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(RmsApplicationJeecgEntity rmsApplicationJeecg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(rmsApplicationJeecg.getId())) {
			rmsApplicationJeecg = rmsApplicationJeecgService.getEntity(RmsApplicationJeecgEntity.class, rmsApplicationJeecg.getId());
			String serverId =rmsApplicationJeecg.getServerId();
		      if(StringUtil.isNotEmpty(serverId)) {
		    	  List<ServerEntity>  ServerEntitys = rmsApplicationJeecgService.findByProperty(ServerEntity.class, "serverId", serverId);
					 if(ServerEntitys != null && ServerEntitys.size()>0) {
						 String serverName = ServerEntitys.get(0).getServerName();
						 if(StringUtil.isNotEmpty(serverName)) {
							 rmsApplicationJeecg.setServerId(serverName);
						 }
					 }
		      }
			
			req.setAttribute("rmsApplicationJeecgPage", rmsApplicationJeecg);
		}
		
		/*List<TSBaseUser> tSBaseUsers = rmsApplicationJeecgService.findByProperty(TSBaseUser.class, "id", rmsApplicationJeecg.getAdministrator());
		if(tSBaseUsers != null &&tSBaseUsers.size()>0) {
				rmsApplicationJeecg.setAdministrator(tSBaseUsers.get(0).getRealName());
		}*/
		return new ModelAndView("com/rms/application/rmsApplicationJeecg-update");
	} 	 	
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","rmsApplicationJeecgController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(RmsApplicationJeecgEntity rmsApplicationJeecg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(RmsApplicationJeecgEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, rmsApplicationJeecg, request.getParameterMap());
		List<RmsApplicationJeecgEntity> rmsApplicationJeecgs = this.rmsApplicationJeecgService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"应用管理");
		modelMap.put(NormalExcelConstants.CLASS,RmsApplicationJeecgEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("应用管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,rmsApplicationJeecgs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(RmsApplicationJeecgEntity rmsApplicationJeecg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"应用管理");
    	modelMap.put(NormalExcelConstants.CLASS,RmsApplicationJeecgEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("应用管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<RmsApplicationJeecgEntity> listRmsApplicationJeecgEntitys = ExcelImportUtil.importExcel(file.getInputStream(),RmsApplicationJeecgEntity.class,params);
				for (RmsApplicationJeecgEntity rmsApplicationJeecg : listRmsApplicationJeecgEntitys) {
					rmsApplicationJeecgService.save(rmsApplicationJeecg);
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
	@ApiOperation(value="应用管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<RmsApplicationJeecgEntity>> list() {
		List<RmsApplicationJeecgEntity> listRmsApplicationJeecgs=rmsApplicationJeecgService.getList(RmsApplicationJeecgEntity.class);
		return Result.success(listRmsApplicationJeecgs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取应用管理信息",notes="根据ID获取应用管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		RmsApplicationJeecgEntity task = rmsApplicationJeecgService.get(RmsApplicationJeecgEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取应用管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建应用管理")
	public ResponseMessage<?> create(@ApiParam(name="应用管理对象")@RequestBody RmsApplicationJeecgEntity rmsApplicationJeecg, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsApplicationJeecgEntity>> failures = validator.validate(rmsApplicationJeecg);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsApplicationJeecgService.save(rmsApplicationJeecg);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("应用管理信息保存失败");
		}
		return Result.success(rmsApplicationJeecg);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新应用管理",notes="更新应用管理")
	public ResponseMessage<?> update(@ApiParam(name="应用管理对象")@RequestBody RmsApplicationJeecgEntity rmsApplicationJeecg) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RmsApplicationJeecgEntity>> failures = validator.validate(rmsApplicationJeecg);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			rmsApplicationJeecgService.saveOrUpdate(rmsApplicationJeecg);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新应用管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新应用管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除应用管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			rmsApplicationJeecgService.deleteEntityById(RmsApplicationJeecgEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("应用管理删除失败");
		}

		return Result.success();
	}
}

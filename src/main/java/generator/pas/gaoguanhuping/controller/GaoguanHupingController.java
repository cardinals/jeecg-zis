package generator.pas.gaoguanhuping.controller;
import generator.pas.gaoguanhuping.entity.GaoguanHupingEntity;
import generator.pas.gaoguanhuping.service.GaoguanHupingServiceI;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;

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
import org.hibernate.criterion.Restrictions;
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
import org.apache.commons.collections.CollectionUtils;
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
 * @Description: PAS_董事总经理及高级管理人员互评
 * @author onlineGenerator
 * @date 2018-11-08 14:44:02
 * @version V1.0   
 *
 */
@Api(value="GaoguanHuping",description="PAS_董事总经理及高级管理人员互评",tags="gaoguanHupingController")
@Controller
@RequestMapping("/gaoguanHupingController")
public class GaoguanHupingController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GaoguanHupingController.class);

	@Autowired
	private GaoguanHupingServiceI gaoguanHupingService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	

	/**
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(GaoguanHupingEntity page){
		String message = null;
		List<GaoguanHupingEntity> pasLeadList=page.getGghpList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasLeadList)){
			for(GaoguanHupingEntity pasLead:pasLeadList){
					try {
						message = "PasOnStaff例子添加成功";
						gaoguanHupingService.save(pasLead);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		return j;
	}

	/**
	 * 检查当前用户评分的状态
	 * @return
	 */
	@RequestMapping(params = "checkConfirm")
	@ResponseBody
	public AjaxJson checkConfirm() {
		TSUser loginUser = ResourceUtil.getSessionUser();
		AjaxJson j = new AjaxJson();
		String message = "<检查当前用户-"+loginUser.getUserName()+"评分状态>操作成功";
		try {
			Boolean result = gaoguanHupingService.getInitStatusByLoginUser(loginUser);
			systemService.addLog(message, Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
			Map<String, Object> attr  =  new HashMap<String,Object>();
			//True:为开始评分/False:尚未开始评分
			attr.put("initStatus", result);
			j.setAttributes(attr);
		}
		catch(Exception e){
			e.printStackTrace();
			message = "检查当前用户评分的状态失败!";
			throw new BusinessException(e.getMessage());
		}
		return j;
	}
	
	/**
	 * PAS_董事总经理及高级管理人员互评列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("generator/pas/gaoguanhuping/gaoguanHupingList");
	}

	@RequestMapping(params = "iframePage")
	public ModelAndView iframePage() {
		return new ModelAndView("generator/pas/gaoguanhuping/gghpIframePage");
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
	public void datagrid(GaoguanHupingEntity gaoguanHuping,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GaoguanHupingEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gaoguanHuping, request.getParameterMap());
		//-start
		TSUser loginUser = ResourceUtil.getSessionUser();
		Boolean initStatus = gaoguanHupingService.getInitStatusByLoginUser(loginUser);
		//True:为开始评分 / False:尚未开始评分
		if(initStatus) {//已经评完分，展示数据就可以了
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			gaoguanHupingService.getDataGridReturn(cq, true);
		}else {//尚未开始评分
			//List<TSUser> listExceptLoginUser = pasStaffLeadService.getAllDeptStaffExceptLoginUser(loginUser);
			//获取高管人员
			//List<Map<String, Object>> allLeaders = gaoguanHupingService.getAllLeads();
			
			List<Map<String, Object>> allLeaders = gaoguanHupingService.getAllLeads(loginUser);
			
			//组装数据列表--排除自己的其他高管角色
			List<GaoguanHupingEntity> result = gaoguanHupingService.installDataGridList(allLeaders,loginUser);
			dataGrid.setResults(result);
			
		}
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除PAS_董事总经理及高级管理人员互评
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(GaoguanHupingEntity gaoguanHuping, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		gaoguanHuping = systemService.getEntity(GaoguanHupingEntity.class, gaoguanHuping.getId());
		message = "PAS_董事总经理及高级管理人员互评删除成功";
		try{
			gaoguanHupingService.delete(gaoguanHuping);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "PAS_董事总经理及高级管理人员互评删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除PAS_董事总经理及高级管理人员互评
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "PAS_董事总经理及高级管理人员互评删除成功";
		try{
			for(String id:ids.split(",")){
				GaoguanHupingEntity gaoguanHuping = systemService.getEntity(GaoguanHupingEntity.class, 
				id
				);
				gaoguanHupingService.delete(gaoguanHuping);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "PAS_董事总经理及高级管理人员互评删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PAS_董事总经理及高级管理人员互评
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(GaoguanHupingEntity gaoguanHuping, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "PAS_董事总经理及高级管理人员互评添加成功";
		try{
			gaoguanHupingService.save(gaoguanHuping);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "PAS_董事总经理及高级管理人员互评添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新PAS_董事总经理及高级管理人员互评
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(GaoguanHupingEntity gaoguanHuping, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "PAS_董事总经理及高级管理人员互评更新成功";
		GaoguanHupingEntity t = gaoguanHupingService.get(GaoguanHupingEntity.class, gaoguanHuping.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(gaoguanHuping, t);
			gaoguanHupingService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "PAS_董事总经理及高级管理人员互评更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * PAS_董事总经理及高级管理人员互评新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(GaoguanHupingEntity gaoguanHuping, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(gaoguanHuping.getId())) {
			gaoguanHuping = gaoguanHupingService.getEntity(GaoguanHupingEntity.class, gaoguanHuping.getId());
			req.setAttribute("gaoguanHupingPage", gaoguanHuping);
		}
		return new ModelAndView("generator/pas/gaoguanhuping/gaoguanHuping-add");
	}
	/**
	 * PAS_董事总经理及高级管理人员互评编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(GaoguanHupingEntity gaoguanHuping, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(gaoguanHuping.getId())) {
			gaoguanHuping = gaoguanHupingService.getEntity(GaoguanHupingEntity.class, gaoguanHuping.getId());
			req.setAttribute("gaoguanHupingPage", gaoguanHuping);
		}
		return new ModelAndView("generator/pas/gaoguanhuping/gaoguanHuping-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","gaoguanHupingController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(GaoguanHupingEntity gaoguanHuping,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(GaoguanHupingEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gaoguanHuping, request.getParameterMap());
		List<GaoguanHupingEntity> gaoguanHupings = this.gaoguanHupingService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"PAS_董事总经理及高级管理人员互评");
		modelMap.put(NormalExcelConstants.CLASS,GaoguanHupingEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("PAS_董事总经理及高级管理人员互评列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,gaoguanHupings);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(GaoguanHupingEntity gaoguanHuping,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"PAS_董事总经理及高级管理人员互评");
    	modelMap.put(NormalExcelConstants.CLASS,GaoguanHupingEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("PAS_董事总经理及高级管理人员互评列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<GaoguanHupingEntity> listGaoguanHupingEntitys = ExcelImportUtil.importExcel(file.getInputStream(),GaoguanHupingEntity.class,params);
				for (GaoguanHupingEntity gaoguanHuping : listGaoguanHupingEntitys) {
					gaoguanHupingService.save(gaoguanHuping);
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
	@ApiOperation(value="PAS_董事总经理及高级管理人员互评列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<GaoguanHupingEntity>> list() {
		List<GaoguanHupingEntity> listGaoguanHupings=gaoguanHupingService.getList(GaoguanHupingEntity.class);
		return Result.success(listGaoguanHupings);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取PAS_董事总经理及高级管理人员互评信息",notes="根据ID获取PAS_董事总经理及高级管理人员互评信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		GaoguanHupingEntity task = gaoguanHupingService.get(GaoguanHupingEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取PAS_董事总经理及高级管理人员互评信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建PAS_董事总经理及高级管理人员互评")
	public ResponseMessage<?> create(@ApiParam(name="PAS_董事总经理及高级管理人员互评对象")@RequestBody GaoguanHupingEntity gaoguanHuping, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<GaoguanHupingEntity>> failures = validator.validate(gaoguanHuping);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			gaoguanHupingService.save(gaoguanHuping);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("PAS_董事总经理及高级管理人员互评信息保存失败");
		}
		return Result.success(gaoguanHuping);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新PAS_董事总经理及高级管理人员互评",notes="更新PAS_董事总经理及高级管理人员互评")
	public ResponseMessage<?> update(@ApiParam(name="PAS_董事总经理及高级管理人员互评对象")@RequestBody GaoguanHupingEntity gaoguanHuping) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<GaoguanHupingEntity>> failures = validator.validate(gaoguanHuping);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			gaoguanHupingService.saveOrUpdate(gaoguanHuping);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新PAS_董事总经理及高级管理人员互评信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新PAS_董事总经理及高级管理人员互评信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除PAS_董事总经理及高级管理人员互评")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			gaoguanHupingService.deleteEntityById(GaoguanHupingEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("PAS_董事总经理及高级管理人员互评删除失败");
		}

		return Result.success();
	}
}

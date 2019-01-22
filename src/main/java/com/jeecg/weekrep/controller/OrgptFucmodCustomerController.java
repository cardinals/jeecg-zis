package com.jeecg.weekrep.controller;
import com.jeecg.weekrep.entity.OrgptFucmodCustomerEntity;
import com.jeecg.weekrep.entity.OrgptFucmodWeekrepEntity;
import com.jeecg.weekrep.service.OrgptFucmodCustomerServiceI;
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
 * @Description: 机构客户和类型导入
 * @author onlineGenerator
 * @date 2018-06-08 15:04:33
 * @version V1.0   
 *
 */
@Api(value="OrgptFucmodCustomer",description="机构客户和类型导入",tags="orgptFucmodCustomerController")
@Controller
@RequestMapping("/orgptFucmodCustomerController")
public class OrgptFucmodCustomerController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(OrgptFucmodCustomerController.class);

	@Autowired
	private OrgptFucmodCustomerServiceI orgptFucmodCustomerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "getCustomerName")
	@ResponseBody
	public AjaxJson getCustomerName(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		List<OrgptFucmodCustomerEntity> customerNames = new ArrayList<OrgptFucmodCustomerEntity>();
		j.setAttributes(attrMap);
		
		String hql = "from OrgptFucmodCustomerEntity";
		customerNames = systemService.findByQueryString(hql);
		if(customerNames != null){
			attrMap.put("customerNames", customerNames);
		}else {
			//j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
			j.setSuccess(false);
			
		}
		return j;
	}

	
	@RequestMapping(params = "getCustomerNa")
	@ResponseBody
	public AjaxJson getCustomerNa(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		j.setAttributes(attrMap);
		String id = req.getParameter("id");
		OrgptFucmodWeekrepEntity orgptFucmodWeekrepEntity= systemService.getEntity(OrgptFucmodWeekrepEntity.class, id);
		String targetorginwk = orgptFucmodWeekrepEntity.getTargetorginwk();
		String accessflag = orgptFucmodWeekrepEntity.getAccessflag();
		String cooperationpoint = orgptFucmodWeekrepEntity.getCooperationpoint();
		if(orgptFucmodWeekrepEntity != null){
			attrMap.put("customername", targetorginwk);//本周拜访机构
			attrMap.put("accessflag", accessflag);//是否准入
			attrMap.put("cooperationpoint", cooperationpoint);//业务合作点
			
		}else {
			//j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
			j.setSuccess(false);
			
		}
		return j;
	}

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/weekrep/orgptFucmodCustomerList");
	}

	

	@RequestMapping(params = "datagrid")
	public void datagrid(OrgptFucmodCustomerEntity orgptFucmodCustomer,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(OrgptFucmodCustomerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, orgptFucmodCustomer, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.orgptFucmodCustomerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(OrgptFucmodCustomerEntity orgptFucmodCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		orgptFucmodCustomer = systemService.getEntity(OrgptFucmodCustomerEntity.class, orgptFucmodCustomer.getId());
		message = "机构客户和类型导入删除成功";
		try{
			orgptFucmodCustomerService.delete(orgptFucmodCustomer);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "机构客户和类型导入删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "机构客户和类型导入删除成功";
		try{
			for(String id:ids.split(",")){
				OrgptFucmodCustomerEntity orgptFucmodCustomer = systemService.getEntity(OrgptFucmodCustomerEntity.class, 
				id
				);
				orgptFucmodCustomerService.delete(orgptFucmodCustomer);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "机构客户和类型导入删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(OrgptFucmodCustomerEntity orgptFucmodCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "机构客户和类型导入添加成功";
		try{
			orgptFucmodCustomerService.save(orgptFucmodCustomer);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "机构客户和类型导入添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(OrgptFucmodCustomerEntity orgptFucmodCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "机构客户和类型导入更新成功";
		OrgptFucmodCustomerEntity t = orgptFucmodCustomerService.get(OrgptFucmodCustomerEntity.class, orgptFucmodCustomer.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(orgptFucmodCustomer, t);
			orgptFucmodCustomerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "机构客户和类型导入更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(OrgptFucmodCustomerEntity orgptFucmodCustomer, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(orgptFucmodCustomer.getId())) {
			orgptFucmodCustomer = orgptFucmodCustomerService.getEntity(OrgptFucmodCustomerEntity.class, orgptFucmodCustomer.getId());
			req.setAttribute("orgptFucmodCustomerPage", orgptFucmodCustomer);
		}
		return new ModelAndView("com/jeecg/weekrep/orgptFucmodCustomer-add");
	}
	
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(OrgptFucmodCustomerEntity orgptFucmodCustomer, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(orgptFucmodCustomer.getId())) {
			orgptFucmodCustomer = orgptFucmodCustomerService.getEntity(OrgptFucmodCustomerEntity.class, orgptFucmodCustomer.getId());
			req.setAttribute("orgptFucmodCustomerPage", orgptFucmodCustomer);
		}
		return new ModelAndView("com/jeecg/weekrep/orgptFucmodCustomer-update");
	}
	
	
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","orgptFucmodCustomerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	
	@RequestMapping(params = "exportXls")
	public String exportXls(OrgptFucmodCustomerEntity orgptFucmodCustomer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(OrgptFucmodCustomerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, orgptFucmodCustomer, request.getParameterMap());
		List<OrgptFucmodCustomerEntity> orgptFucmodCustomers = this.orgptFucmodCustomerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"机构客户和类型导入");
		modelMap.put(NormalExcelConstants.CLASS,OrgptFucmodCustomerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("机构客户和类型导入列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,orgptFucmodCustomers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(OrgptFucmodCustomerEntity orgptFucmodCustomer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"机构客户和类型导入");
    	modelMap.put(NormalExcelConstants.CLASS,OrgptFucmodCustomerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("机构客户和类型导入列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<OrgptFucmodCustomerEntity> listOrgptFucmodCustomerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),OrgptFucmodCustomerEntity.class,params);
				for (OrgptFucmodCustomerEntity orgptFucmodCustomer : listOrgptFucmodCustomerEntitys) {
					orgptFucmodCustomerService.save(orgptFucmodCustomer);
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
	@ApiOperation(value="机构客户和类型导入列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<OrgptFucmodCustomerEntity>> list() {
		List<OrgptFucmodCustomerEntity> listOrgptFucmodCustomers=orgptFucmodCustomerService.getList(OrgptFucmodCustomerEntity.class);
		return Result.success(listOrgptFucmodCustomers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取机构客户和类型导入信息",notes="根据ID获取机构客户和类型导入信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		OrgptFucmodCustomerEntity task = orgptFucmodCustomerService.get(OrgptFucmodCustomerEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取机构客户和类型导入信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建机构客户和类型导入")
	public ResponseMessage<?> create(@ApiParam(name="机构客户和类型导入对象")@RequestBody OrgptFucmodCustomerEntity orgptFucmodCustomer, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<OrgptFucmodCustomerEntity>> failures = validator.validate(orgptFucmodCustomer);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			orgptFucmodCustomerService.save(orgptFucmodCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("机构客户和类型导入信息保存失败");
		}
		return Result.success(orgptFucmodCustomer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新机构客户和类型导入",notes="更新机构客户和类型导入")
	public ResponseMessage<?> update(@ApiParam(name="机构客户和类型导入对象")@RequestBody OrgptFucmodCustomerEntity orgptFucmodCustomer) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<OrgptFucmodCustomerEntity>> failures = validator.validate(orgptFucmodCustomer);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			orgptFucmodCustomerService.saveOrUpdate(orgptFucmodCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新机构客户和类型导入信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新机构客户和类型导入信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除机构客户和类型导入")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			orgptFucmodCustomerService.deleteEntityById(OrgptFucmodCustomerEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("机构客户和类型导入删除失败");
		}

		return Result.success();
	}
}

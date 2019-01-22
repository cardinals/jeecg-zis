package com.rms.assethistory.controller;
import com.rms.asset.entity.AssetEntity;
import com.rms.assethistory.entity.AssetHistoryEntity;
import com.rms.assethistory.service.AssetHistoryServiceI;
import com.rms.contract.entity.ContractEntity;
import com.rms.employee.entity.EmployeeEntity;
import com.rms.payment.entity.PaymentEntity;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.jeecg.demo.dao.JeecgMinidaoDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: RMS_历史资产表
 * @author onlineGenerator
 * @date 2018-11-05 16:11:29
 * @version V1.0   
 *
 */
@Api(value="AssetHistory",description="RMS_历史资产表",tags="assetHistoryController")
@Controller
@RequestMapping("/assetHistoryController")
public class AssetHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AssetHistoryController.class);

	@Autowired
	private AssetHistoryServiceI assetHistoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;


	/**
	 * RMS_历史资产表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/assethistory/assetHistoryList");
	}

	/**
	 * 查询合同下的付款信息
	 */

	@RequestMapping(params = "datagridHis")
	public void datagridHis(AssetHistoryEntity assetHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AssetHistoryEntity.class, dataGrid);
		
		String paramId = request.getParameter("paramId");
		
		AssetEntity asset = systemService.get(AssetEntity.class, Integer.parseInt(paramId));
		cq.eq("assetId",asset.getAssetId());
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		parameterMap.remove("paramId");
		//排序
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lastUpdateTime", "desc");
		cq.setOrder(map);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, assetHistory, request.getParameterMap());
		cq.add();
		this.assetHistoryService.getDataGridReturn(cq, true);
		
		//以下为新增的地方
		List<AssetHistoryEntity> gzAssetList = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();
		for(AssetHistoryEntity temp:gzAssetList){
			  //此为针对原来的行数据，拓展的新字段
	        Map m = new HashMap();
	        m.put("orgName",this.jeecgMinidaoDao.getDepartment(temp.getEmployeeId()));//部门名称
	        m.put("employName",this.jeecgMinidaoDao.getEmployeeName(temp.getEmployeeId()));//员工名称
	        extMap.put(temp.getEmployeeId(), m);
		}
		String departmentId="employeeId";//页面传递上来的关联字段
		TagUtil.datagridExt(response, dataGrid,extMap,departmentId);
		
		
	}
	/***
	 * 
	 * ==============当期用户的历史资产信息=============
	 * @param assetHistory
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid2")
	public void datagrid2(AssetHistoryEntity assetHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(AssetHistoryEntity.class, dataGrid);
		//查询条件组装器
		String paramId = request.getParameter("paramId");
		
		if(StringUtil.isNotEmpty(paramId)) {
			EmployeeEntity employee = systemService.getEntity(EmployeeEntity.class, paramId);
			if(employee!=null && StringUtil.isNotEmpty(employee.getEmployeeId())) {
				//1.通过 employeeId 查询出匹配好的一条历史分配设备记录的asset_id
				String employeeId = employee.getEmployeeId();
				//AssetHistoryEntity assetHistory2 = assetHistoryService.findByProperty(AssetHistoryEntity.class, "employeeId", employeeId).get(0);
				if(employeeId != null && StringUtil.isNotEmpty(employeeId)){
					cq.eq("employeeId",employeeId);
					cq.eq("operation", "2");//2代表分配操作
					Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
					parameterMap.remove("paramId");
					org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, assetHistory, parameterMap);
					cq.add();
					this.assetHistoryService.getDataGridReturn(cq, true);
					
					//---start
					List<AssetHistoryEntity> gzAssetList = dataGrid.getResults();
					Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();

					for(AssetHistoryEntity temp:gzAssetList){
						  //此为针对原来的行数据，拓展的新字段
				        Map m = new HashMap();
			            if(StringUtil.isNotEmpty(temp.getAssetId())) {
					 	     m.put("CurrentPeople",this.jeecgMinidaoDao.getEmployeeNameByAssetId(temp.getAssetId()));
			            }
				        extMap.put(temp.getAssetId(), m);
					}
					String assetId="assetId";//页面传递上来的关联字段
					TagUtil.datagridExt(response, dataGrid,extMap,assetId);
					//----end
					
				}
				
			}
		}
		
	
	}
	
	
	@RequestMapping(params = "datagrid")
	public void datagrid(AssetHistoryEntity assetHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AssetHistoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, assetHistory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.assetHistoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除RMS_历史资产表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AssetHistoryEntity assetHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		assetHistory = systemService.getEntity(AssetHistoryEntity.class, assetHistory.getId());
		message = "RMS_历史资产表删除成功";
		try{
			assetHistoryService.delete(assetHistory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_历史资产表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_历史资产表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_历史资产表删除成功";
		try{
			for(String id:ids.split(",")){
				AssetHistoryEntity assetHistory = systemService.getEntity(AssetHistoryEntity.class, 
				Integer.parseInt(id)
				);
				assetHistoryService.delete(assetHistory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_历史资产表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加RMS_历史资产表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AssetHistoryEntity assetHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_历史资产表添加成功";
		try{
			assetHistoryService.save(assetHistory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_历史资产表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新RMS_历史资产表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AssetHistoryEntity assetHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_历史资产表更新成功";
		AssetHistoryEntity t = assetHistoryService.get(AssetHistoryEntity.class, assetHistory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(assetHistory, t);
			assetHistoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_历史资产表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * RMS_历史资产表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AssetHistoryEntity assetHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(assetHistory.getId())) {
			assetHistory = assetHistoryService.getEntity(AssetHistoryEntity.class, assetHistory.getId());
			req.setAttribute("assetHistoryPage", assetHistory);
		}
		return new ModelAndView("com/rms/assethistory/assetHistory-add");
	}
	/**
	 * RMS_历史资产表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AssetHistoryEntity assetHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(assetHistory.getId())) {
			assetHistory = assetHistoryService.getEntity(AssetHistoryEntity.class, assetHistory.getId());
			req.setAttribute("assetHistoryPage", assetHistory);
		}
		return new ModelAndView("com/rms/assethistory/assetHistory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","assetHistoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AssetHistoryEntity assetHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AssetHistoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, assetHistory, request.getParameterMap());
		List<AssetHistoryEntity> assetHistorys = this.assetHistoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_历史资产表");
		modelMap.put(NormalExcelConstants.CLASS,AssetHistoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_历史资产表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,assetHistorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AssetHistoryEntity assetHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_历史资产表");
    	modelMap.put(NormalExcelConstants.CLASS,AssetHistoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_历史资产表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<AssetHistoryEntity> listAssetHistoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AssetHistoryEntity.class,params);
				for (AssetHistoryEntity assetHistory : listAssetHistoryEntitys) {
					assetHistoryService.save(assetHistory);
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
	@ApiOperation(value="RMS_历史资产表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<AssetHistoryEntity>> list() {
		List<AssetHistoryEntity> listAssetHistorys=assetHistoryService.getList(AssetHistoryEntity.class);
		return Result.success(listAssetHistorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_历史资产表信息",notes="根据ID获取RMS_历史资产表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		AssetHistoryEntity task = assetHistoryService.get(AssetHistoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_历史资产表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_历史资产表")
	public ResponseMessage<?> create(@ApiParam(name="RMS_历史资产表对象")@RequestBody AssetHistoryEntity assetHistory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AssetHistoryEntity>> failures = validator.validate(assetHistory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			assetHistoryService.save(assetHistory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_历史资产表信息保存失败");
		}
		return Result.success(assetHistory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_历史资产表",notes="更新RMS_历史资产表")
	public ResponseMessage<?> update(@ApiParam(name="RMS_历史资产表对象")@RequestBody AssetHistoryEntity assetHistory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AssetHistoryEntity>> failures = validator.validate(assetHistory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			assetHistoryService.saveOrUpdate(assetHistory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_历史资产表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_历史资产表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_历史资产表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			assetHistoryService.deleteEntityById(AssetHistoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_历史资产表删除失败");
		}

		return Result.success();
	}
}

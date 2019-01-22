package com.rms.asset.controller;
import com.rms.asset.entity.AssetEntity;
import com.rms.asset.service.AssetServiceI;
import com.rms.contract.entity.ContractEntity;
import com.rms.employee.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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
 * @Description: RMS_资产管理
 * @author onlineGenerator
 * @date 2018-11-05 16:03:30
 * @version V1.0   
 *
 */
@Api(value="Asset",description="RMS_资产管理",tags="assetController")
@Controller
@RequestMapping("/assetController")
public class AssetController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AssetController.class);

	@Autowired
	private AssetServiceI assetService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;

	
	/**
	 * ==============历史资产信息跳转===========
	 * 
	 * @return
	 */
	@RequestMapping(params = "goHisAsset")
	public ModelAndView goHisAsset(AssetEntity Asset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(Asset.getId())) {
			Asset = assetService.getEntity(AssetEntity.class, Asset.getId());
			req.setAttribute("HisAssetPage", Asset);
		}
		return new ModelAndView("com/rms/asset/asset-his");
	}
	

	/**
	 * RMS_资产管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/asset/assetList");
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(AssetEntity asset,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AssetEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, asset, request.getParameterMap());
		try{
		
		//自定义追加查询条件
		String query_purchaseDate_begin = request.getParameter("purchaseDate_begin");
		String query_purchaseDate_end = request.getParameter("purchaseDate_end");
		//电脑序列号
		String pcNo = request.getParameter("pcNo");
		if(StringUtil.isNotEmpty(pcNo)){
			cq.like("pcNo", pcNo);
		}
		if(StringUtil.isNotEmpty(query_purchaseDate_begin)){
			cq.ge("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_begin));
		}
		if(StringUtil.isNotEmpty(query_purchaseDate_end)){
			cq.le("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		//TSUser user = ResourceUtil.getSessionUser();
		//cq.eq("createdBy", user.getId());
		//cq.add();
		this.assetService.getDataGridReturn(cq, true);
		
		//以下为新增的地方
		List<AssetEntity> gzAssetList = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();

		String[] array = {"0","1","2","3"};// //库房：0、一层机房；1、三层库房；2、四层库房；3、五层库房；
		for(AssetEntity temp:gzAssetList){
			  //此为针对原来的行数据，拓展的新字段
	        Map m = new HashMap();
            if(StringUtil.isNotEmpty(temp.getEmployeeId())) {
		        int index = Arrays.binarySearch(array,temp.getEmployeeId());
		        if(index > 0) {
		        	 m.put("orgName","");//部门名称
		        	 m.put("employName",this.jeecgMinidaoDao.getTypename(temp.getEmployeeId()));//员工名称--这里对应的机房位置
		        }else {
		        	m.put("orgName",this.jeecgMinidaoDao.getDepartment(temp.getDepartmentId()));//部门名称
		 	        m.put("employName",this.jeecgMinidaoDao.getEmployeeName(temp.getDepartmentId()));//员工名称
		        }
            }
	        extMap.put(temp.getDepartmentId(), m);
		}
		String departmentId="departmentId";//页面传递上来的关联字段
		TagUtil.datagridExt(response, dataGrid,extMap,departmentId);
	}
	
	@RequestMapping(params = "datagrid_old")
	public void datagrid_old(AssetEntity asset,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AssetEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, asset, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_purchaseDate_begin = request.getParameter("purchaseDate_begin");
		String query_purchaseDate_end = request.getParameter("purchaseDate_end");
		if(StringUtil.isNotEmpty(query_purchaseDate_begin)){
			cq.ge("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_begin));
		}
		if(StringUtil.isNotEmpty(query_purchaseDate_end)){
			cq.le("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purchaseDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.assetService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * ====================查看当前资产信息========================
	 * @param asset
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid2")
	public void datagrid2(AssetEntity asset,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AssetEntity.class, dataGrid);
		//查询条件组装器
		String paramId = request.getParameter("paramId");
		
		if(StringUtil.isNotEmpty(paramId)) {
			EmployeeEntity employee = systemService.getEntity(EmployeeEntity.class, paramId);
			if(employee!=null && StringUtil.isNotEmpty(employee.getEmployeeId())) {
				cq.eq("employeeId",employee.getEmployeeId());
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("lastUpdateTime", "desc");
				cq.setOrder(map);
				Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
				parameterMap.remove("paramId");
				org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, asset, parameterMap);
				cq.add();
				this.assetService.getDataGridReturn(cq, true);
				TagUtil.datagrid(response, dataGrid);
			}
		}
		
	}
	
	
	
	/**
	 * 删除RMS_资产管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AssetEntity asset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		asset = systemService.getEntity(AssetEntity.class, asset.getId());
		message = "RMS_资产管理删除成功";
		try{
			assetService.delete(asset);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_资产管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_资产管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_资产管理删除成功";
		try{
			for(String id:ids.split(",")){
				AssetEntity asset = systemService.getEntity(AssetEntity.class, 
				Integer.parseInt(id)
				);
				assetService.delete(asset);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_资产管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 
	 /**
		 * ===========================更新但前使用人，使用部门字段，并增加历史分配记录
		 * 
		 * @param ids
		 * @return
		 */
		@RequestMapping(params = "updateEmployee")
		@ResponseBody
		public AjaxJson updateEmployee(AssetEntity asset, HttpServletRequest request) {
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "资源分配成功";
			try{
			String[] array = {"0","1","2","3"};// //库房：0、一层机房；1、三层库房；2、四层库房；3、五层库房；
			
		    if (StringUtil.isNotEmpty(asset.getId())) {
					asset = assetService.getEntity(AssetEntity.class, asset.getId());
					String  emplyeeId = asset.getEmployeeId();
					if(StringUtil.isNotEmpty(emplyeeId)) {
						int index = Arrays.binarySearch(array,emplyeeId);
				        if(index > -1) {
				        	String yuanGongName  = request.getParameter("yuanGongName");
				        	if(StringUtil.isNotEmpty(yuanGongName)) {
								EmployeeEntity employee2 = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeName", yuanGongName);
								String employeeId = employee2.getEmployeeId();
								if(StringUtil.isNotEmpty(employeeId)) {
									asset.setOperation("2");
									asset.setEmployeeId(employeeId);
									asset.setDepartmentId(employeeId);
									assetService.saveOrUpdate2(asset,asset.getAssetId());
								}
							}
				        }else {
							EmployeeEntity employee = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeId", emplyeeId);
							if(employee != null && StringUtil.isNotEmpty(employee.getEmployeeName())) {
								String yuanGongName  = request.getParameter("yuanGongName");
								if(!employee.getEmployeeName().equals(yuanGongName)) {
									if(StringUtil.isNotEmpty(yuanGongName)) {
										EmployeeEntity employee2 = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeName", yuanGongName);
										String employeeId = employee2.getEmployeeId();
										if(StringUtil.isNotEmpty(employeeId)) {
											asset.setOperation("2");
											asset.setEmployeeId(employeeId);
											asset.setDepartmentId(employeeId);
											assetService.saveOrUpdate2(asset,asset.getAssetId());
										}
									}
								}
							}
				        }
			
					}else {//如果页面字段为空
						

						String yuanGongName  = request.getParameter("yuanGongName");
							if(StringUtil.isNotEmpty(yuanGongName)) {
								EmployeeEntity employee2 = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeName", yuanGongName);
								String employeeId = employee2.getEmployeeId();
								if(StringUtil.isNotEmpty(employeeId)) {
									asset.setOperation("2");
									asset.setEmployeeId(employeeId);
									asset.setDepartmentId(employeeId);
									assetService.saveOrUpdate2(asset,asset.getAssetId());
								}
							}
					}
				}
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "资源分配失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	
		//=============================资产废弃======================================
		@RequestMapping(params = "discardAsset")
		@ResponseBody
		public AjaxJson discardAsset(AssetEntity asset, HttpServletRequest request) {
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "资产废弃成功";
			try{
				
			
				
				if (StringUtil.isNotEmpty(asset.getId())) {
					asset = assetService.get(AssetEntity.class, asset.getId());
					
					asset.setOperation("4");
					/*String storehouse = request.getParameter("storehouse");
					asset.setStorehouse(storehouse);
					asset.setDepartmentId(storehouse);//清空部门
					asset.setEmployeeId(storehouse);//使用人改为 库房
					assetService.updateEntitie(asset);*/
					
					
				}
				
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "资产废弃失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
		
		
		/**
		 * ================================资产回收
		 * 
		 *
		 */
		@RequestMapping(params = "assetRetrieve")
		@ResponseBody
		public AjaxJson assetRetrieve(AssetEntity asset, HttpServletRequest request) {
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "RMS_资产管理添加成功";
			try{
				
				
				if (StringUtil.isNotEmpty(asset.getId())) {
					asset = assetService.get(AssetEntity.class, asset.getId());
					String storehouse = request.getParameter("storehouse");
					asset.setOperation("3");
					asset.setStorehouse(storehouse);
					asset.setDepartmentId(storehouse);//清空部门
					asset.setEmployeeId(storehouse);//使用人改为 库房
					assetService.updateEntitie(asset);
					
					
				}
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "RMS_资产管理添加失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
		
		
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AssetEntity asset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_资产管理添加成功";
		try{
			TSUser tSUser = ResourceUtil.getSessionUser();
			  String maxField = "asset_id";//带有日期的字段
			  String tableName = "rms_asset_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      asset.setAssetId(dateFormatId);
		      asset.setOperation("0");//0-新增操作
		      asset.setCreatedBy(tSUser.getId());
			assetService.save2(asset,dateFormatId);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_资产管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新RMS_资产管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AssetEntity asset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_资产管理更新成功";
		AssetEntity t = assetService.get(AssetEntity.class, asset.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(asset, t);
			
			 /* String maxField = "asset_id";//带有日期的字段
			  String tableName = "rms_asset_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      t.setAssetId(dateFormatId);*/
		      //0、新增；1、修改；2、分配；3、回收；4、废弃；
		      t.setOperation("1");//0-修改操作
			
			//assetService.save2(asset,dateFormatId);
		      TSUser tSUser = ResourceUtil.getSessionUser();
		      t.setUpdatedBy(tSUser.getId());
			assetService.saveOrUpdate3(t,asset.getAssetId());
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_资产管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "goAllotStaff")
	public ModelAndView goAllotStaff(AssetEntity asset, HttpServletRequest req) {
	
		//String id = req.getParameter("id");
		//1.根据id查询emplyeeId
		if (StringUtil.isNotEmpty(asset.getId())) {
			asset = assetService.getEntity(AssetEntity.class, asset.getId());
			req.setAttribute("assetPage", asset);
		}
		String  emplyeeId = asset.getEmployeeId();
		if(StringUtil.isNotEmpty(emplyeeId)) {
			EmployeeEntity employee = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeId", emplyeeId);
			//2.根据emplyeeId查询用户名称
			if(employee != null && StringUtil.isNotEmpty(employee.getEmployeeName()))
				req.setAttribute("name", employee.getEmployeeName());
		}

		return new ModelAndView("com/rms/asset/asset-allot");
	}
	
	
	/**
	 * 资源分配
	 * @param asset
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goRetrieveAsset")
	public ModelAndView goRetrieveAsset(AssetEntity asset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(asset.getId())) {
			asset = assetService.getEntity(AssetEntity.class, asset.getId());
			//1.资产类型
			String assetType = null;
			//2.资产编号
			String type = asset.getMaintype();
			if(StringUtils.isNotEmpty(type)){
				String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='assets' and  t.typecode= ? ";
				Map<String,Object> typeName =  assetService.findOneForJdbc(sql, type);
				//asset.setMaintype((typeName!=null?(String) typeName.get("typename"):"未知"));
				 assetType = (String) typeName.get("typename");
			}
			
			String bianhao = asset.getAssetNo();
			//3.员工编号
			//4.员工姓名
			String employeeId = asset.getEmployeeId();
			EmployeeEntity employee = null;
			if(StringUtil.isNotEmpty(employeeId)) {
			    employee = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeId", employeeId);
				/*if(employee != null )
					req.setAttribute("employeePage", employee);*/
			}
			req.setAttribute("assetType", assetType);//资产类型
			req.setAttribute("employeePage", employee);
			req.setAttribute("assetPage", asset);
		}
		return new ModelAndView("com/rms/asset/asset-retrieve");
	}

	/**
	 * RMS_资产管理=====================资产废弃=============================
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDiscardAsset")
	public ModelAndView goDiscardAsset(AssetEntity asset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(asset.getId())) {

			asset = assetService.getEntity(AssetEntity.class, asset.getId());
			//1.资产类型
			String assetType = null;
			//2.资产编号
			String type = asset.getMaintype();
			if(StringUtils.isNotEmpty(type)){
				String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='assets' and  t.typecode= ? ";
				Map<String,Object> typeName =  assetService.findOneForJdbc(sql, type);
				//asset.setMaintype((typeName!=null?(String) typeName.get("typename"):"未知"));
				 assetType = (String) typeName.get("typename");
			}
			
			String bianhao = asset.getAssetNo();
			//3.员工编号
			//4.员工姓名
			String employeeId = asset.getEmployeeId();
			EmployeeEntity employee = null;
			if(StringUtil.isNotEmpty(employeeId)) {
			    employee = assetService.findUniqueByProperty(EmployeeEntity.class, "employeeId", employeeId);
				/*if(employee != null )
					req.setAttribute("employeePage", employee);*/
			}
			req.setAttribute("assetType", assetType);//资产类型
			req.setAttribute("employeePage", employee);
			req.setAttribute("assetPage", asset);
		
		}
		return new ModelAndView("com/rms/asset/asset-discard");
	}
	
	
	/**
	 * RMS_资产管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AssetEntity asset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(asset.getId())) {
			asset = assetService.getEntity(AssetEntity.class, asset.getId());
			req.setAttribute("assetPage", asset);
		}
		return new ModelAndView("com/rms/asset/asset-add");
	}
	/**
	 * RMS_资产管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AssetEntity asset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(asset.getId())) {
			asset = assetService.getEntity(AssetEntity.class, asset.getId());
			req.setAttribute("assetPage", asset);
		}
		return new ModelAndView("com/rms/asset/asset-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","assetController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AssetEntity asset,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AssetEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, asset, request.getParameterMap());
		List<AssetEntity> assets = this.assetService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_资产管理");
		modelMap.put(NormalExcelConstants.CLASS,AssetEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_资产管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,assets);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AssetEntity asset,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_资产管理");
    	modelMap.put(NormalExcelConstants.CLASS,AssetEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_资产管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<AssetEntity> listAssetEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AssetEntity.class,params);
				for (AssetEntity asset : listAssetEntitys) {
					assetService.save(asset);
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
	@ApiOperation(value="RMS_资产管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<AssetEntity>> list() {
		List<AssetEntity> listAssets=assetService.getList(AssetEntity.class);
		return Result.success(listAssets);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_资产管理信息",notes="根据ID获取RMS_资产管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		AssetEntity task = assetService.get(AssetEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_资产管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_资产管理")
	public ResponseMessage<?> create(@ApiParam(name="RMS_资产管理对象")@RequestBody AssetEntity asset, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AssetEntity>> failures = validator.validate(asset);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			assetService.save(asset);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_资产管理信息保存失败");
		}
		return Result.success(asset);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_资产管理",notes="更新RMS_资产管理")
	public ResponseMessage<?> update(@ApiParam(name="RMS_资产管理对象")@RequestBody AssetEntity asset) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AssetEntity>> failures = validator.validate(asset);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			assetService.saveOrUpdate(asset);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_资产管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_资产管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_资产管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			assetService.deleteEntityById(AssetEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_资产管理删除失败");
		}

		return Result.success();
	}
}

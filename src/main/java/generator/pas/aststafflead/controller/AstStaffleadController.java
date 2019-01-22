package generator.pas.aststafflead.controller;
import generator.pas.aststafflead.entity.AstStaffleadEntity;
import generator.pas.aststafflead.service.AstStaffleadServiceI;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;

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
 * @Description: 员工对上级领导评价
 * @author onlineGenerator
 * @date 2018-11-27 13:22:49
 * @version V1.0   
 *
 */
@Api(value="AstStafflead",description="员工对上级领导评价",tags="astStaffleadController")
@Controller
@RequestMapping("/astStaffleadController")
public class AstStaffleadController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AstStaffleadController.class);

	@Autowired
	private AstStaffleadServiceI astStaffleadService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 员工对上级领导评价列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("generator/pas/aststafflead/astStaffleadList");
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
	public void datagrid(AstStaffleadEntity astStafflead,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AstStaffleadEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, astStafflead, request.getParameterMap());
		//-start
		TSUser loginUser = ResourceUtil.getSessionUser();
		Boolean initStatus = astStaffleadService.getInitStatusByLoginUser(loginUser);
		//True:为开始评分 / False:尚未开始评分
		if(initStatus) {
			//已经评完分，展示数据就可以了
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			astStaffleadService.getDataGridReturn(cq, true);
		}else {
			//尚未开始评分
			List<TSUser> deptLeader = astStaffleadService.getDeptLeaderOfLoginUser(loginUser);
			//组装数据列表
			List<AstStaffleadEntity> result = astStaffleadService.installDataGridList(deptLeader,loginUser);
			dataGrid.setResults(result);
			
		}
		TagUtil.datagrid(response, dataGrid);
		
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
			Boolean result = astStaffleadService.getInitStatusByLoginUser(loginUser);
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
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(AstStaffleadEntity page){
		String message = null;
		List<AstStaffleadEntity> pasStaffLeadList=page.getAstStaffleadList();    
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasStaffLeadList)){
			for(AstStaffleadEntity pasOnStaff:pasStaffLeadList){
					try {
						message = "astStafflead例子添加成功";
						astStaffleadService.save(pasOnStaff);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		return j;
	}
	/**
	 * 删除员工对上级领导评价
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AstStaffleadEntity astStafflead, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		astStafflead = systemService.getEntity(AstStaffleadEntity.class, astStafflead.getId());
		message = "员工对上级领导评价删除成功";
		try{
			astStaffleadService.delete(astStafflead);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "员工对上级领导评价删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除员工对上级领导评价
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工对上级领导评价删除成功";
		try{
			for(String id:ids.split(",")){
				AstStaffleadEntity astStafflead = systemService.getEntity(AstStaffleadEntity.class, 
				id
				);
				astStaffleadService.delete(astStafflead);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "员工对上级领导评价删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加员工对上级领导评价
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AstStaffleadEntity astStafflead, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工对上级领导评价添加成功";
		try{
			astStaffleadService.save(astStafflead);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "员工对上级领导评价添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新员工对上级领导评价
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AstStaffleadEntity astStafflead, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工对上级领导评价更新成功";
		AstStaffleadEntity t = astStaffleadService.get(AstStaffleadEntity.class, astStafflead.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(astStafflead, t);
			astStaffleadService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "员工对上级领导评价更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 员工对上级领导评价新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AstStaffleadEntity astStafflead, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(astStafflead.getId())) {
			astStafflead = astStaffleadService.getEntity(AstStaffleadEntity.class, astStafflead.getId());
			req.setAttribute("astStaffleadPage", astStafflead);
		}
		return new ModelAndView("generator/pas/aststafflead/astStafflead-add");
	}
	/**
	 * 员工对上级领导评价编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AstStaffleadEntity astStafflead, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(astStafflead.getId())) {
			astStafflead = astStaffleadService.getEntity(AstStaffleadEntity.class, astStafflead.getId());
			req.setAttribute("astStaffleadPage", astStafflead);
		}
		return new ModelAndView("generator/pas/aststafflead/astStafflead-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","astStaffleadController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AstStaffleadEntity astStafflead,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AstStaffleadEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, astStafflead, request.getParameterMap());
		List<AstStaffleadEntity> astStaffleads = this.astStaffleadService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"员工对上级领导评价");
		modelMap.put(NormalExcelConstants.CLASS,AstStaffleadEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工对上级领导评价列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,astStaffleads);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AstStaffleadEntity astStafflead,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"员工对上级领导评价");
    	modelMap.put(NormalExcelConstants.CLASS,AstStaffleadEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工对上级领导评价列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<AstStaffleadEntity> listAstStaffleadEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AstStaffleadEntity.class,params);
				for (AstStaffleadEntity astStafflead : listAstStaffleadEntitys) {
					astStaffleadService.save(astStafflead);
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
	@ApiOperation(value="员工对上级领导评价列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<AstStaffleadEntity>> list() {
		List<AstStaffleadEntity> listAstStaffleads=astStaffleadService.getList(AstStaffleadEntity.class);
		return Result.success(listAstStaffleads);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取员工对上级领导评价信息",notes="根据ID获取员工对上级领导评价信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		AstStaffleadEntity task = astStaffleadService.get(AstStaffleadEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取员工对上级领导评价信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建员工对上级领导评价")
	public ResponseMessage<?> create(@ApiParam(name="员工对上级领导评价对象")@RequestBody AstStaffleadEntity astStafflead, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AstStaffleadEntity>> failures = validator.validate(astStafflead);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			astStaffleadService.save(astStafflead);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("员工对上级领导评价信息保存失败");
		}
		return Result.success(astStafflead);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新员工对上级领导评价",notes="更新员工对上级领导评价")
	public ResponseMessage<?> update(@ApiParam(name="员工对上级领导评价对象")@RequestBody AstStaffleadEntity astStafflead) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AstStaffleadEntity>> failures = validator.validate(astStafflead);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			astStaffleadService.saveOrUpdate(astStafflead);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新员工对上级领导评价信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新员工对上级领导评价信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除员工对上级领导评价")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			astStaffleadService.deleteEntityById(AstStaffleadEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("员工对上级领导评价删除失败");
		}

		return Result.success();
	}
}

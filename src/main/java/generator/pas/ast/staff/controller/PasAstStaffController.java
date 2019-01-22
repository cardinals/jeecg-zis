package generator.pas.ast.staff.controller;
import generator.pas.ast.staff.entity.PasAstStaffEntity;
import generator.pas.ast.staff.service.PasAstStaffServiceI;
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

import org.apache.commons.collections.CollectionUtils;
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
 * @Description: 员工互评_资产
 * @author onlineGenerator
 * @date 2018-11-26 14:03:23
 * @version V1.0   
 *
 */
@Api(value="PasAstStaff",description="员工互评_资产",tags="pasAstStaffController")
@Controller
@RequestMapping("/pasAstStaffController")
public class PasAstStaffController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PasAstStaffController.class);

	@Autowired
	private PasAstStaffServiceI pasAstStaffService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 员工互评_资产列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("generator/pas/ast/staff/pasAstStaffList");
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
	public void datagrid(PasAstStaffEntity pasAstStaff,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		CriteriaQuery cq = new CriteriaQuery(PasAstStaffEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasAstStaff, request.getParameterMap());
		//判断当前用户是否已经开始评分
		Boolean initStatus = pasAstStaffService.getInitStatusByLoginUser(loginUser);
		if(initStatus) {
			//开始评分
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			pasAstStaffService.getDataGridReturn(cq, true);
		}else {
			//尚未开始评分
			//获取当前资产登录用户所在部门的所有普通员工角色(注:当前用户除外)
			List<Map<String, String>> listArray = pasAstStaffService.getAllDeptStaffExceptLoginUser(loginUser);
			//组装数据列表
			List<PasAstStaffEntity> result = pasAstStaffService.installDataGridList(listArray,loginUser);
			dataGrid.setResults(result);
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除员工互评_资产
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PasAstStaffEntity pasAstStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pasAstStaff = systemService.getEntity(PasAstStaffEntity.class, pasAstStaff.getId());
		message = "员工互评_资产删除成功";
		try{
			pasAstStaffService.delete(pasAstStaff);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "员工互评_资产删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除员工互评_资产
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工互评_资产删除成功";
		try{
			for(String id:ids.split(",")){
				PasAstStaffEntity pasAstStaff = systemService.getEntity(PasAstStaffEntity.class, 
				id
				);
				pasAstStaffService.delete(pasAstStaff);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "员工互评_资产删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加员工互评_资产
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PasAstStaffEntity pasAstStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工互评_资产添加成功";
		try{
			pasAstStaffService.save(pasAstStaff);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "员工互评_资产添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(PasAstStaffEntity page){
		String message = null;
		List<PasAstStaffEntity> pasAstStaffList=page.getPasAstStaffList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasAstStaffList)){
			for(PasAstStaffEntity pasAstStaff:pasAstStaffList){
				try {
					message = "PasAstStaff例子添加成功";
					//jeecgDemo.setStatus("0");
					pasAstStaffService.save(pasAstStaff);
					systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	/**
	 * 更新员工互评_资产
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PasAstStaffEntity pasAstStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "员工互评_资产更新成功";
		PasAstStaffEntity t = pasAstStaffService.get(PasAstStaffEntity.class, pasAstStaff.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pasAstStaff, t);
			pasAstStaffService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "员工互评_资产更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 员工互评_资产新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PasAstStaffEntity pasAstStaff, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasAstStaff.getId())) {
			pasAstStaff = pasAstStaffService.getEntity(PasAstStaffEntity.class, pasAstStaff.getId());
			req.setAttribute("pasAstStaffPage", pasAstStaff);
		}
		return new ModelAndView("generator/pas/ast/staff/pasAstStaff-add");
	}
	/**
	 * 员工互评_资产编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PasAstStaffEntity pasAstStaff, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasAstStaff.getId())) {
			pasAstStaff = pasAstStaffService.getEntity(PasAstStaffEntity.class, pasAstStaff.getId());
			req.setAttribute("pasAstStaffPage", pasAstStaff);
		}
		return new ModelAndView("generator/pas/ast/staff/pasAstStaff-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pasAstStaffController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PasAstStaffEntity pasAstStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PasAstStaffEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasAstStaff, request.getParameterMap());
		List<PasAstStaffEntity> pasAstStaffs = this.pasAstStaffService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"员工互评_资产");
		modelMap.put(NormalExcelConstants.CLASS,PasAstStaffEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工互评_资产列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pasAstStaffs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PasAstStaffEntity pasAstStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"员工互评_资产");
    	modelMap.put(NormalExcelConstants.CLASS,PasAstStaffEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("员工互评_资产列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PasAstStaffEntity> listPasAstStaffEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PasAstStaffEntity.class,params);
				for (PasAstStaffEntity pasAstStaff : listPasAstStaffEntitys) {
					pasAstStaffService.save(pasAstStaff);
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
	@ApiOperation(value="员工互评_资产列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PasAstStaffEntity>> list() {
		List<PasAstStaffEntity> listPasAstStaffs=pasAstStaffService.getList(PasAstStaffEntity.class);
		return Result.success(listPasAstStaffs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取员工互评_资产信息",notes="根据ID获取员工互评_资产信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PasAstStaffEntity task = pasAstStaffService.get(PasAstStaffEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取员工互评_资产信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建员工互评_资产")
	public ResponseMessage<?> create(@ApiParam(name="员工互评_资产对象")@RequestBody PasAstStaffEntity pasAstStaff, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasAstStaffEntity>> failures = validator.validate(pasAstStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasAstStaffService.save(pasAstStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("员工互评_资产信息保存失败");
		}
		return Result.success(pasAstStaff);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新员工互评_资产",notes="更新员工互评_资产")
	public ResponseMessage<?> update(@ApiParam(name="员工互评_资产对象")@RequestBody PasAstStaffEntity pasAstStaff) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasAstStaffEntity>> failures = validator.validate(pasAstStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasAstStaffService.saveOrUpdate(pasAstStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新员工互评_资产信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新员工互评_资产信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除员工互评_资产")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pasAstStaffService.deleteEntityById(PasAstStaffEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("员工互评_资产删除失败");
		}

		return Result.success();
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
			Boolean result = pasAstStaffService.getInitStatusByLoginUser(loginUser);
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
}

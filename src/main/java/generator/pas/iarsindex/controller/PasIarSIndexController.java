package generator.pas.iarsindex.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;

import generator.pas.iarsindex.entity.PasIarSIndexEntity;
import generator.pas.iarsindex.service.PasIarSIndexServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 绩效考核系统投研交流评价表
 * @author onlineGenerator
 * @date 2018-10-31 14:43:26
 * @version V1.0   
 *
 */
@Api(value="PasIarSIndex",description="绩效考核系统投研交流评价表",tags="pasIarSIndexController")
@Controller
@RequestMapping("/pasIarSIndexController")
public class PasIarSIndexController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PasIarSIndexController.class);

	@Autowired
	private PasIarSIndexServiceI pasIarSIndexService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	

	
	@RequestMapping(params = "iframePage")
	public ModelAndView iframePage(HttpServletRequest request,ModelMap model) {
		return new ModelAndView("generator/pas/iarsindex/pasIarSIndexIframePage");
	}
	
	
	/**
	 * 绩效考核系统投研交流评价表列表 页面跳转
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,ModelMap model) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		Boolean initStatus  = pasIarSIndexService.getInitStatusByLoginUser(loginUser);
		String searchStatus = "";//查看评语权限
		if(initStatus) {
			searchStatus = "OK";
		}else {
			searchStatus = "NoAu";
		}
		model.put("searchStatus", searchStatus);
		return new ModelAndView("generator/pas/iarsindex/pasIarSIndexList");
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
			Boolean result = pasIarSIndexService.getInitStatusByLoginUser(loginUser);
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
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(PasIarSIndexEntity pasIarSIndex,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		CriteriaQuery cq = new CriteriaQuery(PasIarSIndexEntity.class, dataGrid);
		Boolean status = pasIarSIndexService.getInitStatusByLoginUser(loginUser);
		//true : 已评分  false : 未评分
		if(status) {
			//评分已经结束,将已经评的分数查询出来
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			pasIarSIndexService.getDataGridReturn(cq, true);
		}else {
			//评分尚未开始,组装数据
			//pasOnDeptService.getDataGridReturnCustomized(cq,loginUser);  
			pasIarSIndexService.installCustomizedData(cq,loginUser);
			dataGrid.setResults(cq.getResults());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除绩效考核系统投研交流评价表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PasIarSIndexEntity pasIarSIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pasIarSIndex = systemService.getEntity(PasIarSIndexEntity.class, pasIarSIndex.getId());
		message = "绩效考核系统投研交流评价表删除成功";
		try{
			pasIarSIndexService.delete(pasIarSIndex);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "绩效考核系统投研交流评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除绩效考核系统投研交流评价表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "绩效考核系统投研交流评价表删除成功";
		try{
			for(String id:ids.split(",")){
				PasIarSIndexEntity pasIarSIndex = systemService.getEntity(PasIarSIndexEntity.class, 
				id
				);
				pasIarSIndexService.delete(pasIarSIndex);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "绩效考核系统投研交流评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加绩效考核系统投研交流评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PasIarSIndexEntity pasIarSIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "绩效考核系统投研交流评价表添加成功";
		try{
			pasIarSIndexService.save(pasIarSIndex);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "绩效考核系统投研交流评价表添加失败";
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
	public AjaxJson saveRows(PasIarSIndexEntity page){
		String message = null;
		List<PasIarSIndexEntity> pasIarSIndexList=page.getPasIarSIndexList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasIarSIndexList)){
			for(PasIarSIndexEntity pasIarSIndex:pasIarSIndexList){
					try {
						message = "PasIarSIndex例子添加成功";
						//jeecgDemo.setStatus("0");
						pasIarSIndexService.save(pasIarSIndex);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}
		}
		return j;
	}
	
	/**
	 * 更新绩效考核系统投研交流评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PasIarSIndexEntity pasIarSIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "绩效考核系统投研交流评价表更新成功";
		PasIarSIndexEntity t = pasIarSIndexService.get(PasIarSIndexEntity.class, pasIarSIndex.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pasIarSIndex, t);
			pasIarSIndexService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "绩效考核系统投研交流评价表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 绩效考核系统投研交流评价表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PasIarSIndexEntity pasIarSIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasIarSIndex.getId())) {
			pasIarSIndex = pasIarSIndexService.getEntity(PasIarSIndexEntity.class, pasIarSIndex.getId());
			req.setAttribute("pasIarSIndexPage", pasIarSIndex);
		}
		return new ModelAndView("generator/pas/iarsindex/pasIarSIndex-add");
	}
	/**
	 * 绩效考核系统投研交流评价表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PasIarSIndexEntity pasIarSIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasIarSIndex.getId())) {
			pasIarSIndex = pasIarSIndexService.getEntity(PasIarSIndexEntity.class, pasIarSIndex.getId());
			req.setAttribute("pasIarSIndexPage", pasIarSIndex);
		}
		return new ModelAndView("generator/pas/iarsindex/pasIarSIndex-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pasIarSIndexController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PasIarSIndexEntity pasIarSIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PasIarSIndexEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasIarSIndex, request.getParameterMap());
		List<PasIarSIndexEntity> pasIarSIndexs = this.pasIarSIndexService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"绩效考核系统投研交流评价表");
		modelMap.put(NormalExcelConstants.CLASS,PasIarSIndexEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("绩效考核系统投研交流评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pasIarSIndexs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PasIarSIndexEntity pasIarSIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"绩效考核系统投研交流评价表");
    	modelMap.put(NormalExcelConstants.CLASS,PasIarSIndexEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("绩效考核系统投研交流评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PasIarSIndexEntity> listPasIarSIndexEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PasIarSIndexEntity.class,params);
				for (PasIarSIndexEntity pasIarSIndex : listPasIarSIndexEntitys) {
					pasIarSIndexService.save(pasIarSIndex);
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
	@ApiOperation(value="绩效考核系统投研交流评价表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PasIarSIndexEntity>> list() {
		List<PasIarSIndexEntity> listPasIarSIndexs=pasIarSIndexService.getList(PasIarSIndexEntity.class);
		return Result.success(listPasIarSIndexs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取绩效考核系统投研交流评价表信息",notes="根据ID获取绩效考核系统投研交流评价表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PasIarSIndexEntity task = pasIarSIndexService.get(PasIarSIndexEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取绩效考核系统投研交流评价表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建绩效考核系统投研交流评价表")
	public ResponseMessage<?> create(@ApiParam(name="绩效考核系统投研交流评价表对象")@RequestBody PasIarSIndexEntity pasIarSIndex, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasIarSIndexEntity>> failures = validator.validate(pasIarSIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasIarSIndexService.save(pasIarSIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("绩效考核系统投研交流评价表信息保存失败");
		}
		return Result.success(pasIarSIndex);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新绩效考核系统投研交流评价表",notes="更新绩效考核系统投研交流评价表")
	public ResponseMessage<?> update(@ApiParam(name="绩效考核系统投研交流评价表对象")@RequestBody PasIarSIndexEntity pasIarSIndex) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasIarSIndexEntity>> failures = validator.validate(pasIarSIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasIarSIndexService.saveOrUpdate(pasIarSIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新绩效考核系统投研交流评价表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新绩效考核系统投研交流评价表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除绩效考核系统投研交流评价表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pasIarSIndexService.deleteEntityById(PasIarSIndexEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("绩效考核系统投研交流评价表删除失败");
		}

		return Result.success();
	}
	
	

    @RequestMapping(params = "showDialogPage")
    public ModelAndView showDialogPage(HttpServletRequest request,ModelMap model) {
            return new ModelAndView("generator/pas/iarsindex/pasIarSIndexDialog");
    }
}

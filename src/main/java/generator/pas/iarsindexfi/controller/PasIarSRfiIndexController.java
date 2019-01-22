package generator.pas.iarsindexfi.controller;
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

import generator.pas.iarsindexfi.entity.PasIarSRfiIndexEntity;
import generator.pas.iarsindexfi.service.PasIarSRfiIndexServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 2018年度投研交流满意度评价表
 * @author onlineGenerator
 * @date 2018-11-06 14:13:13
 * @version V1.0   
 *
 */
@Api(value="PasIarSRfiIndex",description="2018年度投研交流满意度评价表",tags="pasIarSRfiIndexController")
@Controller
@RequestMapping("/pasIarSRfiIndexController")
public class PasIarSRfiIndexController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PasIarSRfiIndexController.class);

	@Autowired
	private PasIarSRfiIndexServiceI pasIarSRfiIndexService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 2018年度投研交流满意度评价表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,ModelMap model) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		Boolean initStatus  = pasIarSRfiIndexService.getInitStatusByLoginUser(loginUser);
		String searchStatus = "";//查看评语权限
		if(initStatus) {
			searchStatus = "OK";
		}else {
			searchStatus = "NoAu";
		}
		model.put("searchStatus", searchStatus);
		return new ModelAndView("generator/pas/iarsindexfi/pasIarSRfiIndexList");
	}

	
	/**
	 * 2018年度投研交流满意度评价表列表框架页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "iframePage")
	public ModelAndView iframePage(HttpServletRequest request) {
		return new ModelAndView("generator/pas/iarsindexfi/pasIarSRfiIndexIframePage");
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
	public void datagrid(PasIarSRfiIndexEntity pasIarSRfiIndex,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		CriteriaQuery cq = new CriteriaQuery(PasIarSRfiIndexEntity.class, dataGrid);
		Boolean status = pasIarSRfiIndexService.getInitStatusByLoginUser(loginUser);
		//true : 已评分  false : 未评分
		if(status) {
			//评分已经结束,将已经评的分数查询出来
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			pasIarSRfiIndexService.getDataGridReturn(cq, true);
		}else {
			//评分尚未开始,组装数据
			//pasOnDeptService.getDataGridReturnCustomized(cq,loginUser);  
			pasIarSRfiIndexService.installCustomizedData(cq,loginUser);
			dataGrid.setResults(cq.getResults());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除2018年度投研交流满意度评价表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PasIarSRfiIndexEntity pasIarSRfiIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pasIarSRfiIndex = systemService.getEntity(PasIarSRfiIndexEntity.class, pasIarSRfiIndex.getId());
		message = "2018年度投研交流满意度评价表删除成功";
		try{
			pasIarSRfiIndexService.delete(pasIarSRfiIndex);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度投研交流满意度评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除2018年度投研交流满意度评价表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度投研交流满意度评价表删除成功";
		try{
			for(String id:ids.split(",")){
				PasIarSRfiIndexEntity pasIarSRfiIndex = systemService.getEntity(PasIarSRfiIndexEntity.class, 
				id
				);
				pasIarSRfiIndexService.delete(pasIarSRfiIndex);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度投研交流满意度评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加2018年度投研交流满意度评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PasIarSRfiIndexEntity pasIarSRfiIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度投研交流满意度评价表添加成功";
		try{
			pasIarSRfiIndexService.save(pasIarSRfiIndex);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度投研交流满意度评价表添加失败";
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
	public AjaxJson saveRows(PasIarSRfiIndexEntity page){
		String message = null;
		List<PasIarSRfiIndexEntity> pasIarSRfiIndexList=page.getPasIarSRfiIndexList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasIarSRfiIndexList)){
			for(PasIarSRfiIndexEntity pasIarSRfiIndex:pasIarSRfiIndexList){
					try {
						message = "PasIarSRfiIndex例子添加成功";
						//jeecgDemo.setStatus("0");
						pasIarSRfiIndexService.save(pasIarSRfiIndex);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		return j;
	}
	
	/**
	 * 更新2018年度投研交流满意度评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PasIarSRfiIndexEntity pasIarSRfiIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度投研交流满意度评价表更新成功";
		PasIarSRfiIndexEntity t = pasIarSRfiIndexService.get(PasIarSRfiIndexEntity.class, pasIarSRfiIndex.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pasIarSRfiIndex, t);
			pasIarSRfiIndexService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "2018年度投研交流满意度评价表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 2018年度投研交流满意度评价表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PasIarSRfiIndexEntity pasIarSRfiIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasIarSRfiIndex.getId())) {
			pasIarSRfiIndex = pasIarSRfiIndexService.getEntity(PasIarSRfiIndexEntity.class, pasIarSRfiIndex.getId());
			req.setAttribute("pasIarSRfiIndexPage", pasIarSRfiIndex);
		}
		return new ModelAndView("generator/pas/iarsindexfi/pasIarSRfiIndex-add");
	}
	/**
	 * 2018年度投研交流满意度评价表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PasIarSRfiIndexEntity pasIarSRfiIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasIarSRfiIndex.getId())) {
			pasIarSRfiIndex = pasIarSRfiIndexService.getEntity(PasIarSRfiIndexEntity.class, pasIarSRfiIndex.getId());
			req.setAttribute("pasIarSRfiIndexPage", pasIarSRfiIndex);
		}
		return new ModelAndView("generator/pas/iarsindexfi/pasIarSRfiIndex-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pasIarSRfiIndexController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PasIarSRfiIndexEntity pasIarSRfiIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PasIarSRfiIndexEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasIarSRfiIndex, request.getParameterMap());
		List<PasIarSRfiIndexEntity> pasIarSRfiIndexs = this.pasIarSRfiIndexService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"2018年度投研交流满意度评价表");
		modelMap.put(NormalExcelConstants.CLASS,PasIarSRfiIndexEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("2018年度投研交流满意度评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pasIarSRfiIndexs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PasIarSRfiIndexEntity pasIarSRfiIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"2018年度投研交流满意度评价表");
    	modelMap.put(NormalExcelConstants.CLASS,PasIarSRfiIndexEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("2018年度投研交流满意度评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PasIarSRfiIndexEntity> listPasIarSRfiIndexEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PasIarSRfiIndexEntity.class,params);
				for (PasIarSRfiIndexEntity pasIarSRfiIndex : listPasIarSRfiIndexEntitys) {
					pasIarSRfiIndexService.save(pasIarSRfiIndex);
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
	@ApiOperation(value="2018年度投研交流满意度评价表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PasIarSRfiIndexEntity>> list() {
		List<PasIarSRfiIndexEntity> listPasIarSRfiIndexs=pasIarSRfiIndexService.getList(PasIarSRfiIndexEntity.class);
		return Result.success(listPasIarSRfiIndexs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取2018年度投研交流满意度评价表信息",notes="根据ID获取2018年度投研交流满意度评价表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PasIarSRfiIndexEntity task = pasIarSRfiIndexService.get(PasIarSRfiIndexEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取2018年度投研交流满意度评价表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建2018年度投研交流满意度评价表")
	public ResponseMessage<?> create(@ApiParam(name="2018年度投研交流满意度评价表对象")@RequestBody PasIarSRfiIndexEntity pasIarSRfiIndex, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasIarSRfiIndexEntity>> failures = validator.validate(pasIarSRfiIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasIarSRfiIndexService.save(pasIarSRfiIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("2018年度投研交流满意度评价表信息保存失败");
		}
		return Result.success(pasIarSRfiIndex);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新2018年度投研交流满意度评价表",notes="更新2018年度投研交流满意度评价表")
	public ResponseMessage<?> update(@ApiParam(name="2018年度投研交流满意度评价表对象")@RequestBody PasIarSRfiIndexEntity pasIarSRfiIndex) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasIarSRfiIndexEntity>> failures = validator.validate(pasIarSRfiIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasIarSRfiIndexService.saveOrUpdate(pasIarSRfiIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新2018年度投研交流满意度评价表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新2018年度投研交流满意度评价表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除2018年度投研交流满意度评价表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pasIarSRfiIndexService.deleteEntityById(PasIarSRfiIndexEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("2018年度投研交流满意度评价表删除失败");
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
			Boolean result = pasIarSRfiIndexService.getInitStatusByLoginUser(loginUser);
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
	
    @RequestMapping(params = "showDialogPage")
    public ModelAndView showDialogPage(HttpServletRequest request,ModelMap model) {
            return new ModelAndView("generator/pas/iarsindexfi/pasIarSRfiIndexDialog");
    }
}

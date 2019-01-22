package generator.pas.sma.controller;
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

import generator.pas.sma.entity.PasSmaSupIndexEntity;
import generator.pas.sma.service.PasSmaSupIndexServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 2018年度市场销售支持满意度评价表
 * @author onlineGenerator
 * @date 2018-11-07 13:30:41
 * @version V1.0   
 *
 */
@Api(value="PasSmaSupIndex",description="2018年度市场销售支持满意度评价表",tags="pasSmaSupIndexController")
@Controller
@RequestMapping("/pasSmaSupIndexController")
public class PasSmaSupIndexController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PasSmaSupIndexController.class);

	@Autowired
	private PasSmaSupIndexServiceI pasSmaSupIndexService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "iframePage")
	public ModelAndView iframePage(HttpServletRequest request,ModelMap model) {
		return new ModelAndView("generator/pas/sma/pasSmaSupIndexIframePage");
	}

	/**
	 * 2018年度市场销售支持满意度评价表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,ModelMap model) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		Boolean initStatus  = pasSmaSupIndexService.getInitStatusByLoginUser(loginUser);
		String searchStatus = "";//查看评语权限
		if(initStatus) {
			searchStatus = "OK";
		}else {
			searchStatus = "NoAu";
		}
		model.put("searchStatus", searchStatus);
		return new ModelAndView("generator/pas/sma/pasSmaSupIndexList");
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
			Boolean result = pasSmaSupIndexService.getInitStatusByLoginUser(loginUser);
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
	public void datagrid(PasSmaSupIndexEntity pasSmaSupIndex,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser loginUser = ResourceUtil.getSessionUser();
		CriteriaQuery cq = new CriteriaQuery(PasSmaSupIndexEntity.class, dataGrid);
		//true : 已评分  false : 未评分
		Boolean status = pasSmaSupIndexService.getInitStatusByLoginUser(loginUser);
		if(status) {
			//评分已经结束,将已经评的分数查询出来
			cq.add(Restrictions.eq("appraiser", loginUser.getUserName()));
			pasSmaSupIndexService.getDataGridReturn(cq, true);
		}else {
			//评分尚未开始,组装数据
			pasSmaSupIndexService.installCustomizedData(cq,loginUser);
			dataGrid.setResults(cq.getResults());
		
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除2018年度市场销售支持满意度评价表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PasSmaSupIndexEntity pasSmaSupIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pasSmaSupIndex = systemService.getEntity(PasSmaSupIndexEntity.class, pasSmaSupIndex.getId());
		message = "2018年度市场销售支持满意度评价表删除成功";
		try{
			pasSmaSupIndexService.delete(pasSmaSupIndex);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度市场销售支持满意度评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除2018年度市场销售支持满意度评价表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度市场销售支持满意度评价表删除成功";
		try{
			for(String id:ids.split(",")){
				PasSmaSupIndexEntity pasSmaSupIndex = systemService.getEntity(PasSmaSupIndexEntity.class, 
				id
				);
				pasSmaSupIndexService.delete(pasSmaSupIndex);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度市场销售支持满意度评价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加2018年度市场销售支持满意度评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PasSmaSupIndexEntity pasSmaSupIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度市场销售支持满意度评价表添加成功";
		try{
			pasSmaSupIndexService.save(pasSmaSupIndex);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "2018年度市场销售支持满意度评价表添加失败";
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
	public AjaxJson saveRows(PasSmaSupIndexEntity page){
		String message = null;
		List<PasSmaSupIndexEntity> pasSmaSupIndexList=page.getPasSmaSupIndexList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasSmaSupIndexList)){
			for(PasSmaSupIndexEntity pasSmaSupIndex:pasSmaSupIndexList){
					try {
						message = "PasSmaSupIndex例子添加成功";
						//jeecgDemo.setStatus("0");
						pasSmaSupIndexService.save(pasSmaSupIndex);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			}
		}
		return j;
	}
	
	/**
	 * 更新2018年度市场销售支持满意度评价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PasSmaSupIndexEntity pasSmaSupIndex, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "2018年度市场销售支持满意度评价表更新成功";
		PasSmaSupIndexEntity t = pasSmaSupIndexService.get(PasSmaSupIndexEntity.class, pasSmaSupIndex.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pasSmaSupIndex, t);
			pasSmaSupIndexService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "2018年度市场销售支持满意度评价表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 2018年度市场销售支持满意度评价表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PasSmaSupIndexEntity pasSmaSupIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasSmaSupIndex.getId())) {
			pasSmaSupIndex = pasSmaSupIndexService.getEntity(PasSmaSupIndexEntity.class, pasSmaSupIndex.getId());
			req.setAttribute("pasSmaSupIndexPage", pasSmaSupIndex);
		}
		return new ModelAndView("generator/pas/sma/pasSmaSupIndex-add");
	}
	/**
	 * 2018年度市场销售支持满意度评价表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PasSmaSupIndexEntity pasSmaSupIndex, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasSmaSupIndex.getId())) {
			pasSmaSupIndex = pasSmaSupIndexService.getEntity(PasSmaSupIndexEntity.class, pasSmaSupIndex.getId());
			req.setAttribute("pasSmaSupIndexPage", pasSmaSupIndex);
		}
		return new ModelAndView("generator/pas/sma/pasSmaSupIndex-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pasSmaSupIndexController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PasSmaSupIndexEntity pasSmaSupIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PasSmaSupIndexEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasSmaSupIndex, request.getParameterMap());
		List<PasSmaSupIndexEntity> pasSmaSupIndexs = this.pasSmaSupIndexService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"2018年度市场销售支持满意度评价表");
		modelMap.put(NormalExcelConstants.CLASS,PasSmaSupIndexEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("2018年度市场销售支持满意度评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pasSmaSupIndexs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PasSmaSupIndexEntity pasSmaSupIndex,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"2018年度市场销售支持满意度评价表");
    	modelMap.put(NormalExcelConstants.CLASS,PasSmaSupIndexEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("2018年度市场销售支持满意度评价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PasSmaSupIndexEntity> listPasSmaSupIndexEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PasSmaSupIndexEntity.class,params);
				for (PasSmaSupIndexEntity pasSmaSupIndex : listPasSmaSupIndexEntitys) {
					pasSmaSupIndexService.save(pasSmaSupIndex);
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
	@ApiOperation(value="2018年度市场销售支持满意度评价表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PasSmaSupIndexEntity>> list() {
		List<PasSmaSupIndexEntity> listPasSmaSupIndexs=pasSmaSupIndexService.getList(PasSmaSupIndexEntity.class);
		return Result.success(listPasSmaSupIndexs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取2018年度市场销售支持满意度评价表信息",notes="根据ID获取2018年度市场销售支持满意度评价表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PasSmaSupIndexEntity task = pasSmaSupIndexService.get(PasSmaSupIndexEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取2018年度市场销售支持满意度评价表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建2018年度市场销售支持满意度评价表")
	public ResponseMessage<?> create(@ApiParam(name="2018年度市场销售支持满意度评价表对象")@RequestBody PasSmaSupIndexEntity pasSmaSupIndex, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasSmaSupIndexEntity>> failures = validator.validate(pasSmaSupIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasSmaSupIndexService.save(pasSmaSupIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("2018年度市场销售支持满意度评价表信息保存失败");
		}
		return Result.success(pasSmaSupIndex);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新2018年度市场销售支持满意度评价表",notes="更新2018年度市场销售支持满意度评价表")
	public ResponseMessage<?> update(@ApiParam(name="2018年度市场销售支持满意度评价表对象")@RequestBody PasSmaSupIndexEntity pasSmaSupIndex) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasSmaSupIndexEntity>> failures = validator.validate(pasSmaSupIndex);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasSmaSupIndexService.saveOrUpdate(pasSmaSupIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新2018年度市场销售支持满意度评价表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新2018年度市场销售支持满意度评价表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除2018年度市场销售支持满意度评价表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pasSmaSupIndexService.deleteEntityById(PasSmaSupIndexEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("2018年度市场销售支持满意度评价表删除失败");
		}

		return Result.success();
	}
	

    @RequestMapping(params = "showDialogPage")
    public ModelAndView showDialogPage(HttpServletRequest request,ModelMap model) {
            return new ModelAndView("generator/pas/sma/pasSmaSupIndexDialog");
    }
}

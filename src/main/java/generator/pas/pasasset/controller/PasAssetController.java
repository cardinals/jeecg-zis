package generator.pas.pasasset.controller;
import generator.pas.pasasset.entity.PasAssetEntity;
import generator.pas.pasasset.service.PasAssetServiceI;
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
 * @Description: 资产名单
 * @author onlineGenerator
 * @date 2018-11-26 11:39:33
 * @version V1.0   
 *
 */
@Api(value="PasAsset",description="资产名单",tags="pasAssetController")
@Controller
@RequestMapping("/pasAssetController")
public class PasAssetController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PasAssetController.class);

	@Autowired
	private PasAssetServiceI pasAssetService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 资产名单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("generator/pas/pasasset/pasAssetList");
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
	public void datagrid(PasAssetEntity pasAsset,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PasAssetEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasAsset, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.pasAssetService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除资产名单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PasAssetEntity pasAsset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		pasAsset = systemService.getEntity(PasAssetEntity.class, pasAsset.getId());
		message = "资产名单删除成功";
		try{
			pasAssetService.delete(pasAsset);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资产名单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资产名单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资产名单删除成功";
		try{
			for(String id:ids.split(",")){
				PasAssetEntity pasAsset = systemService.getEntity(PasAssetEntity.class, 
				id
				);
				pasAssetService.delete(pasAsset);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资产名单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资产名单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PasAssetEntity pasAsset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资产名单添加成功";
		try{
			pasAssetService.save(pasAsset);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资产名单添加失败";
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
	public AjaxJson saveRows(PasAssetEntity page){
		String message = null;
		List<PasAssetEntity> pasAssetList=page.getPasAssetList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(pasAssetList)){
			for(PasAssetEntity pasAsset:pasAssetList){
				if (StringUtil.isNotEmpty(pasAsset.getId())) {
					PasAssetEntity t =pasAssetService.get(PasAssetEntity.class, pasAsset.getId());
					try {
						message = "PasAsset例子更新成功";
						MyBeanUtils.copyBeanNotNull2Bean(pasAsset, t);
						pasAssetService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						message = "PasAsset例子添加成功";
						//jeecgDemo.setStatus("0");
						pasAssetService.save(pasAsset);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		return j;
	}
	
	/**
	 * 更新资产名单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PasAssetEntity pasAsset, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资产名单更新成功";
		PasAssetEntity t = pasAssetService.get(PasAssetEntity.class, pasAsset.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(pasAsset, t);
			pasAssetService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资产名单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 资产名单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PasAssetEntity pasAsset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasAsset.getId())) {
			pasAsset = pasAssetService.getEntity(PasAssetEntity.class, pasAsset.getId());
			req.setAttribute("pasAssetPage", pasAsset);
		}
		return new ModelAndView("generator/pas/pasasset/pasAsset-add");
	}
	/**
	 * 资产名单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PasAssetEntity pasAsset, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pasAsset.getId())) {
			pasAsset = pasAssetService.getEntity(PasAssetEntity.class, pasAsset.getId());
			req.setAttribute("pasAssetPage", pasAsset);
		}
		return new ModelAndView("generator/pas/pasasset/pasAsset-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","pasAssetController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PasAssetEntity pasAsset,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PasAssetEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pasAsset, request.getParameterMap());
		List<PasAssetEntity> pasAssets = this.pasAssetService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资产名单");
		modelMap.put(NormalExcelConstants.CLASS,PasAssetEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资产名单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,pasAssets);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PasAssetEntity pasAsset,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资产名单");
    	modelMap.put(NormalExcelConstants.CLASS,PasAssetEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资产名单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PasAssetEntity> listPasAssetEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PasAssetEntity.class,params);
				for (PasAssetEntity pasAsset : listPasAssetEntitys) {
					pasAssetService.save(pasAsset);
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
	@ApiOperation(value="资产名单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PasAssetEntity>> list() {
		List<PasAssetEntity> listPasAssets=pasAssetService.getList(PasAssetEntity.class);
		return Result.success(listPasAssets);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取资产名单信息",notes="根据ID获取资产名单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PasAssetEntity task = pasAssetService.get(PasAssetEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取资产名单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建资产名单")
	public ResponseMessage<?> create(@ApiParam(name="资产名单对象")@RequestBody PasAssetEntity pasAsset, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasAssetEntity>> failures = validator.validate(pasAsset);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasAssetService.save(pasAsset);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资产名单信息保存失败");
		}
		return Result.success(pasAsset);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新资产名单",notes="更新资产名单")
	public ResponseMessage<?> update(@ApiParam(name="资产名单对象")@RequestBody PasAssetEntity pasAsset) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PasAssetEntity>> failures = validator.validate(pasAsset);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			pasAssetService.saveOrUpdate(pasAsset);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新资产名单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新资产名单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除资产名单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			pasAssetService.deleteEntityById(PasAssetEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资产名单删除失败");
		}

		return Result.success();
	}
}

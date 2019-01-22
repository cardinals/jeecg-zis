package com.jeecg.maintest.controller;
import com.jeecg.maintest.entity.MainTestEntity;
import com.jeecg.maintest.service.MainTestServiceI;
import com.jeecg.maintest.page.MainTestPage;
import com.jeecg.fubiaotest.entity.FubiaoTestEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 主表测试版
 * @author onlineGenerator
 * @date 2018-07-16 10:05:35
 * @version V1.0   
 *
 */
@Api(value="MainTest",description="主表测试版",tags="mainTestController")
@Controller
@RequestMapping("/mainTestController")
public class MainTestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MainTestController.class);

	@Autowired
	private MainTestServiceI mainTestService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 主表测试版列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/maintest/mainTestList");
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
	public void datagrid(MainTestEntity mainTest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MainTestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mainTest);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mainTestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除主表测试版
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MainTestEntity mainTest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mainTest = systemService.getEntity(MainTestEntity.class, mainTest.getId());
		String message = "主表测试版删除成功";
		try{
			mainTestService.delMain(mainTest);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "主表测试版删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除主表测试版
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "主表测试版删除成功";
		try{
			for(String id:ids.split(",")){
				MainTestEntity mainTest = systemService.getEntity(MainTestEntity.class,
				id
				);
				mainTestService.delMain(mainTest);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "主表测试版删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加主表测试版
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MainTestEntity mainTest,MainTestPage mainTestPage, HttpServletRequest request) {
		List<FubiaoTestEntity> fubiaoTestList =  mainTestPage.getFubiaoTestList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			mainTestService.addMain(mainTest, fubiaoTestList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "主表测试版添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新主表测试版
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MainTestEntity mainTest,MainTestPage mainTestPage, HttpServletRequest request) {
		List<FubiaoTestEntity> fubiaoTestList =  mainTestPage.getFubiaoTestList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			mainTestService.updateMain(mainTest, fubiaoTestList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新主表测试版失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 主表测试版新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MainTestEntity mainTest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mainTest.getId())) {
			mainTest = mainTestService.getEntity(MainTestEntity.class, mainTest.getId());
			req.setAttribute("mainTestPage", mainTest);
		}
		return new ModelAndView("com/jeecg/maintest/mainTest-add");
	}
	
	/**
	 * 主表测试版编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MainTestEntity mainTest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mainTest.getId())) {
			mainTest = mainTestService.getEntity(MainTestEntity.class, mainTest.getId());
			req.setAttribute("mainTestPage", mainTest);
		}
		return new ModelAndView("com/jeecg/maintest/mainTest-update");
	}
	
	
	/**
	 * 加载明细列表[主表测试]
	 * 
	 * @return
	 */
	@RequestMapping(params = "fubiaoTestList")
	public ModelAndView fubiaoTestList(MainTestEntity mainTest, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		
		Object fU_NAME0 = mainTest.getMainName();
		Object fU_AGE0 = mainTest.getMainAge();
		//===================================================================================
		//查询-主表测试
	    String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
	    try{
	    	List<FubiaoTestEntity> fubiaoTestEntityList = systemService.findHql(hql0,fU_NAME0,fU_AGE0);
			req.setAttribute("fubiaoTestList", fubiaoTestEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/fubiaotest/fubiaoTestList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(MainTestEntity mainTest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(MainTestEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mainTest);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<MainTestEntity> list=this.mainTestService.getListByCriteriaQuery(cq, false);
    	List<MainTestPage> pageList=new ArrayList<MainTestPage>();
        if(list!=null&&list.size()>0){
        	for(MainTestEntity entity:list){
        		try{
        		MainTestPage page=new MainTestPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object fU_NAME0 = entity.getMainName();
           		    Object fU_AGE0 = entity.getMainAge();
				    String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
        	        List<FubiaoTestEntity> fubiaoTestEntityList = systemService.findHql(hql0,fU_NAME0,fU_AGE0);
            		page.setFubiaoTestList(fubiaoTestEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"主表测试版");
        map.put(NormalExcelConstants.CLASS,MainTestPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("主表测试版列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<MainTestPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), MainTestPage.class, params);
				MainTestEntity entity1=null;
				for (MainTestPage page : list) {
					entity1=new MainTestEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            mainTestService.addMain(entity1, page.getFubiaoTestList());
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
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"主表测试版");
		map.put(NormalExcelConstants.CLASS,MainTestPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("主表测试版列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "mainTestController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="主表测试版列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<MainTestPage>> list() {
		List<MainTestEntity> list= mainTestService.getList(MainTestEntity.class);
    	List<MainTestPage> pageList=new ArrayList<MainTestPage>();
        if(list!=null&&list.size()>0){
        	for(MainTestEntity entity:list){
        		try{
        			MainTestPage page=new MainTestPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object fU_NAME0 = entity.getMainName();
					Object fU_AGE0 = entity.getMainAge();
				     String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
	    			List<FubiaoTestEntity> fubiaoTestOldList = this.mainTestService.findHql(hql0,fU_NAME0,fU_AGE0);
            		page.setFubiaoTestList(fubiaoTestOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取主表测试版信息",notes="根据ID获取主表测试版信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		MainTestEntity task = mainTestService.get(MainTestEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取主表测试版信息为空");
		}
		MainTestPage page = new MainTestPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object fU_NAME0 = task.getMainName();
				Object fU_AGE0 = task.getMainAge();
		    String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
			List<FubiaoTestEntity> fubiaoTestOldList = this.mainTestService.findHql(hql0,fU_NAME0,fU_AGE0);
    		page.setFubiaoTestList(fubiaoTestOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建主表测试版")
	public ResponseMessage<?> create(@ApiParam(name="主表测试版对象")@RequestBody MainTestPage mainTestPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MainTestPage>> failures = validator.validate(mainTestPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<FubiaoTestEntity> fubiaoTestList =  mainTestPage.getFubiaoTestList();
		
		MainTestEntity mainTest = new MainTestEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(mainTestPage,mainTest);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存主表测试版失败");
        }
		mainTestService.addMain(mainTest, fubiaoTestList);

		return Result.success(mainTest);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新主表测试版",notes="更新主表测试版")
	public ResponseMessage<?> update(@RequestBody MainTestPage mainTestPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MainTestPage>> failures = validator.validate(mainTestPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<FubiaoTestEntity> fubiaoTestList =  mainTestPage.getFubiaoTestList();
		
		MainTestEntity mainTest = new MainTestEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(mainTestPage,mainTest);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("主表测试版更新失败");
        }
		mainTestService.updateMain(mainTest, fubiaoTestList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除主表测试版")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			MainTestEntity mainTest = mainTestService.get(MainTestEntity.class, id);
			mainTestService.delMain(mainTest);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("主表测试版删除失败");
		}

		return Result.success();
	}
}

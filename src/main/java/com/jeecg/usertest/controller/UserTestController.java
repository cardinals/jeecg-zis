package com.jeecg.usertest.controller;
import com.jeecg.usertest.entity.UserTestEntity;
import com.jeecg.usertest.service.UserTestServiceI;
import com.jeecg.usertest.page.UserTestPage;
import com.jeecg.usertestline.entity.UserTestLineEntity;
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
 * @Description: 测试用户信息维护
 * @author onlineGenerator
 * @date 2018-03-23 17:05:52
 * @version V1.0   
 *
 */
@Api(value="UserTest",description="测试用户信息维护",tags="userTestController")
@Controller
@RequestMapping("/userTestController")
public class UserTestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserTestController.class);

	@Autowired
	private UserTestServiceI userTestService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 测试用户信息维护列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/usertest/userTestList");
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
	public void datagrid(UserTestEntity userTest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UserTestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, userTest);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.userTestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除测试用户信息维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(UserTestEntity userTest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		userTest = systemService.getEntity(UserTestEntity.class, userTest.getId());
		String message = "测试用户信息维护删除成功";
		try{
			userTestService.delMain(userTest);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试用户信息维护删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除测试用户信息维护
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "测试用户信息维护删除成功";
		try{
			for(String id:ids.split(",")){
				UserTestEntity userTest = systemService.getEntity(UserTestEntity.class,
				id
				);
				userTestService.delMain(userTest);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "测试用户信息维护删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加测试用户信息维护
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(UserTestEntity userTest,UserTestPage userTestPage, HttpServletRequest request) {
		List<UserTestLineEntity> userTestLineList =  userTestPage.getUserTestLineList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			userTestService.addMain(userTest, userTestLineList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试用户信息维护添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新测试用户信息维护
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(UserTestEntity userTest,UserTestPage userTestPage, HttpServletRequest request) {
		List<UserTestLineEntity> userTestLineList =  userTestPage.getUserTestLineList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			userTestService.updateMain(userTest, userTestLineList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新测试用户信息维护失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 测试用户信息维护新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(UserTestEntity userTest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(userTest.getId())) {
			userTest = userTestService.getEntity(UserTestEntity.class, userTest.getId());
			req.setAttribute("userTestPage", userTest);
		}
		return new ModelAndView("com/jeecg/usertest/userTest-add");
	}
	
	/**
	 * 测试用户信息维护编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(UserTestEntity userTest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(userTest.getId())) {
			userTest = userTestService.getEntity(UserTestEntity.class, userTest.getId());
			req.setAttribute("userTestPage", userTest);
		}
		return new ModelAndView("com/jeecg/usertest/userTest-update");
	}
	
	
	/**
	 * 加载明细列表[usertestline]
	 * 
	 * @return
	 */
	@RequestMapping(params = "userTestLineList")
	public ModelAndView userTestLineList(UserTestEntity userTest, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object uSERNAME0 = userTest.getUsername();
		//===================================================================================
		//查询-usertestline
	    String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
	    try{
	    	List<UserTestLineEntity> userTestLineEntityList = systemService.findHql(hql0,uSERNAME0);
			req.setAttribute("userTestLineList", userTestLineEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/usertestline/userTestLineList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(UserTestEntity userTest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(UserTestEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, userTest);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<UserTestEntity> list=this.userTestService.getListByCriteriaQuery(cq, false);
    	List<UserTestPage> pageList=new ArrayList<UserTestPage>();
        if(list!=null&&list.size()>0){
        	for(UserTestEntity entity:list){
        		try{
        		UserTestPage page=new UserTestPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object uSERNAME0 = entity.getUsername();
				    String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
        	        List<UserTestLineEntity> userTestLineEntityList = systemService.findHql(hql0,uSERNAME0);
            		page.setUserTestLineList(userTestLineEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"测试用户信息维护");
        map.put(NormalExcelConstants.CLASS,UserTestPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("测试用户信息维护列表", "导出人:Jeecg",
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
				List<UserTestPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), UserTestPage.class, params);
				UserTestEntity entity1=null;
				for (UserTestPage page : list) {
					entity1=new UserTestEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            userTestService.addMain(entity1, page.getUserTestLineList());
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
		map.put(NormalExcelConstants.FILE_NAME,"测试用户信息维护");
		map.put(NormalExcelConstants.CLASS,UserTestPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("测试用户信息维护列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "userTestController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="测试用户信息维护列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<UserTestPage>> list() {
		List<UserTestEntity> list= userTestService.getList(UserTestEntity.class);
    	List<UserTestPage> pageList=new ArrayList<UserTestPage>();
        if(list!=null&&list.size()>0){
        	for(UserTestEntity entity:list){
        		try{
        			UserTestPage page=new UserTestPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object uSERNAME0 = entity.getUsername();
				     String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
	    			List<UserTestLineEntity> userTestLineOldList = this.userTestService.findHql(hql0,uSERNAME0);
            		page.setUserTestLineList(userTestLineOldList);
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
	@ApiOperation(value="根据ID获取测试用户信息维护信息",notes="根据ID获取测试用户信息维护信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		UserTestEntity task = userTestService.get(UserTestEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取测试用户信息维护信息为空");
		}
		UserTestPage page = new UserTestPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object uSERNAME0 = task.getUsername();
		    String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
			List<UserTestLineEntity> userTestLineOldList = this.userTestService.findHql(hql0,uSERNAME0);
    		page.setUserTestLineList(userTestLineOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建测试用户信息维护")
	public ResponseMessage<?> create(@ApiParam(name="测试用户信息维护对象")@RequestBody UserTestPage userTestPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<UserTestPage>> failures = validator.validate(userTestPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<UserTestLineEntity> userTestLineList =  userTestPage.getUserTestLineList();
		
		UserTestEntity userTest = new UserTestEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(userTestPage,userTest);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存测试用户信息维护失败");
        }
		userTestService.addMain(userTest, userTestLineList);

		return Result.success(userTest);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新测试用户信息维护",notes="更新测试用户信息维护")
	public ResponseMessage<?> update(@RequestBody UserTestPage userTestPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<UserTestPage>> failures = validator.validate(userTestPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<UserTestLineEntity> userTestLineList =  userTestPage.getUserTestLineList();
		
		UserTestEntity userTest = new UserTestEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(userTestPage,userTest);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("测试用户信息维护更新失败");
        }
		userTestService.updateMain(userTest, userTestLineList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除测试用户信息维护")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			UserTestEntity userTest = userTestService.get(UserTestEntity.class, id);
			userTestService.delMain(userTest);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("测试用户信息维护删除失败");
		}

		return Result.success();
	}
}

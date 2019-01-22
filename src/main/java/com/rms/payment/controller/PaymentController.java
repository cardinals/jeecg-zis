package com.rms.payment.controller;
import com.rms.contract.entity.ContractEntity;
import com.rms.database.entity.DatabaseEntity;
import com.rms.payment.entity.PaymentEntity;
import com.rms.payment.service.PaymentServiceI;
import com.rms.server.entity.ServerEntity;

import java.util.ArrayList;
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
 * @Description: RMS_付款管理
 * @author onlineGenerator
 * @date 2018-11-03 10:12:24
 * @version V1.0   
 *
 */
@Api(value="Payment",description="RMS_付款管理",tags="paymentController")
@Controller
@RequestMapping("/paymentController")
public class PaymentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PaymentController.class);

	@Autowired
	private PaymentServiceI paymentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;

	/**
	 * RMS_付款管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/rms/payment/paymentList");
	}

	/**
	 * 查询合同下的付款信息
	 */

	@RequestMapping(params = "datagridForPayment")
	public void datagridForPayment(PaymentEntity payment,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PaymentEntity.class, dataGrid);
		
		String paramId = request.getParameter("paramId");//这里代表server类的Id
		//1,通过contract类的id查询到contract类的contract_id
		ContractEntity contractEntity = systemService.get(ContractEntity.class, paramId);
		cq.eq("contractId",contractEntity.getContractId());
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		parameterMap.remove("paramId");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, payment, request.getParameterMap());
		cq.add();
		this.paymentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		

	
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
	public void datagrid(PaymentEntity payment,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PaymentEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, payment, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.paymentService.getDataGridReturn(cq, true);
		//TagUtil.datagrid(response, dataGrid);
		
		//================start
		List<PaymentEntity> list = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();
		for(PaymentEntity temp:list){
		        //此为针对原来的行数据，拓展的新字段
		        Map m = new HashMap();
		        m.put("contractInfo",this.jeecgMinidaoDao.getcontractNo(temp.getContractId()));
		        extMap.put(temp.getContractId(), m);
		}
		String contractId="contractId";//页面传递上来的关联字段
		TagUtil.datagridExt(response, dataGrid,extMap,contractId);
		//===================end
	}
	
	/**
	 * 删除RMS_付款管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PaymentEntity payment, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		payment = systemService.getEntity(PaymentEntity.class, payment.getId());
		message = "RMS_付款管理删除成功";
		try{
			paymentService.delete(payment);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_付款管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除RMS_付款管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_付款管理删除成功";
		try{
			for(String id:ids.split(",")){
				PaymentEntity payment = systemService.getEntity(PaymentEntity.class, 
				id
				);
				paymentService.delete(payment);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_付款管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加RMS_付款管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PaymentEntity payment, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_付款管理添加成功";
		try{
			  String maxField = "PAYMENT_ID";//带有日期的字段
			  String tableName = "rms_payment_jeecg";//表名
			  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		      String  currentDate = df.format(new Date());
		      String dateFormatId = (systemService.getIdForCurrentDate(currentDate, maxField, tableName).add(new BigDecimal(1))).toString();
		      payment.setPaymentId(dateFormatId);
		      String contract_id = request.getParameter("contract_id");//
		      
		      String contractId = request.getParameter("contractId");//
		      String sql = "SELECT * from rms_contract_jeecg t  where t.contract_id = ? ";
		      List<Map<String, Object>>  maps =  paymentService.findForJdbc(sql, contractId);
		      if(maps != null && maps.size()> 0 ) {
		    	  String name =(String) maps.get(0).get("name");//合同名称
		    	  if(StringUtil.isNotEmpty(name)) {
		    		  payment.setContractName(name);
		    	  }
		    	  
		    	
		      }
		      
		      //已经付款//paidAmount
			paymentService.save(payment,contract_id);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "RMS_付款管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新RMS_付款管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PaymentEntity payment, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "RMS_付款管理更新成功";
		PaymentEntity t = paymentService.get(PaymentEntity.class, payment.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(payment, t);
			paymentService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "RMS_付款管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * RMS_付款管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PaymentEntity payment, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(payment.getId())) {
			payment = paymentService.getEntity(PaymentEntity.class, payment.getId());
			req.setAttribute("paymentPage", payment);
		}
		return new ModelAndView("com/rms/payment/payment-add");
	}
	/**
	 * RMS_付款管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(PaymentEntity payment, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(payment.getId())) {
			payment = paymentService.getEntity(PaymentEntity.class, payment.getId());
			req.setAttribute("paymentPage", payment);
		}
		return new ModelAndView("com/rms/payment/payment-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","paymentController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PaymentEntity payment,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PaymentEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, payment, request.getParameterMap());
		List<PaymentEntity> payments = this.paymentService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_付款管理");
		modelMap.put(NormalExcelConstants.CLASS,PaymentEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_付款管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,payments);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PaymentEntity payment,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"RMS_付款管理");
    	modelMap.put(NormalExcelConstants.CLASS,PaymentEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("RMS_付款管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PaymentEntity> listPaymentEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PaymentEntity.class,params);
				for (PaymentEntity payment : listPaymentEntitys) {
					paymentService.save(payment);
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
	@ApiOperation(value="RMS_付款管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PaymentEntity>> list() {
		List<PaymentEntity> listPayments=paymentService.getList(PaymentEntity.class);
		return Result.success(listPayments);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取RMS_付款管理信息",notes="根据ID获取RMS_付款管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PaymentEntity task = paymentService.get(PaymentEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取RMS_付款管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建RMS_付款管理")
	public ResponseMessage<?> create(@ApiParam(name="RMS_付款管理对象")@RequestBody PaymentEntity payment, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PaymentEntity>> failures = validator.validate(payment);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			paymentService.save(payment);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_付款管理信息保存失败");
		}
		return Result.success(payment);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新RMS_付款管理",notes="更新RMS_付款管理")
	public ResponseMessage<?> update(@ApiParam(name="RMS_付款管理对象")@RequestBody PaymentEntity payment) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PaymentEntity>> failures = validator.validate(payment);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			paymentService.saveOrUpdate(payment);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新RMS_付款管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新RMS_付款管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除RMS_付款管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			paymentService.deleteEntityById(PaymentEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("RMS_付款管理删除失败");
		}

		return Result.success();
	}
}

package com.jeecg.bankaccount.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.cgform.util.DataHandlerUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
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
import com.jeecg.accountratechanges.entity.AccountRateChangesEntity;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.bankaccount.page.BankAccountPage;
import com.jeecg.bankaccount.service.BankAccountServiceI;
import com.jeecg.bankaccountemp.entity.BankAccountempEntity;
import com.jeecg.bankaccountemp.service.BankAccountempServiceI;
import com.jeecg.bankcode.entity.BankCodeEntity;
import com.jeecg.bankcode.service.BankCodeServiceI;
import com.jeecg.bankcomment.entity.BankCommentEntity;
import com.jeecg.demo.entity.JeecgDemoEntity;
import com.jeecg.productcode.service.ProductCodeServiceI;
import com.jeecg.raiseaccountuse.entity.RaiseAccountUseEntity;
import com.jeecg.reportemail.entity.ReportEmailEntity;
import com.jeecg.reportemail.service.ReportEmailServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 银行账户信息总表
 * @author onlineGenerator
 * @date 2018-06-21 10:23:38
 * @version V1.0   
 *
 */
@Api(value="BankAccount",description="银行账户信息总表",tags="bankAccountController")
@Controller
@RequestMapping("/bankAccountController")
public class BankAccountController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankAccountController.class);

	@Autowired
	private BankAccountServiceI bankAccountService;
	@Autowired
	private BankAccountempServiceI bankAccountempService;
	
	@Autowired
	private BankCodeServiceI BankCodeService;
	
	@Autowired
	private ProductCodeServiceI ProductCodeService;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private ProductCodeServiceI productCodeService;
	@Autowired
	private ReportEmailServiceI reportEmailService;
	
	@RequestMapping(params = "goCheck")
	public ModelAndView goCheck( HttpServletRequest request) {
		logger.info("----审核-----");
		String id=request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			BankAccountEntity bankAccount = bankAccountService.getEntity(BankAccountEntity.class, id);
			request.setAttribute("bankAccountPage", bankAccount);
		}
		return new ModelAndView("com/jeecg/bankaccount/bankAccount-check");
		
	}
	
	@RequestMapping(params = "doCheck")
	@ResponseBody
	public AjaxJson doCheck(String content,String id,String status) {
		logger.info("-------审核意见:"+content);//demo简单作打印,实际项目可酌情处理
		String message = null;
		AjaxJson j = new AjaxJson();
		//根据id查询临时表的实体类
		BankAccountempEntity bankAccountTemp = systemService.getEntity(BankAccountempEntity.class, id);
		BankAccountEntity bankAccount = systemService.getEntity(BankAccountEntity.class, id);
		if("Y".equals(status)){//审核通过才会同步数据
			try {
				if(bankAccountTemp!=null){
					
					//审核通过之后，还需要同步两张附表的数据
					/**
					 * 先同步附表再更新主表，因为主表的账户利率发生变化就不好查了
					 */
					bankAccountService.updateAttach2(id);
					/***
					 * 这一步只是为了同步主表，其实临时表已经有数据
					 */
					MyBeanUtils.copyBeanNotNull2Bean(bankAccountTemp, bankAccount);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{			//在申请人更新的时候，虽然没有更新主表信息，但是允许他更新附表信息，
			     		//所以当审核不通过的时候，需要删除对应的附表信息
			
			//if("Y".equals(bankAccount.getStatus())){
				//...
				//如果之前的历史已经是审核通过的状态，而再次进行驳回的时候，不应该再去删除历史的附表，应该不做任何操作
			//}else{
			/**
			 * 之前的逻辑错了	
			 */
	/*			if(bankAccountTemp!=null){
					//1,删除对应的账户利率这条数据
					String acountInterestRate = bankAccount.getAcountInterestRate();
					if(acountInterestRate!=null&&!acountInterestRate.equals(bankAccount.getAcountInterestRate())){
						AccountRateChangesEntity accountRateChange = systemService.findUniqueByProperty(AccountRateChangesEntity.class, "detailRate", acountInterestRate);
						String sql = "select * from account_rate_changes t where t.detail_rate = ? order by t.update_date desc LIMIT 0,1";
						Map<String, Object>  sss = systemService.findOneForJdbc(sql, acountInterestRate);
						System.out.println(sss);
						//systemService.executeSql(sql, param)
						if(accountRateChange!=null){
							systemService.delete(accountRateChange);
						}
						
					}
					//2,删除对应的在用产品这条数据
					String  onlineProduct = bankAccount.getOnlineProduct();
					if(onlineProduct!=null&&!onlineProduct.equals(bankAccount.getOnlineProduct())){
						RaiseAccountUseEntity raiseAccountUse = systemService.findUniqueByProperty(RaiseAccountUseEntity.class, "preProject", onlineProduct);
						if(raiseAccountUse!=null){
							systemService.delete(raiseAccountUse);
						}
					}
					
				}*/
			//}
			
			
			
		}
		//不管审核通过不通过，都得将审核信息记录下来，方便申请人查看
		BankCommentEntity bankCommentEntity = new BankCommentEntity();
		bankCommentEntity.setForeignKey(id);
		bankCommentEntity.setTime(new Date());
		if(StringUtil.isEmpty(content)){
			if("Y".equals(status)){
				content = "审核通过！";
				
			}else{
				content = "审核未通过！";
			}
		}else{
			if("Y".equals(status)){
				content = "审核通过:"+content;
			}else{
				content = "审核未通过:"+content;
			}
		}
		bankCommentEntity.setComment(content);
		message = "审核成功";
		try{
			bankAccount.setStatus(status);
			this.bankAccountService.save(bankCommentEntity);
			this.bankAccountService.updateEntitie(bankAccount);
			/**
			 * 当审核人审核账户信息的时候，会发送邮件给申请人
			 */
			
		    PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
		    Properties properties = ftpConfig.getProperties();
		    String  mailBubject = "关于账户管理系统审核通知";
		    String  mailBody = "您修改的账户信息审核完毕,审核情况如下:<br>"+content;
		    String sendto = "majianchao@zrfunds.com.cn";
		    String sendcc = "";
		    if(bankAccount != null && StringUtils.isNotEmpty(bankAccount.getJingban())){
		    	  TSUser tSUser = bankAccountService.get(TSUser.class, bankAccount.getJingban());
				    if(tSUser != null&&StringUtils.isNotEmpty(tSUser.getEmail())) {
				    	sendto = tSUser.getEmail();
				    }
		    }
		    logger.info(sendto);
			DataHandlerUtil.sendMail(mailBody,mailBubject,properties,sendto,sendcc);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 获取银行账号类型
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getBankNumberType")
	@ResponseBody
	public AjaxJson getBankNumberType(HttpServletRequest req) {
		
  		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		j.setAttributes(attrMap);
		String id = req.getParameter("id");
		BankAccountEntity bankAccount= systemService.getEntity(BankAccountEntity.class, id);
		String bankNumberType = bankAccount.getAcountType();//账户类型
		String acountStatus  = bankAccount.getAcountStatus();//账户状态
		if(bankNumberType != null){
			attrMap.put("bankNumberType", bankNumberType);//银行账号类型
			attrMap.put("acountStatus", acountStatus);//银行账户状态
			
		}else {
			j.setSuccess(false);
			
		}
		return j;
	}

	/**
	 * 银行账户信息总表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/bankaccount/bankAccountList");
	}
	/**
	 * 银行账户信息总表列表--用于审核--0927
	 * 
	 * @return
	 */
	@RequestMapping(params = "list2")
	public ModelAndView list2(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/bankaccount/bankAccountList2");
	}

	/**
	 * easyui AJAX请求数据
	 * =============================================高级查询走这套逻辑=========================
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BankAccountEntity bankAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankAccountEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccount, request.getParameterMap());
		
		try{
		//自定义追加查询条件
			//查询条件组装器  多字段排序
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("createDate", "desc");
			map.put("updateDate", "desc");
			cq.setOrder(map);
			
			//cq.addOrder("createDate", SortDirection.toEnum("desc"));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankAccountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 *     审核数据展示 0927
	 * @param bankAccount
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid2")
	public void datagrid2(BankAccountEntity bankAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankAccountEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccount, request.getParameterMap());
		try{
		//自定义追加查询条件
			//查询条件组装器  多字段排序
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("createDate", "desc");
			map.put("updateDate", "desc");
			cq.setOrder(map);
			//cq.addOrder("createDate", SortDirection.toEnum("desc"));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankAccountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "datagridForm")
	public void datagridForm(BankAccountEntity bankAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		dataGrid.setSqlbuilder("");
		CriteriaQuery cq = new CriteriaQuery(BankAccountEntity.class, dataGrid);
		//查询条件组装器
		//根据输入的基金代码查询出对应的在线产品
		/*String productCode = bankAccount.getOnlineProduct();
		if(productCode!=null&&!"".equals(productCode)){
			ProductCodeEntity productCodeEntity = productCodeService.findUniqueByProperty(ProductCodeEntity.class, "productcode", productCode);
			//重置bankAccount的在线产品属性
			if(productCodeEntity!=null){
				bankAccount.setOnlineProduct(productCodeEntity.getProductname()==null?"":productCodeEntity.getProductname());
			}
		}*/
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccount);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccount, request.getParameterMap());
		
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankAccountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除银行账户信息总表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BankAccountEntity bankAccount, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bankAccount = systemService.getEntity(BankAccountEntity.class, bankAccount.getId());
		String message = "银行账户信息总表删除成功";
		try{
			bankAccountService.delMain(bankAccount);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息总表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量审核银行账户信息总表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchShenhe")
	@ResponseBody
	public AjaxJson doBatchShenhe(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "银行账户信息总表审核成功";
		try{
			for(String id:ids.split(",")){
				BankAccountEntity bankAccount = systemService.getEntity(BankAccountEntity.class,
				id
				);
				bankAccount.setStatus("Y");//已审核
				bankAccountService.saveOrUpdate(bankAccount);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息总表审核失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	/**
	 * 批量删除银行账户信息总表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "银行账户信息总表删除成功";
		try{
			for(String id:ids.split(",")){
				BankAccountEntity bankAccount = systemService.getEntity(BankAccountEntity.class,
				id
				);
				bankAccountService.delMain(bankAccount);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息总表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加银行账户信息总表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BankAccountEntity bankAccount,BankAccountPage bankAccountPage, HttpServletRequest request) {
		List<AccountRateChangesEntity> accountRateChangesList =  bankAccountPage.getAccountRateChangesList();
		List<RaiseAccountUseEntity> raiseAccountUseList =  bankAccountPage.getRaiseAccountUseList();
		if(raiseAccountUseList==null||raiseAccountUseList.size()==0){
			RaiseAccountUseEntity raiseAccountUseEntity = new RaiseAccountUseEntity();
			raiseAccountUseList = new ArrayList<RaiseAccountUseEntity>();
			raiseAccountUseList.add(raiseAccountUseEntity);
		}
		AjaxJson j = new AjaxJson();
		String message = "添加成功";   
		try{
			bankAccount.setStatus("X");//复核状态：未复核--20180830  N改为X
			//根据账户名字查到账户id，并存入数据库
			if(StringUtils.isNotEmpty(bankAccount.getJingban())) {
				TSBaseUser tSBaseUser = bankAccountService.findUniqueByProperty(TSBaseUser.class, "realName", bankAccount.getJingban());
				bankAccount.setJingban(tSBaseUser.getId());
			}
			bankAccountService.addMain(bankAccount, accountRateChangesList,raiseAccountUseList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "银行账户信息总表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(bankAccount);
		return j;
	}
	/**
	 * 更新银行账户信息总表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BankAccountEntity bankAccount,BankAccountPage bankAccountPage, HttpServletRequest request) {
		List<AccountRateChangesEntity> accountRateChangesList =  bankAccountPage.getAccountRateChangesList();
		List<RaiseAccountUseEntity> raiseAccountUseList =  bankAccountPage.getRaiseAccountUseList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			/**
			 * 附表的前三个字段跟新，根据主表的三个字段进行同步更新
			 */
			//bankAccountService.updateMainBy3Attr(bankAccount, accountRateChangesList,raiseAccountUseList);
			/**
			 * 当用户修改了附表信息时，附表根据修改内容将进行更新
			 */
			bankAccount.setStatus("X");
			/*
			 * 在更新数据之前，需要先将之前的数据保存在临时表-(bank_accountemp)中，
			 * 当审核人 进行审核通过之后，在讲临时表中的数据存入到正式表中(bank_account)	
			 */
			//先查询临时表中有无该数据，如果有的话，进行更新，没有的话进行新增，但是新增的时候用自己的数据
			BankAccountempEntity bankAccountempEntity = bankAccountempService.get(BankAccountempEntity.class, bankAccount.getId());
			//如果存在就进行更新，如果不存在，进行插入但是用自己的id
			if(bankAccountempEntity != null){
				//BankAccountempEntity bankAccountemp = new BankAccountempEntity();
				MyBeanUtils.copyBeanNotNull2Bean(bankAccount,bankAccountempEntity);
				bankAccountempService.saveOrUpdate(bankAccountempEntity);
			}else{
				/*
				 * INSERT INTO bank_accountemp VALUES 
				('2c90e6b265ae37ed0165ae3c35dd0001', '管理员', 'admin', '2018-09-06 17:35:06', null,
						null, null, 'A03', 'A03', ' 22223336666777', 
						'现金增利', '工商银行股份有限公司', '002', '4', '2018-07-18 00:00:00', 
						'-1', ' 4', '2018-07-11 00:00:00', '0', '001377', 
						' 13', '0', '王', null, ' 2', 
						'55', '', null, '中融现金增利', '现', 
						'王', '张元元', '上海', null, '',
						null);*/

				String sql = "INSERT INTO bank_accountemp (id,create_name,create_by,create_date,update_name, "
						+ "update_by,update_date,sys_org_code,sys_company_code,account_number, "
						+ "acount_name,acount_fullname,acount_shortname,acount_type,kaihu_date, "
						+ "	acount_status,acount_interest_rate,interest_rate_date,is_liushui, "
						+ "	online_product,big_zhifu,is_pre_end,contact,contact_type,line_hanghao,cundan, "
						+ "	remarks,todate, seal,acount_abbreve,zhang,jingban,contact_addr,zuoji,mobile_phone,fuhe_status,file) VALUES(?,?,?,?,?,"
						+ "?, ?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,?,?)";
				
				//Map<String,Object> map = new HashMap<String,Object> ();
				//MyBeanUtils.copyBean2Map(map, bankAccount);
				//map.remove("class");
			String file = bankAccount.getFile();
			if(file==null){
				file = "";
			}
			bankAccountempService.executeSql(sql, 
					    bankAccount.getId(),
					    bankAccount.getCreateName(),
					    bankAccount.getCreateBy(),
					    bankAccount.getCreateDate(),
					    bankAccount.getUpdateName(),
						bankAccount.getUpdateBy(),
						bankAccount.getUpdateDate(),
						bankAccount.getSysOrgCode(),
						bankAccount.getSysCompanyCode(),
						bankAccount.getAccountNumber(),
						bankAccount.getAcountName(),
						bankAccount.getAcountFullname(),
						bankAccount.getAcountShortname(),
						bankAccount.getAcountType(),
						bankAccount.getKaihuDate(),
						bankAccount.getAcountStatus(),
						bankAccount.getAcountInterestRate(),
						bankAccount.getInterestRateDate(),
						bankAccount.getIsLiushui(),
						bankAccount.getOnlineProduct(),
						bankAccount.getBigZhifu(),
						bankAccount.getIsPreEnd(),
						bankAccount.getContact(),
						bankAccount.getContactType(),
						bankAccount.getLineHanghao(),
						bankAccount.getCundan(),
						bankAccount.getRemarks(),
						bankAccount.getTodate(),
						bankAccount.getSeal(),
						bankAccount.getAcountAbbreve(),
						bankAccount.getZhang(),
						bankAccount.getJingban(),
						bankAccount.getContactAddr(),
						bankAccount.getZuoJi(),
						bankAccount.getMobilePhone(),
						bankAccount.getStatus(),
						file); //新增的文件参数-18-09-12
			          
				 
			}
			
			//账户修改的时候，正式库的数据不会发生变化，只是把数据存入到临时表中
			//等到审核通过时候再将临时表的数据转存入到正式表
			bankAccountService.updateMain(bankAccount, accountRateChangesList,raiseAccountUseList);
			/**
			 * 进行邮件发送
			 * 当申请人修改账户信息的时候，会发送邮件给审核人,邮件内容如下
					xxx的账户信息进行了修改，
					请登录中融基金智能管理平台系统 http://qy.zrfunds.com.cn/zis/loginController.do?login
					进行查看~
			 */
			
		    PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
		    Properties properties = ftpConfig.getProperties();
		    TSUser user = ResourceUtil.getSessionUser();
		    String  mailBubject = "账户管理系统更新";
		    String  mailBody = user.getRealName()+"的账户信息进行了修改,请登录账户管理系统进行查看,地址如下:<br> http://qy.zrfunds.com.cn/zis";
		    //查询邮件配置的邮箱
		    ReportEmailEntity reportEmail = reportEmailService.findUniqueByProperty(ReportEmailEntity.class, "reportName", "bankAccount");
	        String sendto = reportEmail!=null?reportEmail.getSentTo():"liangqingyang@zrfunds.com.cn";
	        String sendcc = reportEmail!=null?reportEmail.getSentCc():"";
	        logger.info(sendto);
			DataHandlerUtil.sendMail(mailBody,mailBubject,properties,sendto,sendcc);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新银行账户信息总表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 银行账户信息总表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BankAccountEntity bankAccount, HttpServletRequest req) {
		
		if (StringUtil.isNotEmpty(bankAccount.getId())) {
			//开户银行简称
			bankAccount = bankAccountService.getEntity(BankAccountEntity.class, bankAccount.getId());
			req.setAttribute("bankAccountPage", bankAccount);
			
		}
		TSUser user = ResourceUtil.getSessionUser();
		/*if(StringUtil.isNotEmpty(user.getRealName())){
			req.setAttribute("realName", user.getRealName());
		}*/	
		if(user!= null) {
			req.setAttribute("user", user);
		}
		return new ModelAndView("com/jeecg/bankaccount/bankAccount-add");
	}
	
	/**
	 * 银行账户信息总表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BankAccountEntity bankAccount, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankAccount.getId())) {
			bankAccount = bankAccountService.getEntity(BankAccountEntity.class, bankAccount.getId());
			if(StringUtils.isNotEmpty(bankAccount.getJingban())) {
				TSBaseUser tSBaseUser = bankAccountService.findUniqueByProperty(TSBaseUser.class, "id", bankAccount.getJingban());
				if(tSBaseUser!=null&&StringUtils.isNotEmpty(tSBaseUser.getRealName()))
				req.setAttribute("realName", tSBaseUser.getRealName());
				
			}
			req.setAttribute("bankAccountPage", bankAccount);
			
		}
		return new ModelAndView("com/jeecg/bankaccount/bankAccount-update");
	}
	/**
	 * 根据id查询账户利率变化明细表
	 * @param bankAccount
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "accountRateChangesList")
	public ModelAndView accountRateChangesList(BankAccountEntity bankAccount, HttpServletRequest req) {
	
		
		String bankAccountId = bankAccount.getId();
		String foreignKey = req.getParameter("id");
 		//if(bankAccountId!=null&&!"".equals(bankAccountId)){
		if(foreignKey!=null&&!"".equals(foreignKey)){
 			//查询-账户利率变化明细表
 		    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND foreign_key = ?   ORDER BY CREATE_DATE DESC ";
 		    try{
 		    	List<AccountRateChangesEntity> accountRateChangesEntityList = systemService.findHql(hql0,foreignKey);
 		    	req.setAttribute("accountRateChangesList", accountRateChangesEntityList);
 			}catch(Exception e){
 				logger.info(e.getMessage());
 			}
 		}
		
		return new ModelAndView("com/jeecg/accountratechanges/accountRateChangesList");
	}
	
	/**
	 * 根据id-加载明细列表[募集账户使用情况明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "raiseAccountUseList")
	public ModelAndView raiseAccountUseList(BankAccountEntity bankAccount, HttpServletRequest req) {
	
		String foreignKey = req.getParameter("id");
		//查询-募集账户使用情况明细
	    String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND foreign_key = ? ORDER BY CREATE_DATE DESC ";
	    try{
	    	List<RaiseAccountUseEntity> raiseAccountUseEntityList = systemService.findHql(hql1,foreignKey);
			req.setAttribute("raiseAccountUseList", raiseAccountUseEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/raiseaccountuse/raiseAccountUseList");
	}
	
	/**
	 * 根据id-审核意见历史记录[审核意见情况明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "bankCommentList")
	public ModelAndView bankCommentList(BankAccountEntity bankAccount, HttpServletRequest req) {
	
		String foreignKey = req.getParameter("id");
	    String hql = "from BankCommentEntity where 1 = 1 AND foreign_key = ? ORDER BY time DESC ";
	    try{
	    	List<BankCommentEntity> bankCommentList = systemService.findHql(hql,foreignKey);
			req.setAttribute("bankCommentList", bankCommentList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/bankcomment/bankCommentList2");
	}
	
	/**
	 * 加载明细列表[账户利率变化明细表]
	 * ===========================根据bankAccount的三个字段去查询数据库
	 * @return
	 */
	@RequestMapping(params = "accountRateChangesListBy3Attr")
	public ModelAndView accountRateChangesListBy3Attr(BankAccountEntity bankAccount, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		/*Object dETAIL_NUMBER0 = bankAccount.getDETAIL_NUMBER();
		Object dETAIL_NAME0 = bankAccount.getDETAIL_NAME();
		Object dETAIL_FULLNAME0 = bankAccount.getDETAIL_FULLNAME();*/
		
		Object dETAIL_NUMBER0 = null;
		Object dETAIL_NAME0 = null;
		Object dETAIL_FULLNAME0 = null;
		String bankAccountId = bankAccount.getId();
 		if(bankAccountId!=null&&!"".equals(bankAccountId)){
			BankAccountEntity bankAccount2 = bankAccountService.get(BankAccountEntity.class, bankAccountId);
			
			dETAIL_NUMBER0 = bankAccount2.getAccountNumber();
			dETAIL_NAME0 = bankAccount2.getAcountName();
			dETAIL_FULLNAME0 = bankAccount2.getAcountFullname();
		}else{
			dETAIL_NUMBER0 = bankAccount.getAccountNumber();
			dETAIL_NAME0 = bankAccount.getAcountName();
			dETAIL_FULLNAME0 = bankAccount.getAcountFullname();
		}
		
		//===================================================================================
		//查询-账户利率变化明细表
	    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ORDER BY CREATE_DATE DESC ";
	    try{
	    	List<AccountRateChangesEntity> accountRateChangesEntityList = systemService.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
			req.setAttribute("accountRateChangesList", accountRateChangesEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/accountratechanges/accountRateChangesList");
	}
	/**
	 * 加载明细列表[募集账户使用情况明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "raiseAccountUseListBy3Attr")
	public ModelAndView raiseAccountUseListBy3Attr(BankAccountEntity bankAccount, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object yINHANG_ACCOUNT1 = null;
		Object yINHANG_NAME1 = null;
		Object yINHANG_NAMEFULL1 = null;
		String bankAccountId = bankAccount.getId();
		if(bankAccountId!=null&&!"".equals(bankAccountId)){
			BankAccountEntity bankAccount2 = bankAccountService.get(BankAccountEntity.class, bankAccountId);
			
			yINHANG_ACCOUNT1 = bankAccount2.getAccountNumber();
			yINHANG_NAME1 = bankAccount2.getAcountName();
			yINHANG_NAMEFULL1 = bankAccount2.getAcountFullname();
		}else{
			yINHANG_ACCOUNT1 = bankAccount.getAccountNumber();
			yINHANG_NAME1 = bankAccount.getAcountName();
			yINHANG_NAMEFULL1 = bankAccount.getAcountFullname();
		}
		
		//===================================================================================
		//查询-募集账户使用情况明细
	    String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ORDER BY CREATE_DATE DESC ";
	    try{
	    	List<RaiseAccountUseEntity> raiseAccountUseEntityList = systemService.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
			req.setAttribute("raiseAccountUseList", raiseAccountUseEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/raiseaccountuse/raiseAccountUseList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(BankAccountEntity bankAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(BankAccountEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankAccount);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<BankAccountEntity> list=this.bankAccountService.getListByCriteriaQuery(cq, false);
    	List<BankAccountPage> pageList=new ArrayList<BankAccountPage>();
        if(list!=null&&list.size()>0){
        	for(BankAccountEntity entity:list){
        		try{
        			//导出的字典项显示
        			
        			//开户银行简称
        			String shortname =  entity.getAcountShortname();
        			BankCodeEntity BankCode= BankCodeService.findUniqueByProperty(BankCodeEntity.class, "bankcode", shortname);
        			entity.setAcountShortname(BankCode!=null?BankCode.getBankname():shortname);
        			String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg"
        					+ " on t.typegroupid=tg.id where tg.typegroupname  = ?  and t.typecode = ? ";
        			//账户类型
        			Map<String,Object> AcountType = bankAccountempService.findOneForJdbc(sql, "账户类型",entity.getAcountType());
        			entity.setAcountType(AcountType!=null?(String) AcountType.get("typename"):null);
        			//账户状态
        			Map<String,Object> AcountStatus = bankAccountempService.findOneForJdbc(sql, "账户状态",entity.getAcountStatus());
        			entity.setAcountStatus(AcountStatus!=null?(String) AcountStatus.get("typename"):"募集在用");
        			//是否流水户
        			String  Liushui = entity.getIsLiushui();
        			entity.setIsLiushui("1".equals(Liushui)?"是":"否");
        			//是否可以提前结息
        			String  isPreEnd = entity.getIsPreEnd();
        			entity.setIsPreEnd("1".equals(isPreEnd)?"是":"否");
        			
        		BankAccountPage page=new BankAccountPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    /*Object dETAIL_NUMBER0 = entity.getDETAIL_NUMBER();
           		    Object dETAIL_NAME0 = entity.getDETAIL_NAME();
           		    Object dETAIL_FULLNAME0 = entity.getDETAIL_FULLNAME();*/
           		    
           		 Object dETAIL_NUMBER0 = entity.getAccountNumber();
         		 Object dETAIL_NAME0 = entity.getAcountName();
         		 Object dETAIL_FULLNAME0 = entity.getAcountFullname();
         		
				    //String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
         		 String hql0 = "from AccountRateChangesEntity where 1 = 1 AND foreignKey = ? ";
      	         
         		 //List<AccountRateChangesEntity> accountRateChangesEntityList = systemService.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
         		List<AccountRateChangesEntity> accountRateChangesEntityList = systemService.findHql(hql0,entity.getId());
            	
         		 page.setAccountRateChangesList(accountRateChangesEntityList);
           		    /*Object yINHANG_ACCOUNT1 = entity.getYINHANG_ACCOUNT();
           		    Object yINHANG_NAME1 = entity.getYINHANG_NAME();
           		    Object yINHANG_NAMEFULL1 = entity.getYINHANG_NAMEFULL();*/
           		    
	           		 Object yINHANG_ACCOUNT1 = entity.getAccountNumber();
	         		 Object yINHANG_NAME1 = entity.getAcountName();
	         		 Object yINHANG_NAMEFULL1 = entity.getAcountFullname();
         		 
				    /*String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
        	        List<RaiseAccountUseEntity> raiseAccountUseEntityList = systemService.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
            		*/
	         		String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND foreignKey = ? ";
        	        List<RaiseAccountUseEntity> raiseAccountUseEntityList = systemService.findHql(hql1,entity.getId());
        	    	/**保存-募集账户使用情况明细*/
        			for(RaiseAccountUseEntity raiseAccountUse:raiseAccountUseEntityList){
        				
        				raiseAccountUse.setForeignKey(bankAccount.getId());
        				String jiexiStatus = raiseAccountUse.getJiexiStatus();
        				
        				switch(jiexiStatus){
        				  case "-1":
        					  jiexiStatus = "未结息";
        					  break;
        				  case "0":
        					  jiexiStatus = "部分结息";
        					  break;
        				  case "1":
        					  jiexiStatus = "已结息";
        					  break;
        				  default:
        					  jiexiStatus = "未结息";//找不到匹配项的话，指定为未结息
        					  break;
        				}
        				raiseAccountUse.setJiexiStatus(jiexiStatus);
        			}
        			
	         		 page.setRaiseAccountUseList(raiseAccountUseEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"银行账户信息总表");
        map.put(NormalExcelConstants.CLASS,BankAccountPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("银行账户信息总表列表", "导出人:Jeecg",
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
			params.setTitleRows(2);//表格标题行数,默认0
			params.setHeadRows(2);//表头行数,默认1
			params.setNeedSave(true);
			try {
				List<BankAccountPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), BankAccountPage.class, params);
				BankAccountEntity entity1=null;
				for (BankAccountPage page : list) {
					entity1=new BankAccountEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            //bankAccountService.addMain(entity1, page.getAccountRateChangesList(),page.getRaiseAccountUseList());
		            
		            bankAccountService.addMainForImportExcel(entity1, page.getAccountRateChangesList(),page.getRaiseAccountUseList());
		            
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
		map.put(NormalExcelConstants.FILE_NAME,"银行账户信息总表");
		map.put(NormalExcelConstants.CLASS,BankAccountPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("银行账户信息总表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "bankAccountController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="银行账户信息总表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BankAccountPage>> list() {
		List<BankAccountEntity> list= bankAccountService.getList(BankAccountEntity.class);
    	List<BankAccountPage> pageList=new ArrayList<BankAccountPage>();
        if(list!=null&&list.size()>0){
        	for(BankAccountEntity entity:list){
        		try{
        			BankAccountPage page=new BankAccountPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					/*Object dETAIL_NUMBER0 = entity.getDETAIL_NUMBER();
					Object dETAIL_NAME0 = entity.getDETAIL_NAME();
					Object dETAIL_FULLNAME0 = entity.getDETAIL_FULLNAME();
					*/
					 Object dETAIL_NUMBER0 = entity.getAccountNumber();
	         		 Object dETAIL_NAME0 = entity.getAcountName();
	         		 Object dETAIL_FULLNAME0 = entity.getAcountFullname();
	         		 
					/*Object yINHANG_ACCOUNT1 = entity.getYINHANG_ACCOUNT();
					Object yINHANG_NAME1 = entity.getYINHANG_NAME();
					Object yINHANG_NAMEFULL1 = entity.getYINHANG_NAMEFULL();*/
					
					 Object yINHANG_ACCOUNT1 = entity.getAccountNumber();
	         		 Object yINHANG_NAME1 = entity.getAcountName();
	         		 Object yINHANG_NAMEFULL1 = entity.getAcountFullname();
	         		 
				     String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
	    			List<AccountRateChangesEntity> accountRateChangesOldList = this.bankAccountService.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
            		page.setAccountRateChangesList(accountRateChangesOldList);
				     String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
	    			List<RaiseAccountUseEntity> raiseAccountUseOldList = this.bankAccountService.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
            		page.setRaiseAccountUseList(raiseAccountUseOldList);
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
	@ApiOperation(value="根据ID获取银行账户信息总表信息",notes="根据ID获取银行账户信息总表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BankAccountEntity task = bankAccountService.get(BankAccountEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取银行账户信息总表信息为空");
		}
		BankAccountPage page = new BankAccountPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object dETAIL_NUMBER0 = task.getAccountNumber();
				Object dETAIL_NAME0 = task.getAcountName();
				Object dETAIL_FULLNAME0 = task.getAcountFullname();
         		 
				Object yINHANG_ACCOUNT1 = task.getAccountNumber();
				Object yINHANG_NAME1 = task.getAcountName();
				Object yINHANG_NAMEFULL1 = task.getAcountFullname();
		    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
			List<AccountRateChangesEntity> accountRateChangesOldList = this.bankAccountService.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
    		page.setAccountRateChangesList(accountRateChangesOldList);
		    String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
			List<RaiseAccountUseEntity> raiseAccountUseOldList = this.bankAccountService.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
    		page.setRaiseAccountUseList(raiseAccountUseOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建银行账户信息总表")
	public ResponseMessage<?> create(@ApiParam(name="银行账户信息总表对象")@RequestBody BankAccountPage bankAccountPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankAccountPage>> failures = validator.validate(bankAccountPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<AccountRateChangesEntity> accountRateChangesList =  bankAccountPage.getAccountRateChangesList();
		List<RaiseAccountUseEntity> raiseAccountUseList =  bankAccountPage.getRaiseAccountUseList();
		
		BankAccountEntity bankAccount = new BankAccountEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(bankAccountPage,bankAccount);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存银行账户信息总表失败");
        }
		bankAccountService.addMain(bankAccount, accountRateChangesList,raiseAccountUseList);

		return Result.success(bankAccount);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新银行账户信息总表",notes="更新银行账户信息总表")
	public ResponseMessage<?> update(@RequestBody BankAccountPage bankAccountPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BankAccountPage>> failures = validator.validate(bankAccountPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<AccountRateChangesEntity> accountRateChangesList =  bankAccountPage.getAccountRateChangesList();
		List<RaiseAccountUseEntity> raiseAccountUseList =  bankAccountPage.getRaiseAccountUseList();
		
		BankAccountEntity bankAccount = new BankAccountEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(bankAccountPage,bankAccount);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("银行账户信息总表更新失败");
        }
		bankAccountService.updateMain(bankAccount, accountRateChangesList,raiseAccountUseList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除银行账户信息总表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			BankAccountEntity bankAccount = bankAccountService.get(BankAccountEntity.class, id);
			bankAccountService.delMain(bankAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("银行账户信息总表删除失败");
		}

		return Result.success();
	}
}

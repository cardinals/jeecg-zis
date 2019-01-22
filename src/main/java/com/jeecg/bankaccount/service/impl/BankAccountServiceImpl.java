package com.jeecg.bankaccount.service.impl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.accountratechanges.entity.AccountRateChangesEntity;
import com.jeecg.accountratechangestemp.entity.AccountRateChangestempEntity;
import com.jeecg.bankaccount.controller.BankAccountController;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.bankaccount.service.BankAccountServiceI;
import com.jeecg.bankaccountemp.entity.BankAccountempEntity;
import com.jeecg.bankcode.entity.BankCodeEntity;
import com.jeecg.productcode.entity.ProductCodeEntity;
import com.jeecg.raiseaccountuse.entity.RaiseAccountUseEntity;
import com.jeecg.raiseaccountusetemp.entity.RaiseAccountUsetempEntity;
import com.sun.star.accessibility.AccessibleRelationType;


@Service("bankAccountService")
@Transactional
public class BankAccountServiceImpl extends CommonServiceImpl implements BankAccountServiceI {
	private static final Logger logger = Logger.getLogger(BankAccountController.class);
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BankAccountEntity)entity);
 	}
	
	public void addMain(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList){
			//保存主信息
			this.save(bankAccount);
			BankAccountEntity bankAccount2= this.get(BankAccountEntity.class, bankAccount.getId());
			bankAccount2.setUpdateDate(bankAccount.getCreateDate());
			/**
			 * 保存2张-附表信息
			 */
			String  acountInterestRate = bankAccount.getAcountInterestRate();
			String  onlineProduct =  bankAccount.getOnlineProduct();
			if(!"".equals(acountInterestRate)){
				/**保存-账户利率变化明细表*/
				for(AccountRateChangesEntity accountRateChanges:accountRateChangesList){
					//外键设置
					accountRateChanges.setDetailNumber(bankAccount.getAccountNumber());
					//外键设置
					accountRateChanges.setDetailName(bankAccount.getAcountName());
					//外键设置
					accountRateChanges.setDetailFullname(bankAccount.getAcountFullname());
					//新增外键-0716
					accountRateChanges.setForeignKey(bankAccount.getId());
					
					//个人新增
					if(bankAccount.getInterestRateDate()!=null){
						accountRateChanges.setStartDate(bankAccount.getInterestRateDate());
						//获得2099-12-31 的Date对象
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date endDate;
						try {
							endDate = dateFormat.parse("2099-12-31");
							accountRateChanges.setEndDate(endDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
					}
					//银行账户利率
					accountRateChanges.setDetailRate(bankAccount.getAcountInterestRate()==null?"":bankAccount.getAcountInterestRate());
					this.save(accountRateChanges);
				}
			}
			if(!"".equals(onlineProduct)){
				/**保存-账户使用情况明细*/
				for(RaiseAccountUseEntity raiseAccountUse:raiseAccountUseList){
					//外键设置
					raiseAccountUse.setYinhangAccount(bankAccount.getAccountNumber());
					//外键设置
					raiseAccountUse.setYinhangName(bankAccount.getAcountName());
					//外键设置
					raiseAccountUse.setYinhangNamefull(bankAccount.getAcountFullname());
					
					raiseAccountUse.setForeignKey(bankAccount.getId());
					//初始状态未结息
					raiseAccountUse.setJiexiStatus("-1");
					//个人新增
					raiseAccountUse.setPreProject(bankAccount.getOnlineProduct()==null?"":bankAccount.getOnlineProduct());
					this.save(raiseAccountUse);
					
				}
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(bankAccount);
	}

	public void addMainForImportExcel(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList){
			
		    String acountType = bankAccount.getAcountType();
		    switch(acountType){
			  case "托管账户":
				  acountType = "1";
				  break;
			  case "定存账户":
				  acountType = "2";
				  break;
			  case "直销清算账户":
				  acountType = "3";
				  break;
			  case "TA清算账户":
				  acountType = "4";
				  break;
			  case "募集账户":
				  acountType = "5";
				  break;
			  case "公司自营账户":
				  acountType = "6";
				  break;
			  case "证券户":
				  acountType = "7";
				  break;
			  case "期货户":
				  acountType = "8";
				  break;
			  case "中债账户":
				  acountType = "9";
				  break;
			  case "上清所账户":
				  acountType = "10";
				  break;
			  case "上海股东卡":
				  acountType = "11";
				  break;
			  case "深圳股东卡":
				  acountType = "12";
				  break;
			  case "期货保证金账户":
				  acountType = "13";
				  break;
			  case "中债资金账户":
				  acountType = "14";
				  break;
			  case "上清所资金账户":
				  acountType = "15";
				  break;
			  case "期货资金账户":
				  acountType = "16";          
				  break;
			  default:
				       ;//找不到匹配项的话，不改变数据
				  break;
			}
		    bankAccount.setStatus("X");//复核状态：待审核
		    bankAccount.setAcountType(acountType);
		    String acountStatus =  bankAccount.getAcountStatus();
		    switch(acountStatus){
			  case "其他":
				  acountStatus = "-1";
				  break;
			  case "已注销":
				  acountStatus = "0";
				  break;
			  case "正常":
				  acountStatus = "1";
				  break;
			  case "开户中":
				  acountStatus = "3";
				  break;
			  case "募集在用":
				  acountStatus = "2";
				  break;
			  case "销户中":
				  acountStatus = "4";
				  break;
			  default:
				       ;//找不到匹配项的话，不做操作
				  break;
			}
		    bankAccount.setAcountStatus(acountStatus);
		    /**
		     * 获取开户银行简称代码 存入数据库
		     */
		    String acountShortname = bankAccount.getAcountShortname();
		    //BankCodeEntity bankCodeEntity = this.findUniqueByProperty(BankCodeEntity.class, "bankname", acountShortname);
		    List<BankCodeEntity> bankCodeEntityList =  this.findByProperty(BankCodeEntity.class, "bankname", acountShortname);
		    if(bankCodeEntityList!=null&&bankCodeEntityList.size()>0){
		    	bankAccount.setAcountShortname(bankCodeEntityList.get(0).getBankcode());
		    }
		    /**
		     * 获取在用产品，将在用产品的代码存入数据库
		     */
		    String onlineProduct = bankAccount.getOnlineProduct();
		    //ProductCodeEntity productCodeEntity = this.findUniqueByProperty(ProductCodeEntity.class, "productname", onlineProduct);
		    List<ProductCodeEntity> productCodeEntity =  this.findByProperty(ProductCodeEntity.class, "productname", onlineProduct);
		    if(productCodeEntity!=null&&productCodeEntity.size()>0){
		    	
		    	bankAccount.setOnlineProduct(productCodeEntity.get(0).getProductcode());
		    }
		    String isLiushui = bankAccount.getIsLiushui();
		    if(StringUtils.isNotEmpty(isLiushui)){
		    	bankAccount.setIsLiushui("是".equals(isLiushui)==true?"1":"0");
		    }
		    String isPreEnd = bankAccount.getIsPreEnd();
		    if(StringUtils.isNotEmpty(isPreEnd)){
		    	bankAccount.setIsPreEnd("是".equals(isPreEnd)==true?"1":"0");
		    }
		    //根据账户名字查到账户id，并存入数据库--0921
			if(StringUtils.isNotEmpty(bankAccount.getJingban())) {
				TSBaseUser tSBaseUser = this.findUniqueByProperty(TSBaseUser.class, "realName", bankAccount.getJingban());
				if(tSBaseUser != null) {
					bankAccount.setJingban(tSBaseUser.getId());
				}
			}
		    //保存主信息
			this.save(bankAccount);
		
			/**保存-账户利率变化明细表*/
			for(AccountRateChangesEntity accountRateChanges:accountRateChangesList){
				//String xxx = accountRateChanges.getDetailRate();
				if(accountRateChanges.getDetailRate()==null||"".equals(accountRateChanges.getDetailRate()))
					continue;
				//外键设置
				accountRateChanges.setForeignKey(bankAccount.getId());
				this.save(accountRateChanges);
			}
			/**保存-募集账户使用情况明细*/
			for(RaiseAccountUseEntity raiseAccountUse:raiseAccountUseList){
				/*//外键设置
				raiseAccountUse.setYinhangAccount(bankAccount.getYINHANG_ACCOUNT());
				//外键设置
				raiseAccountUse.setYinhangName(bankAccount.getYINHANG_NAME());
				//外键设置
				raiseAccountUse.setYinhangNamefull(bankAccount.getYINHANG_NAMEFULL());*/
				//外键设置
				raiseAccountUse.setForeignKey(bankAccount.getId());
				String jiexiStatus = raiseAccountUse.getJiexiStatus();
				
				switch(jiexiStatus){
				  case "未结息":
					  jiexiStatus = "-1";
					  break;
				  case "部分结息":
					  jiexiStatus = "0";
					  break;
				  case "已结息":
					  jiexiStatus = "1";
					  break;
				  default:
					  jiexiStatus = "-1";//找不到匹配项的话，指定为未结息
					  break;
				}
				raiseAccountUse.setJiexiStatus(jiexiStatus);
				this.save(raiseAccountUse);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(bankAccount);
	}
	
	public void updateMainBy3Attr(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList) {
		
		String bankAccountId = bankAccount.getId();
		BankAccountEntity bankAccount2= this.get(BankAccountEntity.class, bankAccountId);
		BankAccountEntity newBankAccount = new BankAccountEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bankAccount2, newBankAccount);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//保存主表信息
		if(StringUtil.isNotEmpty(bankAccount.getId())){
			try {
				BankAccountEntity temp = findUniqueByProperty(BankAccountEntity.class, "id", bankAccount.getId());
				MyBeanUtils.copyBeanNotNull2Bean(bankAccount, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(bankAccount);
		}
		//===================================================================================
		
		/**
		 * 数据库中的字段值
		 */
		Object dETAIL_NUMBER0 = newBankAccount.getAccountNumber();
		Object dETAIL_NAME0 = newBankAccount.getAcountName();
		Object dETAIL_FULLNAME0 = newBankAccount.getAcountFullname();
		Object yINHANG_ACCOUNT1 = newBankAccount.getAccountNumber();
		Object yINHANG_NAME1 = newBankAccount.getAcountName();
		Object yINHANG_NAMEFULL1 = newBankAccount.getAcountFullname();
		//===================================================================================

		//1.查询出数据库的明细数据-账户利率变化明细表
		
			   //暂时注释掉-根据三个字段查询 账户利率变化明细表 0716
			    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
			    List<AccountRateChangesEntity> accountRateChangesOldList = this.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
				 
		 //String hql0 = "from AccountRateChangesEntity where 1 = 1 AND foreign_key = ?   ORDER BY CREATE_DATE DESC ";
		 // List<AccountRateChangesEntity> accountRateChangesOldList = this.findHql(hql0,bankAccountId);
		    	
		//2.筛选更新明细数据-账户利率变化明细表
				if(accountRateChangesList!=null&&accountRateChangesList.size()>0){
				for(AccountRateChangesEntity oldE:accountRateChangesOldList){
					boolean isUpdate = false;
						for(AccountRateChangesEntity sendE:accountRateChangesList){
							//需要更新的明细数据-账户利率变化明细表
							if(oldE.getId().equals(sendE.getId())){
				    			try {
				    				/**
				    				 * 根据主表三个字段-有一个字段修改，附表的字段就会更新，暂时注释掉
				    				 */
				    				sendE.setDetailNumber(bankAccount.getAccountNumber());
				    				sendE.setDetailName(bankAccount.getAcountName());
				    				sendE.setDetailFullname(bankAccount.getAcountFullname());
									
									MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
									this.saveOrUpdate(oldE);
								} catch (Exception e) {
									e.printStackTrace();
									throw new BusinessException(e.getMessage());
								}
								isUpdate= true;
				    			break;
				    		}
				    	}
			    		if(!isUpdate){
				    		//如果数据库存在的明细，前台没有传递过来则是删除-账户利率变化明细表
				    		//super.delete(oldE);
			    		}
			    		
					}
					
					//3.持久化新增的数据-账户利率变化明细表
					/*for(AccountRateChangesEntity accountRateChanges:accountRateChangesList){
						if(oConvertUtils.isEmpty(accountRateChanges.getId())){
							//外键设置
							accountRateChanges.setDetailNumber(bankAccount.getAccountNumber());
							accountRateChanges.setDetailName(bankAccount.getAcountName());
							accountRateChanges.setDetailFullname(bankAccount.getAcountFullname());
							this.save(accountRateChanges);
						}
					}*/
				}
		//===================================================================================
		//1.查询出数据库的明细数据-募集账户使用情况明细
	    String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
	    List<RaiseAccountUseEntity> raiseAccountUseOldList = this.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
		//2.筛选更新明细数据-募集账户使用情况明细
		if(raiseAccountUseList!=null&&raiseAccountUseList.size()>0){
	    for(RaiseAccountUseEntity oldE:raiseAccountUseOldList){
			boolean isUpdate = false;
				for(RaiseAccountUseEntity sendE:raiseAccountUseList){
					//需要更新的明细数据-募集账户使用情况明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
		    				sendE.setYinhangAccount(bankAccount.getAccountNumber());
		    				sendE.setYinhangName(bankAccount.getAcountName());
		    				sendE.setYinhangNamefull(bankAccount.getAcountFullname());
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-募集账户使用情况明细
		    		//super.delete(oldE);
	    		}
	    		
			}
			
		}
		//执行更新操作配置的sql增强
 		//this.doUpdateSql(bankAccount);//注释掉之前的
		
		//当-账户利率-发生变化的时候，向账户利率变化明细表-account_rate_changes 插入数据
		
		String newAccountRate= bankAccount.getAcountInterestRate();//当前录入账户利率
		String oldAccountRate= newBankAccount.getAcountInterestRate();//之前的账户利率
		if(!"".equals(newAccountRate)&&"".equals(oldAccountRate)){
			AccountRateChangesEntity accountRateChanges = new AccountRateChangesEntity();
			accountRateChanges.setDetailNumber(bankAccount.getAccountNumber());
			//外键设置
			accountRateChanges.setDetailName(bankAccount.getAcountName());
			//外键设置
			accountRateChanges.setDetailFullname(bankAccount.getAcountFullname());
			//个人新增
			if(bankAccount.getInterestRateDate()!=null){
				accountRateChanges.setStartDate(bankAccount.getInterestRateDate());
			}
			accountRateChanges.setDetailRate(bankAccount.getAcountInterestRate()==null?"":bankAccount.getAcountInterestRate());
			this.save(accountRateChanges);
		}
		if(!"".equals(oldAccountRate)&&newAccountRate!=null&&!"".equals(newAccountRate)){
			if(!newAccountRate.equals(oldAccountRate)){
				//将第一次录入的记录的 结束日期置为当前日期
				//修改：第一次录入的结束日期为当前的利率启用日期-0714
				final Timestamp END_DATE = new Timestamp(bankAccount.getInterestRateDate().getTime());
				/**
				 * where END_DATE IS NULL 代表了当前只有一条数据，且结束日期为空；
				 */
				String hql3 = "from AccountRateChangesEntity where 1 = 1 AND END_DATE IS NULL AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
				  List<AccountRateChangesEntity> accountRateChangesOldList2 = this.findHql(hql3,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
				 if(accountRateChangesOldList2!=null&&accountRateChangesOldList2.size()>0){
					 //java.util.Date date = new java.util.Date();          // 获取一个Date对象
					  //final Timestamp END_DATE = new Timestamp(date.getTime());
					  accountRateChangesOldList2.get(0).setEndDate(END_DATE);//new Date()
				 }else{
					//执行更新操作配置的sql增强
						//this.doUpdateSql(newBankAccount);
						
						this.toAccountRate(newBankAccount,bankAccount,1);//账户利率变化明细表
				 }
				
			}
			
		}
 		//当-在用产品-发生变化的时候，向募集账户使用情况明细--raise_account_use插入数据
		//...
		
	}

	
	public void delMain(BankAccountEntity bankAccount) {
		//删除主表信息
		this.delete(bankAccount);
		//===================================================================================
		//获取参数
		Object dETAIL_NUMBER0 = bankAccount.getAccountNumber();
		Object dETAIL_NAME0 = bankAccount.getAcountName();
		Object dETAIL_FULLNAME0 = bankAccount.getAcountFullname();
		Object yINHANG_ACCOUNT1 = bankAccount.getAccountNumber();
		Object yINHANG_NAME1 = bankAccount.getAcountName();
		Object yINHANG_NAMEFULL1 = bankAccount.getAcountFullname();
		//===================================================================================
		//删除-账户利率变化明细表
	    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
	    List<AccountRateChangesEntity> accountRateChangesOldList = this.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
		this.deleteAllEntitie(accountRateChangesOldList);
		//===================================================================================
		//删除-募集账户使用情况明细
	    String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
	    List<RaiseAccountUseEntity> raiseAccountUseOldList = this.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
		this.deleteAllEntitie(raiseAccountUseOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BankAccountEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BankAccountEntity preAccountMain){

	 	//1,向表 募集账户使用情况明细，插入更改之前的数据
	    //id用UUID即可
	 	
	 	final String  YINHANG_ACCOUNT =  preAccountMain.getAccountNumber()==null?"":preAccountMain.getAccountNumber();
	 	final String  YINHANG_NAME = preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();
	 	final String  YINHANG_NAMEFULL = preAccountMain.getAcountFullname()==null?"":preAccountMain.getAcountFullname();
	 	final String  pre_project = preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();//========待议
	 	final String  jiexi_status = new StringBuilder("-1").toString();//-1代表未结息
	 	//final String  foreign_key = preAccountMain.getId(); //外键
	 	final String PRI_ID= UUID.randomUUID().toString().replace("-", "");
	 	
	 	String sql_raise_account  = new StringBuilder("insert raise_account_use(id,yinhang_account,yinhang_name,yinhang_namefull,pre_project,jiexi_status)	values(?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_raise_account,PRI_ID,YINHANG_ACCOUNT,YINHANG_NAME,YINHANG_NAMEFULL,pre_project,jiexi_status);
	 	
	 	//2,向表 账户利率变化明细表 ，插入更改之前的数据
	 	final String  DETAIL_NUMBER =  preAccountMain.getAccountNumber()==null?"":preAccountMain.getAccountNumber();
	 	final String  DETAIL_NAME =  preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();
	 	final String  DETAIL_FULLNAME =  preAccountMain.getAcountFullname()==null?"":preAccountMain.getAcountFullname();
	 	final String  DETAIL_RATE =  preAccountMain.getAcountInterestRate()==null?"":preAccountMain.getAcountInterestRate();
	 	//开始时间
	 	final Date  START_DATE_TEMP =  preAccountMain.getKaihuDate()==null?new Date():preAccountMain.getKaihuDate();
	 	final Timestamp START_DATE = new Timestamp(START_DATE_TEMP.getTime());
	 	//结束时间
	 	java.util.Date date = new java.util.Date();          // 获取一个Date对象
	 	final Timestamp END_DATE = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
	 	//final String  ACCOUNT_MAIN_ID = preAccountMain.getId(); //外键
	 	final String ID= UUID.randomUUID().toString().replace("-", "");
	 	
	 	String sql_detail_account  = new StringBuilder("insert account_rate_changes(id,detail_number,detail_name,detail_fullname,detail_rate,start_date,end_date)values(?,?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_detail_account,ID,DETAIL_NUMBER,DETAIL_NAME,DETAIL_FULLNAME,DETAIL_RATE,START_DATE,END_DATE); 		
	 	return true;
 	}
 	
 	public boolean toRaiseAccount(BankAccountEntity preAccountMain,BankAccountEntity bankAccount){

	 	//向表 募集账户使用情况明细，插入更改之前的数据
	 	final String  YINHANG_ACCOUNT =  preAccountMain.getAccountNumber()==null?"":preAccountMain.getAccountNumber();
	 	final String  YINHANG_NAME = preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();
	 	final String  YINHANG_NAMEFULL = preAccountMain.getAcountFullname()==null?"":preAccountMain.getAcountFullname();
	 	final String  pre_project = preAccountMain.getOnlineProduct()==null?"":preAccountMain.getOnlineProduct();//在用产品
	 	final String  jiexi_status = new StringBuilder("-1").toString();//-1代表未结息
	 	String foreign_key = bankAccount.getId();
	 	//结束时间
	 	java.util.Date date = new java.util.Date();          // 获取一个Date对象
	 	final Timestamp CREATE_DATE = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
	 	
	 	final String PRI_ID= UUID.randomUUID().toString().replace("-", "");
	 	String sql_raise_account  = new StringBuilder("insert raise_account_use(id,yinhang_account,yinhang_name,yinhang_namefull,pre_project,jiexi_status,create_date,foreign_key)	values(?,?,?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_raise_account,PRI_ID,YINHANG_ACCOUNT,YINHANG_NAME,YINHANG_NAMEFULL,pre_project,jiexi_status,CREATE_DATE,foreign_key);
	 	return true;
 	}
 	/**
 	 * -----------------------------------------之前是存入到正式表，现在存入到临时表------------------------------
 	 * @param preAccountMain
 	 * @param bankAccount
 	 * @param flag
 	 * @return
 	 */
 	public boolean toAccountRate(BankAccountEntity preAccountMain,BankAccountEntity bankAccount,int flag){
 		//向表 账户利率变化明细表 ，插入更改之前的数据
	 	final String  DETAIL_NUMBER =  preAccountMain.getAccountNumber()==null?"":preAccountMain.getAccountNumber();
	 	final String  DETAIL_NAME =  preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();
	 	final String  DETAIL_FULLNAME =  preAccountMain.getAcountFullname()==null?"":preAccountMain.getAcountFullname();
	 	final String  DETAIL_RATE =  preAccountMain.getAcountInterestRate()==null?"":preAccountMain.getAcountInterestRate();
	 	final Date    START_TIME = preAccountMain.getInterestRateDate();//数据库中账户利率启用时间
	 	String foreign_key = bankAccount.getId();
	 	//开始时间--需要改一下
	 	//final Date  START_DATE_TEMP =  preAccountMain.getKaihuDate()==null?new Date():preAccountMain.getKaihuDate();
	 	 Timestamp START_DATE = new Timestamp(new Date().getTime());
	 	//开始时间是 行一条记录的结束时间，需要先查一下
	 	//...
		//查询-账户利率变化明细表
	    //String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ORDER BY END_DATE DESC ";
	 	String hql0 = "from AccountRateChangesEntity where 1 = 1 AND  foreign_key = ? ORDER BY END_DATE DESC ";
	    AccountRateChangesEntity  accountRateChange = null;
	    try{
	    	List<AccountRateChangesEntity> accountRateChangesEntityList = this.findHql(hql0,preAccountMain.getId());
	    	  accountRateChange = accountRateChangesEntityList.get(0);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	    //历史账户利率有数据，并且有上一次的账户利率启用日期
	 	if(accountRateChange!=null&&START_TIME!=null){
	 		//START_DATE = new Timestamp(accountRateChange.getEndDate().getTime());
	 		//*修改 开始时间是上一次的利率开启时间
	 		START_DATE = new Timestamp(START_TIME.getTime());
	 		
	 	}
	 	//结束时间
	 	//java.util.Date date = new java.util.Date();          // 获取一个Date对象
	 	//final Timestamp END_DATE = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
	 	
	 	//修改：结束日期为当前界面录入的利率启用日期
	 	Timestamp END_DATE = new Timestamp(bankAccount.getInterestRateDate().getTime()); 
	 	if(flag==0){
	 		//获得2099-12-31 的Date对象
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				START_DATE = new Timestamp(START_TIME.getTime());//开始日期设置为
				END_DATE = new Timestamp(bankAccount.getInterestRateDate().getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 	final String ID= UUID.randomUUID().toString().replace("-", "");
	 	//String sql_detail_account  = new StringBuilder("insert account_rate_changes(id,detail_number,detail_name,detail_fullname,detail_rate,start_date,end_date,create_date,foreign_key)values(?,?,?,?,?,?,?,?,?)").toString();
	 	
	 	String sql_detail_account  = new StringBuilder("insert account_rate_changestemp(id,detail_number,detail_name,detail_fullname,detail_rate,start_date,end_date,create_date,foreign_key)values(?,?,?,?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_detail_account,ID,DETAIL_NUMBER,DETAIL_NAME,DETAIL_FULLNAME,DETAIL_RATE,START_DATE,END_DATE,new Timestamp(new Date().getTime()),foreign_key); 		
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BankAccountEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal_bak(String sql,BankAccountEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{account_number}",String.valueOf(t.getAccountNumber()));
 		sql  = sql.replace("#{acount_name}",String.valueOf(t.getAcountName()));
 		sql  = sql.replace("#{acount_fullname}",String.valueOf(t.getAcountFullname()));
 		sql  = sql.replace("#{acount_shortname}",String.valueOf(t.getAcountShortname()));
 		sql  = sql.replace("#{acount_type}",String.valueOf(t.getAcountType()));
 		sql  = sql.replace("#{kaihu_date}",String.valueOf(t.getKaihuDate()));
 		sql  = sql.replace("#{acount_status}",String.valueOf(t.getAcountStatus()));
 		sql  = sql.replace("#{acount_interest_rate}",String.valueOf(t.getAcountInterestRate()));
 		sql  = sql.replace("#{interest_rate_date}",String.valueOf(t.getInterestRateDate()));
 		sql  = sql.replace("#{is_liushui}",String.valueOf(t.getIsLiushui()));
 		sql  = sql.replace("#{online_product}",String.valueOf(t.getOnlineProduct()));
 		sql  = sql.replace("#{big_zhifu}",String.valueOf(t.getBigZhifu()));
 		sql  = sql.replace("#{is_pre_end}",String.valueOf(t.getIsPreEnd()));
 		sql  = sql.replace("#{contact}",String.valueOf(t.getContact()));
 		sql  = sql.replace("#{contact_type}",String.valueOf(t.getContactType()));
 		sql  = sql.replace("#{line_hanghao}",String.valueOf(t.getLineHanghao()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BankAccountEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{account_number}",String.valueOf(t.getAccountNumber()));
 		sql  = sql.replace("#{acount_name}",String.valueOf(t.getAcountName()));
 		sql  = sql.replace("#{acount_abbreve}",String.valueOf(t.getAcountAbbreve()));
 		sql  = sql.replace("#{acount_fullname}",String.valueOf(t.getAcountFullname()));
 		sql  = sql.replace("#{acount_shortname}",String.valueOf(t.getAcountShortname()));
 		sql  = sql.replace("#{acount_type}",String.valueOf(t.getAcountType()));
 		sql  = sql.replace("#{kaihu_date}",String.valueOf(t.getKaihuDate()));
 		sql  = sql.replace("#{acount_status}",String.valueOf(t.getAcountStatus()));
 		sql  = sql.replace("#{acount_interest_rate}",String.valueOf(t.getAcountInterestRate()));
 		sql  = sql.replace("#{interest_rate_date}",String.valueOf(t.getInterestRateDate()));
 		sql  = sql.replace("#{is_liushui}",String.valueOf(t.getIsLiushui()));
 		sql  = sql.replace("#{online_product}",String.valueOf(t.getOnlineProduct()));
 		sql  = sql.replace("#{big_zhifu}",String.valueOf(t.getBigZhifu()));
 		sql  = sql.replace("#{is_pre_end}",String.valueOf(t.getIsPreEnd()));
 		sql  = sql.replace("#{jingban}",String.valueOf(t.getJingban()));
 		sql  = sql.replace("#{contact}",String.valueOf(t.getContact()));
 		sql  = sql.replace("#{contact_addr}",String.valueOf(t.getContactAddr()));
 		sql  = sql.replace("#{contact_type}",String.valueOf(t.getContactType()));
 		sql  = sql.replace("#{line_hanghao}",String.valueOf(t.getLineHanghao()));
 		sql  = sql.replace("#{seal}",String.valueOf(t.getSeal()));
 		sql  = sql.replace("#{zhang}",String.valueOf(t.getZhang()));
 		sql  = sql.replace("#{cundan}",String.valueOf(t.getCundan()));
 		sql  = sql.replace("#{todate}",String.valueOf(t.getTodate()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

 	/**
	 * 当用户修改了附表信息时，附表根据修改内容将进行更新
	 */
	@Override
	public void updateMain(BankAccountEntity bankAccount, List<AccountRateChangesEntity> accountRateChangesList,
			List<RaiseAccountUseEntity> raiseAccountUseList) {
		
		String bankAccountId = bankAccount.getId();
		BankAccountEntity bankAccount2= this.get(BankAccountEntity.class, bankAccountId);
		BankAccountEntity newBankAccount = new BankAccountEntity();///是为了记录之前的数据
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bankAccount2, newBankAccount);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//保存主表信息--2018-09-10暂时不保存主表信息了，但是需要保存附表信息
		
		/*if(StringUtil.isNotEmpty(bankAccount.getId())){
			try {
				BankAccountEntity temp = findUniqueByProperty(BankAccountEntity.class, "id", bankAccount.getId());
				MyBeanUtils.copyBeanNotNull2Bean(bankAccount, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(bankAccount);
		}*/
		
		//===================================================================================
		/**
		 * 只保存账户信息的状态-----不保存页面带过来的其他信息
		 */
		
		if(StringUtil.isNotEmpty(bankAccount.getId())){
			try {
				BankAccountEntity newBankAccount2 = new BankAccountEntity();
				newBankAccount2.setId(bankAccount.getId());
				newBankAccount2.setStatus("X");//待审核状态
				BankAccountEntity temp = findUniqueByProperty(BankAccountEntity.class, "id", bankAccount.getId());
				MyBeanUtils.copyBeanNotNull2Bean(newBankAccount2, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(bankAccount);
		}
		
		/**
		 * 数据库中的字段值
		 */
		/*Object dETAIL_NUMBER0 = newBankAccount.getAccountNumber();
		Object dETAIL_NAME0 = newBankAccount.getAcountName();
		Object dETAIL_FULLNAME0 = newBankAccount.getAcountFullname();
		Object yINHANG_ACCOUNT1 = newBankAccount.getAccountNumber();
		Object yINHANG_NAME1 = newBankAccount.getAcountName();
		Object yINHANG_NAMEFULL1 = newBankAccount.getAcountFullname();*/
		//===================================================================================

		//1.查询出数据库的明细数据-账户利率变化明细表
		
			   //暂时注释掉-根据三个字段查询 账户利率变化明细表 0716
		/*	    String hql0 = "from AccountRateChangesEntity where 1 = 1 AND dETAIL_NUMBER = ?  AND dETAIL_NAME = ?  AND dETAIL_FULLNAME = ? ";
			    List<AccountRateChangesEntity> accountRateChangesOldList = this.findHql(hql0,dETAIL_NUMBER0,dETAIL_NAME0,dETAIL_FULLNAME0);
		*/		 
		 String hql0 = "from AccountRateChangesEntity where 1 = 1 AND foreign_key = ?   ORDER BY CREATE_DATE DESC ";
		 List<AccountRateChangesEntity> accountRateChangesOldList = this.findHql(hql0,bankAccountId);
		    	
		//2.筛选更新明细数据-账户利率变化明细表
			/**
			 * 此段代码忽略-start，忽略原因是，不允许修改附表
			 */
				if(accountRateChangesList!=null&&accountRateChangesList.size()>0){
				for(AccountRateChangesEntity oldE:accountRateChangesOldList){
					boolean isUpdate = false;
						for(AccountRateChangesEntity sendE:accountRateChangesList){
							//需要更新的明细数据-账户利率变化明细表
							if(oldE.getId().equals(sendE.getId())){
				    			try {
				    				/**
				    				 * 根据主表三个字段-有一个字段修改，附表的字段就会更新，暂时注释掉
				    				 */
				    				/*sendE.setDetailNumber(bankAccount.getAccountNumber());
				    				sendE.setDetailName(bankAccount.getAcountName());
				    				sendE.setDetailFullname(bankAccount.getAcountFullname());*/
									
									MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
									this.saveOrUpdate(oldE);
								} catch (Exception e) {
									e.printStackTrace();
									throw new BusinessException(e.getMessage());
								}
								isUpdate= true;
				    			break;
				    		}
				    	}
			    		if(!isUpdate){
				    		//如果数据库存在的明细，前台没有传递过来则是删除-账户利率变化明细表
				    		//super.delete(oldE);
			    		}
			    		
					}
					
					//3.持久化新增的数据-账户利率变化明细表
					/*for(AccountRateChangesEntity accountRateChanges:accountRateChangesList){
						if(oConvertUtils.isEmpty(accountRateChanges.getId())){
							//外键设置
							accountRateChanges.setDetailNumber(bankAccount.getAccountNumber());
							accountRateChanges.setDetailName(bankAccount.getAcountName());
							accountRateChanges.setDetailFullname(bankAccount.getAcountFullname());
							this.save(accountRateChanges);
						}
					}*/
				}
		
		//===================================================================================
		//1.查询出数据库的明细数据-募集账户使用情况明细
	    //String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND yINHANG_ACCOUNT = ?  AND yINHANG_NAME = ?  AND yINHANG_NAMEFULL = ? ";
	    //List<RaiseAccountUseEntity> raiseAccountUseOldList = this.findHql(hql1,yINHANG_ACCOUNT1,yINHANG_NAME1,yINHANG_NAMEFULL1);
		
		String hql1 = "from RaiseAccountUseEntity where 1 = 1 AND foreign_key = ?  ";
		List<RaiseAccountUseEntity> raiseAccountUseOldList = this.findHql(hql1,bankAccountId);
				
			    
	    //2.筛选更新明细数据-募集账户使用情况明细
		if(raiseAccountUseList!=null&&raiseAccountUseList.size()>0){
	    for(RaiseAccountUseEntity oldE:raiseAccountUseOldList){
			boolean isUpdate = false;
				for(RaiseAccountUseEntity sendE:raiseAccountUseList){
					//需要更新的明细数据-募集账户使用情况明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
		    				/*sendE.setYinhangAccount(bankAccount.getAccountNumber());
		    				sendE.setYinhangName(bankAccount.getAcountName());
		    				sendE.setYinhangNamefull(bankAccount.getAcountFullname());*/
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-募集账户使用情况明细
		    		//super.delete(oldE);
	    		}
	    		
			}
			
		}
		
		/**
		 * 此段代码忽略-end
		 */
		
		/**
		 * 当银行账户的利率发生变化时
		 */
		String newAccountRate= bankAccount.getAcountInterestRate();//当前页面传入进来的 账户利率
		String oldAccountRate= newBankAccount.getAcountInterestRate();//之前的账户利率，即该对象的持久化数据
		if(StringUtils.isNotEmpty(newAccountRate)&&!newAccountRate.equals(oldAccountRate)){
			//如果账户利率表的数据大于1条，并且截止日期不为2099
			String sql = "select * from account_rate_changes where 1 = 1 AND foreign_key = ? AND END_DATE != ? ";
			String sql2 = "select * from account_rate_changes where 1 = 1 AND foreign_key = ? ";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate;
			try {
				endDate = dateFormat.parse("2099-12-31");
				List<Map<String, Object>>  maps = this.findForJdbc(sql, bankAccountId,endDate);
				if(maps.size()>=1){
					//向临时表插入数据，不用考虑数据库是否存在，利率发生改变，就存数据
					saveAccountRateChangeTemp(bankAccount, newBankAccount,1);
				}else{
					List<Map<String, Object>>  maps2 = this.findForJdbc(sql2, bankAccountId);
					if(maps2.size()==0){//账户变化表的表没数据
						saveAccountRateChangeTemp(bankAccount, newBankAccount,0);
					}
				}//其余的排除选项，就是只有一条数据，
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 当-在用产品-发生变化的时候，向募集账户使用情况明细--raise_account_usetemp 这张临时表中插入数据插入数据
		 */
				String currentOnlineProduct =  bankAccount.getOnlineProduct();//当前的在用产品
				String oldOnlineProduct = newBankAccount.getOnlineProduct();//之前的在用产品
				if(StringUtils.isNotEmpty(currentOnlineProduct)&&!currentOnlineProduct.equals(oldOnlineProduct)){
					
					RaiseAccountUsetempEntity raiseAccountUseEntity = new RaiseAccountUsetempEntity();
					
					raiseAccountUseEntity.setYinhangAccount(newBankAccount.getAccountNumber());
					raiseAccountUseEntity.setYinhangName(newBankAccount.getAcountName());
					raiseAccountUseEntity.setYinhangNamefull(newBankAccount.getAcountFullname());
					
					raiseAccountUseEntity.setForeignKey(newBankAccount.getId());
					raiseAccountUseEntity.setJiexiStatus("-1");
					raiseAccountUseEntity.setPreProject(newBankAccount.getOnlineProduct()==null?"":newBankAccount.getOnlineProduct());
					this.save(raiseAccountUseEntity);
				}
	}
    /**
     * 
     * @param bankAccount-------当前页面的对象
     * @param newBankAccount----数据库中的持久化对象
     * @param flag
     */
	private void saveAccountRateChangeTemp(BankAccountEntity bankAccount, BankAccountEntity newBankAccount,int flag) {
		AccountRateChangestempEntity  accountRateChangestemp = new AccountRateChangestempEntity();
		accountRateChangestemp.setDetailFullname(newBankAccount.getAcountFullname());
		accountRateChangestemp.setDetailName(newBankAccount.getAcountName());
		accountRateChangestemp.setDetailNumber(newBankAccount.getAccountNumber());
		accountRateChangestemp.setForeignKey(newBankAccount.getId());
		if(flag == 1){
			accountRateChangestemp.setEndDate(bankAccount.getInterestRateDate());
			accountRateChangestemp.setDetailRate(newBankAccount.getAcountInterestRate());
			accountRateChangestemp.setStartDate(newBankAccount.getInterestRateDate());
		}else{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate;
			try {
				endDate = dateFormat.parse("2099-12-31");
				accountRateChangestemp.setEndDate(endDate);
				accountRateChangestemp.setDetailRate(bankAccount.getAcountInterestRate());
				accountRateChangestemp.setStartDate(bankAccount.getInterestRateDate());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.save(accountRateChangestemp);
	}
	/**
	 * 当审核通过的时候，将两张附表的临时表  同步到附表的正式表
	 * 可能会出现反复审核的情况-反复的情况可以用saveOrupdate，因此需要判断何时 同步数据
	 */
	@Override
	public void updateAttach2(String id) {
		
		if(StringUtil.isNotEmpty(id)){
			/**
			 * 附表1
			 */
			String sqlZhengshi = "select * from account_rate_changes where 1 = 1 AND foreign_key = ?  AND END_DATE = ? ";//正式表
			String sqlLinshi = "select * from account_rate_changestemp where 1 = 1 AND foreign_key = ?  AND END_DATE = ? ";//临时表
			BankAccountempEntity bankAccountemp = this.get(BankAccountempEntity.class, id);//测试库
			//获得2099-12-31 的Date对象
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = new Date();
			try {
				endDate = dateFormat.parse("2099-12-31");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Map<String, Object> zhengShi = this.findOneForJdbc(sqlZhengshi, id,endDate);
			    /**
			     * 1.根据截止日期为2099，查询正式表，如果存在，将截止日期改为利率启用日期
			     */
			    if(zhengShi!=null){
			    	AccountRateChangesEntity accountRateChange = this.get(AccountRateChangesEntity.class, zhengShi.get("id").toString());
			    	if(bankAccountemp!=null&&bankAccountemp.getInterestRateDate()!=null){
			    		
			    		accountRateChange.setEndDate(bankAccountemp.getInterestRateDate());
			    	}
			    }else{
			    /**
			     * 2.如果正式表不存在，根据截止日期为2099，查询临时表,并同步到正式表，同步之前要判断正式表是否存在；然后将临时表的截止日期改为主表临时表的账户启用日期
			     *   不存在才去同步，防止反复同步
			     */
			    	Map<String, Object> linShi = this.findOneForJdbc(sqlLinshi, id,endDate);
			    	/**
		    		 * 2.1 如果临时表存在，不用将临时表的截止日期改为利率启用日期，同步到正式表
		    		 *     同步之前要判断正式表是否存在 ，不存在才去同步，防止反复同步
		    		 */
			    	if(linShi!=null){
			    		AccountRateChangestempEntity accountRateTemp = this.get(AccountRateChangestempEntity.class, linShi.get("id").toString());
			    		if(accountRateTemp!=null){
			    			if(bankAccountemp!=null&&bankAccountemp.getInterestRateDate()!=null){
				    			//accountRateTemp.setEndDate(bankAccountemp.getInterestRateDate());
				    			//同步到正式表!,同步之前要判断正式表是否存在 ，不存在才去同步，防止反复同步
				    			AccountRateChangesEntity accountRateChange = this.get(AccountRateChangesEntity.class, linShi.get("id").toString());
				    			if(accountRateChange==null){
				    				AccountRateChangesEntity accountRateChangeSave = new AccountRateChangesEntity();
				    				try {
										MyBeanUtils.copyBeanNotNull2Bean(accountRateTemp,accountRateChangeSave);
										this.save(accountRateChangeSave);
										//将临时表的截止日期改为主表临时表的账户启用日期
										accountRateTemp.setEndDate(bankAccountemp.getInterestRateDate());
									} catch (Exception e) {
										e.printStackTrace();
									}
				    				
				    			}
						    	
				    		}
			    		}
				    	
			    	}else{
			    		/**
			    		 * 2.2 如果不存在，根据开始日期、结束日期、利率、外键# 查询临时表、正式表
			    		 */
			    		//开始日期-就是正式表的利率启用日期
			    		//结束日期-就是临时表的利率启用日期
			    		//利率-就是正式表的利率
			    		BankAccountEntity  bankAccount = this.get(BankAccountEntity.class, id);//正式库
			    		if(bankAccount!=null){
			    			String sqlZhengshi2 = "select * from account_rate_changes where 1 = 1 AND foreign_key = ? and DETAIL_RATE = ? AND START_DATE = ? AND END_DATE = ? order by create_date limit 0,1";
			    			String sqlLinshi2 =  "select * from account_rate_changestemp where 1 = 1 AND foreign_key = ? and DETAIL_RATE = ? AND START_DATE = ? AND END_DATE = ? order by create_date limit 0,1 ";
			    			Date startDate = bankAccount.getInterestRateDate();
			    			Date endDates = bankAccountemp.getInterestRateDate();
			    			String lilv = bankAccount.getAcountInterestRate();
			    			if(startDate!=null&&endDates!=null&&lilv!=null){
			    				
			    				Map<String, Object> mapZhengshi = this.findOneForJdbc(sqlZhengshi2, id,lilv,startDate,endDates);
			    				Map<String, Object> maplinshi = this.findOneForJdbc(sqlLinshi2, id,lilv,startDate,endDates);
			    				/**
			    				 *  如果临时表存在、正式表不存在，将临时表的数据同步到正式表
			    				 */
				    			if(maplinshi!=null&&mapZhengshi==null){
				    				AccountRateChangestempEntity accountRateTemp = this.get(AccountRateChangestempEntity.class, maplinshi.get("id").toString());
				    				if(accountRateTemp!=null){
				    					AccountRateChangesEntity accountRateChange = new AccountRateChangesEntity();
				    					try {
											MyBeanUtils.copyBeanNotNull2Bean(accountRateTemp,accountRateChange);
											this.save(accountRateChange);
										} catch (Exception e) {
											e.printStackTrace();
										}
										
				    				}
						    		
				    			}
			    			}
							
			    		
			    		
			    		}
			    	}
			    }
			    
			/**
			 * 附表2
			 */
			BankAccountEntity  bankAccount = this.get(BankAccountEntity.class, id);//正式库
			String productSqlZhengshi = "select * from raise_account_use t where t.foreign_key = ? and t.pre_project = ?  order by create_date limit 0,1 ";
			String productSqlCeshi = "select * from raise_account_usetemp t where t.foreign_key = ? and t.pre_project = ?  order by create_date limit 0,1 ";
			Map<String, Object> mapZhengshi = this.findOneForJdbc(productSqlZhengshi, id,bankAccount.getOnlineProduct());
			Map<String, Object> mapCeshi = this.findOneForJdbc(productSqlCeshi, id,bankAccount.getOnlineProduct());
			if(mapCeshi != null && mapZhengshi == null){
				RaiseAccountUsetempEntity  raiseAccountUsetemp = this.get(RaiseAccountUsetempEntity.class, mapCeshi.get("id").toString());
				if(raiseAccountUsetemp != null){
					RaiseAccountUseEntity RaiseAccountUseEntity = new RaiseAccountUseEntity();
					try {
						MyBeanUtils.copyBeanNotNull2Bean(raiseAccountUsetemp,RaiseAccountUseEntity);
						this.save(RaiseAccountUseEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
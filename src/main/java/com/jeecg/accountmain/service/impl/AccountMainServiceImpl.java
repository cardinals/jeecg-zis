package com.jeecg.accountmain.service.impl;
import com.jeecg.accountmain.service.AccountMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.accountmain.entity.AccountMainEntity;
import com.jeecg.raiseaccount.entity.RaiseAccountEntity;
import com.jeecg.accountdetail.entity.AccountDetailEntity;

import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.io.Serializable;
import java.sql.Timestamp;


@Service("accountMainService")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class AccountMainServiceImpl extends CommonServiceImpl implements AccountMainServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AccountMainEntity)entity);
 	}
	
	public void addMain(AccountMainEntity accountMain,
	        List<RaiseAccountEntity> raiseAccountList,List<AccountDetailEntity> accountDetailList){
			//保存主信息
			this.save(accountMain);
		
			/**保存-募集资金*/
			for(RaiseAccountEntity raiseAccount:raiseAccountList){
				//外键设置
				raiseAccount.setAccountMainId(accountMain.getId());
				this.save(raiseAccount);
			}
			/**保存-资金明细*/
			for(AccountDetailEntity accountDetail:accountDetailList){
				//外键设置
				accountDetail.setAccountMainId(accountMain.getId());
				this.save(accountDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(accountMain);
	}

	
	public void updateMain(AccountMainEntity accountMain,
	        List<RaiseAccountEntity> raiseAccountList,List<AccountDetailEntity> accountDetailList) {
		AccountMainEntity  newAccountMain = this.get(AccountMainEntity.class, accountMain.getId());
		//执行更新操作配置的sql增强
 		this.doUpdateSql(newAccountMain);
 		
		//保存主表信息
		if(StringUtil.isNotEmpty(accountMain.getId())){
			try {
				AccountMainEntity temp = findUniqueByProperty(AccountMainEntity.class, "id", accountMain.getId());
				MyBeanUtils.copyBeanNotNull2Bean(accountMain, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(accountMain);
		}
//===================================================================================
		//获取参数
		Object id0 = accountMain.getId();
		Object id1 = accountMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-募集资金
	    String hql0 = "from RaiseAccountEntity where 1 = 1 AND aCCOUNT_MAIN_ID = ? ";
	    List<RaiseAccountEntity> raiseAccountOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-募集资金
		if(raiseAccountList!=null&&raiseAccountList.size()>0){
		for(RaiseAccountEntity oldE:raiseAccountOldList){
			boolean isUpdate = false;
				for(RaiseAccountEntity sendE:raiseAccountList){
					//需要更新的明细数据-募集资金
					if(oldE.getId().equals(sendE.getId())){
		    			try {
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-募集资金
	    			//如果数据库存在的明细，前台没有传递过来，说明在编辑的时候，对表单数据进行了删除
		    		//super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-募集资金
			for(RaiseAccountEntity raiseAccount:raiseAccountList){
				if(oConvertUtils.isEmpty(raiseAccount.getId())){
					//外键设置
					raiseAccount.setAccountMainId(accountMain.getId());
					this.save(raiseAccount);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-资金明细
	    String hql1 = "from AccountDetailEntity where 1 = 1 AND aCCOUNT_MAIN_ID = ? ";
	    List<AccountDetailEntity> accountDetailOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-资金明细
		if(accountDetailList!=null&&accountDetailList.size()>0){
		for(AccountDetailEntity oldE:accountDetailOldList){
			boolean isUpdate = false;
				for(AccountDetailEntity sendE:accountDetailList){
					//需要更新的明细数据-资金明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-资金明细
		    		//super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-资金明细
			for(AccountDetailEntity accountDetail:accountDetailList){
				if(oConvertUtils.isEmpty(accountDetail.getId())){
					//外键设置
					accountDetail.setAccountMainId(accountMain.getId());
					this.save(accountDetail);
				}
			}
		}
		
	}

	
	public void delMain(AccountMainEntity accountMain) {
		//删除主表信息
		this.delete(accountMain);
		//===================================================================================
		//获取参数
		Object id0 = accountMain.getId();
		Object id1 = accountMain.getId();
		//===================================================================================
		//删除-募集资金
	    String hql0 = "from RaiseAccountEntity where 1 = 1 AND aCCOUNT_MAIN_ID = ? ";
	    List<RaiseAccountEntity> raiseAccountOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(raiseAccountOldList);
		//===================================================================================
		//删除-资金明细
	    String hql1 = "from AccountDetailEntity where 1 = 1 AND aCCOUNT_MAIN_ID = ? ";
	    List<AccountDetailEntity> accountDetailOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(accountDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AccountMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AccountMainEntity preAccountMain){
	 	
	 	//1,向表 募集账户使用情况明细，插入更改之前的数据
	    //id用UUID即可
	 	//AccountMainEntity  preAccountMain = this.get(AccountMainEntity.class, t.getId());
	 	
	 	final String  YINHANG_ACCOUNT =  preAccountMain.getAccountNumber()==null?"":preAccountMain.getAccountNumber();
	 	final String  YINHANG_NAME = preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();
	 	final String  YINHANG_NAMEFULL = preAccountMain.getAcountFullname()==null?"":preAccountMain.getAcountFullname();
	 	final String  pre_project = preAccountMain.getAcountName()==null?"":preAccountMain.getAcountName();//========待议
	 	final String  jiexi_status = new StringBuilder("-1").toString();//-1代表未结息
	 	final String  foreign_key = preAccountMain.getId(); //外键
	 	final String PRI_ID= UUID.randomUUID().toString().replace("-", "");
	 	
	 	String sql_raise_account  = new StringBuilder("insert raise_account(id,yinhang_account,yinhang_name,yinhang_namefull,pre_project,jiexi_status,account_main_id)	values(?,?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_raise_account,PRI_ID,YINHANG_ACCOUNT,YINHANG_NAME,YINHANG_NAMEFULL,pre_project,jiexi_status,foreign_key);
	 	
	 	//2,向表 账户利率变化明细表 ，插入更改之前的数据
	    //id用UUID即可
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
	 	final String  ACCOUNT_MAIN_ID = preAccountMain.getId(); //外键
	 	final String ID= UUID.randomUUID().toString().replace("-", "");
	 	
	 	String sql_detail_account  = new StringBuilder("insert account_detail(id,detail_number,detail_name,detail_fullname,detail_rate,start_date,end_date,account_main_id)values(?,?,?,?,?,?,?,?)").toString();
	 	this.executeSql(sql_detail_account,ID,DETAIL_NUMBER,DETAIL_NAME,DETAIL_FULLNAME,DETAIL_RATE,START_DATE,END_DATE,ACCOUNT_MAIN_ID); 		
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AccountMainEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AccountMainEntity t){
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
}
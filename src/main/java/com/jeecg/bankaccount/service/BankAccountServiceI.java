package com.jeecg.bankaccount.service;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.accountratechanges.entity.AccountRateChangesEntity;
import com.jeecg.raiseaccountuse.entity.RaiseAccountUseEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface BankAccountServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList) ;
	
	public void addMainForImportExcel(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList);
	/**
	 * 修改一对多-0716
	 * 
	 */
	public void updateMainBy3Attr(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList);
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BankAccountEntity bankAccount,
	        List<AccountRateChangesEntity> accountRateChangesList,List<RaiseAccountUseEntity> raiseAccountUseList);
	public void delMain (BankAccountEntity bankAccount);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BankAccountEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BankAccountEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BankAccountEntity t);
	public void updateAttach2(String id);
}

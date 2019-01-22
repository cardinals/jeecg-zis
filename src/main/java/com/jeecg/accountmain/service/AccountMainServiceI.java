package com.jeecg.accountmain.service;
import com.jeecg.accountmain.entity.AccountMainEntity;
import com.jeecg.raiseaccount.entity.RaiseAccountEntity;
import com.jeecg.accountdetail.entity.AccountDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface AccountMainServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(AccountMainEntity accountMain,
	        List<RaiseAccountEntity> raiseAccountList,List<AccountDetailEntity> accountDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(AccountMainEntity accountMain,
	        List<RaiseAccountEntity> raiseAccountList,List<AccountDetailEntity> accountDetailList);
	public void delMain (AccountMainEntity accountMain);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AccountMainEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AccountMainEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AccountMainEntity t);
}

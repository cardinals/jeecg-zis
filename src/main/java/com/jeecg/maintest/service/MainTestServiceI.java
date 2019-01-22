package com.jeecg.maintest.service;
import com.jeecg.maintest.entity.MainTestEntity;
import com.jeecg.fubiaotest.entity.FubiaoTestEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface MainTestServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(MainTestEntity mainTest,
	        List<FubiaoTestEntity> fubiaoTestList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MainTestEntity mainTest,
	        List<FubiaoTestEntity> fubiaoTestList);
	public void delMain (MainTestEntity mainTest);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MainTestEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MainTestEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MainTestEntity t);
}

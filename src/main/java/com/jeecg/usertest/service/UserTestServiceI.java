package com.jeecg.usertest.service;
import com.jeecg.usertest.entity.UserTestEntity;
import com.jeecg.usertestline.entity.UserTestLineEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface UserTestServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(UserTestEntity userTest,
	        List<UserTestLineEntity> userTestLineList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(UserTestEntity userTest,
	        List<UserTestLineEntity> userTestLineList);
	public void delMain (UserTestEntity userTest);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(UserTestEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(UserTestEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(UserTestEntity t);
}

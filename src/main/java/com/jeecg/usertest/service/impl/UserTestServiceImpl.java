package com.jeecg.usertest.service.impl;
import com.jeecg.usertest.service.UserTestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.usertest.entity.UserTestEntity;
import com.jeecg.usertestline.entity.UserTestLineEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("userTestService")
@Transactional
public class UserTestServiceImpl extends CommonServiceImpl implements UserTestServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((UserTestEntity)entity);
 	}
	
	public void addMain(UserTestEntity userTest,
	        List<UserTestLineEntity> userTestLineList){
			//保存主信息
			this.save(userTest);
		
			/**保存-usertestline*/
			for(UserTestLineEntity userTestLine:userTestLineList){
				//外键设置
				userTestLine.setUsername(userTest.getUsername());
				this.save(userTestLine);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(userTest);
	}

	
	public void updateMain(UserTestEntity userTest,
	        List<UserTestLineEntity> userTestLineList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(userTest.getId())){
			try {
				UserTestEntity temp = findUniqueByProperty(UserTestEntity.class, "id", userTest.getId());
				MyBeanUtils.copyBeanNotNull2Bean(userTest, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(userTest);
		}
		//===================================================================================
		//获取参数
		Object uSERNAME0 = userTest.getUsername();
		//===================================================================================
		//1.查询出数据库的明细数据-usertestline
	    String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
	    List<UserTestLineEntity> userTestLineOldList = this.findHql(hql0,uSERNAME0);
		//2.筛选更新明细数据-usertestline
		if(userTestLineList!=null&&userTestLineList.size()>0){
		for(UserTestLineEntity oldE:userTestLineOldList){
			boolean isUpdate = false;
				for(UserTestLineEntity sendE:userTestLineList){
					//需要更新的明细数据-usertestline
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-usertestline
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-usertestline
			for(UserTestLineEntity userTestLine:userTestLineList){
				if(oConvertUtils.isEmpty(userTestLine.getId())){
					//外键设置
					userTestLine.setUsername(userTest.getUsername());
					this.save(userTestLine);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(userTest);
	}

	
	public void delMain(UserTestEntity userTest) {
		//删除主表信息
		this.delete(userTest);
		//===================================================================================
		//获取参数
		Object uSERNAME0 = userTest.getUsername();
		//===================================================================================
		//删除-usertestline
	    String hql0 = "from UserTestLineEntity where 1 = 1 AND uSERNAME = ? ";
	    List<UserTestLineEntity> userTestLineOldList = this.findHql(hql0,uSERNAME0);
		this.deleteAllEntitie(userTestLineOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(UserTestEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(UserTestEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(UserTestEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,UserTestEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{username}",String.valueOf(t.getUsername()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
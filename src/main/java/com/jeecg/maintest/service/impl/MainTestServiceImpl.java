package com.jeecg.maintest.service.impl;
import com.jeecg.maintest.service.MainTestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.maintest.entity.MainTestEntity;
import com.jeecg.fubiaotest.entity.FubiaoTestEntity;

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


@Service("mainTestService")
@Transactional
public class MainTestServiceImpl extends CommonServiceImpl implements MainTestServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((MainTestEntity)entity);
 	}
	
	public void addMain(MainTestEntity mainTest,
	        List<FubiaoTestEntity> fubiaoTestList){
			//保存主信息
			this.save(mainTest);
		
			
			
			/**保存-主表测试*/
			for(FubiaoTestEntity fubiaoTest:fubiaoTestList){
				//外键设置
				fubiaoTest.setFuName(mainTest.getMainName());
				//外键设置
				fubiaoTest.setFuAge(mainTest.getMainAge());
				this.save(fubiaoTest);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(mainTest);
	}

	
	public void updateMain(MainTestEntity mainTest,
	        List<FubiaoTestEntity> fubiaoTestList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(mainTest.getId())){
			try {
				MainTestEntity temp = findUniqueByProperty(MainTestEntity.class, "id", mainTest.getId());
				MyBeanUtils.copyBeanNotNull2Bean(mainTest, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(mainTest);
		}
		//===================================================================================
		//获取参数
		Object fU_NAME0 = mainTest.getMainName();
		Object fU_AGE0 = mainTest.getMainAge();
		//===================================================================================
		//1.查询出数据库的明细数据-主表测试
	    String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
	    List<FubiaoTestEntity> fubiaoTestOldList = this.findHql(hql0,fU_NAME0,fU_AGE0);
		//2.筛选更新明细数据-主表测试
		if(fubiaoTestList!=null&&fubiaoTestList.size()>0){
		for(FubiaoTestEntity oldE:fubiaoTestOldList){
			boolean isUpdate = false;
				for(FubiaoTestEntity sendE:fubiaoTestList){
					//需要更新的明细数据-主表测试
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-主表测试
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-主表测试
			for(FubiaoTestEntity fubiaoTest:fubiaoTestList){
				if(oConvertUtils.isEmpty(fubiaoTest.getId())){
					//外键设置
					fubiaoTest.setFuName(mainTest.getMainName());
					fubiaoTest.setFuAge(mainTest.getMainAge());
					this.save(fubiaoTest);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(mainTest);
	}

	
	public void delMain(MainTestEntity mainTest) {
		//删除主表信息
		this.delete(mainTest);
		//===================================================================================
		//获取参数
		Object fU_NAME0 = mainTest.getMainName();
		Object fU_AGE0 = mainTest.getMainAge();
		//===================================================================================
		//删除-主表测试
	    String hql0 = "from FubiaoTestEntity where 1 = 1 AND fU_NAME = ?  AND fU_AGE = ? ";
	    List<FubiaoTestEntity> fubiaoTestOldList = this.findHql(hql0,fU_NAME0,fU_AGE0);
		this.deleteAllEntitie(fubiaoTestOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MainTestEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MainTestEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MainTestEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,MainTestEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{main_name}",String.valueOf(t.getMainName()));
 		sql  = sql.replace("#{main_age}",String.valueOf(t.getMainAge()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
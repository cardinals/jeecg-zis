package com.jeecg.manager.service.impl;
import com.jeecg.manager.service.AddmanagerServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.jeecg.mana.entity.ManagEntity;
import com.jeecg.manager.entity.AddmanagerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("addmanagerService")
@Transactional
public class AddmanagerServiceImpl extends CommonServiceImpl implements AddmanagerServiceI {

	
 	public void delete(AddmanagerEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(AddmanagerEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(AddmanagerEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(AddmanagerEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(AddmanagerEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(AddmanagerEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(AddmanagerEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("name", t.getName());
		map.put("age", t.getAge());
		map.put("sex", t.getSex());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,AddmanagerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{age}",String.valueOf(t.getAge()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("addManager",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public void addMain(AddmanagerEntity addmanager, List<ManagEntity> manas) {
		//1.保存主表信息
			try {
				this.save(addmanager);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//2.保存附表信息
		for(ManagEntity mans:manas) {
			mans.setForeignKey(addmanager.getId());
			this.save(mans);
		}
		
	}

	@Override
	public void updateMain(AddmanagerEntity t, List<ManagEntity> manas) {
		//1.更改主表
		try {
			this.saveOrUpdate(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2.查询历史附表信息
		List<ManagEntity> manasHis = this.findByProperty(ManagEntity.class, "foreignKey", t.getId());
		//3.删除附表之前的信息
		if(manasHis.size()>0) {
			for (ManagEntity managEntity : manasHis) {
				this.delete(managEntity);
				//this.deleteEntityById(ManagEntity.class, managEntity.getId());
			}
		}
		//4.添加附表新增的信息
		for (ManagEntity managEntity : manas) {
			String name = managEntity.getName();
			String age = managEntity.getAge();
			String sex = managEntity.getSex();
			if(StringUtil.isEmpty(name)&&StringUtil.isEmpty(age)&&StringUtil.isEmpty(sex))
				continue;
			managEntity.setForeignKey(t.getId());
			this.save(managEntity);
		}
	}

	
}
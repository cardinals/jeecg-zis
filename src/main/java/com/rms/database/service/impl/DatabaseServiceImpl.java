package com.rms.database.service.impl;
import com.rms.database.service.DatabaseServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.database.entity.DatabaseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("databaseService")
@Transactional
public class DatabaseServiceImpl extends CommonServiceImpl implements DatabaseServiceI {

	
 	public void delete(DatabaseEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(DatabaseEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(DatabaseEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(DatabaseEntity t) throws Exception{
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
	private void doUpdateBus(DatabaseEntity t) throws Exception{
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
	private void doDelBus(DatabaseEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(DatabaseEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("database_id", t.getDatabaseId());
		map.put("server_id", t.getServerId());
		map.put("os", t.getOs());
		map.put("ip", t.getIp());
		map.put("db_type", t.getDbType());
		map.put("version", t.getVersion());
		map.put("status", t.getStatus());
		map.put("database_name", t.getDatabaseName());
		map.put("sid", t.getSid());
		map.put("racorha", t.getRacorha());
		map.put("administrator", t.getAdministrator());
		map.put("launch_date", t.getLaunchDate());
		map.put("remark1", t.getRemark1());
		map.put("remark2", t.getRemark2());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,DatabaseEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{database_id}",String.valueOf(t.getDatabaseId()));
 		sql  = sql.replace("#{server_id}",String.valueOf(t.getServerId()));
 		sql  = sql.replace("#{os}",String.valueOf(t.getOs()));
 		sql  = sql.replace("#{ip}",String.valueOf(t.getIp()));
 		sql  = sql.replace("#{db_type}",String.valueOf(t.getDbType()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{database_name}",String.valueOf(t.getDatabaseName()));
 		sql  = sql.replace("#{sid}",String.valueOf(t.getSid()));
 		sql  = sql.replace("#{racorha}",String.valueOf(t.getRacorha()));
 		sql  = sql.replace("#{administrator}",String.valueOf(t.getAdministrator()));
 		sql  = sql.replace("#{launch_date}",String.valueOf(t.getLaunchDate()));
 		sql  = sql.replace("#{remark1}",String.valueOf(t.getRemark1()));
 		sql  = sql.replace("#{remark2}",String.valueOf(t.getRemark2()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
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
					javaInter.execute("rms_database_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
package com.rms.application.service.impl;
import com.rms.application.service.RmsApplicationJeecgServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.application.entity.RmsApplicationJeecgEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("rmsApplicationJeecgService")
@Transactional
public class RmsApplicationJeecgServiceImpl extends CommonServiceImpl implements RmsApplicationJeecgServiceI {

	
 	public void delete(RmsApplicationJeecgEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	

 	public void saveOrUpdate(RmsApplicationJeecgEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(RmsApplicationJeecgEntity t) throws Exception{
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
	private void doUpdateBus(RmsApplicationJeecgEntity t) throws Exception{
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
	private void doDelBus(RmsApplicationJeecgEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(RmsApplicationJeecgEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("application_name_cn", t.getApplicationNameCn());
		map.put("application_name", t.getApplicationName());
		//map.put("container", t.getContainer());
		//map.put("server_id", t.getServerId2());
		map.put("ip", t.getIp());
		map.put("app_type", t.getAppType());
		map.put("status", t.getStatus());
		map.put("app_info", t.getAppInfo());
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
 	public String replaceVal(String sql,RmsApplicationJeecgEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{application_name_cn}",String.valueOf(t.getApplicationNameCn()));
 		sql  = sql.replace("#{application_name}",String.valueOf(t.getApplicationName()));
 		//sql  = sql.replace("#{container}",String.valueOf(t.getContainer()));
 		
 		//sql  = sql.replace("#{server_id}",String.valueOf(t.getServerId2()));
 		
 		sql  = sql.replace("#{ip}",String.valueOf(t.getIp()));
 		sql  = sql.replace("#{app_type}",String.valueOf(t.getAppType()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{app_info}",String.valueOf(t.getAppInfo()));
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
					javaInter.execute("rms_application_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
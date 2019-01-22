package com.rms.server.service.impl;
import com.rms.server.service.ServerServiceI;

import org.apache.commons.lang.xwork.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.rms.hardware.entity.HardwareEntity;
import com.rms.server.entity.ServerEntity;
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

@Service("serverService")
@Transactional
public class ServerServiceImpl extends CommonServiceImpl implements ServerServiceI {

	
 	public void delete(ServerEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ServerEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ServerEntity entity) throws Exception{
 		/*if(entity!=null) {
 			if(StringUtils.isNotEmpty(entity.getVirtural())&&"1".equals(entity.getVirtural())) {
 				ServerEntity serverEntity = new ServerEntity();
 				serverEntity.setServerName(entity.getVcontainer2());
 				entity.setVcontainer(serverEntity);//虚拟机容器
 	 		}
 			if(StringUtils.isNotEmpty(entity.getVirtural())&&"0".equals(entity.getVirtural())) {
 				HardwareEntity hardware = new HardwareEntity();
 				hardware.setName(entity.getContainer());
 				entity.setContainer("待定");;//硬件容器
 	 		}
 		}*/
 		
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ServerEntity t) throws Exception{
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
	private void doUpdateBus(ServerEntity t) throws Exception{
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
	private void doDelBus(ServerEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ServerEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("server_name", t.getServerName());
		map.put("ip", t.getIp());
		map.put("status", t.getStatus());
		map.put("os_type", t.getOsType());
		map.put("virtural", t.getVirtural());
		map.put("os_version", t.getOsVersion());
		map.put("config_info", t.getConfigInfo());
		map.put("manage_ip", t.getManageIp());
		map.put("create_date", t.getCreateDate());
		map.put("inner_ip", t.getInnerIp());
		map.put("outer_ip", t.getOuterIp());
		map.put("remark1", t.getRemark1());
		map.put("remark2", t.getRemark2());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("vcontainerid", t.getVcontainerid());
		map.put("applicant", t.getApplicant());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		//map.put("container", t.getContainer());
		map.put("administrator", t.getAdministrator());
		map.put("hardware_id", t.getHardwareId());
		map.put("vcontainer", t.getVcontainer());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ServerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{server_name}",String.valueOf(t.getServerName()));
 		sql  = sql.replace("#{ip}",String.valueOf(t.getIp()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{os_type}",String.valueOf(t.getOsType()));
 		sql  = sql.replace("#{virtural}",String.valueOf(t.getVirtural()));
 		sql  = sql.replace("#{os_version}",String.valueOf(t.getOsVersion()));
 		sql  = sql.replace("#{config_info}",String.valueOf(t.getConfigInfo()));
 		sql  = sql.replace("#{manage_ip}",String.valueOf(t.getManageIp()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{inner_ip}",String.valueOf(t.getInnerIp()));
 		sql  = sql.replace("#{outer_ip}",String.valueOf(t.getOuterIp()));
 		sql  = sql.replace("#{remark1}",String.valueOf(t.getRemark1()));
 		sql  = sql.replace("#{remark2}",String.valueOf(t.getRemark2()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{vcontainerid}",String.valueOf(t.getVcontainerid()));
 		sql  = sql.replace("#{applicant}",String.valueOf(t.getApplicant()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		//sql  = sql.replace("#{container}",String.valueOf(t.getContainer()));
 		sql  = sql.replace("#{administrator}",String.valueOf(t.getAdministrator()));
 		sql  = sql.replace("#{hardware_id}",String.valueOf(t.getHardwareId()));
 		sql  = sql.replace("#{vcontainer}",String.valueOf(t.getVcontainer()));
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
					javaInter.execute("rms_server_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
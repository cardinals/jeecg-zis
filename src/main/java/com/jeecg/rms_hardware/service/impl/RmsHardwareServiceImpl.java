package com.jeecg.rms_hardware.service.impl;
import com.jeecg.rms_hardware.service.RmsHardwareServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.rms_hardware.entity.RmsHardwareEntity;
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

@Service("rmsHardwareService")
@Transactional
public class RmsHardwareServiceImpl extends CommonServiceImpl implements RmsHardwareServiceI {

	
 	public void delete(RmsHardwareEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(RmsHardwareEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(RmsHardwareEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(RmsHardwareEntity t) throws Exception{
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
	private void doUpdateBus(RmsHardwareEntity t) throws Exception{
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
	private void doDelBus(RmsHardwareEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(RmsHardwareEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("maintype", t.getMaintype());
		map.put("status", t.getStatus());
		map.put("location", t.getLocation());
		map.put("position", t.getPosition());
		map.put("hardware_type", t.getHardwareType());
		map.put("hardware_no", t.getHardwareNo());
		map.put("remote_ilo", t.getRemoteIlo());
		map.put("purchase_date", t.getPurchaseDate());
		map.put("vendor", t.getVendor());
		map.put("warranty_date", t.getWarrantyDate());
		map.put("maintain_date", t.getMaintainDate());
		map.put("warranty_vendor", t.getWarrantyVendor());
		map.put("remark1", t.getRemark1());
		map.put("remark2", t.getRemark2());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("applicant", t.getApplicant());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		map.put("agent", t.getAgent());
		map.put("name", t.getName());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,RmsHardwareEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{maintype}",String.valueOf(t.getMaintype()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{location}",String.valueOf(t.getLocation()));
 		sql  = sql.replace("#{position}",String.valueOf(t.getPosition()));
 		sql  = sql.replace("#{hardware_type}",String.valueOf(t.getHardwareType()));
 		sql  = sql.replace("#{hardware_no}",String.valueOf(t.getHardwareNo()));
 		sql  = sql.replace("#{remote_ilo}",String.valueOf(t.getRemoteIlo()));
 		sql  = sql.replace("#{purchase_date}",String.valueOf(t.getPurchaseDate()));
 		sql  = sql.replace("#{vendor}",String.valueOf(t.getVendor()));
 		sql  = sql.replace("#{warranty_date}",String.valueOf(t.getWarrantyDate()));
 		sql  = sql.replace("#{maintain_date}",String.valueOf(t.getMaintainDate()));
 		sql  = sql.replace("#{warranty_vendor}",String.valueOf(t.getWarrantyVendor()));
 		sql  = sql.replace("#{remark1}",String.valueOf(t.getRemark1()));
 		sql  = sql.replace("#{remark2}",String.valueOf(t.getRemark2()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{applicant}",String.valueOf(t.getApplicant()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		sql  = sql.replace("#{agent}",String.valueOf(t.getAgent()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
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
					javaInter.execute("rms_hardware",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
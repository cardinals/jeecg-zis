package com.rms.assethistory.service.impl;
import com.rms.assethistory.service.AssetHistoryServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.assethistory.entity.AssetHistoryEntity;
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

@Service("assetHistoryService")
@Transactional
public class AssetHistoryServiceImpl extends CommonServiceImpl implements AssetHistoryServiceI {

	
 	public void delete(AssetHistoryEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(AssetHistoryEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(AssetHistoryEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(AssetHistoryEntity t) throws Exception{
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
	private void doUpdateBus(AssetHistoryEntity t) throws Exception{
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
	private void doDelBus(AssetHistoryEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(AssetHistoryEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("asset_history_id", t.getAssetHistoryId());
		map.put("operation", t.getOperation());
		map.put("asset_id", t.getAssetId());
		map.put("company", t.getCompany());
		map.put("maintype", t.getMaintype());
		map.put("asset_no", t.getAssetNo());
		map.put("location", t.getLocation());
		map.put("vendor", t.getVendor());
		map.put("asset_type", t.getAssetType());
		map.put("config", t.getConfig());
		map.put("pc_type", t.getPcType());
		map.put("purchase_date", t.getPurchaseDate());
		map.put("price", t.getPrice());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("employee_id", t.getEmployeeId());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		map.put("operation_by", t.getOperationBy());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,AssetHistoryEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{asset_history_id}",String.valueOf(t.getAssetHistoryId()));
 		sql  = sql.replace("#{operation}",String.valueOf(t.getOperation()));
 		sql  = sql.replace("#{asset_id}",String.valueOf(t.getAssetId()));
 		sql  = sql.replace("#{company}",String.valueOf(t.getCompany()));
 		sql  = sql.replace("#{maintype}",String.valueOf(t.getMaintype()));
 		sql  = sql.replace("#{asset_no}",String.valueOf(t.getAssetNo()));
 		sql  = sql.replace("#{location}",String.valueOf(t.getLocation()));
 		sql  = sql.replace("#{vendor}",String.valueOf(t.getVendor()));
 		sql  = sql.replace("#{asset_type}",String.valueOf(t.getAssetType()));
 		sql  = sql.replace("#{config}",String.valueOf(t.getConfig()));
 		sql  = sql.replace("#{pc_type}",String.valueOf(t.getPcType()));
 		sql  = sql.replace("#{purchase_date}",String.valueOf(t.getPurchaseDate()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{employee_id}",String.valueOf(t.getEmployeeId()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		sql  = sql.replace("#{operation_by}",String.valueOf(t.getOperationBy()));
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
					javaInter.execute("rms_asset_history_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
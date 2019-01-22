package com.rms.asset.service.impl;
import com.rms.asset.service.AssetServiceI;
import com.rms.assethistory.entity.AssetHistoryEntity;

import org.apache.commons.lang.xwork.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.asset.entity.AssetEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("assetService")
@Transactional
public class AssetServiceImpl extends CommonServiceImpl implements AssetServiceI {

	
 	public void delete(AssetEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save2(AssetEntity entity,String dateFormatId) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity,dateFormatId);
 		return t;
 	}
 	
 	public void saveOrUpdate(AssetEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		//this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(AssetEntity t,String dateFormatId) throws Exception{
		
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
		AssetHistoryEntity assetHistory = new AssetHistoryEntity();
		assetHistory.setOperation("0");//操作
		TSUser tSUser = ResourceUtil.getSessionUser();
		if(tSUser != null &&StringUtils.isNotEmpty(tSUser.getRealName())) {
			String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='user' and t.typename = ?";
			Map<String,Object> userId =  this.findOneForJdbc(sql, tSUser.getRealName());
			assetHistory.setUpdatedBy(userId!=null?(String) userId.get("typecode"):tSUser.getRealName());//操作人
		}
		assetHistory.setCreatedBy(tSUser.getId());
		assetHistory.setAssetId(t.getAssetId());
		assetHistory.setLastUpdateTime(new Date());//操作日期
		assetHistory.setAssetId(dateFormatId);
		super.save(assetHistory);
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(AssetEntity t,String assetId) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	//-----------------java增强 start---------------------------
	 	AssetHistoryEntity assetHistory = new AssetHistoryEntity();
		assetHistory.setOperation("2");//分配
		TSUser tSUser = ResourceUtil.getSessionUser();
		if(tSUser != null &&StringUtils.isNotEmpty(tSUser.getRealName())) {
			String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='user' and t.typename = ?";
			Map<String,Object> userId =  this.findOneForJdbc(sql, tSUser.getRealName());
			assetHistory.setUpdatedBy(userId!=null?(String) userId.get("typecode"):tSUser.getRealName());//操作人-就是当前登录人
		}
		assetHistory.setAssetId(t.getAssetId());
		assetHistory.setLastUpdateTime(new Date());//操作日期
		assetHistory.setEmployeeId(t.getEmployeeId());//7.当前使用人
		/*  <t:dgCol title="资产类型"  field="maintype"   queryMode="single"  dictionary="assets"  width="120"></t:dgCol>
		   <t:dgCol title="资产编号"  field="assetNo"  queryMode="group"  width="120"></t:dgCol>
		   <t:dgCol title="品牌"  field="vendor"  width="120"></t:dgCol>
		   <t:dgCol title="类型"  field="maintype"   width="120"></t:dgCol>
		   <t:dgCol title="设备参数"  field="config"    width="120"></t:dgCol>
		   <t:dgCol title="购置日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  width="120"></t:dgCol>*/
		  
		/*1.资产类型
		2.资产编号
		3.品牌
		4.类型
		5.设备参数
		6.购置日期
		7.当前使用人*/
		
		assetHistory.setMaintype(t.getMaintype()!=null?t.getMaintype():"");
		assetHistory.setAssetNo(t.getAssetNo()!=null?t.getAssetNo():"");
		assetHistory.setVendor(t.getVendor()!=null?t.getVendor():"");
		assetHistory.setAssetType(t.getAssetType()!=null?t.getAssetType():"");
		assetHistory.setConfig(t.getConfig()!=null?t.getConfig():"");
		if(t.getPurchaseDate()!=null) {
			assetHistory.setPurchaseDate(t.getPurchaseDate());
		}
		//7.当前使用人
		super.save(assetHistory);
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(AssetEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(AssetEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("asset_id", t.getAssetId());
		map.put("company", "6666");
		map.put("location", t.getLocation());
		map.put("maintype", t.getMaintype());
		map.put("asset_no", t.getAssetNo());
		map.put("department_id", t.getDepartmentId());
		map.put("employee_id", t.getEmployeeId());
		map.put("vendor", t.getVendor());
		map.put("asset_type", t.getAssetType());
		map.put("config", t.getConfig());
		map.put("pc_type", t.getPcType());
		map.put("pc_no", t.getPcNo());
		map.put("power_no", t.getPowerNo());
		map.put("battery_no", t.getBatteryNo());
		map.put("mac", t.getMac());
		map.put("wifi_mac", t.getWifiMac());
		map.put("purchase_date", t.getPurchaseDate());
		map.put("warranty_period", t.getWarrantyPeriod());
		map.put("price", t.getPrice());
		map.put("remark1", t.getRemark1());
		map.put("status", t.getStatus());
		map.put("remark2", t.getRemark2());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		map.put("storehouse", t.getStorehouse());
		map.put("update_time", t.getUpdateTime());
		map.put("operation", t.getOperation());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,AssetEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{asset_id}",String.valueOf(t.getAssetId()));
 		sql  = sql.replace("#{company}",String.valueOf(t.getCompany()));
 		sql  = sql.replace("#{location}",String.valueOf(t.getLocation()));
 		sql  = sql.replace("#{maintype}",String.valueOf(t.getMaintype()));
 		sql  = sql.replace("#{asset_no}",String.valueOf(t.getAssetNo()));
 		sql  = sql.replace("#{department_id}",String.valueOf(t.getDepartmentId()));
 		sql  = sql.replace("#{employee_id}",String.valueOf(t.getEmployeeId()));
 		sql  = sql.replace("#{vendor}",String.valueOf(t.getVendor()));
 		sql  = sql.replace("#{asset_type}",String.valueOf(t.getAssetType()));
 		sql  = sql.replace("#{config}",String.valueOf(t.getConfig()));
 		sql  = sql.replace("#{pc_type}",String.valueOf(t.getPcType()));
 		sql  = sql.replace("#{pc_no}",String.valueOf(t.getPcNo()));
 		sql  = sql.replace("#{power_no}",String.valueOf(t.getPowerNo()));
 		sql  = sql.replace("#{battery_no}",String.valueOf(t.getBatteryNo()));
 		sql  = sql.replace("#{mac}",String.valueOf(t.getMac()));
 		sql  = sql.replace("#{wifi_mac}",String.valueOf(t.getWifiMac()));
 		sql  = sql.replace("#{purchase_date}",String.valueOf(t.getPurchaseDate()));
 		sql  = sql.replace("#{warranty_period}",String.valueOf(t.getWarrantyPeriod()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{remark1}",String.valueOf(t.getRemark1()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{remark2}",String.valueOf(t.getRemark2()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		sql  = sql.replace("#{storehouse}",String.valueOf(t.getStorehouse()));
 		sql  = sql.replace("#{update_time}",String.valueOf(t.getUpdateTime()));
 		sql  = sql.replace("#{operation}",String.valueOf(t.getOperation()));
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
					javaInter.execute("rms_asset_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public Serializable save(AssetEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate2(AssetEntity entity, String dateFormatId) throws Exception {
		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity,dateFormatId);
		
	}

	@Override
	public void saveOrUpdate3(AssetEntity entity, String dateFormatId) throws Exception {
		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus3(entity,dateFormatId);
		
	}

	private void doUpdateBus3(AssetEntity entity, String dateFormatId) {
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	//-----------------java增强 start---------------------------
	 	AssetHistoryEntity assetHistory = new AssetHistoryEntity();
		assetHistory.setOperation("1");//修改操作
		TSUser tSUser = ResourceUtil.getSessionUser();
		if(tSUser != null &&StringUtils.isNotEmpty(tSUser.getRealName())) {
			String sql = "select  t.typecode, t.typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='user' and t.typename = ?";
			Map<String,Object> userId =  this.findOneForJdbc(sql, tSUser.getRealName());
			assetHistory.setUpdatedBy(userId!=null?(String) userId.get("typecode"):tSUser.getRealName());//操作人-就是当前登录人
		}
		assetHistory.setCreatedBy(tSUser.getId());
		assetHistory.setAssetId(entity.getAssetId());
		assetHistory.setLastUpdateTime(new Date());//操作日期
		assetHistory.setEmployeeId(entity.getEmployeeId());
		super.save(assetHistory);
	 	//-----------------java增强 end-----------------------------
		
	}
}
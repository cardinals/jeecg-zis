package com.rms.contract.service.impl;
import com.rms.contract.service.ContractServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.contract.entity.ContractEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import java.math.BigDecimal;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("contractService")
@Transactional
public class ContractServiceImpl extends CommonServiceImpl implements ContractServiceI {

	
 	public void delete(ContractEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ContractEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ContractEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ContractEntity t) throws Exception{
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
	private void doUpdateBus(ContractEntity t) throws Exception{
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
	private void doDelBus(ContractEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ContractEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("contract_id", t.getContractId());
		map.put("contract_no", t.getContractNo());
		map.put("name", t.getName());
		map.put("description", t.getDescription());
		map.put("vendor", t.getVendor());
		map.put("budget_year", t.getBudgetYear());
		map.put("total_amount", t.getTotalAmount());
		map.put("paid_amount", t.getPaidAmount());
		map.put("contracted_by", t.getContractedBy());
		map.put("contract_date", t.getContractDate());
		map.put("expire_date", t.getExpireDate());
		map.put("attachments", t.getAttachments());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("vendor_name", t.getVendorName());
		map.put("remark1", t.getRemark1());
		map.put("remark2", t.getRemark2());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		map.put("agent", t.getAgent());
		map.put("contract_file", t.getContractFile());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ContractEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{contract_no}",String.valueOf(t.getContractNo()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{vendor}",String.valueOf(t.getVendor()));
 		sql  = sql.replace("#{budget_year}",String.valueOf(t.getBudgetYear()));
 		sql  = sql.replace("#{total_amount}",String.valueOf(t.getTotalAmount()));
 		sql  = sql.replace("#{paid_amount}",String.valueOf(t.getPaidAmount()));
 		sql  = sql.replace("#{contracted_by}",String.valueOf(t.getContractedBy()));
 		sql  = sql.replace("#{contract_date}",String.valueOf(t.getContractDate()));
 		sql  = sql.replace("#{expire_date}",String.valueOf(t.getExpireDate()));
 		sql  = sql.replace("#{attachments}",String.valueOf(t.getAttachments()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{vendor_name}",String.valueOf(t.getVendorName()));
 		sql  = sql.replace("#{remark1}",String.valueOf(t.getRemark1()));
 		sql  = sql.replace("#{remark2}",String.valueOf(t.getRemark2()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		sql  = sql.replace("#{agent}",String.valueOf(t.getAgent()));
 		sql  = sql.replace("#{contract_file}",String.valueOf(t.getContractFile()));
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
					javaInter.execute("rms_contract_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public BigDecimal getContractId(String currentDate) {
		 String maxField = "contract_id";
		 String tableName = "rms_contract_jeecg";
		 //String sql = "select MAX(maxField) as primary_key from tableName   where  maxField >"+currentDate+"000"+" and maxField <="+currentDate+"999";
		 StringBuffer sqlBuffer = new StringBuffer();
		 sqlBuffer.append("select MAX(").append(maxField).append(") as primary_key from ").append(tableName)
		 																			.append(" where ")
		 																			.append(maxField)
		 																			.append(">").append(currentDate)
		 																			.append("000 and ").append(maxField)
		 																			.append(" <= ").append(currentDate)
		 																			.append("999");
		 Map<String, Object>  maps = this.findOneForJdbc(sqlBuffer.toString(),null);
		 if(maps.get("primary_key")!=null) {
			 String value = (String) maps.get("primary_key");
			 BigDecimal deciVal = new BigDecimal(value);
			 return deciVal;
		 }
		 
	         return new BigDecimal(currentDate+"000");
	}
}
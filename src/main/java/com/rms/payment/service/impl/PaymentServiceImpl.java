package com.rms.payment.service.impl;
import com.rms.payment.service.PaymentServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.rms.contract.entity.ContractEntity;
import com.rms.payment.entity.PaymentEntity;
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

@Service("paymentService")
@Transactional
public class PaymentServiceImpl extends CommonServiceImpl implements PaymentServiceI {

	
 	public void delete(PaymentEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PaymentEntity entity,String contract_id) throws Exception{
 		Serializable t = super.save(entity);
 		String paymentAmount = entity.getPaymentAmount();//付款金额
 		
 		BigDecimal paymentAmountD = new BigDecimal(paymentAmount);
 		//执行新增操作增强业务
 		if(StringUtil.isNotEmpty(contract_id)) {
 			ContractEntity contractEntity = this.get(ContractEntity.class, contract_id);
 			if(contractEntity != null) {
 				String paidAmount = contractEntity.getPaidAmount();//已付款
 				BigDecimal paidAmountD = new BigDecimal(paidAmount==null?"0.00":paidAmount);
 				if(paymentAmountD!=null) {
 					//day_fee = day_fee.add( new BigDecimal("9.00"));
 					paidAmountD = paidAmountD.add(paymentAmountD);
 					paidAmount = paidAmountD.toString();
 					contractEntity.setPaidAmount(paidAmount);
 				}
 			}
 		
 		}
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public Serializable save(PaymentEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PaymentEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PaymentEntity t) throws Exception{
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
	private void doUpdateBus(PaymentEntity t) throws Exception{
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
	private void doDelBus(PaymentEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PaymentEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("payment_id", t.getPaymentId());
		map.put("contract_id", t.getContractId());
		map.put("contract_name", t.getContractName());
		map.put("reimburse_no", t.getReimburseNo());
		map.put("description", t.getDescription());
		map.put("paid_by", t.getPaidBy());
		map.put("payment_amount", t.getPaymentAmount());
		map.put("payment_date", t.getPaymentDate());
		map.put("create_time", t.getCreateTime());
		map.put("last_update_time", t.getLastUpdateTime());
		map.put("isreimbursed", t.getIsreimbursed());
		map.put("reimburse_amount", t.getReimburseAmount());
		map.put("created_by", t.getCreatedBy());
		map.put("updated_by", t.getUpdatedBy());
		map.put("id", t.getId());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,PaymentEntity t){
 		sql  = sql.replace("#{payment_id}",String.valueOf(t.getPaymentId()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{contract_name}",String.valueOf(t.getContractName()));
 		sql  = sql.replace("#{reimburse_no}",String.valueOf(t.getReimburseNo()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{paid_by}",String.valueOf(t.getPaidBy()));
 		sql  = sql.replace("#{payment_amount}",String.valueOf(t.getPaymentAmount()));
 		sql  = sql.replace("#{payment_date}",String.valueOf(t.getPaymentDate()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{isreimbursed}",String.valueOf(t.getIsreimbursed()));
 		sql  = sql.replace("#{reimburse_amount}",String.valueOf(t.getReimburseAmount()));
 		sql  = sql.replace("#{created_by}",String.valueOf(t.getCreatedBy()));
 		sql  = sql.replace("#{updated_by}",String.valueOf(t.getUpdatedBy()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
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
					javaInter.execute("rms_payment_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
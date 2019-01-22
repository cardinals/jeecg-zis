package com.jeecg.bankaccountemp.service.impl;
import com.jeecg.bankaccountemp.service.BankAccountempServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.bankaccountemp.entity.BankAccountempEntity;
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

@Service("bankAccountempService")
@Transactional
public class BankAccountempServiceImpl extends CommonServiceImpl implements BankAccountempServiceI {

	
 	public void delete(BankAccountempEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BankAccountempEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BankAccountempEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BankAccountempEntity t) throws Exception{
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
	private void doUpdateBus(BankAccountempEntity t) throws Exception{
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
	private void doDelBus(BankAccountempEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BankAccountempEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("account_number", t.getAccountNumber());
		map.put("acount_name", t.getAcountName());
		map.put("acount_fullname", t.getAcountFullname());
		map.put("acount_shortname", t.getAcountShortname());
		map.put("acount_type", t.getAcountType());
		map.put("kaihu_date", t.getKaihuDate());
		map.put("acount_status", t.getAcountStatus());
		map.put("acount_interest_rate", t.getAcountInterestRate());
		map.put("interest_rate_date", t.getInterestRateDate());
		map.put("is_liushui", t.getIsLiushui());
		map.put("online_product", t.getOnlineProduct());
		map.put("big_zhifu", t.getBigZhifu());
		map.put("is_pre_end", t.getIsPreEnd());
		map.put("contact", t.getContact());
		map.put("contact_type", t.getContactType());
		map.put("line_hanghao", t.getLineHanghao());
		map.put("cundan", t.getCundan());
		map.put("remarks", t.getRemarks());
		map.put("todate", t.getTodate());
		map.put("seal", t.getSeal());
		map.put("acount_abbreve", t.getAcountAbbreve());
		map.put("zhang", t.getZhang());
		map.put("jingban", t.getJingban());
		map.put("contact_addr", t.getContactAddr());
		map.put("zuoji", t.getZuoji());
		map.put("mobile_phone", t.getMobilePhone());
		map.put("fuhe_status", t.getFuheStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BankAccountempEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{account_number}",String.valueOf(t.getAccountNumber()));
 		sql  = sql.replace("#{acount_name}",String.valueOf(t.getAcountName()));
 		sql  = sql.replace("#{acount_fullname}",String.valueOf(t.getAcountFullname()));
 		sql  = sql.replace("#{acount_shortname}",String.valueOf(t.getAcountShortname()));
 		sql  = sql.replace("#{acount_type}",String.valueOf(t.getAcountType()));
 		sql  = sql.replace("#{kaihu_date}",String.valueOf(t.getKaihuDate()));
 		sql  = sql.replace("#{acount_status}",String.valueOf(t.getAcountStatus()));
 		sql  = sql.replace("#{acount_interest_rate}",String.valueOf(t.getAcountInterestRate()));
 		sql  = sql.replace("#{interest_rate_date}",String.valueOf(t.getInterestRateDate()));
 		sql  = sql.replace("#{is_liushui}",String.valueOf(t.getIsLiushui()));
 		sql  = sql.replace("#{online_product}",String.valueOf(t.getOnlineProduct()));
 		sql  = sql.replace("#{big_zhifu}",String.valueOf(t.getBigZhifu()));
 		sql  = sql.replace("#{is_pre_end}",String.valueOf(t.getIsPreEnd()));
 		sql  = sql.replace("#{contact}",String.valueOf(t.getContact()));
 		sql  = sql.replace("#{contact_type}",String.valueOf(t.getContactType()));
 		sql  = sql.replace("#{line_hanghao}",String.valueOf(t.getLineHanghao()));
 		sql  = sql.replace("#{cundan}",String.valueOf(t.getCundan()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{todate}",String.valueOf(t.getTodate()));
 		sql  = sql.replace("#{seal}",String.valueOf(t.getSeal()));
 		sql  = sql.replace("#{acount_abbreve}",String.valueOf(t.getAcountAbbreve()));
 		sql  = sql.replace("#{zhang}",String.valueOf(t.getZhang()));
 		sql  = sql.replace("#{jingban}",String.valueOf(t.getJingban()));
 		sql  = sql.replace("#{contact_addr}",String.valueOf(t.getContactAddr()));
 		sql  = sql.replace("#{zuoji}",String.valueOf(t.getZuoji()));
 		sql  = sql.replace("#{mobile_phone}",String.valueOf(t.getMobilePhone()));
 		sql  = sql.replace("#{fuhe_status}",String.valueOf(t.getFuheStatus()));
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
					javaInter.execute("bank_accountemp",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
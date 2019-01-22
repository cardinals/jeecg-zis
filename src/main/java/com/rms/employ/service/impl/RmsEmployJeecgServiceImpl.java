package com.rms.employ.service.impl;
import com.rms.employ.service.RmsEmployJeecgServiceI;

import generator.pas.onstaff.entity.PasOnStaffEntity;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.rms.employ.entity.RmsEmployJeecgEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import java.math.BigDecimal;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("rmsEmployJeecgService")
@Transactional
public class RmsEmployJeecgServiceImpl extends CommonServiceImpl implements RmsEmployJeecgServiceI {

	
 	public void delete(RmsEmployJeecgEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(RmsEmployJeecgEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(RmsEmployJeecgEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(RmsEmployJeecgEntity t) throws Exception{
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
	private void doUpdateBus(RmsEmployJeecgEntity t) throws Exception{
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
	private void doDelBus(RmsEmployJeecgEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(RmsEmployJeecgEntity t){
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
		map.put("bpm_status", t.getBpmStatus());
		map.put("employ_id", t.getEmployId());
		map.put("employ_no", t.getEmployNo());
		map.put("employ_name", t.getEmployName());
		map.put("department_id", t.getDepartmentId());
		map.put("enable", t.getEnable());
		map.put("emali", t.getEmali());
		map.put("status", t.getStatus());
		map.put("asset_id", t.getAssetId());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,RmsEmployJeecgEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{employ_id}",String.valueOf(t.getEmployId()));
 		sql  = sql.replace("#{employ_no}",String.valueOf(t.getEmployNo()));
 		sql  = sql.replace("#{employ_name}",String.valueOf(t.getEmployName()));
 		sql  = sql.replace("#{department_id}",String.valueOf(t.getDepartmentId()));
 		sql  = sql.replace("#{enable}",String.valueOf(t.getEnable()));
 		sql  = sql.replace("#{emali}",String.valueOf(t.getEmali()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{asset_id}",String.valueOf(t.getAssetId()));
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
					javaInter.execute("rms_employ_jeecg",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
	@Override
	public List<RmsEmployJeecgEntity> getEmployeeDataGridList(List<Map<String, Object>> maps) {
		List<RmsEmployJeecgEntity> dataGridResult = new ArrayList<RmsEmployJeecgEntity>();
		for (Map<String, Object> map : maps) {
			RmsEmployJeecgEntity employ = new RmsEmployJeecgEntity();
			String bianhao = (String) map.get("code");//员工编号	
			String employName = (String) map.get("name");//姓名
			//所属部门
			String orgname = (String) map.get("orgname");//姓名
			//是否在职  
			BigDecimal isEnable = (BigDecimal) map.get("is_enable");
			//邮箱
			String email = (String) map.get("ext_attr_2");
			
			employ.setEmployNo(bianhao);
			employ.setEmployName(employName);
			employ.setDepartmentId(orgname);
			employ.setEnable("1".equals(isEnable.toString())?"在职":"离职");
			employ.setEmali(email);
			dataGridResult.add(employ);
		}
		return dataGridResult;
	}
}
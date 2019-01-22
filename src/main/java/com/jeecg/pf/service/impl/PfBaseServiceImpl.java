package com.jeecg.pf.service.impl;
import com.jeecg.pf.service.PfBaseServiceI;
import com.jeecg.pfbamana.entity.PfBaseManagerEntity;
import com.jeecg.pfhismanager.entity.PfHisManagerEntity;
import com.jeecg.pfjijincode.entity.PfJijinCodeEntity;

import org.apache.fop.render.ps.PSTextElementBridge;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.jeecg.mana.entity.ManagEntity;
import com.jeecg.pf.entity.PfBaseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("pfBaseService")
@Transactional
public class PfBaseServiceImpl extends CommonServiceImpl implements PfBaseServiceI {

	
 	public void delete(PfBaseEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PfBaseEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PfBaseEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PfBaseEntity t) throws Exception{
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
	private void doUpdateBus(PfBaseEntity t) throws Exception{
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
	private void doDelBus(PfBaseEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PfBaseEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("name", t.getName());
		map.put("short_name", t.getShortName());
		map.put("esta_date", t.getEstaDate());
		map.put("tuoguan_person", t.getTuoguanPerson());
		map.put("jijin_code", t.getJijinCode());
		map.put("pifu_date", t.getPifuDate());
		map.put("jijin_manager", t.getJijinManager());
		map.put("product_state", t.getProductState());
		map.put("jijin_manager_his", t.getJijinManagerHis());
		map.put("found_scale", t.getFoundScale());
		map.put("faxing_date", t.getFaxingDate());
		map.put("faxing_dateto", t.getFaxingDateto());
		map.put("law_office", t.getLawOffice());
		map.put("futures_company", t.getFuturesCompany());
		map.put("is_dingzhi", t.getIsDingzhi());
		map.put("risk_level", t.getRiskLevel());
		map.put("remarks", t.getRemarks());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,PfBaseEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{short_name}",String.valueOf(t.getShortName()));
 		sql  = sql.replace("#{esta_date}",String.valueOf(t.getEstaDate()));
 		sql  = sql.replace("#{tuoguan_person}",String.valueOf(t.getTuoguanPerson()));
 		sql  = sql.replace("#{jijin_code}",String.valueOf(t.getJijinCode()));
 		sql  = sql.replace("#{pifu_date}",String.valueOf(t.getPifuDate()));
 		sql  = sql.replace("#{jijin_manager}",String.valueOf(t.getJijinManager()));
 		sql  = sql.replace("#{product_state}",String.valueOf(t.getProductState()));
 		sql  = sql.replace("#{jijin_manager_his}",String.valueOf(t.getJijinManagerHis()));
 		sql  = sql.replace("#{found_scale}",String.valueOf(t.getFoundScale()));
 		sql  = sql.replace("#{faxing_date}",String.valueOf(t.getFaxingDate()));
 		sql  = sql.replace("#{faxing_dateto}",String.valueOf(t.getFaxingDateto()));
 		sql  = sql.replace("#{law_office}",String.valueOf(t.getLawOffice()));
 		sql  = sql.replace("#{futures_company}",String.valueOf(t.getFuturesCompany()));
 		sql  = sql.replace("#{is_dingzhi}",String.valueOf(t.getIsDingzhi()));
 		sql  = sql.replace("#{risk_level}",String.valueOf(t.getRiskLevel()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
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
					javaInter.execute("pf_base",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
	@Override
	public void addMain(PfBaseEntity pfBase, List<PfBaseManagerEntity> manas, List<PfJijinCodeEntity> codess) {
		//1.保存主表信息
		try {
			this.save(pfBase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2.保存附表1信息-基金经理
		StringBuffer sb = new StringBuffer();
		for(PfBaseManagerEntity mans:manas) {
			if(!"".equals(mans.getName())) {
				mans.setForeignKey(pfBase.getId());
				this.save(mans);
				//保存到基金经理 历史表中
				PfHisManagerEntity pme = new PfHisManagerEntity();
				pme.setId(mans.getId());
				pme.setName(mans.getName());
				pme.setStartTime(new Date());//开始时间
				pme.setForeignKey(pfBase.getId());//外键
				this.save(pme);
				sb.append(mans.getName()).append(",");
			}
		}
		
		if(sb.length()>0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		pfBase.setJijinManager(sb.toString());
		
		//2.保存附表2信息
		StringBuffer codes = new StringBuffer();
		for(PfJijinCodeEntity code:codess) {
			if(!"".equals(code.getCode())) {
				code.setForeignKey(pfBase.getId());
				this.save(code);
				codes.append(code.getCode()).append(",");
			}
		}
		if(codes.length()>0) {
			codes.deleteCharAt(codes.length() - 1);
		}
		pfBase.setJijinCode(codes.toString());
		try {
			this.saveOrUpdate(pfBase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMain(PfBaseEntity t, List<PfBaseManagerEntity> manas, List<PfJijinCodeEntity> codess) {
		
		//1.更改主表
		try {
			this.saveOrUpdate(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2.查询历史附表信息
		List<PfBaseManagerEntity> manasHis = this.findByProperty(PfBaseManagerEntity.class, "foreignKey", t.getId());
		List<PfJijinCodeEntity> codesHis = this.findByProperty(PfJijinCodeEntity.class, "foreignKey", t.getId());
		//在删除附表之前，更新基金经理的历史表信息
		String hql = "from PfHisManagerEntity where foreignKey = ? and emdTime is NULL";
		List<PfHisManagerEntity> manaHi = this.findHql(hql, t.getId());
		String[] manaDS = new String[manaHi.size()];//数据库中的数据
		String[] manaNew = new String[manas.size()];//页面中的数据
		
		int num = 0;
		for (PfHisManagerEntity manaD : manaHi) {
			manaDS[num] = manaD.getName();
			if(num<manaHi.size()-1)
				num++;
		}
		int num2 =0;
		for (PfBaseManagerEntity mana : manas) {
			manaNew[num2] = mana.getName();
			if(num2<manas.size()-1)
				num2++;
		}
		
		// 1).在数据库中存在的数据且 endTime为null,该数据不在表单提交上的数据中，
				  //说明是需要更新的数据，给endTime赋值
		int  length = manaNew.length;
		System.out.println(length);
		
		//如果表单没有填写基金经理
		if("".equals(manaNew[0])) {
			for (PfHisManagerEntity manaD : manaHi) {
					manaD.setEmdTime(new Date());
			}
		}
		if(!"".equals(manaNew[0])) {
			for (PfHisManagerEntity manaD : manaHi) {
				int index = Arrays.binarySearch(manaNew,manaD.getName());
				if(index<0) {
					manaD.setEmdTime(new Date());
				}
			}
		}
		
		// 2).页面提交上的数据，但是数据库中没有，说明是新增的数据
		for (PfBaseManagerEntity mana : manas) {
			if(!"".equals(mana.getName())) {
				int index = Arrays.binarySearch(manaDS,mana.getName());
				if(index<0) {
					//保存到基金经理 历史表中
					PfHisManagerEntity pme = new PfHisManagerEntity();
					pme.setId(mana.getId());
					pme.setName(mana.getName());
					pme.setStartTime(new Date());//开始时间
					pme.setForeignKey(t.getId());//外键
					this.save(pme);
				}
			}
		}
		
		//3.删除附表之前的信息
		if(manasHis.size()>0) {
			for (PfBaseManagerEntity managEntity : manasHis) {
				this.delete(managEntity);
			}
		}
		if(codesHis.size()>0) {
			for (PfJijinCodeEntity codeEntity : codesHis) {
				this.delete(codeEntity);
			}
		}
		
		//4.添加附表新增的信息
		StringBuffer sb = new StringBuffer();
		for (PfBaseManagerEntity managEntity : manas) {
			String name = managEntity.getName();
			if(StringUtil.isEmpty(name))
				continue;
			managEntity.setForeignKey(t.getId());
			this.save(managEntity);
			sb.append(name).append(",");
		}
		if(sb.length()>0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		t.setJijinManager(sb.toString());
		
		StringBuffer codes = new StringBuffer();
		for (PfJijinCodeEntity code : codess) {
			String codeName = code.getCode();
			if(StringUtil.isEmpty(codeName))
				continue;
			code.setForeignKey(t.getId());
			this.save(code);
			codes.append(codeName).append(",");
		}
		
		System.out.println("codes.length()===="+codes.length());
		if(codes.length()>0) {
			codes.deleteCharAt(codes.length() - 1);
		}
		
		
		t.setJijinCode(codes.toString());
		try {
			this.saveOrUpdate(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveByExcel(List<PfBaseEntity> listPfBaseEntitys) {
		List<String> jinglis = new ArrayList<String>();
		List<String> codes = new ArrayList<String>();
		
		
		for (PfBaseEntity pfBase : listPfBaseEntitys) {
			try {
				this.save(pfBase);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String jings = pfBase.getJijinManager();//基金经理们
			String[] jings2 = jings.split(",");
			
			for (int i = 0; i < jings2.length; i++) {
				PfBaseManagerEntity mans = new PfBaseManagerEntity();
				mans.setForeignKey(pfBase.getId());
				mans.setName(jings2[i]);
				this.save(mans);
				//保存到基金经理 历史表中
				PfHisManagerEntity pme = new PfHisManagerEntity();
				pme.setId(mans.getId());
				pme.setName(mans.getName());
				pme.setStartTime(new Date());//开始时间
				pme.setForeignKey(pfBase.getId());//外键
				this.save(pme);
				
			}
			
			String codess = pfBase.getJijinCode();//基金代码们
			String[] codess2 = codess.split(",");
			
			for (int i = 0; i < codess2.length; i++) {
				
				PfJijinCodeEntity mans = new PfJijinCodeEntity();
				mans.setForeignKey(pfBase.getId());
				mans.setCode(codess2[i]);
				this.save(mans);
				
			}
		}
	}
}
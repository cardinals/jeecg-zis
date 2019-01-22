package com.jeecg.feeroutine.service.impl;
import com.jeecg.feeroutine.service.FeeRoutineServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.feeroutine.entity.FeeRoutineEntity;
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

@Service("feeRoutineService")
@Transactional
public class FeeRoutineServiceImpl extends CommonServiceImpl implements FeeRoutineServiceI {

	
 	public void delete(FeeRoutineEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(FeeRoutineEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FeeRoutineEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	/**
	 * 自定义按钮-[复制一行]业务处理
	 * @param id
	 * @return
	 */
	 public void doLine_copyBus(FeeRoutineEntity t) throws Exception{
	 	//-----------------sql增强 start----------------------------
	 	//sql增强第1条
	 	String sqlEnhance_1 ="insert into fee_routine values('#{UUID}','#{id}','#{fee_date}','#{amount}','#{payee}','#{description}','#{fee_type}','#{fee_xingzhi}','#{fee_fapiao}','#{fee_jingbanren}')";
	 	this.executeSql(replaceVal(sqlEnhance_1,t));
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
	 }
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(FeeRoutineEntity t) throws Exception{
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
	private void doUpdateBus(FeeRoutineEntity t) throws Exception{
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
	private void doDelBus(FeeRoutineEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(FeeRoutineEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("fee_date", t.getFeeDate());
		map.put("amount", t.getAmount());
		map.put("payee", t.getPayee());
		map.put("description", t.getDescription());
		map.put("fee_type", t.getFeeType());
		map.put("fee_xingzhi", t.getFeeXingzhi());
		map.put("fee_fapiao", t.getFeeFapiao());
		map.put("fee_jingbanren", t.getFeeJingbanren());
		map.put("updateby", t.getUpdateby());
		map.put("updatedate", t.getUpdatedate());
		map.put("updatename", t.getUpdatename());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,FeeRoutineEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{fee_date}",String.valueOf(t.getFeeDate()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{payee}",String.valueOf(t.getPayee()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{fee_type}",String.valueOf(t.getFeeType()));
 		sql  = sql.replace("#{fee_xingzhi}",String.valueOf(t.getFeeXingzhi()));
 		sql  = sql.replace("#{fee_fapiao}",String.valueOf(t.getFeeFapiao()));
 		sql  = sql.replace("#{fee_jingbanren}",String.valueOf(t.getFeeJingbanren()));
 		sql  = sql.replace("#{updateby}",String.valueOf(t.getUpdateby()));
 		sql  = sql.replace("#{updatedate}",String.valueOf(t.getUpdatedate()));
 		sql  = sql.replace("#{updatename}",String.valueOf(t.getUpdatename()));
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
					javaInter.execute("fee_routine",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
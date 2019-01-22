package com.jeecg.weekrep.service.impl;
import com.jeecg.weekrep.service.OrgptFucmodWeekrepServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.jeecg.weekrep.entity.OrgptFucmodCustomerEntity;
import com.jeecg.weekrep.entity.OrgptFucmodWeekrepEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("orgptFucmodWeekrepService")
@Transactional
public class OrgptFucmodWeekrepServiceImpl extends CommonServiceImpl implements OrgptFucmodWeekrepServiceI {
	private final String dbKey = "ORACLE";
	private final String DbkeySql="select a.id,"
          +" a.writername, "
          +" a.modifytime, "
          +"  a.orgcustfullname, "
           +"  b.orgcustomertype1, "
           +"  b.orgcustomertype2, "
           +"  a.accessflag, "
           +"  a.accesstype, "
          +"  a.department, "
          +"   a.djperson, "
          +"   a.djpersonjob, "
          +"   a.investsituation, "
           +"  addr.vc_regaddress regaddr, "
           +"  nvl(hospub.f_lastasset,'0') publicshares, "
           +"  nvl(hospriv.f_lastasset,'0') privateshares "
       +" from orgpt_fucmod_custinfo a "
       +" left join orgpt_fucmod_customer b "
       +" on a.orgcustfullname = b.orgcustomername "
      +"  left join (    "
       +"     select m.vc_customname,m.vc_regaddress from ( "
       +"       select row_number() over(partition by t.vc_customname  order by t.vc_customname) rn, "
        +"      t.vc_customname, t.vc_regaddress, dt.vc_address "
         +"           from ZRDS3.tcustinfo t "
         +"           left join ZRDS3.taccoinfo dt "
         +"             on t.vc_custno = dt.vc_custno "
         +"          where t.c_custtype = '0' )m "
          +"         where m.rn =1 "
      +"  ) addr "
      +"  on a.orgcustfullname = addr.vc_customname "
      +" left join ( "
      +"   select f_lastasset,querydate,c_custname from  ( "
      +"   select sum(t.f_lastasset) f_lastasset,to_char(t.d_startdate,'yyyy-mm-dd') querydate,tv.c_custname,tf.c_raisetype "
        +"  from customer201706.tcustoffer t, "
       +"   customer201706.tcustomerinfo tv, "
       +"   customer201706.tfundinfo tf "
       +"   where t.c_custno=tv.c_custno "
       +"   and t.c_fundcode=tf.c_fundcode  "
       +"   and tv.c_custtype='0'  "
       +"   group by tv.c_custname,t.d_startdate,tf.c_raisetype "
       
       +"  ) t "
       +"  where t.c_raisetype = '1' "
     +"  ) hospub on a.orgcustfullname = hospub.c_custname  "
       
      +"   and hospub.querydate = to_char(sysdate - 1 ,'yyyy-mm-dd') "
       +"   left join ( "
       +"   select f_lastasset,querydate,c_custname from  ( "
        
       +"   select sum(t.f_lastasset) f_lastasset,to_char(t.d_startdate,'yyyy-mm-dd') querydate,tv.c_custname,tf.c_raisetype "
       +"   from customer201706.tcustoffer t, "
        +"  customer201706.tcustomerinfo tv, "
        +"  customer201706.tfundinfo tf "
        +"  where t.c_custno=tv.c_custno "
        +"  and t.c_fundcode=tf.c_fundcode  "
        +"  and tv.c_custtype='0'  "
        +"  group by tv.c_custname,t.d_startdate,tf.c_raisetype "
        
		+"    ) t "
		+"  where t.c_raisetype = '2' "
        
   		+"   ) hospriv on a.orgcustfullname = hospriv.c_custname  "
      
   		+"     and hospriv.querydate = to_char(sysdate - 1 ,'yyyy-mm-dd') "
       
   		+"     where a.writername= ? and a.orgcustfullname= ? ";
     
	
 	public void delete(OrgptFucmodWeekrepEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(OrgptFucmodWeekrepEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(OrgptFucmodWeekrepEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(OrgptFucmodWeekrepEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	/*//sql增强-自動生成的，不符合要求
	 	String sqlEnhance_1 ="insert 	orgpt_fucmod_custinfo(id,writername,modifytime,orgcustfullname,accessflag,accesstype,department,djperson,djpersonjob,investsituation)                       values('#{UUID}','#{staffname}','#{update_date}','#{targetorginwk}','#{accessflag}','#{accesstype}','#{department}','#{djperson}','#{djpersonjob}','#{investsituation}')";
	 	this.executeSql(replaceVal(sqlEnhance_1,t));
	 	*/
		
	 	Map<String,String> paramsMap = new HashMap<String,String>();
	 	//1. 根据orgcustfullname 查询customertype1、customertype2
	 	String orgcustFullName = t.getTargetorginwk();
	 	OrgptFucmodCustomerEntity customerEntity = this.findUniqueByProperty(OrgptFucmodCustomerEntity.class, "orgcustomername", orgcustFullName);
	 	String customertype1 = customerEntity.getOrgcustomertype1();
	 	String customertype2 = customerEntity.getOrgcustomertype2();
	 	String staffName = t.getStaffname();
	 	//2.根据dbkey查询非系统库的数据
	 	List<Map<String,Object>> lists = DynamicDBUtil.findList(dbKey, DbkeySql,staffName,orgcustFullName);
	 	int PUBLICSHARES = 0;//公募數量
	 	int PRIVATESHARES  = 0;//私募數量
	 	if(lists!=null&&lists.size()>0){
	 		PUBLICSHARES = Integer.parseInt(lists.get(0).get("PRIVATESHARES").toString());
		 	PRIVATESHARES  = Integer.parseInt(lists.get(0).get("PRIVATESHARES").toString());
	 	}
	 	paramsMap.put("customertype1", customertype1);
	 	paramsMap.put("customertype2", customertype2);
	 	paramsMap.put("PUBLICSHARES", String.valueOf(PUBLICSHARES));
	 	paramsMap.put("PRIVATESHARES",String.valueOf(PRIVATESHARES));
	 	//sql增强
	 	String sqlEnhance_1 ="insert orgpt_fucmod_custinfo(id,writername,modifytime,orgcustfullname,accessflag,accesstype,department,djperson,djpersonjob,investsituation,customertype1,customertype2,publicshares,privateshares)   values('#{UUID}','#{staffname}','#{update_date}','#{targetorginwk}','#{accessflag}','#{accesstype}','#{department}','#{djperson}','#{djpersonjob}','#{investsituation}','#{customertype1}','#{customertype2}','#{publicshares}','#{privateshares}')";
	 	this.executeSql(replaceValEnhance(sqlEnhance_1,t,paramsMap));
	 	//-----------------sql增强 end------------------------------
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(OrgptFucmodWeekrepEntity t) throws Exception{
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
	private void doDelBus(OrgptFucmodWeekrepEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(OrgptFucmodWeekrepEntity t){
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
		map.put("staffname", t.getStaffname());
		map.put("visitdate", t.getVisitdate());
		map.put("targetorginwk", t.getTargetorginwk());
		map.put("accessflag", t.getAccessflag());
		map.put("accesstype", t.getAccesstype());
		map.put("department", t.getDepartment());
		map.put("djperson", t.getDjperson());
		map.put("djpersonjob", t.getDjpersonjob());
		map.put("cooperationpoint", t.getCooperationpoint());
		map.put("followsituation", t.getFollowsituation());
		map.put("customerdemand", t.getCustomerdemand());
		map.put("investsituation", t.getInvestsituation());
		map.put("mailsign", t.getMailsign());
		map.put("corestaff", t.getCorestaff());
		map.put("corestaffpos", t.getCorestaffpos());
		map.put("needsupitems", t.getNeedsupitems());
		map.put("remarks", t.getRemarks());
		map.put("procomment", t.getProcomment());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量-增強版本
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceValEnhance(String sql,OrgptFucmodWeekrepEntity t,Map<String,String> paramsMap){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{staffname}",String.valueOf(t.getStaffname()));
 		sql  = sql.replace("#{visitdate}",String.valueOf(t.getVisitdate()));
 		sql  = sql.replace("#{targetorginwk}",String.valueOf(t.getTargetorginwk()));
 		sql  = sql.replace("#{accessflag}",String.valueOf(t.getAccessflag()));
 		sql  = sql.replace("#{accesstype}",String.valueOf(t.getAccesstype()));
 		sql  = sql.replace("#{department}",String.valueOf(t.getDepartment()));
 		sql  = sql.replace("#{djperson}",String.valueOf(t.getDjperson()));
 		sql  = sql.replace("#{djpersonjob}",String.valueOf(t.getDjpersonjob()));
 		sql  = sql.replace("#{cooperationpoint}",String.valueOf(t.getCooperationpoint()));
 		sql  = sql.replace("#{followsituation}",String.valueOf(t.getFollowsituation()));
 		sql  = sql.replace("#{customerdemand}",String.valueOf(t.getCustomerdemand()));
 		sql  = sql.replace("#{investsituation}",String.valueOf(t.getInvestsituation()));
 		sql  = sql.replace("#{mailsign}",String.valueOf(t.getMailsign()));
 		sql  = sql.replace("#{corestaff}",String.valueOf(t.getCorestaff()));
 		sql  = sql.replace("#{corestaffpos}",String.valueOf(t.getCorestaffpos()));
 		sql  = sql.replace("#{needsupitems}",String.valueOf(t.getNeedsupitems()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{procomment}",String.valueOf(t.getProcomment()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		
 		sql  = sql.replace("#{customertype1}",String.valueOf(paramsMap.get("customertype1")));
 		sql  = sql.replace("#{customertype2}",String.valueOf(paramsMap.get("customertype2")));
 		sql  = sql.replace("#{publicshares}",String.valueOf(paramsMap.get("PUBLICSHARES")));
 		sql  = sql.replace("#{privateshares}",String.valueOf(paramsMap.get("PRIVATESHARES")));
 		
 		return sql;
 	}
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,OrgptFucmodWeekrepEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{staffname}",String.valueOf(t.getStaffname()));
 		sql  = sql.replace("#{visitdate}",String.valueOf(t.getVisitdate()));
 		sql  = sql.replace("#{targetorginwk}",String.valueOf(t.getTargetorginwk()));
 		sql  = sql.replace("#{accessflag}",String.valueOf(t.getAccessflag()));
 		sql  = sql.replace("#{accesstype}",String.valueOf(t.getAccesstype()));
 		sql  = sql.replace("#{department}",String.valueOf(t.getDepartment()));
 		sql  = sql.replace("#{djperson}",String.valueOf(t.getDjperson()));
 		sql  = sql.replace("#{djpersonjob}",String.valueOf(t.getDjpersonjob()));
 		sql  = sql.replace("#{cooperationpoint}",String.valueOf(t.getCooperationpoint()));
 		sql  = sql.replace("#{followsituation}",String.valueOf(t.getFollowsituation()));
 		sql  = sql.replace("#{customerdemand}",String.valueOf(t.getCustomerdemand()));
 		sql  = sql.replace("#{investsituation}",String.valueOf(t.getInvestsituation()));
 		sql  = sql.replace("#{mailsign}",String.valueOf(t.getMailsign()));
 		sql  = sql.replace("#{corestaff}",String.valueOf(t.getCorestaff()));
 		sql  = sql.replace("#{corestaffpos}",String.valueOf(t.getCorestaffpos()));
 		sql  = sql.replace("#{needsupitems}",String.valueOf(t.getNeedsupitems()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{procomment}",String.valueOf(t.getProcomment()));
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
					javaInter.execute("orgpt_fucmod_weekrep",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
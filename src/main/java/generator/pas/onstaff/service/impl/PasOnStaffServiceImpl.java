package generator.pas.onstaff.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.util.ArrayUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.onstaff.entity.PasOnStaffEntity;
import generator.pas.onstaff.service.PasOnStaffServiceI;

@Service("pasOnStaffService")
@Transactional
public class PasOnStaffServiceImpl extends CommonServiceImpl implements PasOnStaffServiceI {

	
 	public void delete(PasOnStaffEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PasOnStaffEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PasOnStaffEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PasOnStaffEntity t) throws Exception{
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
	private void doUpdateBus(PasOnStaffEntity t) throws Exception{
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
	private void doDelBus(PasOnStaffEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PasOnStaffEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		//map.put("appraiser", t.getAppraiser());
		map.put("goal_person", t.getGoalPerson());
		map.put("goal_person_dept", t.getGoalPersonDept());
		map.put("professional_skill", t.getProfessionalSkill());
		map.put("coop_eff", t.getCoopEff());
		map.put("coop_atti", t.getCoopAtti());
		map.put("coop_outcome", t.getCoopOutcome());
		map.put("total_score", t.getTotalScore());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,PasOnStaffEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		//sql  = sql.replace("#{appraiser}",String.valueOf(t.getAppraiser()));
 		sql  = sql.replace("#{goal_person}",String.valueOf(t.getGoalPerson()));
 		sql  = sql.replace("#{goal_person_dept}",String.valueOf(t.getGoalPersonDept()));
 		sql  = sql.replace("#{professional_skill}",String.valueOf(t.getProfessionalSkill()));
 		sql  = sql.replace("#{coop_eff}",String.valueOf(t.getCoopEff()));
 		sql  = sql.replace("#{coop_atti}",String.valueOf(t.getCoopAtti()));
 		sql  = sql.replace("#{coop_outcome}",String.valueOf(t.getCoopOutcome()));
 		sql  = sql.replace("#{total_score}",String.valueOf(t.getTotalScore()));
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
					javaInter.execute("pas_on_staff",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public List<Map<String,String>> getAllDeptStaffExceptLoginUser(TSUser loginUser) {
		List<Map<String,String>> rs = new ArrayList<>();
		/**
		 * 此sql 查询当前用户所在部门的所有普通员工(除自己外) 新增附加表数据
		 */
		//先查询当前登录人 在全表中普通角色的部门下的同事
		String hqlQuery = "select * from ( " + 
				"select a.username,a.realname,r.rolecode,d.org_code,d.departname  from  t_s_base_user a " + 
				"left join t_s_user_org uo on a.id = uo.user_id " + 
				"left join t_s_role_user ru on a.id = ru.userid " + 
				"left join t_s_role r on ru.roleid = r.id " + 
				"left join t_s_depart d on uo.org_id = d.id " + 
				"union all " + 
				"select username,realname,rolecode,org_code,depart_name depart_name from pas_mod_attached " + 
				") c " + 
				"where  c.rolecode = 'putong' " + 
				"and c.username = '"+loginUser.getUserName()+"'";
		Session session = getSession();
		Query query = session.createSQLQuery(hqlQuery);
		//本系统中 系统表及附加表中每个用户只能有一个普通用户的角色
	    Object[] rsult = (Object[]) query.uniqueResult();
	    if(rsult != null) {
	    	String orgCode = (String) rsult[3];
	    	//获取该部门下除当前登录人以外所有普通员工
	    	String getPtsByOrgCode =  "select * from ( " + 
					"select a.username,a.realname,r.rolecode,d.org_code,d.departname  from  t_s_base_user a " + 
					"left join t_s_user_org uo on a.id = uo.user_id " + 
					"left join t_s_role_user ru on a.id = ru.userid " + 
					"left join t_s_role r on ru.roleid = r.id " + 
					"left join t_s_depart d on uo.org_id = d.id " + 
					"union all " + 
					"select username,realname,rolecode,org_code,depart_name depart_name from pas_mod_attached " + 
					") c " + 
					"where  c.rolecode = 'putong' " + 
					"and c.org_code = '"+orgCode+"' "+
					"and c.username != '"+loginUser.getUserName()+"' ";
	    	Query query2 = query = session.createSQLQuery(getPtsByOrgCode);
	    	List<Object[]> obj = query2.list();	
	    	if(obj.size() > 0 ) {
	    		for (Object[] object : obj) {
	    			Map<String, String> kv  = new HashMap<String,String>(); 
	    			String username = (String) object[0];//username
	    			String realname = (String) object[1];//realname
	    			String rolecode = (String) object[2];//rolecode
	    			String orgcode = (String) object[3];//orgcode
	    			String departname = (String) object[4];//departname
	    			kv.put("username", username);
	    			kv.put("realname", realname);
	    			kv.put("rolecode", rolecode);
	    			kv.put("orgcode", orgcode);
	    			kv.put("departname", departname);
	    			rs.add(kv);
				}
	    	}
	    }
		//List<String[]> rOkList = new ArrayList<String[]>();
		
		/*for (Object[] obj : objlist) {
			String[] arr = new String[3];
			arr[0]= (String) obj[0];
			arr[1]= (String) obj[1];
			arr[2]= (String) obj[2];
			rOkList.add(arr);
		}*/
		return rs;
	}
	
	
	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_staff中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from  PasOnStaffEntity where appraiser = :loginUserName ";
		Query query = commonDao.getSession().createQuery(hqlGetCount);
		query.setString("loginUserName", loginUser.getUserName());
		int existCount = ((Long) query.uniqueResult()).intValue();
		//True:为开始评分/False:尚未开始评分
		if(existCount > 0 ) {
			result = true;
		}else {
			result = false;
		}
		return result;
	}

	@Override
	public List<PasOnStaffEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser) {
		List<PasOnStaffEntity> dataGridResult = new ArrayList<PasOnStaffEntity>();
		for (Map<String, String> user : listArray) {
			PasOnStaffEntity pasOnStaff = new PasOnStaffEntity();
			pasOnStaff.setId(UUIDGenerator.generate());
			pasOnStaff.setAppraiser(loginUser.getUserName());
			pasOnStaff.setGoalPerson(user.get("realname"));
			pasOnStaff.setGoalPersonDept(user.get("departname"));
			dataGridResult.add(pasOnStaff);
		}
		return dataGridResult;
	}

}
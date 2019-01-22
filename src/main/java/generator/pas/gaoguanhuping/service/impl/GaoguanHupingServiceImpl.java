package generator.pas.gaoguanhuping.service.impl;
import generator.pas.gaoguanhuping.service.GaoguanHupingServiceI;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;

import org.hibernate.Query;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import generator.pas.gaoguanhuping.entity.GaoguanHupingEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("gaoguanHupingService")
@Transactional
public class GaoguanHupingServiceImpl extends CommonServiceImpl implements GaoguanHupingServiceI {

	@Override
	public List<GaoguanHupingEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser) {

		List<GaoguanHupingEntity> dataGridResult = new ArrayList<GaoguanHupingEntity>();
		if(allLeaders!=null) {
			for (Map<String, Object> map : allLeaders) {
				if(loginUser.getRealName().equals(map.get("realname")))
					continue;
				GaoguanHupingEntity pasFuzeLeader = new GaoguanHupingEntity();
				pasFuzeLeader.setId(UUIDGenerator.generate());
				pasFuzeLeader.setAppraiser(loginUser.getUserName());
				pasFuzeLeader.setGoalPerson((String)map.get("realname"));//被评价人
				pasFuzeLeader.setPosition((String)map.get("rolename"));//职位
				dataGridResult.add(pasFuzeLeader);
			}
		}
		return dataGridResult;
	
	}

	/**
	 * ======================获取高管人员=============================
	 */
	@Override
	public List<Map<String, Object>> getAllLeads() {
		String sql = "SELECT bu.realname,r.rolename from t_s_user u "
				+ "LEFT JOIN t_s_base_user bu on bu.id = u.id "
				+ "LEFT JOIN t_s_role_user ru on u.id = ru.userid " 
				+"LEFT JOIN t_s_role r on r.ID = ru.roleid"
				+ " where r.rolecode in (?,?,?,?) "
               
				+"ORDER BY case  when r.rolecode  ='chairman' then 1 "
                +"when r.rolecode  ='president' then 2 "
                +"when r.rolecode  ='vicePresident' then 3 "
                +"when r.rolecode  ='managDirector' then 4 end ";
				
		String roleCode0 = "chairman";//董事长
		String roleCode = "president";//总裁
		String roleCode2 = "vicePresident";//副总裁
		String roleCode3 = "managDirector";//董事总经理
		List<Map<String, Object>>  users = this.findForJdbc(sql, roleCode0,roleCode,roleCode2,roleCode3);
		return users;
	}
	/**
	 * 	评分人范围：督察长、副总裁
	 * 	副总裁（不含评分人本人，不含督察长）
	 */
	@Override
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		//获取副总裁
		String sql ="SELECT u.ID,u.realname,r.rolename  from t_s_base_user u " + 
				"LEFT JOIN t_s_role_user ru on ru.userid = u.ID " + 
				"LEFT JOIN t_s_role r on ru.roleid = r.ID " + 
				"where r.rolecode = 'vicePresident' " +
				" order by case u.realname " +
				" when '曹健' then 1 " +
				" when '向祖荣' then 2 " +
				" when '易海波' then 3 " +
				" when '王启道' then 4 " +
				" end ";
		//注释掉时间为 20181211
		//获取副总裁
				/*String sql ="SELECT u.ID,u.realname,r.rolename  from t_s_base_user u " + 
						"LEFT JOIN t_s_role_user ru on ru.userid = u.ID " + 
						"LEFT JOIN t_s_role r on ru.roleid = r.ID " + 
						"where r.rolecode in ('vicePresident','duchazhang')";*/
				
		
		List<Map<String, Object>>  users = this.findForJdbc(sql);
		return users;
	}
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询 pas_fuze_leader 中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from GaoguanHupingEntity where appraiser = :loginUserName";
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
	
 	public void delete(GaoguanHupingEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(GaoguanHupingEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(GaoguanHupingEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(GaoguanHupingEntity t) throws Exception{
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
	private void doUpdateBus(GaoguanHupingEntity t) throws Exception{
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
	private void doDelBus(GaoguanHupingEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(GaoguanHupingEntity t){
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
		map.put("goal_person", t.getGoalPerson());
		map.put("position", t.getPosition());
		map.put("appraiser", t.getAppraiser());
		map.put("goal_person_dept", t.getGoalPersonDept());
		map.put("yeji", t.getYeji());
		map.put("team_lead", t.getTeamLead());
		map.put("deve_create", t.getDeveCreate());
		map.put("sincerity_hardwork", t.getSincerityHardwork());
		map.put("total_score", t.getTotalScore());
		map.put("confirm_flag", t.getConfirmFlag());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,GaoguanHupingEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{goal_person}",String.valueOf(t.getGoalPerson()));
 		sql  = sql.replace("#{position}",String.valueOf(t.getPosition()));
 		sql  = sql.replace("#{appraiser}",String.valueOf(t.getAppraiser()));
 		sql  = sql.replace("#{goal_person_dept}",String.valueOf(t.getGoalPersonDept()));
 		sql  = sql.replace("#{yeji}",String.valueOf(t.getYeji()));
 		sql  = sql.replace("#{team_lead}",String.valueOf(t.getTeamLead()));
 		sql  = sql.replace("#{deve_create}",String.valueOf(t.getDeveCreate()));
 		sql  = sql.replace("#{sincerity_hardwork}",String.valueOf(t.getSincerityHardwork()));
 		sql  = sql.replace("#{total_score}",String.valueOf(t.getTotalScore()));
 		sql  = sql.replace("#{confirm_flag}",String.valueOf(t.getConfirmFlag()));
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
					javaInter.execute("pas_gaoguan_huping",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
package generator.pas.astfuzeleader.service.impl;
import generator.pas.astfuzeleader.service.AstFuzeLeaderServiceI;
import generator.pas.pasasset.entity.PasAssetEntity;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;

import org.hibernate.Query;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import generator.pas.astfuzeleader.entity.AstFuzeLeaderEntity;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("astFuzeLeaderService")
@Transactional
public class AstFuzeLeaderServiceImpl extends CommonServiceImpl implements AstFuzeLeaderServiceI {

	
 	public void delete(AstFuzeLeaderEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(AstFuzeLeaderEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(AstFuzeLeaderEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(AstFuzeLeaderEntity t) throws Exception{
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
	private void doUpdateBus(AstFuzeLeaderEntity t) throws Exception{
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
	private void doDelBus(AstFuzeLeaderEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(AstFuzeLeaderEntity t){
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
 	public String replaceVal(String sql,AstFuzeLeaderEntity t){
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
					javaInter.execute("ast_fuze_leader",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询 pas_fuze_leader 中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from AstFuzeLeaderEntity where appraiser = :loginUserName";
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
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		List<Map<String, Object>>  users = new ArrayList<Map<String, Object>>();//方法返回结果
		TSDepart  depart = loginUser.getCurrentDepart();
		String currentDepart = depart.getDepartname();
		
		String username = loginUser.getUserName();//登录名称
		String realName = loginUser.getRealName();//实际名称
	 		   String hql = "from PasAssetEntity t where t.name = ? and t.kaoheCode='departFuze'";
	 		   List<PasAssetEntity>  fuze = this.findHql(hql, realName);
	 		   if(fuze!=null && fuze.size()>0) {
	 				Map<String,Object> fenguanMap = new HashMap<String,Object>();
	 				fenguanMap.put("name", "黄志斌");
					fenguanMap.put("position","");
					users.add(fenguanMap);
					return users;
	 		   }
	 		   return null;
	}

	@Override
	public List<AstFuzeLeaderEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser) {
		List<AstFuzeLeaderEntity> dataGridResult = new ArrayList<AstFuzeLeaderEntity>();
		if(allLeaders!=null) {
			for (Map<String, Object> map : allLeaders) {
				if(loginUser.getRealName().equals(map.get("realname")))
					continue;
				AstFuzeLeaderEntity pasFuzeLeader = new AstFuzeLeaderEntity();
				pasFuzeLeader.setId(UUIDGenerator.generate());
				pasFuzeLeader.setAppraiser(loginUser.getUserName());
				pasFuzeLeader.setGoalPerson((String)map.get("name"));//被评价人
				pasFuzeLeader.setPosition((String)map.get("position"));//职位
				//pasFuzeLeader.setGoalPerson((String)map.get("realname"));//被评价人
				//pasFuzeLeader.setPosition((String)map.get("rolename"));//职位
				
				dataGridResult.add(pasFuzeLeader);
			}
		}
		return dataGridResult;
	}
}
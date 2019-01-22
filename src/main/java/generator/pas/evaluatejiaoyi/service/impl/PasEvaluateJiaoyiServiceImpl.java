package generator.pas.evaluatejiaoyi.service.impl;
import generator.pas.evaluatejiaoyi.service.PasEvaluateJiaoyiServiceI;
import generator.pas.iarsindex.entity.PasIarSIndexEntity;

import org.hibernate.Query;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import generator.pas.evaluatejiaoyi.entity.PasEvaluateJiaoyiEntity;
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

@Service("pasEvaluateJiaoyiService")
@Transactional
public class PasEvaluateJiaoyiServiceImpl extends CommonServiceImpl implements PasEvaluateJiaoyiServiceI {

	
 	public void delete(PasEvaluateJiaoyiEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PasEvaluateJiaoyiEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PasEvaluateJiaoyiEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PasEvaluateJiaoyiEntity t) throws Exception{
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
	private void doUpdateBus(PasEvaluateJiaoyiEntity t) throws Exception{
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
	private void doDelBus(PasEvaluateJiaoyiEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PasEvaluateJiaoyiEntity t){
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
		map.put("appraiser", t.getAppraiser());
		map.put("goal_person", t.getGoalPerson());
		map.put("goal_depart", t.getGoalDepart());
		map.put("professional_skill", t.getProfessionalSkill());
		map.put("coop_atti", t.getCoopAtti());
		map.put("jiaoyi_skill", t.getJiaoyiSkill());
		map.put("talk_message", t.getTalkMessage());
		map.put("total_score", t.getTotalScore());
		map.put("comment", t.getComment());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,PasEvaluateJiaoyiEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{appraiser}",String.valueOf(t.getAppraiser()));
 		sql  = sql.replace("#{goal_person}",String.valueOf(t.getGoalPerson()));
 		sql  = sql.replace("#{goal_depart}",String.valueOf(t.getGoalDepart()));
 		sql  = sql.replace("#{professional_skill}",String.valueOf(t.getProfessionalSkill()));
 		sql  = sql.replace("#{coop_atti}",String.valueOf(t.getCoopAtti()));
 		sql  = sql.replace("#{jiaoyi_skill}",String.valueOf(t.getJiaoyiSkill()));
 		sql  = sql.replace("#{talk_message}",String.valueOf(t.getTalkMessage()));
 		sql  = sql.replace("#{total_score}",String.valueOf(t.getTotalScore()));
 		sql  = sql.replace("#{comment}",String.valueOf(t.getComment()));
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
					javaInter.execute("pas_evaluate_jiaoyi",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasEvaluateJiaoyiEntity where appraiser = :loginUserName";
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
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser) {
		//根据当前登录人找到 其对应部门
		String userName = loginUser.getUserName();
		String departName = "";
		if("luojie".equals(userName)) {
			departName = "固收投资部";
		}
		if("huangzhen".equals(userName)) {
			departName = "权益投资部";
		}
		if("zhaofei".equals(userName)) {
			departName = "量化投资部";
		}
		if("tiangang".equals(userName)) {
			departName = "策略投资部";
		}
		if("fushiwei".equals(userName)) {
			departName = "国际业务部";
		}
		if("marongrong".equals(userName)) {
			departName = "专户管理部";
		}
		
			List<PasEvaluateJiaoyiEntity> pasIarSIndexEntitys = new ArrayList<PasEvaluateJiaoyiEntity>();
			PasEvaluateJiaoyiEntity evaluateJiaoyi = new PasEvaluateJiaoyiEntity();
				
			    evaluateJiaoyi.setId(UUIDGenerator.generate());
				evaluateJiaoyi.setAppraiser(userName);//评价人
				evaluateJiaoyi.setAppraiserDept(departName);//评分人部门
				evaluateJiaoyi.setGoalDepart("交易部");//被评价部门
				pasIarSIndexEntitys.add(evaluateJiaoyi);
				
			cq.setResults(pasIarSIndexEntitys);
					
	}
	
	
	
	
}
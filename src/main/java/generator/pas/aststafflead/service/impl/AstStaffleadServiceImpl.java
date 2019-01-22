package generator.pas.aststafflead.service.impl;
import generator.pas.aststafflead.service.AstStaffleadServiceI;
import generator.pas.pasasset.entity.PasAssetEntity;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;

import org.hibernate.Query;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import generator.pas.aststafflead.entity.AstStaffleadEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("astStaffleadService")
@Transactional
public class AstStaffleadServiceImpl extends CommonServiceImpl implements AstStaffleadServiceI {

	//SMSTest
		private final static  String dbKey = "SMSTest";
		private final static String departName = "SELECT t.depart from pas_asset t where t.name = ? ";
	 
	
 	public void delete(AstStaffleadEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(AstStaffleadEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(AstStaffleadEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(AstStaffleadEntity t) throws Exception{
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
	private void doUpdateBus(AstStaffleadEntity t) throws Exception{
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
	private void doDelBus(AstStaffleadEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(AstStaffleadEntity t){
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
		map.put("dept", t.getDept());
		map.put("appraiser", t.getAppraiser());
		map.put("appraiser_dept", t.getAppraiserDept());
		map.put("professional_skill", t.getProfessionalSkill());
		map.put("team_build", t.getTeamBuild());
		map.put("zeren", t.getZeren());
		map.put("quanju", t.getQuanju());
		map.put("total_score", t.getTotalScore());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,AstStaffleadEntity t){
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
 		sql  = sql.replace("#{dept}",String.valueOf(t.getDept()));
 		sql  = sql.replace("#{appraiser}",String.valueOf(t.getAppraiser()));
 		sql  = sql.replace("#{appraiser_dept}",String.valueOf(t.getAppraiserDept()));
 		sql  = sql.replace("#{professional_skill}",String.valueOf(t.getProfessionalSkill()));
 		sql  = sql.replace("#{team_build}",String.valueOf(t.getTeamBuild()));
 		sql  = sql.replace("#{zeren}",String.valueOf(t.getZeren()));
 		sql  = sql.replace("#{quanju}",String.valueOf(t.getQuanju()));
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
					javaInter.execute("pas_ast_stafflead",data);
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
		//查询 pas_staff_lead 中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from AstStaffleadEntity where appraiser = :loginUserName";
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
	public List<TSUser> getDeptLeaderOfLoginUser(TSUser loginUser) {
		List<TSUser> DeptLeader =  new ArrayList<TSUser>();
	 	   List<PasAssetEntity>  pasAsset = this.findByProperty(PasAssetEntity.class, "name", loginUser.getRealName());
	 	   if(pasAsset!=null && pasAsset.size()>0) {
	 		   String departCode = pasAsset.get(0).getDepartCode();//部门名称代码
	 		   String hql = "from PasAssetEntity t where t.departCode = ? and t.kaoheCode='departFuze'";
	 		   List<PasAssetEntity>  fuzes = this.findHql(hql, departCode);
	 		   if(fuzes!=null && fuzes.size()>0) {
		 			 String  fuzeName = fuzes.get(0).getName();
		 			 List<TSUser> tSUser = this.findByProperty(TSUser.class, "realName", fuzeName) ;
			 			 if(tSUser!= null && tSUser.size()>0) {
			 				DeptLeader.add(tSUser.get(0));
			 				return DeptLeader;
		 			}
	 		  }
	 				   
	 	   }
	 	   return null;
	}

	@Override
	public List<AstStaffleadEntity> installDataGridList(List<TSUser> deptLeader, TSUser loginUser) {
		List<AstStaffleadEntity> dataGridResult = new ArrayList<AstStaffleadEntity>();
		TSUser user = this.get(TSUser.class, loginUser.getId());
		
		for (TSUser leader : deptLeader) {
			AstStaffleadEntity pasStaffLead = new AstStaffleadEntity();
			pasStaffLead.setId(UUIDGenerator.generate());
			pasStaffLead.setAppraiser(loginUser.getUserName());//评价人
			pasStaffLead.setGoalPerson(leader.getRealName());//被评价人
			boolean flag = true;
			try {
				List<Map<String,Object>> fuZeDepart = DynamicDBUtil.findList(dbKey, departName, leader.getRealName());
				if(fuZeDepart!=null && fuZeDepart.size()>0) {
					String bumenName = (String)fuZeDepart.get(0).get("depart");
					if(StringUtil.isNotEmpty(bumenName)) {
						pasStaffLead.setDept(bumenName);
						flag = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DynamicDBUtil.closeDBkey2(dbKey);
			}
			if(flag) {
				pasStaffLead.setDept( user != null?user.getUserOrgList().get(0).getTsDepart().getDepartname():"信息技术部");//登录人所在部门
			}
			//评价人所在部门
			pasStaffLead.setAppraiserDept(loginUser.getCurrentDepart().getDepartname());
			dataGridResult.add(pasStaffLead);
		
		}
		return dataGridResult;
	}
}
package generator.pas.passtafflead.service.impl;
import generator.pas.passtafflead.service.PasStaffLeadServiceI;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import generator.pas.fuzefenguan.entity.FuzeFenguanEntity;
import generator.pas.onstaff.entity.PasOnStaffEntity;
import generator.pas.pasasset.entity.PasAssetEntity;
import generator.pas.pasmodattached.entity.PasModAttachedEntity;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;

@Service("pasStaffLeadService")
@Transactional
public class PasStaffLeadServiceImpl extends CommonServiceImpl implements PasStaffLeadServiceI {
	//SMSTest
	private final static  String dbKey = "SMSTest";
	private final static String departName = "SELECT t.depart from pas_asset t where t.name = ? ";
 	
 	public void delete(PasStaffLeadEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PasStaffLeadEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PasStaffLeadEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PasStaffLeadEntity t) throws Exception{
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
	private void doUpdateBus(PasStaffLeadEntity t) throws Exception{
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
	private void doDelBus(PasStaffLeadEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PasStaffLeadEntity t){
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
		map.put("appraiser", t.getAppraiser());
		map.put("dept", t.getDept());
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
 	public String replaceVal(String sql,PasStaffLeadEntity t){
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
 		sql  = sql.replace("#{appraiser}",String.valueOf(t.getAppraiser()));
 		sql  = sql.replace("#{dept}",String.valueOf(t.getDept()));
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
					javaInter.execute("pas_staff_lead",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
 	
 	
 	/**
 	 * 2018-11-17
 	 * 1.专户管理部部门员工分别对许超、马荣荣评分；
 	 * 2.评分人范围：部门负责人以下全体员工
 	 * 3.评分对象：评分人所在部门的部门负责人及协管领导
 	 */
 	@Override
	public List<TSUser> getDeptLeaderOfLoginUser(TSUser loginUser) {
 		List<TSUser> DeptLeader =  new ArrayList<TSUser>();
 		String departName ="专户管理部";//专户管理部的 部门名称
 		String departName2 ="固收投资部";//固收投资部的 部门名称
 		TSDepart currentDepart = loginUser.getCurrentDepart();
 		String username = loginUser.getUserName();
 		
 		/*//1.许超登录
 		if("xuchao".equals(username)) {
 			List<TSUser> tSUser = this.findByProperty(TSUser.class, "userName", "marongrong");//马蓉蓉
 			if(tSUser!= null && tSUser.size()>0) {
 				DeptLeader.add(tSUser.get(0));
 			}
 			return DeptLeader;
 		}
 		//2.罗杰登录
 		if("luojie".equals(username)) {
 			List<TSUser> tSUser = this.findByProperty(TSUser.class, "userName", "huangzhen");//黄震
 			if(tSUser!= null && tSUser.size()>0) {
 				DeptLeader.add(tSUser.get(0));
 			}
 			return DeptLeader;
 		}*/
 		
 		//20181126-start
 	   /*List<PasAssetEntity>  pasAsset = this.findByProperty(PasAssetEntity.class, "name", loginUser.getRealName());
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
 				   
 	   }*/
 		//20181126-end
 		// 1.专户管理部
 		if(departName.equals(currentDepart.getDepartname())) {
 			//1.1获取 许超、马荣荣 
 			List<TSUser> tSUser = this.findByProperty(TSUser.class, "userName", "xuchao");//许超
 			List<TSUser> tSUser2 = this.findByProperty(TSUser.class, "userName", "marongrong");//马蓉蓉
 			if(tSUser!= null && tSUser.size()>0) {
 				DeptLeader.add(tSUser.get(0));
 			}
 			if(tSUser2!= null && tSUser2.size()>0) {
 				DeptLeader.add(tSUser2.get(0));
 			}
 			//1.2 专户部门下的协管领导
 			List<FuzeFenguanEntity> fuzeFenguan = this.findByProperty(FuzeFenguanEntity.class, "departName", departName);
 			if(fuzeFenguan!= null && fuzeFenguan.size()>0) {
 				String realname = fuzeFenguan.get(0).getDepartXieguan();
 				List<TSUser> xieguanUser = this.findByProperty(TSUser.class, "realName", realname);
 				if(xieguanUser!= null && xieguanUser.size()>0) {
 					//DeptLeader.add(xieguanUser.get(0));
 					//this.filterUser(xieguanUser.get(0), DeptLeader);
 				}
 			}
 			return DeptLeader;
 		}
 		
 	// 2.固收投资部
 	 		if(departName2.equals(currentDepart.getDepartname())) {
 	 			//1.1获取 许超、马荣荣 
 	 			
 	 			List<TSUser> tSUser = this.findByProperty(TSUser.class, "userName", "huangzhen");//黄震
 	 			List<TSUser> tSUser2 = this.findByProperty(TSUser.class, "userName", "luojie");//罗杰
 	 			if(tSUser!= null && tSUser.size()>0) {
 	 				DeptLeader.add(tSUser.get(0));
 	 			}
 	 			if(tSUser2!= null && tSUser2.size()>0) {
 	 				DeptLeader.add(tSUser2.get(0));
 	 			}
 	 			//1.2 专户部门下的协管领导
 	 			List<FuzeFenguanEntity> fuzeFenguan = this.findByProperty(FuzeFenguanEntity.class, "departName", departName2);
 	 			if(fuzeFenguan!= null && fuzeFenguan.size()>0) {
 	 				String realname = fuzeFenguan.get(0).getDepartXieguan();
 	 				List<TSUser> xieguanUser = this.findByProperty(TSUser.class, "realName", realname);
 	 				if(xieguanUser!= null && xieguanUser.size()>0) {
 	 					//DeptLeader.add(xieguanUser.get(0));
 	 					//this.filterUser(xieguanUser.get(0), DeptLeader);
 	 				}
 	 			}
 	 			return DeptLeader;
 	 		}
 	 		
 	 	// 3.客服部
 	 		if("客服部".equals(currentDepart.getDepartname())) {
 	 			//1.1代昕、肖佳琦 
 	 			
 	 			List<TSUser> tSUser = this.findByProperty(TSUser.class, "userName", "daixin");//代昕
 	 			List<TSUser> tSUser2 = this.findByProperty(TSUser.class, "userName", "xiaojiaqi");//肖佳琦 
 	 			if(tSUser!= null && tSUser.size()>0) {
 	 				DeptLeader.add(tSUser.get(0));
 	 			}
 	 			if(tSUser2!= null && tSUser2.size()>0) {
 	 				DeptLeader.add(tSUser2.get(0));
 	 			}
 	 			/*//1.2 专户部门下的协管领导
 	 			List<FuzeFenguanEntity> fuzeFenguan = this.findByProperty(FuzeFenguanEntity.class, "departName", departName2);
 	 			if(fuzeFenguan!= null && fuzeFenguan.size()>0) {
 	 				String realname = fuzeFenguan.get(0).getDepartXieguan();
 	 				List<TSUser> xieguanUser = this.findByProperty(TSUser.class, "realName", realname);
 	 				if(xieguanUser!= null && xieguanUser.size()>0) {
 	 					DeptLeader.add(xieguanUser.get(0));
 	 				}
 	 			}*/
 	 			return DeptLeader;
 	 		}
 	 		
 		
 		//2. 非专户管理部、固收投资部门  客服部
 		String sql = "SELECT u.ID,u.realname from t_s_base_user u " + 
 				"LEFT JOIN t_s_role_user ru on ru.userid = u.ID " + 
 				"LEFT JOIN t_s_role r on ru.roleid = r.ID " + 
 				"LEFT JOIN t_s_user_org o on o.user_id = u.ID " + 
 				"LEFT JOIN t_s_depart d on d.ID = o.org_id " + 
 				"where d.departname= ? and r.rolecode='departFuze'";
 		
 		List<Map<String, Object>>  maps = this.findForJdbc(sql, currentDepart.getDepartname());
 		if(maps!=null && maps.size()>0) {
 			String userId = (String)maps.get(0).get("ID");
 			if(StringUtil.isNotEmpty(userId)) {
 				TSUser tSUser  = this.get(TSUser.class, userId);
 				if(tSUser != null) {
 					DeptLeader.add(tSUser);//部门负责人
 				}
 			}
 		}
 		//--非专户管理部 协管领导
 		List<FuzeFenguanEntity> fuzeFenguan = this.findByProperty(FuzeFenguanEntity.class, "departName", currentDepart.getDepartname());
			if(fuzeFenguan!= null && fuzeFenguan.size()>0) {
				String realname = fuzeFenguan.get(0).getDepartXieguan();
				List<TSUser> xieguanUser = this.findByProperty(TSUser.class, "realName", realname);
				if(xieguanUser!= null && xieguanUser.size()>0) {
					DeptLeader.add(xieguanUser.get(0));
				}
			}
			
 		return DeptLeader;
 	}
 	/**
 	 *  获取当前登录人的所在部门 负责人信息-11-15
 	 */
 	@Override
	public List<TSUser> getDeptLeaderOfLoginUser_old(TSUser loginUser) {
 		List<TSUser> DeptLeader =  new ArrayList<TSUser>();

		//1.根据当前登录账号在 pas_mod_attached中查找 自己是否是部门负责人
		//找到本部门的正部门负责人进行评分
		String hql = "from PasModAttachedEntity where username = ? and rolecode = ? ";
		String hql2 = "from PasModAttachedEntity where  rolecode = ? and orgCode = ? ";
		String hql3 = "from PasModAttachedEntity where  username = ? and rolecode = ? and orgCode = ? ";
		String hql4 = "from PasModAttachedEntity where  ( rolecode = ? or rolecode = ? ) and orgCode = ? ";
		String username = loginUser.getUserName();//登录人账号
		TSDepart departMent = loginUser.getCurrentDepart();
		String departCode = departMent.getOrgCode();
		String Zrolecode = "departFuze";//正部门负责人
		String rolecode = "fudepartFuze";//副部门负责人
		String putongRole = "putong";//普通员工角色m
		List<PasModAttachedEntity> fudepartFuzes =  this.findHql(hql, username,rolecode);
		//1. 以副部门负责人登录---本身在附加表中。正部门负责人也在附加表中
		if(fudepartFuzes != null && fudepartFuzes.size()>0) {
			//根据当前部门找到正部门负责人
			List<PasModAttachedEntity> departFuzes =  this.findHql(hql2, Zrolecode,departCode);
			if(departFuzes != null && departFuzes.size() > 0) {
				String userName = departFuzes.get(0).getUsername();
				if(StringUtil.isNotEmpty(userName)) {
					List<TSUser> users = this.findByProperty(TSUser.class, "userName", userName);
					if(users != null && users.size()>0) {
						DeptLeader.add(users.get(0));
						return DeptLeader;
					}
				}
			}
			
		}
		//2. 当前登录人不在附加表中，但是他的正副负责人都在附加表中-----------11-16
		if(fudepartFuzes != null && fudepartFuzes.isEmpty()) {
			List<PasModAttachedEntity> departFuzes =  this.findHql(hql4, Zrolecode,rolecode,departCode);
				if(departFuzes != null && departFuzes.size() > 0) {
					String userName = departFuzes.get(0).getUsername();
					if(StringUtil.isNotEmpty(userName)) {
						List<TSUser> users = this.findByProperty(TSUser.class, "userName", userName);
						if(users != null && users.size()>0) {
							DeptLeader.add(users.get(0));
						}
					}
					//以下为新增的11-16下午
					String userName2 = departFuzes.get(1).getUsername();
					if(StringUtil.isNotEmpty(userName2)) {
						List<TSUser> users = this.findByProperty(TSUser.class, "userName", userName2);
						if(users != null && users.size()>0) {
							DeptLeader.add(users.get(0));
						}
					}
					
					return DeptLeader;
				}
		}
		//3. 以普通员工身份登录---并且本人在附加表中，部门负责人不在附加表
		List<PasModAttachedEntity> departFuze2s =  this.findHql(hql3, username,putongRole,departCode);
		if(departFuze2s != null && departFuze2s.size() > 0) {
			String fuZenRolecode = "departFuze";
			String fuZenorgcode = "A04A20";
			String fuZenRenSql = "SELECT u.ID as id,u.username as username FROM t_s_base_user u " + 
								 "LEFT JOIN t_s_user_org uo on u.id = uo.user_id " + 
								 "LEFT JOIN t_s_depart d on uo.org_id = d.ID " + 
								 "LEFT JOIN t_s_role_user ru on ru.userid = u.id " + 
								 "LEFT JOIN t_s_role r on r.id = ru.roleid " + 
								 "where r.rolecode = ? and d.org_code = ? ";
			List<Map<String, Object>> maps =  this.findForJdbc(fuZenRenSql, fuZenRolecode,fuZenorgcode);
			if(maps != null && maps.size() > 0) {
				String userId = (String) maps.get(0).get("id");
				TSUser tSUser = this.get(TSUser.class, userId);
				//return tSUser;
				DeptLeader.add(tSUser);
				return DeptLeader;
			}
			
		}
		
		//4. 以普通员工登录，并且本人不在附加表中，部门负责人不在附加表中
		List<TSUserOrg> deptAllTSUserOrg = (ArrayList<TSUserOrg>) findByProperty(TSUserOrg.class, "tsDepart.id", loginUser.getDepartid());
		int size = deptAllTSUserOrg.size();
		//查询部门负责人角色
		String sql = "select ru.userid,ru.roleid  from t_s_role_user ru LEFT JOIN t_s_role r on r.ID = ru.roleid where r.rolecode= ? ";
		String roleCode = "departFuze";//部门负责人角色
		List<Map<String, Object>> userIds =  this.findForJdbc(sql, roleCode);
		bgm:for (int i = 0; i < size; i++) {
				TSUserOrg tsUserOrg = deptAllTSUserOrg.get(i);
				for (Map<String, Object> map : userIds) {
					String userid= (String) map.get("userid");
					if(StringUtils.isNotEmpty(userid)) {
						if(userid.equals(tsUserOrg.getTsUser().getId())) {
							//DeptLeader = tsUserOrg.getTsUser();
							DeptLeader.add(tsUserOrg.getTsUser());
							break bgm;
						}
					}
				}
			}
		
		
		
		return DeptLeader;
	}
 	
 	
 	/**
 	 *  获取当前登录人的所在部门 负责人信息-10-24---20181115之前的代码
 	 */
/* 	@Override
	public TSUser getDeptLeaderOfLoginUser(TSUser loginUser) {
		List<TSUserOrg> deptAllTSUserOrg = (ArrayList<TSUserOrg>) findByProperty(TSUserOrg.class, "tsDepart.id", loginUser.getDepartid());
		TSUser DeptLeader = null;
		int size = deptAllTSUserOrg.size();
		//查询部门负责人角色
		String sql = "select ru.userid userid from t_s_role_user ru where ru.roleid = ? ";
		String roleId = "2c90e6b2669a608c01669a79cec10001";//部门负责人角色
		
		List<Map<String, Object>> userIds =  this.findForJdbc(sql, roleId);
		bgm:for (int i = 0; i < size; i++) {
				TSUserOrg tsUserOrg = deptAllTSUserOrg.get(i);
				for (Map<String, Object> map : userIds) {
					String userid= (String) map.get("userid");
					if(StringUtils.isNotEmpty(userid)) {
						if(userid.equals(tsUserOrg.getTsUser().getId())) {
							DeptLeader = tsUserOrg.getTsUser();
							break bgm;
						}
					}
				}
			}
		return DeptLeader;
	}
*/
	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询 pas_staff_lead 中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasStaffLeadEntity where appraiser = :loginUserName";
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
	public List<PasStaffLeadEntity> installDataGridList(TSUser deptLeader, TSUser loginUser) {
		List<PasStaffLeadEntity> dataGridResult = new ArrayList<PasStaffLeadEntity>();
		PasStaffLeadEntity pasStaffLead = new PasStaffLeadEntity();
		TSUser user = this.get(TSUser.class, loginUser.getId());
		
		pasStaffLead.setId(UUIDGenerator.generate());
		pasStaffLead.setAppraiser(loginUser.getUserName());//评价人
		pasStaffLead.setGoalPerson(deptLeader.getRealName());//被评价人
		pasStaffLead.setDept( user != null?user.getUserOrgList().get(0).getTsDepart().getDepartname():"信息技术部");//登录人所在部门
		
		dataGridResult.add(pasStaffLead);
		return dataGridResult;
		
	}

	
	
	
	@Override
	public List<TSUser> getAllDeptStaffExceptLoginUser(TSUser loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PasStaffLeadEntity> installDataGridList(List<TSUser> deptLeader, TSUser loginUser) {
		List<PasStaffLeadEntity> dataGridResult = new ArrayList<PasStaffLeadEntity>();
		TSUser user = this.get(TSUser.class, loginUser.getId());
		
		for (TSUser leader : deptLeader) {
			PasStaffLeadEntity pasStaffLead = new PasStaffLeadEntity();
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
				DynamicDBUtil.closeDBkey(dbKey);
				//DynamicDBUtil.closeDBkey2(dbKey);
			}
			if(flag) {
				pasStaffLead.setDept( user != null?user.getUserOrgList().get(0).getTsDepart().getDepartname():"信息技术部");//登录人所在部门
			}
			//评价人所在部门
			pasStaffLead.setAppraiserDept(loginUser.getCurrentDepart().getDepartname());
			dataGridResult.add(pasStaffLead);
			break;
		}
		return dataGridResult;
	}

	@Override
	public List<TSUser> filterUser(TSUser user, List<TSUser> users) {
		String toUserName = user.getUserName();
		
		/*for (TSUser tsUser : users) {
			if(StringUtils.isNotEmpty(toUserName) && !toUserName.equals(tsUser.getUserName())) {
				users.add(user);
			}
		}*/
		return users;
	}
}
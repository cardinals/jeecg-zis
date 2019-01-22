package generator.pas.pasfuzeleader.service.impl;
import generator.pas.pasfuzeleader.service.PasFuzeLeaderServiceI;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import generator.pas.fuzefenguan.entity.FuzeFenguanEntity;
import generator.pas.onstaff.entity.PasOnStaffEntity;
import generator.pas.pasasset.entity.PasAssetEntity;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;

@Service("pasFuzeLeaderService")
@Transactional
public class PasFuzeLeaderServiceImpl extends CommonServiceImpl implements PasFuzeLeaderServiceI {

	String chairman ="chairman";//董事长
	String president = "president";//总裁
	String vicePresident = "vicePresident";//副总裁
	String managDirector = "managDirector";//董事总经理
	String duchazhang =	"duchazhang";//督察长
	String departFuze =	"departFuze";//部门负责人
	String judgeDepartFuze = "SELECT bu.realname,bu.username,r.rolename from t_s_base_user bu " + 
			"LEFT JOIN  t_s_role_user ru on ru.userid = bu.ID " + 
			"left join t_s_role r on r.id = ru.roleid " + 
			"where bu.username = ? and r.rolecode = ? ";//判断当前登录人是否是什么角色
	String getChairmans ="SELECT bu.realname,r.rolename from t_s_user u " + 
							" LEFT JOIN t_s_base_user bu on bu.id = u.id " + 
							" LEFT JOIN t_s_role_user ru on u.id = ru.userid " + 
							" LEFT JOIN t_s_role r on r.ID = ru.roleid " + 
							" where r.rolecode = ? ";
	//判断当前人是否 是督察长、副总裁、董事总经理 职位
	String judgePosition = "SELECT bu.username,bu.realname,r.rolename from  t_s_base_user bu " + 
					"LEFT JOIN t_s_role_user ru on bu.id = ru.userid " + 
					"LEFT JOIN t_s_role r on r.ID = ru.roleid " + 
					"where r.rolecode in ('duchazhang','vicePresident','managDirector') " + 
					"and bu.username = ? ";
	//判断当前人是否 是督察长、副总裁职位
		String judgeGaoGuan = "SELECT bu.username,bu.realname,r.rolename from  t_s_base_user bu " + 
						"LEFT JOIN t_s_role_user ru on bu.id = ru.userid " + 
						"LEFT JOIN t_s_role r on r.ID = ru.roleid " + 
						"where r.rolecode in ('duchazhang','vicePresident') " + 
						"and bu.username = ? ";
	//获取两种角色下的人，比如总裁、董事长	
		String getZongcai ="SELECT bu.realname,r.rolename from t_s_user u " + 
				" LEFT JOIN t_s_base_user bu on bu.id = u.id " + 
				" LEFT JOIN t_s_role_user ru on u.id = ru.userid " + 
				" LEFT JOIN t_s_role r on r.ID = ru.roleid " + 
				" where r.rolecode in(?,?)";
	//获取三种角色下的人
		String getRole3 ="SELECT bu.realname,r.rolename from t_s_user u " + 
				" LEFT JOIN t_s_base_user bu on bu.id = u.id " + 
				" LEFT JOIN t_s_role_user ru on u.id = ru.userid " + 
				" LEFT JOIN t_s_role r on r.ID = ru.roleid " + 
				" where r.rolecode in(?,?,?)";
		
		  final String names[] = {"王瑶","杨凯","曹健","向祖荣","易海波","王启道","马荣荣","黄震"};
	@Override
	public List<PasFuzeLeaderEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser) {

		List<PasFuzeLeaderEntity> dataGridResult = new ArrayList<PasFuzeLeaderEntity>();
		if(allLeaders!=null) {
			for (Map<String, Object> map : allLeaders) {
				if(loginUser.getRealName().equals(map.get("realname")))
					continue;
				PasFuzeLeaderEntity pasFuzeLeader = new PasFuzeLeaderEntity();
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
	
	/**
	 * 获取部门负责人的上级领导人
	 */
	@Override
	public List<Map<String, Object>> getAllLeads() {
		String sql = "SELECT bu.realname,r.rolename from t_s_user u "
				+ "LEFT JOIN t_s_base_user bu on bu.id = u.id "
				+ "LEFT JOIN t_s_role_user ru on u.id = ru.userid " 
				+"LEFT JOIN t_s_role r on r.ID = ru.roleid"
				+ " where r.rolecode in (?,?,?)";
		String roleCode = "president";//总裁
		String roleCode2 = "vicePresident";//副总裁
		String roleCode3 = "managDirector";//董事总经理
		List<Map<String, Object>>  users = this.findForJdbc(sql, roleCode,roleCode2,roleCode3);
		return users;
	}
	
	/**20181205
	 * “部门负责人及以上人员对上级评价”评分名单修订如下：
		1）所有部门负责人及董事总经理给副总裁打分，统计结果时区分出其分管的部门负责人及董事总经理的打分；
		2）督察长、副总裁、董事总经理及所有部门负责人给总裁打分；
		3）黄志斌、杨凯(总裁)、督察长、副总裁、董事总经理给董事长打分。
		4）部门负责人（许超、罗杰）给协管领导打分。
	 * 
	 */
	@SuppressWarnings("null")
	@Override
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		
		List<Map<String, Object>>  users = new ArrayList<Map<String, Object>>();//方法返回结果
		TSDepart  depart = loginUser.getCurrentDepart();
		String currentDepart = depart.getDepartname();
		String username = loginUser.getUserName();//登录名称
		String realName = loginUser.getRealName();//实际名称
		
		//情况一：部门负责人（许超、罗杰）--------协管领导、副总裁、总裁，直接return 结果
		if("xuchao".equals(username)||"luojie".equals(username)) {
			//1.协管领导
			//1.1.许超登录
			if("xuchao".equals(username)) {
				Map<String,Object> fenguanMap = new HashMap<String,Object>();
				fenguanMap.put("name", "马荣荣");
				fenguanMap.put("position","董事总经理");
				users.add(fenguanMap);
			}
			//1.2.罗杰登录
	 		if("luojie".equals(username)) {
	 			Map<String,Object> fenguanMap = new HashMap<String,Object>();
				fenguanMap.put("name", "黄震");
				fenguanMap.put("position","董事总经理");
				users.add(fenguanMap);
	 		}
			//2.获取 副总裁.总裁
			List<Map<String, Object>> chairmans = this.findForJdbc(getZongcai, vicePresident,president);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
						Map<String, Object> putmap = new HashMap<String, Object>();
						putmap.put("name", map.get("realname"));
						putmap.put("position",map.get("rolename"));
						users.add(putmap);
					}
					
				}
		      Collections.sort(users, new Comparator<Map<String, Object>>(){

					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						int num = posionOfName((String)o1.get("name"),names)-posionOfName((String)o2.get("name"),names);
						return num;
					}
		        } );
			return users;
		}
		
		//2.黄志斌、杨凯(总裁)--------------董事长，直接return 结果
		//if("huangzhibin".equals(username)||"yangkai".equals(username)) {
		//黄志斌--------------董事长，直接return 结果 20181214
		if("huangzhibin".equals(username)) {
		List<Map<String, Object>> chairmans = this.findForJdbc(getChairmans, chairman);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
						Map<String, Object> putmap = new HashMap<String, Object>();
						putmap.put("name", map.get("realname"));
						putmap.put("position",map.get("rolename"));
						users.add(putmap);
					
				}
			}
		     Collections.sort(users, new Comparator<Map<String, Object>>(){
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								int num = posionOfName((String)o1.get("name"),names)-posionOfName((String)o2.get("name"),names);
								return num;
							}
				        } );
			return users;
		}
		
		//3. 部门负责人----------------------副总裁、总裁
				List<Map<String, Object>> fuzes = this.findForJdbc(judgeDepartFuze, username,"departFuze");
				if(fuzes != null &&fuzes.size()>0) {
					
					List<Map<String, Object>> chairmans = this.findForJdbc(getZongcai, vicePresident,president);
					if(chairmans != null &&chairmans.size()>0) {
						for (Map<String, Object> map : chairmans) {
								Map<String, Object> putmap = new HashMap<String, Object>();
								putmap.put("name", map.get("realname"));
								putmap.put("position",map.get("rolename"));
								users.add(putmap);
							}
							
						}
					
				}
		
		//4. 董事总经理----------------------副总裁、总裁、董事长
		
				
				List<Map<String, Object>> dongshi = this.findForJdbc(judgeDepartFuze, username,"managDirector");
				if(dongshi != null &&dongshi.size()>0) {
					List<Map<String, Object>> chairmans = this.findForJdbc(getRole3, vicePresident,president,chairman);
					if(chairmans != null &&chairmans.size()>0) {
						for (Map<String, Object> map : chairmans) {
							Map<String, Object> putmap = new HashMap<String, Object>();
							putmap.put("name", map.get("realname"));
							putmap.put("position",map.get("rolename"));
							users.add(putmap);
						}
					}
				}
		//5. 督察长、副总裁------------------总裁、董事长
				List<Map<String, Object>> gaoguans = this.findForJdbc(judgeGaoGuan, username);
				if(gaoguans != null &&gaoguans.size()>0) {
					// 获取总裁、董事长
					List<Map<String, Object>> chairmans = this.findForJdbc(getZongcai, chairman,president);
					if(chairmans != null &&chairmans.size()>0) {
						for (Map<String, Object> map : chairmans) {
								Map<String, Object> putmap = new HashMap<String, Object>();
								putmap.put("name", map.get("realname"));
								putmap.put("position",map.get("rolename"));
								users.add(putmap);
							}
							
						}
					
					}
		//最后对三四五的情况进行统一过滤(users去重)
		Set<Map<String, Object>>  set = new  HashSet<Map<String, Object>> (); 
        List<Map<String, Object>> newUsers = new  ArrayList<Map<String, Object>>(); 
        for (Map<String, Object> user:users) {
            if(realName.equals(user.get("name")))
        	continue;
           if(set.add(user)){
        	   newUsers.add(user);
           }
       }
        
        //对 newUsers进行排序
        //顺序问题：王瑶-->杨凯-->曹健-->向祖荣-->易海波-->王启道
        
      
        //start
        Collections.sort(newUsers, new Comparator<Map<String, Object>>(){

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int num = posionOfName((String)o1.get("name"),names)-posionOfName((String)o2.get("name"),names);
				return num;
			}
        } );
        //end
        return newUsers;
	}
	
	
	/**----------------------------------2018-11-26-------------
	 * 注释掉代码时间20181205，用以保存历史修改记录
	 * 
	 * 2.评分名单：
			1）部门负责人及协管领导给其分管领导打分；
			2）督察长、副总裁、董事总经理及所有部门负责人给总裁打分；
			3）督察长、副总裁给董事长打分。
	 */
	/*@SuppressWarnings("null")
	@Override
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		
		List<Map<String, Object>>  users = new ArrayList<Map<String, Object>>();//方法返回结果
		TSDepart  depart = loginUser.getCurrentDepart();
		String currentDepart = depart.getDepartname();
		
		String username = loginUser.getUserName();//登录名称
		String realName = loginUser.getRealName();//实际名称
		//特殊情况一
		//1.许超登录
 		if("xuchao".equals(username)) {
 			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			fenguanMap.put("name", "马荣荣");
			fenguanMap.put("position","董事总经理");
			users.add(fenguanMap);
			return users;
 		
 		}
 		//2.罗杰登录
 		if("luojie".equals(username)) {
 			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			fenguanMap.put("name", "黄震");
			fenguanMap.put("position","董事总经理");
			users.add(fenguanMap);
			return users;
 		}
 		//特殊情况二
	 		   String hql = "from PasAssetEntity t where t.name = ? and t.kaoheCode='departFuze'";
	 		   List<PasAssetEntity>  fuze = this.findHql(hql, realName);
	 		   if(fuze!=null && fuze.size()>0) {
	 				Map<String,Object> fenguanMap = new HashMap<String,Object>();
	 				fenguanMap.put("name", "黄志斌");
					fenguanMap.put("position","");
					users.add(fenguanMap);
					return users;
	 		   }
		*//**四种普通情况，如下
		 *  协管领导--分管领导

			部门负责人--分管领导、总裁
			
			董事总经理--总裁
			
			督察长、副总裁--总裁、董事长
		 *//*
	 	//1.如果 是协管领导
	 	String xieguanHql = "from FuzeFenguanEntity where departName = ? and departXieguan = ? ";
	 	List<FuzeFenguanEntity> fuzeFenguan = this.findHql(xieguanHql, currentDepart,realName);
	 	
	 	if(fuzeFenguan != null &&fuzeFenguan.size()>0) {
	 		Map<String,Object> fenguanMap = new HashMap<String,Object>();
			// 获取 部门分管领导
				FuzeFenguanEntity fuzeFenguanEntity = fuzeFenguan.get(0);
				String beiPing = fuzeFenguanEntity.getDepartFenguan();
				String position = fuzeFenguanEntity.getFenguanPosition();
				fenguanMap.put("name", beiPing);
				fenguanMap.put("position",position);
				users.add(fenguanMap);
				//return users;//-------考虑到一个人会有多个角色，不直接return了！
	 	}
	 	
	 	//2. 如果是 部门负责人
		List<Map<String, Object>> fuzes = this.findForJdbc(judgeDepartFuze, username,"departFuze");
		if(fuzes != null &&fuzes.size()>0) {
			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			//1.1 获取 部门分管领导
			List<FuzeFenguanEntity>  fuzeFenguanEntitys =  this.commonDao.findByProperty(FuzeFenguanEntity.class, "departZhuguan", realName);
			if(fuzeFenguanEntitys != null && fuzeFenguanEntitys.size()>0) {
				FuzeFenguanEntity fuzeFenguanEntity = fuzeFenguanEntitys.get(0);
				String beiPing = fuzeFenguanEntity.getDepartFenguan();
				String position = fuzeFenguanEntity.getFenguanPosition();
				fenguanMap.put("name", beiPing);
				fenguanMap.put("position",position);
				users.add(fenguanMap);
			}
			//1.2 获取总裁
			List<Map<String, Object>> chairmans = this.findForJdbc(getChairmans, president);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
				
					String name = (String)map.get("realname");
					String fenguan = (String)fenguanMap.get("name");
					
					if(StringUtil.isNotEmpty(name) && !name.equals(fenguan)) {
						
						Map<String, Object> putmap = new HashMap<String, Object>();
						putmap.put("name", map.get("realname"));
						putmap.put("position",map.get("rolename"));
						users.add(putmap);
					}
					
				}
			}
			
			//return users;		
		}
		
		
		//3. 是 董事总经理
		List<Map<String, Object>> dongshi = this.findForJdbc(judgeDepartFuze, username,"managDirector");
		if(dongshi != null &&dongshi.size()>0) {
			List<Map<String, Object>> chairmans = this.findForJdbc(getChairmans, president);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
					Map<String, Object> putmap = new HashMap<String, Object>();
					putmap.put("name", map.get("realname"));
					putmap.put("position",map.get("rolename"));
					users.add(putmap);
				}
			}
			//return users;
		}
		//4.是 督察长、副总裁
		List<Map<String, Object>> gaoguans = this.findForJdbc(judgeGaoGuan, username);
		if(gaoguans != null &&gaoguans.size()>0) {
			// 获取总裁、董事长
			List<Map<String, Object>> chairmans = this.findForJdbc(getZongcai, president,chairman);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
						Map<String, Object> putmap = new HashMap<String, Object>();
						putmap.put("name", map.get("realname"));
						putmap.put("position",map.get("rolename"));
						users.add(putmap);
					}
					
				}
			
				//return users;
			}
		
		//users去重
		Set<Map<String, Object>>  set = new  HashSet<Map<String, Object>> (); 
        List<Map<String, Object>> newUsers = new  ArrayList<Map<String, Object>>(); 
        for (Map<String, Object> user:users) {
           if(set.add(user)){
        	   newUsers.add(user);
           }
       }

        return newUsers;
			//return users;
	}*/
	
	/**注释时间：2018-11-26
	 *  2.评分名单：
		1）部门负责人给分管领导打分；
		2）督察长、副总裁、董事总经理及所有部门负责人给总裁打分；
		3）督察长、副总裁、董事总经理给董事长打分。
	 *  部门负责人及以上人员对上级领导评分
	 */
/*	@SuppressWarnings("null")
	@Override
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		
		List<Map<String, Object>>  users = new ArrayList<Map<String, Object>>();//方法返回结果
		
		String username = loginUser.getUserName();//登录名称
		String realName = loginUser.getRealName();//实际名称
		
		//20181126-start
		
		//1.许超登录
 		if("xuchao".equals(username)) {
 			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			fenguanMap.put("name", "马荣荣");
			fenguanMap.put("position","董事总经理");
			users.add(fenguanMap);
			return users;
 		
 		}
 		//2.罗杰登录
 		if("luojie".equals(username)) {
 			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			fenguanMap.put("name", "黄震");
			fenguanMap.put("position","董事总经理");
			users.add(fenguanMap);
			return users;
 		}
 		
	 		   String hql = "from PasAssetEntity t where t.name = ? and t.kaoheCode='departFuze'";
	 		   List<PasAssetEntity>  fuze = this.findHql(hql, realName);
	 		   if(fuze!=null && fuze.size()>0) {
	 				Map<String,Object> fenguanMap = new HashMap<String,Object>();
	 				fenguanMap.put("name", "黄志斌");
					fenguanMap.put("position","");
					users.add(fenguanMap);
					return users;
	 		   }
		//20181126-end
				
		boolean gaoguanFlag = false;//判断是否是 督察长、副总裁、董事总经理等高管角色
		List<Map<String, Object>> gaoguans = this.findForJdbc(judgePosition, username);
		if(gaoguans != null &&gaoguans.size()>0) {
			gaoguanFlag = true;
		}
		boolean fuzeFlag = false;//判断是否是 部门负责人
		List<Map<String, Object>> fuzes = this.findForJdbc(judgeDepartFuze, username,departFuze);
		if(fuzes != null &&fuzes.size()>0) {
				fuzeFlag = true;
		}
	
		//1. 当前登录人是部门负责人
		if(fuzeFlag) {
			Map<String,Object> fenguanMap = new HashMap<String,Object>();
			//1.1 获取 部门分管领导
			List<FuzeFenguanEntity>  fuzeFenguanEntitys =  this.commonDao.findByProperty(FuzeFenguanEntity.class, "departZhuguan", realName);
			if(fuzeFenguanEntitys != null && fuzeFenguanEntitys.size()>0) {
				FuzeFenguanEntity fuzeFenguanEntity = fuzeFenguanEntitys.get(0);
				String beiPing = fuzeFenguanEntity.getDepartFenguan();
				String position = fuzeFenguanEntity.getFenguanPosition();
				fenguanMap.put("name", beiPing);
				fenguanMap.put("position",position);
				users.add(fenguanMap);
			}
			//1.2 获取总裁
			List<Map<String, Object>> chairmans = this.findForJdbc(getChairmans, president);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
				
					String name = (String)map.get("realname");
					String fenguan = (String)fenguanMap.get("name");
					
					if(StringUtil.isNotEmpty(name) && !name.equals(fenguan)) {
						
						Map<String, Object> putmap = new HashMap<String, Object>();
						putmap.put("name", map.get("realname"));
						putmap.put("position",map.get("rolename"));
						users.add(putmap);
					}
					
				}
			}
			
			//return users;
		}
		
		//2.  督察长、副总裁、董事总经理 给总裁、董事长打分；
		if(gaoguanFlag&&fuzeFlag) {
			
			//2.1  获取董事长
			List<Map<String, Object>> dongshi = this.findForJdbc(getChairmans, chairman);
			if(dongshi != null &&dongshi.size()>0) {
				for (Map<String, Object> map : dongshi) {
					Map<String, Object> putmap = new HashMap<String, Object>();
					putmap.put("name", map.get("realname"));
					putmap.put("position",map.get("rolename"));
					users.add(putmap);
				}
			}
			
			return users;
		}
		
		if(gaoguanFlag&&!fuzeFlag) {
			//2.1  获取总裁
			List<Map<String, Object>> chairmans = this.findForJdbc(getChairmans, president);
			if(chairmans != null &&chairmans.size()>0) {
				for (Map<String, Object> map : chairmans) {
					Map<String, Object> putmap = new HashMap<String, Object>();
					putmap.put("name", map.get("realname"));
					putmap.put("position",map.get("rolename"));
					users.add(putmap);
				}
			}
			
			//2.1  获取董事长
			List<Map<String, Object>> dongshi = this.findForJdbc(getChairmans, chairman);
			if(dongshi != null &&dongshi.size()>0) {
				for (Map<String, Object> map : dongshi) {
					Map<String, Object> putmap = new HashMap<String, Object>();
					putmap.put("name", map.get("realname"));
					putmap.put("position",map.get("rolename"));
					users.add(putmap);
				}
			}
			
			return users;
		}
		
		return users;
	}*/

/*	@Override
	public List<Map<String, Object>> getAllLeads(TSUser loginUser) {
		
		String[] roles = {"departFuze","duchazhang","vicePresident", "managDirector"};
		List<Map<String, Object>>  users = new ArrayList<Map<String, Object>>();//方法返回结果
		String username = loginUser.getUserName();//登录名称
		String realName = loginUser.getRealName();//实际名称
		
		List<Map<String, Object>> maps = this.findForJdbc(judgeDepartFuze, username,departFuze);
		//1. 当前登录人是部门负责人
		if(maps !=null &&maps.size()>0) {
			
		}
		//判断当前登录人是否是部门负责人
		
	}*/
	
	
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询 pas_fuze_leader 中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasFuzeLeaderEntity where appraiser = :loginUserName";
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
	//end
	
	//String字符串在数组中的位置20181211
	 private int posionOfName(String name,String[] names) {
		 
		 for (int i = 0; i < names.length; i++) {
			if(name.equals(names[i])) {
				return i;
			}
				
		}
     	return -1;
     }
	 
 	public void delete(PasFuzeLeaderEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PasFuzeLeaderEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PasFuzeLeaderEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PasFuzeLeaderEntity t) throws Exception{
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
	private void doUpdateBus(PasFuzeLeaderEntity t) throws Exception{
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
	private void doDelBus(PasFuzeLeaderEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PasFuzeLeaderEntity t){
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
 	public String replaceVal(String sql,PasFuzeLeaderEntity t){
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
					javaInter.execute("pas_fuze_leader",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}




	
}
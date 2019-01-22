package generator.pas.ondept.service.impl;
import generator.pas.ondept.service.PasOnDeptServiceI;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import generator.pas.ondept.entity.PasOnDeptEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("pasOnDeptService")
@Transactional
public class PasOnDeptServiceImpl extends CommonServiceImpl implements PasOnDeptServiceI {

 	

 	
	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		//PasOnDeptEntity
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasOnDeptEntity where appraiser = :loginUserName";
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
	
	/**
	 * 系统加载数据 , 查询除当前登录用户管理的部门外所有的部门及部门负责人，支持分页  (弃用)
	 */
	@Override
	public void getDataGridReturnCustomized(CriteriaQuery cq,TSUser loginUser) {
		//
		HqlQuery hqlQuery = new HqlQuery(PasOnDeptEntity.class, "" + 
				"select ru.userid,u.realname ,uo.org_id,dt.departname from t_s_role_user  ru " + 
				"left join t_s_base_user u on ru.userid = u.id " + 
				"inner join t_s_user_org uo on ru.userid = uo.user_id " + 
				"left join t_s_depart  dt on dt.id = uo.org_id " + 
				"where ru.roleid = ( " + 
				"	select a.id  from t_s_role a" + 
				"	where a.rolecode ='departFuze'  " + 
				") " + 
				"and ru.userid <> ( " + 
				"	select b.id  from t_s_base_user b" + 
				"	where b.username = '"+loginUser.getUserName()+"' " + 
				") " , cq.getDataGrid());
		hqlQuery.setCurPage(cq.getCurPage());
		PageList pageList = this.commonDao.getPageListBySql(hqlQuery,false);
		List<PasOnDeptEntity> pasDeptEntitys = new ArrayList<PasOnDeptEntity>();
		for (int i = 0; i < pageList.getResultList().size(); i++) {
			PasOnDeptEntity pasOnDeptEntity = new PasOnDeptEntity();
			Object[] objs = (Object[])pageList.getResultList().get(i);
			String realname = (String) objs[1];
			String departname = (String) objs[3];
			pasOnDeptEntity.setId(UUIDGenerator.generate());
			pasOnDeptEntity.setGoalDept(departname);
			pasOnDeptEntity.setGoalDeptManager(realname);
			pasDeptEntitys.add(pasOnDeptEntity);
		}
		cq.getDataGrid().setResults(pasDeptEntitys);
		cq.getDataGrid().setTotal(pageList.getCount());

		cq.clear();
		cq = null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void installCustomizedData(CriteriaQuery cq,TSUser loginUser) {
		String hqlQuery = ""+
				"select ru.userid,case when  u.realname = '马荣荣'  " + 
				" 		then '马荣荣/许超'  " +
				" 		when dt.departname = '固收投资部'  "+
				"       then '黄震/罗杰'    "+
				"       else u.realname " + 
				"       end  as realname ,uo.org_id,"
				+ "		case dt.departname when '市场部'   then '市场部(含客服部)'  else dt.departname end as departname from t_s_role_user  ru " + 
				"left join t_s_base_user u on ru.userid = u.id " + 
				"inner join t_s_user_org uo on ru.userid = uo.user_id " + 
				"left join t_s_depart  dt on dt.id = uo.org_id " + 
				"where ru.roleid = ( " + 
				"	select a.id  from t_s_role a" + 
				"	where a.rolecode ='departFuze'  " + 
				") " + 
				"and ru.userid <> ( " + 
				"	select b.id  from t_s_base_user b" + 
				"	where b.username = '"+loginUser.getUserName()+"' " + 
				") ";
		Query query = getSession().createSQLQuery(hqlQuery);
	    List<Object[]> businessData = query.list();
		List<PasOnDeptEntity> pasDeptEntitys = new ArrayList<PasOnDeptEntity>();
		for (int i = 0; i < businessData.size(); i++) {
			PasOnDeptEntity pasOnDeptEntity = new PasOnDeptEntity();
			Object[] objs = (Object[])businessData.get(i);
			String realname = (String) objs[1];
			String departname = (String) objs[3];
			pasOnDeptEntity.setId(UUIDGenerator.generate());
			pasOnDeptEntity.setGoalDept(departname);
			pasOnDeptEntity.setGoalDeptManager(realname);
			pasOnDeptEntity.setAppraiser(loginUser.getUserName());
			pasDeptEntitys.add(pasOnDeptEntity);
		}
		/**
		 * 测试
		 */
		/*
		List<PasOnDeptEntity> pasDeptEntitys = new ArrayList<PasOnDeptEntity>();
		PasOnDeptEntity p1 = new PasOnDeptEntity();
		
		p1.setId(UUIDGenerator.generate());
		p1.setGoalDept("量化投资部");
		p1.setGoalDeptManager("赵非");
		p1.setAppraiser(loginUser.getUserName());
		pasDeptEntitys.add(p1);
		PasOnDeptEntity p2 = new PasOnDeptEntity();
		p2.setId(UUIDGenerator.generate());
		p2.setGoalDept("国际业务部");
		p2.setGoalDeptManager("付世伟");
		p2.setAppraiser(loginUser.getUserName());
		pasDeptEntitys.add(p2);*/
		cq.setResults(pasDeptEntitys);
	}
}
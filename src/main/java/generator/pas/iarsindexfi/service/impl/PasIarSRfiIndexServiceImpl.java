package generator.pas.iarsindexfi.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.iarsindexfi.entity.PasIarSRfiIndexEntity;
import generator.pas.iarsindexfi.service.PasIarSRfiIndexServiceI;

@Service("pasIarSRfiIndexService")
@Transactional
public class PasIarSRfiIndexServiceImpl extends CommonServiceImpl implements PasIarSRfiIndexServiceI {

	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasIarSRfiIndexEntity where appraiser = :loginUserName";
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

	/*@Override
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser) {
		String sqlQuery =  "select  realname,departname " + 
							"from t_s_role_user ru " + 
							"left join t_s_role r  on ru.roleid = r.id  " + 
							"left join t_s_base_user u on ru.userid = u.id  " + 
							"left join t_s_user_org uo  on u.id = uo.user_id " + 
							"left join t_s_depart d on uo.org_id = d.id " + 
							"where r.rolecode = 'departFuze' " + 
							"and d.org_code in ('A04A12','A04A13','A04A16','A04A15') " + 
							"and u.username != '"+loginUser.getUserName()+"' ";
		
		Query query = getSession().createSQLQuery(sqlQuery);
		List<Object[]> businessData = query.list(); 
		List<PasIarSRfiIndexEntity> pasIarSRfiIndexList = new ArrayList<PasIarSRfiIndexEntity>();
		for (int i = 0; i < businessData.size(); i++) {
			PasIarSRfiIndexEntity pasIarSRfiIndexEntity = new PasIarSRfiIndexEntity();
			String realname = (String) businessData.get(i)[0];
			String departname = (String) businessData.get(i)[1];
			pasIarSRfiIndexEntity.setId(UUIDGenerator.generate());
			pasIarSRfiIndexEntity.setGoalUsername(realname);
			pasIarSRfiIndexEntity.setGoalDept(departname);
			pasIarSRfiIndexEntity.setAppraiser(loginUser.getUserName());
			pasIarSRfiIndexList.add(pasIarSRfiIndexEntity);
		}
		cq.setResults(pasIarSRfiIndexList);
	}*/
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser) {
		String[] departments = {"固收投资部","权益投资部","国际业务部","策略投资部"};
		List<PasIarSRfiIndexEntity> pasIarSRfiIndexList = new ArrayList<PasIarSRfiIndexEntity>();
		for (int i = 0; i < departments.length; i++) {
			PasIarSRfiIndexEntity pasIarSRfiIndexEntity = new PasIarSRfiIndexEntity();
			String departname = departments[i];
			pasIarSRfiIndexEntity.setId(UUIDGenerator.generate());
			pasIarSRfiIndexEntity.setGoalDept(departname);
			pasIarSRfiIndexEntity.setAppraiser(loginUser.getUserName());
			pasIarSRfiIndexList.add(pasIarSRfiIndexEntity);
		}
		cq.setResults(pasIarSRfiIndexList);
	}
	

	
 
}
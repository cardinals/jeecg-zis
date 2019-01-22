package generator.pas.iarsindex.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.iarsindex.entity.PasIarSIndexEntity;
import generator.pas.iarsindex.service.PasIarSIndexServiceI;
import generator.pas.ondept.entity.PasOnDeptEntity;

@Service("pasIarSIndexService")
@Transactional
public class PasIarSIndexServiceImpl extends CommonServiceImpl implements PasIarSIndexServiceI {


	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasIarSIndexEntity where appraiser = :loginUserName";
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

	@SuppressWarnings("unchecked")
	@Override
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser) {
		String sqlQuery = "select bu.realname,pir.appraiser_dept from pas_iar_relation pir " + 
					 	  "left join t_s_base_user bu on pir.goal_uname = bu.username " + 
						  "where appraiser = '"+loginUser.getUserName()+"'";
				  
		Query query = getSession().createSQLQuery(sqlQuery);
	    List<Object[]> businessData = query.list();
		List<PasIarSIndexEntity> pasIarSIndexEntitys = new ArrayList<PasIarSIndexEntity>();
		for (int i = 0; i < businessData.size(); i++) {
			PasIarSIndexEntity pasIarSIndexEntity = new PasIarSIndexEntity();
			String realname = (String)businessData.get(i)[0];
			String appraiserDept = (String)businessData.get(i)[1];
			pasIarSIndexEntity.setId(UUIDGenerator.generate());
			pasIarSIndexEntity.setGoalPerson(realname);
			pasIarSIndexEntity.setAppraiser(loginUser.getUserName());
			pasIarSIndexEntity.setAppraiserDept(appraiserDept);
			pasIarSIndexEntitys.add(pasIarSIndexEntity);
		}
		cq.setResults(pasIarSIndexEntitys);
		
	}


}
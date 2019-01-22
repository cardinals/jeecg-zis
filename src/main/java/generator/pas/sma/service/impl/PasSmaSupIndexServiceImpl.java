package generator.pas.sma.service.impl;
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
import generator.pas.sma.entity.PasSmaSupIndexEntity;
import generator.pas.sma.service.PasSmaSupIndexServiceI;

@Service("pasSmaSupIndexService")
@Transactional
public class PasSmaSupIndexServiceImpl extends CommonServiceImpl implements PasSmaSupIndexServiceI {

	

	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasSmaSupIndexEntity where appraiser = :loginUserName";
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
		String sqlQuery = "SELECT d.departname, t.realname " + 
				"FROM t_s_role_user ru " + 
				"	LEFT JOIN t_s_base_user t ON t.id = ru.userid " + 
				"	LEFT JOIN t_s_user_org uo ON t.id = uo.user_id " + 
				"	LEFT JOIN t_s_depart d ON uo.org_id = d.id " + 
				"WHERE ru.roleid = ( " + 
				"	SELECT id " + 
				"	FROM t_s_role " + 
				"	WHERE rolecode = 'touZi' " + 
				") " + 
				"ORDER BY CASE  " + 
				"	WHEN d.departname = '权益投资部' " + 
				"	AND t.realname = '孔学兵' THEN 5.1 " + 
				"	WHEN d.departname = '权益投资部' THEN 5 " + 
				"	WHEN d.departname = '固收投资部' " + 
				"	AND t.realname = '罗杰' THEN 4.1 " + 
				"	WHEN d.departname = '固收投资部' THEN 4 " + 
				"	WHEN d.departname = '量化投资部' " + 
				"	AND t.realname = '赵菲' THEN 3.1 " + 
				"	WHEN d.departname = '量化投资部' THEN 3 " + 
				"	WHEN d.departname = '国际业务部' " + 
				"	AND t.realname = '付世伟' THEN 2.1 " + 
				"	WHEN d.departname = '国际业务部' THEN 2 " + 
				"	WHEN d.departname = '策略投资部' " + 
				"	AND t.realname = '田刚' THEN 1.1 " + 
				"	WHEN d.departname = '策略投资部' THEN 1 " + 
				"	ELSE 0 " + 
				"END DESC ";
				   
		Query query = getSession().createSQLQuery(sqlQuery);
		List<Object[]> businessData = query.list();
		List<PasSmaSupIndexEntity> pasSmaSupIndexEntitys = new ArrayList<PasSmaSupIndexEntity>();
		for (int i = 0; i < businessData.size(); i++) {
			PasSmaSupIndexEntity pasSmaSupIndexEntity = new PasSmaSupIndexEntity();
			String departname = (String) businessData.get(i)[0];
			String realname = (String) businessData.get(i)[1];
			pasSmaSupIndexEntity.setId(UUIDGenerator.generate());
			pasSmaSupIndexEntity.setGoalPerson(realname);
			pasSmaSupIndexEntity.setGoalDept(departname);
			pasSmaSupIndexEntity.setAppraiser(loginUser.getUserName());
			pasSmaSupIndexEntitys.add(pasSmaSupIndexEntity);
		}
		cq.setResults(pasSmaSupIndexEntitys);
		
	}

}
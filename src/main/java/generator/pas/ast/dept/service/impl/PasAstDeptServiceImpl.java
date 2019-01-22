package generator.pas.ast.dept.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.ast.dept.entity.PasAstDeptEntity;
import generator.pas.ast.dept.service.PasAstDeptServiceI;

@Service("pasAstDeptService")
@Transactional
public class PasAstDeptServiceImpl extends CommonServiceImpl implements PasAstDeptServiceI {

	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_staff中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from  PasAstDeptEntity where appraiser = :loginUserName ";
		Query query = commonDao.getSession().createQuery(hqlGetCount);
		query.setString("loginUserName", loginUser.getRealName());
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
	public List<PasAstDeptEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser) {
		List<PasAstDeptEntity> rs = new ArrayList<PasAstDeptEntity>();
		for (Map<String, String> map : listArray) {
			PasAstDeptEntity ent  = new PasAstDeptEntity();
			ent.setId(UUIDGenerator.generate());
			ent.setAppraiser(loginUser.getRealName());
			ent.setGoalDept(map.get("dept"));
			ent.setGoalDeptManager(map.get("realName"));
			rs.add(ent);
		}
		return rs;
	}
	

	@Override
	public List<Map<String, String>> getAllDeptManExceptLoginUser(TSUser loginUser) {
		/**
		 * 此sql: 查询当前资产用户所在部门的所有普通员工(除自己外) 新增附加表数据
		 */
		String hqlQuery = "select name,depart from pas_asset a " + 
						  "where a.kaohe_code = 'departFuze' " + 
						  "and a.name != '"+loginUser.getRealName()+"'";
		Session session = getSession();
		Query query = session.createSQLQuery(hqlQuery);
		List<Object[]> objList = query.list();
		List<Map<String, String>> rsList = new ArrayList<>();
		for (Object[] obj  : objList) {
			Map<String, String>  kv = new HashMap<String, String>();
			kv.put("realName", (String)obj[0]);
			kv.put("dept", (String)obj[1]);
			rsList.add(kv);
		}
		return rsList;
	}

	
}
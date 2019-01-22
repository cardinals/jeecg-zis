package generator.pas.ast.staff.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.ast.staff.entity.PasAstStaffEntity;
import generator.pas.ast.staff.service.PasAstStaffServiceI;
import generator.pas.onstaff.entity.PasOnStaffEntity;

@Service("pasAstStaffService")
@Transactional
public class PasAstStaffServiceImpl extends CommonServiceImpl implements PasAstStaffServiceI {
	

	@Override
	public Boolean getInitStatusByLoginUser(TSUser loginUser) {
		Boolean result = null;
		//查询pas_on_staff中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from  PasAstStaffEntity where appraiser = :loginUserName ";
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
	public List<Map<String, String>> getAllDeptStaffExceptLoginUser(TSUser loginUser) {
		/**
		 * 此sql: 查询当前资产用户所在部门的所有普通员工(除自己外) 新增附加表数据
		 */
		String hqlQuery = "select name,depart from pas_asset a " + 
						  "where a.depart_code = ( " + 
					      "	select depart_code from pas_asset " + 
					      "	where name = '"+loginUser.getRealName()+"' " + 
					      ") " + 
					      "and a.kaohe_code = 'yuangong' " + 
					      "and name != '"+loginUser.getRealName()+"'		";
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

	@Override
	public List<PasAstStaffEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser) {
		List<PasAstStaffEntity> dataGridResult = new ArrayList<PasAstStaffEntity>();
		for (Map<String, String> user : listArray) {
			PasAstStaffEntity pasOnStaff = new PasAstStaffEntity();
			pasOnStaff.setId(UUIDGenerator.generate());
			pasOnStaff.setAppraiser(loginUser.getUserName());
			pasOnStaff.setGoalPerson(user.get("realName"));
			pasOnStaff.setGoalPersonDept(user.get("dept"));
			dataGridResult.add(pasOnStaff);
		}
		return dataGridResult;
	}
	
	
	

}
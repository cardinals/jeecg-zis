package generator.pas.pasresult.service.impl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import generator.pas.pasresult.entity.PasResultEntity;
import generator.pas.pasresult.service.PasResultServiceI;

@Service("pasResultService")
@Transactional
public class PasResultServiceImpl extends CommonServiceImpl implements PasResultServiceI {

	@Override
	public Integer getCountForResultOnstaff(String realName, String string) {
		
		Boolean result = null;
		//查询pas_on_dept中是否存在当前用户的评分数据
		String hqlGetCount = "select count(*) from PasResultEntity where appraiser = :loginUserName and module = :module";
		Query query = commonDao.getSession().createQuery(hqlGetCount);
		query.setString("loginUserName", realName);
		query.setString("module", string);
		int existCount = ((Long) query.uniqueResult()).intValue();
		return existCount;
	}
}
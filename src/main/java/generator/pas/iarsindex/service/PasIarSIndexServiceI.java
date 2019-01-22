package generator.pas.iarsindex.service;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;


public interface PasIarSIndexServiceI extends CommonService{
	

 	/**
 	 * 获得初始状态  
 	 * @param loginUser
 	 * @return true : 已评分   false : 未评分
 	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	/**
	 *  定制查询数据
	 * @param cq
	 * @param loginUser
	 */
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser);
 	
}

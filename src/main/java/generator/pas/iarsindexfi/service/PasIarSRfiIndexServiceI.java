package generator.pas.iarsindexfi.service;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

public interface PasIarSRfiIndexServiceI extends CommonService{
 	
 	/**
 	 *   @desc 根据登录用户获取评分状态
 	 * @param loginUser
 	 * @return true:评分结束/False:评分未开始
 	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	/**
	 *  定制查询数据
	 * @param cq
	 * @param loginUser
	 */
	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser);
 	
}

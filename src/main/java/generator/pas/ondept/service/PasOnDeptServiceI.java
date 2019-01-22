package generator.pas.ondept.service;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

public interface PasOnDeptServiceI extends CommonService{
	
 	/**
 	 * 获取评分状态. 返回True:评分已经结束.返回False:评分未开始
 	 * @param loginUser
 	 * @return
 	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	
	/**
	 * 系统加载数据 , 查询除当前登录用户管理的部门外所有的部门及部门负责人
	 * @param cq
	 */
	public void getDataGridReturnCustomized(CriteriaQuery cq,TSUser loginUser);

	public void installCustomizedData(CriteriaQuery cq,TSUser loginUser);
 	
}

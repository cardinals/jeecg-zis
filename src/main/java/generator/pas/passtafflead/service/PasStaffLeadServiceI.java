package generator.pas.passtafflead.service;
import generator.pas.onstaff.entity.PasOnStaffEntity;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;

public interface PasStaffLeadServiceI extends CommonService{
	
 	public void delete(PasStaffLeadEntity entity) throws Exception;
 	
 	public Serializable save(PasStaffLeadEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasStaffLeadEntity entity) throws Exception;
 	
 	public List<TSUser> filterUser(TSUser user, List<TSUser> users );
 	/**
 	 * 
 	 * @param loginUser
 	 * @return 获取当前登录用户所在部门的所有员工(注:当前用户除外)
 	 */
	public List<TSUser> getAllDeptStaffExceptLoginUser(TSUser loginUser);

	/**
	 *   获取当前登录用户是否开始评分的状态
	 * @param loginUser
	 * @return  True:为开始评分/False:尚未开始评分
	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	
	/**
	 *    拼装初始化数据
	 * @param listExceptLoginUser
	 * @param loginUser 
	 * @return 
	 */
	public List<PasStaffLeadEntity> installDataGridList(List<TSUser> listExceptLoginUser, TSUser loginUser);
	
	/**
	 *    获取部门负责人
	 * @param loginUser
	 * @return
	 */
	List<TSUser> getDeptLeaderOfLoginUser(TSUser loginUser);
	
	List<TSUser> getDeptLeaderOfLoginUser_old(TSUser loginUser);

	List<PasStaffLeadEntity> installDataGridList(TSUser deptLeader, TSUser loginUser);
	
 	
}

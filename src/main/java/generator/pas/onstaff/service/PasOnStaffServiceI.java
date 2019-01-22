package generator.pas.onstaff.service;
import generator.pas.onstaff.entity.PasOnStaffEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PasOnStaffServiceI extends CommonService{
	
 	public void delete(PasOnStaffEntity entity) throws Exception;
 	
 	public Serializable save(PasOnStaffEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasOnStaffEntity entity) throws Exception;
 	/**
 	 * 
 	 * @param loginUser
 	 * @return 获取当前登录用户所在部门的所有员工(注:当前用户除外)   +查询附加表数据
 	 */
	public List<Map<String,String>> getAllDeptStaffExceptLoginUser(TSUser loginUser);

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
	public List<PasOnStaffEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser);

	
}

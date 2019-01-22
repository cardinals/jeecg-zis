package generator.pas.ast.staff.service;
import generator.pas.ast.staff.entity.PasAstStaffEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PasAstStaffServiceI extends CommonService{
 	
 	/**
	 *   获取当前登录用户是否开始评分的状态
	 * @param loginUser
	 * @return  True:为开始评分/False:尚未开始评分
	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	/**
	 * 获取当前资产登录用户所在部门的所有普通员工角色(注:当前用户除外)
	 * @param loginUser
	 * @return
	 */
	public List<Map<String, String>> getAllDeptStaffExceptLoginUser(TSUser loginUser);
	/**
	 * 拼装初始化数据
	 * @param listArray
	 * @param loginUser
	 * @return
	 */
	public List<PasAstStaffEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser);
 	
}

package generator.pas.ast.dept.service;
import generator.pas.ast.dept.entity.PasAstDeptEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PasAstDeptServiceI extends CommonService{
	
 	/**
	 * 获取当前登录用户是否开始评分的状态
 	 * @param loginUser
 	 * @return True:为开始评分/False:尚未开始评分
 	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	
	
	public List<PasAstDeptEntity> installDataGridList(List<Map<String, String>> listArray, TSUser loginUser);
	
	/**
	 * 获得资产所有部门负责人除当前登录人
	 * @param loginUser
	 * @return
	 */
	public List<Map<String, String>> getAllDeptManExceptLoginUser(TSUser loginUser);
 	
}

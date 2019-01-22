package generator.pas.pasfuzeleader.service;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PasFuzeLeaderServiceI extends CommonService{
	
 	public void delete(PasFuzeLeaderEntity entity) throws Exception;
 	
 	public Serializable save(PasFuzeLeaderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasFuzeLeaderEntity entity) throws Exception;
 	
 	/**
	 *   获取当前登录用户是否开始评分的状态
	 * @param loginUser
	 * @return  True:为开始评分/False:尚未开始评分
	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	
	/**  4.部门负责人对上级领导评分----1117上一个版本
	  *         获取所有的上级领导
	 * @param loginUser
	 * @return
	 */
	public List<Map<String, Object>>  getAllLeads();
	
	/**
	 *  5.部门负责人及以上人员对上级领导评分----------------20181117
	 *  
	 *	1）部门负责人给分管领导打分；总裁打分；
		2）督察长、副总裁、董事总经理及所有部门负责人给总裁打分；
		3）督察长、副总裁、董事总经理给董事长打分。
		许超还要额外给马荣荣评分；
	 */
	
	public List<Map<String, Object>>  getAllLeads(TSUser loginUser);

	public List<PasFuzeLeaderEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser);
}

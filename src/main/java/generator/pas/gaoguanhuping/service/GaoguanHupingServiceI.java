package generator.pas.gaoguanhuping.service;
import generator.pas.gaoguanhuping.entity.GaoguanHupingEntity;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GaoguanHupingServiceI extends CommonService{
	
 	public void delete(GaoguanHupingEntity entity) throws Exception;
 	
 	public Serializable save(GaoguanHupingEntity entity) throws Exception;
 	
 	public void saveOrUpdate(GaoguanHupingEntity entity) throws Exception;
 	
 	

 	/**
	 *   获取当前登录用户是否开始评分的状态
	 * @param loginUser
	 * @return  True:为开始评分/False:尚未开始评分
	 */
	public Boolean getInitStatusByLoginUser(TSUser loginUser);
	/**
	  * 获取所有的上级领导
	 * @param loginUser
	 * @return
	 */
	public List<Map<String, Object>>  getAllLeads();
	
	public List<Map<String, Object>>  getAllLeads(TSUser loginUser);

	public List<GaoguanHupingEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser);
 	
}

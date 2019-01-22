package generator.pas.zongcai.service;
import generator.pas.zongcai.entity.PasGaoguanZongcaiEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PasGaoguanZongcaiServiceI extends CommonService{
	
 	public void delete(PasGaoguanZongcaiEntity entity) throws Exception;
 	
 	public Serializable save(PasGaoguanZongcaiEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasGaoguanZongcaiEntity entity) throws Exception;

	public Boolean getInitStatusByLoginUser(TSUser loginUser);

	public List<Map<String, Object>> getAllLeads(TSUser loginUser);

	public List<PasGaoguanZongcaiEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser);
 	
}

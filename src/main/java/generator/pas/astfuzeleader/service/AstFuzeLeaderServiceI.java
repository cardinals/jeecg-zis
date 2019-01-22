package generator.pas.astfuzeleader.service;
import generator.pas.astfuzeleader.entity.AstFuzeLeaderEntity;
import generator.pas.pasfuzeleader.entity.PasFuzeLeaderEntity;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface AstFuzeLeaderServiceI extends CommonService{
	
 	public void delete(AstFuzeLeaderEntity entity) throws Exception;
 	
 	public Serializable save(AstFuzeLeaderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AstFuzeLeaderEntity entity) throws Exception;

	public Boolean getInitStatusByLoginUser(TSUser loginUser);

	public List<Map<String, Object>> getAllLeads(TSUser loginUser);

	public List<AstFuzeLeaderEntity> installDataGridList(List<Map<String, Object>> allLeaders, TSUser loginUser);

 	
}

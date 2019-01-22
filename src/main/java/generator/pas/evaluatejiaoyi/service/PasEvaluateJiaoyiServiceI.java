package generator.pas.evaluatejiaoyi.service;
import generator.pas.evaluatejiaoyi.entity.PasEvaluateJiaoyiEntity;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;

public interface PasEvaluateJiaoyiServiceI extends CommonService{
	
 	public void delete(PasEvaluateJiaoyiEntity entity) throws Exception;
 	
 	public Serializable save(PasEvaluateJiaoyiEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasEvaluateJiaoyiEntity entity) throws Exception;

	public Boolean getInitStatusByLoginUser(TSUser loginUser);

	public void installCustomizedData(CriteriaQuery cq, TSUser loginUser);

 	
}

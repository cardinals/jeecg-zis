package generator.pas.pasmodattached.service;
import generator.pas.pasmodattached.entity.PasModAttachedEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PasModAttachedServiceI extends CommonService{
	
 	public void delete(PasModAttachedEntity entity) throws Exception;
 	
 	public Serializable save(PasModAttachedEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasModAttachedEntity entity) throws Exception;
 	
}

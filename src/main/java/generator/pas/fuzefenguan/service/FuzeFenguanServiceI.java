package generator.pas.fuzefenguan.service;
import generator.pas.fuzefenguan.entity.FuzeFenguanEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FuzeFenguanServiceI extends CommonService{
	
 	public void delete(FuzeFenguanEntity entity) throws Exception;
 	
 	public Serializable save(FuzeFenguanEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FuzeFenguanEntity entity) throws Exception;
 	
}

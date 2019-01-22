package generator.pas.pasasset.service;
import generator.pas.pasasset.entity.PasAssetEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PasAssetServiceI extends CommonService{
	
 	public void delete(PasAssetEntity entity) throws Exception;
 	
 	public Serializable save(PasAssetEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PasAssetEntity entity) throws Exception;
 	
}

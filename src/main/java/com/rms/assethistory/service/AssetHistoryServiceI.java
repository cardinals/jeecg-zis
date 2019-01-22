package com.rms.assethistory.service;
import com.rms.assethistory.entity.AssetHistoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface AssetHistoryServiceI extends CommonService{
	
 	public void delete(AssetHistoryEntity entity) throws Exception;
 	
 	public Serializable save(AssetHistoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AssetHistoryEntity entity) throws Exception;
 	
}

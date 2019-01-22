package com.rms.asset.service;
import com.rms.asset.entity.AssetEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface AssetServiceI extends CommonService{
	
 	public void delete(AssetEntity entity) throws Exception;
 	
 	public Serializable save(AssetEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AssetEntity entity) throws Exception;
 	public void saveOrUpdate2(AssetEntity entity,String dateFormatId) throws Exception;
 	public void saveOrUpdate3(AssetEntity entity,String dateFormatId) throws Exception;
	public Serializable save2(AssetEntity asset, String dateFormatId) throws Exception;
 	
}

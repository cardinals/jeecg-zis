package com.rms.database.service;
import com.rms.database.entity.DatabaseEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface DatabaseServiceI extends CommonService{
	
 	public void delete(DatabaseEntity entity) throws Exception;
 	
 	public Serializable save(DatabaseEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DatabaseEntity entity) throws Exception;
 	
}

package com.jeecg.mana.service;
import com.jeecg.mana.entity.ManagEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ManagServiceI extends CommonService{
	
 	public void delete(ManagEntity entity) throws Exception;
 	
 	public Serializable save(ManagEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ManagEntity entity) throws Exception;
 	
}

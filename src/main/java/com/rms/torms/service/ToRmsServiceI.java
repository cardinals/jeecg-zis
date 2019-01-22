package com.rms.torms.service;
import com.rms.torms.entity.ToRmsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ToRmsServiceI extends CommonService{
	
 	public void delete(ToRmsEntity entity) throws Exception;
 	
 	public Serializable save(ToRmsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ToRmsEntity entity) throws Exception;
 	
}

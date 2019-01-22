package com.jeecg.dianzihu.service;
import com.jeecg.dianzihu.entity.DianzihuEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface DianzihuServiceI extends CommonService{
	
 	public void delete(DianzihuEntity entity) throws Exception;
 	
 	public Serializable save(DianzihuEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DianzihuEntity entity) throws Exception;
 	
}

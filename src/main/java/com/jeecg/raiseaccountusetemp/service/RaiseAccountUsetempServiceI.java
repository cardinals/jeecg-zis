package com.jeecg.raiseaccountusetemp.service;
import com.jeecg.raiseaccountusetemp.entity.RaiseAccountUsetempEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RaiseAccountUsetempServiceI extends CommonService{
	
 	public void delete(RaiseAccountUsetempEntity entity) throws Exception;
 	
 	public Serializable save(RaiseAccountUsetempEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RaiseAccountUsetempEntity entity) throws Exception;
 	
}

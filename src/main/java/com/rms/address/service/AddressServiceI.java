package com.rms.address.service;
import com.rms.address.entity.AddressEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface AddressServiceI extends CommonService{
	
 	public void delete(AddressEntity entity) throws Exception;
 	
 	public Serializable save(AddressEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AddressEntity entity) throws Exception;
 	
}

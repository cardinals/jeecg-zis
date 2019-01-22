package com.jeecg.pfjijincode.service;
import com.jeecg.pfjijincode.entity.PfJijinCodeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PfJijinCodeServiceI extends CommonService{
	
 	public void delete(PfJijinCodeEntity entity) throws Exception;
 	
 	public Serializable save(PfJijinCodeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PfJijinCodeEntity entity) throws Exception;
 	
}

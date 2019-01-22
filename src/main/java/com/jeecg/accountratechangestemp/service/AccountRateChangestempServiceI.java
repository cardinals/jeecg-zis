package com.jeecg.accountratechangestemp.service;
import com.jeecg.accountratechangestemp.entity.AccountRateChangestempEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface AccountRateChangestempServiceI extends CommonService{
	
 	public void delete(AccountRateChangestempEntity entity) throws Exception;
 	
 	public Serializable save(AccountRateChangestempEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AccountRateChangestempEntity entity) throws Exception;
 	
}

package com.jeecg.bankcode.service;
import com.jeecg.bankcode.entity.BankCodeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BankCodeServiceI extends CommonService{
	
 	public void delete(BankCodeEntity entity) throws Exception;
 	
 	public Serializable save(BankCodeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BankCodeEntity entity) throws Exception;
 	
}

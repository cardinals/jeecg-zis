package com.jeecg.bankaccountemp.service;
import com.jeecg.bankaccountemp.entity.BankAccountempEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BankAccountempServiceI extends CommonService{
	
 	public void delete(BankAccountempEntity entity) throws Exception;
 	
 	public Serializable save(BankAccountempEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BankAccountempEntity entity) throws Exception;
 	
}

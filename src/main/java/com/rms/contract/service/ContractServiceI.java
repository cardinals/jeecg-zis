package com.rms.contract.service;
import com.rms.contract.entity.ContractEntity;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ContractServiceI extends CommonService{
	
 	public void delete(ContractEntity entity) throws Exception;
 	
 	public Serializable save(ContractEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ContractEntity entity) throws Exception;

	public BigDecimal getContractId(String currentDate);
 	
}

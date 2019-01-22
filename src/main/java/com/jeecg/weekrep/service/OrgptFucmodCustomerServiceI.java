package com.jeecg.weekrep.service;
import com.jeecg.weekrep.entity.OrgptFucmodCustomerEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface OrgptFucmodCustomerServiceI extends CommonService{
	
 	public void delete(OrgptFucmodCustomerEntity entity) throws Exception;
 	
 	public Serializable save(OrgptFucmodCustomerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(OrgptFucmodCustomerEntity entity) throws Exception;
 	
}

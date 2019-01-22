package com.rms.payment.service;
import com.rms.payment.entity.PaymentEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PaymentServiceI extends CommonService{
	
 	public void delete(PaymentEntity entity) throws Exception;
 	
 	public Serializable save(PaymentEntity entity) throws Exception;
 	
 	public Serializable save(PaymentEntity entity,String contractId) throws Exception;
 	
 	public void saveOrUpdate(PaymentEntity entity) throws Exception;
 	
}

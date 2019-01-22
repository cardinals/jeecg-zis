package com.jeecg.bankcomment.service;
import com.jeecg.bankcomment.entity.BankCommentEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BankCommentServiceI extends CommonService{
	
 	public void delete(BankCommentEntity entity) throws Exception;
 	
 	public Serializable save(BankCommentEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BankCommentEntity entity) throws Exception;
 	
}

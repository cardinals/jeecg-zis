package com.jeecg.banktest.service;
import com.jeecg.banktest.entity.BanktestEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BanktestServiceI extends CommonService{
	
 	public void delete(BanktestEntity entity) throws Exception;
 	
 	public Serializable save(BanktestEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BanktestEntity entity) throws Exception;
 	
}

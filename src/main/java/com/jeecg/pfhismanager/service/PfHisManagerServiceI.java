package com.jeecg.pfhismanager.service;
import com.jeecg.pfhismanager.entity.PfHisManagerEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PfHisManagerServiceI extends CommonService{
	
 	public void delete(PfHisManagerEntity entity) throws Exception;
 	
 	public Serializable save(PfHisManagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PfHisManagerEntity entity) throws Exception;
 	
}

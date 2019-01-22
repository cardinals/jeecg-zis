package com.jeecg.sysparams.service;
import com.jeecg.sysparams.entity.TSSysParamsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TSSysParamsServiceI extends CommonService{
	
 	public void delete(TSSysParamsEntity entity) throws Exception;
 	
 	public Serializable save(TSSysParamsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TSSysParamsEntity entity) throws Exception;
 	
}

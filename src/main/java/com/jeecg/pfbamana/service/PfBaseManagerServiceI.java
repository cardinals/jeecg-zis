package com.jeecg.pfbamana.service;
import com.jeecg.pfbamana.entity.PfBaseManagerEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PfBaseManagerServiceI extends CommonService{
	
 	public void delete(PfBaseManagerEntity entity) throws Exception;
 	
 	public Serializable save(PfBaseManagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PfBaseManagerEntity entity) throws Exception;
 	
}

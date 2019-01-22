package com.rms.testtab.service;
import com.rms.testtab.entity.TesttabEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TesttabServiceI extends CommonService{
	
 	public void delete(TesttabEntity entity) throws Exception;
 	
 	public Serializable save(TesttabEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TesttabEntity entity) throws Exception;
 	
}

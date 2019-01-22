package com.jeecg.zpysystem.service;
import com.jeecg.zpysystem.entity.ZpBradeV2Entity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZpBradeV2ServiceI extends CommonService{
	
 	public void delete(ZpBradeV2Entity entity) throws Exception;
 	
 	public Serializable save(ZpBradeV2Entity entity) throws Exception;
 	
 	public void saveOrUpdate(ZpBradeV2Entity entity) throws Exception;
 	
}

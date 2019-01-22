package com.jeecg.zpysystem.service;
import com.jeecg.zpysystem.entity.ZpGradeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZpGradeServiceI extends CommonService{
	
 	public void delete(ZpGradeEntity entity) throws Exception;
 	
 	public Serializable save(ZpGradeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZpGradeEntity entity) throws Exception;
 	
}

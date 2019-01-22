package com.jeecg.departmentfee.service;
import com.jeecg.departmentfee.entity.DepartmentFeeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface DepartmentFeeServiceI extends CommonService{
	
 	public void delete(DepartmentFeeEntity entity) throws Exception;
 	
 	public Serializable save(DepartmentFeeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DepartmentFeeEntity entity) throws Exception;
 	
}

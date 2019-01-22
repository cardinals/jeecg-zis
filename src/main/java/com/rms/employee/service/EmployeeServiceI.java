package com.rms.employee.service;
import com.rms.employee.entity.EmployeeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmployeeServiceI extends CommonService{
	
 	public void delete(EmployeeEntity entity) throws Exception;
 	
 	public Serializable save(EmployeeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmployeeEntity entity) throws Exception;
 	
}

package com.rms.hardware.service;
import com.rms.hardware.entity.HardwareEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HardwareServiceI extends CommonService{
	
 	public void delete(HardwareEntity entity) throws Exception;
 	
 	public Serializable save(HardwareEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HardwareEntity entity) throws Exception;
 	
}

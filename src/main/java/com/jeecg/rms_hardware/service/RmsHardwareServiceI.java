package com.jeecg.rms_hardware.service;
import com.jeecg.rms_hardware.entity.RmsHardwareEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RmsHardwareServiceI extends CommonService{
	
 	public void delete(RmsHardwareEntity entity) throws Exception;
 	
 	public Serializable save(RmsHardwareEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RmsHardwareEntity entity) throws Exception;
 	
}

package com.jeecg.reportemail.service;
import com.jeecg.reportemail.entity.ReportEmailEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ReportEmailServiceI extends CommonService{
	
 	public void delete(ReportEmailEntity entity) throws Exception;
 	
 	public Serializable save(ReportEmailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ReportEmailEntity entity) throws Exception;
 	
}

package com.jeecg.weekrep.service;
import com.jeecg.weekrep.entity.OrgptFucmodWeekrepEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface OrgptFucmodWeekrepServiceI extends CommonService{
	
 	public void delete(OrgptFucmodWeekrepEntity entity) throws Exception;
 	
 	public Serializable save(OrgptFucmodWeekrepEntity entity) throws Exception;
 	
 	public void saveOrUpdate(OrgptFucmodWeekrepEntity entity) throws Exception;
 	
}

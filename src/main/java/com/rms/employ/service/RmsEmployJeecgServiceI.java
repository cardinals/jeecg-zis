package com.rms.employ.service;
import com.rms.employ.entity.RmsEmployJeecgEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface RmsEmployJeecgServiceI extends CommonService{
	
 	public void delete(RmsEmployJeecgEntity entity) throws Exception;
 	
 	public Serializable save(RmsEmployJeecgEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RmsEmployJeecgEntity entity) throws Exception;

	public List<RmsEmployJeecgEntity> getEmployeeDataGridList(List<Map<String, Object>> maps);
 	
}

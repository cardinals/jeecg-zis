package com.jeecg.manager.service;
import com.jeecg.mana.entity.ManagEntity;
import com.jeecg.manager.entity.AddmanagerEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface AddmanagerServiceI extends CommonService{
	
 	public void delete(AddmanagerEntity entity) throws Exception;
 	
 	public Serializable save(AddmanagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AddmanagerEntity entity) throws Exception;

	public void addMain(AddmanagerEntity addmanager, List<ManagEntity> manas);

	public void updateMain(AddmanagerEntity t, List<ManagEntity> manas);
 	
}

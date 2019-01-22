package com.jeecg.pf.service;
import com.jeecg.pf.entity.PfBaseEntity;
import com.jeecg.pfbamana.entity.PfBaseManagerEntity;
import com.jeecg.pfjijincode.entity.PfJijinCodeEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface PfBaseServiceI extends CommonService{
	
 	public void delete(PfBaseEntity entity) throws Exception;
 	
 	public Serializable save(PfBaseEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PfBaseEntity entity) throws Exception;

	public void addMain(PfBaseEntity pfBase, List<PfBaseManagerEntity> manas, List<PfJijinCodeEntity> codess);

	public void updateMain(PfBaseEntity t, List<PfBaseManagerEntity> manas, List<PfJijinCodeEntity> codess);

	public void saveByExcel(List<PfBaseEntity> listPfBaseEntitys);
 	
}

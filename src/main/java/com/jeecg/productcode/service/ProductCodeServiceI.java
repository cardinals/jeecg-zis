package com.jeecg.productcode.service;
import com.jeecg.productcode.entity.ProductCodeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ProductCodeServiceI extends CommonService{
	
 	public void delete(ProductCodeEntity entity) throws Exception;
 	
 	public Serializable save(ProductCodeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ProductCodeEntity entity) throws Exception;
 	
}

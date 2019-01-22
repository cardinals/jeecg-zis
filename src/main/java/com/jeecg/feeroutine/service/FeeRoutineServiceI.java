package com.jeecg.feeroutine.service;
import com.jeecg.feeroutine.entity.FeeRoutineEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FeeRoutineServiceI extends CommonService{
	
 	public void delete(FeeRoutineEntity entity) throws Exception;
 	
 	public Serializable save(FeeRoutineEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FeeRoutineEntity entity) throws Exception;
 	
 	/**
	 * 自定义按钮-[复制一行]业务处理
	 * @param id
	 * @return
	 */
	 public void doLine_copyBus(FeeRoutineEntity t) throws Exception;
}

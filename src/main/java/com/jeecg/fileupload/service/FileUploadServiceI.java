package com.jeecg.fileupload.service;
import com.jeecg.fileupload.entity.FileUploadEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FileUploadServiceI extends CommonService{
	
 	public void delete(FileUploadEntity entity) throws Exception;
 	
 	public Serializable save(FileUploadEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FileUploadEntity entity) throws Exception;
 	
}

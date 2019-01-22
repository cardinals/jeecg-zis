package generator.pas.pasresult.service;
import generator.pas.pasresult.entity.PasResultEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PasResultServiceI extends CommonService{
	

	public Integer getCountForResultOnstaff(String realName, String string);
 	
}

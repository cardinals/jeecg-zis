package generator.pas.aststafflead.service;
import generator.pas.aststafflead.entity.AstStaffleadEntity;
import generator.pas.passtafflead.entity.PasStaffLeadEntity;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;

public interface AstStaffleadServiceI extends CommonService{
	
 	public void delete(AstStaffleadEntity entity) throws Exception;
 	
 	public Serializable save(AstStaffleadEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AstStaffleadEntity entity) throws Exception;

	public Boolean getInitStatusByLoginUser(TSUser loginUser);

	public List<TSUser> getDeptLeaderOfLoginUser(TSUser loginUser);

	public List<AstStaffleadEntity> installDataGridList(List<TSUser> deptLeader, TSUser loginUser);
 	
}

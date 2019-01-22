package com.jeecg.demo.dao;

import java.util.List;
import java.util.Map;

import com.jeecg.chat.entity.ChatMessageHistory;
import org.jeecgframework.minidao.annotation.*;

import com.jeecg.demo.entity.JeecgDemoEntity;
import com.jeecg.demo.entity.JeecgLogReport;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

/**
 * Minidao例子
 * 
 */
@MiniDao
public interface JeecgMinidaoDao {
	
	@Arguments("pid")
 	@Sql("select ID,NAME,PID from t_s_region where pid=:pid order by name_en")
    List<Map<String, String>> getProCity(String pid);
	
 	@Sql("select ID,NAME,PID from t_s_region order by name_en")
    List<Map<String, String>> getAllRegions();

 	@ResultType(JeecgDemoEntity.class)
	public MiniDaoPage<JeecgDemoEntity> getAllEntities(@Param("jeecgDemo") JeecgDemoEntity jeecgDemo, @Param("page")  int page, @Param("rows") int rows,@Param("authSql") String authSql);

	@Sql("SELECT count(*) FROM jeecg_demo")
	Integer getCount();

	@Sql("SELECT SUM(salary) FROM jeecg_demo")
	Integer getSumSalary();
	
	@Arguments("id")
	@ResultType(String.class)
	@Sql("SELECT org_code FROM t_s_depart where id=:id")
	public java.lang.String getOrgCode(String id);
	
	//员工所在部门
	
	@Arguments("employeeId")
	@ResultType(String.class)
	@Sql("SELECT department from rms_employee_oa t where employee_id=:employeeId")
	public java.lang.String getDepartment(String employeeId);
	
	//员工姓名
	@Arguments("employeeId")
	@ResultType(String.class)
	@Sql("SELECT employee_name from rms_employee_oa t where employee_id=:employeeId")
	public java.lang.String getEmployeeName(String employeeId);
	
	
	//库房名称
	@Arguments("typecode")
	@ResultType(String.class)
	@Sql("select  typename from t_s_type t LEFT JOIN  t_s_typegroup tg on t.typegroupid=tg.id  where tg.typegroupcode='storehouse' and  t.typecode =:typecode")
	public java.lang.String getTypename(String typecode);
		
	//根据assId查询员工姓名
	@Arguments("assetId")
	@ResultType(String.class)
	@Sql("SELECT employee_name from rms_employee_oa e LEFT JOIN rms_asset_jeecg a on a.employee_id = e.employee_id where a.asset_id=:assetId")
	public java.lang.String getEmployeeNameByAssetId(String assetId);
	
	/**
	 * 应用管理-------OS、ip信息
	 */
     //所在设备 - start
	//1. 应用不在虚拟机的容器中，而在硬件所部署的容器中
	//根据serverId查询容器(硬件)信息------此时硬件容器没有虚拟机
		@Arguments("serverId")
		@ResultType(String.class)
		@Sql("SELECT h.name from rms_hardware_jeecg h LEFT JOIN  rms_server_jeecg s  on s.CONTAINER = h.hardware_id where s.VIRTURAL = 0 and s.SERVER_ID =:serverId ")
		public java.lang.String getHardWareName(String serverId);
	//2.应用在虚拟机的容器中
	String sql = "select h.name from rms_application_jeecg a " + 
				 "LEFT JOIN rms_server_jeecg r1 on a.SERVER_ID = r1.SERVER_ID " + 
				 "LEFT JOIN rms_server_jeecg r2 on r1.VCONTAINER = r2.SERVER_ID " + 
				 "left join rms_hardware_jeecg h on h.hardware_id = r2.CONTAINER "+
				 "where r1.VIRTURAL = 1 and r2.VIRTURAL = 0  and  r1.SERVER_ID =:serverId ";
	@Arguments("serverId")
	@ResultType(String.class)
	@Sql(sql)
	public java.lang.String getVirturalName(String serverId);	
	//所在设备 - end	
	//根据serverId查询serverName
	@Arguments("serverId")
	@ResultType(String.class)
	@Sql("SELECT s.SERVER_NAME from rms_server_jeecg s where s.SERVER_ID =:serverId ")
	public java.lang.String getServerName(String serverId);
	
	//根据serverId查询OS信息
	@Arguments("serverId")
	@ResultType(String.class)
	@Sql("SELECT s.ip from rms_server_jeecg s where s.SERVER_ID =:serverId  ")
	public java.lang.String getIPName(String serverId);
	/**
	 * ****数据库管理-------OS、ip信息
	 */
	//根据serverId查询serverName
	@Arguments("serverId")
	@ResultType(String.class)
	@Sql("SELECT s.SERVER_NAME from rms_server_jeecg s where s.SERVER_ID =:serverId")
	public java.lang.String getServerNameForOS(String serverId);
	
	//根据serverId查询OS信息
	@Arguments("serverId")
	@ResultType(String.class)
	@Sql("SELECT s.IP from rms_server_jeecg s where s.SERVER_ID =:serverId")
	public java.lang.String getIPNameForOS(String serverId);
	
	
	@Arguments("contractId")
	@ResultType(String.class)
	@Sql("SELECT t.contract_no from rms_contract_jeecg t where t.contract_id =:contractId")
	public java.lang.String getcontractNo(String contractId);
	
	
	
	/*@Arguments({"jeecgMinidao", "page", "rows"})
	public List<Map> getAllEntities(JeecgMinidaoEntity jeecgMinidao, int page, int rows);

	@Arguments({"jeecgMinidao", "page", "rows"})
	@ResultType(JeecgMinidaoEntity.class)
	public List<JeecgMinidaoEntity> getAllEntities2(JeecgMinidaoEntity jeecgMinidao, int page, int rows);*/

	//@Arguments("id")
	//JeecgMinidaoEntity getJeecgMinidao(String id);

/*	
*/

	/*@Arguments("jeecgMinidao")
	int update(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void insert(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void delete(JeecgMinidaoEntity jeecgMinidao);*/
	
	@Arguments("log")
	@ResultType(JeecgLogReport.class)
	List<JeecgLogReport> getLogReportData(JeecgLogReport log);
	
	@Arguments("log")
	List<Map<String, Object>> getLogChartData(JeecgLogReport log);
}

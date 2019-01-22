package org.jeecgframework.web.system.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.rms.employee.entity.EmployeeEntity;
import com.rms.employee.service.EmployeeServiceI;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.DynamicDBUtil;
public class UpdateEmployee implements Job {
	
	private static final Logger logger = Logger.getLogger(UpdateEmployee.class);
	
	private final static  String dbKey = "OA";
	@Autowired
	private EmployeeServiceI employeeServiceI;
	
	public void run() {
		long start = System.currentTimeMillis();
		logger.info("===================获取OA系统员工信息--并更新到本地--定时任务开始=====================");
		updateEmployees();
		logger.info("===================获取OA系统员工信息--并更新到本地--定时任务结束=====================");
		long end = System.currentTimeMillis();
		long times = end - start;
		logger.info("总耗时"+times+"毫秒");
	}
	
	private void updateEmployees() {
		String sql="select m.*,o.name orgname  from V3XUSER.ORG_MEMBER m left join V3XUSER.ORG_UNIT o on o.id=m.org_department_id WHERE ORG_DEPARTMENT_ID!=-1";
		
		try {
			List<Map<String,Object>> maps = (List<Map<String,Object>>)DynamicDBUtil.findList(dbKey, sql, null);
			for (Map<String, Object> map : maps) {
				
				BigDecimal employeeId = (BigDecimal) map.get("ID");	//员工ID
				EmployeeEntity Employee = employeeServiceI.findUniqueByProperty(EmployeeEntity.class, "employeeId", employeeId.toString());
				if(Employee==null) {//如果存在用户，不必更新
					String bianhao = (String) map.get("code");//员工编号	
					String employName = (String) map.get("name");//姓名
					String orgname = (String) map.get("orgname");//所属部门
					BigDecimal isEnable = (BigDecimal) map.get("is_enable");//是否在职  
					String email = (String) map.get("ext_attr_2");//邮箱
					BigDecimal status = (BigDecimal) map.get("status");//在职状态
					//String asset_id = (String) map.get("asset_id");//名下资产
					EmployeeEntity employee = new EmployeeEntity();
					employee.setEmployeeId(employeeId.toString());
					employee.setEmployeeNo(bianhao);
					employee.setEmployeeName(employName);
					employee.setDepartment(orgname);
					employee.setEnable("1".equals(isEnable.toString())?"是":"否");
					employee.setEmali(email);
					employee.setStatus(status.toString());
					
					employeeServiceI.save(employee);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DynamicDBUtil.closeDBkey(dbKey);//关闭数据源
		}
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		run();
	}

}

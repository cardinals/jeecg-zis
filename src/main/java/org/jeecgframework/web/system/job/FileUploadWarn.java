package org.jeecgframework.web.system.job;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.web.cgform.util.DataHandlerUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeecg.bankaccount.controller.BankAccountController;
import com.jeecg.reportemail.entity.ReportEmailEntity;
import com.jeecg.reportemail.service.ReportEmailServiceI;
@Service("fileUploadWarn")
public class FileUploadWarn extends CommonServiceImpl implements Job {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankAccountController.class);

	private final static  String dbKey = "ctptadb";
	@Autowired
	private ReportEmailServiceI reportEmailService;
	
	/**
	 * 正式环境sql       
	 */
	 	
	//获取当前系统时间
    private final static String currentSystemTime = "select t.c_value from  hsord.tsysparameter@dbl_tadb t where t.c_item ='SYSDATE'";
    //是否是工作日,当t.l_workflag为1 是工作日 否则非工作日
 	private final static String isWorkDay = "select t.d_date,t.l_workflag  from   ta4.topenday@dbl_tadb  t where t.d_date = ? ";
 	//直销库 select c_value tsysparameter表 where c_item=‘UPTOTJYDNUM’，c_value值 |BK:1|  0-未上传 1-上传
 	private final static String isfileUpload = "select t.c_value from hsds.tsysparameter@dbl_tadb t where t.c_item='UPTOTJYDNUM'";
 	
 	
 	/**
	 * 	如果未上传增 发邮件提醒业务人员 xiongxiaoxue@zrfunds.com.cn zhangyuanyuan@zrfunds.com.cn 抄送 itservice@zrfunds.com.cn
	 * 	邮件标题： 【提醒】请尽快上传平安银行份额文件
		邮件内容： 您好！请尽快上传平安银行份额文件并通知平安相关人员，否则可能造成银行垫资户冻结，谢谢！ 
	 */
 	public boolean isFileUpload() {
 		
 		boolean flag = true;//默认已上传
 		try {
			//1,获取当前系统时间
			Map<String,Object> currentTime = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, currentSystemTime, null);
			String currentDay = (String)currentTime.get("C_VALUE");
			//2,判断当前系统时间是否是工作日
			Map<String,Object> workFlagObject = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, isWorkDay, currentDay);
			String  workFlag = workFlagObject.get("L_WORKFLAG").toString();
			if("1".equals(workFlag)) {
				logger.info("今天是工作日");
				Map<String,Object> isfileUploadObj = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, isfileUpload, null);
				if(isfileUploadObj != null) {
					String isfileUpload = (String)isfileUploadObj.get("C_VALUE");
					logger.info("文件是否上传原字段-"+isfileUpload);
					if(StringUtils.isNotEmpty(isfileUpload)) {
						//切割字符串-c_value值 |BK:1|  0-未上传 1-上传
						String isFileUploadFlag = fileUploadFlag(isfileUpload);
						if("0".equals(isFileUploadFlag)) {
							flag = false;//文件未上传
							logger.info("文件未上传-"+isFileUploadFlag);
						}
						
						if("1".equals(isFileUploadFlag)) {
							logger.info("文件已上传-"+isFileUploadFlag);
						}
					}
				}
			}else {
				logger.info("今天不是工作日!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DynamicDBUtil.closeDBkey(dbKey);
		}
 		
 		return flag;
 	}
	
	public void run() {
		try {
			long start = System.currentTimeMillis();
			logger.info("===================判断文件是否上传---定时发送任务开始===================");
			boolean isFileUpload = isFileUpload();
			if(!isFileUpload) {
				//获取收件人与抄送人
				 ReportEmailEntity reportEmail = reportEmailService.findUniqueByProperty(ReportEmailEntity.class, "reportName", "fileUploadWarn");
			     String sendto = reportEmail!=null?reportEmail.getSentTo():"liangqingyang@zrfunds.com.cn";
			     String sendcc = reportEmail!=null?reportEmail.getSentCc():"liangqingyang@zrfunds.com.cn";
			     logger.info(sendto);
			     if(StringUtils.isNotEmpty(sendto)||StringUtils.isNotEmpty(sendcc)) {
			    	 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
					 Properties properties = ftpConfig.getProperties();
					 String mailBubject = "【提醒】请尽快上传平安银行份额文件";
					 String mailBody = "您好！请尽快上传平安银行份额文件并通知平安相关人员，否则可能造成银行垫资户冻结，谢谢！";
					 DataHandlerUtil.sendMail(mailBody,mailBubject,properties,sendto,sendcc);
			     }
			}
			logger.info("===================判断文件是否上传---定时发送任务结束===================");
			long end = System.currentTimeMillis();
			long times = end - start;
			logger.info("总耗时"+times+"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
			
			 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
			 Properties properties = ftpConfig.getProperties();
			 String mailBubject = "【警告】平安银行定时任务出错";
			 String mailBody = e.getMessage();
			 String sendto = "majianchao@zrfunds.com.cn";
			 String sendcc = "wangxiangyu@zrfunds.com.cn,liangqingyang@zrfunds.com.cn";
			 DataHandlerUtil.sendMail(mailBody,mailBubject,properties,sendto,sendcc);
		}
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		run();
	}
	/**
	 * 切割字符串  |BK:1| ,获取数字0或1
	 * @param isFileUpload
	 * @return
	 */
	private static String fileUploadFlag(String isFileUpload) {
		String flag = "";
		if(StringUtils.isNotEmpty(isFileUpload)) {
			String[] fileSplit = isFileUpload.split(":");
			if(fileSplit.length>0) {
				String[] fileSplit2 =fileSplit[1].split("\\|");
				if(fileSplit2.length>0) {
					flag = fileSplit2[0];
				}
			}
		}
		return flag;
	}
	
}

package org.jeecgframework.web.cgform.util;
import java.util.Properties;

import org.apache.log4j.Logger;


public  class  DataHandlerUtil {
	
	private static final Logger LOG = Logger.getLogger(DataHandlerUtil.class);
	
	public static void commonMailSending(StringBuffer tbody, String subject,
			Properties properties) {
		MailSends mail = new MailSends(properties);
		try {
			mail.sendmail(subject, tbody.toString());
			LOG.info("邮件发送成功!");
		} catch (Exception e) {
			LOG.info("邮件发送失败");
			e.printStackTrace();
		}
	}
	
	public static void commonMailSendingwithAttach(String tbody,String subject,String dayMailFile,String dayMailFileName,Properties properties){
		try {
			MailSends mail = new MailSends(properties);
			mail.sendmailWithAttach(subject,tbody,dayMailFile+dayMailFileName,dayMailFileName);
			LOG.info("commonMailSendingWithAttach邮件发送成功!");
		} catch (Exception e) {
			LOG.info("commonMailSendingWithAttach邮件发送完毕失败!");
			e.printStackTrace();
			LOG.info(e.getMessage());
		}
	}
	/*
	 * 点击发送按钮，将报表生成的excel 发送给配置好的用户-2018-08-06
	 */
	public static void sendMailForExcel(String tbody,String subject,String dayMailFile,String dayMailFileName,Properties properties,String sendto,String sendcc){
		try {
			MailSends mail = new MailSends(properties);
			mail.sendmailWithExcel(subject,tbody,dayMailFile+dayMailFileName,dayMailFileName, sendto, sendcc);
			LOG.info("sendMailForExcel邮件发送成功!");
		} catch (Exception e) {
			LOG.info("sendMailForExcel邮件发送失败!");
			e.printStackTrace();
			LOG.info(e.getMessage());
			throw new RuntimeException("邮件发送失败！");
		}
	}
	/*
	 * 点击发送按钮，将发送给配置好的用户-2018-08-06
	 */
	public static void sendMail(String tbody,String subject,Properties properties,String sendto,String sendcc){
		try {
			MailSends mail = new MailSends(properties);
			mail.sendmail(subject,tbody, sendto, sendcc);
			LOG.info("sendmail邮件发送成功!");
		} catch (Exception e) {
			LOG.info("sendmail邮件发送失败!");
			e.printStackTrace();
			LOG.info(e.getMessage());
			throw new RuntimeException("邮件发送失败！");
		}
	}
	
	
}

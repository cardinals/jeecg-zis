package org.jeecgframework.web.cgform.util;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.jeecgframework.web.cgform.exception.UtilException;



/**
 * 发邮件类MailSend
 * 遗留问题：可以发zrfunds.com.cn，但网易、qq等邮箱发送失败
 * 单例？
 * */

public class MailSends {
	
	private static final Logger LOG = Logger.getLogger(MailSends.class);
	private ResourceBundle paramFile;
	private Properties propertiesFile;
	
	private static String mailFromName = "信息技术部-智能管理平台";
	
	
	public MailSends(){
	}
	public MailSends(ResourceBundle paramFile){
		this.paramFile=paramFile;
	}
	public MailSends(Properties propertiesFile){
		this.propertiesFile = propertiesFile;
	}
	/**
	 * 方法：sendmail
	 * 功能描述：发邮件
	 * */
	public void sendmail(String subject, String text) throws Exception { 
		//1.配置邮件服务器参数
		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable", Boolean.parseBoolean(this.propertiesFile.getProperty("mail.smtp.ssl.enable")));
		props.put("mail.smtp.host",  this.propertiesFile.getProperty("mail.smtp.host"));//mail.zrfunds.com.cn
		props.put("mail.smtp.port",  Integer.parseInt(this.propertiesFile.getProperty("mail.smtp.port")));//公司邮箱465，587
		props.put("mail.smtp.auth", Boolean.parseBoolean(this.propertiesFile.getProperty("mail.smtp.auth")));//有缺陷！
		//2.构造Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(propertiesFile.getProperty("username"), propertiesFile.getProperty("password"));
		    }
		});
		//3.设置邮件标题、内容等
		try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(this.propertiesFile.getProperty("from"),mailFromName));//发件人
		    String[] cc = null; //抄送人
		    if(propertiesFile.getProperty("sendCC")!=null){
		    	cc = propertiesFile.getProperty("sendCC").split(",");
			    InternetAddress[] cc2 = new InternetAddress[cc.length];  //抄送人列表
		        for (int i = 0; i < cc.length; i++) {  
		        	cc2[i] = new InternetAddress(cc[i]);  
		        } 
	            message.setRecipients(Message.RecipientType.CC, cc2);//抄送人
		    }
            String[] to = propertiesFile.getProperty("sendTo").split(",");
	        InternetAddress[] to2 = new InternetAddress[to.length];  //收件人列表
	        for (int i = 0; i < to.length; i++) {  
	        	to2[i] = new InternetAddress(to[i]);  
	        }  
	        message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,to2);
	        message.setSubject(subject);//主题
	        LOG.info("Send To:"+java.util.Arrays.asList(to).toString());
	        if(propertiesFile.getProperty("sendCC")!=null){
	        	 LOG.info("Send CC:"+java.util.Arrays.asList(cc).toString());
	        }
	        LOG.info("Send Subject:"+subject);
		    LOG.info("Send Context:"+text);
		    message.setContent(text, "text/html;charset=UTF-8");//发送内容
		//	            message.setContent("<html><body><table ><tr>  <td>100</td>  <td>200</td> <td>300</td></tr><tr>  <td>400</td>  <td>500</td>  <td>600</td></tr></table></body></html>", "text/html;charset=UTF-8");
		//	            message.setContent("<div><a href='http://www.fkjava.org'>测试的HTML邮件</a><a href='http://www.fkjava.org'>测试的HTML邮件</a></div>", "text/html;charset=UTF-8");
		    //data
            message.setSentDate(new Date());  
            message.saveChanges();  
            Transport.send(message);
            LOG.info("Send Successfully!");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
//            e.printStackTrace();
        	LOG.error(UtilException.getTrace(e));
        }
	 }
	
	/**
	 * 测试程序入口
	 * */
	/*public static void main(String[] args) {
		String from = "itservice@zrfunds.com.cn";
		String sendTo[] = {  "ios@zrfunds.com.cn"};
		String sendCC[] = { "zhangsong@zrfunds.com.cn", "ios@zrfunds.com.cn"};
		String subject = "主题";
		String text = "内容";
		MailSends maiSend = new MailSends(PropertiesHandlerUtil.getMailPropertiesFile("params"));
		try {
			maiSend.sendmail(subject,text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("发送完毕！");
	}*/
	
	
	
	/**
	 * 方法：sendmail
	 * 功能描述：发邮件
	 * */
	public void sendmailWithAttach(String subject, String text,String dayMailFilePath,String dayMailFileName) throws Exception { 
		//1.配置邮件服务器参数
		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable", Boolean.parseBoolean(this.propertiesFile.getProperty("mail.smtp.ssl.enable")));
		props.put("mail.smtp.host",  this.propertiesFile.getProperty("mail.smtp.host"));//mail.zrfunds.com.cn
		props.put("mail.smtp.port",  Integer.parseInt(this.propertiesFile.getProperty("mail.smtp.port")));//公司邮箱465，587
		//props.put("mail.smtp.auth", Boolean.parseBoolean(this.propertiesFile.getProperty("mail.smtp.auth")));//有缺陷！
		props.put("mail.smtp.auth", true);//新加的-2018-06-04
//		props.put("mail.transport.protocol", "smtp");
		//2.构造Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(propertiesFile.getProperty("username"), propertiesFile.getProperty("password"));
		    }
		});
	    //2.设置发件人和抄送人、收件人、主题
		Message message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress(this.propertiesFile.getProperty("from"),mailFromName));//发件人
			String[] cc = null; //抄送人
			if(propertiesFile.getProperty("sendCC")!=null){
				cc = propertiesFile.getProperty("sendCC").split(",");
			    InternetAddress[] cc2 = new InternetAddress[cc.length];  //抄送人列表
			    for (int i = 0; i < cc.length; i++) {  
			    	cc2[i] = new InternetAddress(cc[i]);  
			    } 
			    message.setRecipients(Message.RecipientType.CC, cc2);//抄送人
			}
			String[] to = propertiesFile.getProperty("sendTo").split(",");
			InternetAddress[] to2 = new InternetAddress[to.length];  //收件人列表
			for (int i = 0; i < to.length; i++) {  
				to2[i] = new InternetAddress(to[i]);  
			}  
			message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,to2);
			message.setSubject(subject);//主题
			
			
			//3. 邮件消息内容设置，包括两个附件和一段正文
			Multipart msgPart = new MimeMultipart("mixed");
			message.setContent(msgPart);		
			
			MimeBodyPart body = new MimeBodyPart();     //表示正文
			MimeBodyPart attach1 = new MimeBodyPart();  //表示附件1
//	    MimeBodyPart attach2 = new MimeBodyPart();  //表示附件2
			msgPart.addBodyPart(body);
			msgPart.addBodyPart(attach1);
//	    msgPart.addBodyPart(attach2);
			
			/*以下为设置正文*/
			/*正文是文字和图片混合的*/
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			MimeBodyPart content = new MimeBodyPart(); //文字
			//MimeBodyPart img = new MimeBodyPart();     //图片
			contentPart.addBodyPart(content);
			/*contentPart.addBodyPart(img);
			ByteArrayDataSource fileds = new ByteArrayDataSource(new FileInputStream("D:\\touxiang.jpg"),"application/octet-stream");  
			DataHandler imgDataHandler = new DataHandler(fileds);
			img.setDataHandler(imgDataHandler);
			img.setHeader("Content-ID", "<touxiang.jpg>");
			img.setFileName("touxianga.jpg");*/
			//设置文字内容
			/**
			 * 注意：在html代码中要想显示刚才的touxiang.jpg
			 * src里不能直接写Content-ID的值，要用cid:这种方式
			 */
//	    content.setContent("<div style='color:red;font-size:18px;'>从sohu发来的邮件</div>：我这里有一张图片<img src='cid:touxiang.jpg' alt='touxiang' width=\"100px\" height='100px' />,好看吗？", "text/html;charset=utf-8");
			if(text == null){
				content.setContent("详见附件!","text/html;charset=utf-8");
			}else{
				content.setContent(text+",详见附件!","text/html;charset=utf-8");
			}
			
			/*正文内容设置结束*/
			
			/*下面为设置附件*/
			attach1.setDataHandler(new DataHandler(new FileDataSource(dayMailFilePath)));
			attach1.setFileName(dayMailFileName);
			
			message.saveChanges();
			//把邮件以文件的形式写入到磁盘
			/*OutputStream os = new FileOutputStream("E:\\demo.eml");
			message.writeTo(os);
			os.close();*/
			Transport.send(message);
			//LOG.info("FA Send Successfully!");
		} catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
	 
	}
	/*
	 * 点击发送按钮，将报表生成的excel 发送给配置好的用户-2018-08-06
	 */
	public void sendmailWithExcel(String subject, String text,String dayMailFilePath,String dayMailFileName,String sendto,String sendcc) throws Exception { 
		//1.配置邮件服务器参数
		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable",false);
		props.put("mail.smtp.host",this.propertiesFile.getProperty("mail.smtp.host"));//mail.zrfunds.com.cn
		props.put("mail.smtp.port",Integer.parseInt(this.propertiesFile.getProperty("mail.smtp.port")));//公司邮箱465，587
		props.put("mail.smtp.auth",true);
		
		//2.构造Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(propertiesFile.getProperty("username"), propertiesFile.getProperty("password"));
		        //return new PasswordAuthentication("test@service.com.cn", "zrjj@1234");
		    }
		});
	    //2.设置发件人和抄送人、收件人、主题
		Message message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress(this.propertiesFile.getProperty("from"),mailFromName));//发件人
			String[] cc = null; //抄送人
			if(sendcc!=null){
				cc = sendcc.split(",");
			    InternetAddress[] cc2 = new InternetAddress[cc.length];  //抄送人列表
			    for (int i = 0; i < cc.length; i++) {  
			    	cc2[i] = new InternetAddress(cc[i]);  
			    } 
			    message.setRecipients(Message.RecipientType.CC, cc2);//抄送人
			}
			String[] to = sendto.split(",");
			InternetAddress[] to2 = new InternetAddress[to.length];  //收件人列表
			for (int i = 0; i < to.length; i++) {  
				to2[i] = new InternetAddress(to[i]);  
			}  
			message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,to2);
			message.setSubject(subject);//主题
			
			
			//3. 邮件消息内容设置，包括两个附件和一段正文
			Multipart msgPart = new MimeMultipart("mixed");
			message.setContent(msgPart);		
			
			MimeBodyPart body = new MimeBodyPart();     //表示正文
			MimeBodyPart attach1 = new MimeBodyPart();  //表示附件1
//	    MimeBodyPart attach2 = new MimeBodyPart();  //表示附件2
			msgPart.addBodyPart(body);
			msgPart.addBodyPart(attach1);
//	    msgPart.addBodyPart(attach2);
			
			/*以下为设置正文*/
			/*正文是文字和图片混合的*/
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			MimeBodyPart content = new MimeBodyPart(); //文字
			//MimeBodyPart img = new MimeBodyPart();     //图片
			contentPart.addBodyPart(content);
			/*contentPart.addBodyPart(img);
			ByteArrayDataSource fileds = new ByteArrayDataSource(new FileInputStream("D:\\touxiang.jpg"),"application/octet-stream");  
			DataHandler imgDataHandler = new DataHandler(fileds);
			img.setDataHandler(imgDataHandler);
			img.setHeader("Content-ID", "<touxiang.jpg>");
			img.setFileName("touxianga.jpg");*/
			//设置文字内容
			/**
			 * 注意：在html代码中要想显示刚才的touxiang.jpg
			 * src里不能直接写Content-ID的值，要用cid:这种方式
			 */
//	    content.setContent("<div style='color:red;font-size:18px;'>从sohu发来的邮件</div>：我这里有一张图片<img src='cid:touxiang.jpg' alt='touxiang' width=\"100px\" height='100px' />,好看吗？", "text/html;charset=utf-8");
			if(text == null){
				content.setContent("详见附件!","text/html;charset=utf-8");
			}else{
				content.setContent(text+",详见附件!","text/html;charset=utf-8");
			}
			
			/*正文内容设置结束*/
			
			/*下面为设置附件*/
			attach1.setDataHandler(new DataHandler(new FileDataSource(dayMailFilePath)));
			attach1.setFileName(dayMailFileName);
			
			message.saveChanges();
			//把邮件以文件的形式写入到磁盘
			/*OutputStream os = new FileOutputStream("E:\\demo.eml");
			message.writeTo(os);
			os.close();*/
			Transport.send(message);
			//LOG.info("FA Send Successfully!");
		} catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败");
		}
	 
	}
	public void sendmail(String subject, String text,String sendto,String sendcc) throws Exception { 
		//1.配置邮件服务器参数
		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable",false);
		props.put("mail.smtp.host",this.propertiesFile.getProperty("mail.smtp.host"));//mail.zrfunds.com.cn
		props.put("mail.smtp.port",Integer.parseInt(this.propertiesFile.getProperty("mail.smtp.port")));//公司邮箱465，587
		props.put("mail.smtp.auth",true);//本地
		//props.put("mail.smtp.auth","true");//服务器
		
		//2.构造Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(propertiesFile.getProperty("username"), propertiesFile.getProperty("password"));
		    }
		});
	    //2.设置发件人和抄送人、收件人、主题
		Message message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress(this.propertiesFile.getProperty("from"),mailFromName));//发件人
			String[] cc = null; //抄送人
			if(sendcc!=null && !(sendcc.equals(""))){
				cc = sendcc.split(",");
			    InternetAddress[] cc2 = new InternetAddress[cc.length];  //抄送人列表
			    for (int i = 0; i < cc.length; i++) {  
			    	cc2[i] = new InternetAddress(cc[i]);  
			    } 
			    message.setRecipients(Message.RecipientType.CC, cc2);//抄送人
			}
			String[] to = sendto.split(",");
			InternetAddress[] to2 = new InternetAddress[to.length];  //收件人列表
			for (int i = 0; i < to.length; i++) {  
				to2[i] = new InternetAddress(to[i]);  
			}  
			message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,to2);
			message.setSubject(subject);//主题
			
			
			//3. 邮件消息内容设置，包括两个附件和一段正文
			Multipart msgPart = new MimeMultipart("mixed");
			message.setContent(msgPart);		
			
			MimeBodyPart body = new MimeBodyPart();     //表示正文
			
			msgPart.addBodyPart(body);
			
			/*以下为设置正文*/
			/*正文是文字和图片混合的*/
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			MimeBodyPart content = new MimeBodyPart(); //文字
			contentPart.addBodyPart(content);
			if(text != null){
				content.setContent(text,"text/html;charset=utf-8");
			}
			
			message.saveChanges();

			Transport.send(message);
			//LOG.info("FA Send Successfully!");
		} catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败");
		}
	 
	}
	
	
	public static void main(String[] args) {
		/*MailSends mailSends = new MailSends();
		try {
			mailSends.sendmailWithAttach2("测试主题222", "测试内容666", "D:\\result.xls", "result.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		System.out.println(System.currentTimeMillis()); 
		String filePath="c:\\image\\product\\001.png";
		String[] aa=filePath.split("\\\\");
		String name=aa[aa.length-1];
		System.out.println(name);
		
		String fileName2=name.substring(0,name.lastIndexOf("."));
		System.out.println(fileName2);
	}
	
	//测试用 开始
	
	public void sendmailWithAttach2(String subject, String text,String dayMailFilePath,String dayMailFileName) throws Exception { 
		//1.配置邮件服务器参数
		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable", Boolean.parseBoolean("true"));
		props.put("mail.smtp.host",  "mail.zrfunds.com.cn");//mail.zrfunds.com.cn
		props.put("mail.smtp.port",  25);//公司邮箱465，587
		props.put("mail.smtp.auth", true);//新加的-2018-06-04
		//2.构造Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("itservice@service.com.cn", "zrjj@1234");
		    }
		});
	    //2.设置发件人和抄送人、收件人、主题
		Message message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress("itservice@zrfunds.com.cn",mailFromName));//发件人
			String[] cc = null; //抄送人
			//if(propertiesFile.getProperty("sendCC")!=null){
				cc = "majianchao@zrfunds.com.cn,chenmiaolei@zrfunds.com.cn".split(",");
			    InternetAddress[] cc2 = new InternetAddress[cc.length];  //抄送人列表
			    for (int i = 0; i < cc.length; i++) {  
			    	cc2[i] = new InternetAddress(cc[i]);  
			    } 
			    message.setRecipients(Message.RecipientType.CC, cc2);//抄送人
			//}
			String[] to = "majianchao@zrfunds.com.cn,chenmiaolei@zrfunds.com.cn".split(",");
			InternetAddress[] to2 = new InternetAddress[to.length];  //收件人列表
			for (int i = 0; i < to.length; i++) {  
				to2[i] = new InternetAddress(to[i]);  
			}  
			message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,to2);
			message.setSubject(subject);//主题
			
			
			//3. 邮件消息内容设置，包括两个附件和一段正文
			Multipart msgPart = new MimeMultipart("mixed");
			message.setContent(msgPart);		
			
			MimeBodyPart body = new MimeBodyPart();     //表示正文
			MimeBodyPart attach1 = new MimeBodyPart();  //表示附件1
			msgPart.addBodyPart(body);
			msgPart.addBodyPart(attach1);
			
			/*以下为设置正文*/
			/*正文是文字和图片混合的*/
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			MimeBodyPart content = new MimeBodyPart(); //文字
			//MimeBodyPart img = new MimeBodyPart();     //图片
			contentPart.addBodyPart(content);
			
			//设置文字内容
			/**
			 * 注意：在html代码中要想显示刚才的touxiang.jpg
			 * src里不能直接写Content-ID的值，要用cid:这种方式
			 */
			if(text == null){
				content.setContent("详见附件!","text/html;charset=utf-8");
			}else{
				content.setContent(text+",详见附件!","text/html;charset=utf-8");
			}
			
			/*正文内容设置结束*/
			
			/*下面为设置附件*/
			attach1.setDataHandler(new DataHandler(new FileDataSource(dayMailFilePath)));
			attach1.setFileName(dayMailFileName);
			
			message.saveChanges();
			
			Transport.send(message);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
	 
	}
	
	//测试用 结束
}



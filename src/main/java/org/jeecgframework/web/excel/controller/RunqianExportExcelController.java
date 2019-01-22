package org.jeecgframework.web.excel.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.web.cgform.util.DataHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.reportemail.entity.ReportEmailEntity;
import com.jeecg.reportemail.service.ReportEmailServiceI;
import com.raqsoft.report.model.ReportDefine;
import com.raqsoft.report.usermodel.Context;
import com.raqsoft.report.usermodel.Engine;
import com.raqsoft.report.usermodel.IReport;
import com.raqsoft.report.util.JsonUtil;
import com.raqsoft.report.util.ReportUtils;

@Controller
@RequestMapping(value="/runqianExportExcel")
public class RunqianExportExcelController extends BaseController   {
	@Autowired
	private ReportEmailServiceI reportEmailService;
	
	private static final long serialVersionUID = 1L;

				/**
			     * Constructor of the object.
			     */
			    public RunqianExportExcelController() {
			            super();
			    }

	        /**
	         * Destruction of the servlet. <br>
	         */
	       /* public void destroy() {
	                super.destroy(); // Just puts "destroy" string in log
	                // Put your code here
	        }*/

	        /**
	         * The doGet method of the servlet. <br>
	         *
	         * This method is called when a form has its tag value method equals to get.
	         * 
	         * @param request the request send by the client to the server
	         * @param response the response send by the server to the client
	         * @throws ServletException if an error occurred
	         * @throws IOException if an error occurred
	         */
	        /**
	         * 集算报表提供了多个导出Excel的方法，除了可以导出xls格式，也可以导出xlsx格式文件，开发人员可以根据需求选择导出Excel的方法：
        ReportUtils.exportToExcel(java.io.OutputStream os, IReport report, boolean pageBroken); 
        ReportUtils.exportToExcel(java.io.OutputStream os, IReport report, boolean pageBroken, int dispRatio); 
        ReportUtils.exportToExcel(java.lang.String fileName, IReport report, boolean pageBroken); 
        ReportUtils.exportToExcel(java.lang.String fileName, IReport report, boolean pageBroken, int dispRatio);
        ReportUtils.exportToExcel 2007(java.lang.String fileName,IReport report,boolean pageBroken) 
        ReportUtils.exportToExcel 2007(java.io.OutputStream os,IReport report,boolean pageBroken)

		导出PDF与导出Excel类似，调用相应导出PDF的方法即可，集算报表提供导出PDF的方法包括：
			
        ReportUtils.exportToPDF(java.io.OutputStream os, IReport report); 
        ReportUtils.exportToPDF(java.io.OutputStream os, IReport report, boolean pageBroken, boolean graphicOut) 
        ReportUtils.exportToPDF(java.lang.String fileName, IReport report); 
        ReportUtils.exportToPDF(java.lang.String fileName, IReport report, boolean pageBroken, boolean graphicOut)

	         */
	    	@RequestMapping(value="/exportExcel")
	    	@ResponseBody
	        public AjaxJson exportExcel(HttpServletRequest request, HttpServletResponse response)
	                        throws ServletException, IOException {
	    			AjaxJson j = new AjaxJson();
	                String report = request.getParameter("rpx");
	               //保证报表名称的完整性
	                int iTmp = 0;
	                if( (iTmp = report.lastIndexOf(".rpx")) <= 0 ){
	                        report = report + ".rpx";
	                        iTmp = 0;
	                }
	                String[] reportNames=report.split("/");
        			String reportName=reportNames[reportNames.length-1];
        			reportName=reportName.substring(0,reportName.lastIndexOf("."));
        			
//	                String i_fullname =new String(request.getParameter("i_fullname").getBytes("iso8859-1"),"UTF-8");
        			//Enumeration paramNames =(Enumeration)request.getSession().getAttribute(reportName);
	                //String i_fullname =request.getParameter("i_fullname");
	                
        			//虚拟路径
        			//String reportFile = request.getSession().getServletContext().getRealPath("/WEB-INF/reportFiles/"+report);
	                //硬盘下的路径
	                String reportFile = "C:\\raqsoft\\reportFiles\\"+report;
	                
	                //HashMap map=new HashMap();
					
					//Enumeration paramNames = request.getParameterNames();
					
					/*if(paramNames!=null){
					
					    while(paramNames.hasMoreElements()){
					    	//paramNames.nextElement();
					
					        String paramName = (String) paramNames.nextElement();
					
					        String paramValue=request.getParameter(paramName);
					
					        //把参数名和参数值分别加入到hashmap的key和value里
					        if(!"rpx".equals(paramName)||!"scroll".equals(paramName)||!"action".equals(paramName)){
					        	map.put(paramName,paramValue);
					        }
					        
					    }
					
					}*/
					//request.getSession().removeAttribute(reportName);
	                HashMap map =(HashMap)request.getSession().getAttribute(reportName);
	                Context context = new Context(); 
	                try {
	                        ReportDefine rd = (ReportDefine)ReportUtils.read(reportFile);
	                        //context.setParamValue("i_fullname", i_fullname); 
	                        context.setParamMap(map);
	                        Engine engine = new Engine(rd, context);   //构造报表引擎
	                        IReport iReport = engine.calc();   //运算报表
	                        String filePath = "C:\\raqsoft\\exportFiles\\onclick\\";
	                        String fileName = reportName+".xls";
	                        			
	                        String excelFileName = filePath + fileName;
	                        //导出excel文件
	                        ReportUtils.exportToExcel(excelFileName, iReport, true);
	                        request.getSession().removeAttribute(reportName);
	                        //调用发送邮件开始...2018-08-06
	                        PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
	            	        Properties properties = ftpConfig.getProperties();
	            	        DataHandlerUtil  dataHandlerUtil =new DataHandlerUtil();
	            	        String mailBubject = "";
	            	        String mailBody = "您好,请查看";
	            	        if("huankuan".equals(reportName)){
	                        	fileName = "report_zrfunds.xls";
	                        	mailBubject = "基金快赎业务还款明细表(中融基金)";
	                        }	
	            	        //收件人、抄送人 配置到数据库中
	            			//根据表中的fileName字段去查询   sendCC  sendTo
	            	        ReportEmailEntity reportEmail = reportEmailService.findUniqueByProperty(ReportEmailEntity.class, "reportName", reportName);
	            	        String sendto = reportEmail!=null?reportEmail.getSentTo():"";
	            	        String sendcc = reportEmail!=null?reportEmail.getSentCc():"";
	            	        dataHandlerUtil.sendMailForExcel(mailBody,mailBubject,filePath,fileName,properties,sendto,sendcc);
	            	        j.setMsg("邮件发送成功!");
	            	        //...调用发送邮件结束
	            	        
	                } catch (Throwable e) {
	                        e.printStackTrace();
	                        j.setSuccess(false);
	                        j.setMsg("邮件发送失败!!!");
	                }
	                return j;
	        }
	    	/**
	    	 * 导出excel-带有日期格式的
	    	 * @param request
	    	 * @param response
	    	 * @return
	    	 * @throws ServletException
	    	 * @throws IOException
	    	 */

	    	@RequestMapping(value="/exportExcelWithDate")
	    	@ResponseBody
	        public AjaxJson exportExcelWithDate(HttpServletRequest request, HttpServletResponse response)
	                        throws ServletException, IOException {
	    			AjaxJson j = new AjaxJson();
	                String report = request.getParameter("rpx");
	                
	               //保证报表名称的完整性
	                int iTmp = 0;
	                if( (iTmp = report.lastIndexOf(".rpx")) <= 0 ){
	                        report = report + ".rpx";
	                        iTmp = 0;
	                }
	                String[] reportNames=report.split("/");
        			String reportName=reportNames[reportNames.length-1];
        			reportName=reportName.substring(0,reportName.lastIndexOf("."));
        			
        			//硬盘下的路径
	                String reportFile = "C:\\raqsoft\\reportFiles\\"+report;
	                String startTime = "";
	                boolean isHasstartTime = false;
	                HashMap map =(HashMap)request.getSession().getAttribute(reportName);
	                if(map!=null){
		                if(map.containsKey("rq")){
		                	 startTime =  (String)map.get("rq");
		                	if(StringUtils.isNotEmpty(startTime)){
		                		startTime = startTime.replaceAll("-", "");
		                		isHasstartTime = true;
		                	}
		                }
	                }
	                Context context = new Context(); 
	                try {
	                        ReportDefine rd = (ReportDefine)ReportUtils.read(reportFile);

	                        context.setParamMap(map);
	                        Engine engine = new Engine(rd, context);   //构造报表引擎
	                        IReport iReport = engine.calc();   //运算报表
	                        String filePath = "C:\\raqsoft\\exportFiles\\onclick\\";
	                        String fileName = reportName+".xls";
	                        if(isHasstartTime){
	                        	fileName = reportName+startTime+".xls";
	                        }
	                        String excelFileName = filePath + fileName;
	                        //导出excel文件
	                        //ReportUtils.exportToExcel(excelFileName, iReport, true);
	                        //1、设置response 响应头
	                		response.reset();
	                		response.setCharacterEncoding("UTF-8");
	                		response.setContentType("multipart/form-data");
	                		response.setHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(fileName, "UTF-8"));
	                		
	                        ReportUtils.exportToExcel(response.getOutputStream(), iReport, true);
	                        request.getSession().removeAttribute(reportName);
	                        //文件下载...start
	                        
	                     
	                		/*File file = new File(filePath,fileName);
	                		//2、 读取文件--输入流
	                		InputStream input=new FileInputStream(file);
	                		//3、 写出文件--输出流
	                		OutputStream out = response.getOutputStream();
	                		byte[] buff =new byte[1024];
	                		int index=0;
	                		//4、执行 写出操作
	                		while((index= input.read(buff))!= -1){
	                			out.write(buff, 0, index);
	                			out.flush();
	                		}
	                		//out.close();
	                		input.close();
*/
	                        //文件下载...end
	                      j.setMsg("excel导出成功!");
	            	        
	                } catch (Throwable e) {
	                        e.printStackTrace();
	                        j.setSuccess(false);
	                        j.setMsg("excel导出失败!!!");
	                }
	                return j;
	        }
	    	
	    	
public static void writeToJson(String filePath,String string) throws IOException
{
    File file = new File(filePath);
    char [] stack = new char[1024];
    int top=-1;
//    String string = object.toString();
    StringBuffer sb = new StringBuffer();
    char [] charArray = string.toCharArray();
    for(int i=0;i<charArray.length;i++){
        char c= charArray[i];
        if ('{' == c || '[' == c) {  
            stack[++top] = c; 
            sb.append("\n"+charArray[i] + "\n");  
            for (int j = 0; j <= top; j++) {  
                sb.append("\t");  
            }  
            continue;  
        }
         if ((i + 1) <= (charArray.length - 1)) {  
                char d = charArray[i+1];  
                if ('}' == d || ']' == d) {  
                    top--; 
                    sb.append(charArray[i] + "\n");  
                    for (int j = 0; j <= top; j++) {  
                        sb.append("\t");  
                    }  
                    continue;  
                }  
            }  
            if (',' == c) {  
                sb.append(charArray[i] + "");  
                for (int j = 0; j <= top; j++) {  
                    sb.append("");  
                }  
                continue;  
            }  
            sb.append(c);  
        }  
        Writer write = new FileWriter(file);  
        write.write(sb.toString());  
        write.flush();  
        write.close();  
}
	        /**
	         * The doPost method of the servlet. <br>
	         *
	         * This method is called when a form has its tag value method equals to post.
	         * 
	         * @param request the request send by the client to the server
	         * @param response the response send by the server to the client
	         * @throws ServletException if an error occurred
	         * @throws IOException if an error occurred
	         */
	        public void doPost(HttpServletRequest request, HttpServletResponse response)
	                        throws ServletException, IOException {

	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();
	                out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	                out.println("<HTML>");
	                out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
	                out.println("  <BODY>");
	                out.print("    This is ");
	                out.print(this.getClass());
	                out.println(", using the POST method");
	                out.println("  </BODY>");
	                out.println("</HTML>");
	                out.flush();
	                out.close();
	        }

	        /**
	         * Initialization of the servlet. <br>
	         *
	         * @throws ServletException if an error occurs
	         */
	        public void init() throws ServletException {
	                // Put your code here
	        }

	}


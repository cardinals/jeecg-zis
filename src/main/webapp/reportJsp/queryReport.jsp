<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/raqsoftReport.tld" prefix="report" %>
<%@ page import="java.util.*"%>
<% 
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
	request.setCharacterEncoding( "UTF-8" );
	String appmap = request.getContextPath();
	String report = request.getParameter( "rpx" );
	String scroll = request.getParameter( "scroll" );
	if (scroll==null || scroll.length()==0) scroll = "no";
	String[] reportNames=report.split("/");
	String reportName=reportNames[reportNames.length-1];
	reportName=reportName.substring(0,reportName.lastIndexOf("."));
		
	StringBuffer param=new StringBuffer();
	 HashMap map=new HashMap();
	Enumeration paramNames2 = request.getParameterNames();
	//session.setAttribute(reportName,paramNames2);
	if(paramNames2!=null){
		while(paramNames2.hasMoreElements()){
			String paramName = (String) paramNames2.nextElement();
			String paramValue=request.getParameter(paramName);
			//System.out.println("paramValue="+paramValue);
			if(paramValue!=null){
				//把参数拼成name=value;name2=value2;.....的形式
				 if(!"rpx".equals(paramName)&&!"scroll".equals(paramName)&&!"action".equals(paramName)){
					 //param.append(paramName).append("=").append(paramValue).append(";");
					 System.out.println(paramName);
					 map.put(paramName,paramValue);
				 }
					
			}
		}
	} 
	session.setAttribute(reportName,map);
	//session.setAttribute(reportName,param.toString());
	
%>
<report:html name="report1" reportFileName="<%=report%>"
	funcBarLocation="no"
	needScroll="<%=scroll%>"
	scrollWidth="100%" 
	scrollHeight="100%"
	needImportEasyui="no"
/>
<script language="javascript">
	document.getElementById( "t_page_span" ).innerHTML = getPageCount( "report1" );
	document.getElementById( "report1_currPage" ).innerHTML = getCurrPage( "report1" );
	try{ _resizeScroll('report1');}catch(ex){}
</script>

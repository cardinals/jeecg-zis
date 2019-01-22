<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/raqsoftReport.tld" prefix="report" %>
<% 
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
	request.setCharacterEncoding( "UTF-8" );
	String appmap = request.getContextPath();
	String report = request.getParameter( "rpx" );
	String scroll = request.getParameter( "scroll" );
	String scale = request.getParameter("scale");
	//2017.10.13 by hdw
	String excelFormat = request.getParameter( "excelFormat" );
	String excelPageStyle = request.getParameter( "excelPageStyle" );
	if (scroll==null || scroll.length()==0) scroll = "no";
%>
<report:html name="report1" reportFileName="<%=report%>"
	funcBarLocation="no"
	needScroll="<%=scroll%>"
	scrollWidth="100%" 
	scrollHeight="100%"
	needImportEasyui="no"
	excelFormat="<%=excelFormat%>"
	excelPageStyle="<%=excelPageStyle%>"
	scale="<%=scale %>"
/>
<script language="javascript">
	document.getElementById( "t_page_span" ).innerHTML = getPageCount( "report1" );
	document.getElementById( "report1_currPage" ).innerHTML = getCurrPage( "report1" );
	try{ _resizeScroll('report1');}catch(ex){}
</script>

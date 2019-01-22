<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.raqsoft.dm.*"%>
<%@ page import="com.raqsoft.report.view.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
		String appmap = request.getContextPath();
%>
<title>报表中心</title>
</head>
<script type="text/javascript" src="<%=appmap%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=appmap%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=appmap%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script type="text/javascript">
</script>
<body class="layui-layout-body">
<div class="layui-layout">
	<div>
	<jsp:include page="./header.jsp"></jsp:include>
	</div>
    <div class="layui-side" style="width:300px;height:100%;padding-top:60px;overflow:hidden;z-index:1" id="tree">
   	<iframe id="leftF" name="left1" src="" style="height:100%;" frameborder=0></iframe>
    </div>
	<div id="props" class="layui-body" style="top:60px;left:300px;overflow-y:hidden">
    <iframe id="showProp" name="props" src="" style="height:100%; width:100%;z-index: 9999" frameborder=0></iframe> 
   </div>
</div>
<script>
$(function(){
	showReportList(1);
	layui.use("layer",function(){
	});
});
</script>
</body>
</html>
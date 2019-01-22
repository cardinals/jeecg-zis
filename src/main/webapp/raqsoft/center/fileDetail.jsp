<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="/WEB-INF/dateFormat.tld" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="com.raqsoft.report.view.*"%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <%
    	String contextPath = request.getContextPath();
    %>
<html>
<head>
<script src="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script type="text/javascript">
	function refresh(){
		window.location.href = "";
	}
	function goback(){
		window.location.href = "reportCenterServlet?action=24";
	}
 	
     		
</script>
</head>
<body >
<div>共${fn:length(fileInfoList) }条</div>
<div class="layui-container" align="center">
<table class="layui-table"  id="table" title="详情" imagePath="<%=contextPath%>/images/table">
	<thead>
		<tr>
			<th>报表文件名</th>
			<th>最后修改时间</th>
			<th>文件大小</th>
			<th>读写权限</th>
		</tr>
	</thead>
	<c:forEach items="${fileInfoList }" var="fileInfo">
	<tr>
		<td>${fileInfo.filename }</td>
		<td><fmt:dateFormat value="${fileInfo.lastModified }"></fmt:dateFormat></td>
		<td>${fileInfo.len }&nbsp;B</td>
		<td>
			<c:choose>
			<c:when test="${fileInfo.mode%2 eq 1 }"></c:when>
				读；
			</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>
</div>

</body>
</html>

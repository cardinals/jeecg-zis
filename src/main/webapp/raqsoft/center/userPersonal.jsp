<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.raqsoft.report.view.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
  String contextPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script>
function form_submit(){
	var submiturl = form.action;
	submiturl += "&userId=${userObj.userId}";
	submiturl += "&email="+encodeURIComponent(form.email.value);
	submiturl += "&phone="+encodeURIComponent(form.phone.value);
	console.log(form.phone);
	$.ajax({
		type:'post',
		data:[],
		url:submiturl,
		success:function(){
			alert("保存成功");
			window.location="<%=contextPath%>/raqsoft/center/userPersonal.jsp";
		}
	});
}
</script>
</head>
<body>
	<div align="center" class="layui" style="margin-top:20px">个人信息修改</div>
	<br>
	<div align="center">
	<form id=form method=post
		action="<%=contextPath%>/reportCenterServlet?action=53">
		<input type="hidden" value="${userObj.userId}" id="userId" name="userId"/>
		<TABLE align=center class="layui-table" style="table-layout: fixed; BORDER-COLLAPSE: collapse;width:350px">
			<tr class="">
				<td><span>用户名</span></td>
				<td>
					${userObj.userName}
				</td>
			</tr>
			<tr class="">
				<td><span>邮箱</span></td>
				<td>
					<input class="layui-input" style="width:130px" type="text" name='email' id="email" value="${userObj.email}"/>
				</td>
			</tr>
			<tr class="">
				<td><span>电话</span></td>
				<td>
					<input class="layui-input" type="text" style="width:130px"  name="phone" id="phone" value="${userObj.phone}"/>
				</td>
			</tr>
			<tr class="" style="display:none">
				<td><span>用户宏</span></td>
				<td>
					<input class="layui-input" type="text" style="width:130px"  name="params" id="params" value=""/>
				</td>
			</tr>
			<TR>
				<td width=100% align=center colspan=2>
					<input type="button" class="layui-btn layui-btn-green" onclick="form_submit();" value="提交"/>
				</td>
			</TR>
		</table>
	</form>
	</div>
</body>
</html>
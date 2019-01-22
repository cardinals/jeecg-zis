<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.raqsoft.report.view.*"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<% String appmap = request.getContextPath(); %>
<c:if test="${userObj ne null }">
	<script type="text/javascript">window.location = "<%=appmap %><%=ReportConfig.raqsoftDir%>/center/centerIndex.jsp"</script>
</c:if>
<link rel="stylesheet" href="<%=appmap%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script src="<%=appmap%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=appmap%><%=ReportConfig.raqsoftDir%>/center/layui/layui.js"></script>
<script src="<%=appmap%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
</head>
<script type="text/javascript">
	var device = navigator.userAgent;
	var isMobile = device.indexOf('Mobile') >= 0;
	if(isMobile){
		window.location = "<%=appmap %><%=ReportConfig.raqsoftDir%>/center/mobile/jsp/mobileLogin.jsp"
	}
	function doLogin(){
		var form = $("#loginForm")[0];
		var userName = form.userName.value;
		var password = form.p.value;
		$.ajax({
			data:[],
			type:"post",
			url:"<%=appmap %>/reportCenterServlet?action=3&userName="+encodeURIComponent(userName)+"&p="+password,
			success:function(data){
				if(data != "success"){
					layui.use("layer",function(){
						layer.msg(data);
					});
					$(form.p).focus();
				}
				else
					top.window.location="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/centerIndex.jsp";
			}
		});
	}
	
	function reset_checkbox(){
		$('#isManager').val('0');
	}
	
	function keyDown(e)
	{
	e = e||window.event;
	  if (e.keyCode == 13)
	  {
	    e.returnValue=false;
	    e.cancel = true;
	    doLogin();
	  }
	}
</script>
 <style>
 body{background: url(<%=appmap%><%=ReportConfig.raqsoftDir%>/center/images/login.png)no-repeat;background-size:cover;font-size: 16px;}
    .form{
    background: rgba(255,255,255,0);
    padding: 20px;
    padding-left: 40px;
     padding-right: 40px;
}

h1{
	font-size: 54px;
	padding-top:50px
}
</style>
<body >
<div class="layui-container" style="margin-top: 120px;">  
  <div class="layui-row" style="height:100%;">
    <div class="layui-col-md4 layui-col-md-offset7"  style="height:100%;">
		<form class="layui-form" method="post" id="loginForm" action="<%=appmap %>/reportCenterServlet" style="border-radius:10px;height:475px;width:550px;margin-left: 40px;background-color: #EEEEEE">
		<h1 style="text-align: center">润乾报表中心</h1>
		 <div class="layui-form-item" style="margin-top:50px;">
		<input onkeydown="keyDown();" style="width:380px;margin-left:80px" type="text" name="userName" required  lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
		</div>
		<div class="layui-form-item">
		<div class="layui-input-inline">
		<input onkeydown="keyDown();" style="width:380px;margin-left:80px" type="password" name="p" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
		</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md4 layui-col-md-offset2"  style="margin-left:80px">
			<input type="button" id="loginbtn" value="登录" onclick="doLogin();return false;" style="margin-top:40px;width:150px" class="layui-btn layui-btn-warm"/>
 			</div>
			<div class="layui-col-md4" style="margin-left:80px;margin-top:45px"><span style="color: red;" id="msg" ></span></div>
		</div>
		<input type="hidden" name="action" value="3"/>
		</form>
    </div>
  </div>
  </div>
</body>
</html>
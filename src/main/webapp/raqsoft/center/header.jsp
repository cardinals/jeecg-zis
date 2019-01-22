<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.raqsoft.report.view.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String appmap = request.getContextPath();
	String loginType = (String)(request.getSession().getAttribute("loginType"));
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<script src="<%=appmap %>/js/jquery-1.8.3.min.js"></script>
<script src="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<style type="text/css">
a:link {text-decoration:none;}
a:visited {text-decoration:none;}
</style>
<script type="text/javascript">
	var device = navigator.userAgent;
	var isMobile = device.indexOf('Mobile') >= 0;
	var currReport = "";
	if(isMobile){
		window.location = "<%=appmap %><%=ReportConfig.raqsoftDir%>/center/mobile/jsp/managerIndex.jsp"
	}
	<% 
		if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
		else response.setHeader("Pragma","no-cache");
		request.setCharacterEncoding( "UTF-8" );
		appmap = request.getContextPath();
	%>
	function logout(){
		alert(11111111);
		window.top.location="<%=appmap%>/reportCenterServlet?action=5";
	}
	
	function noLeftLayout(url){
		var leftF = window.top.document.getElementById("leftF");
		var showPropF = window.top.document.getElementById("showProp");
		leftF.style.display = "none";
		$('#props').css("left","0px");
		showPropF.setAttribute( "src", url );
	}
	
	function showReportList(ifShowTreeNodeComp){
		var leftF = window.top.document.getElementById("leftF");
		var showPropF = window.top.document.getElementById("showProp");
		var showNodeType = "no";
		leftF.style.display="";
		$('#props').css("left","300px");
		leftF.setAttribute("src", "<%=appmap %><%=ReportConfig.raqsoftDir%>/center/tree.jsp?showReportContent="+loginType+"&ifShowTreeNodeComp="+ifShowTreeNodeComp);
	}

	function _reportManage(){
		noLeftLayout("<%=appmap%>/reportCenterServlet?action=24");
	}
	
	function _roleManage(){
		var leftF = window.top.document.getElementById("leftF");
		var showPropF = window.top.document.getElementById("showProp");
		leftF.style.display = "";
		$('#props').css("left","300px");
		leftF.setAttribute( "src", "<%=appmap%>/reportCenterServlet?action=34" );
		showPropF.setAttribute("src", "<%=appmap%>/reportCenterServlet?action=35&userAction=32" + "&roleId");
	}
	
	function _userManage(){
		noLeftLayout("<%=appmap%>/reportCenterServlet?action=16");
	}
	
	function _fileManage(){
		noLeftLayout("<%=appmap%>/reportCenterServlet?action=39&fileType=all");
	}
	
	function _modifyPwd(){
		noLeftLayout("<%=appmap%>/reportCenterServlet?action=31&isManager=yes");
	}
	
	function _personal(){
		noLeftLayout("<%=appmap%>/raqsoft/center/userPersonal.jsp");
	}
	
	//以下6个方法从index移植
	function showJsp(url,name){
		currReport=name;
	    document.getElementById("showProp").src = encodeURI(url); 
	    showLoading();
	}
	
	/* function doSearch( value ) {
		if( value == "" ) {
			alert( "请输入搜索内容" );
			return;
		}
		var searchUrl = encodeURI( "reportJsp/showReport.jsp?rpx=/search/search.rpx&search=" + value );
		document.getElementById("showProp").src = searchUrl;
	} */
	
	function readme(){
		document.getElementById("showProp").src = encodeURI("./reportJsp/showReport.jsp?rpx=/search/readme.rpx&search="+currReport); 
	}
	
	function show(name){
		currReport=name;
	    document.getElementById("showProp").src = encodeURI("/demo/reportJsp/showReport.jsp?rpx="+name); 
	    showLoading();
	}
	
	function showLoading() {
		var mban = document.getElementById( "mengban" );
		if(mban == null) return;
		var ww = document.getElementById( "sidebar" ).offsetWidth;
		var hh = document.getElementById( "headerbar" ).offsetHeight;
		mban.style.left = ww + "px";
		mban.style.top = hh + "px";
		mban.style.width = document.body.clientWidth - ww + "px";
		mban.style.height = document.getElementById( "mainDiv" ).offsetHeight + "px";
		mban.style.display = "";
	}
	
	function hideLoading() {
		var mban = document.getElementById( "mengban" );
		if(mban == null) return;
		mban.style.display = "none";
	}		
</script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?2ed84d0183a98a7b957fdc81fa379e13";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<div class="layui-header layui-bg-black">
<div class="layui-logo" style="background-color: #2F4046"><img src="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/images/logo3.png"/></div>
 <ul class="layui-nav layui-layout-left">
  <li class="headbtn layui-this layui-nav-item user manager"><a href="javascript:showReportList(1);">显示节点</a></li>
  <li class="headbtn layui-nav-item manager"><a href="javascript:_roleManage();">机构管理</a></li>
  <li class="headbtn layui-nav-item manager"><a href="javascript:_userManage();">用户管理</a></li>
  <li class="headbtn layui-nav-item user manager"><a href="javascript:_reportManage();">报表管理</a></li>
  <li class="headbtn layui-nav-item manager"><a href="javascript:_fileManage();">查询分析管理</a></li>
  <li class="headbtn layui-nav-item user"><a href="javascript:_personal();">用户个人信息</a></li>
  <li class="headbtn layui-nav-item supermanager"><a href="javascript:_modifyPwd();">密码管理</a></li>
  <li class="layui-nav-item visitor"><a href="javascript:readme();">报表说明</a></li>
</ul>
<ul class="layui-nav layui-layout-right">
	<li class="layui-nav-item user manager visitor" style="color: yellow;">
	       当前用户:<span class="manager">&nbsp;管理员:&nbsp;</span>
	       <span class="visitor">&nbsp;访客:&nbsp;</span>
	      <span class="visitor user manager">&nbsp;${userObj.userName}</span>
	      
    </li>
	<li class="layui-nav-item user normalmanager"><a href="javascript:_modifyPwd();">个人密码</a></li>
    <li class="layui-nav-item user manager visitor"><a href="javascript:logout();">退出</a></li>
</div>
</div>
</body>
<script type="text/javascript">
	var loginType = '<%=loginType%>';
	$(".user").hide();
	$(".visitor").hide();
	$(".manager").hide();
	$(".supermanager").hide();
	$(".normalmanager").hide();
	if("user" == loginType){
		$('.user').show();
	}
	if("normalManager" == loginType || "supermanager" == loginType){		
		$('.manager').show();
	}
	if("normalManager" == loginType ){
		$(".normalmanager").show();
	}
	if("supermanager" == loginType ){
		$(".supermanager").show();
	}
	if("visitor" == loginType){
		$('.visitor').show();
	}
	$(function(){
		$('.headbtn').bind("click",function(event){
			$('.headbtn').removeClass('layui-this');
			$(event.target.parentNode).addClass('layui-this');
		});
		layui.use('element', function(){
			  var element = layui.element;
		});
	});
</script>
</html>
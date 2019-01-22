<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String appmap = request.getContextPath();
%>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="../css/mui.min.css" rel="stylesheet" />
		<link href="../css/style.css" rel="stylesheet" />
		<script src="../js/mui.min.js"></script>
		<script src="../js/mui.enterfocus.js"></script>
		<script src="../js/app.js"></script>
		<style>
			html,
			body {
				background-color: #efeff4;
			}
		</style>
		<script>
			mui.init();
			var device = navigator.userAgent;
			var isMobile = device.indexOf('Mobile') >= 0;
			if(!isMobile){
				window.location = "<%=appmap%>/raqsoft/center/centerManage.jsp"
			}
		</script>
	</head>

	<body>

<header class="mui-bar mui-bar-nav">
    <a class="mui-icon mui-icon-close mui-pull-right" href="<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=5&isMobile=1"></a>
    <h1 class="mui-title">报表中心--管理员</h1>
</header>
<div class="mui-content">
    <div class="mui-card">
        <ul class="mui-table-view mui-grid-view mui-grid-9">
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=tree.jsp?showReportContent=no">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">节点管理</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=34&isMobile=1">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">角色管理</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=16&isMobile=1">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">用户管理</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=24&isMobile=1">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">报表管理</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=39&fileType=all&isMobile=1">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">查询分析文件</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="./outfit.jsp?inner=<%=appmap%>/raqsoft/center/mobile/jsp/reportCenterServlet?action=31&isManager=yes">
                    <span class="mui-icon mui-icon-home"></span>
                    <div class="mui-media-body">密码管理</div></a></li>
        </ul> 
    </div>

</div>
</body>
</html>
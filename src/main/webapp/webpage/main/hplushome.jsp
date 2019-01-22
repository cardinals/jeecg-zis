<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--360浏览器优先以webkit内核解析-->
<title></title>
<link rel="shortcut icon" href="images/favicon.ico">
<link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="plug-in-ui/hplus/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="plug-in-ui/hplus/css/animate.css" rel="stylesheet">
<link href="plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">
<link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
<script src="plug-in/laydate/laydate.js"></script>
<!--  <link href="plug-in/themes/fineui/css/animate.css" rel="stylesheet">
    <link href="plug-in/themes/fineui/css/style.css?v=4.1.0" rel="stylesheet"> -->
    <!-- console按钮插件 -->
<!--  <script src="https://www.w3cways.com/demo/vconsole/vconsole.min.js"></script>
 --> 
<style type="text/css">
.gray-bg {
	background-color: #e9ecf3;
}

.col-sm-2 {
	width: 10%;
	padding-left: 5px;
	padding-right: 5px;
	float: left;
}

.p-lg {
	padding: 0px 0px 10px 0px;
}

.widget {
	margin-top: 0px;
}

.iconfont {
	font-size: 30px;
	color: white;
}

h2 {
	font-size: 19px;
}

.echart_div {
	height: 240px;
	width: 100%;
}

.ibtn {
	cursor: pointer;
}

.flot-chart {
	height: 400px;
}
/*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
	.container {
    	 width:99% !important; margin:10px;
    	 padding-right: 1px !important;
    	 padding-left: 1px !important;
	}
	.color_red{color:#e55555;} */
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content"></div>

	<div class="wrapper wrapper-content">
		<table style="font-size: 16px;">
				<!-- 	<tr><td>2018年度员工合作满意度评价表</td></tr> -->
					<tr><td>根据2018年度绩效考核工作安排，现启动同级评价及下属评价环节，请大家认真对待、积极配合。</td></tr>
					<tr><td>评分说明：</td></tr>
					<tr><td>1、本次评分全部为匿名评分，请您本着公正客观的态度逐项评分，评分结果将计入被评价人2018年度绩效考核得分。</td></tr>
					<tr><td>2、评分内容详见页面左侧，每项评分均需填写，请勿遗漏，<font color="red">一旦提交将无法修改</font>。</td></tr>
					<tr><td>3、当您评分时，满分视为无效评分；所有评分相同视为无效评分；漏打、错打、无效评分均无法提交。</td></tr>
					<tr><td>4、本系统将于<font color="red">2018年12月21日22：00</font>关闭，请您于规定时间内完成评分，逾期视为弃权，感谢您的支持与配合！</td></tr>
					<tr><td>5、如在评分过程中出现任何问题，请及时与人力资源部景卫平、刘玉雪联系。</td></tr>
					<tr><td>6、以下为评分标准，供评分参考。</td></tr>
		</table>
		<div class="wrapper wrapper-content"></div>
		<table  style="width: 100%;height: 200px;text-align: center" border="1">
					<tr align="center">
						<td colspan="2"><b>评分标准</b></td>
					</tr>
					<tr>
						<td width="50%"><b>合计得分</b></td>
						<td  width="50%"><b>含义</b></td>
					</tr>
					<tr>
						<td>95（含）-100</td>
						<td>非常满意，总是超越标准</td>
					</tr>
					<tr>
						<td>90（含）-95</td>
						<td>比较满意，经常超越标准</td>
					</tr>
					<tr>
						<td>80（含）-90</td>
						<td>基本满意，基本达到标准</td>
					</tr>
					<tr>
						<td>70（含）-80</td>
						<td>不太满意，偶尔达到标准</td>
					</tr>
					<tr>
						<td>低于70</td>
						<td>不满意</td>
					</tr>
	 </table>
	</div>
	<!-- 全局js -->
	<script src="plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
</body>
</html>
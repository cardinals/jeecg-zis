<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String appmap = request.getContextPath();
	String showReportContent = request.getParameter("showReportContent");
%>
<head>
<meta charset="utf-8">
<!-- Required Stylesheets -->
<link href="<%=appmap %>/css/bootstrap.css" rel="stylesheet">
 <link href="<%=appmap %>/css/bootstrap-treeview.css" rel="stylesheet">
<!-- Required Javascript -->
<script src="<%=appmap %>/js/jquery.js"></script>
<script src="../js/mtree.js"></script>
<script src="<%=appmap %>/js/bootstrap-treeview.js"></script> 
</head>  
<body>
<style>
	li{
		font-size: x-large
	}
</style>
<div class="panel panel-default">
</div>
	<div class="panel panel-primary">
	<div class="panel-heading">
    </div>
	    <div class="panel-body">
	        <div id="tree" class="">
			</div>
	    </div>
	</div>
</body>

<script type="text/javascript">

function getTreeJson(){
	var jsonArr;
	$.ajax({
		type:"post",
		url:"./reportCenterServlet?action=4&showReportContent=<%=showReportContent%>",
		async:false,
		success:function(jsonStr){
			console.log(jsonStr);
			jsonArr = eval("("+jsonStr+")");
		},
		error:function(){
			jsonArr = eval("([{text:'无'}])");
		}
	});
	
	<%-- href:"javascript:tapNode('<%=appmap %>/raqsoft/center/onLineUser.jsp')" --%>
	return jsonArr;
}



var data1 = getTreeJson();
$('#tree').treeview({
	  data: data1,         // data is not optional
	  levels: 5,
	  backColor:'white',
	  enableLinks:true
	});    
</script>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<%@ page import="com.raqsoft.report.view.*"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<script src="<%=contextPath%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script type="text/javascript">
	function refresh(){
		window.location.href = "";
	}
	
	function detail(rpt, rp){
		layui.use('layer', function(){
	    	layer.open({
	    		type:2,
	    		title:'文件夹详情',
	    		content:'<%=contextPath%>/reportCenterServlet?action=50&rpt='+encodeURIComponent(rpt),
	    		area: ['650px','200px'],
	    		offset: '100px'
	    	});
	    });
	}
	
	function down(fileName){
		fileName = encodeURIComponent(fileName);
		window.location="<%=contextPath%>/reportCenterServlet?action=38&file="+fileName+"&fileDirType=report"+"&rightType=0";
	}
	
	function deleteSelect(){
	  var subGo=0;
	  var delReportIds = new Array();
	  for(var i=0;i<document.forms.form3.elements.length;i++){
        if(document.forms.form3.elements[i].type=="checkbox"){
		  if(document.forms.form3.elements[i].checked){
			  if(document.forms.form3.elements[i].name=='selectAll'){
				  continue;
			  }
			  delReportIds[delReportIds.length] = document.forms.form3.elements[i].value;
			  subGo++;
		   }
	    }
	   }

	    if(subGo<1){
		   alert("您没有选择报表！");
		   return ;
	    }
          if(window.confirm("删除后无法找回，请确认！")==false)return;
          document.forms.form3.action = '<%=contextPath %>/reportCenterServlet?action=25&fileType=report&delReportIds='+delReportIds;
          document.forms.form3.submit();
    }

 function selectAll(form3 ){
	  for(var i=0;i<form3.elements.length;i++){
        if(form3.elements[i].type=="checkbox"){
					 form3.elements[i].checked = true;
	     }
	   }
 	}
 	
 function clearAll(form3 ){
	  for(var i=0;i<form3.elements.length;i++){
        if(form3.elements[i].type=="checkbox"){
					 form3.elements[i].checked = false;
	     }
	   }
 	}
 	
	var device = navigator.userAgent;
	var isMobile = device.indexOf('Mobile') >= 0;
	$(function(){
		if(isMobile){
			$('#goBackButton').hide();
		}
	});  	
	
	function selectAllToggle(){
 		if($(form3.selectAll).prop("checked") == true){
 			selectAll(form3);
 		}else{
 			clearAll(form3);
 		}
 	}
	
	function addReport(){
		 layui.use('layer', function(){
		   $('#openLayerIndex').val(layer.open({
		    	type:2,
		    	title:"上传报表",
		    	content:'<%=contextPath%>/reportCenterServlet?action=26&uploadType=report',
		    	area:['550px','300px'],
		    	offset: '100px'
		    }));
		   });
	}
	
	function jumpToPage(page){
		var targetPage;
		if(page != null){
			targetPage = page;
		}else{
			targetPage = document.getElementById("page").value;
		}
		window.location = "<%=contextPath%>/reportCenterServlet?action=24&currPage=0&page="+targetPage+"&sizePerPage="+document.getElementById("sizePerPage").value;
	}
</script>
<style>
	.col-check{
		width:90px
	}
	.col-id{
		width:90px
	}
	.col-rpt{
		width:250px
	}
	.col-cnname{
		width:250px
	}
	.col-type{
		width:210px
	}
	.col-mani{
		width:130px
	}
</style>
</head>
<body >
<input type="hidden" value id="openLayerIndex"/>
<div align="center">
<div style="width:1200px;top:0px;">
<div class="layui-layout" style="margin-left:20px;margin-top:20px;">
	<div class="layui-bg-white" style="height:40px">
	<div class="layui-row">
    <div class="layui-col-xs1">
    <button  style="cursor: pointer;"  class="layui-btn layui-bg-green layui-btn-sm" onclick="addReport()"><i class="layui-icon">&#xe654;</i>添加</button>
    </div>
    <div class="layui-col-xs1">
    <button style="cursor: pointer;" class="layui-btn layui-bg-red layui-btn-sm" onclick="deleteSelect()"><i class="layui-icon">&#xe640;</i>删除</button>
    </div>
  </div>
</div>
</div>
</div>
</div>
<div class="layui-container" align="center">
<form action="<%=contextPath %>/" method="post" name="form3" id="form3" style="margin-bottom:40px">
		<table lay-skin="nob" class="layui-table"
			id="table1" title="报表管理" 
			rowsDisplayed="15" style="width:1200px;top:70px;margin:0px;">
			<thead>
	     		<tr>
	     			<th class="col-check"><input name="selectAll" type="checkbox" onchange="selectAllToggle();"/></th>
	     			<th class="col-id">ID</th>
	     			<th class="col-rpt">报表文件名</th>
	     			<th class="col-cnname">报表中文名</th>
	     			<th class="col-type">报表类型</th>
	     			<th class="col-mani">操作</th>
	     		</tr>
     		</thead></table>
     		<div id="mainTableDiv" style="position:relative;width:1200px;height:450px;overflow-y: scroll;overflow-x: hidden">
     		<table id="mainTable" lay-skin="nob" class="layui-table"
			id="table1" title="报表管理" 
			rowsDisplayed="15" style="width:1200px;top:100px;">
			<tbody>
     		<c:forEach items="${pageInfo.entity }" var="report">
     		<tr>
     			<td class="col-check"><input name="" type="checkbox" value="${report.reportId }"></td>
     			<td class="col-id">${report.reportId }</td>
     			<td class="col-rpt">${report.rpt }</td>
     			<td class="col-cnname">${report.name }</td>
     			<td class="col-type">
     			<c:if test="${report.type eq 1}">报表(组)/填报表</c:if>
     			<c:if test="${report.type eq 2}">参数表单</c:if>
     			<c:if test="${report.type eq 3}">dfx脚本</c:if>
     			<c:if test="${report.type eq 8}">dfx文件</c:if>
     			<c:if test="${report.type eq 9}">其他文件</c:if>
     			</td>
	     		<td class="col-mani">
	     			<span onClick="down('${report.rpt }')" class="down"><img src="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/images/down.gif" border="noborder"/></span>
	     		</td>
     		</tr>
     		</c:forEach>
     		</tbody>
     		</table>
     		</div>
</form>
</div>
<div style="position:fixed;bottom:0px;background-color: white" align="right" class="layui-table-page">
<div style="padding-right:100px" id="layui-table-page1">
<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-2">
<a href="javascript:jumpToPage('${pageInfo.previousPage}');" class="layui-laypage-prev <c:if test='${pageInfo.targetPage eq 1 }'>layui-disabled</c:if>" data-page="0"><i class="layui-icon"></i></a>
<button type="button" onclick="jumpToPage('1');" class="layui-btn-sm" title="首页">1</button>
<span style="color: gray">当前在第${pageInfo.targetPage }页</span>
<button type="button" onclick="jumpToPage('${pageInfo.totalPage}');" class="layui-btn-sm" title="尾页">${pageInfo.totalPage}</button>
<a href="javascript:jumpToPage('${pageInfo.nextPage}');" class="layui-laypage-next <c:if test='${pageInfo.targetPage eq pageInfo.totalPage }'>layui-disabled</c:if>" data-page="2"><i class="layui-icon"></i></a>
<span class="layui-laypage-skip">到第<input type="text" min="1" id="page" value="1" class="layui-input">页<button onclick="jumpToPage();" type="button" class="layui-laypage-btn">确定</button></span><span class="layui-laypage-limits">
<select id="sizePerPage" lay-ignore="" value="${pageInfo.sizePerPage }">
	<%
	for(int i = 1; i <= 10; i++){
	%>
	<option value="<%=i %>0" ><%=i %>0 条/页</option>
	<%
	}
	%>
</select>
<script>
$(function(){
	$('#sizePerPage').val('${pageInfo.sizePerPage }');
});
top.window.onresize=function(){
	var mtd = document.getElementById('mainTableDiv');
	var mt = document.getElementById('mainTable');
	var targetHeight = 0;
	var h1 = top.window.innerHeight;
	targetHeight = parseInt(h1) - 100;
	if(targetHeight < 200){
		targetHeight = 200;
	}
	if(450 < targetHeight){
		targetHeight = 450;
	}
	mtd.style.height = targetHeight + "px";
	
}
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<%@ page import="com.raqsoft.report.view.*" %>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
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
	    		area:['550px','200px'],
	    		offset: '100px'
	    	});
	    });
	}
	
	function down(fileName){
		fileName = encodeURIComponent(fileName);
		window.location = "<%=contextPath%>/reportCenterServlet?action=38&file="+fileName+"&fileDirType=other";
	}
	
	function deleteSelect(){
	  var subGo=0;
	  var deleteFiles = new Array();
	  for(var i=0;i<document.forms.form3.elements.length;i++){
        if(document.forms.form3.elements[i].type=="checkbox"){
		  if(document.forms.form3.elements[i].checked){
			  if(document.forms.form3.elements[i].name=='selectAll'){
				  continue;
			  }
			  deleteFiles[deleteFiles.length] = document.forms.form3.elements[i].value;
			  subGo++;
		   }
	    }
	   }
	    if(subGo<1){
		   alert("您没有选择文件！");
		   return ;
	    }
          if(window.confirm("删除后无法找回，请确认！")==false)return;
          document.forms.form3.action = '<%=contextPath %>/reportCenterServlet?action=25&fileType=files&deleteFiles='+encodeURIComponent(deleteFiles);
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
	
	function addFile(){
	    layui.use('layer', function(){
	    	$('#openLayerIndex').val(layer.open({
	    		type:2,
	    		title:"上传文件",
	    		content:'<%=contextPath%>/reportCenterServlet?action=26&uploadType=file',
	    		area:['550px','300px'],
	    		offset: '100px'
	    	}));
	    });
	}
</script>
</head>
<body >
<input type="hidden" value id="openLayerIndex"/>
<div align="center">
<div style="width:1200px;">
<div class="layui-layout" style="margin-left:20px;margin-top:20px">
	<div class="layui-bg-white" style="height:40px">
	<div class="layui-row">
    <div class="layui-col-xs1">
    <button  style="cursor: pointer;"  class="layui-btn layui-bg-green layui-btn-sm" onclick="addFile()"><i class="layui-icon">&#xe654;</i>添加</button>
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
<form action="<%=contextPath %>/" method="post" name="form3" id="form3">
		<table lay-skin="nob" class="layui-table"
			id="table1" title="文件管理" 
			rowsDisplayed="15" style="width:1200px">
			<thead>
	     		<tr>
	     			<th><input name="selectAll" type="checkbox" onchange="selectAllToggle();"/></th>
	     			<th >文件名</th>
	     			<th >文件类型</th>
	     			<!-- <th >所属文件夹</th> -->
	     			<th >操作</th>
	     		</tr>
     			</thead>
			
     		<c:forEach items="${fileList }" var="file">
     		<tr>
     			<td style="width:100px"><input name="clspwd" type="checkbox" value="${file.dir }"></td>
     			<td>${file.dir}</td>
     			<td>${file.type}</td>
				<%-- <td>
	     			<a href='javascript:detail("${file.dir}", "" )'>查看</a>
	     		</td> --%>
	     		<td>
	     			<span onClick="down('${file.dir}')" class="down"><img src="<%=contextPath %><%=ReportConfig.raqsoftDir%>/center/images/down.gif" border="noborder"/></span>
	     		</td>
     		
     		</tr>
     		</c:forEach>
     	</table>
</form>
</div>
</body>
</html>

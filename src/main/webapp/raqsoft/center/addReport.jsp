<%@ page contentType="text/html;charset=UTF-8"%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.raqsoft.report.view.*"%>
<%@ page isELIgnored="false" %> 
 <%
    	String contextPath = request.getContextPath();
 		String uploadType = request.getParameter("uploadType");
 		String refresh = request.getParameter("refresh");
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
				function delReportTR(e){
					var fl = addFile.rows.length;
				  while (e.tagName != "TD")   
			      e  =  e.parentElement;   
			 		while (e.tagName != "TR")   
			      e  =  e.parentElement;   
					var ri = e.rowIndex;
					uploadReport.deleteRow( ri );
				}
				
				function addReportTR(){
				  tr = document.createElement( "tr" );
				  td = document.createElement( "td" );
				  td.setAttribute( "width" , "100%"  );
				  td.innerHTML = "上传文件：<input type=file size=50 name=file><img src='<%=contextPath%>/images/del.gif' style='cursor:pointer' onclick='delReportTR(event.srcElement)'>";
				  tr.appendChild( td );
				  addFile.appendChild( tr );
				}
			</script>
	</head>
	<body>
	<script type="text/javascript">
	function regroupActionBeforeSubmit(){
		var refresh = "<%=refresh %>";
		var tmp1 =  form2.raq.value;
	  	var rp = document.getElementById("relativePath") != null ? document.getElementById("relativePath").value : null;
	  	var uploadType = "<%=uploadType%>";
	  	var name ="";
	  	if(uploadType == "report"){
		  	name = document.getElementById("cnName").value;
	  	}
		var type = document.getElementById("type").value;
		form2.action = form2.action +"?uploadType="+uploadType+"&name="+encodeURIComponent(name)
		+"&type="+type
		+"&rp="+encodeURIComponent(rp)
		+"&refresh="+refresh;   /* +"&writedb="+form2.writedb.value;*/ 
		if(type == "4" || type=="5"){
			form2.action += "&dqldb="+document.getElementById("extraSelect").value;
		}
	}
	
	function toSubmit(){
		var refresh1 = "<%=refresh %>";
		var uploadType = "<%=uploadType%>";
		if(uploadType == "report"){
			var name = document.getElementById("cnName").value;
			if("report"== uploadType && (name == null || name.length == 0)){
				alert("请填写文件名称");
				return;
			}
	  	}
		regroupActionBeforeSubmit();
		var tmp1 = form2.raq.value;
		if(refresh1 == "no"){
			var formData = new FormData(form2);
			$.ajax({
				data:formData,
				type:'post',
				url:form2.action,
				async: false,  
		        cache: false,  
		        contentType: false,  
		        processData: false,  
				success:function(callbackstr){
					if(callbackstr.indexOf("|||") < 0){
						alert("异步上传失败！");
						closeFrameLayer("showProp");
						return;
					}
					alert("异步上传成功！");
					var sep = "|||";
					var arr = callbackstr.split(sep);
					top.document.getElementById("showProp").contentWindow.refreshReportList(arr[0], arr[1]);
					closeFrameLayer("showProp");
				}
			});
			return;
		}
	  	if ( tmp1 != '' ){
			  	var tmp2 = tmp1.split("/");
			  	var tmp3 = tmp2[ tmp2.length-1 ];
			  	tmp3 = tmp3.toUpperCase();
			  	form2.action = "";//保留
			  	if( form2.file.value!='' ){
			  		  var uploadFile = form2.file.value.toUpperCase();
			  	    if ( uploadFile.indexOf( tmp3 ) < 0)	{
			  	    	 alert( "更新不能执行 确保更新文件是'" + tmp3.toLowerCase() + "'！" );
			  	    	 return;
			  	    }
			  	    else{
			  	    	 form2.submit();	
			  	    }
			  	}
			  	else{
			  	    form2.submit();	
			  	}
	  	}else{
	  		 form2.submit();	
	  	}
	}
	
	
	function closeFrameLayer(frame){
		var index = top.document.getElementById(frame).contentDocument.getElementById("openLayerIndex").value;
		var frameWin = top.document.getElementById(frame).contentWindow;
		frameWin.layer.close(index);
	}
	
	function goBack(){
		var uploadType = "<%=uploadType%>";
		if(uploadType == "report") window.location = "<%=contextPath%>/reportCenterServlet?action=24";
		else window.location = "<%=contextPath%>/reportCenterServlet?action=39&fileType=all";
	}			
	
	function showExtra(event){
		var value = event.value;
		if(value == "4" || value == "5"){
			$('#extraSpan').show();
		}else{
			$('#extraSpan').hide();
		}
	}
	
	</script>
		<form id=form2 method=post action="<%=contextPath %>/reportCenterServlet/upload" accept-charset="utf-8" enctype="multipart/form-data">
		    <input type="hidden" name="uploadType" value="<%=request.getParameter("uploadType")%>">
		    <input type="hidden" name="refresh" value="<%=request.getParameter("refresh")%>">
			<TABLE id=uploadReport align=center cellSpacing=0 cellPadding=3 width="450" border=1 class=layui-table
				style="table-layout:fixed; BORDER-COLLAPSE: collapse">
				  <tr>
				  	<td  align=center class="tableHeader" colSpan="2" > 添加文件 </td>
				  </tr>
				<%if("report".equals(uploadType)){%>
					<TR>
						<td width=100%>文件中文名:
						</td><td>
							<input id="cnName" type=text size="10" name='name' value='${reportName }'>
							<font color=red>*必填</font>
						</td>				
					</TR>
				<% } %>
				<TR>
					<td width=100%>路径:
					</td>
					<td>
						  <select id="relativePath" name="rp">
							<%if("report".equals(uploadType)){%>
							<c:forEach items="${pathArr }" var="path">
								<option value="${path }">${path }</option>
							</c:forEach>
							<%} else {%>
						  		<option value="dcts">dcts</option>
						  		<option value="vsbs">vsbs</option>
						  		<option value="analyseFiles">analyseFiles</option>
						  		<option value="dfx">dfx</option>
						  		<option value="dfxScript">dfxScript</option>
						  		<option value="inputFiles">inputFiles</option>
						  	<%} %>
						  </select>
					</td>
				</TR>
				<TR>
					<td width=100%>文件类型:
					</td><td>
						  <select id="type" name="type" onchange="showExtra(this);">
				       	   	  <%if("file".equals(uploadType)){ %>
					       		<option value="4"  <c:if test="${report.type eq 4}">selected</c:if>>dct文件</option>
				       	   	  	<option value="5"  <c:if test="${report.type eq 5}">selected</c:if>>vsb文件</option>
				       	   	  	<option value="7"  <c:if test="${report.type eq 7}">selected</c:if>>数据源文件</option>
				       	   	  	<option value="6"  <c:if test="${report.type eq 6}">selected</c:if>>填报文件</option>
				       	   	  	<option value="3"  <c:if test="${report.type eq 3}">selected</c:if>>查询文件(qyx)</option>
					       	  <% }else{%>
				       	   	  <%-- <option value="3"  <c:if test="${report.type eq 3}">selected</c:if>>dfx脚本</option>--%>
				       	   	  <option value="1"  <c:if test="${report.type eq 1}">selected</c:if>>报表(组)/填报表</option>
				       	   	  <option value="2"  <c:if test="${report.type eq 2}">selected</c:if>>参数表单</option>
				       	   	   <option value="9"  <c:if test="${report.type eq 9}">selected</c:if>>其他类型</option>
				       	   	  <% }%>
				       	   	  <option value="8"  <c:if test="${report.type eq 8}">selected</c:if>>dfx文件</option>
						  </select>
					</td>				
				</TR>
				<%
				if("file".equals(uploadType)){
				%>
				<TR id="extraSpan">
					<td width=100%>关联dql数据库:
					</td><td>
						  <select id="extraSelect" name="extraSelect">
						  	<c:forEach items="${dqldbs }" var="db">
						  		<option value="${db }">${db }</option>
						  	</c:forEach>
						  </select>
					</td>				
				</TR>
				<%
				}
				%>
				
					<!-- <TR>
						<td width=100%>是否写入数据库:
			  	        <input type=checkbox name='writedb' id='writedb' value='0' onclick="javascript:if(form2.writedb.checked==true){writedb.value='1';}else{writedb.value='0';}">
			  	      
			  	 	</td>				
					</TR> -->
				<TR>
					<td width=100% colSpan="2">
						选择上传文件:
						<input type=file size="50" name=file id=file>
					</td>
				</TR>
				<tr>
					<td colspan="2"> <input type=hidden name=raq id=raq value='${report.rpt }'>
					<button onclick="toSubmit();" type="button" class="layui-btn">
  					<i class="layui-icon">&#xe67c;</i>确定
					</button>	
					</td>
				</tr>	
        		<tbody id="addFile" width="100%"></tbody>
				<!-- <TR>
					<td align=center>
						<button type="button" class="layui-btn" id="test2">
  					<i class="layui-icon">&#xe621;</i>选择子报表
					</button>	
					</td>
					<td>
					<button type="button" class="layui-btn" id="test1">
  					<i class="layui-icon">&#xe67c;</i>确定
					</button>				
					</td>
					
				</TR> -->
			</TABLE>
		</form>
<script>
layui.use('upload', function(){
  var upload = layui.upload;
  //执行实例
  var uploadInst = upload.render({
	multiple:true,
    elem: '#test2',//点击test2进入上传选择界面
    url: '#', //上传接口
    bindAction:'#test1',//点击test1提交
    auto: false,//是否选择后直接上传
    accept:'file',//上传类型默认只有图片
    acceptMime:'file/*',//上传可用类型配合
    choose:function(obj){
    	var files = obj.pushFile();//加入上传组
    	//预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
        obj.preview(function(index, file, result){
        	//遍历文件，找到文件名和
        }); 
    },
    before: function(){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
    },
    done: function(res){
      alert("lay上传成功");
    },
    error: function(){
      //请求异常回调
    }
  });
});
</script>
	</body>
</html>

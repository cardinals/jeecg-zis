<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<%@ page import="com.raqsoft.report.view.*"%>
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
	var newCount = 0;
	
	function deleteSelect(){
		var delUserIds = new Array();
	  var subGo=0;
	  for(var i=0;i<document.forms.form3.elements.length;i++){
        if(document.forms.form3.elements[i].type=="checkbox"){
		  if(document.forms.form3.elements[i].checked){
			  if(document.forms.form3.elements[i].name == 'selectAll'){
				  continue;
			  }
			  delUserIds[delUserIds.length] = document.forms.form3.elements[i].value;
			  subGo++;
		   }
	    }
	   }
	    if(subGo<1){
		   alert("您没有选择用户！");
		   return ;
	    }
          if(window.confirm("确认要删除 请确认！")==false)return;
          document.forms.form3.action = '<%=contextPath%>/reportCenterServlet?action=14&delUserIds='+ delUserIds;
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

 	window.onload = function(){
 		var device = navigator.userAgent;
		var isMobile = device.indexOf('Mobile') >= 0;
		if(isMobile){
			document.getElementById('goBackButton').style.display = "none";
		}
 	};
 	
 	function selectAllToggle(){
 		if($(form3.selectAll).prop("checked") == true){
 			selectAll(form3);
 		}else{
 			clearAll(form3);
 		}
 	}
 	
 	function modifyInList(id){
 		var saveForm = form3;
 		if(saveForm.tagName != "FORM"){
 			return;
 		}
 		var userId = id;
		var url = "<%=contextPath%>/reportCenterServlet?action=13";
		if(userId!= null){
			url += "&userId="+userId;
		}
		var userName = eval("saveForm.userName_"+userId);
		userName = $(userName);
		if(userName.val() != null && userName.val().length != 0){
			if(userName.val() == "admin" || userName.val() == "raq_visitor"){
				alert("不能设置用户名为"+ userName.val()+"！");
				return;
			}
			url += "&userName="+encodeURIComponent(userName.val());
		}else{
			alert("用户名不能为空！");
			userName.focus();
			return;
		}
		
		var role = eval("saveForm.userRole_"+userId);
		if(role.value != null){
			url += "&role="+encodeURIComponent(role.value);
		}
		url += "&params=";
		$.ajax({
			type:"post",
			url:url,
			data:[],
			success:function(str){
				if(str == "success"){
					alert("保存成功!");
					window.location='<%=contextPath%>/reportCenterServlet?action=16';
				}else{
					alert(str);
				}
			}
		});
 	}
 	
 	function showMenu(td,userId,acId){
 		var dl = $(td).children('dl')[0];
 		$(dl).show();
 		if($(dl).children('dd').length > 0){return;}
 		$.ajax({
 			type:"post",
 			url:"<%=contextPath%>/reportCenterServlet?action=58&userId="+userId,
 			data:[],
 			success:function(acIds){
 				$(dl).empty();
 				if(acIds != null && acIds.length != 0){
 					var acIdArr = acIds.split(',');
 					for(var k = 0; k < acIdArr.length; k++){
	 					$(dl).append('<dd><a style=\"margin-left:20px\" href=\"JavaScript:window.location=\'<%=contextPath%>/reportCenterServlet?action=58&userId='+userId+'&acId='+acIdArr[k]+'\';\">'+acIdArr[k]+'</a><a href=\"javascript:deleteCondition(\''+userId+'\',\''+acIdArr[k]+'\');\"><i style=\"float:right;color:red\" class=layui-icon>&#x1006;</i></a></dd>');
 					}
 				}
 				$(dl).append('<dd><a href=\"JavaScript:window.location=\'<%=contextPath%>/reportCenterServlet?action=59&userId='+userId+'\';\"><i style=\"color:#CFA;font: bold;\" class=\"layui-icon\">&#xe654;</i><i>添加</i></a></dd>');
 			}
 		});
 		
 	}
 	
 	function deleteCondition(userId, acId){
 		confirm("确认要删除:“"+acId+"”的条件");
 		$.ajax({
 			type:"post",
 			url:"<%=contextPath%>/reportCenterServlet?action=60&userId="+userId+"&acId="+acId,
 			data:[],
 			success:function(str){
 				if(str == "success"){
 					window.location = "<%=contextPath%>/reportCenterServlet?action=16";
 				}
 			}
 		});
 		
 	}
 	
 	function hideMenuEx(td){
 		var dl = $(td).children('dl')[0];
 		$(dl).hide();
 	}
 	
 	function hideMenu(dl){
 		$(dl).hide();
 	}
 	
 	function addInList(count, event){
 		var url = "<%=contextPath%>/reportCenterServlet?action=12";
		var userName = $("#userName-"+count);
		if(userName.val() != null && userName.val().length != 0){
			url += "&userName="+encodeURIComponent(userName.val());
		}else{
			alert("用户名不能为空！");
			userName.focus();
			return;
		}
		var role = $("#newSelect-"+count).val();
		if(role != null){
			url += "&role="+encodeURIComponent(role);
		}
		url += "&params=";
		$.ajax({
			type:"post",
			data:[],
			async: false, 
			url:url,
			success:function(str){
				if(str == "success"){
					alert("保存成功!");
					rebuildSaveButton(userName,event);
				}else{
					alert(str);
				}
			}
		});
 	}
 	
 	function rebuildSaveButton(userName,saveButton){
 		$.ajax({
 			type:'post',
 			data:[],
 			url:"<%=contextPath%>/reportCenterServlet?action=-16&userName="+userName.val(),
			success:function(userId1){
				var newAddTempId = userName.attr("id");
				newAddTempId = newAddTempId.split('-')[1];
				$("#newSelect-"+newAddTempId).attr("name","userRole_"+userId1);
				$("#userName-"+newAddTempId).attr("name","userName_"+userId1);
				$("#newCheckBox-"+newAddTempId).prop("disabled",false).val(userId1);
				
				$(saveButton).removeAttr("onclick");
				$(saveButton).attr("onclick","modifyInList("+userId1+");");
			}
		});
 	} 	
 	
 	function add(){
 		var tmp = $("<tr id='newTr"+newCount+"'></tr>");
		$('#table1 tbody').append(tmp);
		tmp = $("<td><input id='newCheckBox-"+newCount+"' name='' type='checkbox' value disabled></td>");
		$('#newTr'+newCount).append(tmp);
		tmp = $("<td><input id='userName-"+newCount+"' type='text' value/></td>");
		$('#newTr'+newCount).append(tmp);
		tmp = $("<td id='newSelectTd"+newCount+"'></td>");
		$('#newTr'+newCount).append(tmp);
		tmp = $("<select id='newSelect-"+newCount+"' name='userRole-"+newCount+"'></select>");
		$('#newSelectTd'+newCount).append(tmp);
		tmp = $("<c:forEach items='${roleArr }' var='role'>\n<c:if test='${role ne null }'>\n<option value='${role.id }' >${role.name }</option>\n</c:if>\n</c:forEach>");
		$('#newSelect-'+newCount).append(tmp);
		tmp = $("<td></td>");
		$('#newTr'+newCount).append(tmp);
		tmp = $("<td></td>");
		/* $('#newTr'+newCount).append(tmp);
		tmp = $("<td></td>"); */
		//预留
		$('#newTr'+newCount).append(tmp);
		tmp = $("<td><input type='button' onclick=\'addInList("+newCount+",this);\' class='layui-btn layui-btn-sm layui-bg-green' value='保存'/></td>");
		$('#newTr'+newCount).append(tmp);
		$('#newSelect-'+newCount).val('-1');
		$('#userName-'+newCount).focus();
		newCount++;
 	}
</script>

</head>
<body>
<div align="center">
<div style="width:1200px;">
<div class="layui-layout" style="margin-left:20px;margin-top:20px">
	<div class="layui-bg-white" style="height:40px">
	<div class="layui-row">
    <div class="layui-col-xs1">
    <button  style="cursor: pointer;"  class="layui-btn layui-bg-green layui-btn-sm" onclick="add()"><i class="layui-icon">&#xe654;</i>添加</button>
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
<form action="<%=contextPath %>/" method="post" id="form3">
		<table lay-skin="nob" class="layui-table"
			id="table1" title="用户管理" 
			rowsDisplayed="15" style="width:1200px;">
			<thead>
     		<tr>
     			<th><input name="selectAll" type="checkbox" onchange="selectAllToggle();"/></th>
     			<th>&nbsp;用户名称&nbsp;</th>
     			<th>&nbsp;机构名称&nbsp;</th>
     			<th>&nbsp;邮箱&nbsp;</th>
     			<th>&nbsp;电话&nbsp;</th>
     			<!-- <th>&nbsp;用户分析条件&nbsp;</th> -->
     			<th>&nbsp;操作&nbsp;</th>
     		</tr>
     		</thead>
     		<c:forEach items="${userArr }" var="user">
     		<c:if test="${user ne null }">
     		<tr>
     			<td><input name="" type="checkbox" value="${user.userId }" <c:if test="${user.userName eq 'raq_visitor'}">disabled</c:if>></td>
     			<input name="userId_${user.userId}" type="hidden" value="${user.userId }"/>
	     		<td><input name="userName_${user.userId}" type="text" value="${user.userName }" <c:if test="${user.userName eq 'raq_visitor'}">disabled</c:if>/></td>
	     		<td>
	     		<select name="userRole_${user.userId}" id="roleList_${user.userId}" <c:if test="${user.userName eq 'raq_visitor'}">disabled</c:if>>
		     	<c:forEach items="${roleArr }" var="role">
		     		<c:if test="${role ne null }">
	     			<option value="${role.id }" <c:if test="${user.roleId eq role.id }">selected</c:if>>${role.name }</option>
	     			</c:if>
		     	</c:forEach>
	     		</select>
	     		</td>
	     		<td>${user.email}</td>
     			<td>${user.phone}</td>
     			<%-- <td onmouseenter="showMenu(this, '${user.userId}');">
     			<span>下拉&nbsp;<i style="font-size: 10px" class="layui-icon">&#xe61a;</i></span>
			    <dl class="layui-nav-child" onmouseleave="hideMenu(this);" style="top: 0px">
			    </dl>
     			</td> --%>
	     		<td>
	     		<c:if test="${user.userName ne 'raq_visitor'}"><input type="button" onclick="modifyInList('${user.userId}');" class="layui-btn layui-btn-sm layui-bg-green" value="保存"/></c:if>
	     		</td>
     		</tr>
     		</c:if>
     		</c:forEach>
     	</table>
</form>
</div>
</body>
</html>

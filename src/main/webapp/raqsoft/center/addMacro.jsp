<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<%@ page import="com.raqsoft.report.view.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
  	String contextPath = request.getContextPath();
	com.raqsoft.center.Config cfg = com.raqsoft.center.Center.getConfig( application );
	Object[] dqldbs = cfg.getSpecifiedDbs("com.datalogic.jdbc.LogicDriver");
	String dct_vsb_json = cfg.getDctVsbJson();
	String fileRoot = cfg.getFileRoot();
%>
<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=contextPath%><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<script>
function form_submit(){
	form = $("#form")[0];
	var submiturl = form.action;
	if(form.acId.value == null || form.acId.value == 0){
		alert("必须输\"条件名(id)\"");
		return;
	}
	submiturl += "&userId="+encodeURIComponent(form.userId.value);
	submiturl += "&acId="+encodeURIComponent(form.acId.value);
	submiturl += "&dataSource="+encodeURIComponent(form.dataSource.value);
	submiturl += "&filters="+encodeURIComponent(form.filters.value);
	submiturl += "&params="+encodeURIComponent(form.params.value);
	submiturl += "&vsb="+encodeURIComponent(form.vsb.value);
	$.ajax({
		type:'post',
		data:[],
		url:submiturl,
		success:function(){
			alert("保存成功");
			window.location="<%=contextPath%>/reportCenterServlet?action=16";
		}
	});
}
function filter(event){
	console.log(event);
	 $('#vsb').empty();
	 $('#vsb').append("<OPTION value=\"\"></OPTION>");
	 var json = eval("("+"<%=dct_vsb_json%>"+")");
	 var fileRoot = "<%=fileRoot%>";
	 for(var i = 0; i < json.length; i++){
		 for(var key in json[i]){
			 if(key == event.value){
				 var selections = json[i][key];
				 for(var j = 0; j < selections.length; j++){
					 var vsbs = selections[j]['vsbs'];
					 for(var k = 0; k < vsbs.length; k++){
						 var select = "";
						 if(fileRoot+"/"+vsbs[k] == "${analyseCondition.vsb}"){
							 select = "selected"
						 }
						 $('#vsb').append("<OPTION "+select+" value=\""+fileRoot+"/"+vsbs[k]+"\">"+vsbs[k]+"</OPTION>");
					 }
				 }
			 }
		 }
	 }
}
$(function(){
	$("#form")[0].dataSource.value = '${analyseCondition.dataSource }';
	filter($('#dataSource')[0]);
	layui.use('form', function(){
		  var form = layui.form; 
		  form.render();
	});
});
</script>
</head>
<body>
	<div align="center" class="layui" style="margin-top:20px"></div>
	<br>
	<div align="center">
	<form id=form method=post
		action="<%=contextPath%>/reportCenterServlet?action=${userAction}">
		<input type="hidden" value="${userId}" id="userId" name="userId"/>
		<TABLE align=center class="layui-table" style="table-layout: fixed; BORDER-COLLAPSE: collapse;width:350px">
			<tr class="">
				<td><span>条件名(id)</span></td>
				<td>
					<c:if test="${analyseCondition eq null }"><input class=layui-input type="text" value="" id="acId" name="acId"/></c:if>
					<c:if test="${analyseCondition ne null }"><input type="hidden" value="${analyseCondition.id}" id="acId" name="acId"/>${analyseCondition.id}</c:if>
				</td>
			</tr>
			<tr class="">
				<td><span>dql数据源</span></td>
				<TD><select onchange="filter(this);" name=dataSource id=dataSource class="layui-input"> 
		    	<option value="">不选择</option>
		    	<%
		    		for( int i = 0; i < dqldbs.length; i++ ) {
		   				out.println( "<option" );
		   				out.println( " value=\"" + dqldbs[i] + "\">" + dqldbs[i] + "</option>" );
		    		}
		    	%>
		    	</select>
		    	</TD>
			</tr>
			<tr class="">
				<td><span>用户过滤条件</span></td>
				<td>
					<input class=layui-input type="text" placeholder="格式:[param1,value1;param2,value2]" value="${analyseCondition.filters}" id="filters" name="filters"/>
				</td>
			</tr>
			<tr class="">
				<td><span>用户参数</span></td>
				<td>
					<input class=layui-input type="text" placeholder="格式:[param1,value1;param2,value2]" value="${analyseCondition.paramValues}" id="params" name="params"/>
				</td>
			</tr>
			<tr class="">
				<td><span>vsb文件</span></td>
				<td>
					<select class=layui-input id=vsb name=vsb value="">
					</select>
				</td>
			</tr>
			<TR>
				<td width=100% align=center colspan=2>
					<input class="layui-btn layui-btn-green" type="button" class="layui-btn layui-btn-green" onclick="form_submit();" value="提交"/>
				</td>
			</TR>
		</table>
	</form>
	</div>
</body>
</html>
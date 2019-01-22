<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>OS信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/jquery/jquery-autocomplete/jquery.autocomplete.css">
  <script type="text/javascript" src="<%=basePath%>/plug-in/jquery/jquery-autocomplete/jquery.autocomplete.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="serverController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${serverPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							OS名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="serverName" name="serverName" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">OS名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							ip信息:
						</label>
					</td>
					<td class="value">
					     	 <input id="ip" name="ip" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">ip信息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"  typeGroupCode="oststa"  defaultVal="${serverPage.status}" hasLabel="false"  title="状态" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="osType" type="list"  typeGroupCode="ostype"  defaultVal="${serverPage.osType}" hasLabel="false"  title="类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							虚拟机:
						</label>
					</td>
					<td class="value">
							 <%--  <t:dictSelect field="virtural" type="radio"  typeGroupCode="01NY"  defaultVal="${serverPage.virtural}" hasLabel="false"  title="虚拟机" ></t:dictSelect>   --%>   
						
							 <input type="radio" name="virtural"   value="1" onclick="getRadio()">是
							 <input type="radio" name="virtural"  checked="checked" value="0" onclick="getRadio2()">否
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">虚拟机</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							系统版本号:
						</label>
					</td>
					<td class="value">
					     	 <input id="osVersion" name="osVersion" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">系统版本号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							配置信息:
						</label>
					</td>
					<td class="value">
					     	 <input id="configInfo" name="configInfo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">配置信息</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							管理员:
						</label>
					</td>
					<td class="value">
					     	 <!-- <input id="manageIp" name="manageIp" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
						 -->
						   	 <input id="administrator" name="administrator" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
						
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">管理员</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建日期:
						</label>
					</td>
					<td class="value">
							   <input id="createDate" name="createDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark1" name="remark1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<!-- 硬件上层容器: -->
							上层容器(硬件设备):
						</label>
					</td>
					<td class="value">
					     	<!-- <input id="container" name="container" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							  -->
							 
							 <t:autocomplete entityName="HardwareEntity" searchField="name" name="container"></t:autocomplete> 
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">硬件上层容器</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							上层容器(虚拟机):
						</label>
					</td>
					<td class="value">
					     <!-- 	 <input id="vcontainer2" name="vcontainer2" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							 -->
							 <t:autocomplete entityName="ServerEntity" searchField="serverName" name="vcontainer" ></t:autocomplete> 
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">虚拟上层容器</label>
						</td>
					</tr> 
				
				
			</table>
		</t:formvalid>
		<p>${entityName}</p>
 </body>

  <script src = "webpage/com/rms/Server/server.js"></script>	
  
   <script type="text/javascript">
   
   
   function getRadio_2(){
	   alert("是虚拟机");
	   
   }
   var names ; //定义数据
   var  url="serverController.do?getVservers";//获取虚拟机设备
    function getRadio(){
    	alert("是虚拟机");

		  var  url="serverController.do?getVservers";//获取虚拟机设备
	
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			//dataType: "json",
			//contentType: "application/json",
			url : url,// 请求的action路径
			
			error : function() {
					//无此路径
			},
			
			success : function(data) {
		/*   var d = $.parseJSON(data);
		  if (d.success) {//获取数据成功
			
				  alert(d.attributes.entityName);
				 
				  // $("#container").attr("entityName","ServerEntity");
				   //  $("#container").attr("searchField","serverName");
				     $( "#container" ).autocomplete({
				    	 searchField : d.attributes.entityName,
				    	 entityName: d.attributes.filedName
				       });
				    alert("2222222222222222");
		 } else {//获取数据失败
			 
				

		  } */
			names = data;
            alert(data);
           autocompleteFn(url);
		}
	  });
		
    }
   
   
   
   
  //自动 补全方法
    function autocompleteFn(names){
      $("#container").autocomplete(names,{
        minChars:1,
        max: 10,
        dataType:"json",
        autoFill: true,
        mustMatch: true,
        matchContains: true,
        scrollHeight: 220,
        formatItem: function(data, i, total) {
          return "<I>"+data.SERVER_NAME+"</I>";
        },
        formatMatch: function(data, i, total) {
          return data.SERVER_NAME;
        },
        formatResult: function(data) {
          return data.SERVER_NAME;
        }
      });
    } 

   
    
    
    function getRadio2(){
    	alert("不是虚拟机");
    	  $("[name='container']").attr("datatype","*")
    }
   
   </script>

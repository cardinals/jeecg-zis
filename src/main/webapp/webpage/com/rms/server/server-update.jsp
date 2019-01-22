<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>OS信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="serverController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${serverPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								OS名称:
							</label>
						</td>
						<td class="value">
						    <input id="serverName" name="serverName" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${serverPage.serverName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">OS名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								ip信息:
							</label>
						</td>
						<td class="value">
						    <input id="ip" name="ip" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${serverPage.ip}'/>
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
									<t:dictSelect field="status" type="list"  typeGroupCode="oststa"   defaultVal="${serverPage.status}" hasLabel="false"  title="状态" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="osType" type="list"  typeGroupCode="ostype"   defaultVal="${serverPage.osType}" hasLabel="false"  title="类型" ></t:dictSelect>     
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
									<t:dictSelect field="virtural" type="radio"  typeGroupCode="01NY"   defaultVal="${serverPage.virtural}" hasLabel="false"  title="虚拟机" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">虚拟机</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								系统版本号:
							</label>
						</td>
						<td class="value">
						    <input id="osVersion" name="osVersion" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${serverPage.osVersion}'/>
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
						    <input id="configInfo" name="configInfo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${serverPage.configInfo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">配置信息</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								管理员:
							</label>
						</td>
						<td class="value">
						   <%--  <input id="manageIp" name="manageIp" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${serverPage.manageIp}'/>
							 --%>  <input id="administrator" name="administrator" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${serverPage.administrator}'/>
						
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
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" value='<fmt:formatDate value='${serverPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark1" name="remark1" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${serverPage.remark1}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								硬件上层容器:
							</label>
						</td>
						<td class="value">
						    <%-- <input id="container" name="container" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${serverPage.container}'/> --%>
							 <t:autocomplete entityName="HardwareEntity" searchField="name" name="container" defValue="${serverPage.container}"></t:autocomplete> 
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">硬件上层容器</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								虚拟上层容器:
							</label>
						</td>
						<td class="value">
						    <%-- <input id="vcontainer2" name="vcontainer2" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${serverPage.vcontainer}'/> --%>
							
							<t:autocomplete entityName="ServerEntity" searchField="serverName" name="vcontainer"  defValue="${serverPage.vcontainer}"></t:autocomplete> 
						
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">虚拟上层容器</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/Server/server.js"></script>		

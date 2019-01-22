<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>应用管理</title>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="rmsApplicationJeecgController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${rmsApplicationJeecgPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							中文名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="applicationNameCn" name="applicationNameCn" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							英文名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="applicationName" name="applicationName" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							OS:
						</label>
					</td>
					<td class="value">
					     	 <!-- <input id="serverId" name="serverId" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" /> -->
							 <t:autocomplete entityName="ServerEntity" searchField="serverName" name="serverId" datatype="*"></t:autocomplete> 
							
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">OS</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="appType" type="list"  typeGroupCode="appliType"  defaultVal="${rmsApplicationJeecgPage.appType}" hasLabel="false"  title="类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"  typeGroupCode="applstate"  defaultVal="${rmsApplicationJeecgPage.status}" hasLabel="false"  title="状态" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							应用描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="appInfo" name="appInfo" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">应用描述</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							管理员:
						</label>
					</td>
					<td class="value">                                     
					     	 <input id="administrator" name="administrator" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">管理员</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上线日期:
						</label>
					</td>
					<td class="value">                                                            
							   <input id="launchDate" name="launchDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" value='${launchDate}'/>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上线日期</label>
						</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" >
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remark1" name="remark1"  ignore="ignore" ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/application/rmsApplicationJeecg.js"></script>		

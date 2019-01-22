<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_数据库管理</title>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="databaseController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${databasePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							OS:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="os" name="os" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							 -->
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
							  <t:dictSelect field="dbType" type="list"  typeGroupCode="dbTypes"  defaultVal="${databasePage.dbType}" hasLabel="false"  title="类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号:
						</label>
					</td>
					<td class="value">
					     	 <input id="version" name="version" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"  typeGroupCode="applstate"  defaultVal="${databasePage.status}" hasLabel="false"  title="状态" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="databaseName" name="databaseName" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							SID:
						</label>
					</td>
					<td class="value">
					     	 <input id="sid" name="sid" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">SID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							RACHA:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="racorha" type="list"  typeGroupCode="racha"  defaultVal="${databasePage.racorha}" hasLabel="false"  title="RACHA" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">RACHA</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							管理员:
						</label>
					</td>
					<td class="value">
					     	 <input id="administrator" name="administrator" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">管理员</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上线时间:
						</label>
					</td>
					<td class="value">
							   <input id="launchDate" name="launchDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上线时间</label>
						</td>
				</tr>
				<tr>
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
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/database/database.js"></script>		

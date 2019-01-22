<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>连接到rms</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="toRmsController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${toRmsPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户名:
							</label>
						</td>
						<td class="value">
						    <input id="username" name="username" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${toRmsPage.username}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								密码:
							</label>
						</td>
						<td class="value">
						    <input id="password" name="password" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${toRmsPage.password}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">密码</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/torms/toRms.js"></script>		

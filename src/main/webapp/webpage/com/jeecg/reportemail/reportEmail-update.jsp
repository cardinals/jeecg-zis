<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报表邮件配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reportEmailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${reportEmailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								报表名称:
							</label>
						</td>
						<td class="value">
						    <input id="reportName" name="reportName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${reportEmailPage.reportName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报表名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收件人:
							</label>
						</td>
						<td class="value">
						    <input id="sentTo" name="sentTo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${reportEmailPage.sentTo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收件人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								抄送人:
							</label>
						</td>
						<td class="value">
						    <input id="sentCc" name="sentCc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${reportEmailPage.sentCc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抄送人</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/reportemail/reportEmail.js"></script>		

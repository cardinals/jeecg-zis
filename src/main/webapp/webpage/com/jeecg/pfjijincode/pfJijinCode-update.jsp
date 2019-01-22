<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>基金代码表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pfJijinCodeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pfJijinCodePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								基金代码:
							</label>
						</td>
						<td class="value">
						    <input id="code" name="code" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfJijinCodePage.code}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">基金代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外键:
							</label>
						</td>
						<td class="value">
						    <input id="foreignKey" name="foreignKey" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfJijinCodePage.foreignKey}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外键</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/pfjijincode/pfJijinCode.js"></script>		

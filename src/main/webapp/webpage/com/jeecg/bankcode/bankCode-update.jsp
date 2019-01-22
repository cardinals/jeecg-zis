<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行编码</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bankCodeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${bankCodePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行编码:
							</label>
						</td>
						<td class="value">
						    <input id="bankcode" name="bankcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankCodePage.bankcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行名称:
							</label>
						</td>
						<td class="value">
						    <input id="bankname" name="bankname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankCodePage.bankname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行名称</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/bankcode/bankCode.js"></script>		

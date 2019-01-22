<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_付款管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="contractController.do?setProxy" >
		<input id="id" name="id" type="hidden" value="${contractPage.id}"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
						<td align="right">
						<label class="Validform_label">
							代理人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="agent" type="list"  typeGroupCode="user"  hasLabel="false"  title="代理人" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款人</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/payment/payment.js"></script>		

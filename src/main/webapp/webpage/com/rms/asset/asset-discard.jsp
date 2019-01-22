<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_资产废弃</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="assetController.do?discardAsset" >
					<input id="id" name="id" type="hidden" value="${assetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
				<p class="value">
				 确定要废弃该资产吗？<br>
						资产类型：${assetType}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						资产编号：${assetPage.assetNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						员工编号：${employeePage.employeeNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						员工姓名：${employeePage.employeeName}
				</p>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/asset/asset.js"></script>		

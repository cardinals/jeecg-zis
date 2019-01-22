<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_资产管理</title>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="assetController.do?updateEmployee" >
					<input id="id" name="id" type="hidden" value="${assetPage.id}"/>
		<table style="width: 300px;" cellpadding="0" cellspacing="1" class="formtable">
		
				<tr>
					<td align="right">
						<label class="Validform_label">
							员工:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="vendor" name="vendor" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							 -->
							 
							  <t:autocomplete entityName="EmployeeEntity" searchField="employeeName" name="yuanGongName" defValue="${name}"></t:autocomplete> 
				
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/asset/asset.js"></script>		

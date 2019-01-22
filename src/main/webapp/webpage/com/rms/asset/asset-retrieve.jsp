<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_资产管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="assetController.do?assetRetrieve" >
					<input id="id" name="id" type="hidden" value="${assetPage.id }"/>
		<table style="width: 300px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							请选择回收入库位置:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="storehouse" type="list"  typeGroupCode="storehouse"  hasLabel="false"  title="公司" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司</label>
						</td>
					
					</tr>
				
				<tr>
				<p class="value">
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

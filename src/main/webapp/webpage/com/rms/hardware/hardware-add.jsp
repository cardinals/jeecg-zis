<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_设备管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hardwareController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${hardwarePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="maintype" name="maintype" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							 -->
							  <t:dictSelect field="maintype" type="list"  typeGroupCode="sblx"  defaultVal="${hardwarePage.maintype}" hasLabel="false"  title="设备类型" ></t:dictSelect>     
							  
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"  typeGroupCode="hwst"  defaultVal="${hardwarePage.status}" hasLabel="false"  title="状态" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">设备名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							序列号:
						</label>
					</td>
					<td class="value">
					     	 <input id="hardwareNo" name="hardwareNo" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">序列号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							厂商:
						</label>
					</td>
					<td class="value">
					     	 <input id="vendor" name="vendor" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">厂商</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							型号:
						</label>
					</td>
					<td class="value">
					     	 <input id="hardwareType" name="hardwareType" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">型号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="location" type="list"  typeGroupCode="sbdz"  defaultVal="${hardwarePage.location}" hasLabel="false"  title="地址" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							机柜位:
						</label>
					</td>
					<td class="value">
					     	 <input id="position" name="position" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机柜位</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							采购日期:
						</label>
					</td>
					<td class="value">
							   <input id="purchaseDate" name="purchaseDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							保修日期:
						</label>
					</td>
					<td class="value">
							   <input id="warrantyDate" name="warrantyDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">保修日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最近维修日期:
						</label>
					</td>
					<td class="value">
							   <input id="maintainDate" name="maintainDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最近维修日期</label>
						</td>
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
				<tr>
					<td align="right">
						<label class="Validform_label">
							远程管理IP:
						</label>
					</td>
					<td class="value">
					     	 <input id="remoteIlo" name="remoteIlo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">远程管理IP</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							保修厂商:
						</label>
					</td>
					<td class="value">
					     	 <input id="warrantyVendor" name="warrantyVendor" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">保修厂商</label>
						</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/hardware/hardware.js"></script>		

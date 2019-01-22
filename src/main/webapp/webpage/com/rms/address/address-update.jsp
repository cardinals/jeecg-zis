<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_地址管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="addressController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${addressPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								全称:
							</label>
						</td>
						<td class="value">
						    <input id="addrdetails" name="addrdetails" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${addressPage.addrdetails}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">全称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								简称:
							</label>
						</td>
						<td class="value">
						    <input id="addrshort" name="addrshort" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addressPage.addrshort}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">简称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								联系人:
							</label>
						</td>
						<td class="value">
						    <input id="contact" name="contact" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addressPage.contact}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						    <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addressPage.phone}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addressPage.remark}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/address/address.js"></script>		

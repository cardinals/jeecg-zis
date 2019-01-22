<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>机构客户和类型导入</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="orgptFucmodCustomerController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${orgptFucmodCustomerPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							客户机构全称:
						</label>
					</td>
					<td class="value">
					     	 <input id="orgcustomername" name="orgcustomername" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户机构全称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							机构类型1:
						</label>
					</td>
					<td class="value">
					     	 <input id="orgcustomertype1" name="orgcustomertype1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机构类型1</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							机构类型2:
						</label>
					</td>
					<td class="value">
					     	 <input id="orgcustomertype2" name="orgcustomertype2" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机构类型2</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/weekrep/orgptFucmodCustomer.js"></script>		

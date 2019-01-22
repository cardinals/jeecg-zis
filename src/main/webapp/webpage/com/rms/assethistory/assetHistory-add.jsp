<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_历史资产表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="assetHistoryController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${assetHistoryPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							历史记录ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="assetHistoryId" name="assetHistoryId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">历史记录ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							操作:
						</label>
					</td>
					<td class="value">
					     	 <input id="operation" name="operation" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							唯一键:
						</label>
					</td>
					<td class="value">
					     	 <input id="assetId" name="assetId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">唯一键</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							公司:
						</label>
					</td>
					<td class="value">
					     	 <input id="company" name="company" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							资产类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="maintype" name="maintype" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资产类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							资产编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="assetNo" name="assetNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资产编号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地区:
						</label>
					</td>
					<td class="value">
					     	 <input id="location" name="location" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							供应商:
						</label>
					</td>
					<td class="value">
					     	 <input id="vendor" name="vendor" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							品牌型号:
						</label>
					</td>
					<td class="value">
					     	 <input id="assetType" name="assetType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">品牌型号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							设备参数:
						</label>
					</td>
					<td class="value">
					     	 <input id="config" name="config" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">设备参数</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电脑型号:
						</label>
					</td>
					<td class="value">
					     	 <input id="pcType" name="pcType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电脑型号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							购置日期:
						</label>
					</td>
					<td class="value">
							   <input id="purchaseDate" name="purchaseDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">购置日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							购置单价:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">购置单价</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							创建日期:
						</label>
					</td>
					<td class="value">
							   <input id="createTime" name="createTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最后修改时间:
						</label>
					</td>
					<td class="value">
							   <input id="lastUpdateTime" name="lastUpdateTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最后修改时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							使用人:
						</label>
					</td>
					<td class="value">
					     	 <input id="employeeId" name="employeeId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">使用人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人:
						</label>
					</td>
					<td class="value">
					     	 <input id="createdBy" name="createdBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							修改人:
						</label>
					</td>
					<td class="value">
					     	 <input id="updatedBy" name="updatedBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">修改人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							操作人:
						</label>
					</td>
					<td class="value">
					     	 <input id="operationBy" name="operationBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作人</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/assethistory/assetHistory.js"></script>		

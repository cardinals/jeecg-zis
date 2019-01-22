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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="assetController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${assetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								公司:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="company" type="list"  typeGroupCode="company"   defaultVal="${assetPage.company}" hasLabel="false"  title="公司" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								地区:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="location" type="list"  typeGroupCode="area"   defaultVal="${assetPage.location}" hasLabel="false"  title="地区" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								资产类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="maintype" type="list"  typeGroupCode="assets"   defaultVal="${assetPage.maintype}" hasLabel="false"  title="资产类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资产类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								资产编号:
							</label>
						</td>
						<td class="value">
						    <input id="assetNo" name="assetNo" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${assetPage.assetNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资产编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								品牌:
							</label>
						</td>
						<td class="value">
						    <input id="vendor" name="vendor" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.vendor}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">品牌</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								型号:
							</label>
						</td>
						<td class="value">
						    <input id="assetType" name="assetType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.assetType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">型号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								设备参数:
							</label>
						</td>
						<td class="value">
						    <input id="config" name="config" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.config}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">设备参数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								电脑型号:
							</label>
						</td>
						<td class="value">
						    <input id="pcType" name="pcType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.pcType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电脑型号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电脑序列号:
							</label>
						</td>
						<td class="value">
						    <input id="pcNo" name="pcNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.pcNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电脑序列号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								电源序列号:
							</label>
						</td>
						<td class="value">
						    <input id="powerNo" name="powerNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.powerNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电源序列号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电池序列号:
							</label>
						</td>
						<td class="value">
						    <input id="batteryNo" name="batteryNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.batteryNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电池序列号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								有线Mac:
							</label>
						</td>
						<td class="value">
						    <input id="mac" name="mac" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.mac}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有线Mac</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								无线Mac:
							</label>
						</td>
						<td class="value">
						    <input id="wifiMac" name="wifiMac" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.wifiMac}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">无线Mac</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								购置日期:
							</label>
						</td>
						<td class="value">
									  <input id="purchaseDate" name="purchaseDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="ignore" value='<fmt:formatDate value='${assetPage.purchaseDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">购置日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								保修年限:
							</label>
						</td>
						<td class="value">
						    <input id="warrantyPeriod" name="warrantyPeriod" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.warrantyPeriod}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">保修年限</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								购置单价:
							</label>
						</td>
						<td class="value">
						    <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.price}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">购置单价</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remark1" name="remark1" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.remark1}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								操作:
							</label>
						</td>
						<td class="value">
						    <input id="operation" name="operation" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${assetPage.operation}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/asset/asset.js"></script>		

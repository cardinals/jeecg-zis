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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="paymentController.do?doAdd" >
				<%-- 	<input id="contractId" name="contractId" type="hidden" value="${paymentPage.id }"/>
					 --%><!-- 合同-contractId -->
					 	<input id="contract_id" name="contract_id" type="hidden" value="${contractPage.id}"/>
					<input id="contractId" name="contractId" type="hidden" value="${contractPage.contractId}"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
	<tr>
			<td align="right">
						<label class="Validform_label">
							综合编号:
						</label>
					</td>
					<td class="value">
						 <input id="param1" name="param1" value="${contractPage.contractNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" readonly="readonly" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">综合编号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							合同名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="param2" name="param2" value="${contractPage.name}"  type="text" style="width: 150px" class="inputxt"  ignore="ignore" readonly="readonly" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同名称</label>
						</td>
				</tr>
				
				
				<tr>
						<td align="right">
						<label class="Validform_label">
							付款人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="paidBy" type="list"  typeGroupCode="user"  defaultVal="${paymentPage.paidBy}" hasLabel="false"  title="付款人" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							报销单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="reimburseNo" name="reimburseNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报销单号</label>
						</td>
				</tr>
				<tr>
				<td align="right">
						<label class="Validform_label">
							付款金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="paymentAmount" name="paymentAmount" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款金额</label>
						</td>
						
					<td align="right">
						<label class="Validform_label">
							付款事由:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款事由</label>
						</td>
				</tr>
		<%-- 		<tr>
					<td align="right">
						<label class="Validform_label">
							付款人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="paidBy" type="list"  typeGroupCode="user"  defaultVal="${paymentPage.paidBy}" hasLabel="false"  title="付款人" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款人</label>
						</td>
				</tr> --%>
		<!-- 		<tr>
					<td align="right">
						<label class="Validform_label">
							付款金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="paymentAmount" name="paymentAmount" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款金额</label>
						</td>
				</tr> -->
				<tr>
					<td align="right">
						<label class="Validform_label">
							付款时间:
						</label>
					</td>
					<td class="value">
							   <input id="paymentDate" name="paymentDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款时间</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/payment/payment.js"></script>		

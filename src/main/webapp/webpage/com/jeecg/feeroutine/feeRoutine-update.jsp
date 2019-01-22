<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>部门日常费用</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="feeRoutineController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${feeRoutinePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								报销日期:
							</label>
						</td>
						<td class="value">
									  <input id="feeDate" name="feeDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${feeRoutinePage.feeDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报销日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								金额:
							</label>
						</td>
						<td class="value">
						    <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${feeRoutinePage.amount}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收款方:
							</label>
						</td>
						<td class="value">
						    <input id="payee" name="payee" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${feeRoutinePage.payee}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款方</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用类型:
							</label>
						</td>
						<td class="value">
						    <input id="feeType" name="feeType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${feeRoutinePage.feeType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用性质:
							</label>
						</td>
						<td class="value">
						    <input id="feeXingzhi" name="feeXingzhi" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${feeRoutinePage.feeXingzhi}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用性质</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发票:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="feeFapiao" type="list"  typeGroupCode="FPYN"   defaultVal="${feeRoutinePage.feeFapiao}" hasLabel="false"  title="发票" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发票</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								经办人:
							</label>
						</td>
						<td class="value">
						    <input id="feeJingbanren" name="feeJingbanren" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${feeRoutinePage.feeJingbanren}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">经办人</label>
						</td>
					</tr>
				
					<tr>
						<td align="right">
							<label class="Validform_label">
								付款说明明细:
							</label>
						</td>
						<td class="value" >
						  	 	<textarea id="description" style="width:600px;" class="inputxt" rows="6" name="description"  ignore="ignore" >${feeRoutinePage.description}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款说明明细</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/feeroutine/feeRoutine.js"></script>		

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>部门支出</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="departmentFeeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${departmentFeePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								日期:
							</label>
						</td>
						<td class="value">
									  <input id="feeDate" name="feeDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${departmentFeePage.feeDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								划款金额:
							</label>
						</td>
						<td class="value">
						    <input id="feeAmount" name="feeAmount" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${departmentFeePage.feeAmount}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">划款金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用类型:
							</label>
						</td>
						<td class="value">
						    <input id="feeType" name="feeType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${departmentFeePage.feeType}'/>
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
						    <input id="feeXingzhi" name="feeXingzhi" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${departmentFeePage.feeXingzhi}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用性质</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用发票:
							</label>
						</td>
						<td class="value">
						    <input id="feeFapiao" name="feeFapiao" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${departmentFeePage.feeFapiao}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用发票</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								费用经办人:
							</label>
						</td>
						<td class="value">
							 <input id="feeJingbanren" name="feeJingbanren" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${departmentFeePage.feeJingbanren}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用经办人</label>
						</td>
					</tr>
				
					<tr>
						<td align="right">
							<label class="Validform_label">
								划款说明:
							</label>
						</td>
						<td class="value" >
						  	 	<textarea id="description" style="width:600px;" class="inputxt" rows="6" name="description"  ignore="ignore" >${departmentFeePage.description}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">划款说明</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/departmentfee/departmentFee.js"></script>		
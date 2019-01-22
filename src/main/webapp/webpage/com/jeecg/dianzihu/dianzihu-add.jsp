<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>垫资户本金收益</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="dianzihuController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${dianzihuPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							确认日:
						</label>
					</td>
					<td class="value">
					     	 <input id="qurenday" name="qurenday" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">确认日</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上一个交易日:
						</label>
					</td>
					<td class="value">
					     	 <input id="jiaoyiday" name="jiaoyiday" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上一个交易日</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总金额</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="principalMoney" name="principalMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本金</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="interest" name="interest" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利息</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/dianzihu/dianzihu.js"></script>		

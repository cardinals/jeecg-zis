<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_员工管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="rmsEmployJeecgController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${rmsEmployJeecgPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							员工编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="employNo" name="employNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="employName" name="employName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属部门:
						</label>
					</td>
					<td class="value">
					     	 <input id="departmentId" name="departmentId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否在职:
						</label>
					</td>
					<td class="value">
					     	 <input id="enable" name="enable" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否在职</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							邮箱:
						</label>
					</td>
					<td class="value">
					     	 <input id="emali" name="emali" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/employ/rmsEmployJeecg.js"></script>		

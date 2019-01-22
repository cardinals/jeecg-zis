<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>员工互评_资产</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasAstStaffController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasAstStaffPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalPersonDept" name="goalPersonDept" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.goalPersonDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								专业水平:
							</label>
						</td>
						<td class="value">
						     	 <input id="professionalSkill" name="professionalSkill" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.professionalSkill}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">专业水平</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合作效率:
							</label>
						</td>
						<td class="value">
						     	 <input id="coopEff" name="coopEff" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.coopEff}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作效率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合作态度:
							</label>
						</td>
						<td class="value">
						     	 <input id="coopAtti" name="coopAtti" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.coopAtti}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作态度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合作结果:
							</label>
						</td>
						<td class="value">
						     	 <input id="coopOutcome" name="coopOutcome" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.coopOutcome}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作结果</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合计:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstStaffPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合计</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/ast/staff/pasAstStaff.js"></script>		

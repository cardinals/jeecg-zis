<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PAS_员工对部门负责人评分</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasStaffLeadController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasStaffLeadPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						    <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasStaffLeadPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="dept" name="dept" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasStaffLeadPage.dept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								专业水平:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="professionalSkill" type="list"  datatype="n"  typeGroupCode="pas_s50"   defaultVal="${pasStaffLeadPage.professionalSkill}" hasLabel="false"  title="专业水平" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">专业水平</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								团队建设:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="teamBuild" type="list"  datatype="n"  typeGroupCode="pas_s50"   defaultVal="${pasStaffLeadPage.teamBuild}" hasLabel="false"  title="团队建设" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团队建设</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合计:
							</label>
						</td>
						<td class="value">
						    <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasStaffLeadPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合计</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/passtafflead/pasStaffLead.js"></script>		

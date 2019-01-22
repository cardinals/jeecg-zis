<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>绩效考核系统员工互评</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasOnStaffController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasOnStaffPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								评价人:
							</label>
						</td>
						<td class="value">
						     	 <input id="appraiser" name="appraiser" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${pasOnStaffPage.appraiser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${pasOnStaffPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalPersonDept" name="goalPersonDept" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${pasOnStaffPage.goalPersonDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务水平:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="professionalSkill" type="list" 		datatype="n" typeGroupCode="pas_s30"   defaultVal="${pasOnStaffPage.professionalSkill}" hasLabel="false"  title="业务水平" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务水平</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合作效率:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="coopEff" type="list" 		datatype="n" typeGroupCode="pas_s20"   defaultVal="${pasOnStaffPage.coopEff}" hasLabel="false"  title="合作效率" ></t:dictSelect>     
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
									<t:dictSelect field="coopAtti" type="list" 		datatype="n" typeGroupCode="pas_s20"   defaultVal="${pasOnStaffPage.coopAtti}" hasLabel="false"  title="合作态度" ></t:dictSelect>     
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
									<t:dictSelect field="coopOutcome" type="list" 		datatype="n" typeGroupCode="pas_s30"   defaultVal="${pasOnStaffPage.coopOutcome}" hasLabel="false"  title="合作结果" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作结果</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总分:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasOnStaffPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总分</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/onstaff/pasOnStaff.js"></script>		

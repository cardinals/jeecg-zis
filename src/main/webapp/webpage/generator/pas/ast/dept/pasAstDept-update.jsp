<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>部门互评_资产</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasAstDeptController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasAstDeptPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalDept" name="goalDept" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstDeptPage.goalDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门负责人:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalDeptManager" name="goalDeptManager" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstDeptPage.goalDeptManager}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门负责人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								专业水平:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="professionalSkill" type="list" 		datatype="*" typeGroupCode="pas_s30"   defaultVal="${pasAstDeptPage.professionalSkill}" hasLabel="false"  title="专业水平" ></t:dictSelect>     
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
									<t:dictSelect field="coopEff" type="list" 		datatype="*" typeGroupCode="pas_s20"   defaultVal="${pasAstDeptPage.coopEff}" hasLabel="false"  title="合作效率" ></t:dictSelect>     
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
									<t:dictSelect field="coopAtti" type="list" 		datatype="*" typeGroupCode="pas_s20"   defaultVal="${pasAstDeptPage.coopAtti}" hasLabel="false"  title="合作态度" ></t:dictSelect>     
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
									<t:dictSelect field="coopOutcome" type="list" 		datatype="*" typeGroupCode="pas_s30"   defaultVal="${pasAstDeptPage.coopOutcome}" hasLabel="false"  title="合作结果" ></t:dictSelect>     
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
						     	 <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore"  value='${pasAstDeptPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总分</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/ast/dept/pasAstDept.js"></script>		

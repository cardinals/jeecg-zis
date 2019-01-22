<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>绩效考核系统部门互评</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasOnDeptController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasOnDeptPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalDept" name="goalDept" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasOnDeptPage.goalDept}'/>
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
						     	 <input id="goalDeptManager" name="goalDeptManager" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasOnDeptPage.goalDeptManager}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门负责人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务水平:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="professionalSkill" type="list"  datatype="n"  typeGroupCode="pas_s30"   defaultVal="${pasOnDeptPage.professionalSkill}" hasLabel="false"  title="业务水平" ></t:dictSelect>     
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
									<t:dictSelect field="coopEff" type="list"  datatype="n"  typeGroupCode="pas_s20"   defaultVal="${pasOnDeptPage.coopEff}" hasLabel="false"  title="合作效率" ></t:dictSelect>     
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
									<t:dictSelect field="coopAtti" type="list"  datatype="n"  typeGroupCode="pas_s20"   defaultVal="${pasOnDeptPage.coopAtti}" hasLabel="false"  title="合作态度" ></t:dictSelect>     
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
									<t:dictSelect field="coopOutcome" type="list"  datatype="n"  typeGroupCode="pas_s30"   defaultVal="${pasOnDeptPage.coopOutcome}" hasLabel="false"  title="合作结果" ></t:dictSelect>     
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
						     	 <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="checked"  value='${pasOnDeptPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评语:
							</label>
						</td>
						<td class="value">
						     	 <input id="commentDept" name="commentDept" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasOnDeptPage.commentDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评语</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/ondept/pasOnDept.js"></script>		

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>2018年度交易支持满意度评价表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasEvaluateJiaoyiController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasEvaluateJiaoyiPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价部门:
							</label>
						</td>
						<td class="value">
						    <input id="goalDepart" name="goalDepart" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasEvaluateJiaoyiPage.goalDepart}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易指令完成度:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="professionalSkill" type="list"  datatype="n"  typeGroupCode="pas_s30"   defaultVal="${pasEvaluateJiaoyiPage.professionalSkill}" hasLabel="false"  title="交易指令完成度" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易指令完成度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								指令执行效率:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="coopAtti" type="list"  datatype="n"  typeGroupCode="pas_s30"   defaultVal="${pasEvaluateJiaoyiPage.coopAtti}" hasLabel="false"  title="指令执行效率" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">指令执行效率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易技能:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="jiaoyiSkill" type="list"  datatype="n"  typeGroupCode="pas_s20"   defaultVal="${pasEvaluateJiaoyiPage.jiaoyiSkill}" hasLabel="false"  title="交易技能" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易技能</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								信息沟通:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="talkMessage" type="list"  datatype="n"  typeGroupCode="pas_s20"   defaultVal="${pasEvaluateJiaoyiPage.talkMessage}" hasLabel="false"  title="信息沟通" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">信息沟通</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总分:
							</label>
						</td>
						<td class="value">
						    <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasEvaluateJiaoyiPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评分说明:
							</label>
						</td>
						<td class="value">
						    <input id="comment" name="comment" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasEvaluateJiaoyiPage.comment}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评分说明</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/evaluatejiaoyi/pasEvaluateJiaoyi.js"></script>		

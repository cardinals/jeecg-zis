<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PAS_董事总经理及高级管理人员互评</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="gaoguanHupingController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${gaoguanHupingPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						    <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								职位:
							</label>
						</td>
						<td class="value">
						    <input id="position" name="position" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.position}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评价人:
							</label>
						</td>
						<td class="value">
						    <input id="appraiser" name="appraiser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.appraiser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="goalPersonDept" name="goalPersonDept" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.goalPersonDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业绩完成情况:
							</label>
						</td>
						<td class="value">
						    <input id="yeji" name="yeji" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${gaoguanHupingPage.yeji}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业绩完成情况</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								团队领导力:
							</label>
						</td>
						<td class="value">
						    <input id="teamLead" name="teamLead" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${gaoguanHupingPage.teamLead}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团队领导力</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发展创新力:
							</label>
						</td>
						<td class="value">
						    <input id="deveCreate" name="deveCreate" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${gaoguanHupingPage.deveCreate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发展创新力</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								诚信敬业度:
							</label>
						</td>
						<td class="value">
						    <input id="sincerityHardwork" name="sincerityHardwork" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${gaoguanHupingPage.sincerityHardwork}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">诚信敬业度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合计:
							</label>
						</td>
						<td class="value">
						    <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合计</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								确认状态:
							</label>
						</td>
						<td class="value">
						    <input id="confirmFlag" name="confirmFlag" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${gaoguanHupingPage.confirmFlag}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">确认状态</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/gaoguanhuping/gaoguanHuping.js"></script>		

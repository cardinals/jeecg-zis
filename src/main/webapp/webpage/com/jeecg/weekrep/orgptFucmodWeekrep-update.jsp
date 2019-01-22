<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>周报</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
  <script src = "webpage/com/jeecg/weekrep/orgptFucmodWeekrep.js"></script>	
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="orgptFucmodWeekrepController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${orgptFucmodWeekrepPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								员工姓名:
							</label>
						</td>
						<td class="value">
						    <input id="staffname" name="staffname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.staffname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								拜访日期:
							</label>
						</td>
						<td class="value">
									  <input id="visitdate" name="visitdate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${orgptFucmodWeekrepPage.visitdate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">拜访日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								本周拜访机构:
							</label>
						</td>
						<td class="value">
							<%-- 		<t:dictSelect field="targetorginwk" type="list"  typeGroupCode=""   defaultVal="${orgptFucmodWeekrepPage.targetorginwk}" hasLabel="false"  title="本周拜访机构" ></t:dictSelect>     
							 --%>
							 <select id="targetorginwk" name="targetorginwk" ignore="ginore" value="${orgptFucmodWeekrepPage.targetorginwk}"></select>
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本周拜访机构</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否准入:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="accessflag" type="list"  typeGroupCode="01NY"   defaultVal="${orgptFucmodWeekrepPage.accessflag}" hasLabel="false"  title="是否准入" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否准入</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								准入类型:
							</label>
						</td>
						<td class="value">
						    <input id="accesstype" name="accesstype" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.accesstype}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">准入类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								部门:
							</label>
						</td>
						<td class="value">
						    <input id="department" name="department" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.department}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								对接人:
							</label>
						</td>
						<td class="value">
						    <input id="djperson" name="djperson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.djperson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">对接人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								对接人职位:
							</label>
						</td>
						<td class="value">
						    <input id="djpersonjob" name="djpersonjob" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.djpersonjob}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">对接人职位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务合作点:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="cooperationpoint" type="list"  typeGroupCode="cooperapo"   defaultVal="${orgptFucmodWeekrepPage.cooperationpoint}" hasLabel="false"  title="业务合作点" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务合作点</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								跟进情况:
							</label>
						</td>
						<td class="value">
						    <input id="followsituation" name="followsituation" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.followsituation}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">跟进情况</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户需求:
							</label>
						</td>
						<td class="value">
						    <input id="customerdemand" name="customerdemand" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.customerdemand}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户需求</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								投资概况:
							</label>
						</td>
						<td class="value">
						    <input id="investsituation" name="investsituation" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.investsituation}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投资概况</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								邮标:
							</label>
						</td>
						<td class="value">
						    <input id="mailsign" name="mailsign" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.mailsign}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮标</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								核心人员:
							</label>
						</td>
						<td class="value">
						    <input id="corestaff" name="corestaff" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.corestaff}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">核心人员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								核心人员职位:
							</label>
						</td>
						<td class="value">
						    <input id="corestaffpos" name="corestaffpos" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.corestaffpos}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">核心人员职位</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								需要支持事项:
							</label>
						</td>
						<td class="value">
						    <input id="needsupitems" name="needsupitems" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.needsupitems}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">需要支持事项</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.remarks}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								批注:
							</label>
						</td>
						<td class="value">
						    <input id="procomment" name="procomment" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${orgptFucmodWeekrepPage.procomment}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  	

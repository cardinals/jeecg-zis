<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>副总裁对上级评价</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasGaoguanZongcaiController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasGaoguanZongcaiPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人名称:
							</label>
						</td>
						<td class="value">
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.createBy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人登录名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建日期:
							</label>
						</td>
						<td class="value">
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pasGaoguanZongcaiPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新人名称:
							</label>
						</td>
						<td class="value">
						    <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.updateName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.updateBy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pasGaoguanZongcaiPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属公司:
							</label>
						</td>
						<td class="value">
						    <input id="sysCompanyCode" name="sysCompanyCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.sysCompanyCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公司</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								职位:
							</label>
						</td>
						<td class="value">
						    <input id="position" name="position" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.position}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职位</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								发展创新力:
							</label>
						</td>
						<td class="value">
						    <input id="deveCreate" name="deveCreate" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasGaoguanZongcaiPage.deveCreate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发展创新力</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评价人:
							</label>
						</td>
						<td class="value">
						    <input id="appraiser" name="appraiser" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.appraiser}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评价人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								合计:
							</label>
						</td>
						<td class="value">
						    <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.totalScore}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合计</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						    <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								诚信敬业度:
							</label>
						</td>
						<td class="value">
						    <input id="sincerityHardwork" name="sincerityHardwork" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasGaoguanZongcaiPage.sincerityHardwork}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">诚信敬业度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						    <input id="goalPersonDept" name="goalPersonDept" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.goalPersonDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业绩完成情况:
							</label>
						</td>
						<td class="value">
						    <input id="yeji" name="yeji" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasGaoguanZongcaiPage.yeji}'/>
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
						    <input id="teamLead" name="teamLead" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasGaoguanZongcaiPage.teamLead}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团队领导力</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								确认状态:
							</label>
						</td>
						<td class="value">
						    <input id="confirmFlag" name="confirmFlag" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasGaoguanZongcaiPage.confirmFlag}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">确认状态</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/zongcai/pasGaoguanZongcai.js"></script>		

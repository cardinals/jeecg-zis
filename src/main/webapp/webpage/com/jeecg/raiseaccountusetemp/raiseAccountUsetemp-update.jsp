<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品变化临时表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="raiseAccountUsetempController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${raiseAccountUsetempPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人名称:
							</label>
						</td>
						<td class="value">
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.createBy}'/>
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
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${raiseAccountUsetempPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新人名称:
							</label>
						</td>
						<td class="value">
						    <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.updateName}'/>
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
						    <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.updateBy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${raiseAccountUsetempPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
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
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属公司:
							</label>
						</td>
						<td class="value">
						    <input id="sysCompanyCode" name="sysCompanyCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.sysCompanyCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公司</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行账号:
							</label>
						</td>
						<td class="value">
						    <input id="yinhangAccount" name="yinhangAccount" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.yinhangAccount}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								银行户名:
							</label>
						</td>
						<td class="value">
						    <input id="yinhangName" name="yinhangName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.yinhangName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行户名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								开户银行全称:
							</label>
						</td>
						<td class="value">
						    <input id="yinhangNamefull" name="yinhangNamefull" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.yinhangNamefull}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行全称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								曾用产品:
							</label>
						</td>
						<td class="value">
						    <input id="preProject" name="preProject" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.preProject}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">曾用产品</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								结息状态:
							</label>
						</td>
						<td class="value">
						    <input id="jiexiStatus" name="jiexiStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.jiexiStatus}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结息状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								结息状态2:
							</label>
						</td>
						<td class="value">
						    <input id="jiexiStatus2" name="jiexiStatus2" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.jiexiStatus2}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结息状态2</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外键:
							</label>
						</td>
						<td class="value">
						    <input id="foreignKey" name="foreignKey" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${raiseAccountUsetempPage.foreignKey}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外键</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/raiseaccountusetemp/raiseAccountUsetemp.js"></script>		

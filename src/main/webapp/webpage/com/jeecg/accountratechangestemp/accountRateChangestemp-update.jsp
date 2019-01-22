<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>账户利率临时表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="accountRateChangestempController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${accountRateChangestempPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人名称:
							</label>
						</td>
						<td class="value">
						    <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.createName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						    <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.createBy}'/>
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
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${accountRateChangestempPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新人名称:
							</label>
						</td>
						<td class="value">
						    <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.updateName}'/>
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
						    <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.updateBy}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${accountRateChangestempPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
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
						    <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.sysOrgCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属公司:
							</label>
						</td>
						<td class="value">
						    <input id="sysCompanyCode" name="sysCompanyCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.sysCompanyCode}'/>
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
						    <input id="detailNumber" name="detailNumber" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.detailNumber}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								银行户名:
							</label>
						</td>
						<td class="value">
						    <input id="detailName" name="detailName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.detailName}'/>
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
						    <input id="detailFullname" name="detailFullname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.detailFullname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行全称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								利率:
							</label>
						</td>
						<td class="value">
						    <input id="detailRate" name="detailRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.detailRate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								开始日期:
							</label>
						</td>
						<td class="value">
									  <input id="startDate" name="startDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${accountRateChangestempPage.startDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								结束日期:
							</label>
						</td>
						<td class="value">
									  <input id="endDate" name="endDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${accountRateChangestempPage.endDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外键:
							</label>
						</td>
						<td class="value">
						    <input id="foreignKey" name="foreignKey" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${accountRateChangestempPage.foreignKey}'/>
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
  <script src = "webpage/com/jeecg/accountratechangestemp/accountRateChangestemp.js"></script>		

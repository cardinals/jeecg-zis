<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户信息临时表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bankAccountempController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${bankAccountempPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							创建人登录名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
							   <input id="createDate" name="createDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							更新人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							更新日期:
						</label>
					</td>
					<td class="value">
							   <input id="updateDate" name="updateDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
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
					     	 <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							所属公司:
						</label>
					</td>
					<td class="value">
					     	 <input id="sysCompanyCode" name="sysCompanyCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							银行户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountName" name="acountName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="acountFullname" name="acountFullname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行全称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							开户银行简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountShortname" name="acountShortname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行简称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountType" name="acountType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							开户日期:
						</label>
					</td>
					<td class="value">
							   <input id="kaihuDate" name="kaihuDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountStatus" name="acountStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							账户利率:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountInterestRate" name="acountInterestRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户利率</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							利率启用日期:
						</label>
					</td>
					<td class="value">
							   <input id="interestRateDate" name="interestRateDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率启用日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否流水户:
						</label>
					</td>
					<td class="value">
					     	 <input id="isLiushui" name="isLiushui" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否流水户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							在用产品:
						</label>
					</td>
					<td class="value">
					     	 <input id="onlineProduct" name="onlineProduct" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">在用产品</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							大额支号付:
						</label>
					</td>
					<td class="value">
					     	 <input id="bigZhifu" name="bigZhifu" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">大额支号付</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否可以提前结息:
						</label>
					</td>
					<td class="value">
					     	 <input id="isPreEnd" name="isPreEnd" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否可以提前结息</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
					     	 <input id="contact" name="contact" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系方式:
						</label>
					</td>
					<td class="value">
					     	 <input id="contactType" name="contactType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系方式</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							行内联行行号:
						</label>
					</td>
					<td class="value">
					     	 <input id="lineHanghao" name="lineHanghao" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">行内联行行号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							存单金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="cundan" name="cundan" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">存单金额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							备注项:
						</label>
					</td>
					<td class="value">
					     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注项</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							到期日:
						</label>
					</td>
					<td class="value">
							   <input id="todate" name="todate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">到期日</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							托管行预留印鉴:
						</label>
					</td>
					<td class="value">
					     	 <input id="seal" name="seal" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">托管行预留印鉴</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="acountAbbreve" name="acountAbbreve" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户简称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							人名章:
						</label>
					</td>
					<td class="value">
					     	 <input id="zhang" name="zhang" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人名章</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							经办人:
						</label>
					</td>
					<td class="value">
					     	 <input id="jingban" name="jingban" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">经办人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							联系地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="contactAddr" name="contactAddr" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系地址</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							座机:
						</label>
					</td>
					<td class="value">
					     	 <input id="zuoji" name="zuoji" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">座机</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							手机:
						</label>
					</td>
					<td class="value">
					     	 <input id="mobilePhone" name="mobilePhone" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							复核状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="fuheStatus" name="fuheStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">复核状态</label>
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
  <script src = "webpage/com/jeecg/bankaccountemp/bankAccountemp.js"></script>		

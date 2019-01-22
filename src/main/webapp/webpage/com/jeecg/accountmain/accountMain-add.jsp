<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户信息总表</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="accountMainController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${accountMainPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">银行账号:</label>
			</td>
			<td class="value">
		     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行账号</label>
			</td>
			<td align="right">
				<label class="Validform_label">银行户名:</label>
			</td>
			<td class="value">
		     	 <input id="acountName" name="acountName" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行户名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">开户银行全称:</label>
			</td>
			<td class="value">
		     	 <input id="acountFullname" name="acountFullname" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户银行全称</label>
			</td>
			<td align="right">
				<label class="Validform_label">开户银行简称:</label>
			</td>
			<td class="value">
		     	 <input id="acountShortname" name="acountShortname" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户银行简称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户类型:</label>
			</td>
			<td class="value">
					  <t:dictSelect field="acountType" type="list" 		datatype="*"  typeGroupCode="accountype"  defaultVal="${accountMainPage.acountType}" hasLabel="false"  title="账户类型" ></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">开户日期:</label>
			</td>
			<td class="value">
					  <input id="kaihuDate" name="kaihuDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="ignore"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户状态:</label>
			</td>
			<td class="value">
		     	 <input id="acountStatus" name="acountStatus" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">账户利率:</label>
			</td>
			<td class="value">
		     	 <input id="acountInterestRate" name="acountInterestRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户利率</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">利率启用日期:</label>
			</td>
			<td class="value">
					  <input id="interestRateDate" name="interestRateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="ignore"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利率启用日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否流水户:</label>
			</td>
			<td class="value">
		     	 <input id="isLiushui" name="isLiushui" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否流水户</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">在用产品:</label>
			</td>
			<td class="value">
		     	 <input id="onlineProduct" name="onlineProduct" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">在用产品</label>
			</td>
			<td align="right">
				<label class="Validform_label">大额支付号:</label>
			</td>
			<td class="value">
		     	 <input id="bigZhifu" name="bigZhifu" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大额支付号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否可以提前结息:</label>
			</td>
			<td class="value">
		     	 <input id="isPreEnd" name="isPreEnd" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否可以提前结息</label>
			</td>
			<td align="right">
				<label class="Validform_label">联系人:</label>
			</td>
			<td class="value">
		     	 <input id="contact" name="contact" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">联系方式:</label>
			</td>
			<td class="value">
		     	 <input id="contactType" name="contactType" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系方式</label>
			</td>
			<td align="right">
				<label class="Validform_label">行内联行行号:</label>
			</td>
			<td class="value">
		     	 <input id="lineHanghao" name="lineHanghao" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">行内联行行号</label>
			</td>
		</tr>
	
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="accountMainController.do?raiseAccountList&id=${accountMainPage.id}" icon="icon-search" title="募集资金" id="raiseAccount"></t:tab>
				 <t:tab href="accountMainController.do?accountDetailList&id=${accountMainPage.id}" icon="icon-search" title="资金明细" id="accountDetail"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_raiseAccount_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="raiseAccountList[#index#].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  	<input name="raiseAccountList[#index#].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  	<input name="raiseAccountList[#index#].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left">
					  	<input name="raiseAccountList[#index#].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">曾用产品</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="raiseAccountList[#index#].jiexiStatus" type="list"    typeGroupCode="jiexi"  defaultVal="" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
				  </td>
			</tr>
		 </tbody>
	<tbody id="add_accountDetail_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="accountDetailList[#index#].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  	<input name="accountDetailList[#index#].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  	<input name="accountDetailList[#index#].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left">
					  	<input name="accountDetailList[#index#].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">利率</label>
				  </td>
				  <td align="left">
							<input name="accountDetailList[#index#].startDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;" ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开始日期</label>
				  </td>
				  <td align="left">
							<input name="accountDetailList[#index#].endDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;" ignore="ignore" />
					  <label class="Validform_label" style="display: none;">结束日期</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/accountmain/accountMain.js"></script>
	
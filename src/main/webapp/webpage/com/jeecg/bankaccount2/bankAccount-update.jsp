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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="bankAccountController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${bankAccountPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">银行账号:</label>
			</td>
			<td class="value">
		     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.accountNumber}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行账号</label>
			</td>
			<td align="right">
				<label class="Validform_label">银行户名:</label>
			</td>
			<td class="value">
		     	 <input id="acountName" name="acountName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行户名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户简称:</label>
			</td>
			<td class="value">
		     	 <input id="acountAbbreve" name="acountAbbreve" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountAbbreve}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户简称</label>
			</td>
			<td align="right">
				<label class="Validform_label">开户银行全称:</label>
			</td>
			<td class="value">
		     	 <input id="acountFullname" name="acountFullname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountFullname}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户银行全称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">开户银行简称:</label>
			</td>
			<td class="value">
		     	 <input id="acountShortname" name="acountShortname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountShortname}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户银行简称</label>
			</td>
			<td align="right">
				<label class="Validform_label">账户类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="acountType" type="list"   typeGroupCode="accountype"  defaultVal="${bankAccountPage.acountType}" hasLabel="false"  title="账户类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">开户日期:</label>
			</td>
			<td class="value">
					  <input id="kaihuDate" name="kaihuDate" type="text" style="width: 150px"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.kaihuDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开户日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">账户状态:</label>
			</td>
			<td class="value">
		     	 <input id="acountStatus" name="acountStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountStatus}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户利率:</label>
			</td>
			<td class="value">
		     	 <input id="acountInterestRate" name="acountInterestRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountInterestRate}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账户利率</label>
			</td>
			<td align="right">
				<label class="Validform_label">利率启用日期:</label>
			</td>
			<td class="value">
					  <input id="interestRateDate" name="interestRateDate" type="text" style="width: 150px"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.interestRateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利率启用日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否流水户:</label>
			</td>
			<td class="value">
		     	 <input id="isLiushui" name="isLiushui" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.isLiushui}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否流水户</label>
			</td>
			<td align="right">
				<label class="Validform_label">在用产品:</label>
			</td>
			<td class="value">
		     	 <input id="onlineProduct" name="onlineProduct" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.onlineProduct}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">在用产品</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">大额支号付:</label>
			</td>
			<td class="value">
		     	 <input id="bigZhifu" name="bigZhifu" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.bigZhifu}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大额支号付</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否可以提前结息:</label>
			</td>
			<td class="value">
		     	 <input id="isPreEnd" name="isPreEnd" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.isPreEnd}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否可以提前结息</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">经办人:</label>
			</td>
			<td class="value">
		     	 <input id="jingban" name="jingban" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.jingban}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">经办人</label>
			</td>
			<td align="right">
				<label class="Validform_label">联系人:</label>
			</td>
			<td class="value">
		     	 <input id="contact" name="contact" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contact}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">联系地址:</label>
			</td>
			<td class="value">
		     	 <input id="contactAddr" name="contactAddr" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contactAddr}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">联系方式:</label>
			</td>
			<td class="value">
		     	 <input id="contactType" name="contactType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contactType}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">行内联行行号:</label>
			</td>
			<td class="value">
		     	 <input id="lineHanghao" name="lineHanghao" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.lineHanghao}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">行内联行行号</label>
			</td>
			<td align="right">
				<label class="Validform_label">托管行预留印鉴:</label>
			</td>
			<td class="value">
		     	 <input id="seal" name="seal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.seal}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">托管行预留印鉴</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">人名章:</label>
			</td>
			<td class="value">
		     	 <input id="zhang" name="zhang" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.zhang}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人名章</label>
			</td>
			<td align="right">
				<label class="Validform_label">存单金额:</label>
			</td>
			<td class="value">
		     	 <input id="cundan" name="cundan" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${bankAccountPage.cundan}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">存单金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">到期日:</label>
			</td>
			<td class="value">
					  <input id="todate" name="todate" type="text" style="width: 150px"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.todate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">到期日</label>
			</td>
		</tr>
	
		<tr>
			<td align="right">
				<label class="Validform_label">备注项:</label>
			</td>
			<td class="value" colspan="3">
				 <textarea id="remarks" style="width:600px;" class="inputxt" rows="6" name="remarks"  ignore="ignore" >${bankAccountPage.remarks}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注项</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="bankAccountController.do?accountRateChangesList&dETAIL_NUMBER=${bankAccountPage.dETAIL_NUMBER}&dETAIL_NAME=${bankAccountPage.dETAIL_NAME}&dETAIL_FULLNAME=${bankAccountPage.dETAIL_FULLNAME}" icon="icon-search" title="代码生成版本2" id="accountRateChanges"></t:tab>
				 <t:tab href="bankAccountController.do?raiseAccountUseList&yINHANG_ACCOUNT=${bankAccountPage.yINHANG_ACCOUNT}&yINHANG_NAME=${bankAccountPage.yINHANG_NAME}&yINHANG_NAMEFULL=${bankAccountPage.yINHANG_NAMEFULL}" icon="icon-search" title="代码生成版本2" id="raiseAccountUse"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_accountRateChanges_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">利率</label>
				  </td>
				  <td align="left">
							<input name="accountRateChangesList[#index#].startDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开始日期</label>
				  </td>
				  <td align="left">
							<input name="accountRateChangesList[#index#].endDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">结束日期</label>
				  </td>
			</tr>
		 </tbody>
		<tbody id="add_raiseAccountUse_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">曾用产品</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="raiseAccountUseList[#index#].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/bankaccount2/bankAccount.js"></script>	

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="testOrderMainController.do?save">
			<input id="id" name="id" type="hidden" value="${testOrderMainPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">订单号:</label></td>
			<td class="value">
				<input nullmsg="请填写订单号" errormsg="订单号格式不对" class="inputxt" id="orderCode" name="orderCode" ignore="ignore"	value="${testOrderMainPage.orderCode}" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">订单日期:</label></td>
			<td class="value">
				<input nullmsg="请填写订单日期" errormsg="订单日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="orderDate" name="orderDate" ignore="ignore"	  value="<fmt:formatDate value='${testOrderMainPage.orderDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单金额:</label></td>
			<td class="value">
				<input nullmsg="请填写订单金额" errormsg="订单金额格式不对" class="inputxt" id="orderMoney" name="orderMoney" ignore="ignore"	value="${testOrderMainPage.orderMoney}" datatype="d" />
				<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">备注:</label></td>
			<td class="value">
				<input nullmsg="请填写备注" errormsg="备注格式不对" class="inputxt" id="content" name="content" ignore="ignore"	value="${testOrderMainPage.content}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单扫描件:</label></td>
			<td class="value">
				<input nullmsg="请填写订单扫描件" errormsg="订单扫描件格式不对" class="inputxt" id="ctype" name="ctype" ignore="ignore"	value="${testOrderMainPage.ctype}" />
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="testOrderMainController.do?testOrderCustomList&id=${testOrderMainPage.id}" icon="icon-search" title="客户明细" id="testOrderCustom"></t:tab>
				 <t:tab href="testOrderMainController.do?testOrderTicketList&id=${testOrderMainPage.id}" icon="icon-search" title="产品明细" id="testOrderTicket"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_testOrderCustom_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="testOrderCustomList[#index#].name" maxlength="100" type="text" style="width:120px;"></td>
				  <td align="left"><input name="testOrderCustomList[#index#].money" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="testOrderCustomList[#index#].sex" maxlength="4" type="text" style="width:120px;"></td>
				  <td align="left"><input name="testOrderCustomList[#index#].telphone" maxlength="32" type="text" style="width:120px;"></td>
				  <td align="left"><input name="testOrderCustomList[#index#].sfPic" maxlength="500" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_testOrderTicket_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="testOrderTicketList[#index#].ticketCode" maxlength="100" type="text" style="width:120px;"></td>
				  <td align="left"><input name="testOrderTicketList[#index#].tickectDate" maxlength="" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 </body>
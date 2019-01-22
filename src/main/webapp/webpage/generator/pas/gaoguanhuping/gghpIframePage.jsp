<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
   <div region="center" style="padding:0px;border:0px;overflow-x:hidden;">
   <div id="accDiv" class="easyui-accordion" style="padding-right:15px;overflow-x:hidden;box-sizing: border-box;">
		<div title="2018年度高级管理人员及董事总经理互评表" data-options="iconCls:'icon-ok'" style="overflow:auto;box-sizing: border-box;">
				<table>
					<tr><td>说明：</td></tr>
					<tr><td>一、本评分为匿名评分，请您本着公正客观的态度逐项评分，评分结果将作为被评价人2018年度绩效考核结果的重要部分。</td></tr>
					<tr><td>二、请于2018年12月15日前完成评分，逾期视为弃权，感谢您的支持与配合！</td></tr>
				</table>
		</div>
		
   </div>
    <iframe id="mainList" src="${webRoot}/gaoguanHupingController.do?list" frameborder="0" height="60%" width="100%"></iframe>
  	<table  style="width: 100%;height: 200px;text-align: center" border="1">
					<tr align="center">
						<td colspan="2">评分标准</td>
					</tr>
					<tr>
						<td width="50%">合计得分</td>
						<td  width="50%">含义</td>
					</tr>
					<tr>
						<td>95（含）以上</td>
						<td>（S）业绩卓越，总是超越标准</td>
					</tr>
					<tr>
						<td>90（含）-95</td>
						<td>（A）业绩优秀，经常超越标准</td>
					</tr>
					<tr>
						<td>80（含）-90</td>
						<td>（B）业绩良好，基本达到标准</td>
					</tr>
					<tr>
						<td>70（含）-80</td>
						<td>（C）业绩合格，偶尔达到标准</td>
					</tr>
					<tr>
						<td>低于70</td>
						<td>（D）业绩不符合要求</td>
					</tr>
	 </table>
  </div>
</div>
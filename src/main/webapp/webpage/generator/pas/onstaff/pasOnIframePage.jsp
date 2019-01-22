<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
   <div region="center" style="padding:0px;border:0px;overflow-x:hidden;">
   <div id="accDiv" class="easyui-accordion" style="padding-right:15px;overflow-x:hidden;box-sizing: border-box;">
		<div title="2018年度员工合作满意度评价表" data-options="iconCls:'icon-ok'" style="overflow:auto;box-sizing: border-box;">
				<table>
				<!-- 	<tr><td>2018年度员工合作满意度评价表</td></tr> -->
					<tr><td>说明：</td></tr>
					<tr><td>一、本评分为匿名评分，请您本着公正客观的态度逐项评分，评分结果将计入被评价人2018年度绩效考核得分。</td></tr>
					<tr><td>二、注意：所有合计得分相同视为无效评分；合计得分出现满分视为无效评分；漏打、错打无法提交。</td></tr>
					<tr><td>三、请于2018年12月15日前完成评分，逾期视为弃权，感谢您的支持与配合！</td></tr>
				</table>
		</div>
		<!-- 	<div title="title" data-options="iconCls:'icon-ok'" style="overflow:auto;">
			放置子表的框框
		</div> -->
   </div>
    <iframe id="mainList" src="${webRoot}/pasOnStaffController.do?list" frameborder="0" height="100%" width="100%"></iframe>
  <!-- 	<table  style="width: 100%;height: 200px;text-align: center" border="1">
					<tr align="center">
						<td colspan="2">评分标准</td>
					</tr>
					<tr>
						<td width="50%">合计得分</td>
						<td  width="50%">含义</td>
					</tr>
					<tr>
						<td>95（含）-100</td>
						<td>非常满意，总是超越标准</td>
					</tr>
					<tr>
						<td>90（含）-95</td>
						<td>比较满意，经常超越标准</td>
					</tr>
					<tr>
						<td>80（含）-90</td>
						<td>基本满意，基本达到标准</td>
					</tr>
					<tr>
						<td>70（含）-80</td>
						<td>不太满意，偶尔达到标准</td>
					</tr>
					<tr>
						<td>低于70</td>
						<td>不满意</td>
					</tr>
	 </table> -->
  </div>
</div>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>主表测试版</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="mainTestController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${mainTestPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">名字:</label>
			</td>
			<td class="value">
		     	 <input id="mainName" name="mainName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${mainTestPage.mainName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名字</label>
			</td>
			<td align="right">
				<label class="Validform_label">年龄:</label>
			</td>
			<td class="value">
		     	 <input id="mainAge" name="mainAge" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${mainTestPage.mainAge}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">年龄</label>
			</td>
		</tr>
	
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="mainTestController.do?fubiaoTestList&fU_NAME=${mainTestPage.fU_NAME}&fU_AGE=${mainTestPage.fU_AGE}" icon="icon-search" title="主表测试" id="fubiaoTest"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_fubiaoTest_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="fubiaoTestList[#index#].fuName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">附表名字</label>
				  </td>
				  <td align="left">
					  		<input name="fubiaoTestList[#index#].fuAge" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">附表年龄</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/maintest/mainTest.js"></script>	

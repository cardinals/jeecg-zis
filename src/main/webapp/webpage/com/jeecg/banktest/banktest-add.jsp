<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>测试用例1</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
	 
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="banktestController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${banktestPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" 		 ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect id="sexid" extendJson="{onchange:'sffxyh(this.options[this.options.selectedIndex].value)'}" field="sex" type="list" datatype="*" typeGroupCode="sex"  defaultVal="${banktestPage.sex}" hasLabel="false"  title="性别" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年龄:
						</label>
					</td>
					<td class="value">
					     	 <input id="age" name="age" type="text" style="width: 150px" class="inputxt"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年龄</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/banktest/banktest.js"></script>
   <script type="text/javascript">
  //编写自定义JS代码
	 /*  $("#sexid").click(function(){
		  alert('点击我。。。');
	}); */
  function sffxyh(optionValue){
	  //alert(optionValue);
	  //业务逻辑...
	  if(optionValue==0){//姓名必填
		  //alert("男的...");
		  $("#name").attr("datatype","*");
	  }else{
		  //alert("女的...");//年龄必填
		  $("#age").attr("datatype","*");
	  }
	  
  }
  </script>		

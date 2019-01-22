<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>按钮表格</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="buttonTestController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${buttonTestPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${buttonTestPage.name}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								年纪:
							</label>
						</td>
						<td class="value">
						    <input id="age" name="age" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${buttonTestPage.age}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年纪</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/buttontest/buttonTest.js"></script>		

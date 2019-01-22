<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>基金经理表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pfBaseManagerController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pfBaseManagerPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBaseManagerPage.name}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								中文姓名:
							</label>
						</td>
						<td class="value">
						    <input id="nameZh" name="nameZh" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBaseManagerPage.nameZh}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外键:
							</label>
						</td>
						<td class="value">
						    <input id="foreignKey" name="foreignKey" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBaseManagerPage.foreignKey}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外键</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/pfbamana/pfBaseManager.js"></script>		

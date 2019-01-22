<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>历任基金经理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pfHisManagerController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pfHisManagerPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="nameZh" name="nameZh" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="startTime" name="startTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="emdTime" name="emdTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始:
						</label>
					</td>
					<td class="value">
					     	 <input id="startString" name="startString" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束:
						</label>
					</td>
					<td class="value">
					     	 <input id="endString" name="endString" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							外键:
						</label>
					</td>
					<td class="value">
					     	 <input id="foreignKey" name="foreignKey" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外键</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/pfhismanager/pfHisManager.js"></script>		

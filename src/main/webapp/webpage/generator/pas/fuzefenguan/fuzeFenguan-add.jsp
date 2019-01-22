<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>部门及部门的角色列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fuzeFenguanController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${fuzeFenguanPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="departName" name="departName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="departCode" name="departCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门代码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门主管:
						</label>
					</td>
					<td class="value">
					     	 <input id="departZhuguan" name="departZhuguan" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门主管</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门分管:
						</label>
					</td>
					<td class="value">
					     	 <input id="departFenguan" name="departFenguan" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门分管</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门分管领导职位:
						</label>
					</td>
					<td class="value">
					     	 <input id="fenguanPosition" name="fenguanPosition" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门分管领导职位</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门协管:
						</label>
					</td>
					<td class="value">
					     	 <input id="departXieguan" name="departXieguan" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门协管</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/fuzefenguan/fuzeFenguan.js"></script>		

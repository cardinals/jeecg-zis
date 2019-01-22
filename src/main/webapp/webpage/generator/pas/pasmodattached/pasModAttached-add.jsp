<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>绩效考评特殊人员附加表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasModAttachedController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pasModAttachedPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="username" name="username" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							真实姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="realname" name="realname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">真实姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							角色代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="rolecode" name="rolecode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">角色代码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							角色名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="rolename" name="rolename" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">角色名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="orgCode" name="orgCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门代码</label>
						</td>
				</tr>
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
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/pasmodattached/pasModAttached.js"></script>		

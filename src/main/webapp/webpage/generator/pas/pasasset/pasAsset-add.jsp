<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>资产名单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasAssetController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pasAssetPage.id }"/>
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
							部门:
						</label>
					</td>
					<td class="value">
					     	 <input id="depart" name="depart" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
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
							考核类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="kaoheType" name="kaoheType" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">考核类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							考核类型代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="kaoheCode" name="kaoheCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">考核类型代码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/pasasset/pasAsset.js"></script>		

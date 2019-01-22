<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>绩效考核系统投研交流评价表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasIarSIndexController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pasIarSIndexPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							被评分人:
						</label>
					</td>
					<td class="value">
					     	 <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评分人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							专业水平:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="professionalSkill" type="list"  datatype="n"  typeGroupCode="pas_s50"  defaultVal="${pasIarSIndexPage.professionalSkill}" hasLabel="false"  title="专业水平" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">专业水平</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合作态度:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="coopAtti" type="list"  datatype="n"  typeGroupCode="pas_s50"  defaultVal="${pasIarSIndexPage.coopAtti}" hasLabel="false"  title="合作态度" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作态度</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总分:
						</label>
					</td>
					<td class="value">
					     	 <input id="totalScore" name="totalScore" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总分</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评分说明:
						</label>
					</td>
					<td class="value">
					     	 <input id="comment" name="comment" type="text" style="width: 150px" class="inputxt"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评分说明</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/iarsindex/pasIarSIndex.js"></script>		

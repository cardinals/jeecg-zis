<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>绩效考核是否打分</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasResultController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pasResultPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							评价人:
						</label>
					</td>
					<td class="value">
					     	 <input id="appraiser" name="appraiser" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评价人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评价模块:
						</label>
					</td>
					<td class="value">
					     	 <input id="module" name="module" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评价模块</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/pasresult/pasResult.js"></script>		

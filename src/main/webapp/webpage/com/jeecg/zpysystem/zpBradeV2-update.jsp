<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>面试评分表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zpBradeV2Controller.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${zpBradeV2Page.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="resumeName" name="resumeName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${zpBradeV2Page.resumeName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第一轮面试成绩:
							</label>
						</td>
						<td class="value">
						    <input id="grade1" name="grade1" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${zpBradeV2Page.grade1}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第一轮面试成绩</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第二轮面试成绩:
							</label>
						</td>
						<td class="value">
						    <input id="grade2" name="grade2" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${zpBradeV2Page.grade2}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第二轮面试成绩</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								录用状态:
							</label>
						</td>
						<td class="value">
						    <input id="jpStatus" name="jpStatus" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${zpBradeV2Page.jpStatus}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">录用状态</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/zpysystem/zpBradeV2.js"></script>		

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>2018年度市场销售支持满意度评价表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasSmaSupIndexController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasSmaSupIndexPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人所属部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalDept" name="goalDept" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasSmaSupIndexPage.goalDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalPerson" name="goalPerson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasSmaSupIndexPage.goalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								市场销售支持满意度:
							</label>
						</td>
						<td class="value">
						     	 <input id="smaSupIdx" name="smaSupIdx" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${pasSmaSupIndexPage.smaSupIdx}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">市场销售支持满意度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评分说明:
							</label>
						</td>
						<td class="value">
						     	 <input id="comment" name="comment" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pasSmaSupIndexPage.comment}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评分说明</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/sma/pasSmaSupIndex.js"></script>		

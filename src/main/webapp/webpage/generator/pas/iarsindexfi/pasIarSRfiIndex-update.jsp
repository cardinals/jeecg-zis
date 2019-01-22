<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>2018年度投研交流满意度评价表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pasIarSRfiIndexController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${pasIarSRfiIndexPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价人:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalUsername" name="goalUsername" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasIarSRfiIndexPage.goalUsername}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								被评价部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="goalDept" name="goalDept" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasIarSRfiIndexPage.goalDept}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">被评价部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								投研交流满意度:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="iarCmuIdx" type="list"  datatype="n"  typeGroupCode="pas_100"   defaultVal="${pasIarSRfiIndexPage.iarCmuIdx}" hasLabel="false"  title="投研交流满意度" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投研交流满意度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评分说明:
							</label>
						</td>
						<td class="value">
						     	 <input id="comment" name="comment" type="text" style="width: 150px" class="inputxt"  ignore="checked"  value='${pasIarSRfiIndexPage.comment}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评分说明</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/generator/pas/iarsindexfi/pasIarSRfiIndex.js"></script>		

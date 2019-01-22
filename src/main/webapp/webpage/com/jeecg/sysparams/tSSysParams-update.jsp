<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统参数</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSSysParamsController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${tSSysParamsPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								模块名称:
							</label>
						</td>
						<td class="value">
						    <input id="cClass" name="cClass" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cClass}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模块名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								参数名称:
							</label>
						</td>
						<td class="value">
						    <input id="citem" name="citem" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.citem}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								参数值:
							</label>
						</td>
						<td class="value">
						    <input id="cValue" name="cValue" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cValue}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数值</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否修改:
							</label>
						</td>
						<td class="value">
						    <input id="cModify" name="cModify" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cModify}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否修改</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								参数描述:
							</label>
						</td>
						<td class="value">
						    <input id="cDescribe" name="cDescribe" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cDescribe}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								标志:
							</label>
						</td>
						<td class="value">
						    <input id="cCryptflag" name="cCryptflag" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cCryptflag}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								标志位:
							</label>
						</td>
						<td class="value">
						    <input id="cType" name="cType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								取值范围:
							</label>
						</td>
						<td class="value">
						    <input id="cValuebound" name="cValuebound" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cValuebound}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">取值范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类别:
							</label>
						</td>
						<td class="value">
						    <input id="cShowclass" name="cShowclass" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cShowclass}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								子类:
							</label>
						</td>
						<td class="value">
						    <input id="cShowsubclass" name="cShowsubclass" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSSysParamsPage.cShowsubclass}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">子类</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/sysparams/tSSysParams.js"></script>		

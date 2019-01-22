<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户意见</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bankCommentController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${bankCommentPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								意见修改时间:
							</label>
						</td>
						<td class="value">
									  <input id="time" name="time" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${bankCommentPage.time}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">意见修改时间</label>
						</td>
					</tr>
				
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核意见:
							</label>
						</td>
						<td class="value" >
						  	 	<textarea id="comment" style="width:600px;" class="inputxt" rows="6" name="comment"  ignore="ignore" >${bankCommentPage.comment}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/bankcomment/bankComment.js"></script>		

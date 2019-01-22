<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
	var api = frameElement.api, W = api.opener;
	api.button({
	    id:'valueOk',
		name:'确定',
		callback:ok
	},{
		id:'cancelOk',
		name:'取消'
	});
	window.onload = function()
	{
	    document.getElementById('content').value = $(W.cottarget).val();;
	};
	function ok()
	{	
	  W.callBack(document.getElementById('content').value);
	};
</script>
</head>
 <body>
  			<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"  >
				<table style="width:320px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								评语:
							</label>
						</td>
						<td class="value">
							<textarea id="content" maxlength="1000" rows="6" cols="36"></textarea>
							<span class="Validform_checktip">字数限制：1000字</span>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
 </html>

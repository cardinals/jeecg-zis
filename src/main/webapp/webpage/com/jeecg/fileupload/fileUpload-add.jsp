<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>文件上传测试</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fileUploadController.do?doAdd" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${fileUploadPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankName" name="bankName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行户名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件1:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
									
									console.log(111);
									console.log($("input[name='id']").val());
									console.log(222);
									
										var serverMsg="";
										$(function(){
											$('#file').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_file',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',	
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													
													console.log(111);
													console.log($("input[name='id']").val());
													console.log(222);
													
													$('#file').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'file_upload',
														'cgFormField':'FILE'
													});
												} ,
												onQueueComplete : function(queueData) {
													 var win = frameElement.api.opener;
													 win.reloadTable();
													 win.tip(serverMsg);
													 frameElement.api.close();
												},
												onUploadSuccess : function(file, data, response) {
													var d=$.parseJSON(data);
													if(d.success){
														var win = frameElement.api.opener;
														serverMsg = d.msg;
													}
												},
												onFallback: function() {
								                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
								                },
								                onSelectError: function(file, errorCode, errorMsg) {
								                    switch (errorCode) {
								                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
								                        break;
								                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
								                        break;
								                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
								                        break;
								                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
								                        break;
								                    }
								                },
								                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
											});
										});
									</script>
									<span id="file_uploadspan"><input type="file" name="file" id="file" /></span> 
								</div> 
								<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件1</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件2:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#file2').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_file2',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',	
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													$('#file2').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'file_upload',
														'cgFormField':'FILE2'
													});
												} ,
												onQueueComplete : function(queueData) {
													 var win = frameElement.api.opener;
													 win.reloadTable();
													 win.tip(serverMsg);
													 frameElement.api.close();
												},
												onUploadSuccess : function(file, data, response) {
													var d=$.parseJSON(data);
													if(d.success){
														var win = frameElement.api.opener;
														serverMsg = d.msg;
													}
												},
												onFallback: function() {
								                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
								                },
								                onSelectError: function(file, errorCode, errorMsg) {
								                    switch (errorCode) {
								                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
								                        break;
								                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
								                        break;
								                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
								                        break;
								                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
								                        break;
								                    }
								                },
								                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
											});
										});
									</script>
									<span id="file_uploadspan"><input type="file" name="file2" id="file2" /></span> 
								</div> 
								<div class="form" id="filediv_file2"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件2</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件3:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#file3').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_file3',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',	
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													$('#file3').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'file_upload',
														'cgFormField':'FILE3'
													});
												} ,
												onQueueComplete : function(queueData) {
													 var win = frameElement.api.opener;
													 win.reloadTable();
													 win.tip(serverMsg);
													 frameElement.api.close();
												},
												onUploadSuccess : function(file, data, response) {
													var d=$.parseJSON(data);
													if(d.success){
														var win = frameElement.api.opener;
														serverMsg = d.msg;
													}
												},
												onFallback: function() {
								                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
								                },
								                onSelectError: function(file, errorCode, errorMsg) {
								                    switch (errorCode) {
								                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
								                        break;
								                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
								                        break;
								                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
								                        break;
								                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
								                        break;
								                    }
								                },
								                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
											});
										});
									</script>
									<span id="file_uploadspan"><input type="file" name="file3" id="file3" /></span> 
								</div> 
								<div class="form" id="filediv_file3"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件3</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/fileupload/fileUpload.js"></script>		
	  	<script type="text/javascript">
	  		function jeecgFormFileCallBack(data){
	  			if (data.success == true) {
					uploadFile(data);
				} else {
					if (data.responseText == '' || data.responseText == undefined) {
						$.messager.alert('错误', data.msg);
						$.Hidemsg();
					} else {
						try {
							var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
							$.messager.alert('错误', emsg);
							$.Hidemsg();
						} catch(ex) {
							$.messager.alert('错误', data.responseText + '');
						}
					}
					return false;
				}
				if (!neibuClickFlag) {
					var win = frameElement.api.opener;
					win.reloadTable();
				}
	  		}
	  		function upload() {
					$('#file').uploadify('upload', '*');	
					$('#file2').uploadify('upload', '*');	
					$('#file3').uploadify('upload', '*');	
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
					$('#file').uploadify('cancel', '*');
					$('#file2').uploadify('cancel', '*');
					$('#file3').uploadify('cancel', '*');
			}
			function uploadFile(data){
				if(!$("input[name='id']").val()){
					if(data.obj!=null && data.obj!='undefined'){
						$("input[name='id']").val(data.obj.id);
					}
				}
				if($(".uploadify-queue-item").length>0){
					upload();
				}else{
					if (neibuClickFlag){
						alert(data.msg);
						neibuClickFlag = false;
					}else {
						var win = frameElement.api.opener;
						win.reloadTable();
						win.tip(data.msg);
						frameElement.api.close();
					}
				}
			}
	  	</script>

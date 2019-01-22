<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>RMS_合同管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="contractController.do?doAdd" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${contractPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							综合编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="contractNo" name="contractNo" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">综合编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合作方:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="vendor" type="list" datatype="*" dictTable="rms_vendor_jeecg" dictField="id" dictText="vendor_name"  defaultVal="${contractPage.vendor}" hasLabel="false"  title="合作方" ></t:dictSelect>     
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合作方</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预算年份:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="budgetYear" type="list" 		datatype="*" typeGroupCode="budgetyear"  defaultVal="${contractPage.budgetYear}" hasLabel="false"  title="预算年份" ></t:dictSelect>     
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预算年份</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总额:
						</label>
					</td>
					<td class="value">
					     	 <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总额</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签订人:
						</label>
					</td>
					<td class="value">
						  <t:dictSelect field="contractedBy" type="list" 		datatype="*" typeGroupCode="user"  defaultVal="${contractPage.contractedBy}" hasLabel="false"  title="签订人" ></t:dictSelect>     
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签订人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							签订日期:
						</label>
					</td>
					<td class="value">
							   <input id="contractDate" name="contractDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">签订日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同到期日:
						</label>
					</td>
					<td class="value">
							   <input id="expireDate" name="expireDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同到期日</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同文件:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#contractFile').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_contractFile',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',	
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													$('#contractFile').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'rms_contract_jeecg',
														'cgFormField':'CONTRACT_FILE'
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
									<span id="file_uploadspan"><input type="file" name="contractFile" id="contractFile" /></span> 
								</div> 
								<div class="form" id="filediv_contractFile"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同文件</label>
						</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同描述:
						</label>
					</td>
					<td class="value" >
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="description" name="description" 		datatype="*" ignore="checked" ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同描述</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" >
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remark1" name="remark1"  ignore="ignore" ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/contract/contract.js"></script>		
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
					$('#contractFile').uploadify('upload', '*');	
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
					$('#contractFile').uploadify('cancel', '*');
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

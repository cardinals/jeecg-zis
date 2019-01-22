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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="contractController.do?doUpdate" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${contractPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								综合编号:
							</label>
						</td>
						<td class="value">
						    <input id="contractNo" name="contractNo" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${contractPage.contractNo}'/>
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
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${contractPage.name}'/>
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
									<t:dictSelect field="vendor" type="list" 		datatype="*" dictTable="rms_vendor_jeecg" dictField="ID" dictText="vendor_name"   defaultVal="${contractPage.vendor}" hasLabel="false"  title="合作方" ></t:dictSelect>     
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
									<t:dictSelect field="budgetYear" type="list" 		datatype="*" typeGroupCode="budgetyear"   defaultVal="${contractPage.budgetYear}" hasLabel="false"  title="预算年份" ></t:dictSelect>     
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
						    <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt" 		datatype="*" ignore="checked"  value='${contractPage.totalAmount}'/>
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
									<t:dictSelect field="contractedBy" type="list" 		datatype="*" typeGroupCode="user"   defaultVal="${contractPage.contractedBy}" hasLabel="false"  title="签订人" ></t:dictSelect>     
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
									  <input id="contractDate" name="contractDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 		datatype="*" ignore="checked" value='<fmt:formatDate value='${contractPage.contractDate}' type="date" pattern="yyyy-MM-dd"/>'/>
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
									  <input id="expireDate" name="expireDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${contractPage.expireDate}' type="date" pattern="yyyy-MM-dd"/>'/>
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
									<table id="contract_file_fileTable"></table>
										<table></table>
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
						  	 	<textarea id="description" style="width:600px;" class="inputxt" rows="6" name="description" 		datatype="*" ignore="checked" >${contractPage.description}</textarea>
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
						  	 	<textarea id="remark1" style="width:600px;" class="inputxt" rows="6" name="remark1"  ignore="ignore" >${contractPage.remark1}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/rms/contract/contract.js"></script>		
	  	<script type="text/javascript">
		  	//加载 已存在的 文件
		  	$(function(){
		  		var cgFormId=$("input[name='id']").val();
		  		$.ajax({
		  		   type: "post",
		  		   url: "contractController.do?getFiles&id=" +  cgFormId,
		  		   success: function(data){
		  			 var arrayFileObj = jQuery.parseJSON(data).obj;
		  			 
		  			$.each(arrayFileObj,function(n,file){
		  				var fieldName = file.field.toLowerCase();
		  				var table = $("#"+fieldName+"_fileTable");
		  				var tr = $("<tr style=\"height:34px;\"></tr>");
		  				var title = file.title;
		  				if(title.length > 15){
		  					title = title.substring(0,12) + "...";
		  				}
		  				var td_title = $("<td>" + title + "</td>");
		  		  		var td_download = $("<td><a style=\"margin-left:10px;\" href=\"commonController.do?viewFile&fileid=" + file.fileKey + "&subclassname=org.jeecgframework.web.cgform.entity.upload.CgUploadEntity\" title=\"下载\">下载</a></td>")
		  		  		var td_view = $("<td><a style=\"margin-left:10px;\" href=\"javascript:void(0);\" onclick=\"openwindow('预览','commonController.do?openViewFile&fileid=" + file.fileKey + "&subclassname=org.jeecgframework.web.cgform.entity.upload.CgUploadEntity','fList',700,500)\">预览</a></td>");
		  		  		var td_del = $("<td><a style=\"margin-left:10px;\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"del('cgUploadController.do?delFile&id=" + file.fileKey + "',this)\">删除</a></td>");
		  		  		tr.appendTo(table);
		  		  		td_title.appendTo(tr);
		  		  		td_download.appendTo(tr);
		  		  		td_view.appendTo(tr);
		  		  		td_del.appendTo(tr);
		  			 });
		  		   }
		  		});
		  	});
		  	
		  	/**
		 	 * 删除图片数据资源
		 	 */
		  	function del(url,obj){
		  		var content = "请问是否要删除该资源";
		  		var navigatorName = "Microsoft Internet Explorer"; 
		  		if( navigator.appName == navigatorName ){ 
		  			$.dialog.confirm(content, function(){
		  				submit(url,obj);
		  			}, function(){
		  			});
		  		}else{
		  			layer.open({
						title:"提示",
						content:content,
						icon:7,
						yes:function(index){
							submit(url,obj);
						},
						btn:['确定','取消'],
						btn2:function(index){
							layer.close(index);
						}
					});
		  		}
		  	}
		  	
		  	function submit(url,obj){
		  		$.ajax({
		  			async : false,
		  			cache : false,
		  			type : 'POST',
		  			url : url,// 请求的action路径
		  			error : function() {// 请求失败处理函数
		  			},
		  			success : function(data) {
		  				var d = $.parseJSON(data);
		  				if (d.success) {
		  					var msg = d.msg;
		  					tip(msg);
		  					obj.parentNode.parentNode.parentNode.deleteRow(obj.parentNode.parentNode);
		  				} else {
		  					tip(d.msg);
		  				}
		  			}
		  		});
		  	}
		  	
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

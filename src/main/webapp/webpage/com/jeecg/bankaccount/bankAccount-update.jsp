<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>银行账户信息总表</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
  <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
  	<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
	<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  
 
  <script type="text/javascript">

  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
	//根据用户状态设置必填项开始
	var acountStatus = $("#acountStatus").val();
	if(acountStatus==3){
		 $("#acountName").removeAttr("datatype");
		  $("#accountNumber").removeAttr("datatype");
		  $("#acountFullname").removeAttr("datatype");
		  $("[name='acountShortname']").removeAttr("datatype");
		  $("#kaihuDate").removeAttr("datatype");
		  $("[name='acountType']").removeAttr("datatype");
	}
	var acountStatus = $("#acountStatus").val();
	if ($("#acountStatus").val()!=2) { 


			 $("#acountStatus option[value='2']").remove();

	}
	
	//根据用户状态设置必填项结束
	//颜色-start
	  var id = $("input[name='id']").val();
		$("#acountShortname").find("option").remove(); 
		  
		  var  customerName="bankCodeController.do?getBankEntity";//获取银行实体
		  
			$.ajax({
			  async : false,
			  cache : false,
			type : 'POST',
			url : customerName,// 请求的action路径
			
			error : function() {
			  
					//获取机构名称的请求成功
			},
			success : function(data) {
			  var d = $.parseJSON(data);
			  if (d.success) {//获取机构名称数据成功
				  $("#acountShortname").append('<option value="-1"> -- 请选择 -- </option>');
					
					$.each(d.attributes.bank, function(key, val) {
					
						$("#acountShortname").append('<option value="' + val.bankname + '">' + val.bankname + '</option>');
						
					});
					   
					
			 } else {
				 
					//showErrorMsg(d.msg);//获取机构名称数据失败
					return false;

			  }
			}
		  });
//===productStart
			$("#onlineProduct").find("option").remove(); 
			  
			  var  customerName="productCodeController.do?getProductEntity";//获取银行实体
			  
				$.ajax({
				  async : false,
				  cache : false,
				type : 'POST',
				url : customerName,// 请求的action路径
				
				error : function() {
				  
						//获取机构名称的请求成功
				},
				success : function(data) {
				  var d = $.parseJSON(data);
				  if (d.success) {//获取机构名称数据成功
					  $("#onlineProduct").append('<option value="-1"> -- 请选择 -- </option>');
						
						$.each(d.attributes.product, function(key, val) {
						
							$("#onlineProduct").append('<option value="' + val.productname + '">' + val.productname + '</option>');
							
						});
						   
						
				 } else {
					 
						//showErrorMsg(d.msg);//获取机构名称数据失败
						return false;

				  }
				}
			  });		
			
//===productEnd
				//编辑界面
				if(id){
					
					  var  bankNumberrName="bankAccountController.do?getBankNumberType";
					   var formData = new Object();
					formData['id'] = id ? id : "";
						$.ajax({
						  async : false,
						  cache : false,
						type : 'POST',
						url : bankNumberrName,
						data : formData,
						error : function() {
						  
								
						},
						success : function(data) {
						  var d = $.parseJSON(data);
						  if (d.success) {
							  sffxyh(d.attributes.bankNumberType);
							  addColor(d.attributes.acountStatus);
							} else {
							 
								//showErrorMsg(d.msg);
								return false;

						  }
						}
					  });
					 
				}


				function addColor(optionValue){
					if(optionValue==-1){
						$("#acountStatSpan").css("background-color","White"); 
					}
					if(optionValue==0){
						$("#acountStatSpan").css("background-color","gray"); 
						}
					if(optionValue==1){
						 $("#acountStatSpan").css("background-color","green");
					}
					if(optionValue==2){
						 $("#acountStatSpan").css("background-color","red");
					}
				}


  	//颜色-end
  });
  

  
 </script>
  
 </head>
 <body style="overflow-x: hidden;">
  
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="bankAccountController.do?doUpdate" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${bankAccountPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">银行账号:</label>
			</td>
			<td class="value">
		     	 <input id="accountNumber" name="accountNumber"  onkeypress="return gotoInt(event)"  type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.accountNumber}'/>
				<span class="Validform_checktip"></span>
				<label id="accountNumber2" class="Validform_label" style="display: none;">银行账号</label>
			</td>
			<td align="right">
				<label class="Validform_label">银行户名:</label>
			</td>
			<td class="value">
		     	 <input id="acountName" name="acountName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountName}'/>
				<span class="Validform_checktip"></span>
				<label id="acountName2"  class="Validform_label" style="display: none;">银行户名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户简称:</label>
			</td>
			<td class="value">
		     	 <input id="acountAbbreve" name="acountAbbreve" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountAbbreve}'/>
				<span class="Validform_checktip"></span>
				<label id="acountAbbreve2" class="Validform_label" style="display: none;">账户简称</label>
			</td>
			<td align="right">
				<label class="Validform_label">开户银行全称:</label>
			</td>
			<td class="value">
		     	 <input id="acountFullname" name="acountFullname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountFullname}'/>
				<span class="Validform_checktip"></span>
				<label id="acountFullname2" class="Validform_label" style="display: none;">开户银行全称</label>
			</td>
		</tr>
		
		<tr>
			<td align="right">
				<label class="Validform_label">开户银行简称:</label>
			</td>
			<fieldset>
			<td class="value">
		     	<%--  <input id="acountShortname" name="acountShortname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountShortname}'/>
				 --%>
				 <%-- <t:dictSelect  field="acountShortname" dictTable="bank_code" dictCondition="where 1=1" dictField="bankcode" dictText="bankname"  defaultVal="${bankAccountPage.acountShortname}"  > </t:dictSelect>  
				  --%>
				   <t:autocomplete entityName="BankCodeEntity" searchField="bankname" name="acountShortname"  defValue="${bankAccountPage.acountShortname}"></t:autocomplete> 
				
				  <span class="Validform_checktip"></span>
				<label id="acountShortname2"  class="Validform_label" style="display: none;">开户银行简称</label>
			</td>
			</fieldset>
			<td align="right">
				<label class="Validform_label">账户类型:</label>
			</td>
			<td class="value">
				<%-- 	<t:dictSelect field="acountType" type="list"   typeGroupCode="accountype"  defaultVal="${bankAccountPage.acountType}" hasLabel="false"  title="账户类型"></t:dictSelect>     
				 --%>
				 <t:dictSelect field="acountType" type="list"  extendJson="{onchange:'sffxyh(this.options[this.options.selectedIndex].value)'}"  datatype="*" typeGroupCode="accountype"  defaultVal="${bankAccountPage.acountType}" hasLabel="false"  title="账户类型"></t:dictSelect>     
				 <span class="Validform_checktip"></span>
				<label id="acountType2" class="Validform_label" style="display: none;">账户类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">开户日期:</label>
			</td>
			<td class="value">
				<%-- 	  <input id="kaihuDate" name="kaihuDate" type="text" style="width: 150px"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.kaihuDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				 --%>
				 	  <input id="kaihuDate" name="kaihuDate" type="text" style="width: 150px"   ignore="ignore" class="Wdate" onClick="WdatePicker()"  value='<fmt:formatDate value='${bankAccountPage.kaihuDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				
				 <span class="Validform_checktip"></span>
				<label id="kaihuDate2" class="Validform_label" style="display: none;">开户日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">账户状态:</label>
			</td>
			<td class="value">
		     	<%--  <input id="acountStatus" name="acountStatus" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountStatus}'/>
				 --%>
				  <select id="acountStatus" name="acountStatus" ignore="ignore" >
				      <option ${bankAccountPage.acountStatus eq ''?'selected':''} value="">--请选择--</option>
					  <option ${bankAccountPage.acountStatus eq '-1'?'selected':''} value="-1">其他</option>
					  <option ${bankAccountPage.acountStatus eq '0'?'selected':''}  value="0">已注销</option>
					  <option ${bankAccountPage.acountStatus eq '1'?'selected':''}  value="1">正常</option>
					  <option ${bankAccountPage.acountStatus eq '2'?'selected':''}  value="2">募集在用</option>
					  <option ${bankAccountPage.acountStatus eq '3'?'selected':''}  value="3">开户中</option>
					  <option ${bankAccountPage.acountStatus eq '4'?'selected':''}  value="4">销户中</option>
				 </select>
				<!--  <span class="Validform_checktip"></span> -->
				<div id="acountStatSpan" style="width: 10px;height: 22px;
  														  float: right;margin-left: 0%;margin-right: 58%;margin-top: 1%;"></div>
				
				<label id="acountStatus2" class="Validform_label" style="display: none;">账户状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">账户利率:</label>
			</td>
			<td class="value">
		     	<%--  <input id="acountInterestRate" name="acountInterestRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountInterestRate}'/>
				<span class="Validform_checktip"></span> --%>
				 <input id="acountInterestRate" name="acountInterestRate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.acountInterestRate}'/><span style="margin-left:0px;" class="Validform_checktip">%</span>
				
				<label id="acountInterestRate2" class="Validform_label" style="display: none;">账户利率</label>
			</td>
			<td align="right">
				<label class="Validform_label">利率启用日期:</label>
			</td>
			<td class="value">
				<%-- 	  <input id="interestRateDate" name="interestRateDate" type="text" style="width: 150px"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.interestRateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				 --%> <input id="interestRateDate" name="interestRateDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"   ignore="ignore"  value='<fmt:formatDate value='${bankAccountPage.interestRateDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				
				<span class="Validform_checktip"></span>
				<label id="interestRateDate2"  class="Validform_label" style="display: none;">利率启用日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否流水户:</label>
			</td>
			<td class="value">
		     	<%--  <input id="isLiushui" name="isLiushui" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.isLiushui}'/>
				 --%>
				  <t:dictSelect field="isLiushui" type="list"   typeGroupCode="01NY"  defaultVal="${bankAccountPage.isLiushui}" hasLabel="false"  title="是否流水户息"></t:dictSelect>     
				 
				 <span class="Validform_checktip"></span>
				<label id="isLiushui2" class="Validform_label" style="display: none;">是否流水户</label>
			</td>
			<td align="right">
				<label class="Validform_label">在用产品:</label>
			</td>
			<td class="value">
		     	<%--  <input id="onlineProduct" name="onlineProduct" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.onlineProduct}'/>
				 --%>
				 <t:dictSelect  field="onlineProduct" dictTable="product_code" dictCondition="where 1=1" dictField="productcode" dictText="productcode"  defaultVal="${bankAccountPage.onlineProduct}"  > </t:dictSelect>  
				
				 <span class="Validform_checktip"></span>
				<label id="onlineProduct2" class="Validform_label" style="display: none;">在用产品</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">大额支号付:</label>
			</td>
			<td class="value">
		     	 <input id="bigZhifu" name="bigZhifu" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.bigZhifu}'/>
				<span class="Validform_checktip"></span>
				<label id="bigZhifu2" class="Validform_label" style="display: none;">大额支号付</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否可以提前结息:</label>
			</td>
			<td class="value">
		     	<%--  <input id="isPreEnd" name="isPreEnd" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.isPreEnd}'/>
				 --%>
				 <t:dictSelect field="isPreEnd" type="list"   typeGroupCode="01NY"  defaultVal="${bankAccountPage.isPreEnd}" hasLabel="false"  title="是否可以提前结息"></t:dictSelect>     
			
				 <span class="Validform_checktip"></span>
				<label id="isPreEnd2"  class="Validform_label" style="display: none;">是否可以提前结息</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">经办人:</label>
			</td>
			<td class="value">
		     	 <input id="jingban" name="jingban" type="text" style="width: 150px" class="inputxt"  ignore="ignore" readonly="readonly" value='${realName}'/>
				<span class="Validform_checktip"></span>
				<label  id="jingban2" class="Validform_label" style="display: none;">经办人</label>
			</td>
			<td align="right">
				<label class="Validform_label">联系人:</label>
			</td>
			<td class="value">
		     	 <input id="contact" name="contact" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contact}'/>
				<span class="Validform_checktip"></span>
				<label id="contact2" class="Validform_label" style="display: none;">联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">联系地址:</label>
			</td>
			<td class="value">
		     	 <input id="contactAddr" name="contactAddr" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contactAddr}'/>
				<span class="Validform_checktip"></span>
				<label id="contactAddr2" class="Validform_label" style="display: none;">联系地址</label>
			</td>
			<%-- <td align="right">
				<label class="Validform_label">联系方式:</label>
			</td>
			<td class="value">
		     	 <input id="contactType" name="contactType" type="text"  onkeypress="return gotoInt(event)" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.contactType}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系方式</label>
			</td> --%>
			
			<td align="right">
				<label class="Validform_label">联系方式-座机:</label>
			</td>
			<td class="value">
		     	 <input id="zuoJi" name="zuoJi" type="text"  onkeypress="return gotoInt(event)" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.zuoJi}'/>
				<span class="Validform_checktip"></span>
				<label id="zuoJi2" class="Validform_label" style="display: none;">联系方式-座机</label>
			</td>
			
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">行内联行行号:</label>
			</td>
			<td class="value">
		     	 <input id="lineHanghao" name="lineHanghao" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.lineHanghao}'/>
				<span class="Validform_checktip"></span>
				<label id="lineHanghao2" class="Validform_label" style="display: none;">行内联行行号</label>
			</td>
			<%-- <td align="right">
				<label class="Validform_label">托管行预留印鉴:</label>
			</td>
			<td class="value">
		     	 <input id="seal" name="seal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.seal}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">托管行预留印鉴</label>
			</td> --%>
			<td align="right">
				<label class="Validform_label">联系方式-手机:</label>
			</td>
			<td class="value">
		     	 <input id="mobilePhone" name="mobilePhone" type="text"  onkeypress="return gotoInt(event)" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.mobilePhone}'/>
				<span class="Validform_checktip"></span>
				<label id="mobilePhone2" class="Validform_label" style="display: none;">联系方式-手机</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">人名章:</label>
			</td>
			<td class="value">
		     	 <input id="zhang" name="zhang" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.zhang}'/>
				<span class="Validform_checktip"></span>
				<label id="zhang2" class="Validform_label" style="display: none;">人名章</label>
			</td>
			<td align="right">
				<label class="Validform_label">存单金额:</label>
			</td>
			<td class="value">
		     	 <input id="cundan" name="cundan" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${bankAccountPage.cundan}'/>
				<span class="Validform_checktip"></span>
				<label id="cundan2" class="Validform_label" style="display: none;">存单金额</label>
			</td>
		</tr>
		<tr>
		
		   <td align="right">
				<label class="Validform_label">托管行预留印鉴:</label>
			</td>
			<td class="value">
		     	 <input id="seal" name="seal" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${bankAccountPage.seal}'/>
				<span class="Validform_checktip"></span>
				<label id="seal2" class="Validform_label" style="display: none;">托管行预留印鉴</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">到期日:</label>
			</td>
			<td class="value">
					  <input id="todate" name="todate" type="text" style="width: 150px"   ignore="ignore" class="Wdate" onClick="WdatePicker()"  value='<fmt:formatDate value='${bankAccountPage.todate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label id="todate2" class="Validform_label" style="display: none;">到期日</label>
			</td>
		</tr>
	
		<tr>
		<!-- 文件上传 -->
 				<td align="right">
							<label class="Validform_label">
								文件:
							</label>
						</td>
						<td class="value">
									<table id="file_fileTable"></table>
										<table></table>
										<script type="text/javascript">
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
														$('#file').uploadify("settings", "formData", {
															'cgFormId':cgFormId,
															'cgFormName':'bank_account',
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
										<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件</label>
						</td>
		<!-- 文件上传 -->
			<td align="right">
				<label class="Validform_label">备注项:</label>
			</td>
			<td class="value" colspan="2">
				 <textarea id="remarks" style="width:150px; height:100px;"  class="inputxt" rows="6" name="remarks"  ignore="ignore" >${bankAccountPage.remarks}</textarea>
				<span class="Validform_checktip"></span>
				<label id="remarks2" class="Validform_label" style="display: none;">备注项</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false"> 				
				   
				 	<%--  <t:tab href="bankAccountController.do?accountRateChangesList&accountNumber=encodeURI(encodeURI('${bankAccountPage.accountNumber}'))&dETAIL_NAME=${bankAccountPage.acountName}&dETAIL_FULLNAME=${bankAccountPage.acountFullname}" icon="icon-search" title="账户利率变化明细表" id="accountRateChanges"></t:tab>
				  --%>
				  <%--   <t:tab href="bankAccountController.do?raiseAccountUseList&yINHANG_ACCOUNT=${bankAccountPage.accountNumber}&yINHANG_NAME=${bankAccountPage.acountName}&yINHANG_NAMEFULL=${bankAccountPage.acountFullname}" icon="icon-search" title="募集账户使用情况明细" id="raiseAccountUse"></t:tab>
				 --%>
				 
				   
				   <%-- <t:tab href="bankAccountController.do?accountRateChangesListBy3Attr&id=${bankAccountPage.id}" icon="icon-search" title="账户利率变化明细表" id="accountRateChanges"></t:tab> --%>
				   <t:tab href="bankAccountController.do?accountRateChangesList&id=${bankAccountPage.id}" icon="icon-search" title="账户利率变化明细表" id="accountRateChanges"></t:tab>
				   <t:tab href="bankAccountController.do?raiseAccountUseList&id=${bankAccountPage.id}" icon="icon-search" title="账户使用情况明细" id="raiseAccountUse"></t:tab>
				   <t:tab href="bankAccountController.do?bankCommentList&id=${bankAccountPage.id}" icon="icon-search" title="复审人历史意见" id="bankCommentList"></t:tab>
				
				
				 </t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_accountRateChanges_table_template">
						<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailNumber" value='${bankAccountPage.accountNumber}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailName" value='${bankAccountPage.acountName}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailFullname"  value='${bankAccountPage.acountFullname}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left">
					  		<input name="accountRateChangesList[#index#].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">利率</label>
				  </td>
				  <td align="left">
							<input name="accountRateChangesList[#index#].startDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">开始日期</label>
				  </td>
				  <td align="left">
							<input name="accountRateChangesList[#index#].endDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">结束日期</label>
				  </td>
			</tr>
		 </tbody>
		<tbody id="add_raiseAccountUse_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangAccount" value='${bankAccountPage.accountNumber}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"   />
					  <label class="Validform_label" style="display: none;">银行账号</label>
				  </td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangName" value='${bankAccountPage.acountName}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  />
					  <label class="Validform_label" style="display: none;">银行户名</label>
				  </td>
				  <td align="left">
					  		<input name="raiseAccountUseList[#index#].yinhangNamefull" value='${bankAccountPage.acountFullname}' maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  />
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				  </td>
				  <td align="left"> 
					  	<input name="raiseAccountUseList[#index#].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  
					   <label class="Validform_label" style="display: none;">曾用产品</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="raiseAccountUseList[#index#].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
				  </td>
			</tr>
		 </tbody>
		
		</table>
 </body>
<!-- -文件上传 -->
<script src = "webpage/com/jeecg/bankaccount/fileUpload.js"></script>		
	  	<script type="text/javascript">
		  	//加载 已存在的 文件
		  	$(function(){
		  		var cgFormId=$("input[name='id']").val();
		  		$.ajax({
		  		   type: "post",
		  		   url: "fileUploadController.do?getFiles&id=" +  cgFormId,
		  		   success: function(data){
		  			 var arrayFileObj = jQuery.parseJSON(data).obj;
		  			 
		  			$.each(arrayFileObj,function(n,file){
		  				var fieldName = file.field.toLowerCase();
		  				var table = $("#"+fieldName+"_fileTable");
		  				var tr = $("<tr style=\"height:34px;width:auto;\"></tr>");
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
					$('#file').uploadify('upload', '*');	
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
					$('#file').uploadify('cancel', '*');	
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

<!-- -文件上传 -->
<script type="text/javascript">
//查询临时表中的数据-start

$(document).ready(function(){
	 
	  var id = $("#id").val();
	  console.log(id);
	  if(id){
						
						  var  bankAccountemp="bankAccountempController.do?getBankAccountempEntity";
						   var formData = new Object();
						formData['id'] = id ? id : "";
							$.ajax({
							  async : false,
							  cache : false,
							type : 'POST',
							url : bankAccountemp,
							data : formData,
							error : function() {
							  //访问路径不存在
									
							},
							success : function(data) {
							  var d = $.parseJSON(data);
							  if (d.success) {//存在临时记录，说明被修改过
								  console.log('start');//属性
								  var acount = d.attributes.bankAccountemp2;
								  var acountView = d.attributes.bankAccountemp;//显示的对象
								  
								  var acountZhengShi = d.attributes.bankAccount;
								 //  console.log($("[name='acountShortname']").val());//属性值
								  // $("[name='acountShortname']").attr("datatype","*").removeAttr("ignore");
								  $.each(acount, function(key, val) {  
									  
									  $.each(acountZhengShi, function(key, val) {  //内层循环
										  //console.log(key);
										 // console.log(acount[key]);
										  //if(acount[key]!=null&&acount[key]&&acountZhengShi[key]!=null&&acountZhengShi[key]){
										  if(acount[key]!=null&&acount[key]){
											  if(acountZhengShi[key]!=acount[key]){
												  //console.log(acountZhengShi['status']);
												  console.log(key+""+acount[key]);
												  console.log(key+""+acountZhengShi[key]);
												 // $("#"+key).css("background-color","#FFD700");
												 if(acountZhengShi['status']=='X'){
													  $("#"+key+"2").css({"display":"inline","color":"red"});
												      $("#"+key+"2").html("修改为--> "+acountView[key]);
												}
											  }
										  }
										  
										  console.log(acountZhengShi['acountInterestRate']);//无
										  console.log("测试临时"+acount['acountInterestRate']);//有
										  
										  if(!acountZhengShi[key]&&acount[key]){
											  if(acountZhengShi['status']=='X'&&key != 'acountStatus' &&key != 'jingban'){
												  $("#"+key+"2").css({"display":"inline","color":"red"});
											      $("#"+key+"2").html("修改为--> "+acountView[key]);
											}
										  }
									  });
									
									  
									  
									  });
								  console.log('end');
								  
								  /* var acountName = d.attributes.bankAccountemp.acountName;
								  var currentName = $("#acountName").val();
								  if(acountName!=currentName){
									  console.log("不相等");
								  }else{
									  console.log("相等");
								  }
								  
							      $("#acountName").css("background-color","#FFD700");
							      $("#acountName2").html("修改为--> "+acountName); */
							  }else {//不存在临时记录，说明没有被修改过，不用做任何操作
								 
									////showErrorMsg(d.msg);
									return false;

							  }
							}
						  });
						 
					}
	  
	  
	  
	 
});
//查询临时表中的数据-end


  //编写自定义JS代码
   var global_optionValue;
  function sffxyh(optionValue){
	  if(optionValue==""){
		  return;
	  }
	  //业务逻辑...
	  if(optionValue!=5){
		  //账户状态，设置为可选，“正常”、“已注销”、“募集在用”（只有募集账户显示此项）
		  $("[name='acountStatus'] option[value='2']").remove(); 
	  }
	 
		  setCommonAttr();
		  if(optionValue==1||optionValue==3||optionValue==4||optionValue==5){
			  $("#acountInterestRate").attr("datatype","*").removeAttr("ignore");
			  $("#interestRateDate").attr("datatype","*").removeAttr("ignore");
		  }
		  if(optionValue==1||(optionValue>=9&&optionValue<=16)){
			  $("[name='onlineProduct']").attr("datatype","*").removeAttr("ignore");
		  }
		  if(optionValue==2){
			  $("#isLiushui").attr("datatype","*").removeAttr("ignore");//是否流水户 必选！
			  $("[name='onlineProduct']").attr("datatype","*").removeAttr("ignore");
		  }
		  if(optionValue==5){//5代表募集账户
			  $("#isPreEnd").attr("datatype","*").removeAttr("ignore");//是否可以提前结息 必选！
			  //账户状态，设置为可选，“正常”、“已注销”、“募集在用”（只有募集账户显示此项）
			  //$("[name='acountStatus'] option[value='2']").remove(); 
			  //alert($("[name='acountStatus'] option[value='2']").length);
			  if ($("option[value=2]","#acountStatus").length==0) { 

　　　				 $("[name='acountStatus']").append('<option value="2">募集在用</option>');

　　				}
			 
			  
		  }
	  
	  
	  if(global_optionValue==3){
		  $("#acountName").removeAttr("datatype");
		  $("#accountNumber").removeAttr("datatype");
		  $("#acountFullname").removeAttr("datatype");
		  $("[name='acountShortname']").removeAttr("datatype");
		  $("#kaihuDate").removeAttr("datatype");
		  
	  }
	  var acountStatus = $("#acountStatus").val();
		if ($("#acountStatus").val()==3) { 
			 $("#acountName").removeAttr("datatype");
			  $("#accountNumber").removeAttr("datatype");
			  $("#acountFullname").removeAttr("datatype");
			  $("[name='acountShortname']").removeAttr("datatype");
			  $("#kaihuDate").removeAttr("datatype");
		}
  }
  
function setCommonAttr(){
	  $("#acountName").attr("datatype","*").removeAttr("ignore");
	  $("#acountFullname").attr("datatype","*").removeAttr("ignore");
	  $("[name='acountShortname']").attr("datatype","*").removeAttr("ignore");
	  $("#kaihuDate").attr("datatype","*").removeAttr("ignore");
	  $("#acountStatus").attr("datatype","*").removeAttr("ignore");
	  $("#contact").attr("datatype","*").removeAttr("ignore");
	  $("#contactType").attr("datatype","*").removeAttr("ignore");
  }
function addColorAndSetRequired(optionValue){
	global_optionValue = optionValue;
	if(optionValue!=3){//3代表开户中
		  $("#acountName").attr("datatype","*").removeAttr("ignore");
		  $("#accountNumber").attr("datatype","*").removeAttr("ignore");
		  $("#acountFullname").attr("datatype","*").removeAttr("ignore");
		  $("[name='acountShortname']").attr("datatype","*").removeAttr("ignore");
		  $("#kaihuDate").attr("datatype","*").removeAttr("ignore");
		  $("[name='acountType']").attr("datatype","*").removeAttr("ignore");
	}else{
		  $("#acountName").removeAttr("datatype");
		  $("#accountNumber").removeAttr("datatype");
		  $("#acountFullname").removeAttr("datatype");
		  $("[name='acountShortname']").removeAttr("datatype");
		  $("#kaihuDate").removeAttr("datatype");
		  $("[name='acountType']").removeAttr("datatype");
	}
	if(optionValue==-1){
		$("#acountStatSpan").css("background-color","White"); 
	}
	if(optionValue==0){
		$("#acountStatSpan").css("background-color","gray"); 
		}
	if(optionValue==1){
		 $("#acountStatSpan").css("background-color","green");
	}
	if(optionValue==2){
		 $("#acountStatSpan").css("background-color","red");
	}
}

$("select#acountStatus").change(function(){
	addColorAndSetRequired($(this).val());
 });

//初始化下标
  function resetTrNum(tableId) {
  	$tbody = $("#"+tableId+"");
  	$tbody.find('>tr').each(function(i){
  		$(':input, select,button,a', this).each(function(){
  			var $this = $(this),validtype_str = $this.attr('validType'), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'), val = $this.val();
  			if(name!=null){
  				if (name.indexOf("#index#") >= 0){
  					$this.attr("name",name.replace('#index#',i));
  				}else{
  					var s = name.indexOf("[");
  					var e = name.indexOf("]");
  					var new_name = name.substring(s+1,e);
  					$this.attr("name",name.replace(new_name,i));
  				}
  			}
  			if(id!=null){
  				if (id.indexOf("#index#") >= 0){
  					$this.attr("id",id.replace('#index#',i));
  				}else{
  					var s = id.indexOf("[");
  					var e = id.indexOf("]");
  					var new_id = id.substring(s+1,e);
  					$this.attr("id",id.replace(new_id,i));
  				}
  			}
  			if(onclick_str!=null){
  				if (onclick_str.indexOf("#index#") >= 0){
  					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
  				}else{
  				}
  			}
  			if(validtype_str!=null){
  				if(validtype_str.indexOf("#index#") >= 0){
  					$this.attr("validType",validtype_str.replace('#index#',i));
  				}
  			}
  		});
  		$(this).find('div[name=\'xh\']').html(i+1);
  	});
  }
  
//只能输入数字
  function gotoInt(e){
   var isie = (document.all) ? true : false;//判断是IE内核还是Mozilla 
   var key; 
   if (isie) {
    key = window.event.keyCode;//IE使用windows.event事件 
   }else { 
    key = e.which;//3个按键函数有一个默认的隐藏变量，这里用e来传递。e.which给出一个索引值给Mo内核（注释1） 
   } 
   if(key>=48&&key<=57){
      return true;
   }
   return false;
  }
  </script>		

 
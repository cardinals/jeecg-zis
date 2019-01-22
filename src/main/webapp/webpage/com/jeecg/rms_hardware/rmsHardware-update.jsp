<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>rms_hardware</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="online/template/ledefault/css/vendor.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap-theme.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap.css">
  <link rel="stylesheet" href="online/template/ledefault/css/app.css">
  
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/style.css" type="text/css"/>
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/tablefrom.css" type="text/css"/>
  
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
  <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
  <script type="text/javascript" src="plug-in/easyui/locale/zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
  <script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
  <script type="text/javascript" src="plug-in/tools/curdtools_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
   <script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>

 <body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="rmsHardwareController.do?doUpdate" tiptype="1" >
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" name="id" value='${rmsHardwarePage.id}' >
			
			
			<div class="tab-wrapper">
			    <!-- tab -->
			    <ul class="nav nav-tabs">
			      <li role="presentation" class="active"><a href="javascript:void(0);">rms_hardware</a></li>
			    </ul>
			    <!-- tab内容 -->
			    <div class="con-wrapper" id="con-wrapper1" style="display: block;">
			      <div class="row form-wrapper">
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>maintype：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="maintype" type="list" extendJson="{class:'form-control'}"   typeGroupCode="servertype"  defaultVal="${rmsHardwarePage.maintype}" hasLabel="false"  title="maintype"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">maintype</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>status：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="status" type="radio" extendJson="{class:'form-control'}"   typeGroupCode="status"  defaultVal="${rmsHardwarePage.status}" hasLabel="false"  title="status"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">status</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>location：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="location" name="location" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.location}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">location</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>position：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="position" name="position" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.position}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">position</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>hardwareType：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="hardwareType" name="hardwareType" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.hardwareType}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">hardwareType</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>hardwareNo：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="hardwareNo" name="hardwareNo" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.hardwareNo}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">hardwareNo</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>remoteIlo：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="remoteIlo" name="remoteIlo" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.remoteIlo}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">remoteIlo</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>purchaseDate：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="purchaseDate" name="purchaseDate" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${rmsHardwarePage.purchaseDate}' type="date" pattern="yyyy-MM-dd"/>' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">purchaseDate</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>vendor：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="vendor" name="vendor" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.vendor}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">vendor</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>warrantyDate：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="warrantyDate" name="warrantyDate" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${rmsHardwarePage.warrantyDate}' type="date" pattern="yyyy-MM-dd"/>' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">warrantyDate</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>maintainDate：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="maintainDate" name="maintainDate" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" ignore="ignore"  value='<fmt:formatDate value='${rmsHardwarePage.maintainDate}' type="date" pattern="yyyy-MM-dd"/>' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">maintainDate</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>warrantyVendor：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="warrantyVendor" name="warrantyVendor" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.warrantyVendor}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">warrantyVendor</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>remark1：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="remark1" name="remark1" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.remark1}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">remark1</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>remark2：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="remark2" name="remark2" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.remark2}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">remark2</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>createTime：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="createTime" name="createTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${rmsHardwarePage.createTime}' type="date" pattern="yyyy-MM-dd"/>' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">createTime</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>lastUpdateTime：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="lastUpdateTime" name="lastUpdateTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${rmsHardwarePage.lastUpdateTime}' type="date" pattern="yyyy-MM-dd"/>' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">lastUpdateTime</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>applicant：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="applicant" type="list" extendJson="{class:'form-control'}"   typeGroupCode="user"  defaultVal="${rmsHardwarePage.applicant}" hasLabel="false"  title="applicant"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">applicant</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>createdBy：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="createdBy" type="list" extendJson="{class:'form-control'}"   typeGroupCode="user"  defaultVal="${rmsHardwarePage.createdBy}" hasLabel="false"  title="createdBy"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">createdBy</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>updatedBy：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="updatedBy" type="list" extendJson="{class:'form-control'}"   typeGroupCode="user"  defaultVal="${rmsHardwarePage.updatedBy}" hasLabel="false"  title="updatedBy"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">updatedBy</label>
			          </div>
			          
			        
			          <div class="col-xs-3 text-center">
			          	<b>agent：</b>
			          </div>
			          <div class="col-xs-3">
								<t:dictSelect field="agent" type="list" extendJson="{class:'form-control'}"   typeGroupCode="user"  defaultVal="${rmsHardwarePage.agent}" hasLabel="false"  title="agent"></t:dictSelect>     
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">agent</label>
			          </div>
							</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>name：</b>
			          </div>
			          <div class="col-xs-3">
								<input id="name" name="name" type="text" class="form-control" ignore="ignore"  value='${rmsHardwarePage.name}' />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">name</label>
			          </div>
							<div class="col-xs-2 text-center"><b></b></div>
			         		<div class="col-xs-4"></div>
							</div>
			          
			        
			          <div class="row" id = "sub_tr" style="display: none;">
				        <div class="col-xs-12 layout-header">
				          <div class="col-xs-6"></div>
				          <div class="col-xs-6"><button type="button" onclick="neibuClick();" class="btn btn-default">提交</button></div>
				        </div>
				      </div>
			     </div>
			   </div>
			   
			   <div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
			 </div>
  </t:formvalid>

<script type="text/javascript">
   $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
	
	if(location.href.indexOf("mode=read")!=-1){
		//查看模式控件禁用
		$("#formobj").find(":input").attr("disabled","disabled");
	}
	if(location.href.indexOf("mode=onbutton")!=-1){
		//其他模式显示提交按钮
		$("#sub_tr").show();
	}
   });

  var neibuClickFlag = false;
  function neibuClick() {
	  neibuClickFlag = true; 
	  $('#btn_sub').trigger('click');
  }

</script>
 </body>
<script src = "webpage/com/jeecg/rms_hardware/rmsHardware.js"></script>		
</html>
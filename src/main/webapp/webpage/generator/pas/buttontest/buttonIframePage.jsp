<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<!-- <div class="easyui-layout" fit="true">
   <div region="center" style="padding:0px;border:0px;overflow-x:hidden;"> -->
   
 
  <!--  <div id="accDiv" class="easyui-accordion" style="padding-right:15px;overflow-x:hidden;box-sizing: border-box;"> -->
	
	
		<div style="height:300px;border-bottom:1px solid #000;"><!-- 上侧div -->
		<div  id="right" style="float: left;margin-right:200px;"><!-- 左侧div -->
   		  <iframe id="mainList" src="${webRoot}/buttonTestController.do?list" frameborder="0" height="100%" width="160%"></iframe>
  		 </div>
		
		<div title="按钮表格测试" style="height:200px;" ><!--右侧div -->
				<table style="width: 40%;height: 200px;text-align: center;align:right">
					<tr align="center" ><td>交易金额(元):<input name="Fruit" type="radio" value="1" checked />买<input name="Fruit" type="radio" value="-1" />卖</td><td><input id="money" name="money"></td></tr>
					<tr><td>买卖后基金指数资产股票市值(元)：</td><td><input  id="maimoney" disabled="disabled"></td></tr>
					<tr><td>实际交易金额(元)：</td><td><input disabled="disabled"></td></tr>
					<tr><td><button onclick="createNumber()" type="button" style="width:130px;height:40px">生成并导出交易指令</button></td><td><button type="button" style="width:130px;height:40px">导出样本股信息</button></td></tr>
					<tr><td><button type="button" style="width:130px;height:40px">导出交易清单 </button></td><td><button type="button" style="width:130px;height:40px">构建空白组合</button></td></tr>
					
					<tr><td colspan="2"><button type="button" style="width:270px;height:40px">交易计算</button></td></tr>
				</table>
		</div>
		</div>
		
		<div ><!-- 下侧div -->
   		  <iframe id="mainList" src="${webRoot}/banktestController.do?list" frameborder="0" height="100%" width="160%"></iframe>
  		 </div>
<script type="text/javascript">

function createNumber(){
	var ControllerUrl="pasStaffLeadController.do?checkConfirm";//后台路径
	
	var radioVal =  $('input:radio:checked').val();   
	var inputVal = $("#money").val(); 
	var needVal = radioVal*inputVal;//传到后台的数据
	
	 $.ajax({
		  type : 'POST',
		  url : ControllerUrl,
		  data : {needVal:needVal},
		  error : function() {
		          },
		  success : function(data) {
			  var d = $.parseJSON(data);
			  if (d.success) {
				 alert("后台返回成功！");
				 
				 $('#maimoney').val(needVal); 
				 
			  }
			  
		  }
		});
	
	
}
</script>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公募基本表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pfBaseController.do?doUpdate" beforeSubmit = "beforefunc">
					<input id="id" name="id" type="hidden" value="${pfBasePage.id }"/>
					<input id="manas" name="manas" type="hidden" value="${manas}"/>
					<input id="codes" name="codes" type="hidden" value="${codes}"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" >
					<tr>
							<label class="Validform_label">
								名称:
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  datatype="*"   value='${pfBasePage.name}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label">
								基金简称:
							</label>
						    <input id="shortName" name="shortName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBasePage.shortName}'/>
					</tr><br>
					<tr>
							<label class="Validform_label">
								成立日期:
							</label>
									  <input id="estaDate" name="estaDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pfBasePage.estaDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<label class="Validform_label">
								托管人:
							</label>&nbsp;&nbsp;&nbsp;
									<t:dictSelect field="tuoguanPerson" type="list"  typeGroupCode="tuoGuan"   defaultVal="${pfBasePage.tuoguanPerson}" hasLabel="false"  title="托管人" ></t:dictSelect>     
					</tr><br>
						<!-- start2 -->
				<tr style="width: 200px;">	
		  		<ul id="dsUL2">
    				<li id="dsLI2" name="dsLI2" class="conditions oop" >
    						<label class="Validform_label">
								基金代码:
							</label>
    				   <input id="field2" name="cons[0].fld" type="text" id="orgCode" style="width: 150px" >
    					<span>
                            <a id="add" href="javascript:addULChild2()" class="fa fa-plus-square" title="添加一个新的过滤条件" style="margin-left: 3px;"></a>
                            <a id="delete" href="javascript:void(0)" onclick="deleteULChild2(this)" class="fa fa-minus-square" title="删除此过滤条件" style="margin-left: 23px;"></a>
    					</span><label style="color:red;">(可动态添加多个基金代码)</label>
    					</li>
					</ul>
				</tr>
				 <input  id="_sqlbuilder2" name="_sqlbuilder2"   type="hidden" />
		<!-- end2 -->
					<tr>
							<label class="Validform_label">
								产品状态:
							</label>
							<t:dictSelect field="productState" type="list"  typeGroupCode="pro_state"   defaultVal="${pfBasePage.productState}" hasLabel="false"  title="产品状态" ></t:dictSelect>     
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label class="Validform_label">
								批复日期:
							</label>
									  <input id="pifuDate" name="pifuDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pfBasePage.pifuDate}' type="date" pattern="yyyy-MM-dd"/>'/>
					</tr><br>
							<!-- start -->
				<tr style="width: 200px;">	
		  		<ul id="dsUL">
    				<li id="dsLI" name="dsLI" class="conditions oop" >
    						<label class="Validform_label">
								基金经理:
							</label>
    				   <input id="field" name="cons[0].fld" type="text" id="orgCode" style="width: 150px" >
    					<span>
                            <a id="add" href="javascript:addULChild()" class="fa fa-plus-square" title="添加一个新的过滤条件" style="margin-left: 3px;"></a>
                            <a id="delete" href="javascript:void(0)" onclick="deleteULChild(this)" class="fa fa-minus-square" title="删除此过滤条件" style="margin-left: 23px;"></a>
    					</span>
    					<label style="color:red;">(可动态添加多个基金经理)</label>
    					</li>
					</ul>
				</tr>
				 <input  id="_sqlbuilder" name="_sqlbuilder"   type="hidden" />
		<!-- end -->
					<label class="Validform_label">
								成立规模:
							</label>
						    <input id="foundScale" name="foundScale" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBasePage.foundScale}'/>
					</tr><br>
					<tr>
							<label class="Validform_label">
								发行开始日期:
							</label>
									  <input id="faxingDate" name="faxingDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pfBasePage.faxingDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<label class="Validform_label">
								发行截止日期:
							</label>
									  <input id="faxingDateto" name="faxingDateto" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${pfBasePage.faxingDateto}' type="date" pattern="yyyy-MM-dd"/>'/>
					</tr><br>
					<tr>
							<label class="Validform_label">
								律师事务所:
							</label>&nbsp;&nbsp;&nbsp;
									<t:dictSelect field="lawOffice" type="list"  typeGroupCode="lvshi"   defaultVal="${pfBasePage.lawOffice}" hasLabel="false"  title="律师事务所" ></t:dictSelect>     
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label class="Validform_label">
								期货公司:
							</label>
						    <input id="futuresCompany" name="futuresCompany" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBasePage.futuresCompany}'/>
					</tr><br>
					<tr>
							<label class="Validform_label">
								机构定制:
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <input id="isDingzhi" name="isDingzhi" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${pfBasePage.isDingzhi}'/>
							<label class="Validform_label">
								风险等级:
							</label>
									<t:dictSelect field="riskLevel" type="list"  typeGroupCode="risk_level"   defaultVal="${pfBasePage.riskLevel}" hasLabel="false"  title="风险等级" ></t:dictSelect>     
					</tr><br><br>
				
					<tr>
							<label for= "remarks"  class="Validform_label">
								备注:
							</label>
						  	 	<textarea id="remarks" style="width:500px;height:80px;vertical-align:middle;" class="inputxt" rows="6" name="remarks"  ignore="ignore" >${pfBasePage.remarks}</textarea>
					</tr><br>
					
	
		
		
		
			</table>
		</t:formvalid>
 </body>
<script type="text/javascript" >
 $(function(){
	//1. 遍历基金经理
	var jsonDate = ${manas};
	 $.each(jsonDate, function (i, n)
	 {
		 if(i==0){//默认第一条数据
			 var myLi = document.getElementById("dsLI");
		 		$("#field").val(n.name);
		 }else{
			 
			    var myUl = document.getElementById("dsUL");
		  		var myLi = document.getElementById("dsLI");
		  		var newLi = document.createElement("li");
		  		newLi.setAttribute("name","dsLI");
		  		$(newLi).addClass("oop");
		  		newLi.innerHTML=myLi.innerHTML;
		  		myUl.appendChild(newLi);
		  		//start
		  		$("ul[id='dsUL'] li:eq("+i+") input[id='field']").val(n.name); 
		  		//end
		  		resetTrNum();
		  		
		 }
		
	 });
	 
		//2. 遍历基金代码
		var jsonDate2 = ${codes};
		 $.each(jsonDate2, function (i, n)
		 {
			 if(i==0){//默认第一条数据
				 var myLi = document.getElementById("dsLI2");
			 		$("#field2").val(n.code);
			 }else{
				 
				    var myUl = document.getElementById("dsUL2");
			  		var myLi = document.getElementById("dsLI2");
			  		var newLi = document.createElement("li");
			  		newLi.setAttribute("name","dsLI2");
			  		$(newLi).addClass("oop");
			  		newLi.innerHTML=myLi.innerHTML;
			  		myUl.appendChild(newLi);
			  		//start
			  		$("ul[id='dsUL2'] li:eq("+i+") input[id='field2']").val(n.code); 
			  		//end
			  		resetTrNum2();
			  		
			 }
			
		 });
		 
	 
 });
 
 function beforefunc(){
	 beforefuncA();
	 beforefuncB();
 }
 
  function beforefuncA(){
	  var idIndex = 100;
		
		
		var dsli = document.getElementsByName("dsLI");
		var json = new StringBuffer();
		$(dsli).each(function(i){
			idIndex++;
			var field = $(this).find("input[id='field']").val();
			if(i == 0) {
				json.append("[{\"name\":\""+field+"\"}");	
				
			}
			if(i != 0) {
				json.append(",{\"name\":\""+field+"\"}");
				
			
			}
			
		});
		json.append("]");
		$("#_sqlbuilder").val(json.toString());
  }

  function beforefuncB(){
	  var idIndex = 100;
		
		
		var dsli = document.getElementsByName("dsLI2");
		var json = new StringBuffer();
		$(dsli).each(function(i){
			idIndex++;
			var field = $(this).find("input[id='field2']").val();
			
			
			if(i == 0) {
				json.append("[{\"code\":\""+field+"\"}");	
				
			}
			if(i != 0) {
				json.append(",{\"code\":\""+field+"\"}");
				
			
			}
			
		});
		json.append("]");
		$("#_sqlbuilder2").val(json.toString());
  }
  	//添加StringBuffer
  	function StringBuffer(){  
  	    this.strings = new Array;  
  	}  
  	  
  	StringBuffer.prototype.append=function(str){  
  	    this.strings.push(str); //追加指定元素  
  	};  
  	  
  	StringBuffer.prototype.toString = function(){  
  	    return this.strings.join(""); //向数组之间的元素插入指定字符串（此处为空字符串），并返回。  
  	};
  	//添加相同的li
  	function addULChild() {
  		var	myUl = document.getElementById("dsUL");
  		var	myLi = document.getElementsByName("dsLI")[0];//修改时间20190108
  		var newLi = document.createElement("li");
  		newLi.setAttribute("name","dsLI");
  		$(newLi).addClass("oop");
  		newLi.innerHTML=myLi.innerHTML;
  		myUl.appendChild(newLi);
  		resetTrNum();
  	}
  	
  //添加相同的li
  	function addULChild2() {
  		var	myUl = document.getElementById("dsUL2");
  		var	myLi = document.getElementsByName("dsLI2")[0];//修改时间20190108
  		var newLi = document.createElement("li");
  		newLi.setAttribute("name","dsLI2");
  		$(newLi).addClass("oop");
  		newLi.innerHTML=myLi.innerHTML;
  		myUl.appendChild(newLi);
  		resetTrNum2();
  		
  	}
  
  	//初始化下标
  	function resetTrNum() {
  		 $('#dsUL').find('li').each(function(i){
  			$(':input,select',this).each(function(){
  				var thisli = $(this);
  				var name = thisli.attr('name');
  				if(name!=null){
  				var reg=new RegExp("^cons");
  	    			if(reg.test(name)) {
  	    				if(name.indexOf("#index#") >= 0) {
  	    					thisli.attr("name",name.replace('#index#',i-1));
  	    				}else {
  	    					var s = name.indexOf("[");
  	    					var e = name.indexOf("]");
  	    					var new_name = name.substring(s+1,e);
  	    					thisli.attr("name",name.replace(new_name,i-1));
  	    				}
  	    			}
  				}
  			});
  		});
  	}
  	
    //初始化下标
  	function resetTrNum2() {
  		 $('#dsUL2').find('li').each(function(i){
  			$(':input,select',this).each(function(){
  				var thisli = $(this);
  				var name = thisli.attr('name');
  				if(name!=null){
  				var reg=new RegExp("^cons");
  	    			if(reg.test(name)) {
  	    				if(name.indexOf("#index#") >= 0) {
  	    					thisli.attr("name",name.replace('#index#',i-1));
  	    				}else {
  	    					var s = name.indexOf("[");
  	    					var e = name.indexOf("]");
  	    					var new_name = name.substring(s+1,e);
  	    					thisli.attr("name",name.replace(new_name,i-1));
  	    				}
  	    			}
  				}
  			});
  		});
  	}
    
  	//删除当前li
  	function deleteULChild(col) {
  		var myLi = document.getElementById("dsLI");
  		var dsli = document.getElementsByName("dsLI");
  		//判断最后剩一个li则不删除
  		$(dsli).each(function(i){
  			if(i!=0) {
  				$(col).closest("li").remove();
  			}
  		})
  		resetTrNum();
  	}
  	
	//删除当前li  2
  	function deleteULChild2(col) {
  		var dsli = document.getElementsByName("dsLI2");
  		//判断最后剩一个li则不删除
  		$(dsli).each(function(i){
  			if(i!=0) {
  				$(col).closest("li").remove();
  			}
  		})
  		resetTrNum2();
  	}
	
  	//重置按钮，清空所有
  	function searchReset() {
  		$("#dsUL").find(".oop:gt(0)").remove();
  	    $("#dsLI").find(":input").val("");
  	    $("#_sqlbuilder").val(null);
  	    jeecgDemoListquerysearch();
  	}
  	//判断输入的是否为日期格式
  	function CheckDate(strInputDate) {
  		  if (strInputDate == "") return false;
  		  strInputDate = strInputDate.replace(/-/g, "/");
  		  var d = new Date(strInputDate);
  		  if (isNaN(d)) return false;
  		  var arr = strInputDate.split("/");
  		  return ((parseInt(arr[0], 10) == d.getFullYear()) && (parseInt(arr[1], 10) == (d.getMonth() + 1)) && (parseInt(arr[2], 10) == d.getDate()));
  	}
  
  </script>		

	

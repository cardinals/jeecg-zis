<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>动态增加属性</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
  
  <style type="text/css">
   label{
            cursor: pointer;
            padding: 3px 6px;
            text-align: left;
            width: 80px;
            vertical-align: top;
        }
  
  </style>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="addmanagerController.do?doUpdate" beforeSubmit = "beforefunc">
					<input id="id" name="id" type="hidden" value="${addmanagerPage.id }"/>
					<input id="manas" name="manas" type="hidden" value="${manas}"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1">
					<tr  style="width: 200px;">
						姓名：<input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.name}'/>
						 年纪：<input id="age" name="age" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.age}'/>
						 性别：<input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.sex}'/>
					</tr>
			<!-- start -->
				<tr style="width: 200px;">	
		  		<ul id="dsUL">
    				<li id="dsLI" name="dsLI" class="conditions oop" >
    				
    					姓名：<input id="field" name="cons[0].fld" type="text" id="orgCode" style="width: 150px" >
    					年纪：<input id="condition" name="cons[0].ctyp"  type="text" class="compareType" style="width:150px">
    					性别：<input id="conValue" name="cons[0].val" type="text" class="text conditionValue" title="" style="width: 150px" >
    					<span>
                            <a id="add" href="javascript:addULChild()" class="fa fa-plus-square" title="添加一个新的过滤条件" style="margin-left: 3px;"></a>
                            <a id="delete" href="javascript:void(0)" onclick="deleteULChild(this)" class="fa fa-minus-square" title="删除此过滤条件" style="margin-left: 23px;"></a>
    					</span>
    					</li>
					</ul>
				</tr>
				 <input  id="_sqlbuilder" name="_sqlbuilder"   type="hidden" />
		<!-- end -->
		
		<tr  style="width: 200px;">
						姓名：<input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.name}'/>
						 年纪：<input id="age" name="age" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.age}'/>
						 性别：<input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${addmanagerPage.sex}'/>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
 <script type="text/javascript" >
 $(function(){
	//根据不同的角色，隐藏 对应的控件-start
	//$("#name").hide();
	
	//根据不同的角色，隐藏 对应的控件-end
	var jsonDate = ${manas};
	//var jsonDate = $("#manas").val();
	//alert(jsonDate);
	 $.each(jsonDate, function (i, n)
	 {
		 if(i==0){//默认第一条数据
			 var myLi = document.getElementById("dsLI");
		 		$("#field").val(n.name);
		 		$("#condition").val(n.age);
		 		$("#conValue").val(n.sex);
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
		  		$("ul[id='dsUL'] li:eq("+i+") input[id='condition']").val(n.age); 
		  		$("ul[id='dsUL'] li:eq("+i+") input[id='conValue']").val(n.sex); 
		  		//end
		  		resetTrNum();
		  		
		 }
		
	 });
	 
 });
 
  function beforefunc(){
	  var idIndex = 100;
		
		var dsli = document.getElementsByName("dsLI");
		var json = new StringBuffer();
		$(dsli).each(function(i){
			idIndex++;
			var field = $(this).find("input[id='field']").val();
			var condition = $(this).find("input[id='condition']").val();
			var cValue = $(this).find("input[id='conValue']").val();
			
		
			/* if(i == 0) {
				json.append("[{\"field\":\""+field+"\",\"condition\":\""+condition+"\",\"value\":\""+cValue+"\"}");	
			}
			if(i != 0) {
				json.append(",{\"field\":\""+field+"\",\"condition\":\""+condition+"\",\"value\":\""+cValue+"\"}");
			} */
			
			if(i == 0) {
				json.append("[{\"name\":\""+field+"\",\"age\":\""+condition+"\",\"sex\":\""+cValue+"\"}");	
			}
			if(i != 0) {
				json.append(",{\"name\":\""+field+"\",\"age\":\""+condition+"\",\"sex\":\""+cValue+"\"}");
			}
			
		});
		json.append("]");
		$("#_sqlbuilder").val(json.toString());
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
  		//var myLi = document.getElementById("dsLI");//删除第一个li 不能再增加li
  		var	myLi = document.getElementsByName("dsLI")[0];//修改时间20190108
  		var newLi = document.createElement("li");
  		newLi.setAttribute("name","dsLI");
  		$(newLi).addClass("oop");
  		newLi.innerHTML=myLi.innerHTML;
  		myUl.appendChild(newLi);
  		resetTrNum();
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


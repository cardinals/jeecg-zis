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
  
  <style>
ul {
    list-style-type: none;
    list-style-image: none;
}
.conditionType {
    display: block;
    margin-bottom: 6px;
    padding: 6px 0 8px;
    width: 100%;
}select {
    padding-right: 2px!important;
}
select {
    padding: 5px 7px;
}
#dsUL .conditionSelect {
    width: 200px;
}
.datagrid .panel-body {
	position: relative;
    overflow: auto;
}
</style>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="addmanagerController.do?doAdd" beforeSubmit = "beforefunc">
					<input id="id" name="id" type="hidden" value="${addmanagerPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名字:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名字</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年纪:
						</label>
					</td>
					<td class="value">
					     	 <input id="age" name="age" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年纪</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
					     	 <input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
				</tr>
				
				<!-- start -->
				<tr>	
		  		<ul id="dsUL">
		  		
    				<li id="dsLI" name="dsLI" class="conditions oop">
    						<input id="field" name="cons[0].fld" type="text" id="orgCode">
    						<input id="condition" name="cons[0].ctyp"  type="text" class="compareType" style="width:150px">
    						<input id="conValue" name="cons[0].val" type="text" class="text conditionValue" title="">
    					
    					<span>
                            <a id="add" href="javascript:addULChild()" class="fa fa-plus-square" title="添加一个新的li" style="margin-left: 3px;"></a>
                            <a id="delete" href="javascript:void(0)" onclick="deleteULChild(this)" class="fa fa-minus-square" title="删除此li" style="margin-left: 23px;"></a>
    					</span>
    					</li>
					</ul>
				</tr>
				
				 <input  id="_sqlbuilder" name="_sqlbuilder"   type="hidden" />
		<!-- end -->
				
			</table>
		
		</t:formvalid>
 </body>
  <script type="text/javascript" >
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
  		console.log(111);
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

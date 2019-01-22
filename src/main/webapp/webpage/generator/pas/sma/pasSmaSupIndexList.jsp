<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasSmaSupIndexrowList" checkbox="true"  pagination="true" fitColumns="true" 
  title="市场销售支持满意度"  actionUrl="pasSmaSupIndexController.do?datagrid" onLoadSuccess="loadSucessFun" idField="id" fit="false"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true" ></t:dgCol>
    <t:dgCol title="评价人"  field="appraiser"   hidden="true" ></t:dgCol>
    <t:dgCol  align="center" title="部门"  field="goalDept"  rowspan="5" queryMode="single"    ></t:dgCol>
    <t:dgCol  align="center" title="被评价人"  field="goalPerson"  rowspan="5" queryMode="single"    ></t:dgCol>
    <t:dgCol  title="<b>市场销售支持满意度</b>" align="left"  field="smaSupIdx"  queryMode="single"   dictionary="pas_s100" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol  align="center" title="评分说明" newColumn="true" rowspan="5" field="comment" queryMode="single"   extendParams="editor:'text'" width="100"></t:dgCol>
	  
	  <t:dgCol   title="积极配合市场条线提供销售支持&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" newColumn="true"></t:dgCol>
	  <t:dgCol  title="包括首发的路演以及产品运作期间业绩说明和操作回顾等持续营销" newColumn="true"></t:dgCol>
      <t:dgCol  title="根据客户需求,结合产品净值变化、市场变化&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"  newColumn="true"></t:dgCol>
	  <t:dgCol  title="进行客户关系维护，注重服务质量和及时性&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" newColumn="true"></t:dgCol> 
	  
    <t:dgToolBar  title="提交" icon="icon-save" url="pasSmaSupIndexController.do?saveRows" funname="saveData"></t:dgToolBar>
   <t:dgToolBar  title="更新合计分数"  icon="icon-update"  funname="updateData"></t:dgToolBar>
  	<t:dgToolBar  title="查看评语" icon="icon-search" url="pasSmaSupIndexController.do?searchComment"  funname="findComment"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  	<div id="mengban" align="center" style="background-color:white;position:absolute;z-index:99999;display:none;width:1500px;height:1200px">
			<table>
				<tr><td width=100% style="text-align:center;vertical-align:middle"><img src="plug-in/ace/assets/css/images/loading.gif"><br>数据加载中...</td></tr>
			</table>
    </div>
 <script type="text/javascript">
 //更新数据
	function updateData(title,addurl,gname){
		var rows=$('#'+gname).datagrid("getChecked");
		for(var i=0;i<rows.length;i++){//所有行
			//var total1 = parseInt(rows[i].professionalSkill)+parseInt(rows[i].coopEff)+parseInt(rows[i].coopAtti)+parseInt(rows[i].coopOutcome);
			var index1= $('#'+gname).datagrid('getRowIndex', rows[i]);
			$('#'+gname).datagrid('endEdit',index1);
		}
		for(var i=0;i<rows.length;i++){//所有行
			var index1= $('#'+gname).datagrid('getRowIndex', rows[i]);
			//var total1 = parseInt(rows[i].professionalSkill)+parseInt(rows[i].coopEff)+parseInt(rows[i].coopAtti)+parseInt(rows[i].coopOutcome);
			//alert(total1);
			$('#'+gname).datagrid('updateRow',{index:index1,row:{smaSupIdx:smaSupIdx}});
		}
		
		for(var i=0;i<rows.length;i++){//所有行
			
			var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
			$('#'+gname).datagrid('beginEdit',index);
		}
		
	}
    //变量初始化
	var cottarget;
 	var searchStatus = "${searchStatus}";
	function showLoading() {
			var mban = document.getElementById( "mengban" );
			//var ww = document.getElementById( "sidebar" ).offsetWidth;
			//var hh = document.getElementById( "headerbar" ).offsetHeight;
			var ww=0;
			var hh=0;
			mban.style.left = ww + "px";
			mban.style.top =  hh + "px";
			mban.style.width = document.body.clientWidth - ww + "px";
			//mban.style.height = document.getElementById( "mainDiv" ).offsetHeight + "px";
			mban.style.height="100px";
			mban.style.display = "block";
	}
	function hideLoading() {
		var mban = document.getElementById("mengban");
		mban.style.display = "none";
	}	
	
 
	//保存数据
	function saveData(title,addurl,gname){
		if(!endEdit(gname))
			return false;
		//$.messager.confirm("确认", "确定要保存吗？保存后不可修改 ! ", function (r) {
	    $.messager.confirm("确认", "确定要提交吗？提交后不可修改 ! ", function (r) {
			if (r) {
	        	//关闭编辑行
	        	var  editIndex = $('#'+gname).datagrid('getRows').length-1;
	    		for(var u=0;u<=editIndex;u++){
	    			$('#'+gname).datagrid('endEdit',u);
	    		} 
	    		var uprows=$('#'+gname).datagrid("getChanges","updated");
	    		if(uprows.length<=0){
	    			tip("没有需要保存的数据！")
	    			return false;
	    		} 
				//获取所有行
				var rows = $('#'+gname).datagrid("getRows");
	    		//校验业务逻辑
	    		var result={};
	    		for(var i=0;i<rows.length;i++){
	    			for(var d in rows[i]){
	    				result["pasSmaSupIndexList["+i+"]."+d]=rows[i][d];
	    			}
	    		}
	    		$.ajax({
	    			url:"<%=basePath%>/"+addurl,
	    			type:"post",
	    			data:result,
	    			dataType:"json",
	    			success:function(data){
	    				tip("提交成功");
	    				if(data.success){
	    					$('#'+gname).datagrid('reload');
	    					$('#'+gname).datagrid('clearChecked');
	    					searchStatus = "OK";
	    					//reloadTable();
	    				}
	    			}
	    		});
	        }else{
	        	//rjectB("pasOnStaffrowList");
	        	//$('#'+gname).datagrid('reload');
	        	return false;
	        }
	    });
		
	}
	//结束编辑
	function endEdit(gname){
		var  editIndex = $('#'+gname).datagrid('getRows').length-1;
		//控制全部行编辑关闭状态
		var boln = new Boolean(true);
		for(var i=0;i<=editIndex;i++){
			if($('#'+gname).datagrid('validateRow', i)){
				//$('#'+gname).datagrid('endEdit', i);
			}else{
				tip("请选择必填项(带有*的字段)!");
				boln = false;
			}
		}
		if(!boln){
			return false;
		}
		return true;
	}
	//编辑行
	function editRow(title,ckurl,gname){
		 $.ajax({
			url:"<%=basePath%>/"+ckurl,
			type:"post",
			dataType:"json",
			success:function(data){
				//tip(data.msg);
				if(!data.attributes.initStatus){
					//reloadTable();
					$('#'+gname).datagrid('selectAll');
					var rows=$('#'+gname).datagrid("getChecked");
					/* if(rows.length==0){
						tip("请选择条目");
						return false;
					} */
					for(var i=0;i<rows.length;i++){
						var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
						$('#'+gname).datagrid('beginEdit', index);
					}
				}else{
					tip("评分已确认，不可更改! ");
					return false;
				}
				
			}
		});
		
	}

	//取消编辑a
	function reject(title,addurl,gname){
		$('#'+gname).datagrid('clearChecked');
		$('#'+gname).datagrid('rejectChanges');
	}	
	//取消编辑b
	function rjectB(gname){
		$('#'+gname).datagrid('clearChecked');
		$('#'+gname).datagrid('rejectChanges');
	}
		
	
	function loadSucessFun(data){
		showLoading();
		var gname = "pasSmaSupIndexrowList";
		 $.ajax({
				url:"<%=basePath%>/"+"pasSmaSupIndexController.do?checkConfirm",
				type:"post",
				dataType:"json",
				success:function(data){
					//tip(data.msg);
					if(!data.attributes.initStatus){
						$('#'+gname).datagrid('clearChecked');
						$('#'+gname).datagrid('selectAll');
						var rows=$('#'+gname).datagrid("getChecked");
						/* if(rows.length==0){
							tip("请选择条目");
							return false;
						} */
						for(var i=0;i<rows.length;i++){
							var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
							$('#'+gname).datagrid('beginEdit', index);
							//获得当前行的编辑对象数组
							var editors = $('#'+gname).datagrid('getEditors', index); 
							//获取控件对象
							 commentEditor= editors[1].target;//评语框
							//给所有数据行的评语输入框控件绑定事件
							commentEditor.bind("click",function(event){
								event = event || window.event; 
								var target = event.target; //这个就是触发事件的控件 
								createWindowOnComment('评语编辑', 'pasSmaSupIndexController.do?showDialogPage&rowIndex='+index,400,180,target);
							});
						}
					}
					hideLoading();
				}
			});
	}
	
	/*
			创建隐藏元素
	function createHiddenElement(){
		var ipo= document.createElement("input");
		ipo.id = "dgHide";
		ipo.style.display="none";
		document.body.appendChild(ipo);
	} */
	
	
	function createWindowFunc(title,action,width,height){
		$.dialog({
			content: 'url:'+action,
			lock : true,
			zIndex: getzIndex(),
			width:width,
			height:height,
			title:title,
			opacity : 0.3,
			cache:false
		    /*为true等价于function(){}*/
		});
		
	}
	
	function createWindowOnComment(title,action,width,height,target){
		cottarget = target;
		createWindowFunc(title,action,width,height);
	}
	
	function callBack(val){
		$(cottarget).val(val);
		$(cottarget).removeClass("validatebox-invalid"); 
	}
	
	function findComment(title,surl,gname){
		if(searchStatus == "OK"){
			var rows=$('#'+gname).datagrid("getChecked");
			if(rows.length >1 || rows.length <1 ){
				tip("请选择一条记录再查看!");
				return false;
			}else{
				var comment = rows[0].comment;
				$.dialog({
					content: comment,
					lock : true,
					zIndex: getzIndex(),
					width:400,
					height:180,
					title:title,
					opacity : 0.3,
					cache:false,
				    cancelVal: '关闭',
				    cancel: true /*为true等价于function(){}*/
				});
				
			}
		}else{
			tip("数据未保存,请保存后可查看!");
			return false;
		}
	}
 
 </script>
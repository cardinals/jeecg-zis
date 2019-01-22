<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<style>
	.img{
		 display:none;  
         width:360px;  
         height:230px;  
         background:#69F;  
         position:absolute;  
         top:30%;  
         left:40%;  
		
	}
</style>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasOnDeptrowList" checkbox="true"  pagination="true" fitColumns="true" 
  title="部门互评" actionUrl="pasOnDeptController.do?datagrid" idField="id" onLoadSuccess="loadSucessFun" queryMode="group" >
    <t:dgCol title="主键"  field="id"  hidden="true" ></t:dgCol>
    <t:dgCol title="评价人"  field="appraiser"  hidden="true" ></t:dgCol>
    <t:dgCol title="被评价部门" rowspan="4" field="goalDept" queryMode="single"    width="80"></t:dgCol><!-- extendParams="editor:'text'" 屏蔽 只读 -->
    <t:dgCol title="部门负责人" rowspan="4" field="goalDeptManager" queryMode="single"   width="80"></t:dgCol><!-- extendParams="editor:'text'" -->
    <t:dgCol title="<b>专业水平（该项满分为30分）</b>"  field="professionalSkill" queryMode="single"  dictionary="pas_s30" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="<b>合作效率（该项满分为20分）</b>"  field="coopEff" queryMode="single"  dictionary="pas_s20" extendParams="editor:'combobox'"  ></t:dgCol>
    <t:dgCol title="<b>合作态度（该项满分为20分）</b>"  field="coopAtti" queryMode="single"  dictionary="pas_s20" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="<b>合作结果（该项满分为30分）</b>"  field="coopOutcome" queryMode="single"  dictionary="pas_s30" extendParams="editor:'combobox'" ></t:dgCol>
   <%--  <t:dgCol title="评语"  rowspan="4" field="commentDept" align="center" queryMode="single"   extendParams="editor:'text'" width="50"></t:dgCol> --%>
    <t:dgCol title="总分"  rowspan="4" newColumn="true" field="totalScore" align="center" queryMode="single"   width="50"></t:dgCol>
    <!-- start -->
 	  <t:dgCol title="精通所负责工作领域内的专业知识" ></t:dgCol>
	  <t:dgCol title="工作效率高&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="主动配合其他部门的需求&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="办事质量高&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"  newColumn="true" ></t:dgCol>
	  
	  <t:dgCol title="有很强的专业水平和解决问题能力"  ></t:dgCol>
	  <t:dgCol title="守时守信并及时提供反馈&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="态度积极,沟通顺畅&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"  ></t:dgCol>
	  <t:dgCol title="合作的结果满足业务需求 &emsp;&emsp;&emsp;"   newColumn="true"></t:dgCol>
	  
  	  <t:dgCol title=""  ></t:dgCol>
	  <t:dgCol title="无拖延或错漏状况&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"    ></t:dgCol>
	  <t:dgCol title="出现问题时能主动协调解决&emsp;&emsp;"   ></t:dgCol>
	  <t:dgCol title="" ></t:dgCol>
	<!-- end -->
    <t:dgToolBar  title="保存" icon="icon-save" url="pasOnDeptController.do?saveRows" funname="saveData"></t:dgToolBar>
   <t:dgToolBar  title="更新合计分数"  icon="icon-update" url="pasOnStaffController.do?saveRows" funname="updateData"></t:dgToolBar>
   <%-- 	<t:dgToolBar  title="查看评语" icon="icon-search" url="pasOnDeptController.do?searchComment"  funname="findComment"></t:dgToolBar>--%>
 </t:datagrid>
  </div>
 </div>
 	<div id="mengban" align="center" style="background-color:white;position:absolute;z-index:99999;display:none;width:1500px;height:1200px">
			<table>
				<tr><td width=100% style="text-align:center;vertical-align:middle"><img src="plug-in/ace/assets/css/images/loading.gif"><br>数据加载中...</td></tr>
			</table>
    </div>
    
 <script type="text/javascript">
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
 
 //添加行
	function addRow(title,addurl,gname){
		$('#'+gname).datagrid('appendRow',{});
		var editIndex = $('#'+gname).datagrid('getRows').length-1;
		$('#'+gname).datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	}
 
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
			var total1 = parseInt(rows[i].professionalSkill)+parseInt(rows[i].coopEff)+parseInt(rows[i].coopAtti)+parseInt(rows[i].coopOutcome);
			//alert(total1);
			$('#'+gname).datagrid('updateRow',{index:index1,row:{totalScore:total1}});
		}
		
		for(var i=0;i<rows.length;i++){//所有行
			
			var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
			$('#'+gname).datagrid('beginEdit',index);
		}
		
	}
	//保存数据
	function saveData(title,addurl,gname){
		if(!endEdit(gname))
			return false;
		$.messager.confirm("确认", "确定要保存吗？保存后不可修改 ! ", function (r) {
	        if (r) {
	        	//关闭编辑行
	        	var  editIndex = $('#'+gname).datagrid('getRows').length-1;
	    		for(var u=0;u<=editIndex;u++){
	    			$('#'+gname).datagrid('endEdit',u);
	    		} 
				//var url = "jeecgListDemoController.do?doBatchDel&ids=" + id;
				/* $.ajax({
					url:url,
					type:"get",
					dataType:"json",
					success:function(data){
						top.tip(data.msg);
						if(data.success){							
							$("#jeecgrowList").datagrid('reload');
						}
					}
				}) */
	    		var uprows=$('#'+gname).datagrid("getChanges","updated");
	    		if(uprows.length<=0){
	    			tip("没有需要保存的数据！")
	    			return false;
	    		} 
				//创建存储总分列表
				var totalScoreList = [];
	    		//循环更新总分
	    		for(var d in uprows){
	    			var rowIndex = $('#'+gname).datagrid('getRowIndex', uprows[d]);
	    			//计算当前编辑条目的总分
	    			var prfsk_score= parseInt(uprows[d].professionalSkill);//业务水平
	    			var coop_eff = parseInt(uprows[d].coopEff);//合作效率
	    			var coop_atti = parseInt(uprows[d].coopAtti);//合作态度
	    			var coop_outcome = parseInt(uprows[d].coopOutcome);//合作结果
	    			var totalScoreVal = prfsk_score+coop_eff+coop_atti+coop_outcome;
	    			$('#'+gname).datagrid('updateRow',{index:rowIndex,row:{totalScore:totalScoreVal}});
	    			totalScoreList.push(totalScoreVal);
	    		}
	    		
 			
				//获取所有行
				var rows = $('#'+gname).datagrid("getRows");
	    		//校验业务逻辑
	    		if(!businessValid(totalScoreList,gname,rows)){
	    			//重新打开编辑行 
					$('#'+gname).datagrid('selectAll');
					//打开编辑行 
					for(var i=0;i<rows.length;i++){
						var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
						$('#'+gname).datagrid('beginEdit', index);
					}
	    			return false;
	    		}
	    		var result={};
	    		for(var i=0;i<rows.length;i++){
	    			for(var d in rows[i]){
	    				result["pasOnDeptList["+i+"]."+d]=rows[i][d];
	    			}
	    		}
	    		$.ajax({
	    			url:"<%=basePath%>/"+addurl,
	    			type:"post",
	    			data:result,
	    			dataType:"json",
	    			success:function(data){
	    				tip(data.msg);
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
	
	//业务校验 :  1.所有人得分相同视为无效  2. 合计得分出现满分视为无效
	function businessValid(data,gname,rows){
		//获取第一个总分 作为比较基数
		var base = data[0];
		var len= data.length;
		if(data.indexOf(100) != -1){
			//tip("合计得分出现满分视为无效，请重新填写！");
			alert("合计得分出现满分视为无效，请重新填写！");
			changeBackgroundColorX(gname,rows);
			return false;
		}
		//1条记录 不判断之后的逻辑
		if(len <= 1){
			return true;
		}
		//创建布尔变量 作为判断是否所有人的得分相同的标志 true表示所有人得分相同，  false表示不同
		var boflag = new Boolean(true);
		for (var i = 1; i < len; i++) {
			if(base != data[i]){
				boflag = false;
			}
		}
		if(boflag == true){
			//tip("所有人得分相同视为无效，请重新填写");
			alert("所有人得分相同视为无效，请重新填写");
			changeBackgroundColor(gname,rows);
			return false;
		}
		return true;
	}
	
	//有条件更改颜色
	function changeBackgroundColorX(gname,rows){
		var lenth= rows.length;
		for (var i = 0; i < lenth; i++) {
			if(rows[i].totalScore == 100){
				$("[datagrid-row-index='" + i + "']").children("[field=\"totalScore\"]").css({ "background-color": "#F08080" });
				return false;
			}else{
				$("[datagrid-row-index='" + i + "']").children("[field=\"totalScore\"]").css({"background-color":""});
			}
		} 
		// $("[datagrid-row-index='" + index + "']").css({ "background-color": "#F08080" });
	}
	
	
	//无条件更改颜色
	function changeBackgroundColor(gname,rows){
		var lenth= rows.length;
		for (var i = 0; i < lenth; i++) {
				$("[datagrid-row-index='" + i + "']").children("[field=\"totalScore\"]").css({ "background-color": "#F08080" });
		} 
		// $("[datagrid-row-index='" + index + "']").css({ "background-color": "#F08080" });
	}
	
	
	function loadSucessFun(data){
		showLoading();
		var gname = "pasOnDeptrowList";
		 $.ajax({
				url:"<%=basePath%>/"+"pasOnDeptController.do?checkConfirm",
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
							/* var editors = $('#'+gname).datagrid('getEditors', index); 
							//获取控件对象  动态设置必选验证box
							 commentEditor= editors[4].target;//第四列控件
							$(commentEditor).addClass("validatebox-invalid"); 
							$(commentEditor).validatebox({
							    required: true
							});
							//给所有数据行的评语输入框控件绑定事件
							commentEditor.bind("click",function(event){
								event = event || window.event; 
								var target = event.target; //这个就是触发事件的控件 
								createWindowOnComment('评语编辑', 'pasOnDeptController.do?showDialogPage&rowIndex='+index,400,180,target);
							}); */
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
			cache:false,
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
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
				var comment = rows[0].commentDept;
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
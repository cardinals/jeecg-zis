<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasAstDeptrowList" checkbox="true"  pagination="true" fitColumns="true" 
  title="部门互评(资产)" actionUrl="pasAstDeptController.do?datagrid" idField="id"  onLoadSuccess="loadSucessFun" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true" ></t:dgCol>
    <t:dgCol title="评价人"  field="appraiser"  hidden="true"  queryMode="single"></t:dgCol>
    <t:dgCol title="被评价部门"  field="goalDept"  rowspan="4" queryMode="single"   width="80"></t:dgCol>
    <t:dgCol title="部门负责人"  field="goalDeptManager"  rowspan="4" queryMode="single"    width="80"></t:dgCol>
    <t:dgCol title="<b>专业水平(该项满分30分)</b>"  field="professionalSkill" queryMode="single"  dictionary="pas_s30" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="<b>合作效率(该项满分为20分)</b>"  field="coopEff" queryMode="single"  dictionary="pas_s20" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="<b>合作态度(该项满分为20分)</b>"  field="coopAtti" queryMode="single"  dictionary="pas_s20" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="<b>合作结果(该项满分30分)</b>"  field="coopOutcome" queryMode="single"  dictionary="pas_s30" extendParams="editor:'combobox'" ></t:dgCol>
    <t:dgCol title="总分"   rowspan="4" newColumn="true" field="totalScore" queryMode="single"    width="50"></t:dgCol>
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
    <t:dgToolBar title="提交" icon="icon-save" url="pasAstDeptController.do?saveRows" funname="saveData"></t:dgToolBar>
  <t:dgToolBar  title="更新合计分数"  icon="icon-update"  funname="updateData"></t:dgToolBar>
  </t:datagrid>
  </div>
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
		//$.messager.confirm("确认", "确定要保存吗？保存后不可修改 ! ", function (r) {
	   $.messager.confirm("确认", "确定要提交吗？提交后不可修改 ! ", function (r) {
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
	    				result["pasAstDeptList["+i+"]."+d]=rows[i][d];
	    			}
	    		}
	    		$.ajax({
	    			url:"<%=basePath%>/"+addurl,
	    			type:"post",
	    			data:result,
	    			dataType:"json",
	    			success:function(data){
	    				tip("提交成功");
	    				//alert(data.msg);
	    				if(data.success){
	    					$('#'+gname).datagrid('reload');
	    					//reloadTable();
	    					 //  移除当前选项卡
	    					//$("a[data-id='pasOnStaffController.do?list'] i", parent.parent.document).click();
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
		var len= data.length ;
		if(data.indexOf(100) != -1){
			tip("合计得分出现满分视为无效，请重新填写！");
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
			tip("所有人得分相同视为无效，请重新填写");
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
		var gname = "pasAstDeptrowList";
		 $.ajax({
				url:"<%=basePath%>/"+"pasAstDeptController.do?checkConfirm",
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
					}
				}
			});
	}
	
	
	
 </script>
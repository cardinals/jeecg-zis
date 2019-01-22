<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasFuzeLeaderList" checkbox="true" pagination="true" fitColumns="false" title="部门负责人及以上人员对上级领导评分" actionUrl="pasFuzeLeaderController.do?datagrid" idField="id" fit="true" onLoadSuccess="loadSucessFun" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="被评价人"  field="goalPerson"  rowspan="5" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="职位"  field="position"  hidden="true" rowspan="5" queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="评价人"  field="appraiser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="goalPersonDept"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="<b>团队凝聚力(该项满分为25分)&emsp;&emsp;&emsp;"  field="yeji" align="center" queryMode="single"  dictionary="pas_s25" extendParams="editor:'combobox'"  width="220"></t:dgCol>
   <t:dgCol title="<b>工作推动力(该项满分为25分)&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</b>"  field="teamLead" align="center" queryMode="single"  dictionary="pas_s25" extendParams="editor:'combobox'" ></t:dgCol>
   <t:dgCol title="<b>工作指导力(该项满分为25分)&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</b>"  field="deveCreate" align="center" queryMode="single"  dictionary="pas_s25" extendParams="editor:'combobox'" ></t:dgCol>
   <t:dgCol title="<b>公平性(该项满分为25分)&emsp;</b>"  field="sincerityHardwork" align="center"  queryMode="single"  dictionary="pas_s25"  extendParams="editor:'combobox'"></t:dgCol>
   <t:dgCol title="合计"  field="totalScore" align="center" rowspan="5" newColumn="true"  queryMode="single"  width="50"></t:dgCol>
   <!-- start -->
<%--       <t:dgCol title="深刻理解公司战略规划&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="领导艺术水平高&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="对本部门的所有业务了然于心" ></t:dgCol>
	  <t:dgCol title="有大局观，立足公司整体战略部署&emsp;&emsp;"  newColumn="true" ></t:dgCol>
	 
	  <t:dgCol title="带领团队完成本年度业绩目标&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="团队富有活力和激情&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="对岗位安排、风险点都清楚&emsp;" ></t:dgCol>
	  <t:dgCol title="各项工作都围绕公司核心发展目标开展"  newColumn="true" ></t:dgCol>
	 
	  <t:dgCol title="精通所负责工作领域内的专业知识&emsp;" ></t:dgCol>
	  <t:dgCol title="知人善任，团队成员&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="遇到问题勇于承担，敢作敢当" ></t:dgCol>
	  <t:dgCol title="敬畏和尊重规则，各项制度执行到位&emsp;"  newColumn="true" ></t:dgCol>
	 
	  <t:dgCol title="有很强的专业水平和解决问题能力&emsp;" ></t:dgCol>
	  <t:dgCol title="积极性能充分发挥&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="主动协调解决问题&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
	  <t:dgCol title="有团结协作精神；乐于奉献&emsp;&emsp;&emsp;&emsp;&emsp;"  newColumn="true" ></t:dgCol>
	 --%>  
	   <t:dgCol title="领导管理水平高&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"    ></t:dgCol>
  <t:dgCol title="深刻理解公司战略规划&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"  ></t:dgCol>
   <t:dgCol title="对本部门业务、岗位安排和风险点了然于心&emsp;"    ></t:dgCol>
  <t:dgCol title="合理分配工作&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"  newColumn="true"></t:dgCol>
  
  <t:dgCol title="使团队充满热情和希望&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"   ></t:dgCol>
  <t:dgCol title="积极推动团队完成工作目标&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" ></t:dgCol>
  <t:dgCol title="能向下属提供有效的指导，帮助其提高绩效&emsp;"   ></t:dgCol>
  <t:dgCol title="充分发挥下属潜能&emsp;&emsp;&emsp;&emsp;&emsp;"   newColumn="true" ></t:dgCol>
  
  <t:dgCol title="创造坦率、温暖、合作的团队氛围&emsp;&emsp;"    ></t:dgCol>
  <t:dgCol title="在面对压力等障碍下，及时采取行动提升团队竞争力"  ></t:dgCol>
   <t:dgCol title="能及时发现问题，并指明改进方向&emsp;&emsp;&emsp;&emsp;&emsp;"    ></t:dgCol>
  <t:dgCol title="公平对待每一位下属，不偏袒"   newColumn="true"></t:dgCol>
  
  <t:dgCol title="从而提高团队战斗力&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"   ></t:dgCol>
  <t:dgCol title="主动预见潜在问题，并采取方式创造机会或避免危机" ></t:dgCol>
   <t:dgCol title="敢作敢当 ，主动协调解决问题&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"   ></t:dgCol>
  <t:dgCol title=""   width="120"></t:dgCol>
	      <t:dgToolBar  title="提交"  icon="icon-save" url="pasFuzeLeaderController.do?saveRows" funname="saveData"></t:dgToolBar>
   <t:dgToolBar  title="更新合计分数"  icon="icon-update"  funname="updateData"></t:dgToolBar>
   <!-- end -->
<%--    <t:dgCol title="确认状态"  field="confirmFlag"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pasFuzeLeaderController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pasFuzeLeaderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pasFuzeLeaderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pasFuzeLeaderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pasFuzeLeaderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%></t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/pasfuzeleader/pasFuzeLeaderList.js"></script>		
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
			var total1 = parseInt(rows[i].yeji)+parseInt(rows[i].teamLead)+parseInt(rows[i].deveCreate)+parseInt(rows[i].sincerityHardwork);
			//alert(total1);
			$('#'+gname).datagrid('updateRow',{index:index1,row:{totalScore:total1}});
		}
		
		for(var i=0;i<rows.length;i++){//所有行
			
			var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
			$('#'+gname).datagrid('beginEdit',index);
		}
		
	}
	
//start
    //添加行
	function addRow(title,addurl,gname){
		$('#'+gname).datagrid('appendRow',{});
		var editIndex = $('#'+gname).datagrid('getRows').length-1;
		$('#'+gname).datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
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
				//创建存储总分列表
				var totalScoreList = [];
	    		//循环更新总分
	    		for(var d in uprows){
	    			var rowIndex = $('#'+gname).datagrid('getRowIndex', uprows[d]);
	    			//计算当前编辑条目的总分
	    			var prfsk_score= parseInt(uprows[d].yeji);//业绩完成情况
	    			var coop_eff = parseInt(uprows[d].teamLead);//团队领导力
	    			var coop_atti = parseInt(uprows[d].deveCreate);//发展创新力
	    			var coop_outcome = parseInt(uprows[d].sincerityHardwork);//诚信敬业度
	    			var totalScoreVal = prfsk_score+coop_eff+coop_atti+coop_outcome;
	    			$('#'+gname).datagrid('updateRow',{index:rowIndex,row:{totalScore:totalScoreVal}});
	    			totalScoreList.push(totalScoreVal);
	    		}
	    		
				//获取所有行
				var rows = $('#'+gname).datagrid("getRows");
	    		//校验业务逻辑
	    		/* if(!businessValid(totalScoreList,gname,rows)){
	    			//重新打开编辑行 
					$('#'+gname).datagrid('selectAll');
					//打开编辑行 
					for(var i=0;i<rows.length;i++){
						var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
						$('#'+gname).datagrid('beginEdit', index);
					}
	    			return false;
	    		} */
	    		var result={};
	    		for(var i=0;i<rows.length;i++){
	    			for(var d in rows[i]){
	    				result["pasLeadList["+i+"]."+d]=rows[i][d];
	    			}
	    		}
	    		$.ajax({
	    			url:"<%=basePath%>/"+addurl,
	    			type:"post",
	    			data:result,
	    			dataType:"json",
	    			success:function(data){
	    				tip("提交成功");m
	    				if(data.success){
	    					$('#'+gname).datagrid('reload');
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
		var gname = "pasFuzeLeaderList";
		 $.ajax({
				url:"<%=basePath%>/"+"pasFuzeLeaderController.do?checkConfirm",
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

//end
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pasFuzeLeaderController.do?upload', "pasFuzeLeaderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pasFuzeLeaderController.do?exportXls","pasFuzeLeaderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pasFuzeLeaderController.do?exportXlsByT","pasFuzeLeaderList");
}

 </script>
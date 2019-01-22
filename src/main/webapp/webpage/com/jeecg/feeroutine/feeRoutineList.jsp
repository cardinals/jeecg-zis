<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="feeRoutineList" checkbox="true" pagination="true" fitColumns="true" title="部门日常费用" actionUrl="feeRoutineController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="报销日期"  field="feeDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amount"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="收款方"  field="payee"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="付款说明明细"  field="description"  query="true"  queryMode="single"  width="180"></t:dgCol>
   <t:dgCol title="费用类型"  field="feeType"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用性质"  field="feeXingzhi"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发票"  field="feeFapiao"  query="true"  queryMode="single"  dictionary="FPYN"  width="120"></t:dgCol>
   <t:dgCol title="经办人"  field="feeJingbanren"  query="true"  queryMode="single"  width="120" dictionary="user" ></t:dgCol>
   <t:dgCol title="updateBy"  field="updateby"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="updateDate"  field="updatedate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="updateName"  field="updatename"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="feeRoutineController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="feeRoutineController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="feeRoutineController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="feeRoutineController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="feeRoutineController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
     	<t:dgToolBar title="复制一行" icon="icon-edit"  url="feeRoutineController.do?doLine_copy" funname="doLine_copy" ></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/feeroutine/feeRoutineList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
     	//自定义按钮-复制一行
	 	function doLine_copy(title,url,gridname){
	 		var rowData = $('#'+gridname).datagrid('getSelected');
			if (!rowData) {
				tip('请选择复制一行项目');
				return;
			}
			url = url+"&id="+rowData['id'];
	 		createdialog('确认 ', '确定'+title+'吗 ?', url,gridname);
	 	}
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'feeRoutineController.do?upload', "feeRoutineList");
}

//导出
function ExportXls() {
	JeecgExcelExport("feeRoutineController.do?exportXls","feeRoutineList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("feeRoutineController.do?exportXlsByT","feeRoutineList");
}

 </script>
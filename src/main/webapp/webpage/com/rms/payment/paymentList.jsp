<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="paymentList" checkbox="true" pagination="true" fitColumns="true" title="RMS_付款管理" actionUrl="paymentController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="付款编号"  field="paymentId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同ID"  field="contractId" hidden="true"  queryMode="group"    width="120"></t:dgCol>
   <t:dgCol title="综合编号"  field="contractInfo" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同名称"  field="contractName"  queryMode="single"    width="120"></t:dgCol>
   <t:dgCol title="报销单号"  field="reimburseNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="付款事由"  field="description"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="付款人"  field="paidBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="付款金额"  field="paymentAmount"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="付款时间"  field="paymentDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后更新时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="isreimbursed"  field="isreimbursed"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="报销金额"  field="reimburseAmount"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="paymentController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  <%--  <t:dgToolBar title="录入" icon="icon-add" url="paymentController.do?goAdd" funname="add"></t:dgToolBar>
   --%> <t:dgToolBar title="编辑" icon="icon-edit" url="paymentController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="paymentController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="paymentController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/payment/paymentList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'paymentController.do?upload', "paymentList");
}

//导出
function ExportXls() {
	JeecgExcelExport("paymentController.do?exportXls","paymentList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("paymentController.do?exportXlsByT","paymentList");
}

 </script>
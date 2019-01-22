<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pfHisManagerList" checkbox="false" pagination="true" fitColumns="true" title="历任基金经理" actionUrl="pfHisManagerController.do?datagridHis&paramId=${HisManagerPage.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文姓名"  field="nameZh" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="startTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="emdTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始"  field="startString" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束"  field="endString" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外键"  field="foreignKey" hidden="true"  queryMode="single"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
	
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pfHisManagerController.do?upload', "pfHisManagerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pfHisManagerController.do?exportXls","pfHisManagerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pfHisManagerController.do?exportXlsByT","pfHisManagerList");
}

 </script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pfHisManagerList" checkbox="true" pagination="true" fitColumns="true" title="历任基金经理" actionUrl="pfHisManagerController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中文姓名"  field="nameZh"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="startTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="emdTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始"  field="startString"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束"  field="endString"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外键"  field="foreignKey"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pfHisManagerController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pfHisManagerController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pfHisManagerController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pfHisManagerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pfHisManagerController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/pfhismanager/pfHisManagerList.js"></script>		
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasModAttachedList" checkbox="true" pagination="true" fitColumns="true" title="绩效考评特殊人员附加表" actionUrl="pasModAttachedController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="username"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="真实姓名"  field="realname"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="角色代码"  field="rolecode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="角色名称"  field="rolename"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="部门代码"  field="orgCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="部门名称"  field="departName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pasModAttachedController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pasModAttachedController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pasModAttachedController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pasModAttachedController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pasModAttachedController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/pasmodattached/pasModAttachedList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pasModAttachedController.do?upload', "pasModAttachedList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pasModAttachedController.do?exportXls","pasModAttachedList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pasModAttachedController.do?exportXlsByT","pasModAttachedList");
}

 </script>
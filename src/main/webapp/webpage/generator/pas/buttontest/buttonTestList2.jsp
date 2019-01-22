<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="buttonTestList" checkbox="false" pagination="true" fitColumns="true" title="按钮表格" actionUrl="buttonTestController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"   formatter="yyyy-MM-dd"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"formatter="yyyy-MM-dd"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年纪"  field="age"   queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="buttonTestController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="buttonTestController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="buttonTestController.do?goUpdate" funname="update"></t:dgToolBar>
     <t:dgToolBar title="批量删除"  icon="icon-remove" url="buttonTestController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="buttonTestController.do?goUpdate" funname="detail"></t:dgToolBar>
 <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/buttontest/buttonTestList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'buttonTestController.do?upload', "buttonTestList");
}

//导出
function ExportXls() {
	JeecgExcelExport("buttonTestController.do?exportXls","buttonTestList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("buttonTestController.do?exportXlsByT","buttonTestList");
}

 </script>
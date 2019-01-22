<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="rmsEmployJeecgList" checkbox="true" pagination="true" fitColumns="true" title="RMS_员工管理" actionUrl="rmsEmployJeecgController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="主键"  field="employId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="员工编号"  field="employNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="employName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="departmentId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否在职"  field="enable"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮箱"  field="emali"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="在职状态"  field="status"  hidden="true"  queryMode="single"  width="120" dictionary="01NY"></t:dgCol>
   <t:dgCol title="名下资产"  field="assetId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="rmsEmployJeecgController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="rmsEmployJeecgController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="rmsEmployJeecgController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="rmsEmployJeecgController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="rmsEmployJeecgController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  --%> </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/employ/rmsEmployJeecgList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'rmsEmployJeecgController.do?upload', "rmsEmployJeecgList");
}

//导出
function ExportXls() {
	JeecgExcelExport("rmsEmployJeecgController.do?exportXls","rmsEmployJeecgList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("rmsEmployJeecgController.do?exportXlsByT","rmsEmployJeecgList");
}

 </script>
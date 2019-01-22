<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="databaseList" checkbox="false" pagination="true" fitColumns="true" title="RMS_数据库管理" actionUrl="databaseController.do?datagridForData&paramId=${serverPage.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="databaseId"  field="databaseId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所在设备"  field="serverId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="OS"  field="os"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="IP"  field="ip"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="dbType"  hidden="true"  query="false"  queryMode="single"  dictionary="dbTypes"  width="120"></t:dgCol>
   <t:dgCol title="版本号"  field="version"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true"  query="false"  queryMode="single"  dictionary="applstate"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="databaseName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="SID"  field="sid"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="RACHA"  field="racorha"  hidden="true"  query="false"  queryMode="single"  dictionary="racha"  width="120"></t:dgCol>
   <t:dgCol title="管理员"  field="administrator"  query="false"  queryMode="single"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="上线时间"  field="launchDate"  formatter="yyyy-MM-dd"  query="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后更改时间 "  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="databaseController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="databaseController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="databaseController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="databaseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="databaseController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%></t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/database/databaseList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'databaseController.do?upload', "databaseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("databaseController.do?exportXls","databaseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("databaseController.do?exportXlsByT","databaseList");
}

 </script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="rmsHardwareList" checkbox="true" pagination="true" fitColumns="true" title="rms_hardware" actionUrl="rmsHardwareController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="maintype"  field="maintype"  hidden="true"  query="true"  queryMode="single"  dictionary="servertype"  width="120"></t:dgCol>
   <t:dgCol title="status"  field="status"  hidden="true"  query="true"  queryMode="single"  dictionary="status"  width="120"></t:dgCol>
   <t:dgCol title="location"  field="location"  hidden="true"  query="true"  queryMode="single"  dictionary="location"  width="120"></t:dgCol>
   <t:dgCol title="position"  field="position"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="hardwareType"  field="hardwareType"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="hardwareNo"  field="hardwareNo"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="remoteIlo"  field="remoteIlo"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="purchaseDate"  field="purchaseDate"  formatter="yyyy-MM-dd"  hidden="true"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="vendor"  field="vendor"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="warrantyDate"  field="warrantyDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="maintainDate"  field="maintainDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="warrantyVendor"  field="warrantyVendor"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="remark1"  field="remark1"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="remark2"  field="remark2"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="createTime"  field="createTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="lastUpdateTime"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="applicant"  field="applicant"  query="true"  queryMode="single"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="createdBy"  field="createdBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="updatedBy"  field="updatedBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="agent"  field="agent"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="name"  field="name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="rmsHardwareController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="rmsHardwareController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="rmsHardwareController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="rmsHardwareController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="rmsHardwareController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/rms_hardware/rmsHardwareList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'rmsHardwareController.do?upload', "rmsHardwareList");
}

//导出
function ExportXls() {
	JeecgExcelExport("rmsHardwareController.do?exportXls","rmsHardwareList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("rmsHardwareController.do?exportXlsByT","rmsHardwareList");
}

 </script>
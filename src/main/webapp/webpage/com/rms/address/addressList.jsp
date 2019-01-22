<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="addressList" checkbox="true" pagination="true" fitColumns="true" title="RMS_地址管理" actionUrl="addressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="addressId"  field="addressId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="全称"  field="addrdetails"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="简称"  field="addrshort"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contact"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="phone"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="addressController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="addressController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="addressController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="addressController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="addressController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/address/addressList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'addressController.do?upload', "addressList");
}

//导出
function ExportXls() {
	JeecgExcelExport("addressController.do?exportXls","addressList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("addressController.do?exportXlsByT","addressList");
}

 </script>
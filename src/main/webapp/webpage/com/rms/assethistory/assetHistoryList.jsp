<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="assetHistoryList" checkbox="true" pagination="true" fitColumns="true" title="RMS_历史资产表" actionUrl="assetHistoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="历史记录ID"  field="assetHistoryId"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作"  field="operation"  queryMode="group"  dictionary="assetOpera"  width="120"></t:dgCol>
   <t:dgCol title="唯一键"  field="assetId"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="公司"  field="company"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="资产类型"  field="maintype"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="资产编号"  field="assetNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地区"  field="location"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="vendor"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="品牌型号"  field="assetType"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="设备参数"  field="config"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电脑型号"  field="pcType"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="购置日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="购置单价"  field="price"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后修改时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="使用人"  field="employeeId"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updatedBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="操作人"  field="operationBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="assetHistoryController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="assetHistoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="assetHistoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="assetHistoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="assetHistoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/assethistory/assetHistoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'assetHistoryController.do?upload', "assetHistoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("assetHistoryController.do?exportXls","assetHistoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("assetHistoryController.do?exportXlsByT","assetHistoryList");
}

 </script>
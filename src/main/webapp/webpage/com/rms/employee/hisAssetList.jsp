<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="assetHistoryList" checkbox="false" pagination="true" fitColumns="true" title="RMS_历史资产表" actionUrl="assetHistoryController.do?datagrid2&paramId=${employeePage.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="历史记录ID"  field="assetHistoryId"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作"  field="operation"  queryMode="group"  dictionary="assetOpera"  width="120"></t:dgCol>
   
   <t:dgCol title="公司"  field="company"  queryMode="group"  width="120"></t:dgCol> --%>
   
   <t:dgCol title="唯一键"  field="assetId" hidden="true"  width="120"></t:dgCol>
   
	<t:dgCol title="资产类型"  field="maintype"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="资产编号"  field="assetNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="品牌"  field="vendor"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="类型"  field="assetType"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="设备参数"  field="config"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="购置日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="当前使用人"  field="CurrentPeople"  width="120"></t:dgCol>
	<%--  <t:dgCol title="购置单价"  field="price"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后修改时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="使用人"  field="employeeId"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updatedBy"  queryMode="group"  dictionary="user"  width="120"></t:dgCol> --%>
  
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="rmsApplicationJeecgList" checkbox="false" pagination="true" fitColumns="true" title="应用管理" actionUrl="rmsApplicationJeecgController.do?datagridForApply&paramId=${serverPage.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="应用编号"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="中文名称"  field="applicationNameCn"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="英文名称"  field="applicationName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所在设备"  field="container" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="OS"  field="serverId2" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="ip"  field="ip" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="appType" hidden="true"  query="false"  queryMode="single"  dictionary="appliType"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status" hidden="true"  query="false"  queryMode="single"  dictionary="applstate"  width="120"></t:dgCol>
   <t:dgCol title="应用描述"  field="appInfo" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="管理员"  field="administrator" dictionary="user" query="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上线日期"  field="launchDate"  formatter="yyyy-MM-dd"  query="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1" hidden="true"  queryMode="group"  width="200"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后修改时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="rmsApplicationJeecgController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="rmsApplicationJeecgController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="rmsApplicationJeecgController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="rmsApplicationJeecgController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="rmsApplicationJeecgController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/rms/application/rmsApplicationJeecgList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'rmsApplicationJeecgController.do?upload', "rmsApplicationJeecgList");
}

//导出
function ExportXls() {
	JeecgExcelExport("rmsApplicationJeecgController.do?exportXls","rmsApplicationJeecgList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("rmsApplicationJeecgController.do?exportXlsByT","rmsApplicationJeecgList");
}

 </script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="raiseAccountUsetempList" checkbox="true" pagination="true" fitColumns="true" title="产品变化临时表" actionUrl="raiseAccountUsetempController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行账号"  field="yinhangAccount"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行户名"  field="yinhangName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="开户银行全称"  field="yinhangNamefull"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="曾用产品"  field="preProject"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="结息状态"  field="jiexiStatus"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="结息状态2"  field="jiexiStatus2"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="外键"  field="foreignKey"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="raiseAccountUsetempController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="raiseAccountUsetempController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="raiseAccountUsetempController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="raiseAccountUsetempController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="raiseAccountUsetempController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/raiseaccountusetemp/raiseAccountUsetempList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'raiseAccountUsetempController.do?upload', "raiseAccountUsetempList");
}

//导出
function ExportXls() {
	JeecgExcelExport("raiseAccountUsetempController.do?exportXls","raiseAccountUsetempList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("raiseAccountUsetempController.do?exportXlsByT","raiseAccountUsetempList");
}

 </script>
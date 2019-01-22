<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSSysParamsList" checkbox="false" pagination="true" fitColumns="true" title="系统参数" actionUrl="tSSysParamsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模块名称"  field="cClass"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参数名称"  field="citem"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参数值"  field="cValue"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否修改"  field="cModify"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参数描述"  field="cDescribe"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标志"  field="cCryptflag"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标志位"  field="cType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="取值范围"  field="cValuebound"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类别"  field="cShowclass"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="子类"  field="cShowsubclass"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tSSysParamsController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tSSysParamsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tSSysParamsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tSSysParamsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tSSysParamsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/sysparams/tSSysParamsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tSSysParamsController.do?upload', "tSSysParamsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tSSysParamsController.do?exportXls","tSSysParamsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tSSysParamsController.do?exportXlsByT","tSSysParamsList");
}

 </script>
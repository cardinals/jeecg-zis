<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="departmentFeeList" checkbox="true" pagination="true" fitColumns="true" title="部门支出" actionUrl="departmentFeeController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="日期"  field="feeDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="划款金额"  field="feeAmount"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="划款说明"  field="description"  query="true"  queryMode="single"  width="180"></t:dgCol>
   <t:dgCol title="费用类型"  field="feeType"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用性质"  field="feeXingzhi"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用发票"  field="feeFapiao"  query="true"  queryMode="single"  dictionary="FPYN"   width="120"></t:dgCol>
   <t:dgCol title="费用经办人"  field="feeJingbanren"  query="true"  queryMode="single"  width="120" dictionary="user" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="departmentFeeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="departmentFeeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="departmentFeeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="departmentFeeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="departmentFeeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/departmentfee/departmentFeeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'departmentFeeController.do?upload', "departmentFeeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("departmentFeeController.do?exportXls","departmentFeeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("departmentFeeController.do?exportXlsByT","departmentFeeList");
}

 </script>
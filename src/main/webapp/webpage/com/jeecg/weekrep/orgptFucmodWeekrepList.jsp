<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid  name="orgptFucmodWeekrepList" checkbox="false" pagination="true" fitColumns="true" title="周报" actionUrl="orgptFucmodWeekrepController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="员工姓名"  field="staffname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="拜访日期"  field="visitdate"  formatter="yyyy-MM-dd"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="本周拜访机构"  field="targetorginwk"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否准入"  field="accessflag"  queryMode="single"  dictionary="01NY"  width="120"></t:dgCol>
   <t:dgCol title="准入类型"  field="accesstype"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="部门"  field="department"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对接人"  field="djperson"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对接人职位"  field="djpersonjob"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务合作点"  field="cooperationpoint"  queryMode="single"  dictionary="cooperapo"  width="120"></t:dgCol>
   <t:dgCol title="跟进情况"  field="followsituation"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户需求"  field="customerdemand"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="投资概况"  field="investsituation"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮标"  field="mailsign"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="核心人员"  field="corestaff"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="核心人员职位"  field="corestaffpos"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="需要支持事项"  field="needsupitems"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="批注"  field="procomment"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" operationCode="delete" url="orgptFucmodWeekrepController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入"  icon="icon-add" url="orgptFucmodWeekrepController.do?goAdd" funname="add" operationCode="adds"></t:dgToolBar>
   <t:dgToolBar title="编辑" operationCode="update" icon="icon-edit" url="orgptFucmodWeekrepController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  operationCode="allDelete" icon="icon-remove" url="orgptFucmodWeekrepController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="orgptFucmodWeekrepController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
		<t:dgToolBar title="批注" icon="icon-edit" funname="doPizhu" ></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/com/jeecg/weekrep/orgptFucmodWeekrepList.js"></script>		
 
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
		//自定义按钮-批注
	 	function doPizhu(title,url,gridname){
	 		pizhu();

	 	}
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'orgptFucmodWeekrepController.do?upload', "orgptFucmodWeekrepList");
}

//导出
function ExportXls() {
	JeecgExcelExport("orgptFucmodWeekrepController.do?exportXls","orgptFucmodWeekrepList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("orgptFucmodWeekrepController.do?exportXlsByT","orgptFucmodWeekrepList");
}

 </script>
   <t:authFilter/>
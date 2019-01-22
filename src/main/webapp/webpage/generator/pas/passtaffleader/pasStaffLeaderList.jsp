<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasStaffLeaderList" checkbox="true" pagination="true" fitColumns="true" title="PAS_员工对部门负责人评分2.0" actionUrl="pasStaffLeaderController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="被评价人"  field="goalPerson"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评价人"  field="appraiser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="dept"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="专业水平"  field="professionalSkill"  queryMode="single"  dictionary="pas_s25"  width="120"></t:dgCol>
   <t:dgCol title="团队建设"  field="teamBuild"  queryMode="single"  dictionary="pas_s25"  width="120"></t:dgCol>
   <t:dgCol title="责任意识"  field="zeren"  queryMode="single"  dictionary="pas_s25"  width="120"></t:dgCol>
   <t:dgCol title="全局意识"  field="quanju"  queryMode="single"  dictionary="pas_s25"  width="120"></t:dgCol>
   <t:dgCol title="合计"  field="totalScore"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pasStaffLeaderController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pasStaffLeaderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pasStaffLeaderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pasStaffLeaderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pasStaffLeaderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/passtaffleader/pasStaffLeaderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pasStaffLeaderController.do?upload', "pasStaffLeaderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pasStaffLeaderController.do?exportXls","pasStaffLeaderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pasStaffLeaderController.do?exportXlsByT","pasStaffLeaderList");
}

 </script>
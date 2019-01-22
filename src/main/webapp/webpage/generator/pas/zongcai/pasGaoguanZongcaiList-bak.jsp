<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasGaoguanZongcaiList" checkbox="true" pagination="true" fitColumns="true" title="副总裁对上级评价" actionUrl="pasGaoguanZongcaiController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="职位"  field="position"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发展创新力"  field="deveCreate"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="评价人"  field="appraiser"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合计"  field="totalScore"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="被评价人"  field="goalPerson"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="诚信敬业度"  field="sincerityHardwork"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="goalPersonDept"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="业绩完成情况"  field="yeji"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="团队领导力"  field="teamLead"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="确认状态"  field="confirmFlag"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pasGaoguanZongcaiController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pasGaoguanZongcaiController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pasGaoguanZongcaiController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pasGaoguanZongcaiController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pasGaoguanZongcaiController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/zongcai/pasGaoguanZongcaiList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pasGaoguanZongcaiController.do?upload', "pasGaoguanZongcaiList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pasGaoguanZongcaiController.do?exportXls","pasGaoguanZongcaiList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pasGaoguanZongcaiController.do?exportXlsByT","pasGaoguanZongcaiList");
}

 </script>
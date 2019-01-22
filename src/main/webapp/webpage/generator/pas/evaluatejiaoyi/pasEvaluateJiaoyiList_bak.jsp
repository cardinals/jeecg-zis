<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pasEvaluateJiaoyiList" checkbox="false" pagination="true" fitColumns="true" title="2018年度交易支持满意度评价表" actionUrl="pasEvaluateJiaoyiController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评分人"  field="appraiser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="被评分人"  field="goalPerson"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="被评价部门"  field="goalDepart"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易指令完成度"  field="professionalSkill"  queryMode="single"  dictionary="pas_s30"  width="120"></t:dgCol>
   <t:dgCol title="指令执行效率"  field="coopAtti"  queryMode="single"  dictionary="pas_s30"  width="120"></t:dgCol>
   <t:dgCol title="交易技能"  field="jiaoyiSkill"  queryMode="single"  dictionary="pas_s20"  width="120"></t:dgCol>
   <t:dgCol title="信息沟通"  field="talkMessage"  queryMode="single"  dictionary="pas_s20"  width="120"></t:dgCol>
   <t:dgCol title="总分"  field="totalScore"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评分说明"  field="comment"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pasEvaluateJiaoyiController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pasEvaluateJiaoyiController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pasEvaluateJiaoyiController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pasEvaluateJiaoyiController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pasEvaluateJiaoyiController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/generator/pas/evaluatejiaoyi/pasEvaluateJiaoyiList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pasEvaluateJiaoyiController.do?upload', "pasEvaluateJiaoyiList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pasEvaluateJiaoyiController.do?exportXls","pasEvaluateJiaoyiList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pasEvaluateJiaoyiController.do?exportXlsByT","pasEvaluateJiaoyiList");
}

 </script>
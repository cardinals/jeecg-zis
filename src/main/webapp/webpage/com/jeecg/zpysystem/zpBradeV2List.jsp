<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zpBradeV2List" checkbox="false" pagination="true" fitColumns="true" title="面试评分表" actionUrl="zpBradeV2Controller.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="简历ID"  field="resumeId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="resumeName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第一轮面试成绩"  field="grade1"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第二轮面试成绩"  field="grade2"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="录用状态"  field="jpStatus"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zpBradeV2Controller.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zpBradeV2Controller.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zpBradeV2Controller.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zpBradeV2Controller.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zpBradeV2Controller.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
		<t:dgToolBar title="技术面试评分" icon="icon-edit" funname="doJishu"></t:dgToolBar>
		<t:dgToolBar title="综合面试评分" icon="icon-edit" funname="doZonghe"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/zpysystem/zpBradeV2List.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
		//自定义按钮-技术面试评分
	 	function doJishu(title,url,gridname){
	 		jishu();
	 	}
		//自定义按钮-综合面试评分
	 	function doZonghe(title,url,gridname){
	 		zonghe();
	 	}
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zpBradeV2Controller.do?upload', "zpBradeV2List");
}

//导出
function ExportXls() {
	JeecgExcelExport("zpBradeV2Controller.do?exportXls","zpBradeV2List");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zpBradeV2Controller.do?exportXlsByT","zpBradeV2List");
}

 </script>
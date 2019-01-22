<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="employeeList" checkbox="false" pagination="true" fitColumns="true" title="RMS_OA系统员工表" actionUrl="employeeController.do?datagrid" idField="id" fit="true" queryMode="group" extendParams="onRowContextMenu:onRowContextMenu">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="员工ID"  field="employeeId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="员工编号"  field="employeeNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="employeeName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="department" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否在职"  field="enable" query="true" dictionary="workStatus" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮箱"  field="emali"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="在职状态"  field="status" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名下资产"  field="assetId" hidden="true" queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="employeeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="employeeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="employeeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="employeeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="employeeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  --%> </t:datagrid>
  </div>
 </div>
 
     <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkCurrentAsset()" data-options="iconCls:'icon-search'">查看当前资产</div>
 <div onClick="checkHisAsset()" data-options="iconCls:'icon-search'">查看历史资产 </div>

</div>

 <script src = "webpage/com/rms/employee/employeeList.js"></script>		
 <script type="text/javascript">
 
 
 
//添加右击菜单内容
 var globalRowIndex = null;
 function onRowContextMenu(e,rowIndex, rowData){
 	 globalRowIndex = rowIndex;
 	 e.preventDefault();
 	 var gname = "employeeList";
    var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
     $('#mm').menu('show', {
         left:e.pageX,
         top:e.pageY
     });        
     return false;
 }
//==============1.查看当前资产

 function checkCurrentAsset(){
 	 var gname = "employeeList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add_nosave('当前资产信息', 'employeeController.do?goCurrentAsset&id='+id, "employeeList",780,360);
 }
 
//==============1.查看历史资产信息

 function checkHisAsset(){
 	 var gname = "employeeList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add_nosave('历史资产信息', 'employeeController.do?goHisAsset&id='+id, "employeeList",780,360);
 }
 
 
 
 
 
 
 
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'employeeController.do?upload', "employeeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("employeeController.do?exportXls","employeeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("employeeController.do?exportXlsByT","employeeList");
}

 </script>
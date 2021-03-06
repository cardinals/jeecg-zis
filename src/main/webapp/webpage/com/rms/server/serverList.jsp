<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="serverList" checkbox="true" pagination="true" fitColumns="true" title="OS信息" actionUrl="serverController.do?datagrid" idField="id" fit="true" queryMode="group" extendParams="onRowContextMenu:onRowContextMenu">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="OS名称"  field="serverName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="ip信息"  field="ip"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  query="true"  queryMode="single"  dictionary="oststa"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="osType"  query="true"  queryMode="single"  dictionary="ostype"  width="120"></t:dgCol>
   <t:dgCol title="虚拟机"  field="virtural"  queryMode="group"  dictionary="01NY"  width="120"></t:dgCol>
   <t:dgCol title="系统版本号"  field="osVersion"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="配置信息"  field="configInfo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="管理员"  field="administrator"  query="true" dictionary="user" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="innerIp"  field="innerIp"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="outerIp"  field="outerIp"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后修改时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上层容器"  field="vcontainerid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="申请人"  field="applicant"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="硬件上层容器"  field="container" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="服务器管理员"  field="manageIp"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所在硬件"  field="hardwareId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="虚拟上层容器"  field="vcontainer" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="serverController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="serverController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="serverController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="serverController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="serverController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkApplication()" data-options="iconCls:'icon-search'">应用信息</div>
 <div onClick="checkDatabase()" data-options="iconCls:'icon-search'">数据库信息</div> 
</div>
 <script src = "webpage/com/rms/Server/serverList.js"></script>		
 <script type="text/javascript">
 
//添加右击菜单内容
 var globalRowIndex = null;
 function onRowContextMenu(e,rowIndex, rowData){
	 globalRowIndex = rowIndex;
	 e.preventDefault();
	 var gname = "serverList";
    var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
     $('#mm').menu('show', {
         left:e.pageX,
         top:e.pageY
     });        
     return false;
 }
 
 //查看应用信息
 function checkApplication(){
	 var gname = "serverList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);//serverId
     add_nosave('应用信息列表', 'serverController.do?goApplication&id='+id, "serverList",600,300);
 }
 
 //查看数据库信息
 function checkDatabase(){
	 var gname = "serverList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);//serverId
     add_nosave('数据库信息列表', 'serverController.do?goDatabase&id='+id, "serverList",600,300);
 }
 
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'serverController.do?upload', "serverList");
}

//导出
function ExportXls() {
	JeecgExcelExport("serverController.do?exportXls","serverList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("serverController.do?exportXlsByT","serverList");
}

 </script>
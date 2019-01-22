<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hardwareList" checkbox="true" pagination="true" fitColumns="true" title="RMS_设备管理" actionUrl="hardwareController.do?datagrid" idField="id" fit="true" queryMode="group" extendParams="onRowContextMenu:onRowContextMenu">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="maintype"  query="true"  queryMode="single"  dictionary="sblx"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  query="true"  queryMode="single"  dictionary="hwst"  width="120"></t:dgCol>
   <t:dgCol title="设备名称"  field="name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="序列号"  field="hardwareNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="厂商"  field="vendor"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="型号"  field="hardwareType"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="location"  query="true"  queryMode="single"  dictionary="sbdz"  width="120"></t:dgCol>
   <t:dgCol title="机柜位"  field="position"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="设备申请人"  field="applicant"  query="true"  queryMode="single"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="采购日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="保修日期"  field="warrantyDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最近维修日期"  field="maintainDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="远程管理IP"  field="remoteIlo"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="保修厂商"  field="warrantyVendor"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后保修时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="代理人"  field="agent"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="设备编号"  field="hardwareId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="hardwareController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="hardwareController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="hardwareController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="hardwareController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="hardwareController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
 <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkOS()" data-options="iconCls:'icon-search'">查看OS信息</div>
<!--  <div onClick="editHardware()" data-options="iconCls:'icon-edit'">编辑</div> -->
    <!-- <div onClick="view()" data-options="iconCls:'icon-search'">查看</div>
    <div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
    <div onClick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
    <div onClick="del()" data-options="iconCls:'icon-remove'">删除</div>
    <div class="menu-sep"></div>
    <div onClick="print()" data-options="iconCls:'icon-print'">打印</div>
    <div onClick="reload()" data-options="iconCls:'icon-reload'">刷新</div> -->
</div>
 <script src = "webpage/com/rms/hardware/hardwareList.js"></script>		
 <script type="text/javascript">
 
//添加右击菜单内容
 var globalRowIndex = null;
 function onRowContextMenu(e,rowIndex, rowData){
	 globalRowIndex = rowIndex;
	 e.preventDefault();
	 var gname = "hardwareList";
    var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
     $('#mm').menu('show', {
         left:e.pageX,
         top:e.pageY
     });        
     return false;
 }
 
 
 
 $(document).ready(function(){
 });
 
 //查看os信息
 function checkOS(){
	 var gname = "hardwareList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add_nosave('OS信息列表', 'hardwareController.do?goOS&id='+id, "hardwareList",600,300);
 }
 
 function editHardware(){
	 var gname = "hardwareList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     alert(id);
     update('硬件信息', 'hardwareController.do?goUpdate'+id, "hardwareList");
}

 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hardwareController.do?upload', "hardwareList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hardwareController.do?exportXls","hardwareList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hardwareController.do?exportXlsByT","hardwareList");
}

 </script>
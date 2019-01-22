<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="assetList" checkbox="false" pagination="true" fitColumns="false" title="RMS_资产管理" actionUrl="assetController.do?datagrid2&paramId=${employeePage.id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="assetId"  field="assetId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   
   
   
   <t:dgCol title="资产类型"  field="maintype"   queryMode="single"  dictionary="assets"  width="120"></t:dgCol>
   <t:dgCol title="资产编号"  field="assetNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="品牌"  field="vendor"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="assetType"   width="120"></t:dgCol>
   <t:dgCol title="设备参数"  field="config"    width="120"></t:dgCol>
   <t:dgCol title="购置日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  width="120"></t:dgCol>
  
<%--    <t:dgCol title="电脑型号"  field="pcType"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电脑序列号"  field="pcNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电源序列号"  field="powerNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电池序列号"  field="batteryNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="有线Mac"  field="mac"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="无线Mac"  field="wifiMac"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="购置日期"  field="purchaseDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="保修年限"  field="warrantyPeriod"  queryMode="group"  dictionary="repairTime"  width="120"></t:dgCol>
   <t:dgCol title="购置单价"  field="price"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true"  query="true"  queryMode="single"  dictionary="zichanst"  width="120"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后修改时间"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="库房描述"  field="storehouse"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新时间"  field="updateTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
 --%>
  </t:datagrid>
  </div>
 </div>

    <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkHisAsset()" data-options="iconCls:'icon-search'">查看历史记录</div>
 <div onClick=" allotStaff()" data-options="iconCls:'icon-edit'">分配</div>
 <div onClick="retrieveAsset()" data-options="iconCls:'icon-edit'">回收</div> 
  <div onClick="discardAsset()" data-options="iconCls:'icon-edit'">废弃</div> 
</div>
 <script src = "webpage/com/rms/asset/assetList.js"></script>		
 <script type="text/javascript">
 
 
 
//添加右击菜单内容
 var globalRowIndex = null;
 function onRowContextMenu(e,rowIndex, rowData){
 	 globalRowIndex = rowIndex;
 	 e.preventDefault();
 	 var gname = "assetList";
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
 
//==============1.查看资产分配的历史记录

 function checkHisAsset(){
 	 var gname = "assetList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add_nosave('历史记录', 'assetController.do?goHisAsset&id='+id, "assetList",600,300);
 }

//=============2.资产分配

 function allotStaff(){
 	 var gname = "assetList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add('资产分配', 'assetController.do?goAllotStaff&id='+id, "assetList",300,300);
 }

//=============3.资产回收

 function retrieveAsset(){
 	 var gname = "assetList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add('资产回收', 'assetController.do?goRetrieveAsset&id='+id, "assetList",600,150);
 }
 
//=============4.资产废弃
 function discardAsset(){
	 
	 var gname = "assetList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add('废弃', 'assetController.do?goDiscardAsset&id='+id, "assetList",600,150);
 
     
 }
    
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'assetController.do?upload', "assetList");
}

//导出
function ExportXls() {
	JeecgExcelExport("assetController.do?exportXls","assetList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("assetController.do?exportXlsByT","assetList");
}

 </script>
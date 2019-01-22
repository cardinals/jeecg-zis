<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="contractList" checkbox="true" pagination="true" fitColumns="true" title="RMS_合同管理" actionUrl="contractController.do?datagrid" idField="id" fit="true" queryMode="group" extendParams="onRowContextMenu:onRowContextMenu">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同编号"  field="contractId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="综合编号"  field="contractNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同名称"  field="name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同描述"  field="description"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合作方"  field="vendor"  query="true"  queryMode="single"  dictionary="rms_vendor_jeecg,VENDOR_ID,vendor_name"  width="120"></t:dgCol>
   <t:dgCol title="预算年份"  field="budgetYear"  queryMode="group"  dictionary="budgetyear"  width="120"></t:dgCol>
   <t:dgCol title="总额"  field="totalAmount"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="已付款"  field="paidAmount"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="签订人"  field="contractedBy"  query="true"  queryMode="single"  dictionary="user"  width="120"></t:dgCol>
   <t:dgCol title="签订日期"  field="contractDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同到期日"  field="expireDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="附件"  field="attachments"  queryMode="group"  downloadName="附件下载"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最后更新日期"  field="lastUpdateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="vendorName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark1"  hidden="true"  queryMode="group"  width="250"></t:dgCol>
   <t:dgCol title="备注2"  field="remark2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createdBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人"  field="updatedBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="代理人"  field="agent"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同文件"  field="contractFile"  hidden="true"  queryMode="group"  downloadName="附件下载"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="contractController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="contractController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="contractController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="contractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="contractController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
   <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkPayment()" data-options="iconCls:'icon-search'">付款信息</div>
 <div onClick="addPayment()" data-options="iconCls:'icon-add'">增加付款</div>
 <div onClick="setProxy()" data-options="iconCls:'icon-add'">设置代理人</div> 
</div>
 <script src = "webpage/com/rms/contract/contractList.js"></script>		
 <script type="text/javascript">
 
 
//添加右击菜单内容
var globalRowIndex = null;
function onRowContextMenu(e,rowIndex, rowData){
	 globalRowIndex = rowIndex;
	 e.preventDefault();
	 var gname = "contractList";
   var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
    selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
    $('#mm').menu('show', {
        left:e.pageX,
        top:e.pageY
    });        
    return false;
}
//查看付款信息

function checkPayment(){
	 var gname = "contractList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
    var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
    console.info(id);//serverId
    add_nosave('付款信息列表', 'contractController.do?goPayments&id='+id, "contractList",600,300);
}


//增加付款

function addPayment(){
	 var gname = "contractList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
    var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
    console.info(id);
    add('增加付款', 'contractController.do?goAddPayment&id='+id, "contractList",600,300);
}

//设置代理人

function setProxy(){
	 var gname = "contractList";
	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
   var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
   console.info(id);
   add('增加付款', 'contractController.do?goSetProxy&id='+id, "contractList",600,80);
}



 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'contractController.do?upload', "contractList");
}

//导出
function ExportXls() {
	JeecgExcelExport("contractController.do?exportXls","contractList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("contractController.do?exportXlsByT","contractList");
}

 </script>
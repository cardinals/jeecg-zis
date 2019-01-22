<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="pfBaseList" checkbox="true" pagination="true" fitColumns="true" title="公募基本表" actionUrl="pfBaseController.do?datagrid" idField="id" fit="true" queryMode="group" extendParams="onRowContextMenu:onRowContextMenu">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="基金简称"  field="shortName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="成立日期"  field="estaDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="托管人"  field="tuoguanPerson"  queryMode="single"  dictionary="tuoGuan"  width="120"></t:dgCol>
   <t:dgCol title="基金代码"  field="jijinCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="批复日期"  field="pifuDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="基金经理"  field="jijinManager"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品状态"  field="productState"  queryMode="single" dictionary="pro_state"   width="120"></t:dgCol>
   <t:dgCol title="历任基金经理"  field="jijinManagerHis"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="成立规模"  field="foundScale"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发行开始日期"  field="faxingDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发行截止日期"  field="faxingDateto"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="律师事务所"  field="lawOffice"  queryMode="single" dictionary="lvshi"   width="120"></t:dgCol>
   <t:dgCol title="期货公司"  field="futuresCompany"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否为机构定制"  field="isDingzhi"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="风险等级"  field="riskLevel"  queryMode="single" dictionary="risk_level"    width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pfBaseController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="pfBaseController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pfBaseController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="pfBaseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="pfBaseController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
     <div id="mm" class="easyui-menu" style="width:120px;">
 <div onClick="checkHisManager()" data-options="iconCls:'icon-search'">历任基金经理</div>
</div>
 
  
 <script src = "webpage/com/jeecg/pf/pfBaseList.js"></script>		
 <script type="text/javascript">
//添加右击菜单内容
 var globalRowIndex = null;
 function onRowContextMenu(e,rowIndex, rowData){
 	 globalRowIndex = rowIndex;
 	 e.preventDefault();
 	 var gname = "pfBaseList";
    var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
     $('#mm').menu('show', {
         left:e.pageX,
         top:e.pageY
     });        
     return false;
 } 
 
 function checkHisManager(){
 	 var gname = "pfBaseList";
 	 var selected=$("#"+gname).datagrid('getRows'); //获取所有行集合对象
     var id = selected[globalRowIndex].id; //index为当前右键行的索引，指向当前行对象
     console.info(id);
     add_nosave('历任基金经理', 'pfBaseController.do?goHisManager&id='+id, "pfBaseList",600,300);
 }

 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'pfBaseController.do?upload', "pfBaseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("pfBaseController.do?exportXls","pfBaseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("pfBaseController.do?exportXlsByT","pfBaseList");
}

 </script>
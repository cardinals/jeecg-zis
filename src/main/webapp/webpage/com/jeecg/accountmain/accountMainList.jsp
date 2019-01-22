<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="accountMainList" checkbox="true" fitColumns="true" title="银行账户信息总表" actionUrl="accountMainController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行账号"  field="accountNumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行户名"  field="acountName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户银行全称"  field="acountFullname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户银行简称"  field="acountShortname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账户类型"  field="acountType"  queryMode="single"  dictionary="accountype"  width="120"></t:dgCol>
   <t:dgCol title="开户日期"  field="kaihuDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账户状态"  field="acountStatus"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账户利率"  field="acountInterestRate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="利率启用日期"  field="interestRateDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否流水户"  field="isLiushui"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="在用产品"  field="onlineProduct"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="大额支付号"  field="bigZhifu"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否可以提前结息"  field="isPreEnd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contact"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系方式"  field="contactType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="行内联行行号"  field="lineHanghao"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="accountMainController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="accountMainController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="accountMainController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="accountMainController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="accountMainController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/accountmain/accountMainList.js"></script>		
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'accountMainController.do?upload', "accountMainList");
}

//导出
function ExportXls() {
	JeecgExcelExport("accountMainController.do?exportXls","accountMainList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("accountMainController.do?exportXlsByT","accountMainList");
}
 </script>
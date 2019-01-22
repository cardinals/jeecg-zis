<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="testOrderMainList" fitColumns="true" title="订单" actionUrl="testOrderMainController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="订单号" field="orderCode" ></t:dgCol>
   <t:dgCol title="订单日期" field="orderDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="订单金额" field="orderMoney" ></t:dgCol>
   <t:dgCol title="备注" field="content" ></t:dgCol>
   <t:dgCol title="订单扫描件" field="ctype" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="testOrderMainController.do?del&id={id}" urlclass="ace_button" urlfont="fa-trash-o"/>
 	  <t:dgToolBar title="录入" icon="icon-add" url="testOrderMainController.do?addorupdate" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="testOrderMainController.do?addorupdate" funname="update" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
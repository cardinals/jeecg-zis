<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style type="text/css">
	.datagrid-header-rownumber{width:30px}
    .datagrid-cell-rownumber{width:30px}
</style>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="bankAccountList" checkbox="true" fitColumns="false" title="银行账户信息总表" actionUrl="bankAccountController.do?datagrid2" idField="id" fit="true" queryMode="group"
  queryBuilder="true"
			extendParams="headerContextMenu: [
                { text: '保存列定义', iconCls: 'icon-save', disabled: true, handler: function () { saveHeader(); } },
                { text: '自定义菜单', iconCls: 'icon-reload', disabled: true, handler: function (e, field) { alert($.string.format('您点击了{0}', field)); } }
            ],"
  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="银行账号"  field="accountNumber"  queryMode="single"   query="true"></t:dgCol>
   <t:dgCol title="银行户名"  field="acountName"  queryMode="single"   query="true" ></t:dgCol>
   <t:dgCol title="账户简称"  field="acountAbbreve" hidden="true"  queryMode="single"   query="true"></t:dgCol>
   <t:dgCol title="开户银行全称"  field="acountFullname"  queryMode="single"   query="true"></t:dgCol>
   <t:dgCol title="开户银行简称"  field="acountShortname" hidden="true"  query="true" dictionary="bank_code,bankcode,bankname" queryMode="single"   funname="test" ></t:dgCol>
   <t:dgCol title="账户类型"  field="acountType" query="true"  queryMode="single"  dictionary="accountype"  ></t:dgCol>
   <t:dgCol title="开户日期"  field="kaihuDate"  formatter="yyyy-MM-dd"  queryMode="group"   query="true"></t:dgCol>
   <t:dgCol title="账户状态"  query="true"  field="acountStatus"  queryMode="single"  dictionary="acoutSta2"   extendParams="styler: function(value,row,index){ if(value=='1'||value=='正常'){return 'background-color:#008000;color:Black;';} 
   if(value=='0'||value=='已注销'){return 'background-color:#808080;color:Black;';
	}if(value=='2'||value=='募集在用'){return 'background-color:#FF0000;color:Black;';}}"></t:dgCol>
   <t:dgCol title="账户利率"  field="acountInterestRate" hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="利率启用日期" query="true" field="interestRateDate" hidden="true"  formatter="yyyy-MM-dd"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="是否流水户" query="true" field="isLiushui"  dictionary="01NY" hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="基金代码"  field="onlineProduct" query="true" dictionary="product_code,productcode,productcode" queryMode="single"  ></t:dgCol>
   <t:dgCol title="大额支号付" query="true" field="bigZhifu"  queryMode="single" hidden="true"  ></t:dgCol>
   <t:dgCol title="是否可以提前结息" query="true" field="isPreEnd"  dictionary="01NY" hidden="true"   queryMode="single"  ></t:dgCol>
  <t:dgCol title="联系人"  query="true" field="contact"  queryMode="single"  ></t:dgCol>
    <t:dgCol title="联系地址"   query="true" field="contactAddr"  queryMode="single"  width="160"></t:dgCol>
   <%-- <t:dgCol title="联系方式"  query="true"  field="contactType"  queryMode="single"  ></t:dgCol>
    --%>
    <t:dgCol title="联系方式-座机" hidden="true" query="FALSE"  field="zuoJi"  queryMode="single"  ></t:dgCol>
    <t:dgCol title="联系方式-手机" hidden="true" query="FALSE"  field="mobilePhone"  queryMode="single"  ></t:dgCol>
    <t:dgCol title="文件"  field="file"  queryMode="single"  downloadName="附件下载"  width="120"></t:dgCol>
   
    <t:dgCol title="行内联行行号"   query="true" field="lineHanghao" hidden="true"  queryMode="single"  ></t:dgCol>
    <t:dgCol title="托管行预留印鉴"  query="true"  field="seal"   hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="人名章"   field="zhang"  query="true" hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="存单金额"   query="true" field="cundan"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="到期日"  query="true"  field="todate"  formatter="yyyy-MM-dd"  queryMode="group"  ></t:dgCol>
    <t:dgCol title="经办人"   field="jingban"  query="true"  queryMode="single"  dictionary="t_s_base_user,id,realname"></t:dgCol>
     <t:dgCol title="复核状态"  field="status" query="true" extend="{style:{width:'300px';color:'red'};datatype:'*';}"  dictionary="fuhe" width="80" 
     extendParams="styler: function(value,row,index){ 
	if(value=='Y'){return 'background-color:#008000;color:Black;';
	} if(value=='X'){return 'background-color:#FF0000;color:Black;';
	}if(value=='N'){return 'background-color:#FFFF00;color:Black;';}}" formatterjs="changeValue2"></t:dgCol>
    <t:dgCol title="备注项"  field="remarks"  queryMode="single"  width="150" hidden="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="130">  </t:dgCol>
 <t:dgDelOpt title="删除" url="bankAccountController.do?doDel&id={id}" operationCode="dele"   urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgFunOpt  title="审核" funname="szqm(id)" operationCode="shenhe"  urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   
   <t:dgToolBar title="录入" icon="icon-add" operationCode="add"  url="bankAccountController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" funname="updateBar(id)" operationCode="update" url="bankAccountController.do?goUpdate" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" operationCode="delete"  url="bankAccountController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="批量审核"  icon="fa-diamond" operationCode="BatchShenhe"  url="bankAccountController.do?doBatchShenhe" funname="shenheALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="bankAccountController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" operationCode="import"  funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/bankaccount/bankAccountList.js"></script>		
 <script type="text/javascript">
 //  funname="updateBar(id)"
 function updateBar(id){
	 	var value='';
	    var row = $('#bankAccountList').datagrid('getSelected');
	 	if(row!=null){value= row['status'];
	 	}else{
	 		
	 		tip('请选择一条记录');
			return;
	 	}
	 	 if(value=='X'){
			 tip('待审核不可编辑项目');
				return;
		 }
		 /* if(value=='Y'){
			 tip('已审核不可编辑项目');
				return;    
		 } */
		 if(value=='Y'||value=='N'){
			 update('编辑','bankAccountController.do?goUpdate','bankAccountList',1000,600);
				
		 }
		
 }
 function szqm(id) {
		createwindow('经办人录入审核', 'bankAccountController.do?goCheck&id=' + id,420,280);
	}
 
 function test(value,row,index){ 
	 return value;
	 
 }
 
//单元格的格式化函数  value：字段的值 row：行的记录数据 index：行的索引
 function formatAgeFun(acountName,row,index){
 	var str="原："+acountName+",现 ："+(acountName);
 	 $("#"+acountName).css('background-color','#FFD700');
	 
 	return str;
 }
 function changeValue(value,row,index){
	 console.log(value);
	 if(value==0){
		 value='已注销';
	 }
	 if(value==1){
		 value='正常';
	 }
	 if(value==2){
		 value='募集在用';
	 }
	 if(value==3){
		 value='开户中';
	 }
	 if(value==-1){
		 value='其他';
	 }
	 if(value==4){
		 value='销户中';
	 }
	 if(value==null){
		 value='';
	 }
	 return value;
 }
 function changeValue2(value,row,index){
	 if(value=='X'){
		 value='待审核';
	 }
	 if(value=='Y'){
		 value='已审核';
	 }
	 if(value=='N'){
		 value='被回退';
	 }
	 //console.log(value);
	 return value;
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bankAccountController.do?upload', "bankAccountList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bankAccountController.do?exportXls","bankAccountList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bankAccountController.do?exportXlsByT","bankAccountList");
}

//初始化下标
function resetTrNum(tableId) {
	$tbody = $("#"+tableId+"");
	$tbody.find('>tr').each(function(i){
		$(':input, select,button,a', this).each(function(){
			var $this = $(this),validtype_str = $this.attr('validType'), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
			if(validtype_str!=null){
				if(validtype_str.indexOf("#index#") >= 0){
					$this.attr("validType",validtype_str.replace('#index#',i));
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i+1);
	});
}
 </script>
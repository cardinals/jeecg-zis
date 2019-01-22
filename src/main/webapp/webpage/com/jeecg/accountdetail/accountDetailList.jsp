<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addAccountDetailBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delAccountDetailBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addAccountDetailBtn').bind('click', function(){   
 		 var tr =  $("#add_accountDetail_table_template tr").clone();
	 	 $("#add_accountDetail_table").append(tr);
	 	 resetTrNum('add_accountDetail_table');
	 	 return false;
    });  
	$('#delAccountDetailBtn').bind('click', function(){   
      	$("#add_accountDetail_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_accountDetail_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addAccountDetailBtn" href="#">添加</a> <a id="delAccountDetailBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="accountDetail_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						银行账号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						银行户名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						开户银行全称
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						利率
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						开始日期
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						结束日期
				  </td>
	</tr>
	<tbody id="add_accountDetail_table">
	<c:if test="${fn:length(accountDetailList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="accountDetailList[0].id" type="hidden"/>
					<input name="accountDetailList[0].createName" type="hidden"/>
					<input name="accountDetailList[0].createBy" type="hidden"/>
					<input name="accountDetailList[0].createDate" type="hidden"/>
					<input name="accountDetailList[0].updateName" type="hidden"/>
					<input name="accountDetailList[0].updateBy" type="hidden"/>
					<input name="accountDetailList[0].updateDate" type="hidden"/>
					<input name="accountDetailList[0].sysOrgCode" type="hidden"/>
					<input name="accountDetailList[0].sysCompanyCode" type="hidden"/>
					<input name="accountDetailList[0].accountMainId" type="hidden"/>
				  <td align="left">
					  	<input name="accountDetailList[0].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">银行账号</label>
					</td>
				  <td align="left">
					  	<input name="accountDetailList[0].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">银行户名</label>
					</td>
				  <td align="left">
					  	<input name="accountDetailList[0].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
					</td>
				  <td align="left">
					  	<input name="accountDetailList[0].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">利率</label>
					</td>
				  <td align="left">
							<input name="accountDetailList[0].startDate" maxlength="32"  type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">开始日期</label>
					</td>
				  <td align="left">
							<input name="accountDetailList[0].endDate" maxlength="32"  type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">结束日期</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(accountDetailList)  > 0 }">
		<c:forEach items="${accountDetailList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="accountDetailList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="accountDetailList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="accountDetailList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="accountDetailList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="accountDetailList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="accountDetailList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="accountDetailList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="accountDetailList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="accountDetailList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="accountDetailList[${stuts.index }].accountMainId" type="hidden" value="${poVal.accountMainId }"/>
				   <td align="left">
					  	<input name="accountDetailList[${stuts.index }].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailNumber }"/>
					  <label class="Validform_label" style="display: none;">银行账号</label>
				   </td>
				   <td align="left">
					  	<input name="accountDetailList[${stuts.index }].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailName }"/>
					  <label class="Validform_label" style="display: none;">银行户名</label>
				   </td>
				   <td align="left">
					  	<input name="accountDetailList[${stuts.index }].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailFullname }"/>
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				   </td>
				   <td align="left">
					  	<input name="accountDetailList[${stuts.index }].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailRate }"/>
					  <label class="Validform_label" style="display: none;">利率</label>
				   </td>
				   <td align="left">
							<input name="accountDetailList[${stuts.index }].startDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"   ignore="ignore"  value="<fmt:formatDate value='${poVal.startDate}' type="date" pattern="yyyy-MM-dd"/>"/>
					  <label class="Validform_label" style="display: none;">开始日期</label>
				   </td>
				   <td align="left">
							<input name="accountDetailList[${stuts.index }].endDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"   ignore="ignore"  value="<fmt:formatDate value='${poVal.endDate}' type="date" pattern="yyyy-MM-dd"/>"/>
					  <label class="Validform_label" style="display: none;">结束日期</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

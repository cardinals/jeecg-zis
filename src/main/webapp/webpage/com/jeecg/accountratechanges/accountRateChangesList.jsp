<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addAccountRateChangesBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delAccountRateChangesBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addAccountRateChangesBtn').bind('click', function(){   
 		 var tr =  $("#add_accountRateChanges_table_template tr").clone();
	 	 $("#add_accountRateChanges_table").append(tr);
	 	 resetTrNum('add_accountRateChanges_table');
	 	 return false;
    });  
	$('#delAccountRateChangesBtn').bind('click', function(){   
      	$("#add_accountRateChanges_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_accountRateChanges_table'); 
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
<!-- <div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addAccountRateChangesBtn" href="#">添加</a> <a id="delAccountRateChangesBtn" href="#">删除</a> 
</div> -->
<table border="0" cellpadding="2" cellspacing="0" id="accountRateChanges_table">
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
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						外键
				  </td> -->
	</tr>
	<tbody id="add_accountRateChanges_table">
	<c:if test="${fn:length(accountRateChangesList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="accountRateChangesList[0].id" type="hidden"/>
					<input name="accountRateChangesList[0].createName" type="hidden"/>
					<input name="accountRateChangesList[0].createBy" type="hidden"/>
					<input name="accountRateChangesList[0].createDate" type="hidden"/>
					<input name="accountRateChangesList[0].updateName" type="hidden"/>
					<input name="accountRateChangesList[0].updateBy" type="hidden"/>
					<input name="accountRateChangesList[0].updateDate" type="hidden"/>
					<input name="accountRateChangesList[0].sysOrgCode" type="hidden"/>
					<input name="accountRateChangesList[0].sysCompanyCode" type="hidden"/>
				  <td align="left">
					  	<input name="accountRateChangesList[0].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">银行账号</label>
					</td>
				  <td align="left">
					  	<input name="accountRateChangesList[0].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">银行户名</label>
					</td>
				  <td align="left">
					  	<input name="accountRateChangesList[0].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
					</td>
				  <td align="left">
					  	<input name="accountRateChangesList[0].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">利率</label>
					</td>
				  <td align="left">
							<input name="accountRateChangesList[0].startDate" maxlength="32"  type="text" class="Wdate" readonly="readonly"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">开始日期</label>
					</td>
				  <td align="left">
							<input name="accountRateChangesList[0].endDate" maxlength="32"  type="text" class="Wdate" readonly="readonly"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">结束日期</label>
					</td>
				  <!-- <td align="left">
					  	<input name="accountRateChangesList[0].foreignKey" maxlength="36" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">外键</label>
					</td> -->
   			</tr>
	</c:if>
	<c:if test="${fn:length(accountRateChangesList)  > 0 }">
		<c:forEach items="${accountRateChangesList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="accountRateChangesList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="accountRateChangesList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="accountRateChangesList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="accountRateChangesList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="accountRateChangesList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="accountRateChangesList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="accountRateChangesList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="accountRateChangesList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="accountRateChangesList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
				   <td align="left">
					  	<input name="accountRateChangesList[${stuts.index }].detailNumber" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailNumber }"/>
					  <label class="Validform_label" style="display: none;">银行账号</label>
				   </td>
				   <td align="left">
					  	<input name="accountRateChangesList[${stuts.index }].detailName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailName }"/>
					  <label class="Validform_label" style="display: none;">银行户名</label>
				   </td>
				   <td align="left">
					  	<input name="accountRateChangesList[${stuts.index }].detailFullname" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailFullname }"/>
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				   </td>
				   <td align="left">
					  	<input name="accountRateChangesList[${stuts.index }].detailRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.detailRate }"/>
					  <label class="Validform_label" style="display: none;">利率</label>
				   </td>
				   <td align="left">
							<input name="accountRateChangesList[${stuts.index }].startDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"   ignore="ignore"  value="<fmt:formatDate value='${poVal.startDate}' type="date" pattern="yyyy-MM-dd"/>"/>
					  <label class="Validform_label" style="display: none;">开始日期</label>
				   </td>
				   <td align="left">
							<input name="accountRateChangesList[${stuts.index }].endDate" maxlength="32" type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"   ignore="ignore"  value="<fmt:formatDate value='${poVal.endDate}' type="date" pattern="yyyy-MM-dd"/>"/>
					  <label class="Validform_label" style="display: none;">结束日期</label>
				   </td>
				  <%--  <td align="left">
					  	<input name="accountRateChangesList[${stuts.index }].foreignKey" maxlength="36" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.foreignKey }"/>
					  <label class="Validform_label" style="display: none;">外键</label>
				   </td> --%>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addRaiseAccountBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delRaiseAccountBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addRaiseAccountBtn').bind('click', function(){   
 		 var tr =  $("#add_raiseAccount_table_template tr").clone();
	 	 $("#add_raiseAccount_table").append(tr);
	 	 resetTrNum('add_raiseAccount_table');
	 	 return false;
    });  
	$('#delRaiseAccountBtn').bind('click', function(){   
      	$("#add_raiseAccount_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_raiseAccount_table'); 
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
	<a id="addRaiseAccountBtn" href="#">添加</a> <a id="delRaiseAccountBtn" href="#">删除</a> 
	<a id="addRaiseAccountBtn" href="#">添加</a> <a id="delRaiseAccountBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="raiseAccount_table">
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
						曾用产品
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						结息状态
				  </td>
	</tr>
	<tbody id="add_raiseAccount_table">
	<c:if test="${fn:length(raiseAccountList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="raiseAccountList[0].id" type="hidden"/>
					<input name="raiseAccountList[0].createName" type="hidden"/>
					<input name="raiseAccountList[0].createBy" type="hidden"/>
					<input name="raiseAccountList[0].createDate" type="hidden"/>
					<input name="raiseAccountList[0].updateName" type="hidden"/>
					<input name="raiseAccountList[0].updateBy" type="hidden"/>
					<input name="raiseAccountList[0].updateDate" type="hidden"/>
					<input name="raiseAccountList[0].sysOrgCode" type="hidden"/>
					<input name="raiseAccountList[0].sysCompanyCode" type="hidden"/>
					<input name="raiseAccountList[0].accountMainId" type="hidden"/>
				  <td align="left">
					  	<input name="raiseAccountList[0].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">银行账号</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountList[0].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">银行户名</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountList[0].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountList[0].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">曾用产品</label>
					</td>
				  <td align="left">
							<t:dictSelect field="raiseAccountList[0].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="${raiseAccountPage.jiexiStatus}" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(raiseAccountList)  > 0 }">
		<c:forEach items="${raiseAccountList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="raiseAccountList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="raiseAccountList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="raiseAccountList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="raiseAccountList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="raiseAccountList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="raiseAccountList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="raiseAccountList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="raiseAccountList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="raiseAccountList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="raiseAccountList[${stuts.index }].accountMainId" type="hidden" value="${poVal.accountMainId }"/>
				   <td align="left">
					  	<input name="raiseAccountList[${stuts.index }].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangAccount }"/>
					  <label class="Validform_label" style="display: none;">银行账号</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountList[${stuts.index }].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangName }"/>
					  <label class="Validform_label" style="display: none;">银行户名</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountList[${stuts.index }].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangNamefull }"/>
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountList[${stuts.index }].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.preProject }"/>
					  <label class="Validform_label" style="display: none;">曾用产品</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="raiseAccountList[${stuts.index }].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="${poVal.jiexiStatus }" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

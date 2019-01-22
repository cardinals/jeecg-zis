<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addRaiseAccountUseBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delRaiseAccountUseBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addRaiseAccountUseBtn').bind('click', function(){   
 		 var tr =  $("#add_raiseAccountUse_table_template tr").clone();
	 	 $("#add_raiseAccountUse_table").append(tr);
	 	 resetTrNum('add_raiseAccountUse_table');
	 	 return false;
    });  
	$('#delRaiseAccountUseBtn').bind('click', function(){   
      	$("#add_raiseAccountUse_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_raiseAccountUse_table'); 
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
	<a id="addRaiseAccountUseBtn" href="#">添加</a> <a id="delRaiseAccountUseBtn" href="#">删除</a> 
</div> -->
<table border="0" cellpadding="2" cellspacing="0" id="raiseAccountUse_table">
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
				 <!--  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						外键
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						结息状态2
				  </td> -->
	</tr>
	<tbody id="add_raiseAccountUse_table">
	<c:if test="${fn:length(raiseAccountUseList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="raiseAccountUseList[0].id" type="hidden"/>
					<input name="raiseAccountUseList[0].createName" type="hidden"/>
					<input name="raiseAccountUseList[0].createBy" type="hidden"/>
					<input name="raiseAccountUseList[0].createDate" type="hidden"/>
					<input name="raiseAccountUseList[0].updateName" type="hidden"/>
					<input name="raiseAccountUseList[0].updateBy" type="hidden"/>
					<input name="raiseAccountUseList[0].updateDate" type="hidden"/>
					<input name="raiseAccountUseList[0].sysOrgCode" type="hidden"/>
					<input name="raiseAccountUseList[0].sysCompanyCode" type="hidden"/>
				  <td align="left">
					  	<input name="raiseAccountUseList[0].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">银行账号</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountUseList[0].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">银行户名</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountUseList[0].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountUseList[0].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">曾用产品</label>
					</td>
				  <td align="left">
							<t:dictSelect field="raiseAccountUseList[0].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="${raiseAccountUsePage.jiexiStatus}" hasLabel="false"  title="结息状态" readonly="readonly"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
					</td>
				 <!--  <td align="left">
					  	<input name="raiseAccountUseList[0].foreignKey" maxlength="36" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">外键</label>
					</td>
				  <td align="left">
					  	<input name="raiseAccountUseList[0].jiexiStatus2" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">结息状态2</label>
					</td> -->
   			</tr>
	</c:if>
	<c:if test="${fn:length(raiseAccountUseList)  > 0 }">
		<c:forEach items="${raiseAccountUseList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="raiseAccountUseList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="raiseAccountUseList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="raiseAccountUseList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="raiseAccountUseList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="raiseAccountUseList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="raiseAccountUseList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="raiseAccountUseList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="raiseAccountUseList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="raiseAccountUseList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
				   <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].yinhangAccount" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangAccount }"/>
					  <label class="Validform_label" style="display: none;">银行账号</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].yinhangName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangName }"/>
					  <label class="Validform_label" style="display: none;">银行户名</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].yinhangNamefull" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.yinhangNamefull }"/>
					  <label class="Validform_label" style="display: none;">开户银行全称</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].preProject" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.preProject }"/>
					  <label class="Validform_label" style="display: none;">曾用产品</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="raiseAccountUseList[${stuts.index }].jiexiStatus" type="list"   typeGroupCode="jiexi"  defaultVal="${poVal.jiexiStatus }" hasLabel="false"  title="结息状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">结息状态</label>
				   </td>
				  <%--  <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].foreignKey" maxlength="36" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.foreignKey }"/>
					  <label class="Validform_label" style="display: none;">外键</label>
				   </td>
				   <td align="left">
					  	<input name="raiseAccountUseList[${stuts.index }].jiexiStatus2" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.jiexiStatus2 }"/>
					  <label class="Validform_label" style="display: none;">结息状态2</label>
				   </td> --%>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

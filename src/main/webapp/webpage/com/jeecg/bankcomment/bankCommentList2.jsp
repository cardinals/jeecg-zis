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
<table border="0" cellpadding="2" cellspacing="0" id="raiseAccountUse_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<!-- <td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td> -->
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审核时间
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审核意见
				  </td>
				 
	</tr>
	<tbody id="add_raiseAccountUse_table">
	<c:if test="${fn:length(bankCommentList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="bankCommentList[0].id" type="hidden"/>
					<input name="bankCommentList[0].createName" type="hidden"/>
					<input name="bankCommentList[0].createBy" type="hidden"/>
					<input name="bankCommentList[0].createDate" type="hidden"/>
					<input name="bankCommentList[0].updateName" type="hidden"/>
					<input name="bankCommentList[0].updateBy" type="hidden"/>
					<input name="bankCommentList[0].updateDate" type="hidden"/>
					<input name="bankCommentList[0].sysOrgCode" type="hidden"/>
					<input name="bankCommentList[0].sysCompanyCode" type="hidden"/>
				  <td align="left">
					  	<input name="bankCommentList[0].time" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">审核时间</label>
					</td>
				  <td align="left">
					  	<input name="bankCommentList[0].comment" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly">
					  <label class="Validform_label" style="display: none;">审核意见</label>
					</td>
				 
   			</tr>
	</c:if>
	<c:if test="${fn:length(bankCommentList)  > 0 }">
		<c:forEach items="${bankCommentList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<!-- <td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td> -->
						<input name="bankCommentList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="bankCommentList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="bankCommentList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="bankCommentList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="bankCommentList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="bankCommentList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="bankCommentList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="bankCommentList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="bankCommentList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
				   <td align="left">
					  	<input name="bankCommentList[${stuts.index }].time" maxlength="32" type="text" class="inputxt"  style="width:150px;"  ignore="ignore"  value="<fmt:formatDate value='${poVal.time}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"readonly="readonly" />
					  <label class="Validform_label" style="display: none;">审核时间</label>
				   </td>
				   <td align="left">
					  	<input name="bankCommentList[${stuts.index }].comment" maxlength="32" type="text" class="inputxt"  style="width:900px;"  ignore="ignore"  value="${poVal.comment }" readonly="readonly" />
					  <label class="Validform_label" style="display: none;">审核意见</label>
				   </td>
				  
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

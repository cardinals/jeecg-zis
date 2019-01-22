<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addUserTestLineBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delUserTestLineBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addUserTestLineBtn').bind('click', function(){   
 		 var tr =  $("#add_userTestLine_table_template tr").clone();
	 	 $("#add_userTestLine_table").append(tr);
	 	 resetTrNum('add_userTestLine_table');
	 	 return false;
    });  
	$('#delUserTestLineBtn').bind('click', function(){   
      	$("#add_userTestLine_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_userTestLine_table'); 
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
	<a id="addUserTestLineBtn" href="#">添加</a> <a id="delUserTestLineBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="userTestLine_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						身份证
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						地址
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						年龄
				  </td>
	</tr>
	<tbody id="add_userTestLine_table">
	<c:if test="${fn:length(userTestLineList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="userTestLineList[0].id" type="hidden"/>
					<input name="userTestLineList[0].createName" type="hidden"/>
					<input name="userTestLineList[0].createBy" type="hidden"/>
					<input name="userTestLineList[0].createDate" type="hidden"/>
					<input name="userTestLineList[0].updateName" type="hidden"/>
					<input name="userTestLineList[0].updateBy" type="hidden"/>
					<input name="userTestLineList[0].updateDate" type="hidden"/>
					<input name="userTestLineList[0].sysOrgCode" type="hidden"/>
					<input name="userTestLineList[0].sysCompanyCode" type="hidden"/>
					<input name="userTestLineList[0].bpmStatus" type="hidden"/>
				  <td align="left">
					  	<input name="userTestLineList[0].username" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">姓名</label>
					</td>
				  <td align="left">
					  	<input name="userTestLineList[0].idno" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">身份证</label>
					</td>
				  <td align="left">
					  	<input name="userTestLineList[0].addr" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">地址</label>
					</td>
				  <td align="left">
					  	<input name="userTestLineList[0].age" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">年龄</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(userTestLineList)  > 0 }">
		<c:forEach items="${userTestLineList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="userTestLineList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="userTestLineList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="userTestLineList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="userTestLineList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="userTestLineList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="userTestLineList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="userTestLineList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="userTestLineList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="userTestLineList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="userTestLineList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
				   <td align="left">
					  	<input name="userTestLineList[${stuts.index }].username" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.username }"/>
					  <label class="Validform_label" style="display: none;">姓名</label>
				   </td>
				   <td align="left">
					  	<input name="userTestLineList[${stuts.index }].idno" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.idno }"/>
					  <label class="Validform_label" style="display: none;">身份证</label>
				   </td>
				   <td align="left">
					  	<input name="userTestLineList[${stuts.index }].addr" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.addr }"/>
					  <label class="Validform_label" style="display: none;">地址</label>
				   </td>
				   <td align="left">
					  	<input name="userTestLineList[${stuts.index }].age" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.age }"/>
					  <label class="Validform_label" style="display: none;">年龄</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFubiaoTestBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFubiaoTestBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFubiaoTestBtn').bind('click', function(){   
 		 var tr =  $("#add_fubiaoTest_table_template tr").clone();
	 	 $("#add_fubiaoTest_table").append(tr);
	 	 resetTrNum('add_fubiaoTest_table');
	 	 return false;
    });  
	$('#delFubiaoTestBtn').bind('click', function(){   
      	$("#add_fubiaoTest_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_fubiaoTest_table'); 
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
	<a id="addFubiaoTestBtn" href="#">添加</a> <a id="delFubiaoTestBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="fubiaoTest_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						附表名字
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						附表年龄
				  </td>
	</tr>
	<tbody id="add_fubiaoTest_table">
	<c:if test="${fn:length(fubiaoTestList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="fubiaoTestList[0].id" type="hidden"/>
					<input name="fubiaoTestList[0].createName" type="hidden"/>
					<input name="fubiaoTestList[0].createBy" type="hidden"/>
					<input name="fubiaoTestList[0].createDate" type="hidden"/>
					<input name="fubiaoTestList[0].updateName" type="hidden"/>
					<input name="fubiaoTestList[0].updateBy" type="hidden"/>
					<input name="fubiaoTestList[0].updateDate" type="hidden"/>
					<input name="fubiaoTestList[0].sysOrgCode" type="hidden"/>
					<input name="fubiaoTestList[0].sysCompanyCode" type="hidden"/>
					<input name="fubiaoTestList[0].bpmStatus" type="hidden"/>
				  <td align="left">
					  	<input name="fubiaoTestList[0].fuName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">附表名字</label>
					</td>
				  <td align="left">
					  	<input name="fubiaoTestList[0].fuAge" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" >
					  <label class="Validform_label" style="display: none;">附表年龄</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(fubiaoTestList)  > 0 }">
		<c:forEach items="${fubiaoTestList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="fubiaoTestList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="fubiaoTestList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="fubiaoTestList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="fubiaoTestList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="fubiaoTestList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="fubiaoTestList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="fubiaoTestList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="fubiaoTestList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="fubiaoTestList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="fubiaoTestList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
				   <td align="left">
					  	<input name="fubiaoTestList[${stuts.index }].fuName" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.fuName }"/>
					  <label class="Validform_label" style="display: none;">附表名字</label>
				   </td>
				   <td align="left">
					  	<input name="fubiaoTestList[${stuts.index }].fuAge" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.fuAge }"/>
					  <label class="Validform_label" style="display: none;">附表年龄</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

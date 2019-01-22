<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTestOrderCustomBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTestOrderCustomBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTestOrderCustomBtn').bind('click', function(){   
 		 var tr =  $("#add_testOrderCustom_table_template tr").clone();
	 	 $("#add_testOrderCustom_table").append(tr);
	 	 resetTrNum('add_testOrderCustom_table');
    });  
	$('#delTestOrderCustomBtn').bind('click', function(){   
      	$("#add_testOrderCustom_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_testOrderCustom_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTestOrderCustomBtn" href="#">添加</a> <a id="delTestOrderCustomBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="testOrderCustom_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">客户名</td>
				  <td align="left" bgcolor="#EEEEEE">单价</td>
				  <td align="left" bgcolor="#EEEEEE">性别</td>
				  <td align="left" bgcolor="#EEEEEE">电话</td>
				  <td align="left" bgcolor="#EEEEEE">身份证扫描件</td>
	</tr>
	<tbody id="add_testOrderCustom_table">	
	<c:if test="${fn:length(testOrderCustomList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="testOrderCustomList[0].name" maxlength="100" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="testOrderCustomList[0].money" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="testOrderCustomList[0].sex" maxlength="4" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="testOrderCustomList[0].telphone" maxlength="32" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="testOrderCustomList[0].sfPic" maxlength="500" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(testOrderCustomList)  > 0 }">
		<c:forEach items="${testOrderCustomList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="testOrderCustomList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="testOrderCustomList[${stuts.index }].name" maxlength="100" value="${poVal.name }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="testOrderCustomList[${stuts.index }].money" maxlength="" value="${poVal.money }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="testOrderCustomList[${stuts.index }].sex" maxlength="4" value="${poVal.sex }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="testOrderCustomList[${stuts.index }].telphone" maxlength="32" value="${poVal.telphone }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="testOrderCustomList[${stuts.index }].sfPic" maxlength="500" value="${poVal.sfPic }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>
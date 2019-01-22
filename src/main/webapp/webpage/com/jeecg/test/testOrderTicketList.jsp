<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTestOrderTicketBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTestOrderTicketBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTestOrderTicketBtn').bind('click', function(){   
 		 var tr =  $("#add_testOrderTicket_table_template tr").clone();
	 	 $("#add_testOrderTicket_table").append(tr);
	 	 resetTrNum('add_testOrderTicket_table');
    });  
	$('#delTestOrderTicketBtn').bind('click', function(){   
      	$("#add_testOrderTicket_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_testOrderTicket_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTestOrderTicketBtn" href="#">添加</a> <a id="delTestOrderTicketBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="testOrderTicket_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">航班号</td>
				  <td align="left" bgcolor="#EEEEEE">航班时间</td>
	</tr>
	<tbody id="add_testOrderTicket_table">	
	<c:if test="${fn:length(testOrderTicketList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="testOrderTicketList[0].ticketCode" maxlength="100" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="testOrderTicketList[0].tickectDate" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(testOrderTicketList)  > 0 }">
		<c:forEach items="${testOrderTicketList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="testOrderTicketList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="testOrderTicketList[${stuts.index }].ticketCode" maxlength="100" value="${poVal.ticketCode }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="testOrderTicketList[${stuts.index }].tickectDate" maxlength="" value="${poVal.tickectDate }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>
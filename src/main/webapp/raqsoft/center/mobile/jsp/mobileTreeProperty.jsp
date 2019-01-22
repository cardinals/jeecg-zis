<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.raqsoft.center.*" %>
<%@ page import="com.raqsoft.center.console.*" %>
<%@ page import="com.raqsoft.report.view.*"%>
<html>
<head>
<LINK href="style.css" type=text/css rel=stylesheet>
</head>
<body>
<%
	request.setCharacterEncoding( "GBK" );
	String label = request.getParameter( "label" );
	String rpt = request.getParameter( "rpt" );
	String form = request.getParameter( "form" );
	String url = request.getParameter( "url" );
	String scale = request.getParameter( "scale" );
	String paged = request.getParameter( "paged" );
	String scroll = request.getParameter( "scroll" );
	Config cfg = Center.getConfig( application );
	Report[] reports = cfg.getReports();
	String appmap = request.getContextPath();
%>
<script type="text/javascript" src="<%=appmap%><%=ReportConfig.raqsoftDir%>/easyui/jquery.min.js"></script>
<div style="width:100%;height:100%;">
<TABLE style="margin: auto;" cellSpacing=0 cellPadding=0 border=0 class=labelFont>
   	<TR>
    	<TD align=center class=titleFont style="PADDING-BOTTOM: 10px">报表树首页属性</TD>
    </TR>
 	<TR>
    	<TD>树名称&nbsp;<INPUT id=labelBox class=inputFont value="<%=label%>" style="VERTICAL-ALIGN: middle; WIDTH: 305px; HEIGHT: 22px"></TD>
    </TR>
  	<TR height=20>
    	<TD><hr style="color:lightblue;height:2px"></TD>
    </TR>
  	<TR>
    	<TD>首页报表&nbsp;<SELECT id=raqBox class=inputFont style="WIDTH: 293px"> 
    		<OPTION value=""></OPTION>
    	<%
    		for( int i = 0; i < reports.length; i++ ) {
    			if( reports[i].type.equals( "1" ) ) {
    				out.println( "<OPTION value=\"" + reports[i].rpt + "\">" + reports[i].name + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT></TD>
    </TR>
  	<TR height=10>
    	<TD></TD>
    </TR>
  	<TR>
    	<TD>首页参数表单&nbsp;<SELECT id=formBox class=inputFont style="WIDTH: 268px"> 
    		<OPTION value=""></OPTION>
    	<%
    		for( int i = 0; i < reports.length; i++ ) {
    			if( reports[i].type.equals( "2" ) ) {
    				out.println( "<OPTION value=\"" + reports[i].rpt + "\">" + reports[i].name + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT></TD>
    </TR>
  	<TR height=10>
    	<TD></TD>
    </TR>
  	<TR>
    	<TD>报表显示比例&nbsp;<input type=text class=inputFont value="<%=scale%>" id=scaleBox style="width:40px;vertical-align:middle">(网页中显示的大小与报表设计大小的比值)</TD>
	</TR>
  	<TR height=10>
    	<TD></TD>
    </TR>
  	<TR>
    	<TD>是否固定表头&nbsp;<input type=checkbox id=pagedCheck>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;固定表头后是否分页&nbsp;<input type=checkbox id=scrollCheck></TD>
    </TR>
  	<TR height=20>
    	<TD><hr style="color:lightblue;height:2px"></TD>
    </TR>
  	<TR>
    	<TD>首页超链接(以/开头表示相对于WEB应用的根目录)</TD>
	</TR>
  	<TR>
    	<TD><input type=text id=urlBox class=inputFont style="width:345px" value="<%=url%>"></TD>
    </TR>
  	<TR height=10>
    	<TD></TD>
    </TR>
  	<TR>
    	<TD>(首页报表如果为空，则显示首页链接，两者都为空则显示空白)</TD>
	</TR>
  	<TR>
    	<TD colspan=3 style="PADDING-TOP: 10px" align=center>
    		<img src="<%=appmap%>/raqsoft/center/images/submit.gif" style="cursor:pointer" onclick="submit()">
    	</TD>
    </TR>
</TABLE>
</div>
<script language=javascript src="util.js">
</script>

<script language=javascript>
	var leftIFrameWin = window.top.document.getElementById('leftF').contentWindow;
	function submit() {
		if( isEmpty( labelBox.value ) ) {
			alert( "请输入树名称！" );
			return;
		}
		var data = "label=" + labelBox.value;
		data += "&rpt=" + raqBox.value + "&scale=" + scaleBox.value;
		if( pagedCheck.checked ) data += "&paged=1";
		else data += "&paged=0";
		if( scrollCheck.checked ) data += "&scroll=1";
		else data += "&scroll=0";
		if( ! isEmpty( formBox.value ) ) data += "&form=" + formBox.value;
		data += "&url=" + urlEncode( urlBox.value );
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/reportCenterServlet?action=6&"+data,
			data:{},
			success:function(strRet){
				var s = leftIFrameWin.tree_getNodeExpanded();
				leftIFrameWin.location = "tree.jsp?status=" + s + "&position=" + leftIFrameWin.document.body.scrollTop + "&currId=1" ;
			
				leftIFrameWin.tree_setCurrNode( document.getElementById( "id_0" ), false );
				//$('.titleFont')[0].innerHTML="“"+labelBox.value+"”节点属性";
			},
			error:function(strRet){
				alert( "修改节点时错误:\n" + strRet );
			}
		});
	}
	raqBox.value = "<%=rpt%>";
	formBox.value = "<%=form%>";
	pagedCheck.checked = "<%=paged%>" == "1";
	scrollCheck.checked = "<%=scroll%>" == "1";
	
</script>

</body>
</html>

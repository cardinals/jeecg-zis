<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="com.raqsoft.center.console.*" %>
<%@ page import="com.raqsoft.report.view.*"%>
<html>
<head>
<style>
	.toolSpan{
		font-size:28px;
		cursor:pointer;
	}
</style>
<%
	String appmap = request.getContextPath();
	String loginType = (String)(request.getSession().getAttribute("loginType"));
%>
<script type="text/javascript" src="<%=appmap %>/js/jquery-1.8.3.min.js"></script>
<script src="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
</head>
<body class="layui-layout-body" style="width:300px;margin-top: 0px">
<div class="layui-layout manager">
	<div class="layui-bg-gray" style="height:40px">
	<div class="layui-row" style="padding-top:10px">
    <div class="layui-col-xs2">
     <button id="add" title="�����ӽڵ�" onClick="tree_addSubNode()" enterColor="green" class="rootnode layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe654;</i></button>
    </div>
    <div class="layui-col-xs2">
     <button id="ins" title="ͬ������ڵ�" onClick="tree_insertNode()" enterColor="green" class="layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe61f;</i></button>
    </div>
    <div class="layui-col-xs2">
     <button id="show" title="Ԥ��" onClick="report_nodeClick( currNode )" enterColor="blue" class="rootnode layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe623;</i></button>
    </div>
    <div  class="layui-col-xs2">
     <button id="up" title="�ڵ�����" onClick="tree_moveNode('up')" class="layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe619;</i></button>
    </div>
    <div class="layui-col-xs2">
     <button id="down" title="�ڵ�����" onClick="tree_moveNode('down')" class="layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe61a;</i></button>
    </div>
    <div class="layui-col-xs2">
     <button id="del" title="ɾ���ڵ�" onClick="tree_deleteNode()" enterColor="red" class="layui-btn layui-btn-xs layui-bg-gray"><i class="layui-icon">&#xe640;</i></button>
    </div>
  </div>
</div>
</div>
<% 
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
	request.setCharacterEncoding( "UTF-8" );
	String html = "";
	DeployTree tree = new DeployTree( application, request );
	tree.setLabelFace( "����", "black", "12px", "orange" );
	//�����ֱ��ǣ����ڵ�����塢������ɫ�������С����꾭��ʱ����ɫ����Ϊ�մ����ʾ��ȱʡֵ
	tree.setSelectedFace( "red", "#eee8aa" );
	//�����ֱ��ǣ����ڵ㱻ѡ��ʱ��ǰ��ɫ������ɫ����Ϊ�մ����ʾ��ȱʡֵ
	String currNodeId = request.getParameter( "currId" );
	if(currNodeId == null || currNodeId.length() == 0){
		currNodeId ="0";
	}
	html = tree.generateHtml(currNodeId, appmap);
%>
<div id="treeContainer" style="padding-top:10px;height:100%;overflow:scroll;position:relative"><!-- �߶� -->
<%=html%>
</div>
<%
	String status = request.getParameter( "status" );
	String afteradd = request.getParameter( "afteradd" );
	out.print( "<script language=javascript>\n" );
	if( "yes".equals(afteradd) ){
		out.print( "\tvar cNode = document.getElementById(\"id_"+currNodeId+"\");\n" );
		out.print( "\ttree_setCurrNode( cNode, false );\n" );
	}else if(currNodeId == "0"){
		out.println("\tcurrNode = document.getElementById('id_0');\n\tshowToolButtons( currNode , true );\n");
	}
	if( status != null ) {
		String position = request.getParameter( "position" );
		out.print( "\ttree_restoreStatus( \"" + status + "\", " + position + ", \"" + currNodeId + "\" );\n" );
	}
	String ifShowTreeNodeComp = request.getParameter( "ifShowTreeNodeComp" );
	if("1".equals(ifShowTreeNodeComp)){
		out.print( "\twindow.onload=function(){var tNode = document.getElementById('id_0');report_nodeClick( tNode );}\n" );
	}
	out.print( "</script>\n" );
%>

</body>
<script>
	top.window.onresize=function(){
		var tc = document.getElementById('treeContainer');
		var targetHeight = 0;
		var h1 = top.window.innerHeight;
		targetHeight = parseInt(h1) - 100;
		tc.style.height = targetHeight + "px";
	}
	
	$(function(){
		var loginType = '<%=loginType%>';
		/* $('.manager').hide();
		if("normalManager" == loginType || "supermanager" == loginType){		
			$('.manager').show();
		} */
		if("normalManager" != loginType && "supermanager" != loginType){
			$('.layui-icon').css("color","#BBB");
		}
	});
</script>
</html>

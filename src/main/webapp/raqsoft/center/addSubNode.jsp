<%@ page contentType="text/html;charset=utf-8"%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<%@ page import="com.raqsoft.center.console.*" %>
<%@ page import="com.raqsoft.center.*" %>
<%@ page import="com.raqsoft.common.*" %>
<%@ page import="com.raqsoft.report.view.*"%>
<%
	String appmap = request.getContextPath();

	request.setCharacterEncoding( "UTF-8" );
	String id = request.getParameter( "id" );
	String action = request.getParameter( "action" );
	String title = "";
	String mark= request.getParameter( "mark" );
	String dqldb = request.getParameter( "dqldb" );
	String servletActionNum = "7";
	if( action.equals( "2" ) ) {
		title = "插入";
		servletActionNum = "15";
	}
	Config cfg = Center.getConfig( application );
	Role[] ug = cfg.getRoles();
	User[] user = cfg.getUsers();
	Report[] reports = cfg.getReports();
	String[] dbs = cfg.getDbs();
	Object[] dqldbs = cfg.getSpecifiedDbs("com.datalogic.jdbc.LogicDriver");
	String dct_vsb_json = cfg.getDctVsbJson();
	String[] afiles = cfg.getFilesBySuffixType(new String[]{});
	String[] qyxs = cfg.getFilesBySuffixType(new String[]{"qyx"});
	String inputHome = cfg.getInputHome();
	String fileRoot = cfg.getFileRoot();
	java.util.List usersList=new java.util.ArrayList();
	java.util.List groupList=new java.util.ArrayList();
	
	RoleDb ugd=cfg.getUserGroupDbConfig();
	String ds=ugd.getDs();
	String userDb=ugd.getUserName();
	String table=ugd.getTable();
	String group=ugd.getRole();
	com.raqsoft.report.usermodel.Context reportContext = new com.raqsoft.report.usermodel.Context();
	/* if(!"".equals(ds)&&userDb!=null&&!"".equals(table)&&ds!=null){
		java.sql.Connection con = null;
	 	java.sql.Statement st=null;
	  	java.sql.ResultSet rs=null;
		DBSession dbsession = null;
		  try{
			  ISessionFactory isf = (ISessionFactory)com.raqsoft.dm.Env.getDBSessionFactory(ds);
			  dbsession = isf.getSession();
		  }catch(Exception ee){
			  //数据库未开启
		  }
	  try{
	     if(dbsession!=null) {
		  	con = (java.sql.Connection)dbsession.getSession();
		    if(con!=null) {
		        st=con.createStatement();
	            table = table.trim();
	            if (table.startsWith("select") || table.startsWith("SELECT")) {
	              table = "(" + table + ") RQLOGINTABLE";
	            }
		        
		        rs=st.executeQuery("select * from "+table);
		        while(rs.next()){
		             Object obj1=rs.getObject(userDb);
		             if(!usersList.contains(obj1)){
		                 usersList.add(obj1);
		             }
		             Object obj2=rs.getObject(group);
		             if(!groupList.contains(obj2)){
		                 groupList.add(obj2);
		             }
		        }
		     }
		     else{
		       out.print("数据库连接失败 ，请检查jndi配置");
		     }
		   }	
	   }
	   catch(Exception e){
	   
	   }
	   finally{
	      try{
	        if(rs!=null){
	          rs.close();
	        }
	        if(st!=null){
	          st.close();
	        }
	        if(conn!=null){
	          conn.close();
	        }
	      }
	      catch(Exception e){
	      }
	   }
	} */
%>
<html>
<head>
<script type="text/javascript" src="<%=appmap%>/js/jquery-1.8.3.min.js"></script>
<script src="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/layui.js"></script>
<script src="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=appmap %><%=ReportConfig.raqsoftDir%>/center/layui/css/layui.css">
<style>
body{
	background-color: #eee;
}
 .layui-form input[type=checkbox]{

	display:block;
	width:145px
} 
</style>
	<script language="javascript">	
    function addReport(){
    	layui.use('layer', function(){
    		$('#openLayerIndex').val(layer.open({
    			type:2,
    			title:"上传报表",
    			content:'<%=appmap%>/reportCenterServlet?action=26&uploadType=report&refresh=no',
    			area: ['650px', '250px'],
    			offset: 'auto'
    		}));
    	});
	}
    
    function refreshReportList(newReportName, value){
    	$(raqBox).append($("<option value=\""+value+"\" selected>"+newReportName+"</option>"));
    }
    
   	function changeReportType(type){
		  window.location="<%=request.getContextPath()%>/centerJsp/addSubNode.jsp?"+
		  "id=<%=id%>&action=<%=action%>&mark="+type;
	}

	 function noselect(__eid){
	 	   var obj=document.getElementById(__eid);
	 	   var length1=obj.options.length;
	 	   for(var i=0;i<length1;i++){
	 	     obj.options[i].selected=false;	
	 	   }
	 	}
	 	
	 function selectall(__eid){
	 	   var obj=document.getElementById(__eid);
	 	   var length1=obj.options.length;
	 	   for(var i=0;i<length1;i++){
	 	      obj.options[i].selected=true;	
	 	   }	 	
	 }
	 
	 function getLmdTables(tables){
		 $('#anaTables').empty();
		 if(tables.tables == null){
			 $('#anaTables').append("<OPTION value=\"\">未查到</OPTION>");
			 $('#anaTables').css("height","30px");
			 return;
		 }
		 $.each(tables.tables, function(o1, o2) {
			 $('#anaTables').append("<OPTION value=\""+o2.name+"\">"+o2.dispName+"</OPTION>");
		});
		 $('#anaTables').css("height","130px");
	 }
	 
	 function lookoverdir(){
		 $.ajax({
				type:"post",
				url:"<%=appmap%>/reportCenterServlet?action=40",
				dataType: 'json', 
				data:{},
				success:function(strRet){
					var jsonArr = eval(strRet);
					$(businessInputSubDir).empty();
					 $.each(jsonArr, function(o1, o2) {
						 $(businessInputSubDir).append("<OPTION value=\""+o2+"\">"+o2+"</OPTION>");
					});
				},
				error:function(strRet){
					alert( "错误:\n" + strRet );
				}
		});
	 }
	 
	 function dqldbChange(event){
		 if(event.value != "") {
			 $('#qyxBox').val("");
			 $('#qyxBoxMsgSpan').html("&#x1006;");
			 $('#qyxBoxMsgSpan').css("color","red");
			 $('#queryBoxMsgSpan').html("&#xe605;");
			 $('#queryBoxMsgSpan').css("color","green");
		 }
		 anaDqldbChange(event);
		 filter(event);
	 }
	 function anaDqldbChange(event){
		 var dqldb2 = event.value;
		 $('#anaTables').empty();
		 $('#anaTables').append("<OPTION value=\"\">查询中...</OPTION>");
		 $('#frame_tableArrange').attr("src", "<%=appmap %><%=ReportConfig.raqsoftDir%>/guide/jsp/centerQuerySupport.jsp?dataSource="+dqldb2);
	 }
	 function qyxChange(event){
		 if(event.value != "") {
			 $('#queryBox').val("");
			 $('#queryBoxMsgSpan').html("&#x1006;");
			 $('#queryBoxMsgSpan').css("color","red");
			 $('#qyxBoxMsgSpan').html("&#xe605;");
			 $('#qyxBoxMsgSpan').css("color","green");
		 }
	 }
	 
	 function filter(event){
		 $(dctBox).empty();
		 $(dctBox).append("<OPTION value=\"\"></OPTION>");
		 $(vsbBox).empty();
		 $(vsbBox).append("<OPTION value=\"\"></OPTION>");
		 var json = eval("("+"<%=dct_vsb_json%>"+")");
		 var fileRoot = "<%=fileRoot%>";
		 for(var i = 0; i < json.length; i++){
			 for(var key in json[i]){
				 if(key == event.value){
					 var selections = json[i][key];
					 for(var j = 0; j < selections.length; j++){
						 var dcts = selections[j]['dcts'];
						 for(var k = 0; k < dcts.length; k++){
							 $(dctBox).append("<OPTION value=\""+fileRoot+"/"+dcts[k]+"\">"+dcts[k]+"</OPTION>");
						 }
						 var vsbs = selections[j]['vsbs'];
						 for(var k = 0; k < vsbs.length; k++){
							 $(vsbBox).append("<OPTION value=\""+fileRoot+"/"+vsbs[k]+"\">"+vsbs[k]+"</OPTION>");
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 function filterByType(typ){
		 $.ajax({
				data:[],
				url:"<%=appmap %>/reportCenterServlet?action=44&type="+typ,
				type:'post',
				success:function(shts){
					var shtsArr = shts.split(';');
					$(raqBox).empty();
					 for(var k = 0; k < shtsArr.length; k++){
						 var sht = shtsArr[k];
						 var k_v = sht.split(',');
						 $(raqBox).append("<OPTION value=\""+k_v[1]+"\">"+k_v[0]+"</OPTION>");
					 }
				}
		});
	 }
	 
	function reFreshAnalysefileList(analyseType){
		$.ajax({
			data:[],
			url:"<%=appmap %>/reportCenterServlet?action=55&analyseType="+analyseType,
			type:'post',
			success:function(anaFilesString){
				var anaFilesArr = anaFilesString.split(',');
				$(anaBox).empty();
				for(var a = 0 ;a < anaFilesArr.length; a++){
					$(anaBox).append($("<option value=\""+anaFilesArr[a]+"\" selected>"+anaFilesArr[a]+"</option>"));
				}
			}
		});
	} 
	 
	function typeChanged() {
		hideSpan( 'analysisSpan' );
		hideSpan( 'analysisSpan0' );
		hideSpan( 'reportSpan' );
		hideSpan( 'urlSpan' );
		hideSpan( 'querySpan' ); 
		hideSpan( 'businessInput' );
		if( typeBox.value == "1" ) {
			filterByType("");
			showSpan( 'reportSpan' );
		}		
		else if( typeBox.value == "2" ) {
			showSpan( 'urlSpan' );
		}		
		else if( typeBox.value == "3" ) {
			showSpan( 'querySpan' );
			dqldbChange(queryBox);
				filter(queryBox);
		}		
		else if( typeBox.value == "4" ) {
			showSpan( 'analysisSpan' );
		}	
		else if( typeBox.value == "5" ) {
			showSpan( 'businessInput' );
			filterByType("sht");
			lookoverdir();
		}	
	}
	
	function submitNode() {
		if( isEmpty( labelBox.value ) ) {
			alert( "请输入节点名称！" );
			return;
		}
		if( (typeBox.value == "1" || typeBox.value == "5") && isEmpty( raqBox.value ) ) {
			alert( "请输入节点对应的报表！" );
			return;
		}
		else if( typeBox.value == "2" && isEmpty( urlBox.value ) ) {
			alert( "请输入节点对应的超链接！" );
			return;
		}
		else if( (typeBox.value == "3") 
				&& isEmpty(queryBox.value) && isEmpty(qyxBox.value) ) {
			alert( "请正确配置dql数据库或qyx文件！" );
			return;
		}
		var data1 = "id=<%=id%>&type=" + typeBox.value + "&label=" + encodeURIComponent( labelBox.value );
		var groups = getSelectedValues( userGroupBox );
		if( ! isEmpty( groups ) ) data1 += "&roles=" + groups;
		var users = getSelectedValues( userBox );
		if( ! isEmpty( users ) ) data1 += "&users=" + users;
		var anaTables_values = getSelectedValues($('#anaTables')[0]);
		if(typeBox.value == "1" || typeBox.value == "5" ) {
			data1 += "&rpt=" + encodeURIComponent(raqBox.value) + "&scale=" + scaleBox.value + "&params=" + encodeURIComponent(paramsBox.value);
			if( pagedCheck.checked ) data1 += "&paged=1";
			else data1 += "&paged=0";
			if( scrollCheck.checked ) data1 += "&scroll=1";
			else data1 += "&scroll=0";
			if( ! isEmpty( formBox.value ) ) data1 += "&form=" + encodeURIComponent( formBox.value );
		}
		if(typeBox.value == "1"){
			data1 += "&"+$("form[name=radioForm_TreeStructure] input[name='treeStructure']:checked").val()+"=yes";
		}
		if( typeBox.value == "2" ) {
			data1 += "&url=" + encodeURIComponent( urlBox.value );
		}
		if( typeBox.value == "3" ) {
			data1 += "&dqldb=" + encodeURIComponent( queryBox.value )
				 + "&dct=" + encodeURIComponent( dctBox.value )
				 + "&vsb=" + encodeURIComponent( vsbBox.value )
				  + "&qyx=" + encodeURIComponent( qyxBox.value )
				 + "&fixedTable=" + encodeURIComponent( anaTables_values );
		}
		if(typeBox.value == "4"){
			var analyseTypeRadios = $('form[name=radioForm] input[type=radio]');
			var analyseTypeRadiosValue = "";
			for(var i = 0 ; i < analyseTypeRadios.length; i++){
				if(analyseTypeRadios[i].checked==true){
					analyseTypeRadiosValue = analyseTypeRadios[i].value; 
				}
			}
			data1 += "&analyseType=" + analyseTypeRadiosValue;
			if(analyseTypeRadiosValue == "ql"){
				if( isEmpty( anaDataSourceBox.value ) || isEmpty( anaSQLBox.value ) ) {
					alert( "请配置查询数据库和查询语句！" );
					return;
				}
				data1 += "&analyseDB=" + encodeURIComponent( anaDataSourceBox ).value
						+"&analyseSQL=" + encodeURIComponent( anaSQLBox ).value;
				}else if(analyseTypeRadiosValue == "fixedTable"){
					data1 += "&anaTables=" + encodeURIComponent( anaTables_values )
						+ "&anaDqldb=" + encodeURIComponent( anaDqlDataSource.value );
				}else if(analyseTypeRadiosValue == "inputFiles"){
					if( isEmpty( anaBoxInputFiles.value ) ) {
							alert( "请配置要分析的文件！" );
						return;
					}
					data1 += "&anaFile=" + encodeURIComponent( anaBoxInputFiles.value );
				}else if(analyseTypeRadiosValue == "dfxScript"){
					if( isEmpty( dfxScriptBox.value ) ) {
						alert( "请编写dfx脚本！" );
						return;
					}
					data1 += "&dfxScript=" + encodeURIComponent( dfxScriptBox.value );
				}else if(analyseTypeRadiosValue == "dfxFile"){
					if( isEmpty( anaBox.value ) ) {
						alert( "请配置要分析的dfx文件！" );
						return;
					}
					data1 += "&dfxParams=" + encodeURIComponent( dfxParamsBox.value );
					data1 += "&anaFile=" + encodeURIComponent( anaBox.value );
				}else{
					if( isEmpty( anaBox.value ) ) {
						alert( "请配置要分析的文件！" );
						return;
				}
				data1 += "&anaFile=" + encodeURIComponent(anaBox.value);
			}
		}
		if(typeBox.value == "5"){
			data1 += "&businessInputSubDir=" + encodeURIComponent(businessInputSubDir.value)
					+"&dataFileType=" + dataFileType.value;
		}
		$.ajax({
			type:"post",
			url:"<%=appmap%>/reportCenterServlet?action=<%=servletActionNum%>&" + data1,
			data:{},
			success:function(strRet){
				if(strRet.indexOf('success') >= 0){
					var leftIFrame = window.top.document.getElementById("leftF");
					leftIFrame.src = "<%=appmap%><%=ReportConfig.raqsoftDir%>/center/tree.jsp?status=" + leftIFrame.contentWindow.tree_getNodeExpanded() + ",<%=id%>,1" + ","+strRet.substring( 8 )+",1" + "&position=" + leftIFrame.contentWindow.document.body.scrollTop
									+ "&currId=" + strRet.substring( 8 ) + "&afteradd=yes";
				}else{
					alert(strRet);
				}
			},
			error:function(strRet1){
				alert( "生成<%=title%>节点时错误:\n" + strRet1 );
			}
		});
	}
	
	function hideSpan( span ) {
		var elms = document.getElementsByClassName(span)
		for( var i = 0; i < elms.length; i++ ) {
			elms[i].style.display = "none";
		}
	}
	
	function showSpan( span ) {
		var elms = document.getElementsByClassName(span)
		for( var i = 0; i < elms.length; i++ ) {
			elms[i].style.display = "";
		}
	}
	
    
	 
	 function urlReWrite(){
		 var opts = urlselects.childNodes;
		 for(var i = 0 ; i < opts.length; i++){
			 if(opts[i].value == urlBox.value) urlselects.value = urlBox.value; 
			 }
		 }
		
		function attachInputFiles(){
			var inputDir = '<%=inputHome%>' + '/';
			var inputFile1 = inputDir+$('#anaBox').val();
			if($('#anaBoxInputFiles').val().length == 0){
				$('#anaBoxInputFiles').val(inputFile1);
			}else{
				$('#anaBoxInputFiles')[0].value += ';'+inputFile1;
			}
		}
	 
  </script>
  
</head>
<body>
<input type="hidden" value id="openLayerIndex"/>
<div class="layui-container layui-bg-gray" style="top:60px;height:100%"> 
    <div  class="layui-layout">
      <div class="layui-row layui-col-space30" style="margin-top:20px">
	  <div class="layui-col-xs5" style="font-weight: bold;">
	    节点属性    —————————————————————————
	  </div>
	  <div class="layui-col-xs6 layui-col-xs-offset1" style="font-weight: bold;">
	    浏览权限    —————————————————————————————
	  </div>
    </div>
    <div class="layui-row layui-col-space30">
    <div class="layui-col-xs5">
      <div class="grid">
      <TABLE lay-skin="nob" style="border: none;width:420px" class="layui-table layui-bg-gray" cellSpacing=0 cellPadding=0 align=center>
	 	<colgroup>
	 		<col width="150px"/>
	 		<col/>
	 	</colgroup>
	 	<TR>
    	<TD>节点类型</TD>
    	<TD>
	     	 <SELECT id=typeBox class="layui-input" style="VERTICAL-ALIGN: middle; width: 145px" onchange="typeChanged()"> 
	    		<OPTION value="0">分类夹</OPTION>
	    		<OPTION value="1">报表</OPTION>
	    		<OPTION value="2">超链接</OPTION>
	    		<OPTION value="3">DQL明细查询</OPTION>
	    		<OPTION value="4">DQL分组分析</OPTION>
				<OPTION value="5">业务填报</OPTION>
	    	</SELECT>
    	</TD>
	</TR>
	 <TR>	
    	<TD>节点名称</TD>
    	<TD><INPUT id=labelBox class="layui-input" style="VERTICAL-ALIGN: middle; WIDTH: 145px;HEIGHT: 28px"></TD>
	</TR>
	<tr><td colspan="3" >
    	<div style="height:2px;background-color: gray">
	  	</div></td></tr>
  	<TR class="reportSpan businessInput">
    	<TD>对应报表</TD>
    	<TD><SELECT id=raqBox class=layui-input style="WIDTH: 145px;"> 
    	<%
    		for( int i = 0; i < reports.length; i++ ) {
    			if( reports[i].type.equals( "1" ) ) {
    				out.println( "<OPTION value=\"" + reports[i].rpt + "\">" + reports[i].name + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT></TD>
    	<td><input style="margin-left:-10px" type="button" class="layui-btn layui-bg-black layui-btn-sm" onclick="addReport()" value="上传"/>
    	</td>
    	</TD></TR>
  	<TR class="reportSpan businessInput">
	<TD>参数表单</TD>
    	<TD><SELECT id=formBox class="layui-input" style="WIDTH: 145px;"> 
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
  	<TR class="reportSpan businessInput">
    	<TD>缩放比例</TD>
    	<TD>
	<input type=text class="layui-input" value="1.0" id=scaleBox style="width:145px;vertical-align:middle">
	</TD></TR>
  	<TR class="reportSpan businessInput">
    	<TD>固定表头&nbsp;</TD>
    	<TD><input type=checkbox id=scrollCheck onchange="$('#pagedCheck').prop('disabled',!$('#pagedCheck').prop('disabled'));"></TD>
    </TR>
    <TR class="reportSpan businessInput">
    	<TD>固定表头后分页&nbsp;</TD>
    	<TD><input type=checkbox id=pagedCheck disabled></TD>
    </TR>
    <TR class="reportSpan">
    	<TD>树形报表&nbsp;</TD>
    	<TD><form class="layui-form" name="radioForm_TreeStructure">
    	<input type=radio name=treeStructure title="" value="isTree">
    	<input type=radio name=treeStructure value="isOlap">
    	<input class='layui-btn layui-btn-sm' type=button onclick="$('.layui-form[name=radioForm_TreeStructure] input[type=radio]').attr('checked',false);layuiParse();return false;" value="清空"/>
    	</form>
    	</TD>
    </TR>
  	<TR class="reportSpan">
    	<TD colspan=1>报表参数</TD>
    	<TD colspan=2><input placeholder="格式：param1=value;param2=value....." type=text id=paramsBox value="" class=layui-input style="WIDTH: 215px"></TD>
	</TR>
  	<TR class=urlSpan>
    	<TD>超链接</TD>
    	<TD>
    		<select id=urlselects class="layui-input" id="hadfunctons" onchange="javascript:var sv=this.options[this.options.selectedIndex].value; if(sv!=''){urlBox.value=sv;}   ">	
    			<option value="">自定义</option>
    			<option value="onLineUser.jsp">用户在线列表</option>
    		</select>
    	</TD>
	</TR>
  	<TR class=urlSpan>
    	<TD>输入url</TD>
    	<TD><input type="textarea" class="layui-input" onkeyup="urlReWrite();console.log(1);" id=urlBox></TD>
    </TR>
    <TR class=urlSpan>
    	<TD colspan="2">(以/开头表示相对于WEB应用的根目录)</TD>
    </TR>
    
  	<TR class="querySpan">
  		<TD>DQL数据库<i id=queryBoxMsgSpan class="layui-icon"></i></TD>
    	<TD><select id=queryBox class=layui-input onchange="dqldbChange(this);" style="WIDTH: 100px"> 
    	<option value="">不选择</option>
    	<%
    		for( int i = 0; i < dqldbs.length; i++ ) {
   				out.println( "<OPTION" );
   				out.println( " value=\"" + dqldbs[i] + "\">" + dqldbs[i] + "</OPTION>" );
    		}
    	%>
    	</select>
    	</TD>
    </TR>
    <TR class="querySpan">
    	<TD>qyx文件<i id=qyxBoxMsgSpan class="layui-icon"></i></TD>
    	<TD><SELECT id=qyxBox class=layui-input onchange="qyxChange(this);" style="WIDTH: 100px"> 
    	<option value="">不选择</option>
    	<%
    		for( int i = 0; i < qyxs.length; i++ ) {
   				out.println( "<option" );
   				out.println( " value=\"" + fileRoot + "/" + qyxs[i] + "\">" + qyxs[i] + "</option>" );
    		}
    	%>
    	</SELECT></TD>
    </TR>
    <TR class="querySpan">
    	<TD>dct文件:</TD>
    	<TD><SELECT id=dctBox class="layui-input" style="WIDTH: 100px"> 
    	</SELECT></TD>
    </TR>
    <TR class="querySpan">
    	<TD>vsb文件:</TD>
    	<TD><SELECT id=vsbBox class=layui-input style="WIDTH: 100px"> 
    	</SELECT></TD>
    </TR>
    	<TR class="analysisSpan">
    	<TD colspan=3>
	    	<form name="radioForm">
		    	<table style="width=100%">
			    	<tr>
			    	<td>分析类型:&nbsp;</TD>
			    	<td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan3' );anaDqldbChange($('#anaDqlDataSource')[0]);anaBox.value='';" name="analyseType" value="fixedTable"/>dql表
			    	</td><td>
			    		<input type="radio" class="analyseTypeInput" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan1' );showSpan( 'analysisSpan4' );reFreshAnalysefileList('inputFiles');anaBox.value='';anaBoxInputFiles.value='';" name="analyseType" value="inputFiles"/>填报文件
			    	</td></tr>
			    	<tr><td></td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan2' );anaBox.value='';" name="analyseType" value="ql"/>数据源+查询语句
			    	</td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan1' );reFreshAnalysefileList('dataFile');anaBox.value='';" name="analyseType" value="dataFile"/>数据文件
			    	</td></tr>
			    	<tr><td></td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan1' );showSpan( 'analysisSpan6' );reFreshAnalysefileList('dfxFile');anaBox.value='';" name="analyseType" value="dfxFile"/>dfx文件
			    	</td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan0' );showSpan( 'analysisSpan5' );anaBox.value='';" name="analyseType" value="dfxScript"/>dfx脚本
			    	</td></tr>
		    	</table>
	    	</form>
    	</TD>
    	</TR>
    	<TR class="analysisSpan1 analysisSpan0">
    	<TD colspan=1>需要分析的文件:</td>
    	<td><SELECT id=anaBox class="layui-input" style="WIDTH: 140px;" onchange="attachInputFiles();"> 
    	<%
    		for( int i = 0; i < afiles.length; i++ ) {
    			if(afiles[i] != null){
	   				out.println( "<OPTION" );
	   				out.println( " value=\"" + afiles[i] + "\">" + afiles[i] + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT></TD></TR>
    	<TR class="analysisSpan4 analysisSpan0">
    	<TD colspan=1>填报文件</td>
    	<td><input placeholder="上方选择拼接于此" id=anaBoxInputFiles class="layui-input" type="text" style="WIDTH: 140px;" /></TD></TR>
    	<TR  class="analysisSpan2 analysisSpan0">
    	<TD colspan=2>数据库<select id=anaDataSourceBox class="layui-input" style="width:300px" > 
	    	<%
	    		for( int i = 0; i < dbs.length; i++ ) {
	   				out.println( "<option" );
	   				out.println( " value=\"" + dbs[i] + "\">" + dbs[i] + "</option>" );
	    		}
	    	%>
	    	</select>
	    	</TD>
    	</TR>
    	<TR  class="analysisSpan2 analysisSpan0">
    	<TD colspan=2>查询语句<input type="text" id=anaSQLBox class="layui-input" style="WIDTH: 300px"> 
	    </TD>
    	</TR>
    	<TR  class="analysisSpan3 analysisSpan0">
    	<TD colspan=1>DQL数据库</TD>
    	<td colspan=1>
    	<select id=anaDqlDataSource class="layui-input" value="" onchange="anaDqldbChange(this);" style="WIDTH: 100px"> 
    	<%
    		for( int i = 0; i < dqldbs.length; i++ ) {
   				out.println( "<option" );
   				out.println( " value=\"" + dqldbs[i] + "\">" + dqldbs[i] + "</option>" );
    		}
    	%>
    	</select>
    	</td>
    	</TR>
    	<TR  class="analysisSpan3 analysisSpan0 querySpan">
    	<td style="vertical-align:top;">表范围</td>
    	<TD colspan=1>
    	<select multiple="multiple" id=anaTables class="layui-input" style="WIDTH: 100px;height:130px">
    	</select>
	    </TD>
    	</TR>
    	<tr class="analysisSpan5 analysisSpan0">
    	<td>编写dfx脚本</td>
    	<td colspan=2>
    	<input class="layui-input" type="text-area" id="dfxScriptBox" value=""/>
    	</td>
    	</tr>
    	<tr class="analysisSpan6 analysisSpan0">
    	<td>dfx参数</td>
    	<td colspan=2>
    	<input placeholder="格式：param1=value;param2=value....." style="WIDTH: 140px;" class="layui-input" type="text-area" id="dfxParamsBox" value=""/>
    	</td>
    	</tr>
    	<TR class="businessInput">
	    	<TD>数据保存类型</TD>
	    	<TD>
		    	<SELECT id=dataFileType class=layui-input style="VERTICAL-ALIGN: middle; WIDTH: 145px" onchange="typeChanged()"> 
		    		<OPTION value="json" selected>json</OPTION>
		    		<OPTION value="bin">bin</OPTION>
    			</SELECT>
	    	</TD>
  		<TR>
    	<TR class="businessInput">
    		<TD>数据保存目录</TD>
	    	<TD>
	    		<%=inputHome %>/&nbsp;
		    	<select class=layui-input id=businessInputSubDir style="width:145px" >
		    	</select>
	    	</TD>
  		<TR>
    </TR>
		</TABLE>
		</div>
		</div>
		 <div class="layui-col-xs3 layui-col-xs-offset1">
     <div class="grid">
    	     机构浏览权限<input class="layui-btn layui-btn-sm" type="button" value="全选" name="r1" onclick="selectall('userGroupBox')">
    	     <input class="layui-btn layui-btn-sm" type="button" value="清空" name="r1" onclick="noselect('userGroupBox')">
    		<input type=hidden id=authorizedType value='ds'> 
    		<SELECT id=userGroupBox class=layui-input style="WIDTH: 200px; HEIGHT: 450px" size=2 multiple> 
        	<%
        		for( int i = 0; i < ug.length; i++ ) {
				if(!"0".equals(ug[i].getId()) && !"1".equals(ug[i].getId())){
				out.println( "<OPTION value=\"" + ug[i].getId() + "\">" + ug[i].getName() + "</OPTION>" );
				}
			}
        	%>
        </SELECT>
    
     
	</div>
    </div>
    <div class="layui-col-xs3">
      <div class="grid">
       用户浏览权限<input class="layui-btn layui-btn-sm" type="button" value="全选" name="r2" onclick="selectall('userBox')">
  <input class="layui-btn layui-btn-sm" type="button" value="清空" name="r2" onclick="noselect('userBox')">  
  		 <SELECT id=userBox class="layui-input" style="WIDTH: 200px; HEIGHT: 450px" multiple size=2> 
        	<%
        		for( int i = 0; i < user.length; i++ ) {
				if(!"0".equals(user[i].getRoleId()) && !"1".equals(user[i].getRoleId())){
					out.println( "<OPTION value=\"" + user[i].getUserId() + "\">" + user[i].getUserName() + "</OPTION>" );
				}
			}
        	%>
        </SELECT>
      </div>
    </div>
		
	</div>
	<div class="layui-bg-gray" style="position:fixed;left:0px;bottom:0px;height:35px;width:100%">
    <input type="button" value="提交" onclick="submitNode();"id="loginbtn" style="margin-top:0px;" class="layui-btn layui-btn-sm"/>
  </div>
	</div>	
</div>

<script language=javascript src="util.js"></script>
<iframe id="frame_tableArrange" src="" style="display: none;"></iframe>
<script language=javascript>
	hideSpan( 'analysisSpan0' );
	hideSpan( 'reportSpan' );
	hideSpan( 'urlSpan' );
	hideSpan( 'querySpan' );
	hideSpan( 'analysisSpan' )
	hideSpan( 'businessInput' );
	function layuiParse(){
		layui.use('form', function(){
			  var form = layui.form; 
			  form.render();
		});
	}
	layuiParse();
</script>
</body>
</html>

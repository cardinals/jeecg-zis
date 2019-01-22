<%@ page contentType="text/html;charset=UTF-8"%>
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
%>
<html>
<head>
<link href="<%=appmap%>/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=appmap%><%=ReportConfig.raqsoftDir%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=appmap%><%=ReportConfig.raqsoftDir%>/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=appmap%><%=ReportConfig.raqsoftDir%>/easyui/themes/default/easyui.css"/>
</head>
<body>
<%
	request.setCharacterEncoding( "GBK" );
	String id = request.getParameter( "id" );
	String type = request.getParameter( "type" );
	String label = request.getParameter( "label" );
	String roles = request.getParameter( "roles" );
	String rpt = request.getParameter( "rpt" );
	String form = request.getParameter( "form" );
	String url = request.getParameter( "url" );
	String scale = request.getParameter( "scale" );
	String paged = request.getParameter( "paged" );
	String genpf = request.getParameter( "genpf" );
	String scroll = request.getParameter( "scroll" );
	String params = request.getParameter( "params" );
	String dqldb = request.getParameter( "dqldb" );
	String dct = request.getParameter( "dct" );
	String vsb = request.getParameter( "vsb" );
	String afile = request.getParameter( "afile" );
	String query = request.getParameter( "query" );
	String busiDir = request.getParameter( "busiDir" );
	String dataFileType = request.getParameter( "dataFileType" );
	String analyseDB = request.getParameter( "analyseDB" );
	String analyseSQL = request.getParameter( "analyseSQL" );
	String queryType = request.getParameter( "queryType" );
	String fixedTable = request.getParameter( "fixedTable" );
	String anaFile = request.getParameter( "analyseFile" );
	String analyseType = request.getParameter( "analyseType" );
	String reWrite = request.getParameter("reWrite");
	String authorizedType = request.getParameter( "authorizedType" );
	SegmentSet segs = new SegmentSet( roles, true, ';' );
	String groups = segs.get( "1" );    //此节点被授权的用户组
	String users = segs.get( "2" );     //此节点被授权的用户
	if( groups == null ) groups = "";
	if( users == null ) users = "";
	groups = "," + groups + ",";
	users = "," + users + ",";
	Config cfg = Center.getConfig( application );
	Role[] configroles = cfg.getRoles();
	User[] user = cfg.getUsers();
	Report[] reports = cfg.getReports();
	String[] dbs = cfg.getDbs();
	String[] dqldbs = cfg.getDbs();
	String[] dcts = cfg.getDctFiles();
	String[] vsbs = cfg.getVsbFiles();
	String[] afiles = cfg.getAnalysisFiles();
	String inputHome = cfg.getInputHome();
	java.util.List usersList=new java.util.ArrayList();
	java.util.List groupList=new java.util.ArrayList();
	
	RoleDb ugd=cfg.getUserGroupDbConfig();
	String ds=ugd.getDs();
	String userDb=ugd.getUserName();
	String table=ugd.getTable();
	String role=ugd.getRole();
	
	
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
	  	 if(dbsession!=null){
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
		             Object obj2=rs.getObject(role);
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
	        if(con!=null){
	          con.close();
	        }
	      }
	      catch(Exception e){
	      }
	   }
	} */
%>
	<script language="javascript">	
    function changeDiv(par){
      if(par=='xmlblock'){
          xmlDiv.style.display = "block";
          xml.className  = "labelactive";
          dsDiv.style.display = "none";
          ds.className  = "label";      	
      }
      else if(par=='dsblock'){
          xmlDiv.style.display = "none";
          xml.className  = "label";
          dsDiv.style.display = "block";
          ds.className  = "labelactive";
      }
    }  
    
    function addReport(){
    	$('#addReport').dialog({
    	    cache: false,
    	    href: '<%=appmap%>/reportCenterServlet?action=26&uploadType=report&refresh=no'
    	});
    	$('#addReport').dialog('open');
	}
    
    function refreshReportList(newReportName, value){
    	$(raqBox).append($("<option value=\""+value+"\" selected>"+newReportName+"</option>"));
    }
    
   function changeReportType(type){
		   window.location="<%=request.getContextPath()%>/centerJsp/nodeProperty.jsp?"+
		   "id=<%=id%>&type=<%=type%>&url=<%=url%>&label=<%=label%>&roles=<%=roles%>&rpt=<%=rpt%>&form=<%=form%>&scale=<%=scale%>&paged=<%=paged%>&scroll=<%=scroll%>&params=<%=params%>&query=<%=query%>&authorizedType=<%=authorizedType%>&mark="+type;
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
		 $.each(tables.tables, function(o1, o2) {
			 $(fixedTableBox).append("<OPTION value=\""+o2.name+"\">"+o2.dispName+"</OPTION>");
		});
		 fixedTableBox.value = "<%=fixedTable%>";
	 }
	 
	 function lookoverdir(){
		 $.ajax({
				type:"post",
				url:"<%=appmap%>/reportCenterServlet?action=40",
				dataType: 'json', 
				data:{},
				success:function(strRet){
					var jsonArr = eval(strRet);
					 $.each(jsonArr, function(o1, o2) {
						 $(businessInputSubDir).append("<OPTION value=\""+o2+"\">"+o2+"</OPTION>");
					});
					 businessInputSubDir.value = "<%=busiDir%>";
				},
				error:function(strRet){
					alert( "错误:\n" + strRet );
				}
		});
	 }
	 
	 function dqldbChange(event){
		 var dqldb1 = event.value;
		 $('#frame_tableArrange').attr("src", "<%=appmap%>/raqsoft/guide/jsp/centerQuerySupport.jsp?dataSource="+dqldb1);
	 }
  </script>
  
    <%
      if ( authorizedType != null && !"".equals( authorizedType ) && request.getParameter( "mark" ) == null ){
            if ( "ds".equals( authorizedType ) ){
            		%> <script language="javascript">	 changeReportType( 'ds' );  </script> <%
            }
      }
    %>  

<TABLE cellSpacing=0 cellPadding=0 border=0 style="width:600px" align=center class=labelFont>
   	<TR>
    	<TD colspan=3 align=middle style="PADDING-BOTTOM: 10px" class=titleFont>“<%=label%>”节点属性</TD>
    </TR>
 	<TR>
    	<TD>节点类型&nbsp;<SELECT id=typeBox class=inputFont style="VERTICAL-ALIGN: middle; WIDTH: 145px" onchange="typeChanged()" disabled> 
    		<OPTION value="0">分类夹</OPTION>
    		<OPTION value="1">报表</OPTION>
    		<OPTION value="2">超链接</OPTION>
    		<OPTION value="3">DQL查询</OPTION>
    		<OPTION value="4">分析查询</OPTION>
    		<OPTION value="5">业务填报</OPTION>
    	</SELECT></TD>
    	<TD width=40></TD>
    	<TD>节点名称&nbsp;<INPUT id=labelBox class=inputFont value="<%=label%>" style="VERTICAL-ALIGN: middle; WIDTH: 145px; HEIGHT: 22px"></TD>
    </TR>
  	<TR height=10>
    	<TD></TD>
    	<TD></TD>
    	<TD></TD>
    </TR>
  	<%-- <TR>
    	<TD><input type="radio" id="mark" name="mark" value="xml" onclick="changeReportType('xml')" 
    		<%
    		  if("xml".equals(request.getParameter("mark"))||"".equals(request.getParameter("mark"))||request.getParameter("mark")==null){
    		    out.print("checked");
    		  }
    		%>
    		
    		>xml用户配置</TD>
    	<TD></TD>
    	<TD><input type="radio" id="mark" name="mark" value="ds" onclick="changeReportType('ds')"
    		<%
    		  if("ds".equals(request.getParameter("mark"))){
    		    out.print("checked");
    		  }
    		%>
    		
    		>数据库用户配置</TD>
    	
    	</TR> --%>
  	<TR>
    
  	<TR>
  		<%
  		  if("ds".equals(request.getParameter("mark"))){
  		    %>
    	     <TD>可浏览此节点的用户组<font color=red>全选</font><input type=radio name="r1" onclick="selectall('userGroupBox')"><font color=red>清空</font><input type=radio name="r1" onclick="noselect('userGroupBox')"></TD>
    	     <TD></TD>
    	     <TD>可浏览此节点的用户<font color=red>全选</font><input type=radio name="r2" onclick="selectall('userBox')"><font color=red>清空</font><input type=radio name="r2" onclick="noselect('userBox')"></TD>	   
  		    <%
  		  }
  		%>
  		<%
  		  if(!"ds".equals(request.getParameter("mark"))){
  		    %>
    	     <TD>可浏览此节点的用户组<font color=red>全选</font><input type=radio name="r3" onclick="selectall('userGroupBox')"><font color=red>清空</font><input type=radio name="r3" onclick="noselect('userGroupBox')"></TD>
    	      <TD></TD>
    	     <TD>可浏览此节点的用户<font color=red>全选</font><input type=radio name="r4" onclick="selectall('userBox')"><font color=red>清空</font><input type=radio name="r4" onclick="noselect('userBox')"></TD>  		    
    	<%
  		  }
  		%>  		
 </TR>
  	<TR>
  		<%
  		   if("xml".equals(request.getParameter("mark"))||"".equals(request.getParameter("mark"))||request.getParameter("mark")==null){
  		%>
    	<TD>
    		<input type=hidden id=authorizedType value='ds'> 
    		<SELECT id=userGroupBox class=inputFont style="WIDTH: 200px; HEIGHT: 120px" size=2 multiple> 
        	<%
        		for( int i = 0; i < configroles.length; i++ ) {
        			out.print( "<OPTION value=\"" + configroles[i].getId() + "\"" );
        			if( groups.indexOf( "," + configroles[i].getId() + "," ) >= 0 ) out.print( " selected" );
        			out.println( ">" + configroles[i].getName() + "</OPTION>" );
        		}
        	%>
        </SELECT></TD>
    	<TD></TD>
    	<TD><SELECT id=userBox class=inputFont style="WIDTH: 200px; HEIGHT: 120px" multiple size=2> 
        	<%
        		for( int i = 0; i < user.length; i++ ) {
        			out.print( "<OPTION value=\"" + user[i].getUserId() + "\"" );
        			if( users.indexOf( "," + user[i].getUserId() + "," ) >= 0 ) out.print( " selected" );
        			out.println( ">" + user[i].getUserName() + "</OPTION>" );
        		}
        	%>
        </SELECT></TD>
        <%
          }
        %>
  		<%
  		   if("ds".equals(request.getParameter("mark"))){
  		%>
  		
    	<TD>
    		<input type=hidden id=authorizedType value='ds'> 
    		<SELECT id=userGroupBox class=inputFont style="WIDTH: 200px; HEIGHT: 120px" size=2 multiple> 
        	<%
        		for( int i = 0; i < groupList.size(); i++ ) {
        			out.print( "<OPTION value=\"" + groupList.get(i) + "\"" );
        			if( groups.indexOf( "," + groupList.get(i) + "," ) >= 0 ) out.print( " selected" );
        			out.println( ">" + groupList.get(i) + "</OPTION>" );
        		}
        	%>
        </SELECT></TD>
    	<TD></TD>

    	<TD><SELECT id=userBox class=inputFont style="WIDTH: 200px; HEIGHT: 120px" multiple size=2> 
        	<%
        		for( int i = 0; i < usersList.size(); i++ ) {
        			out.print( "<OPTION value=\"" + usersList.get(i) + "\"" );
        			if( users.indexOf( "," + usersList.get(i) + "," ) >= 0 ) out.print( " selected" );
        			out.println( ">" + usersList.get(i) + "</OPTION>" );
        		}
        	%>
        </SELECT>
        </TD>
            	<TD></TD>
    	<TD></TD>
        <%
          }
        %>        
 	</TR>
 	
 	
 	

		
		
  	<TR>
    	<TD colspan=3 style="PADDING-TOP: 0px">(如果用户组和用户都没有被选择，表示所有用户都可以浏览此节点)</TD>
    </TR>
  	<TR height=10>
    	<TD></TD>
    	<TD></TD>
    	<TD></TD>
    </TR>
  	<TR class="reportSpan businessInput">
    	<TD>此节点对应的报表</TD>
    	<TD></TD>
    	<TD>此节点报表对应的参数表单</TD></TR>
  	<TR class="reportSpan businessInput">
    	<TD><SELECT id=raqBox class=inputFont style="WIDTH: 200px"> 
    	<%
    		for( int i = 0; i < reports.length; i++ ) {
    			if( reports[i].type.equals( "1" ) ) {
    				out.println( "<OPTION value=\"" + reports[i].rpt + "\"" );
    				if(rpt != null && rpt.length() != 0 && rpt.equals(reports[i].rpt)){
    					out.println( " selected" );
    				}
    				out.println(">");
    				out.println( reports[i].name + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT>
    	<a onclick="javascript:addReport();" style="cursor: pointer;"><img src="../../images/add.png"/></a>
    	</TD>
    	<TD></TD>
    	<TD><SELECT id=formBox class=inputFont style="WIDTH: 200px"> 
    		<OPTION value=""></OPTION>
    	<%
    		for( int i = 0; i < reports.length; i++ ) {
    			if( reports[i].type.equals( "2" ) ) {
    				out.println( "<OPTION value=\"" + reports[i].rpt + "\"" );
    				if(form != null && form.length() != 0 && form.equals(reports[i].rpt)){
    					out.println( " selected" );
    				}
    				out.println(">");
    				out.println( reports[i].name + "</OPTION>" );
    			}
    		}
    	%>
    	</SELECT></TD>
    </TR>
  	<TR class="reportSpan businessInput" height=10>
    	<TD></TD>
    	<TD></TD>
    	<TD></TD>
    </TR>
  	<TR class="reportSpan businessInput">
    	<TD>报表显示比例&nbsp;<input type=text class=inputFont value="<%=scale%>" id=scaleBox style="width:120px;vertical-align:middle"></TD>
    	<TD colspan=2>(网页中显示的大小与报表设计大小的比值)</TD></TR>
  	<TR class="reportSpan businessInput" height=10>
    	<TD></TD>
    	<TD></TD>
    	<TD></TD>
    </TR>
  	<TR class="reportSpan businessInput">
    	<TD>是否固定表头&nbsp;<input type=checkbox id=scrollCheck></TD>
    	<TD></TD>
    	<TD>固定表头后是否分页&nbsp;<input type=checkbox id=pagedCheck></TD>
    </TR>
<!--     <TR class=reportSpan>
       <td colspan=3>是否按照报表第一个简单SQ数据集生成查询表单&nbsp;<input type=checkbox id=genpCheck><font color="red">(*在不使用参数表单时使用!)</font></td>
    </TR> -->
  	<TR class=reportSpan>
    	<TD colspan=3>报表参数值或宏值(多个之间用分号分隔)&nbsp;<input type=text id=paramsBox value="<%=params%>" class=inputFont style="WIDTH: 215px"></TD>
	</TR>
    
  	<TR class=urlSpan>
    	<TD colspan=3>此节点对应的超链接(以/开头表示相对于WEB应用的根目录)</TD>
	</TR>
  	<TR class=urlSpan>
    	<TD colspan=3>
    		<select id="hadfunctons" onchange="javascript:var sv=this.options[this.options.selectedIndex].value; if(sv!=''){urlBox.value=sv;}">	
    			<option value="">---系统功能连接---</option>
    			<option value="/centerJsp/onLineUser.jsp">用户在线列表</option>
    		</select>
    	</TD>
    </TR>
  	<TR class=urlSpan>
    	<TD colspan=3>url:<input type=text class=inputFont id=urlBox style="width:440px" value="<%=url%>"></TD>
    </TR>
    
    
  	<TR class="querySpan">
    	<TD colspan=3>
    		<table>
    	<TR class="querySpan">
    	<TD colspan=1>DQL数据库:&nbsp;<select id=queryBox class=inputFont onchange="dqldbChange(this);" value="<%=dqldb%>" style="WIDTH: 100px"> 
    	<%
    		for( int i = 0; i < dqldbs.length; i++ ) {
   				out.println( "<option" );
   				out.println( " value=\"" + dqldbs[i] + "\">" + dqldbs[i] + "</option>" );
    		}
    	%>
    	</select>
    	</TD>
    	<TD colspan=1>dct文件:&nbsp;<SELECT id=dctBox class=inputFont style="WIDTH: 100px"> 
    	<%
    		for( int i = 0; i < dcts.length; i++ ) {
   				out.println( "<OPTION" );
   				out.println( " value=\"" + dcts[i] + "\">" + dcts[i] + "</OPTION>" );
    		}
    	%>
    	</SELECT></TD>
    	<TD colspan=1>vsb文件:&nbsp;<SELECT id=vsbBox class=inputFont style="WIDTH: 100px"> 
    	<%
	    	out.println( "<OPTION" );
				if(vsb == null || vsb.length() == 0 ){
				out.println( " selected" );
			}
			out.println( " value=\"\"></OPTION>" );
    		for( int i = 0; i < vsbs.length; i++ ) {
   				out.println( "<OPTION" );
   				out.println( " value=\"" + vsbs[i] + "\">" + vsbs[i] + "</OPTION>" );
    		}
    	%>
    	</SELECT></TD>
    	</tr></table>
    	</TD></TR>
    	
    	<TR class="querySpan">
    	<TD colspan=3>
    	<table>
    	<TR class="querySpan">
    	<TD colspan=1>查询类型:&nbsp;<SELECT id=queryTypeBox class=inputFont style="VERTICAL-ALIGN: middle; WIDTH: 145px"> 
    		<OPTION value="detail">明细查询</OPTION>
    		<OPTION value="group">分组查询</OPTION>
    		<OPTION value="mix">混合查询</OPTION>
    	</SELECT>
    	</TD>
    	<TD colspan=1>表范围:&nbsp;<SELECT id=fixedTableBox class=inputFont style="WIDTH: 100px"> 
    	</SELECT>
    	</tr></table>
    	</TD></TR>
    	
    	
    	<TR class="analysisSpan">
    	<TD colspan=3>
	    	<form name="radioForm">
		    	<table style="width=100%">
			    	<tr>
			    	<td colspan=1 rowspan=1>分析类型:&nbsp;</TD>
			    	<td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan2' );showSpan( 'analysisSpan1' );anaBox.value=''" name="analyseType" value="inputFiles"/>填报文件
			    	</td><td>
			    		<input type="radio" class="analyseType" onclick="showSpan( 'analysisSpan2' );hideSpan( 'analysisSpan1' );anaBox.value=''" name="analyseType" value="ql"/>数据源+查询语句
			    	</td></tr><tr><td></td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan2' );showSpan( 'analysisSpan1' );anaBox.value=''" name="analyseType" value="dataFile"/>数据文件
			    	</td><td>
			    		<input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan2' );showSpan( 'analysisSpan1' );anaBox.value=''" name="analyseType" value="dfxFile"/>DFX
			    	</td></tr><tr><td></td><td>
			    		<!-- <input type="radio" class="analyseType" onclick="hideSpan( 'analysisSpan2' );showSpan( 'analysisSpan1' );anaBox.value=''" name="analyseType" value="dfxScript"/>dfxScript -->
			    	</td></tr>
		    	</table>
	    	</form>
    	</TD>
    	</TR>
    	<TR class="analysisSpan1">
    	<TD colspan=1>分析文件:&nbsp;<SELECT id=anaBox class=inputFont style="WIDTH: 200px"> 
    	<%
    		for( int i = 0; i < afiles.length; i++ ) {
    		
   				out.println( "<OPTION" );
   				out.println( " value=\"" + afiles[i] + "\">" + afiles[i] + "</OPTION>" );
    		}
    	%>
    	</SELECT></TD></TR>
    	<TR  class="analysisSpan2">
    	<TD colspan=1>数据库:&nbsp;<select id=anaDataSourceBox class=inputFont style="WIDTH: 200px"> 
	    	<%
	    		for( int i = 0; i < dbs.length; i++ ) {
	   				out.println( "<option" );
	   				if(analyseDB != null && analyseDB.length() != 0 && analyseDB.equals(dbs[i])){
						out.println( " selected" );
					}
	   				out.println( " value=\"" + dbs[i] + "\">" + dbs[i] + "</option>" );
	    		}
	    	%>
	    	</select>
	    	</TD>
    	</TR>
    	<TR  class="analysisSpan2">
    	<TD colspan=1>QL:&nbsp;<input type="text" id=anaSQLBox class=inputFont style="WIDTH: 600px"> 
	    </TD>
    	</TR>
    	<TR class="businessInput">
	    	<TD colspan=3>业务填报数据保存类型:
		    	<SELECT id=dataFileType class=inputFont style="VERTICAL-ALIGN: middle; WIDTH: 145px" onchange="typeChanged()"> 
		    		<OPTION value="bin">bin</OPTION>
		    		<OPTION value="json">json</OPTION>
    			</SELECT>
	    	</TD>
  		<TR>
  		<TR class="businessInput">
	    	<TD colspan=3>业务填报数据保存目录:<%=inputHome %>/&nbsp;
		    	<select id=businessInputSubDir style="width:250px" >
		    	</select>
	    	</TD>
  		<TR>
    	<TD colspan=3 style="PADDING-TOP: 10px" align=center>
    		<img src="<%=appmap %>/raqsoft/center/images/submit.gif" style="cursor:pointer" onclick="submit()">
    	</TD>
    </TR>
</TABLE>
<div id="addReport" class="easyui-dialog" title="添加报表" style="width:800px;height:400px;padding:10px" 
data-options="
	modal:true,
	closed:true,
	buttons: [
		{text:'cancel',iconCls:'icon-cancel',handler:function(){$('#addReport').dialog('close');}}
		]">
</div>

<script language=javascript src="../../util.js"></script>
<iframe id="frame_tableArrange" src="" style="display: none;"></iframe>
<script language=javascript>
	function typeChanged() {
		if( typeBox.value == "0" ) {
		 	hideSpan( 'analysisSpan' );
			hideSpan( 'reportSpan' );
			hideSpan( 'urlSpan' );
			hideSpan( 'querySpan' ); 
			hideSpan( 'businessInput' );
		}
		else if( typeBox.value == "1" ) {
			hideSpan( 'businessInput' );
			hideSpan( 'analysisSpan' );
			showSpan( 'reportSpan' );
			hideSpan( 'urlSpan' );
			hideSpan( 'querySpan' );
		}		
		else if( typeBox.value == "2" ) {
			hideSpan( 'analysisSpan' );
			hideSpan( 'reportSpan' );
			showSpan( 'urlSpan' );
			hideSpan( 'querySpan' );
			hideSpan( 'businessInput' );
		}		
		else if( typeBox.value == "3" ) {
			hideSpan( 'analysisSpan' );
			hideSpan( 'reportSpan' );
			hideSpan( 'urlSpan' );
			showSpan( 'querySpan' );
			hideSpan( 'businessInput' );
		}		
		else if( typeBox.value == "4" ) {
			showSpan( 'analysisSpan' );
      		hideSpan( 'reportSpan' );
			hideSpan( 'urlSpan' );
			hideSpan( 'querySpan' );
			hideSpan( 'businessInput' );
		}	
		else if( typeBox.value == "5" ) {
			hideSpan( 'analysisSpan' );
      		hideSpan( 'reportSpan' );
			hideSpan( 'urlSpan' );
			hideSpan( 'querySpan' );
			showSpan( 'businessInput' );
			lookoverdir();
		}	
	}
	
	function submit() {
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
				&& ( isEmpty(queryBox.value)
				|| isEmpty(dctBox.value) )
				) {
			alert( "请正确配置查询对应的数据库和dct文件！" );
			return;
		}
		var data = "id=<%=id%>&type=" + typeBox.value + "&label=" + labelBox.value;
		var groups = getSelectedValues( userGroupBox );
		if( ! isEmpty( groups ) ) data += "&groups=" + groups;
		var users = getSelectedValues( userBox );
		if( ! isEmpty( users ) ) data += "&users=" + users;
		if( typeBox.value == "1" || typeBox.value == "5" ) {
			data += "&rpt=" + raqBox.value + "&scale=" + scaleBox.value + "&params=" + urlEncode( paramsBox.value );
			if( pagedCheck.checked ) data += "&paged=1";
			else data += "&paged=0";
			/* if ( genpCheck.checked ) data += "&genpf=1";
			else data += "&genpf=0"; */
			if( scrollCheck.checked ) data += "&scroll=1";
			else data += "&scroll=0";
			if( ! isEmpty( formBox.value ) ) data += "&form=" + formBox.value;
		}
		if( typeBox.value == "2" ) {
			data += "&url=" + urlEncode( urlBox.value );
		}
		if( typeBox.value == "3" ) {
			data += "&dqldb=" + queryBox.value
				 + "&dct=" + dctBox.value
				 + "&vsb=" + vsbBox.value
				 + "&queryType=" + queryTypeBox.value
				 + "&fixedTable=" + fixedTableBox.value;
		}
		if(typeBox.value == "4"){
			data += "&analyseType=" + radioForm.analyseType.value
				+ "&anaFile=" + anaBox.value;
			if(radioForm.analyseType.value == "ql"){
				if( isEmpty( anaDataSourceBox.value ) || isEmpty( anaSQLBox.value ) ) {
					alert( "请配置查询数据库和查询语句！" );
					return;
				}
				data += "&analyseDB=" + anaDataSourceBox.value
						+"&analyseSQL=" + anaSQLBox.value;
			}else{
				if( isEmpty( anaBox.value ) ) {
					alert( "请正确配置分析文件！" );
					return;
				}
				data += "&anaFile=" + encodeURIComponent(urlEncode( anaBox.value ));
			}
		}
		if(typeBox.value == "5"){
			data += "&businessInputSubDir=" + encodeURIComponent(businessInputSubDir.value)
					+"&dataFileType=" + dataFileType.value;
		}
		data += "&authorizedType=" + authorizedType.value;
		$.ajax({
			type:"post",
			url:"<%=appmap%>/reportCenterServlet?action=10&"+data,
			data:{},
			success:function(strRet){
				alert("修改成功！");
				$('.titleFont')[0].innerHTML="“"+labelBox.value+"”节点属性";
			},
			error:function(strRet){
				alert( "修改节点时错误:\n" + strRet );
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
	typeBox.value = "<%=type%>";
	raqBox.value = "<%=rpt%>";
	queryTypeBox.value = "<%=queryType%>";
	formBox.value = "<%=form%>";
	pagedCheck.checked = "<%=paged%>" == "1";
	scrollCheck.checked = "<%=scroll%>" == "1";
	queryBox.value = "<%=dqldb%>";
	anaBox.value = "<%=anaFile%>";
	anaDataSourceBox.value = "<%=analyseDB%>";
	anaSQLBox.value = "<%=analyseSQL%>";
	businessInputSubDir.value = "<%=busiDir%>";
	dataFileType.value = "<%=dataFileType%>";
	<%-- genpCheck.checked = "<%=genpf%>" == "1"; --%>
	<%-- reWrite.checked = "<%=reWrite%>" == "1"; --%>
	
	hideSpan( 'analysisSpan2' );
	hideSpan( 'analysisSpan' );
	hideSpan( 'analysisSpan1' );
	var analyseTypeRadios = $(".analyseType");
	for(var i = 0 ; i < analyseTypeRadios.length; i++){
		if(analyseTypeRadios[i].value == "<%=analyseType%>"){
			analyseTypeRadios[i].checked="checked";
			if(analyseTypeRadios[i].value == "sql") {
				showSpan( 'analysisSpan2' );
				hideSpan( 'analysisSpan1' );
			}else{
				showSpan( 'analysisSpan1' );
				hideSpan( 'analysisSpan2' );
			}
		}
	}
	
	typeChanged();
</script>
</body>
</html>

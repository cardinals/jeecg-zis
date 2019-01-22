<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.raqsoft.report.view.*"%>
<%@include file="/context/mytags.jsp"%>
<%
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
	String appmap = request.getContextPath();
%>
<script type="text/javascript" src="<%=appmap%><%=ReportConfig.raqsoftDir%>/pdfjs/toast/javascript/jquery.toastmessage.js"></script>
<script type="text/javascript">
var formData = new Object();
formData['rpx'] = "<%=request.getParameter("report")%>";
formData['params'] = "<%=request.getParameter("params")%>";
</script>
<link href="<%=appmap%><%=ReportConfig.raqsoftDir%>/pdfjs/toast/resources/css/jquery.toastmessage.css" type="text/css" rel="stylesheet" />
<div class="btnBar">
  <ul class="left">
    <li class="toggleBg borderRight">
      <ul class="fileOper">
        <li><a class="ICOhover" href="#" onClick="exportExcel('report1');return false;"><span title="导出excel" class="excel"></span></a></li>
        <li><a class="ICOhover" href="javascript:void(0);" onclick="download2()"><span title="导出excel-带日期格式" class="excel"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="exportPdf('report1');return false;"><span title="导出pdf" class="pdf"></span></a></li>
		<li><a class="ICOhover" href="#" onClick="exportWord('report1');return false;"><span title="导出word" class="word"></span></a></li>
      <li><a class="ICOhover" href="#" onClick="pdfPrintReport('report1');return false;"><span title="pdf打印预览" class="pdfprint"></span></a></li>
	  </ul>
    </li>
    <li class="toggleBg borderRight">
      <ul class="fileOper">
		<li><a class="ICOhover" href="#" onClick="directPdfPrintReport('report1');return false;"><span title="pdf直接打印" class="pdfprint"></span></a></li>
       </ul>
    </li>
    
    <li class="toggleBg borderRight">
      <ul class="fileOper">
		<li><a class="ICOhover"  href="javascript:void(0);" onclick="send_mail()" ><span title="发送邮件" class="pdfprint" value = "发送邮件"></span></a></li>
       </ul>
    </li>
    
    <li class="floatRight borderLeft">
      <ul class="fileOper">
         <Li><a class="ICOhover" href="#" onClick="try{toPage('report1',1);}catch(e){}return false;"><span title="首页" class="begin"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="try{prevPage('report1');}catch(e){}return false;"><span title="上一页" class="pre"></span></a></li>
        <Li><a class="ICOhover" href="#" onClick="try{nextPage('report1');}catch(e){}return false;"><span title="下一页" class="next"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="try{toPage('report1',getPageCount('report1'));}catch(e){}return false;"><span title="尾页" class="end"></span></a></li>    
      </ul>
    </li>
    <li class="floatRight">  <div style="display:inline-block; margin:9px 4px 3px 4px; float:left; ">第<span id="report1_currPage"></span>页/共<span id="t_page_span"></span>页&nbsp;&nbsp;</div></li>
  </ul>

</div>
<script language=javascript>
	var myToast, flashToast;
	function showToast() {
		myToast = $().toastmessage('showToast', {
		    text     : '正在加载打印页......',
		    sticky   : true,
		    position : 'middle-center',
		    type:         'notice'
		});		
	}
	function closeToast() {
		$().toastmessage('removeToast', myToast);
	}
	function showFlashToast() {
		flashToast = $().toastmessage('showToast', {
		    text     : '正在打印......',
		    sticky   : true,
		    position : 'middle-center',
		    type:     'notice'
		});		
	}
	function closeFlashToast() {
		$().toastmessage('removeToast', flashToast);
	}
	
	function send_mail(){
		  var sendUrl="<%=basePath%>/runqianExportExcel/exportExcel.do";
		  
		  var formData = new Object();
		  formData['rpx'] = "<%=request.getParameter("report")%>";
		  formData['params'] = "<%=request.getParameter("params")%>";
			$.ajax({
			  async : false,
			  cache : false,
		    type : 'POST',
		    url : sendUrl,
		    data : formData,
		    error : function() {
					//验证请求路径失败
		    	d.msg === "邮件发送失败!!!";
				alert(d.msg);
		    },
		    success : function(data) {// 用户名密码验证正确
		      var d = $.parseJSON(data);
		      if (d.success) {
					alert(d.msg);
					return true;
					
		     } else {
		    	 	d.msg === "邮件发送失败!!!";
					alert(d.msg);
				  	return false;

		      }
		    }
		  });
	}
	function download2(){
		  var formData = new Object();
		  formData['rpx'] = "<%=request.getParameter("report")%>";
		  formData['params'] = "<%=request.getParameter("params")%>";
		window.location.href="<%=basePath%>/runqianExportExcel/exportExcelWithDate.do?rpx="+"<%=request.getParameter("report")%>"+"&params="+"<%=request.getParameter("params")%>";
	}
	function exportExcelWithDate(){
		  var sendUrl="<%=basePath%>/runqianExportExcel/exportExcelWithDate.do";
		  
		  var formData = new Object();
		  formData['rpx'] = "<%=request.getParameter("report")%>";
		  formData['params'] = "<%=request.getParameter("params")%>";
			$.ajax({
			  async : false,
			  cache : false,
		    type : 'POST',
		    url : sendUrl,
		    data : formData,
		    error : function() {
					//验证请求路径失败
		    	d.msg === "导出excel失败!!!";
				alert(d.msg);
		    },
		    success : function(data) {// 用户名密码验证正确
		      var d = $.parseJSON(data);
		      if (d.success) {
					alert(d.msg);
					return true;
					
		     } else {
		    	 	d.msg === "导出excel失败!!!";
					alert(d.msg);
				  	return false;

		      }
		    }
		  });
	}
</script>

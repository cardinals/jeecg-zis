<%@ page contentType="text/html;charset=utf-8" %>
<%
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
%>
<div class="btnBar">
  <ul class="left">
    <li class="floatRight borderLeft">
      <ul class="fileOper">
         <Li><a class="ICOhover" href="#" onClick="try{toPage('report1',1);}catch(e){}return false;"><span title="首页" class="begin"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="try{prevPage('report1');}catch(e){}return false;"><span title="上一页" class="pre"></span></a></li>
        <Li><a class="ICOhover" href="#" onClick="try{nextPage('report1');}catch(e){}return false;"><span title="下一页" class="next"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="try{toPage('report1',getPageCount('report1'));}catch(e){}return false;"><span title="尾页" class="end"></span></a></li>    
      </ul>
    </li>
    <li class="floatRight">  <div style="display:inline-block; margin:6px 4px 6px 4px; float:left;font-size:14px">共<span id="report1_totalRecords"></span>条&nbsp;&nbsp;第<span id="report1_currPage"></span>页/共<span id="report1_totalPage"></span>页&nbsp;&nbsp;到<input type=text style="width:40px" onkeyup="if(event.keyCode==13)jumpToPage('report1',value)">页</div></li>
  </ul>
</div>

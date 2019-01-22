<%@ page contentType="text/html;charset=utf-8" %>
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
    <li class="floatRight">  <div style="display:inline-block; margin:9px 4px 3px 4px; float:left; ">第<span id="report1_currPage"></span>页/共<span id="t_page_span"></span>页&nbsp;&nbsp;</div></li>
  </ul>

</div>

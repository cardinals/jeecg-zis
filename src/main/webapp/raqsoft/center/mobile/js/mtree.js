var appRoot,labelColor, mouseOverColor, selectedColor, selectedBackColor;
var currNode;
//document.onmousemove = tree_unselectText;

function tree_unselectText() {
	document.selection.empty();
}

function tree_setEnv( app, lc, moc, sc, sbc ) {
	appRoot = app;
	labelColor = lc;
	mouseOverColor = moc;
	selectedColor = sc;
	selectedBackColor = sbc;
}

function tree_addSubNode() {
	if( currNode == null ) {
		alert( "请先选择一个树节点!" );
		return;
	}
	if( currNode.type == "1" ) {
		alert( "不能在报表节点下添加子节点！" );
		return;
	}
	if( currNode.type == "2" ) {
		alert( "不能在超链接节点下添加子节点！" );
		return;
	}
	parent.document.getElementById("showProp").src = "addSubNode.jsp?action=1&id=" + currNode.getAttribute("nodeid");
}

function tree_insertNode() {
	if( currNode == null ) {
		alert( "请先选择一个树节点!" );
		return;
	}
	parent.document.getElementById("showProp").src = "addSubNode.jsp?action=2&id=" + currNode.getAttribute("nodeid");
}

function tree_moveNode( updown ) {
	
	if( currNode == null ) {
		alert( "请先选择一个树节点!" );
		return;
	}
	$.ajax({
		type:"post",
		url:appRoot + "/reportCenterServlet?action=9&id="+currNode.getAttribute("nodeid")+"&flag="+updown,
		data:{},
		success:function(strRet){
			tree_setCurrNode( document.getElementById( "id_" + strRet.substring( 8 ) ), false );
		},
		error:function(){
			alert( "移动节点时错误:\n" + strRet );
		}
	});
}

function tree_deleteNode() {
	if( currNode == null ) {
		alert( "请先选择一个节点!" );
		return;
	}
	if( currNode.id == "id_0" ) return;
	var name = currNode.getAttribute("label");
	if( !window.confirm( "删除后将不能恢复，真的要删除“" + name + "”节点吗？" ) ) return;
	$.ajax({
		type:"post",
		url:appRoot + "/reportCenterServlet?action=8&id="+currNode.getAttribute("nodeid"),
		data:{},
		success:function(strRet){
			console.log(strRet);
			console.log(strRet == "nomore");
			if( strRet == "nomore" ){
				tree_setCurrNode( document.getElementById( "id_0" ), false );
			}else{
				tree_setCurrNode( document.getElementById( "id_" + strRet.substring( 8 ) ), false );
			}
		},
		error:function(){
			alert( "删除节点时错误:\n" + strRet );
		}
	});
}

function tree_iconClick( obj ) {
	if( obj == null ) return;
   	var subdiv = document.getElementById( "_div_" + obj.id.substring( 5 ) );
   	if( subdiv == null ) return;
   	var nodeValue = obj.attributes.getNamedItem( "nodevalue" );
   	var oldnodevalue = nodeValue.value;
   	if( oldnodevalue == "0" || oldnodevalue == "2" )
      	subdiv.style.display = "";
   	if( oldnodevalue == "0" ) {
      	nodeValue.value = "1";      
      	obj.src = obj.src.substring( 0, obj.src.indexOf( "plus.gif" ) ) + "minus.gif";
   	}
   	if( oldnodevalue == "2" ) {
      	nodeValue.value = "3";      
      	obj.src = obj.src.substring( 0, obj.src.indexOf( "lastplus.gif" ) ) + "lastminus.gif";
   	}
   	if( oldnodevalue == "1" || oldnodevalue == "3" )
      	subdiv.style.display = "none";
   	if( oldnodevalue == "1" ) {
      	nodeValue.value = "0";      
      	obj.src = obj.src.substring( 0, obj.src.indexOf( "minus.gif" ) ) + "plus.gif";
   	}
   	if( oldnodevalue == "3" ) {
      	nodeValue.value = "2";      
      	obj.src = obj.src.substring( 0, obj.src.indexOf( "lastminus.gif" ) ) + "lastplus.gif";
   	}
}

function tree_setCurrNode( node, noRefreshProp ) {
	if( node == null ) return;
	if( currNode != null ) {
		currNode.style.backgroundColor = "";
		currNode.style.color = labelColor;
	}
	node.style.backgroundColor = selectedBackColor;
	node.style.color = selectedColor;
	currNode = node;
}


function showToolSpan( node , isRootNode ){
	var tr = node.parentElement || node.parentNode;
	var type = node.getAttribute("type");
	rpt = encodeURIComponent(node.getAttribute("rpt"));
	if(isRootNode){
		var newImg1 = $("<td onClick=\"tree_addSubNode()\" class=\"toolSpan\"><img alt=\"添加子节点\" src=\"./images/add-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
		$(tr).append(newImg1);
		var newImg3 = $("<td onClick=\"report_nodeClick( currNode )\" class=\"toolSpan\"><img alt=\"浏览\" src=\"./images/detail-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
		$(tr).append(newImg3);
		/*var newImg4 = $("<td onClick=\"tree_deleteNode()\" class=\"toolSpan\"><img src=\"./images/del-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
		$(tr).append(newImg4);*/
		return;
	}
	if(type == "0"){
		var newImg1 = $("<td onClick=\"tree_addSubNode()\" class=\"toolSpan\"><img alt=\"添加子节点\" src=\"./images/add-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
		$(tr).append(newImg1);
	}
	var newImg2 = $("<td onClick=\"tree_insertNode()\" class=\"toolSpan\"><img alt=\"插入同级节点\" src=\"./images/insert-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
	$(tr).append(newImg2);
	if(type == "1" || type == "2"){
		var newImg3 = $("<td onClick=\"report_nodeClick( currNode )\" class=\"toolSpan\"><img alt=\"浏览\" src=\"./images/detail-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
		$(tr).append(newImg3);
	}
	var newImg4 = $("<td onClick=\"tree_deleteNode()\" class=\"toolSpan\"><img alt=\"删除节点\" src=\"./images/del-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
	$(tr).append(newImg4);
	var newImg5 = $("<td onClick=\"tree_moveNode('up')\" class=\"toolSpan\"><img alt=\"上移\" src=\"./images/moveup-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
	$(tr).append(newImg5);
	var newImg6 = $("<td onClick=\"tree_moveNode('down')\" class=\"toolSpan\"><img alt=\"下移\" src=\"./images/movedown-icon.gif\" border=\"noborder\" class=\"toolImg\"/></td>");
	$(tr).append(newImg6);
}



function tree_getNodeExpanded() {
	return tree_getDivExpanded( document.body );
}

function tree_getDivExpanded( pobj ) {
	var s = "";
	for( var i = 0; i < pobj.childNodes.length; i++ ) {
		var obj = pobj.childNodes[i];
		if( obj.tagName == "DIV" ) {
			if( obj.id.indexOf( "_div_" ) == 0 ) {
				var status = "1";
				if( obj.style.display == "none" ) status = "0";
				if( s.length > 0 ) s += ",";
				s += obj.id.substring( 5 ) + "," + status;
			}
		}
		var rtn = tree_getDivExpanded( obj );
		if( rtn.length > 0 ) {
			if( s.length > 0 ) s += ",";
			s += rtn;
		}
	}
	return s;
}

function tree_restoreStatus( nodesStatus, position, currId ) {
   	tree_setCurrNode( document.getElementById( "id_" + currId ), true );
   	var includeCurrNode = false;
	var ids = nodesStatus.split( "," );
	for( var i = 0; i < ids.length; i += 2 ) {
		if( currNode != null && ids[i] == currNode.getAttribute("nodeid") ) includeCurrNode = true;
		if( ids[i] == "0" ) continue;
		if( ids[i+1] == "1" ) {
   			var subdiv = document.getElementById( "_div_" + ids[i] );
   			if( subdiv == null ) continue;
	      	subdiv.style.display = "";
   			var obj = document.getElementById( "_img_" + ids[i] );
   			var nodeValue = obj.attributes.getNamedItem( "nodevalue" );
   			var oldnodevalue = nodeValue.value;
   			if( oldnodevalue == "0" ) {
      			nodeValue.value = "1";      
      			obj.src = obj.src.substring( 0, obj.src.indexOf( "plus.gif" ) ) + "minus.gif";
   			}
   			if( oldnodevalue == "2" ) {
      			nodeValue.value = "3";      
      			obj.src = obj.src.substring( 0, obj.src.indexOf( "lastplus.gif" ) ) + "lastminus.gif";
   			}
   		}
   	}
   	if( currNode != null && !includeCurrNode ) tree_iconClick( document.getElementById( "_img_" + currNode.getAttribute("nodeid") ) );
   	document.body.scrollTop = position;
}

function urlEncode( str )
{
	var dst = "";
	for ( var i = 0; i < str.length; i++ )
	{
		switch ( str.charAt( i ) )
		{
			case ' ':
				dst += "+";
				break;
			case '!':
				dst += "%21";
				break;
			case '\"':
				dst += "%22";
				break;
			case '#':
				dst += "%23";
				break;
			case '$':
				dst += "%24";
				break;
			case '%':
				dst += "%25";
				break;
			case '&':
				dst += "%26";
				break;
			case '\'':
				dst += "%27";
				break;
			case '(':
				dst += "%28";
				break;
			case ')':
				dst += "%29";
				break;
			case '+':
				dst += "%2B";
				break;
			case ',':
				dst += "%2C";
				break;
			case '/':
				dst += "%2F";
				break;
			case ':':
				dst += "%3A";
				break;
			case ';':
				dst += "%3B";
				break;
			case '<':
				dst += "%3C";
				break;
			case '=':
				dst += "%3D";
				break;
			case '>':
				dst += "%3E";
				break;
			case '?':
				dst += "%3F";
				break;
			case '@':
				dst += "%40";
				break;
			case '[':
				dst += "%5B";
				break;
			case '\\':
				dst += "%5C";
				break;
			case ']':
				dst += "%5D";
				break;
			case '^':
				dst += "%5E";
				break;
			case '`':
				dst += "%60";
				break;
			case '{':
				dst += "%7B";
				break;
			case '|':
				dst += "%7C";
				break;
			case '}':
				dst += "%7D";
				break;
			case '~':
				dst += "%7E";
				break;
			default:
				dst += str.charAt( i );
				break;
		}
	}
	return dst;
}

function tapNode(url){
	console.log(window.top.document);
	var mf = top.document.getElementById('mainframe');
	console.log(mf);
	console.log(url);
	mf.src = url;
}
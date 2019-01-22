

function pizhu(){//===================这个js函数适合代码生成开发开发==================
	
	update('周报批注','orgptFucmodWeekrepController.do?goUpdate_pizhu','orgptFucmodWeekrepList',null,null);
	
}

/**增强js:	
 * 获取列表中选中行的数据-推荐使用
 * @param field 数据中字段名
 * @return 选中行的给定字段值
 */
function getorgpt_fucmod_weekrepListSelected(field){
	var row = $('#orgpt_fucmod_weekrepList').datagrid('getSelected');
	if(row!=null){value= row[field];
	}else{
		value='';
	}
	return value;
}
/**
 * 增强js
 */
function pizhu_bak(){//===================这个js函数适合online开发==================
var rowsData = getorgpt_fucmod_weekrepListSelected('id');

		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}


		url = 'orgptFucmodWeekrepController.do?goUpdate_pizhu&id='+rowsData;
		
		$.dialog({
			content: "url:"+url,
			lock : true,
			title: "周报批注",
			opacity : 0.3,
			width:650,
			height:400,
			cache:false,
		    ok: function(){
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});
}




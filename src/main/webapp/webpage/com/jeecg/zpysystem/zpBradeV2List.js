function jishu(){

var rowsData = getjform_zp_b_grade_v2ListSelected('id');


		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}


		url = 'zpGradeController.do?goUpdate_js&id='+rowsData;
		
		$.dialog({
			content: "url:"+url,
			lock : true,
			title: "技术面试评分",
			opacity : 0.3,
			width:500,
			height:300,
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
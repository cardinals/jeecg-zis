$(document).ready(function(){
 
    var id = $("input[name='id']").val();

    
				
			$("#targetorginwk").find("option").remove(); 
			  
			  var  customerName="orgptFucmodCustomerController.do?getCustomerName";//获取机构名称
			  
				$.ajax({
				  async : false,
				  cache : false,
				type : 'POST',
				url : customerName,// 请求的action路径
				
				error : function() {
				  
						//获取机构名称的请求成功
				},
				success : function(data) {
				  var d = $.parseJSON(data);
				  if (d.success) {//获取机构名称数据成功
						
						$.each(d.attributes.customerNames, function(key, val) {
						
							$("#targetorginwk").append('<option value="' + val.orgcustomername + '">' + val.orgcustomername + '</option>');
							
						});
						   
						
				 } else {
					 
						showErrorMsg(d.msg);//获取机构名称数据失败
						return false;

				  }
				}
			  });



	if(id){//编辑或查看界面

		
		


				

				//$("#targetorginwk").find("option").remove(); 
				  
				  var  customerName="orgptFucmodCustomerController.do?getCustomerNa";//获取机构名称
				   var formData = new Object();
				formData['id'] = id ? id : "";
					$.ajax({
					  async : false,
					  cache : false,
					type : 'POST',
					url : customerName,// 请求的action路径
					data : formData,
					error : function() {
					  
							//获取机构名称的请求成功
					},
					success : function(data) {
					  var d = $.parseJSON(data);
					  if (d.success) {//获取机构名称数据成功
							
							
				$("#targetorginwk").val(d.attributes.customername);
                                $("#accessflag").val(d.attributes.accessflag);
                                 $("#cooperationpoint").val(d.attributes.cooperationpoint);


						} else {
						 
							showErrorMsg(d.msg);//获取机构名称数据失败
							return false;

					  }
					}
				  });

				


				
       }
});




//通用弹出式文件上传
function commonUpload(callback){
    $.dialog({
           content: "url:systemController.do?commonUpload",
           lock : true,
           title:"文件上传",
           zIndex:2100,
           width:700,
           height: 200,
           parent:windowapi,
           cache:false,
       ok: function(){
               var iframe = this.iframe.contentWindow;
               iframe.uploadCallback(callback);
                   return true;
       },
       cancelVal: '关闭',
       cancel: function(){
       } 
   });
}
function browseImages(inputId, Img) {// 图片管理器，可多个上传共用
}
function browseFiles(inputId, file) {// 文件管理器，可多个上传共用
}
function decode(value, id) {//value传入值,id接受值
	var last = value.lastIndexOf("/");
	var filename = value.substring(last + 1, value.length);
	$("#" + id).text(decodeURIComponent(filename));
}
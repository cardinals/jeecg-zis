$(document).ready(function(){
    var id = $("input[name='id']").val();

    
				
			$("#acountShortname").find("option").remove(); 
			  
			  var  customerName="bankCodeController.do?getBankEntity";//获取银行实体
			  
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
					  $("#acountShortname").append('<option value="-1"> -- 请选择 -- </option>');
						
						$.each(d.attributes.bank, function(key, val) {
						
							$("#acountShortname").append('<option value="' + val.bankname + '">' + val.bankname + '</option>');
							
						});
						   
						
				 } else {
					 
						showErrorMsg(d.msg);//获取机构名称数据失败
						return false;

				  }
				}
			  });
//===productStart
				$("#onlineProduct").find("option").remove(); 
				  
				  var  customerName="productCodeController.do?getProductEntity";//获取银行实体
				  
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
						  $("#onlineProduct").append('<option value="-1"> -- 请选择 -- </option>');
							
							$.each(d.attributes.product, function(key, val) {
							
								$("#onlineProduct").append('<option value="' + val.productname + '">' + val.productname + '</option>');
								
							});
							   
							
					 } else {
						 
							showErrorMsg(d.msg);//获取机构名称数据失败
							return false;

					  }
					}
				  });		
				
//===productEnd
					//编辑界面
					if(id){
						
						  var  bankNumberrName="bankAccountController.do?getBankNumberType";
						   var formData = new Object();
						formData['id'] = id ? id : "";
							$.ajax({
							  async : false,
							  cache : false,
							type : 'POST',
							url : bankNumberrName,
							data : formData,
							error : function() {
							  
									
							},
							success : function(data) {
							  var d = $.parseJSON(data);
							  if (d.success) {
								  sffxyh(d.attributes.bankNumberType);
								  addColor(d.attributes.acountStatus);
								} else {
								 
									showErrorMsg(d.msg);
									return false;

							  }
							}
						  });
						 
					}

});





//=========================================

//初始化下标
function resetTrNum(tableId) {
	alert(232323);
	$tbody = $("#"+tableId+"");
	$tbody.find('>tr').each(function(i){
		$(':input, select,button,a', this).each(function(){
			var $this = $(this),validtype_str = $this.attr('validType'), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
			if(validtype_str!=null){
				if(validtype_str.indexOf("#index#") >= 0){
					$this.attr("validType",validtype_str.replace('#index#',i));
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i+1);
	});
}
//通用弹出式文件上传
function commonUpload(callback,inputId){
    $.dialog({
           content: "url:systemController.do?commonUpload",
           lock : true,
           title:"文件上传",
           zIndex:getzIndex(),
           width:700,
           height: 200,
           parent:windowapi,
           cache:false,
       ok: function(){
               var iframe = this.iframe.contentWindow;
               iframe.uploadCallback(callback,inputId);
               return true;
       },
       cancelVal: '关闭',
       cancel: function(){
       } 
   });
}
//通用弹出式文件上传-回调
function commonUploadDefaultCallBack(url,name,inputId){
	$("#"+inputId+"_href").attr('href',url).html('下载');
	$("#"+inputId).val(url);
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

//判断非空字段js start

function sffxyh(optionValue){
	  //业务逻辑...
	  if(optionValue!=5){
		  //账户状态，设置为可选，“正常”、“已注销”、“募集在用”（只有募集账户显示此项）
		  $("[name='acountStatus'] option[value='2']").remove(); 
	  }
	  if(optionValue==6){
		  //不做任何操作...
	  }else{
		  setCommonAttr();
		  if(optionValue==1||optionValue==3||optionValue==4||optionValue==5){
			  $("#acountInterestRate").attr("datatype","*").removeAttr("ignore");
			  $("#interestRateDate").attr("datatype","*").removeAttr("ignore");
		  }
		  
		  if(optionValue==2){
			  $("#isLiushui").attr("datatype","*").removeAttr("ignore");//是否流水户 必选！
		  }
		  if(optionValue==5){//5代表募集账户
			  $("#isPreEnd").attr("datatype","*").removeAttr("ignore");//是否可以提前结息 必选！
			  //账户状态，设置为可选，“正常”、“已注销”、“募集在用”（只有募集账户显示此项）
			  //$("[name='acountStatus'] option[value='2']").remove(); 
			  $("[name='acountStatus']").append('<option value="2">募集在用</option>');
			  
		  }
	  }
	  
}

function setCommonAttr(){
	  $("#acountName").attr("datatype","*").removeAttr("ignore");
	  $("#acountFullname").attr("datatype","*").removeAttr("ignore");
	  $("[name='acountShortname']").attr("datatype","*").removeAttr("ignore");
	  $("#kaihuDate").attr("datatype","*").removeAttr("ignore");
	  //$("#acountStatus").attr("datatype","*").removeAttr("ignore");
	  $("#contact").attr("datatype","*").removeAttr("ignore");
	  $("#contactType").attr("datatype","*").removeAttr("ignore");
}
function addColor(optionValue){
	if(optionValue==-1){
		$("#acountStatSpan").css("background-color","White"); 
	}
	if(optionValue==0){
		$("#acountStatSpan").css("background-color","gray"); 
		}
	if(optionValue==1){
		 $("#acountStatSpan").css("background-color","green");
	}
	if(optionValue==2){
		 $("#acountStatSpan").css("background-color","red");
	}
}

$("select#acountStatus").change(function(){
    addColor($(this).val());
 });
/*function sffxyh(optionValue){
	 
	  //业务逻辑...
	  if(optionValue==6){
		  //不做任何操作...
	  }else{
		  setCommonAttr();
		  if(optionValue==1||optionValue==3||optionValue==4||optionValue==5){
			  $("#acountInterestRate").attr("datatype","*").removeAttr("ignore");
			  $("#interestRateDate").attr("datatype","*").removeAttr("ignore");
		  }
		  
		  if(optionValue==2){
			  $("#isLiushui").attr("datatype","*").removeAttr("ignore");//是否流水户 必选！
		  }
		  if(optionValue==5){
			  $("#isPreEnd").attr("datatype","*").removeAttr("ignore");//是否可以提前结息 必选！
		  }
	  }
	  
}

function setCommonAttr(){
	  $("#accountNumber").attr("datatype","*").removeAttr("ignore");
	  $("#acountName").attr("datatype","*").removeAttr("ignore");
	  $("#acountFullname").attr("datatype","*").removeAttr("ignore");
	  $("[name='acountShortname']").attr("datatype","*").removeAttr("ignore");
	  $("#kaihuDate").attr("datatype","*").removeAttr("ignore");
	  $("#acountStatus").attr("datatype","*").removeAttr("ignore");
	  $("#contact").attr("datatype","*").removeAttr("ignore");
	  $("#contactType").attr("datatype","*").removeAttr("ignore");
}*/

//判断非空字段js end
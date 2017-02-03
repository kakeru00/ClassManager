
$(document).ready(function(){
	
	function initFileInput(ctrlName) {    
		var control = $(ctrlName); 
		control.fileinput({
			language: 'zh', //设置语言
			uploadUrl: "handIn", //上传的地址
			allowedFileExtensions : ['jpg', 'png','gif','txt','pdf','zip','rar','doc','docx'],//接收的文件后缀
			dropZoneEnabled: true,
			browseClass: "btn btn-primary", //按钮样式             
			maxFileSize: 5000
		});
	}
	initFileInput("#input-id");
	
	$("#btn_update").on("click",function(){
			alert("click");
			$.ajax({
				url : "../student/update",
				cache : false,
				success : function(html) {
					$("#update").append(html);
				}
					
			});
	});
	
	$("form").submit(function(){
		var data = new FormData($(this)[0]);
		var url = "handIn";
		$.ajax({
			  url: url,
			  type: "POST",
			  data: data,
			  processData: false,  // 告诉jQuery不要去处理发送的数据
			  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
			  success: function(result){
				  
				  if(result==""){
						alert("上传失败，请重试");
						
					}else{
						alert("上传成功！");
						
					}
			  	},
			  error: function(){
				  alert("请检查文件正确性后重试");
			  	}
			  }
			);
		return false;
	});
	
});
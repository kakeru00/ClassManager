<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
 		$("#btn_submit").click(function(){
			var url = $(".modal form").attr("action");
 			var data = $(".modal form").serialize();
 			
			 $.ajax({
				  url: url,
				  type: "POST",
				  data: data,
				  success: function(result){
					  if(result=="success"){
							$("#addSucc").show(0).delay(1500).hide(0);
						}
						else  {
							$("#fail strong").text("操作失败！");
							$("#fail").show(0).delay(1500).hide(0);
						}
					  
				  },
				  error: function(){
					  $("#fail strong").text("操作失败！输入数据不合法，或该班级id已存在。");
					  $("#fail").show(0).delay(1500).hide(0);
				  	}
				});
				  
			
		});
		
	});
</script>
<title>add class</title>
</head>
<body>


<div class="modal"  id="addClassModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建新的班级管理员</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addClass" action="class/add" method="post">
					<div class="form-group has-feedback">
						<label class="col-sm-3 control-label">班级编号</label>
						<div class="col-sm-9">
						<span class="glyphicon glyphicon-user form-control-feedback"></span>
							<input class="form-control" type="text" name="classId" required="required"/>
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-3 control-label">管理密码</label>
						<div class="col-sm-9">
							<div class="">
			<!-- 				
								<button class="btn btn-default" disabled="disabled">
				                     <span class="glyphicon glyphicon-user"></span> 
				                  </button>
							</span> -->
								
								<span class="glyphicon glyphicon-lock form-control-feedback"></span>
								<input class="form-control" type="password" name="password" required="required"/>
			               </div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
				</button>
				<button type="button" id="btn_submit" form="addClass" class="btn btn-primary" data-dismiss="modal">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
				</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
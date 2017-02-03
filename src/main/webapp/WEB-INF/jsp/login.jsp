<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var loginForm = $("#login");
		$("#admin").click(function(){
			loginForm.attr("action","admin")
						.submit();
			
			
		});
		$("#student").click(function(){
			loginForm.attr("action","student")
						.submit();
			
		});
		$("#teacher").click(function(){
			loginForm.attr("action","teacher")
						.submit();
			
		});
		loginForm.submit(function(){
			var url = loginForm.attr("action");
			var data = loginForm.serialize();
				
			$.post(url,data,function(result){
				var pathName = window.document.location.pathname;
				var basePath = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);//获取网站根路径
				if(result=="学生登录成功"){
					location.href=basePath+"/homework/homeworks";
				}else if(result=="管理员登录成功"){
					location.href=basePath+"/teacher/teachers";
				}else if(result=="超管登录成功"){
					location.href=basePath+"/class/classes";
				}else if(result=="教师登录成功"){
					location.href=basePath+"/main";
				}
					$("span").text(result);
				
			});
			return false;
		});
		
		$("#btn_addClass").click(function(){
			$(".modal").modal();
			
		});
		
	})
</script>
</head>
<body>
<!-- 	<form action="login" method="post">
		<input type="text" name="id"/>
		<input type="password" name="password"/>
		<input type="submit" value="登录"/>
	</form> -->
<div class="container-fluid">
	<div id="addSucc" class="alert alert-success navbar navbar-inverse navbar-fixed-top" hidden="hidden">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong>添加成功！</strong>
	</div>
	<div id="fail" class="alert alert-warning navbar navbar-inverse navbar-fixed-top" hidden="hidden">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong></strong>
	</div>
	<%@include file="addClass.jsp" %>
	<div class="row">
	
		<div class="col-md-12">
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h3 class="text-center">
				Class
			</h3>
			<h6 class="text-center">登录后使用</h6>
		</div>
	</div>

	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
			<form class="form-horizontal" role="form" id="login" action="<%=request.getContextPath()%>/student" method="post">
				<div class="form-group">
					 
					<label class="col-sm-2 control-label">
						学号
					</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="id" placeholder="帐号" />
					</div>
				</div>
				<div class="form-group">
					 
					<label class="col-sm-2 control-label">
						密码
					</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password" placeholder="密码" />
					</div>
				</div>
				
			<!-- 	<div class="form-group">
					 
					<label class="col-sm-2 control-label">
						班级
					</label>
					<div class="col-sm-10">
						<select name="class" form="login">
							<option></option>
						</select>
					</div>
				</div> -->
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 
						<button type="button" id="student" class="btn btn-default">
							学生登录
						</button>
						<button type="button" id="teacher" class="btn btn-default">
							教师登录
						</button>
						<input type="button" id="admin" value="管理员登录" class="btn btn-default">
						<button type="button" id="btn_addClass" class="btn btn-default">
							申请一个班级
						</button>	
						
					</div>
				</div>
			</form>
			<span class="text-danger"></span>
		</div>
		<div class="col-md-4">
		</div>
	</div>
</div>


</body>
</html>
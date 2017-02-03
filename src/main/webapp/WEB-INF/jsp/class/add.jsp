<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<title>add class</title>
</head>
<body>
add class page

<div class="row">
		<div class="col-md-12">
			<h3 class="text-center">
				new Class
			</h3>
			<h6 class="text-center">创建新的班级管理员</h6>
		</div>
</div>

<div class="row">

	<div class="col-sm-4"></div>
	<form class="form-horizontal col-sm-4" role="form" action="add" method="post">
		<div class="form-group has-feedback">
			<label class="col-sm-3 control-label">班级编号</label>
			<div class="col-sm-9">
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
				<input class="form-control" type="text" name="classId"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">管理密码</label>
			<div class="col-sm-9">
				<div class="input-group">
<!-- 				
					<button class="btn btn-default" disabled="disabled">
	                     <span class="glyphicon glyphicon-user"></span> 
	                  </button>
				</span> -->
					
					
					<input class="form-control" type="password" name="password"/>
					<span class="input-group-btn">
	                  <button class="btn btn-default" type="submit">
	                     Go!
	                  </button>
	               </span>
               </div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4">
				<input class="btn btn-default " type="submit" value="ok"/>
				<a class="btn btn-default " href="<%=request.getContextPath()%>/class/classes">返回</a>
			</div>
		</div>
	</form>
	<div class="col-sm-4"></div>
</div>
</body>
</html>
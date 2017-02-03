<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>


	
	<div class="row">
		<div class="col-md-12">
			<h3 class="text-center">
				new Student
			</h3>
			<h6 class="text-center">新的教生</h6>
		</div>
</div>

<div class="row">

	<div class="col-sm-4"></div>
	<form class="form-horizontal col-sm-4" role="form" action="add" method="post">
		<div class="form-group">
			<label class="col-sm-3 control-label">学号</label>
			<div class="col-sm-9">
				<div class="input-group">
	               <span class="input-group-addon">
	                  
	                    ${classAdmin.classId}
	                 
	               </span>
	               <input type="text" class="form-control" name="id">
	            </div><!-- /input-group -->
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">密码</label>
			<div class="col-sm-9">
				<div class="input-group">
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
			</div>
		</div>
	</form>
	<div class="col-sm-4"></div>
</div>
</body>
</html>
</body>
</html>
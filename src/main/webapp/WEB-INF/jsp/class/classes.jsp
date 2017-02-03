<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function update(classId){
		var f = $("#"+classId);
		if(f.css("display")=="inline"){
			
			f.hide();
		}
		else
			f.css("display","inline");
		
	}
	$(document).ready(function(){

		
	});
</script>

</head>
<body>
<div class="container">
<a href="<%=request.getContextPath()%>/logout">注销</a>
<div class="row">all classes page
	<h3 class="text-center">目前所有班级</h3>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:forEach items="${classes}" var="c" >
		<!-- 	style="border-radius:8px;border:1px solid" -->
			<div class="col-sm-6">
				<div class="panel panel-info ">
				<div class="panel-heading">
					<h4 class="panel-title">班级编号：${c.classId}</h4>
					
				</div>
				<div class="panel-body">
					<div class="col-sm-7">
					<a href="${c.classId}/delete" type="button" class="btn btn-default">删除</a>
					<a href="#" id="update" type="button" class="btn btn-default" onclick="update(${c.classId});">修改密码</a>
					</div>
					<div class="col-sm-5">
					<form  id="${c.classId}" action="${c.classId}/update" method="post" hidden="true">
						<input value="${c.classId}" type="text" name="classId" hidden="true"/>
						
						<div class="input-group">
							<input  class="form-control" type="password" name="password" placeholder="新密码" required="required"/>
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default">ok</button>
							</span>
						</div>
						
					</form>
					</div>
				</div>
				</div>
			</div>
			
			
			
		</c:forEach>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row ">
<br/><a href="add"  class="btn btn-default btn-lg btn-success col-sm-offset-5  col-sm-2">++++新建班级++++</a>
</div>



</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">ClassManager</a>
		</div>
		<div>
			<ul class="nav navbar-nav ">
				<c:if test="${sessionScope.student!=null }">
					<li class="active">
						<a href="<%=request.getContextPath()%>/student/${sessionScope.student.id}/update">个人信息</a>
					</li>
					<li class="">
						<a href="<%=request.getContextPath()%>/homework/homeworks">课后作业</a>
					</li>
					<li class="active"><a href="#">集体活动</a></li>
				</c:if>
				<c:if test="${sessionScope.teacher!=null }">
					<li class="active">
					<a href="<%=request.getContextPath()%>/teacher/${sessionScope.teacher.id}/update">个人信息</a>
					</li>
					<li class="">
						<a href="<%=request.getContextPath()%>/homework/homeworks">课后作业</a>
					</li>
					<li class="active"><a href="#">集体活动</a></li>
				</c:if>
			</ul>
		</div>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					当前用户：${sessionScope.teacher.name}${sessionScope.classAdmin.classId}${sessionScope.student.name}
			</a></li>
			<li><a href="<%=request.getContextPath()%>/logout"><span
					class="glyphicon glyphicon-log-out"></span> 注销&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</nav>
</body>
</html>
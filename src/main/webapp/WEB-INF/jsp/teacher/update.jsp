<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/>
<div class="container">
	<div class="row">
		<c:if test="${classAdmin != null}"><h3 class="text-center">更改信息</h3></c:if>
		<c:if test="${classAdmin == null}">
			<h3 class="text-center">完善个人信息</h3>
			
		</c:if>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<form:form class="form-horizontal" method="post" modelAttribute="teacher">
				<input type="hidden" name="id" value="${teacher.id }"/>  
				<div class="form-group">
					<label class=" control-label">姓名</label>
					<form:input path="name" required="required" class="form-control" />
				</div>
				<div class="form-group">
					<label class="control-label">密码</label>
					<input class="form-control"  name="password" type="password" value="${student.password}" required="required"/>
				</div>
				<div class="form-group">
					<label class="control-label">邮箱</label>
					<form:input class="form-control"  path="email"/>
				</div>
				<div class="form-group">
					<label class="control-label">手机</label>
					<form:input class="form-control"  path="phone"/>
				</div>
				<c:if test="${classAdmin != null}">
				
					<div class="form-group">
						<label class="control-label">权限</label>
						<form:select class="form-control"  path="access">
							<form:option value="true">有</form:option>
							<form:option value="false">无</form:option>
						</form:select>
					</div>
				</c:if>
				<c:if test="${classAdmin == null}">
					<input type="hidden" name="status" value="true"/>  
				</c:if>
				<%-- <form:input path="classAdmin.classId" hidden="true"/>
				<input name="password" type="password" value="${student.classAdmin.password}" hidden="true"/> --%>
				<button type="submit" class="btn btn-default" >提交</button>
			</form:form>
		</div>
		<div class="col-sm-4"></div>
	</div>
</div>
</body>
</html>
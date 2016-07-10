<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" modelAttribute="user">
		<form:input path="name"/>
		<form:password path="password"/>
		<form:input path="email"/>
		<input type="submit" />
	</form:form>
	
	<form method="post">
		<input type="text" name="name"/>
		<input type="password" name="password"/>
		<input type="text" name="email"/>
		<input type="submit" />
	</form>
</body>
</html>
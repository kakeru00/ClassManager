<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/test.css"/>

<title>Insert title here</title>

</head>
<body>
	<a href="user/users">find</a><br/>
	<a href="user/add">add</a>
<%-- 	<form:form action="user/upload" method="post" enctype="multipart/form-data">
	
	<input type="file" name="file"/><br/>
	<input type="submit"/>
	</form:form> --%>
	<form action="user/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/><br/>
		<input type="submit"/>
	</form>
	<form action="user/download">
		<input type="text" name="fileName"/>
		<input type="submit"/>
	</form>
</body>
</html>
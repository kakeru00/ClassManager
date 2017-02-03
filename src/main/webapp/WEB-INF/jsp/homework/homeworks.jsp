<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="com.zh.entity.Student"%>
<%@ page  import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/fileinput.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/fileinput_locale_zh.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js" ></script>
<script src="<%=request.getContextPath()%>/resources/js/handInHomework.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/> 
<div class="container">
	<c:if test="${student.status==false}">
		<% 	Student student = (Student)session.getAttribute("student");
			String url = request.getContextPath()+"/student/"+student.getId()+"/update";
			response.sendRedirect(url); %>
		
	</c:if>
	
	<div id="update" class="">
		
	</div>
	<div class="row">
		<!-- <button id="btn_update">update</button> -->
		
		
		<c:if test='${sessionScope.teacher != null}'>
			<a href="<%=request.getContextPath()%>/homework/add" class="btn btn-default">add homework</a><br/>
		</c:if>
	</div>
		<br/>
		<br/>
		<%  request.setAttribute("date",new Date().getTime()); %>
	<c:forEach  items="${homeworks}" var="h" varStatus="s"> 
		<div class="panel panel-info">
			<div class="panel-heading">
			${s.count }/${homeworks.size()}、作业标题 ：${h.title} &nbsp;&nbsp;&nbsp;———截止至 ${h.conclude}
			
			<c:if test="${sessionScope.teacher!=null}">
				<a href="<%=request.getContextPath()%>/homework/${h.id }/delete"  class="btn btn-default">delete</a>
				<a href="<%=request.getContextPath()%>/homework/${h.id }/update"  class="btn btn-default">update</a>
				<a href="<%=request.getContextPath()%>/homework/collect/${h.id }"  class="btn btn-default">collect</a>
			</c:if>
			</div>
			
			<div class="panel-body">
			${h.content} <br/>
			<c:if test="${h.conclude.time > date }">
				<div  >
					<form>
						<input type="text" name="homeworkId" value="${h.id }" hidden="hidden"/>
						<div class="form-group">
							<label class="col-sm-1 control-label">交作业</label>
							<div class="col-sm-5">
								<input class="file form-control"  type="file" name="file" data-show-preview="false"/>
							</div>
						</div>
					</form>
				</div>
			</c:if>
			<c:if test="${h.conclude.time < date }">
				<h4 class="text-center text-danger">本作业已过期</h4>
			</c:if>
			</div>
		</div>
		<br/>
		<br/>
	</c:forEach>
	
</div>
</body>
</html>
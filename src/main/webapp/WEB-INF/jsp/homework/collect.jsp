<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#btn_stat").click(function(){
			
			$(".modal").modal();
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/>
<div class="container">

<button class="btn btn-default" id="btn_stat">欠交统计</button>
<c:if test="${stu_hwks.size()!=0}">
	
	<a class="btn btn-default" href="../${stu_hwks.get(0).homework.id}/downloadAll">一键收取</a>
</c:if>
<div class="modal"  id="statisticsModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						以下同学尚未提交本作业
					</h4>
			</div>
			<div class="modal-body">
				<div class="col-sm-6">
					<table class="table table-striped">
						<tbody>
							
							<c:forEach items="${students}" var="s"  step="2">
								<tr><td>${s.id}————${s.name}</td></tr>
							</c:forEach>
							
							
						</tbody>
					
					</table>
				</div>
				<div class="col-sm-6">
				<table class="table table-striped">
					<tbody>
						
						<c:forEach items="${students}" var="s" begin="1"  step="2">
							<tr><td>${s.id}————${s.name}</td></tr>
						</c:forEach>
						
						
					</tbody>
				
				</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>


collect homework<br/>
	<c:if test="${stu_hwks.size()==0}">
		<h4 class="text-center">尚未有人提交?</h4>
	</c:if>
	<c:forEach  items="${stu_hwks}" var="s" > 
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4>${s.student.id }--${s.student.name}</h4>
			</div>
			<div class="panel-body">
				${s.path}
			
				<form method="post" action="<%=request.getContextPath()%>/homework/download">
					<input type="text" name="fileName" value="${s.path}" hidden="hidden" />
				<button type="submit" class="btn btn-default"> 下载</button>
					
				</form>
			</div>
		</div>
		<br/>
	</c:forEach>
</div>
</body>


</html>
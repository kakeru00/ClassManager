<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/resources/css/fileinput.min.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/fileinput_locale_zh.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js" ></script>
<script type="text/javascript" >
	$(document).ready(function(){
		
/* 		$("#1").blur(function(){
			alert($(this).val());
		});  */

		$("form").submit(function(){
			var datetime = $("input[type='datetime-local']");
			var datetime2 = datetime.val().replace("T"," ");
			datetime.attr("type","text");
			datetime.val(datetime2);
			
		});
		
		$("input[type='file']").fileinput({
			language: 'zh', //设置语言
			allowedFileExtensions : ['jpg', 'png','gif','txt','pdf','zip','rar','doc','docx'],//接收的文件后缀
			dropZoneEnabled: true,
			showUpload: false, //是否显示上传按钮
			browseClass: "btn btn-primary", //按钮样式             
			maxFileSize: 5000
		});
		
	});
</script>
</head>
<body>
<c:if test="${sessionScope.student.access==false}">
	<% response.sendRedirect(request.getContextPath()+"/homework/homeworks"); %>
</c:if>
<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/>
<div class="container">
	<div class="col-md-12">
		<h3 class="text-center">
			new Homework
		</h3>
		<h6 class="text-center">发布作业</h6>
	</div>
	<div class="col-sm-2"></div>
	<div class="col-sm-7">
		<form class="form-horizontal" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-3 control-label">标题</label>
				<div class="col-sm-9">
					<input class="form-control" type="text" name="title" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">所属课程</label>
				<div class="col-sm-9">
					<input class="form-control" type="text" name="courseid" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-3 control-label">说明</label>
				<div class="col-sm-9">
					<textarea class="form-control" name="content" rows="" cols=""></textarea><br/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">截止日期</label>
				<div class="col-sm-9">
					<input class="form-control" name="conclude" type="datetime-local" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">附件</label>
				<div class="col-sm-9">
					<input class="form-control file-loading" type="file" name="file"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>
				<div class="col-sm-9">
					<button class="form-control btn btn-default" type="submit">OK</button>
				</div>
			</div>
		<!-- 	标题<input type="text" name="title" /><br/>
			说明<textarea name="content" rows="" cols=""></textarea><br/>
			截止日期<input name="conclude" type="datetime-local" /><br/>
			附件<input type="file" name="file"/><br/>
			<button type="submit">OK</button> -->
		</form>
	</div>
</div>
</body>

</html>
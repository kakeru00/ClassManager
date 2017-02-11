<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
	

	 $("#btn_addTeacher").click(function () {
		 $("#myModalLabel").text("添加学生");
		 var form = $(".modal-body form");
		 form.attr("action","add");
		 form.find("label").text("教师密码");
		 form.find("input").attr("name","password")
		 		.attr("placeholder","输入教师密码");
		 $("#myModal").modal();
	    });                        
	 $("#btn_update").click(function () {
		 $("#myModalLabel").text("修改管理密码");
		 var form = $(".modal-body form");
		 form.attr("action",$("#update").attr("action"));
		 form.find("label").text("新密码");
		 form.find("input").attr("name","password")
		 		.attr("placeholder","输入新密码");
		 $("#myModal").modal();
	    });
	  $("#btn_submit").on("click",function(){
		 var form = $(".modal-body form");
		 var url = form.attr("action");
		 var data = form.serialize();
		/*  $.post(url, data, function(result) {
				if(result!=""){
					$("#addSucc strong").text(result);
		 
					$("#addSucc").show();
					if(result=="添加成功"){
						$("a").click(function(){return false});
						$("button").attr("disabled",true);
						$("input").attr("disabled",true);
						setTimeout(function(){
							location.href="";
						},1500); 
					}else{
						$("#addSucc").delay(1500).hide(0);
					}
				}else {
					location.href="#";
					$("#fail").show(0).delay(2000).hide(0);
				}
			}); */
		 
		 $.ajax({
			  url: url,
			  type: "POST",
			  data: data,
			  success: function(result){
				  if(result!=""){
						$("#addSucc strong").text(result);
			 
						$("#addSucc").show();
						if(result=="添加成功"){
							$("a").click(function(){return false});
							$("button").attr("disabled",true);
							$("input").attr("disabled",true);
							setTimeout(function(){
								location.href="";
							},1500); 
						}else{
							$("#addSucc").delay(1500).hide(0);
						}
					}else {
						$("#fail strong").text("学生数量超限");
						$("#fail").show(0).delay(1500).hide(0);
					}
			  	},
			  error: function(){
				  $("#fail strong").text("操作失败！请检查输入数据合法性。");
				  $("#fail").show(0).delay(1500).hide(0);
			  	}
			});
		
	 }); 
	
	  $(".btn_access").click(function(){
		  var data = $(this).val();
		  var url = data+"/changeAccess";
		  $.post(url,data,function(result){
			  $("#"+result.id).prev().text(result.access);
			  
		  });
	  });
	  
	});
	
</script>
</head>
<body>
<div id="addSucc" class="alert alert-success navbar navbar-inverse navbar-fixed-top" hidden="hidden">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<strong>添加成功！</strong>
</div>
<div id="fail" class="alert alert-warning navbar navbar-inverse navbar-fixed-top" hidden="hidden">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<strong>操作失败！请检查输入数据合法性。</strong>
</div>
<div id="updateSucc" class="alert alert-success" hidden="hidden">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<strong>修改成功！</strong>
</div>
	<input id="studentTotal" value="${students.size()}" hidden="hidden" />

<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/>



	
	<div class="container">
		<div class="row clearfix">
			<div class="col-sm-2"></div>
			<div class="col-sm-8 column">
				<div class="col-sm-12">
				
					<button id="btn_update" type="button"
						class="btn btn-default btn-success">修改管理密码</button>
					<button id="btn_addTeacher" type="button"
						class="btn btn-default btn-success">添加教师</button>
				</div>
				<c:if test="${teachers.size()!=0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>帐号</th>
								<th>姓名</th>
								<th>email</th>
								
								<th>状态</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${teachers}" var="t" step="1"
								varStatus="status">

								<tr>
									<td><a href="${t.id}/update">${t.id}</a></td>
									<td>${t.name}</td>
									<td>${t.email}</td>
						
									<td>
										<c:if test="${t.status==false}">未激活</c:if>
										<c:if test="${t.status==true}">已激活</c:if>
									</td><%-- <a href="${s.id}/delete">delete</a> --%>
								</tr>


							</c:forEach>
							
						</tbody>
					</table>
				</c:if>
				<c:if test="${teachers.size()==0}">
				尚未存在教师<span class="glyphicon glyphicon-hand-right"></span>
				</c:if>
				




			</div>
			<div class="col-sm-2"></div>
		</div>

	
		<form id="update"
			action="<%=request.getContextPath()%>/class/${classAdmin.classId}/update"
			method="post" hidden="hidden">
			<div class="input-group">
				<input class="form-control" name="password" type="password" /> <span
					class="input-group-btn">
					<button type="submit" class="btn btn-default">ok</button>
				</span>
			</div>
		</form>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<form id="form"  method="post">
						<div class="input-group" >
							<label class=" control-label"></label>
							<input class="form-control" name=""  type="text" placeholder="" /> 
	
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="button" id="btn_submit" class="btn btn-primary"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
					
				
			</div>
		</div>
	</div>

</body>
</html>
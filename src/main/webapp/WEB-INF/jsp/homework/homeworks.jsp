<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="com.zh.entity.Student"%>
<%@ page  import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script>
	$(document).ready(function(){
		
		
 		$.post("homeworks",{hql:"from Homework where id<>'' "},function(result){
			alert(result.length);
			for(var i=0;i<result.length;i++){
				$("#homeworkdiv").append(createPanel(i));
				
				$("#"+i).addClass(result[i].course.id);
				$("#"+i).attr("class",$("#"+i).attr("class")+" "+result[i].course.id);
				alert("class:"+$("#"+i).attr("class"));
				$("#"+i).find(".panel-heading").append(result[i].title+result[i].course.id+" ————截止日期："+result[i].conclude);
			}  
			
		});
		
		 $.post("../course/courses",{hql:"from Course where id!='' "},function(result){
			try{
			for(var i=0;i<result.length;i++){
				
				$("#courseul").append("<li><a href='#'>"+result[i].name+"</a></li>");
				$("#courseul").find("li").eq(i+1).on("click",{courseid:result[i].id},function(event){
					//页签切换
					$(this).parent().children().removeClass();
					$(this).addClass("active");
					
					$("#homeworkdiv").find(".panel").hide();//隐藏所有数据
					alert(event.data.courseid);
					//选择性显示
					$("."+event.data.courseid).show();
					
				});
			}
			}catch(err){
				alert(err.description);
			}
		}); 
		 $("#courseul li").eq(0).click(function(){
			$(this).parent().children().removeClass();
			
			$(this).addClass("active");
			$("#homeworkdiv").find(".panel").show();//显示所有数据
			
		}); 
		
	});
	

	 
	function createPanel(id){

			var panel = "<div class='row clearfix' >"
			+"<div class='col-md-12 column'>"
			+"<div class='panel panel-info' id='"+id+"'>"
			+"<div class='panel-heading'></div>"
			+"<div class='panel-body'></div></div></div></div>";
			return panel;

	} 
	
</script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/mainHead.jsp"/> 
		<%  request.setAttribute("date",new Date().getTime()); %>
<div class="container">
	<c:if test="${student.status==false}">
		<% 	Student student = (Student)session.getAttribute("student");
			String url = request.getContextPath()+"/student/"+student.getId()+"/update";
			response.sendRedirect(url); %>
		
	</c:if>
	
	<div id="update" class="">
		
	</div>
	<div class="row clearfix">
		<!-- <button id="btn_update">update</button> -->
		
		
		<c:if test='${sessionScope.teacher != null}'>
			<a href="<%=request.getContextPath()%>/homework/add" class="btn btn-default">add homework</a><br/>
		</c:if>
	</div>
		<br/>
		<br/>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-tabs" id='courseul'>
				<li class="active"><a href="#">全部</a></li>
				
				<!-- <li class="dropdown pull-right"><a href="#"
					data-toggle="dropdown" class="dropdown-toggle">下拉<strong
						class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li><a href="#">操作</a></li>
						<li><a href="#">设置栏目</a></li>
						<li><a href="#">更多设置</a></li>
						<li class="divider"></li>
						<li><a href="#">分割线</a></li>
					</ul></li> -->
			</ul>
		</div>
	</div>
	
	<div id="homeworkdiv">
		
	</div>
	<%-- <div class="row clearfix" id="allhomework">
		<c:forEach  items="${homeworks}" var="h" varStatus="s"> 
		<div class="col-md-12 column">
		<div class="panel panel-info">
			<div class="panel-heading">
			${s.count }/${homeworks.size()}、作业标题 ：${h.title} &nbsp;&nbsp;&nbsp;———截止至 ${h.conclude}
			
			
			<a href="<%=request.getContextPath()%>/homework/${h.id }/delete"  class="btn btn-default">delete</a>
			<a href="<%=request.getContextPath()%>/homework/${h.id }/update"  class="btn btn-default">update</a>
			<a href="<%=request.getContextPath()%>/homework/collect/${h.id }"  class="btn btn-default">collect</a>
		
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
		</div> 
		<br/>
		<br/>
		</c:forEach>
	</div>--%>
</div>
</body>
</html>
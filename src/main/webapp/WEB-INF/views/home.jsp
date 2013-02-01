<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spitter</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

</style>
</head>
<body>
	<sec:authentication	property="principal" var="user" />
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="<%=request.getContextPath()%>/">Spitter
					- just spit it!</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link"> ${user.fullName} </a> 
						(<a href="<%=request.getContextPath()%>/static/j_spring_security_logout">Logout</a>)
					</p>
					<ul class="nav">
						<li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>
						<li><a href="<%=request.getContextPath()%>/edit/${user.username}">Profile</a></li>
						<li><a href="<%=request.getContextPath()%>/help">Help</a></li>
						<li><a href="<%=request.getContextPath()%>/about">About</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div id="main" class="container">
		<h3>Tell your friends what you think...</h3>
		<form action="" method="POST">
			<textarea rows="3" cols="50">I think...</textarea>
		</form>
		<h2>A global community of friends and strangers spitting out
			their inner-most and personal thoughts on the web for everyone else
			to see.</h2>
		<h3>Look at what these people are spitting right now...</h3>
		<c:if test="${not empty spittles}">
			<c:forEach var="spittle" items="${spittles}">
				<s:url value="/spittles/{username}" var="spitter_url">
					<s:param name="username" value="${spittle.spitter.username}" />
				</s:url>
				<li><span class="spittleListImage"> <img src=""
						width="48" border="0" align="middle">
				</span> <span class="spitterListText"> <a href="${spitter_url}">
							<c:out value="${spittle.spitter.username}" />
					</a> - <c:out value="${spittle.text}" /><br /> <small><fmt:formatDate
								value="${spittle.time}" pattern="hh:mma MMM d, yyyy" /></small>
				</span></li>
			</c:forEach>
		</c:if>
		<c:if test="${empty spittles }">
			<c:out value="Nothing new since your last login..."></c:out>
		</c:if>
	</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sec:authentication property="principal" var="user" />
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="<%=request.getContextPath()%>/">Spitter -
				just spit it!</a>
			<div class="nav-collapse collapse">
				<sec:authorize access="isAuthenticated()">
					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link"> ${user.fullName}
						</a> (<a
							href="<%=request.getContextPath()%>/static/j_spring_security_logout">Logout</a>)
					</p>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<p class="navbar-text pull-right">
						<a href="<%=request.getContextPath()%>/login">Login</a>
						<a href="<%=request.getContextPath()%>/spitter/new">Register</a>
					</p>
				</sec:authorize>
				<ul class="nav">
					<li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>
					<sec:authorize access="isAuthenticated()">
						<li><a
							href="<%=request.getContextPath()%>/edit/${user.username}">Profile</a></li>
					</sec:authorize>
					<li><a href="<%=request.getContextPath()%>/help">Help</a></li>
					<li><a href="<%=request.getContextPath()%>/about">About</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
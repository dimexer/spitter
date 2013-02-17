<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="Information for: ${spitter.username}"></c:out></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
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
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	<div id="main" class="container">
		<div class="left">
			<div class="navi">
				<ul class="nav nav-tabs nav-stacked">
					<li><a href="#">Tweets</a></li>
					<li><a href="#">Following</a></li>
					<li><a href="#">Followers</a></li>
					<li><a href="#">Favorites</a></li>
				</ul>
			</div>
		</div>
		<div class="right">
		<div class="spitterInfo">
			<h3><c:out value="${spitter.fullName}" /></h3>
			<c:out value="@${spitter.username}" /><br/>
			<sf:form cssClass="rightElements"  method="POST" action="/spitter-web/spitter/${spitter.username}?follow">
				<button class="btn">Follow ${spitter.username}</button>
			</sf:form>
		</div>
		<div class="spittles">
			<ul>
				<c:forEach var="spittle" items="${spittles}">
					<s:url value="/spittles/{username}" var="spitter_url">
						<s:param name="username" value="${spittle.spitter.username}" />
					</s:url>
					<li>
						<!--<span class="spittleListImage"> 
							<img src=""	width="48" border="0" align="middle">
						</span>--> 
						<div class="spitterListText">
							<div class="spittle-header">
								<span style="float:right;">
									<small>
										<fmt:formatDate	value="${spittle.time}" pattern="dd M" />
									</small>
								</span>
								<span style="float:left;">
								</span> 
							<a href="${spitter_url}">
								<c:out value="${spittle.spitter.fullName}" />
							</a>
							<small><c:out value="@${spittle.spitter.username}"/></small>
							</div>
							<c:out value="${spittle.text}" />
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		</div>
	</div>
</body>
</html>
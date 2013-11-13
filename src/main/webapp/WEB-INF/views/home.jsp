<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Spitter</title>
<jsp:include page="/WEB-INF/views/includes/htmlhead.jsp" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/homePage.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	<div id="main" class="container">
		<h3>Tell your friends what you think...</h3>
		<textarea id="spittleArea" rows="3" cols="50"></textarea>
		<br /> <input id="publish" class="btn" type="Submit" value="Publish">
		<!-- <h2>A global community of friends and strangers spitting out
			their inner-most and personal thoughts on the web for everyone else
			to see.</h2> -->
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
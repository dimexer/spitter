<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spitter</title>
<jsp:include page="/WEB-INF/views/includes/htmlhead.jsp" />
</head>
<body class="login-body">
	<div class="container">
		<s:url var="authUrl" value="/static/j_spring_security_check" />
		<form class="form-signin" method="POST" action="${authUrl}">
			<img class="login-img" src="<%=request.getContextPath()%>/resources/img/logo.jpg" />
			<h2 class="form-signin-heading">Please sign in</h2>
			<input id="username" type="text" class="input-block-level"
				name="j_username" placeholder="Username" /> <input id="password"
				type="password" class="input-block-level" placeholder="Password"
				name="j_password" /> 
			<label class="checkbox"> 
				<input id="remember_me" type="checkbox"
					value="_spring_security_remember_me" /> Remember me
			</label>
			<button class="btn btn-large btn-primary" type="submit">Sign
				in</button><br/>
			<div style="margin-top:5px;">
				<a href="<%=request.getContextPath()%>/spitter/new">Create new account</a><br/>
				<a href="/pwRecover">Forgotten password</a>
			</div>
		</form>
	</div>
</body>
</html>
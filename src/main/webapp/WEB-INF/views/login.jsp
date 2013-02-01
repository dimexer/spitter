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
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
</head>
<body>
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
			<a href="/register">Create new account</a><br/>
			<a href="/pwRecover">Forgotten password</a>
		</form>
	</div>
</body>
</html>
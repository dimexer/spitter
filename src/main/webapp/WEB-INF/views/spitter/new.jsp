<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New spitter</title>
<jsp:include page="/WEB-INF/views/includes/htmlhead.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	<div id="main" class="container">
		<h2>Create free Spitter account</h2>
		<sf:form method="POST" modelAttribute="spitter">
			<sf:errors path="*" cssStyle="color:red;"  />
			<fieldset>
				<table cellspacing="0" id="regForm">
					<tr>
						<th><label for="user_full_name">Full name:</label></th>
						<td><sf:input path="fullName" size="15" id="user_full_name" />
						</td>
					</tr>
					<tr>
						<th><label for="user_screen_name">Username: </label></th>
						<td><sf:input path="username" size="15" maxlength="15"
								id="user_screen_name" /> <small>No spaces, please :) </small></td>
					</tr>
					<tr>
						<th><label for="user_password">Password:</label></th>
						<td><sf:password path="password" size="30"
								showPassword="true" id="user_password" /> <small>6
								characters or more</small></td>
					</tr>
					<tr>
						<th><label for="passwordRepeat">Repeat Password:</label></th>
						<td><sf:password size="30" path="passwordRepeat"
								showPassword="true" id="passwordRepeat" /></td>
					</tr>
					<tr>
						<th><label for="user_email">EmailAddress:</label></th>
						<td><sf:input path="email" size="30" id="user_email" /> <small>In
								case you forget something</small></td>
					</tr>
				</table>
			</fieldset>
			<sf:button class="btn" value="Register">Register</sf:button>
		</sf:form>
	</div>
</body>
</html>
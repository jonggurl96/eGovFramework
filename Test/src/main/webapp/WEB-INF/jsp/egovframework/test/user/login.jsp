<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
let msg = "${msg}";
if(msg != "") {
	alert(msg);
}
</script>
</head>
<body>
<div class="home-logo">
</div>
<div class="home-login-box" style="align:center;text-align:center">
	<div class="login-logo">
		<div><h1><span><spring:message code="label.login"/></span></h1></div>
	</div>
	<form class="login-info-box" method="post">
		<div class="id-box">
			<span><spring:message code="label.id"/></span>
			<input type="text" name="id" placeholder=<spring:message code="label.id.placeholder"/>>
		</div>
		<div class="pw-box">
			<span><spring:message code="label.pw"/></span>
			<input type="password" name="pw" placeholder=<spring:message code="label.pw.placeholder"/>>
		</div>
		<input type="submit">
	</form>
</div>
<div class="footer">
</div>
</body>
</html>
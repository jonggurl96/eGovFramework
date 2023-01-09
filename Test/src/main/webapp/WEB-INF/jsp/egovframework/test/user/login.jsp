<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(document).ready(function() {
	$('.login-btn-box input').on("click", function() {
		$('.login-info-box').submit();
	});
	$('.regist-btn-box input').on("click", function() {
		self.location="/user/regist";
	});
});
var msg = "${msg}";
if(msg){
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
	<form class="login-info-box" method="post" action="/user/loginPost">
		<div class="id-box">
			<span><spring:message code="label.id"/></span>
			<input type="text" name="id" placeholder=<spring:message code="label.id.placeholder"/>>
		</div>
		<div class="pw-box">
			<span><spring:message code="label.pw"/></span>
			<input type="password" name="pw" placeholder=<spring:message code="label.pw.placeholder"/>>
		</div>
	</form>
	<div class="login-btn-box">
		<input type="submit" value="<spring:message code="label.login"/>">
	</div>
	<div class="regist-btn-box">
		<input type="submit" value="<spring:message code="label.user.regist"/>">
	</div>
</div>
<div class="footer">
</div>
</body>
</html>
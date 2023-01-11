<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer src="/js/board/regist.js"></script>
	<title><spring:message code="title.write" /></title>
</head>
<body>
<%@ include file="../user/loginInfo.jspf" %>
<form role="form" method="post">
	<input type="text" name="title" placeholder="Enter Title" >
	<textarea name="content" rows="3" placeholder="Enter Content"></textarea>
	<input type="text" name="writer" placeholder="Enter Writer">
	
</form>
<div class="box-footer">
	<button type="submit" class="btn btn-primary"><spring:message code="button.regist" /></button>
	<button type="submit" class="btn btn-danger"><spring:message code="button.cancel" /></button>
</div>
</body>
</html>
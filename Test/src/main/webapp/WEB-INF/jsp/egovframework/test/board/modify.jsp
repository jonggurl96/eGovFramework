<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>   
	<script defer src="/js/board/modify.js"></script>
	<title><spring:message code="title.modify"/></title>
</head>
<body>
<%@ include file="../user/loginInfo.jspf" %>
<form role="form" method="post">
	<input type="hidden" value="${cri.page}" name="page" id="page"/>
	<input type="hidden" value="${cri.rcpp}" name="rcpp" id="rcpp"/>
	<div>
		<spring:message code="board.bno"/><input type="text" name="bno" value="${vo.bno}" readonly="readonly" id="bno">
	</div>
	<div>
		<spring:message code="board.title"/><input type="text" name="title" value="${vo.title}" >
	</div>
	<div>
		<spring:message code="board.content"/><textarea name="content" rows="3"><c:out value="${vo.content}" /></textarea>
	</div>
	<div>
		<spring:message code="board.writer"/><input type="text" name="writer" value="${vo.writer}" readonly="readonly">
	</div>
</form>
<div class="box-footer">
	<button type="submit" class="btn btn-primary"><spring:message code="button.modify" /></button>
	<button type="submit" class="btn btn-danger"><spring:message code="button.cancel" /></button>
</div>
</body>
</html>
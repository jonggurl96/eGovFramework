<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>   
	<script defer src="/js/board/read.js"></script>
	<script defer src="/js/notice.js"></script>
	<title><spring:message code="title.read" /></title>
</head>
<body>
<%@ include file="../user/loginInfo.jspf" %>

<input type="hidden" id="hidden_msg" value="${msg}">
<spring:eval expression="@propertiesService.getString('delButton')" var="delButton"/>
<input type="hidden" id="delButton" value="${delButton }">

<form role="form" method="post">
	<input type="hidden" name="bno" value="${vo.bno}" id="bno">
	<input type="hidden" name="page" value="${cri.page}" id="page">
	<input type="hidden" name="rcpp" value="${cri.rcpp}" id="rcpp">
</form>
<div style="align:center">
	<div style="width: 12%">
		<div>
			<spring:message code="board.title" />:&nbsp;
			<c:out value="${vo.title }"/>
		</div>
	</div>
	<div style="width: 12%">
		<div>
			<spring:message code="board.content" />:&nbsp;
			<div>
				<textarea rows="3" readonly="readonly"><c:out value="${vo.content }" /></textarea>
			</div>
		</div>
	</div>
	<div style="width: 12%">
		<div>
			<spring:message code="board.writer" />:&nbsp;
			<c:out value="${vo.writer}" />
			<input type="hidden" value="${vo.writer }" id="writer">
		</div>
	</div>
	<div style="width: 12%">
		<div>
			<spring:message code="board.regdate" />:&nbsp;
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.regdate }"/>
		</div>
	</div>
</div>
<div class="box-footer">
	<button type="submit" class="btn btn-warning"><spring:message code="button.modify"/></button>
	<button type="submit" class="btn btn-danger"><spring:message code="button.delete"/></button>
	<button type="submit" class="btn btn-primary"><spring:message code="button.list"/></button>
</div>
<div class="regist-reply-box">
	<spring:message code="placeholder.regist.reply" var="placeholder"/>
	<textarea id="writtenReply" rows="3" placeholder="${placeholder}"></textarea>
	<button onclick="writeReply();"><spring:message code="reply.submit"/></button>
</div>
<div class="replies-box">
	<ul id="replies"></ul>
</div>
</body>
</html>
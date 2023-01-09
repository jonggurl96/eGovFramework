<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>   
<script>
$(document).ready(function() {
	var form = $("form[role='form']");
	$(".btn-warning").on("click", function() {
		if("${vo.writer}" != "${loginInfo.id}") {
			alert("수정 불가");
			return;
		}
		form.attr("action", "/board/modify");
		form.attr("method", "get");
		form.submit();
	});
	$(".btn-danger").on("click", function() {
		if("${vo.writer}" != "${loginInfo.id}") {
			alert("삭제 불가");
			return;
		}
		form.attr("action", "/board/remove");
		form.submit();
	});
	$(".btn-primary").on("click", function() {
		self.location = "/board/paginatedList?page=${cri.page}&rcpp=${cri.rcpp}";
	});
	
	getReplies();
});
let getReplies = () => {
	$.ajax({
		type: "get",
		url: '/replies/all/${vo.bno}',
		success: function(data) {
			let str = "";
			$(data).each(function() {
				str += "<li data-rno=" + this.rno + " class='ReplyList'>" + this.rno + ":" + this.replytext;
				str += "<button onclick='deleteReply("+this.rno+");'><spring:message code='button.delete'/></button></li>";
			});
			$('#replies').html(str);
		}
	});
}
let deleteReply = (rno) => {
	$.ajax({
		type: "delete",
		url: "/replies/" + rno,
		success: function(data) {
			alert(data);
			getReplies();
		}
	});
}

var msg = "${msg}";
if( msg != null && msg != "") {
	alert(msg);
}
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.read" /></title>
</head>
<body>
<form role="form" method="post">
	<input type="hidden" name="bno" value="${vo.bno}">
	<input type="hidden" name="page" value="${cri.page}">
	<input type="hidden" name="rcpp" value="${cri.rcpp}">
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
<div class="replies-box">
	<ul id="replies"></ul>
</div>
</body>
</html>
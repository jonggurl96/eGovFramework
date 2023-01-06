<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${empty loginInfo}">
		<script>
			alert("${msg}");
			self.location='/user/login';
		</script>
	</c:when>
	<c:otherwise>
		<script>
			self.location='/board/paginatedList?page=1&rcpp=10';
		</script>
	</c:otherwise>
</c:choose>
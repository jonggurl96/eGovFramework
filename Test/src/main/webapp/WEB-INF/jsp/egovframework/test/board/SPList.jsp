<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<head>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer>
		let msg = "${msg}";
		if (msg != null && msg != "") {
			alert(msg);
		}
	</script>
	<script defer src="/js/SPList.js"></script>
	<meta charset="UTF-8">
	<title><spring:message code="title.list" /></title>
</head>
<body>

<%@ include file="../user/logout_banner.jspf" %>

<div class="div-body" style="align:center; height:800px background-color:#ffffff">
	<div class="div-body-header" style="text-align:center; background-color:aqua">
		<div class="board-title">
			<h1><span>BOARD<br></span></h1>
			<h3><span>with eGovFramework</span></h3>
		</div> <!-- /.board-title -->
	</div> <!-- /.div-body-header -->
	
	<div class="board-table">
		<div class="board-help-box" style="background:#555555; margin:auto">
			<div class="search-box" style="align:left; text-align:left;float:left">
				<select name="tag" id="tag">
					<option value="all" selected="selected"> --- </option>
					<option value="title"><spring:message code="board.title"/></option>
					<option value="content"><spring:message code="board.content"/></option>
					<option value="writer"><spring:message code="board.writer"/></option>
				</select>
				<input id="keyword" type="text" placeholder="<spring:message code='placeholder.search'/>">
				<button onclick="searchKeyword();"><spring:message code="button.search"/></button>
			</div>
			<div class="pagination-config" style="align:right; text-align:right;float:right">
				<spring:message code="recordCountPerPage"/>:&nbsp;
				<select name="rcpp" id="rcpp" onchange="changeRCPP(this);">
					<option value=10 selected="selected">10</option>
					<option value=20>20</option>
					<option value=30>30</option>
				</select>
				<input type="hidden" id="pre_rcpp" value=10>
			</div>
		</div>
		<div style="clear:both;background:#ffff99">
			<table id="table-board">
				<!-- <script> downloadPage()...... </script> -->				
			</table>
		</div>
	</div> <!-- /.board-table -->
	
	<div class="board-pagination">
		<!-- pagination -->
		<div id="paging">
			<ui:pagination paginationInfo = "${pageInfo}" type="image" jsFunction="otherPage" />
        </div>
        <input type="hidden" id="totalRecordCount" value="${pageInfo.totalRecordCount }">
	</div> <!-- board-pagination -->
	
	<div class="box-footer">
		<a href="/board/regist"><spring:message code="button.regist" /></a>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">
var msg = "${msg}";
if (msg != null && msg != "") {
	alert(msg);
}
</script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

let getDate = (num) => {
	let date = new Date(num);
	
	let month = date.getMonth() + 1;
	let day = date.getDate();
	let hours = date.getHours();
	let minutes = date.getMinutes();
	
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	hours = hours < 10 ? "0" + hours : hours;
	minutes = minutes < 10 ? "0" + minutes : minutes;

	return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes;
}

let downloadPage = function (tag, keyword, page, rcpp) {
	
	let data = {
			"page": page,
			"rcpp": rcpp,
			"totalRecordCount": ${pageInfo.totalRecordCount}
	};
	if(tag === "all") {
		$('#keyword').val("");
	}
	else {
		data["tag"] = tag;
		data["keyword"] = keyword;
	}
	
	$.ajax({
		type: "post",
		url: "/search",
		contentType: "application/json",
		dataType: "text",
		data: JSON.stringify(data),
		success: function(data) {
			
			data = JSON.parse(data);
			let str = "<tr><th><spring:message code='board.bno' /></th>";
			str += "<th><spring:message code='board.title' /></th>";
			str += "<th><spring:message code='board.writer' /></th>";
			str += "<th><spring:message code='board.regdate' /></th></tr>";
			
			data.forEach(element => {
				let href = "/board/read?bno=" + element["bno"] + "&page=${pageInfo.currentPageNo}&rcpp=${pageInfo.recordCountPerPage}";
				str += "<tr><td class='td-bno'>" + element["bno"] + "</td>";
				str += "<td><a href=" + href + ">" + element["title"] + "</a></td>";
				str += "<td>" + element["writer"] + "</td>";
				str += "<td>" + getDate(element["regdate"]) + "</td>";
			});
			$('#table-board').html(str);
		}
	});
}

$(document).ready(function() {
	$('select option').each(function() {
		if($(this).val() == "${pageInfo.recordCountPerPage}") {
			$(this).attr("selected", "selected");
		}
	});
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	let pageNo = $('#paging strong').text();
	downloadPage(tag, keyword, pageNo, rcpp);
	
});

let redirect = (tag, keyword, pageNo, rcpp) => {
	let redirectURL = "/board/SPList?page=" + pageNo + "&rcpp=" + rcpp;
	if(!(tag === "all")) {
		redirectURL += "&tag=" + tag + "&keyword=" + keyword;
	}
	self.location = redirectURL;
}

function otherPage(pageNo) {
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	console.log(pageNo);
	
	redirect(tag, keyword, pageNo, rcpp);
}

let searchKeyword = () => {
	otherPage(1);
}

let changeRCPP = (sel) => {
	
	let boardIndex = ( ${pageInfo.currentPageNo} - 1) * ${pageInfo.recordCountPerPage} + 1;
	let newPage = Math.ceil(boardIndex / sel.value);
	
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	
	redirect(tag, keyword, newPage, sel.value);
}

</script>
<head>
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
	</div> <!-- board-pagination -->
	
	<div class="box-footer">
		<a href="/board/regist"><spring:message code="button.regist" /></a>
	</div>
</div>

</body>
</html>
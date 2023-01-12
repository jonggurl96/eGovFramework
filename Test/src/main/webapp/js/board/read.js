/**
 * 
 */
$(document).ready(function() {
	let form = $("form[role='form']");
	let writer = $('#writer').val();
	let id = $('#login-id').text();
	
	$(".btn-warning").on("click", function() {
		if(writer != id) {
			alert("수정 불가");
			return;
		}
		form.attr("action", "/board/modify");
		form.attr("method", "get");
		form.submit();
	});
	$(".btn-danger").on("click", function() {
		if(writer != id) {
			alert("삭제 불가");
			return;
		}
		form.attr("action", "/board/remove");
		form.submit();
	});
	$(".btn-primary").on("click", function() {
		let page = $('#page').val();
		let rcpp = $('#rcpp').val();
		self.location = "/board/SPList?page=" + page + "&rcpp=" + rcpp;
	});
	
	getReplies();
});
let getReplies = () => {
	let bno = $('#bno').val();
	let delBtnVal = $('#delButton').val();
	
	$.ajax({
		type: "get",
		url: '/replies/all/' + bno,
		success: function(data) {
			let str = "";
			$(data).each(function() {
				str += "<li class='ReplyList'>";
				str += "<label class='reply-writer' title='" + this.writer + "'>" + this.replyer + "</label>";
				str += "<label class='reply-text'>" + this.replytext + "</label>";
				str += "<button class='del-reply-btn' onclick='deleteReply(" + this.rno + ", &quot;" + this.replyer + "&quot;);'>" + delBtnVal + "</button></li>";
			});
			$('#replies').html(str);
		}
	});
}
let deleteReply = (rno, replyer) => {
	let id = $('#login-id').text();
	if(id != replyer) {
		alert("작성자만 삭제 가능합니다.");
		return;
	}
	$.ajax({
		type: "delete",
		url: "/replies/" + rno,
		success: function(data) {
			alert(data);
			getReplies();
		}
	});
}
let writeReply = () => {
	let replyer = $('#login-id').text();
	let replytext = $('#writtenReply').val();
	let bno = $('#bno').val();
	$.ajax({
		type:"post",
		url:"/replies/",
		headers: {
			"Content-Type":"application/json",
			"X-HTTP-Method-Override": "post"
		},
		dataType: "text",
		data: JSON.stringify({
			replytext,
			replyer,
			bno
		}),
		success: function(msg) {
			alert(msg);
			getReplies();
			$('#writtenReply').val("");
		}
	});
}
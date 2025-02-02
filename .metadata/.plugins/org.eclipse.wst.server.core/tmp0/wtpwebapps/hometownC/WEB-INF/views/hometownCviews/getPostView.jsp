<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post</title>

<!-- bootstrap css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />

</head>

<body>
	<nav class="navbar navbar-light bg-#81DAF5"
		style='background-color: #81DAF5'>
		<a class="navbar-brand" style="color: white"><strong>HomeTown
				Community</strong></a>

		<ul class="nav justify-content-end">
			<li class="nav-item"><a class="nav-link" href="#">${member.username }
					님 </a></li>
			<li class="nav-item">
				<button class="btn btn-outline-success" type="button"
					onclick="location.href='login.do'">로그아웃</button>
			</li>
		</ul>

	</nav>
	<br>

	<div class="container">

		<div style='background-color: #E0F2F7'>
			<hr style='height: 1px; background-color: #81DAF5'>
			<div class="row justify-content-between"
				style='padding-left: 15px; padding-right: 15px; height: 40px'>
				<div class="col-md-auto">
					<h5 style='padding-top: 5px'>
						<strong>${post.title }</strong>
					</h5>
				</div>

				<div class="col-md-auto">
					<div class="row">
						<div class="col-md-auto" style="padding-right: 10px">
							<a type="button"
								href="likedPost.do?seq=${post.seq}&&id=${member.id }"> <img
								class="mb-4"
								src="${pageContext.request.contextPath}/resources/img/like.png"
								alt="" width="30" height="30"></a> <strong><span
								style='color: #FE2E9A'> ${post.liked }</span> </strong>
						</div>

						<div class="col-md-auto btn-group btn-sm" role="group"
							style="padding-left: 5px; padding-bottom: 10px; padding-right: 20px"
							aria-label="Basic example">

							<c:if test="${member.id == post.writer}">
								<button class="btn btn-outline-secondary" type="button"
									onclick="location.href='modifyPost.do?seq=${post.seq}&&id=${member.id }'">수정</button>
								<button class="btn btn-outline-secondary" type="button"
									onclick="location.href='deletePost.do?seq=${post.seq}&&id=${member.id }'">삭제</button>
							</c:if>
							<button class="btn btn-outline-secondary" type="button"
								onclick="location.href='getPostList.do?id=${member.id}'">목록</button>
						</div>

					</div>
				</div>
			</div>

			<hr>

			<div class="row" style="padding-left: 15px; padding-right: 15px">
				<div class="col-8">
					<h6>writer. ${post.writer }</h6>
				</div>
				<div class="col-4 text-right">
					<h6>date. ${post.regDate }</h6>
				</div>
			</div>
			<hr>
		</div>
		<div style='padding-left: 15px;'>
			<p style='white-space: pre'>${post.content }</p>
		</div>

		<hr style='height: 1px; background-color: #81DAF5'>
		<p>
			<strong>전체 댓글 <span style='color: red'>
					${fn:length(commentList) }</span>개
			</strong>
		</p>
		<div>
			<c:forEach var="comment" items="${commentList}" varStatus="status">
				<hr style='height: 50%'>
				<div class="row" style='padding-bottom: 0px; padding-left: 15px'>
					<p class="col-1">
						<strong>${comment.writer}</strong>
					</p>
					<p class="col-7" style='white-space: pre'>${comment.comment }</p>
					<p class="col-4 text-right">${comment.regdate}</p>

				</div>
			</c:forEach>
		</div>

		<div style='background-color: #E0F2F7'>
			<hr style='height: 1px; background-color: #81DAF5'>
			<form
				action="insertComment.do?seq=${post.seq }&&writer=${member.id }&&id=${member.id}"
				method="post">
				<div style='padding-left: 15px; padding-right: 15px'>

					<div style='padding-bottom: 15px'>
						<strong>댓글</strong>
					</div>
					<textarea class="form-control" id="comment" name="comment" rows="3"
						placeholder="여기에 댓글을 작성하세요."></textarea>
					<div style='padding-top: 15px'>
						'
						<button class="btn btn-primary" type="submit">댓글달기</button>
					</div>
				</div>
			</form>
			<hr style='height: 1px; background-color: #81DAF5'>
		</div>
	</div>
</body>
</html>
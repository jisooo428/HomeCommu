<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostList</title>

<!-- bootstrap css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>

<style>
.form-select {
	display: block;
	width: 100%;
	padding: .375rem 2.25rem .375rem .75rem;
	-moz-padding-start: calc(0.75rem - 3px);
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #212529;
	background-color: #fff;
	background-image:
		url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='%23343a40' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M2 5l6 6 6-6'/%3e%3c/svg%3e");
	background-repeat: no-repeat;
	background-position: right .75rem center;
	background-size: 16px 12px;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none
}

@media ( prefers-reduced-motion :reduce) {
	.form-select {
		transition: none
	}
}

.form-select:focus {
	border-color: #86b7fe;
	outline: 0;
	box-shadow: 0 0 0 .25rem rgba(13, 110, 253, .25)
}

.form-select




 




[
multiple
]
,
.form-select




 




[
size
]




 




:not




 




(
[
size
=
"1"
]




 




)
{
padding-right








:




 




.75rem








;
background-image








:




 




none










}
.form-select:disabled {
	background-color: #e9ecef
}

.form-select:-moz-focusring {
	color: transparent;
	text-shadow: 0 0 0 #212529
}

.form-select-sm {
	padding-top: .25rem;
	padding-bottom: .25rem;
	padding-left: .5rem;
	font-size: .875rem
}

.form-select-lg {
	padding-top: .5rem;
	padding-bottom: .5rem;
	padding-left: 1rem;
	font-size: 1.25rem
}
</style>

<style>
.footer {
	background-color: #81DAF5;
	text-align: center;
	size: 10px;
	color: white;
}
</style>

</head>
<body>

	<nav
		class="navbar navbar-expand-lg bg-body-tertiary navbar-light bg-#81DAF5"
		style="background-color: #81DAF5">
		<div class="container-fluid">
			<a class="navbar-brand" href="#" style="color: white"><strong>HomeTown
					Community</strong></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="getPostList.do?id=${member.id }">전체 글</a></li>
					<li class="nav-item"><a class="nav-link" href="getPostListCate.do?id=${member.id }&&cateSeq=1">추천 맛집/카페</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="getPostListCate.do?id=${member.id }&&cateSeq=2">추천 장소</a></li>
					<li class="nav-item"><a class="nav-link" href="getPostListCate.do?id=${member.id }&&cateSeq=3">소모임/동아리</a></li>
					<li class="nav-item"><a class="nav-link" href="getPostListCate.do?id=${member.id }&&cateSeq=4">정보공유</a></li>
					<li class="nav-item"><a class="nav-link" href="getPostListCate.do?id=${member.id }&&cateSeq=5">아나바다</a></li>
				</ul>
			</div>

			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link"
					href="editMember.do?id=${member.id }">${member.username } 님 </a></li>
				<li class="nav-item">
					<button class="btn btn-outline-success" type="button"
						onclick="location.href='login.do'">로그아웃</button>
				</li>
			</ul>
		</div>
	</nav>
	<br>

	<div class="container">
		<hr style='height: 1px; background-color: #81DAF5'>
		<div class="row">
			<div class="col-xs-12"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<thead style="background-color: #E0F2F7">
						<tr align="center">
							<th scope="col">NO.</th>
							<th scope="col">TITLE</th>
							<th scope="col">WRITER</th>
							<th scope="col">LIKED</th>
							<th scope="col">DATE</th>
						</tr>
					</thead>
					<c:forEach items="${plist}" var="post">
						<tr align="center">
							<td>${post.rn }</td>
							<th scope="row"><a
								href="getPost.do?seq=${post.seq }&&id=${member.id}">${post.title}</a>
							</th>
							<td>${post.writer}</td>
							<td>${post.liked}</td>
							<td>${post.regDate}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="row justify-content-end" style="margin-right: 30px">
					<div class="col-1 dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="sortByDate.do?id=${member.id }">날짜별(오래된 것부터)</a></li>
							<li><a class="dropdown-item"
								href="sortByLiked.do?id=${member.id }">좋아요 수</a></li>
						</ul>
					</div>
					<input class="col-1 btn btn-primary" type="button" value="포스팅"
						onclick="location.href='insertPost.do?id=${member.id }'" />
				</div>
			</div>
		</div>


		<hr style='height: 1px; background-color: #81DAF5'>
		<form action="searchPostList.do?id=${member.id }" method="post">
			<div class="row" style="padding-left: 300px; padding-right: 300px">
				<select class="form-select col-3" id="searchCon" name="searchCon"
					required>
					<option value="title">TITLE</option>
					<option value="writer">WRITER</option>
				</select> <input type="text" name="searchKey" class="form-control col-7" />
				<input class="btn btn-primary col-2" type="submit" value="검색" />
			</div>
		</form>

	</div>
	<br>

	<nav class="navbar navbar-light bg-#81DAF5"
		style="background-color: #81DAF5">
		<span class="footer">Copyright(c)2023 jigu All rights reserved.</span>
	</nav>


	<%-- <hr>
<input type="button" value="포스팅" onclick="location.href='insertPost.do?id=${member.id }'"/>
<hr>

<table border="1">
	<tr height="50" align="center">
		<td width="150"> 제목 </td>
		<td width="150"> 글쓴이 </td>	
		<td width="150"> 좋아요 </td>		
		<td width="150"> 날짜 </td>		
	</tr>
	<c:forEach items="${plist}" var="post">
	<tr height="50" align="center">
		<td width="150"><a href="getPost.do?seq=${post.seq }&&id=${member.id}">${post.title}</a></td>
		<td width="150">${post.writer}</td>
		<td width="150">${post.liked}</td>
		<td width="150">${post.regDate}</td>
	</tr>
	</c:forEach>
	
</table> --%>
</body>

</html>
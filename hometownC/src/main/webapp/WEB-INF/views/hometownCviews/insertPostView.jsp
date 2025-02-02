<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Posting</title>

<!-- bootstrap css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">


</head>

<body>
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand">HomeTown Community</a>

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


	<form class="was-validated container" 
			action="insertPostProc.do?id=${member.id }" method="post" >
		<div class="form-group">
			<label>Category</label> 
			<select class="custom-select" name="category"
				required>
				<option value="">Category</option>
				<option value="1">추천 맛집/카페</option>
				<option value="2">추천 장소</option>
				<option value="3">소모임/동아리</option>
				<option value="4">동네정보공유</option>
				<option value="5">아나바다</option>
			</select>
			<div class="invalid-feedback">카테고리를 선택해주세요!</div>
		</div>

		<div class="form-group">
			<label>Title</label> 
			<input class="form-control" 
			type="text" name="title" placeholder="여기에 제목을 입력하세요." required>
			<div class="invalid-feedback">제목을 입력해주세요!</div>
		</div>

		<div class="form-group">
			<label>Writer</label> 
			<input class="form-control"
				type="text" value="${member.id }" readonly>
		</div>

		<div class="form-group">
			<label>Content</label>
			<textarea class="form-control is-invalid" 
			id="content" name="content" rows="10" placeholder="여기에 내용을 입력하세요." required></textarea>
			<div class="invalid-feedback">내용을 입력해주세요!</div>
		</div>
	
		<div class="form-group">
			<button type="submit" class="btn btn-primary">포스팅하기</button>
		</div>
	</form>

	<%-- <form action="insertPostProc.do?id=${member.id }" method="post">
<table border="1">
	<tr height="50" align="center">
		<td width="150">카테고리</td>
		<td width="250">
			<select name="category">
				<option value="1">추천 맛집/카페</option>
				<option value="2">추천 장소</option>
				<option value="3">소모임/동아리</option>
				<option value="4">동네정보공유</option>
				<option value="5">아나바다</option>
			</select>
		</td>
	</tr>
	<tr height="50" align="center">	
		<td width="150">제목</td>
		<td width="250"><input type="text" name="title"></td>
	</tr>
	<tr height="50" align="center">
		<td width="150">글쓴이</td>
		<td width="250">${member.id }</td>
	</tr>
	<tr height="50" align="center">
		<td width="150">내용</td>
		<td width="250"><input type="text" name="content"></td>
	</tr>
	<tr height="50" align="center">
		<td colspan="2">
			<input type="submit" value="게시글 등록하기"/>
			<input type="reset" value="초기화"/>	
		</td>
	</tr>
</table>
</form> --%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en" class="h-100" style="overflow: auto;">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>${empty title ? 'KOSMOIndex' : title}</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.1/examples/cover/">

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}	
}
</style>


<!-- Custom styles for this template -->
<link href="<c:url value='/css/cover.css'/>" rel="stylesheet">
</head>
<body class="d-flex h-100 text-center text-white bg-dark" style="overflow: auto;">

	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column">
		<header style="margin-bottom: 20px;">
			<div>
				<nav class="nav nav-masthead justify-content-center float-start">
					<a class="navbar-brand" href="<c:url value='/bbs/Index.kosmo'/>"
						style="color: #88898A;"><strong>KOSMO</strong></a>
				</nav>
				<nav class="nav nav-masthead justify-content-center float-end">
					<a class="nav-link" href="<c:url value='/bbs/Index.kosmo'/>">Home</a>
					<c:if test="${LOGIN != null}" var="isLogin">
						<a class="nav-link" href="<c:url value='/bbs/List.kosmo'/>">게시판</a>
						<a class="nav-link" href="<c:url value='/bbs/MyPage.kosmo'/>">마이페이지</a>
						<a class="nav-link" href="<c:url value='/bbs/SignOut.kosmo'/>">로그아웃</a>
					</c:if>
					<c:if test="${!isLogin}">
						<a class="nav-link" href="<c:url value='/bbs/SignUp.kosmo'/>">회원가입</a>
						<a class="nav-link" href="<c:url value='/bbs/SignIn.kosmo'/>">로그인</a>
					</c:if>
				</nav>
			</div>
		</header>
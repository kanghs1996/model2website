<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en" class="h-100">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>Cover Template · Bootstrap v5.1</title>

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
<body class="d-flex h-100 text-center text-white bg-dark">

	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header style="margin-bottom: 50px;">
			<div>
				<nav class="nav nav-masthead justify-content-center float-md-start">
					<a class="navbar-brand" href="<c:url value='/template/Template2.jsp'/>"
						style="color: #88898A;"><strong>KOSMO</strong></a>
				</nav>
				<nav class="nav nav-masthead justify-content-center float-md-end">
					<a class="nav-link" href="<c:url value='/template/Template2.jsp'/>">Home</a> <a class="nav-link"
						href="#">게시판</a> <a class="nav-link" href="#">회원가입</a>
					<a class="nav-link" href="#">로그인</a>
				</nav>
			</div>
		</header>

		<main class="px-3">
			<div class="jumbotron">
				<h1>
					게시판 <small>Home</small>
				</h1>
			</div>
		</main>

		<footer class="mt-auto text-white-50">
			<p>KOSMO KangHyunsoo</p>
			<p>
				Cover template for <a href="https://getbootstrap.com/"
					class="text-white">Bootstrap</a>, by <a
					href="https://twitter.com/mdo" class="text-white">@mdo</a>.
			</p>
		</footer>
	</div>
</body>
</html>
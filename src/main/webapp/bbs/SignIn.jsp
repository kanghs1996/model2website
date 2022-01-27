<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='/css/signin.css'/>" rel="stylesheet">
<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
<jsp:include page="/include/Top.jsp" />
<main class="form-signin">	
	<form action="<c:url value='/bbs/SignIn.kosmo'/>" method="post" name='form'>
		<img class="mb-4" src="<c:url value="/images/bootstrap-logo.svg"/>" alt=".."
			width="72" height="57">
		<h1 class="h3 mb-3 fw-normal">로그인</h1>		
		<div class="col-sm-12">
			<input type="text" class="form-control"
				name="id" placeholder="아이디" value="${remember_id}">
		</div>
		<div class="col-sm-12">
			<input type="password" class="form-control"
				name="password" placeholder="비밀번호">
		</div>

		<div class="checkbox mb-3">
			<label> <input type="checkbox" name='remember' value="remember-me" 
			${empty check_remember ? '' : 'checked="checked"'}>
				Remember me
			</label>
		</div>
		<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>		
	</form>
</main>
<jsp:include page="/include/Footer.jsp" />
<jsp:include page="/include/Modal.jsp"/>
<script src="<c:url value="/js/SignIn.js"/>"></script>
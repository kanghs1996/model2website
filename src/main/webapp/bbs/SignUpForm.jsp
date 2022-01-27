<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/include/Top.jsp" />
<main class="px-3">	
		<div style="margin-bottom: 30px;">
			<h1>회원가입</h1>
		</div>
		<form class="row gy-2 gx-3 align-items-center" 
			action="<c:url value='/bbs/SignUp.kosmo'/>" 
			method="post" name="form">
			<div class="mb-3 row">
				<label for="id" class="offset-1 col-3 col-form-label">아이디</label>
				<div class="col-7">
					<input type="text" class="form-control" name="id" id="id">
				</div>
			</div>
			<div class="mb-3 row">
				<label for="pass" class="offset-1 col-3 col-form-label">비밀번호</label>
				<div class="col-7">
					<input type="password" class="form-control" name="pass" id="pass">
				</div>
			</div>
			<div class="mb-3 row">
				<label for="check" class="offset-1 col-3 col-form-label">비밀번호
					확인</label>
				<div class="col-7">
					<input type="password" class="form-control" name="check" id="check">
				</div>
			</div>
			<div class="mb-3 row">
				<label for="name" class="offset-1 col-3 col-form-label">이름</label>
				<div class="col-7">
					<input type="text" class="form-control" name="name" id="name">
				</div>
			</div>
			<div class="mb-3 row">
				<label for="nick" class="offset-1 col-3 col-form-label">닉네임</label>
				<div class="col-7">
					<input type="text" class="form-control" name="nick" id="nick">
				</div>
			</div>
			<div class="mb-3 row">
				<div class="offset-1 col-3">성별</div>
				<div class="col-7" style="text-align: left;">
					<label for="gender1" class="form-check-label">남자</label> <input
						type="radio" class="form-check-input" name="gender" id="gender1"
						value="man"> <label for="gender2" class="form-check-label">여자</label>
					<input type="radio" class="form-check-input" name="gender"
						id="gender2" value="woman">
				</div>
			</div>
			<div class="mb-3 row">
				<div class="offset-1 col-3">관심사항</div>
				<div class="col-7" style="text-align: left;">
					<label for="checkbox1" class="form-check-label">정치</label> <input
						type="checkbox" class="form-check-input" name="interest"
						id="checkbox1" value="pol">
				</div>
				<div class="offset-4 col-7" style="text-align: left;">
					<label for="checkbox2" class="form-check-label">경제</label> <input
						type="checkbox" class="form-check-input" name="interest"
						id="checkbox2" value="eco">
				</div>
				<div class="offset-4 col-7" style="text-align: left;">
					<label for="checkbox3" class="form-check-label">연예</label> <input
						type="checkbox" class="form-check-input" name="interest"
						id="checkbox3" value="ent">
				</div>
			</div>
			<div class="mb-3 row">
				<div class="offset-1 col-3">학력</div>
				<div class="col-7">
					<select class="form-select" name="education">
						<option selected>학력을 선택하세요</option>
						<option value="ele">초등학교</option>
						<option value="mid">중학교</option>
						<option value="hig">고등학교</option>
						<option value="uni">대학교</option>
					</select>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="area" class="offset-1 col-3 col-form-label">자기소개</label>
				<div class="col-7">
					<textarea class="form-control" name="introduction" id="area"
						rows="3"></textarea>
				</div>
			</div>
			<div class="mb-3 row">
				<div class="offset-4 col-7" style="text-align: left;">
					<button type="submit" class="btn btn-primary">확인</button>
				</div>
			</div>
		</form>
	
</main>
<jsp:include page="/include/Footer.jsp" />
<jsp:include page="/include/Modal.jsp" />
<script src="<c:url value="/js/SignUpForm.js"/>"></script>
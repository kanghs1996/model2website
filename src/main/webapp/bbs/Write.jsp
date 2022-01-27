<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/include/Top.jsp" />
<main class="px-3">
	<div style="margin-bottom: 30px;">
		<h1>글작성</h1>
	</div>
	<form class="row gy-2 gx-3 align-items-center"
			action="<c:url value='/bbs/Write.kosmo'/>" 
			method="post" name="form">
		<div class="mb-3 row">
			<label for="title"
				class="offset-1 col-3 col-form-label"">제목</label>
			<div class="col-7">
				<input type="text" class="form-control"
					id="title" name="title">
			</div>
		</div>
		<div class="mb-3 row">
			<label for="content"
				class="offset-1 col-3 col-form-label">내용</label>
			<div class="col-7">
				<textarea class="form-control col-7"
					id="content" name="content" rows="7"></textarea>
			</div>
		</div>
		<div class="mb-3 row">
			<div class="offset-4 col-7" style="text-align: left;">
				<button type="submit" class="btn btn-primary">등록</button>
			</div>
		</div>
	</form>

</main>
<jsp:include page="/include/Footer.jsp" />
<jsp:include page="/include/Modal.jsp" />
<script src="<c:url value="/js/Write.js"/>"></script>
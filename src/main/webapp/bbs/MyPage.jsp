<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/include/Top.jsp" />
<main class="px-3">
	<div class="jumbotron">
		<h1>마이페이지</h1>
	</div>
	<div class="table-responsive">
		<table class="table text-white align-middle">
			<tbody>
				<tr>
					<td scope="row">${MYID}</td>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<td scope="row">${MYNAME}</td>
					<td>${dto.name}</td>
				</tr>
				<tr>
					<td scope="row">${MYNICKNAME}</td>
					<td>${dto.nickname}</td>
				</tr>
				<tr>
					<td scope="row">${MYGENDER}</td>
					<td>${dto.gender}</td>
				</tr>
				<tr>
					<td scope="row">${MYINTERREST}</td>
					<td>${inter}</td>
				</tr>
				<tr>
					<td scope="row">${MYEDUCATION}</td>
					<td>${dto.education}</td>
				</tr>
				<tr>
					<td scope="row">${MYREGIDATE}</td>
					<td>${dto.regidate}</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">${MYINTRODUCTION}</td>
				</tr>
				<tr>
					<td colspan="2" scope="row" style="text-align: left;">${dto.introduction}</td>
				</tr>
			</tbody>
			<tfoot>
				<td colspan="2" scope="row"
					style="border-style: none; text-align: right;">
					<a href="<c:url value='/bbs/ReCheckPass.kosmo'/>">
						<button class="btn btn-primary">수정</button></a> 
					<a href="<c:url value='/bbs/ReCheckWithdrawal.kosmo'/>">
						<button class="btn btn-primary" id="del">회원탈퇴</button>	</a>
				</td>
			</tfoot>
		</table>
	</div>
</main>
<jsp:include page="/include/Footer.jsp" />
<script>
	console.log(document.getElementById('del'))
	document.getElementById('del').addEventListener('click',function(e) {
		if(!confirm("정말 탈퇴하시겠습니까?")){
			e.preventDefault();
		}
	});
</script>
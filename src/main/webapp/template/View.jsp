<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/include/Top.jsp" />
<main class="px-3">
	<div class="jumbotron" style="margin-bottom: 20px;">
		<h1>게시판 상세보기</h1>
	</div>
	<div class="table-responsive">
		<table class="table text-white align-middle">
			<thead>
				<tr>
					<td colspan="2" scope="row" style="border-style: none; text-align: right;">
						<c:if test="${PREVNEXT.get('PREV') != 0}">
							<a href="<c:url value='/bbs/View.kosmo?no='/>${PREVNEXT.get('PREV')}&nowPage=${param.nowPage}">
									<button class="btn btn-primary">이전글</button></a>
						</c:if>
						<c:if test="${PREVNEXT.get('NEXT') != 0}"> 
							<a href="<c:url value='/bbs/View.kosmo?no='/>${PREVNEXT.get('NEXT')}&nowPage=${param.nowPage}">
									<button class="btn btn-primary">다음글</button></a>
						</c:if>
						<a href="<c:url value='/bbs/List.kosmo'/>?nowPage=${param.nowPage}">
							<button class="btn btn-primary">목록</button></a>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td scope="row">${VIEWNO}</td>
					<td>${record.no}</td>
				</tr>
				<tr>
					<td scope="row">${VIEWTITLE}</td>
					<td>${record.title}</td>
				</tr>
				<tr>
					<td scope="row">${VIEWCREATER}</td>
					<td>${record.id}</td>
				</tr>
				<tr>
					<td scope="row">${VIEWCOUNT}</td>
					<td><fmt:formatNumber value="${record.viewcount}" groupingUsed="true"/></td>
				</tr>
				<tr>
					<td scope="row">${VIEWDATE}</td>
					<td>${record.regidate}</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">${VIEWCONTENT}</td>
				</tr>
				<tr>
					<td colspan="2" scope="row" style="text-align: left;">${record.content}</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" scope="row" style="border-style: none; text-align: right;">
						<c:if test="${isMyBbs}">
							<a href="<c:url value='/bbs/Edit.kosmo?no='/>${record.no}&nowPage=${param.nowPage}">
								<button class="btn btn-primary">수정</button></a> 
							<a href="<c:url value='/bbs/Delete.kosmo?no='/>${record.no}">
								<button class="btn btn-primary" id="del">삭제</button></a>
						</c:if> 
						<a href="<c:url value='/bbs/List.kosmo'/>?nowPage=${param.nowPage}">
							<button class="btn btn-primary">목록</button></a>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</main>
<jsp:include page="/include/Footer.jsp" />
<script>
	console.log(document.getElementById('del'))
	document.getElementById('del').addEventListener('click',function(e) {
		if(!confirm("정말 삭제하시겠습니까?")){
			e.preventDefault();
		}
	});
</script>
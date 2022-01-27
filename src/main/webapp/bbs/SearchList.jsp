<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/include/Top.jsp" />
<main class="px-3">
	<div class="jumbotron" style="margin-bottom: 20px;">
		<h1>게시판</h1>
	</div>
	<div class="table-responsive">		
		<table class="table text-white align-middle">
			<thead>
				<tr>
					<td colspan="5" scope="row" style="border-style: none; text-align: right;">
						<a class="offset-9 col-2" href="<c:url value='/bbs/List.kosmo'/>">
							<button class="btn btn-primary">전체목록</button>
						</a>
						<a class="col-1" href="<c:url value='/bbs/Write.kosmo'/>">
							<button class="btn btn-primary">글쓰기</button>						
						</a>
					</td>
				</tr>
				<tr>
					<th class="col-2" scope="col">#</th>
					<th class="col-4" scope="col">제목</th>
					<th class="col-2" scope="col">작성자</th>
					<th class="col-2" scope="col">조회수</th>
					<th class="col-2" scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}" var="isEmpty">
					<tr class="text-center">
						<td colspan="6">등록된 게시물이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${not isEmpty}">
					<c:forEach var="item" items="${list}" varStatus="loop">
						<tr>
							<th scope="row">${totalRecordCount - (((nowPage-1) * pageSize) + (loop.count-1))}</th>
							<td style="text-align: left;"><a class="text-white" style="text-decoration: none;"
								href="<c:url value="/bbs/View.kosmo?no="/>${item.no}&nowPage=1">
									${item.title} </a></td>
							<td>${item.id}</td>
							<td><fmt:formatNumber value="${item.viewcount}" groupingUsed="true"/></td>
							<td>${item.regidate}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			
			<tfoot>
				<tr style="text-align: center;">
					<td colspan="5" scope="row" style="border-style: none;">
						${pagingString}</td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="5" scope="row" style="border-style: none;">
						<form action="<c:url value="/bbs/SearchList.kosmo"/>" method="post"
							class="row gy-2 gx-3 align-items-center">
							<div class="offset-2 col-2">
								<select class="form-select" name="searchOption" id="search">
									<option selected>검색옵션</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="nickname">작성자</option>
								</select>
							</div>
							<div class="col-5">
								<input type="text" class="form-control" name="searchContent" id="content">
							</div>
							<div class="col-1">
								<button type="submit" class="btn btn-primary" id="btn">검색</button>
							</div>
						</form>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</main>
<jsp:include page="/include/Footer.jsp" />
<script type="text/javascript">	
	document.getElementById("btn").addEventListener('click', function(e) {
		if(document.getElementById('search').value == "검색옵션"){
			e.preventDefault();
			document.getElementById('search').focus();
			alert('검색옵션을 선택하세요');
		}
		else if(document.getElementById('content').value.length == 0){
			e.preventDefault();
			document.getElementById('content').focus();
			alert('검색내용을 입력하세요');
		}
	});
</script>
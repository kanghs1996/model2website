<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>${empty title ? 'KOSMOIndex' : title}</title>
<!-- 어느 컨트롤러에서 왔는지(포워드 되었는지)에 따라 분기 -->
<c:choose>
	<c:when test="${requestScope.WHERE =='INS'}">
		<c:set var="successMsg" value="가입 성공했어요"/>
		<c:set var="failMsg" value="가입 실패했어요"/>
		<c:set var="successUrl" value="/bbs/Index.kosmo"/>
	</c:when>
	<c:when test="${requestScope.WHERE =='UPD'}">
		<c:set var="successMsg" value="정보 수정 성공했어요"/>
		<c:set var="failMsg" value="정보 수정 실패했어요"/>
		<c:set var="successUrl" value="/bbs/MyPage.kosmo"/>
	</c:when>
	<c:when test="${requestScope.WHERE =='DUPID'}">
		<c:set var="failMsg" value="아이디 중복으로 실패했어요"/>		
	</c:when>
	<c:when test="${requestScope.WHERE =='DUPNICK'}">
		<c:set var="failMsg" value="닉네임 중복으로 실패했어요"/>		
	</c:when>
	<c:when test="${requestScope.WHERE =='RCP'}">
		<c:set var="successMsg" value="비밀번호 확인 성공했습니다"/>
		<c:set var="failMsg" value="비밀번호 확인 실패했습니다"/>
		<c:set var="successUrl" value="/bbs/MemberEdit.kosmo"/>		
	</c:when>
	<c:when test="${requestScope.WHERE =='RCW'}">
		<c:set var="successMsg" value="비밀번호 확인 성공했습니다"/>
		<c:set var="failMsg" value="비밀번호 확인 실패했습니다"/>
		<c:set var="successUrl" value="/bbs/Withdrawal.kosmo"/>		
	</c:when>
	<c:when test="${requestScope.WHERE =='LOG'}">
		<c:set var="successMsg" value="로그인 성공했어요"/>
		<c:set var="failMsg" value="아이디/인증이메일 또는 비밀번호를 잘못 입력하셨습니다."/>		
		<c:set var="successUrl" value="/bbs/Index.kosmo"/>
		<c:if test="${not empty param.remember}" var="remember">
			<c:set var="check_remember" value="${param.remember}" scope="application"/>
			<c:set var="remember_id" value="${param.id}" scope="application"/>
		</c:if>
		<c:if test="${!remember}">
			<c:remove var="check_remember"/>
			<c:remove var="remember_id"/>
		</c:if>
		<c:if test="${SUCCFAIL==1}">
			<c:set var="LOGIN" value="${param.id}" scope="session"/>
		</c:if>
	</c:when>
	<c:when test="${requestScope.WHERE =='BBSW'}">
		<c:set var="successMsg" value="글작성에 성공했습니다"/>
		<c:set var="failMsg" value="글작성에 실패했습니다"/>
		<c:set var="successUrl" value="/bbs/List.kosmo"/>
	</c:when>
	<c:when test="${requestScope.WHERE =='BBSD'}">
		<c:set var="successMsg" value="글삭제 성공했습니다"/>
		<c:set var="failMsg" value="글삭제 실패했습니다"/>
		<c:set var="successUrl" value="/bbs/List.kosmo"/>
	</c:when>
	<c:when test="${requestScope.WHERE =='BBSE'}">
		<c:set var="successMsg" value="글수정 성공했습니다"/>
		<c:set var="failMsg" value="글수정 실패했습니다"/>
		<c:set var="successUrl" value="/bbs/View.kosmo?no=${param.no}&nowPage=${param.nowPage}"/>
	</c:when>	
	<c:otherwise>
		<c:set var="successMsg" value="탈퇴 성공했어요"/>
		<c:set var="failMsg" value="탈퇴 실패했어요"/>
		<c:set var="successUrl" value="/bbs/Index.kosmo"/>	
	</c:otherwise>
</c:choose>

<script>
	<c:choose>
		<c:when test="${SUCCFAIL ==1 }">
			alert("${successMsg}");
			location.replace("<c:url value="${successUrl}"/>");
		</c:when>
		<c:when test="${SUCCFAIL ==0 }">
			alert("${failMsg}");
			history.back();
		</c:when>
	</c:choose>
</script>

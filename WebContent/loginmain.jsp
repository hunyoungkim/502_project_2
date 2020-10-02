<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인 후 페이지</title>
</head>
<body>
	<div>
		${m_id}님
	</div>

	<div>
		<button type="button" value="button" onclick="location.href='./MyPageService.me'">회원정보</button>
	</div>
	<div>
	<c:choose>
		<c:when test="${m_id.equals('admin')}">
			<button type="button" value="button" onclick="location.href='./AdminMemberService.ad'">회원조회</button>
		</c:when>
	</c:choose>
	</div>
	<div>
		<button type="button" value="button" onclick="location.href='./LogoutService.me'">로그아웃</button>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>인덱스 페이지입니다.</h3>
<c:if test="${not empty id }">
	<p>
		<strong><a href="users/info.do">${id }</a></strong>
		님 로그인 중...
	</p>
	<a href="users/logout.do">로그아웃</a>
</c:if>	

<ul>
	<li><a href="member/list.do">회원 목록 보기</a></li>
	<li><a href="users/signup_form.do">회원가입하기</a></li>
	<li><a href="users/login_form.do">로그인하기</a></li>
	<li><a href="file/list.do">자료실 목록 보기</a></li>
	<li><a href="cafe/list.do">카페 목록 보기</a></li>
	<li><a href="shop/list.do">상품 목록 보기</a></li>
	<li><a href="ajax/upload_form.do">ajax 파일 업로드 테스트</a></li>
</ul>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/updateform.jsp</title>
</head>
<body>
<h3>회원정보 수정 페이지 입니다.</h3>
<form action="update.do" method="post" id="updateForm">
	<input type="hidden" name="id" value="${id }"/>
	<label for="id">아이디</label>
	<input type="text" id="id" value="${id }" 
		disabled="disabled"/><br/>
	<label for="email">이메일 주소</label>
	<input type="text" name="email" id="email" 
		value="${dto.email }"/><br/>
	<button type="submit">수정 확인</button>
</form>
<a href="pw_changeform.do">비밀번호 변경</a>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script>
	// form 에 submit 이벤트가 일어 났을때 실행할 함수 등록 
	$("#updateForm").submit(function(){
		//입력한 비밀번호를 읽어와서 
		var pwd1=$("#pwd").val();
		var pwd2=$("#pwd2").val();
		if( pwd1 != pwd2 ){//두개를 다르게 입력하면 
			alert("비밀번호를 확인 하세요");
			return false; //폼 전송 막기 
		}
	});
	
	/*
	document.querySelector("#updateForm")
	.addEventListener("submit", function(event){
		var pwd1=document.querySelector("#pwd").value;
		var pwd2=document.querySelector("#pwd2").value;
		if(pwd1 != pwd2){
			alert("비밀번호를 확인 하세요");
			event.preventDefault();//폼전송 막기 
		}
	});
	*/
</script>
</body>
</html>
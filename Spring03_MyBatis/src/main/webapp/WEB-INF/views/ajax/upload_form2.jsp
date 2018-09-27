<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax/upload_form2.jsp</title>
</head>
<body>
<h3>ajax 파일 업로드 예제2</h3>
<form action="upload2.do" method = "post" enctype="multipart/form-data" id = "fileForm2">
	<label for="file">파일 첨부</label>
	<input type="file" name="file" id = "file"/>
	<button type = "submit">업로드</button>
</form>
<div id = "result">
	

</div>
<script src = "${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script src = "${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>

<script>
	//form 플러그인 동작 시키기
	$("#fileForm").ajaxForm(function(responseData){//자기가 알아서 페이지 전환 이벤트 막음 , json형태로 문자열 전달한거 함수안으로 받는다!!
		console.log(responseData);
	});
</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax/upload_form.jsp</title>
<style>
	img{
		width:200px;
	}
	
	#profileImg{
		width:100px;
		height:100px;
		background-color: skyblue;
		cursor:pointer;
		border-radius:50%;
	}
	
	#fileForm4{
		display:none;
				
	}

</style>
</head>
<body>

<h3>ajax 파일 업로드 예제</h3>

<form action="upload.do" method = "post" enctype="multipart/form-data" id = "fileForm">
	<label for="file">파일 첨부</label>
	<input type="file" name="file" id = "file"/>
	<button type = "submit">업로드</button>
</form>

<h3>ajax 파일 업로드 예제2</h3>

<form action="upload2.do" method = "post" enctype="multipart/form-data" id = "fileForm2">
	<label for="file">파일 첨부</label>
	<input type="file" name="file" id = "file"/>
	<button type = "submit">업로드</button>
</form>

<h3>ajax 파일 업로드 예제3</h3>

<form action="upload2.do" method = "post" enctype="multipart/form-data" id = "fileForm3">
	<label for="file">파일 첨부</label>
	<input type="file" name="file" id = "file"/>
</form>

<div id = "result">

</div>



<h3>ajax 파일 업로드 예제</h3>

<form action="upload2.do" method = "post" enctype="multipart/form-data" id = "fileForm4">
	<label for="file">파일 첨부</label>
	<input type="file" name="file" id = "file"/>
</form>

<img src="${pageContext.request.contextPath }/resources/images/kim1.png" id = "profileImg" />

<script src = "${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script src = "${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>

<script>
	//form 플러그인 동작 시키기
	$("#fileForm").ajaxForm(function(responseData){//자기가 알아서 페이지 전환 이벤트 막음 , json형태로 문자열 전달한거 함수안으로 받는다!!
		console.log(responseData);
	});
	
	$("#fileForm2").ajaxForm(function(responseData){
		//저장된 파일명을 얻어온다.
		var fileName = responseData.fileName;
		
		//이미지 경로
		var imagePath = "${pageContext.request.contextPath}/upload/"+fileName;
		
		//이미지 요소를 만들어서 div에 추가
		$("<img/>").attr("src",imagePath).appendTo("#result");
		

	});
	
	
	$("#fileForm3").ajaxForm(function(responseData){
		//저장된 파일명을 얻어온다.
		var fileName = responseData.fileName;
		
		//이미지 경로
		var imagePath = "${pageContext.request.contextPath}/upload/"+fileName;
		
		//이미지 요소를 만들어서 div에 추가
		$("<img/>").attr("src",imagePath).appendTo("#result");
		
		//폼 리셋 시키기
		$(this).reset();
		
	});
	
	$("#fileForm3 input[type=file]").on("input",function(responseData){
		$("#fileForm3").submit();//제출 시켜버림 input이 발생하면
	});
	
	
	//이미지 클릭하면 input file폼 뜨게!
	$("#fileForm4").ajaxForm(function(responseData){
		//저장된 파일명을 얻어온다.
		var fileName = responseData.fileName;
		
		//이미지 경로
		var imagePath = "${pageContext.request.contextPath}/upload/"+fileName;
		$("#profileImg").attr("src",imagePath);
		
		
	});
	
	
	//이미지를 클릭했을 때 실행할 함수
	$("#profileImg").click(function(){
		$("#fileForm4 input[type=file]").click();//강제 클릭시켜서 이미지를 선택할 수 있게 함
	});
	
	
	// 파일을 선택했을때 실행할 함수 등록 
	$("#fileForm4 input[type=file]").on("input", function(){
		$("#fileForm4").submit();
	});
	
	
</script>

</body>
</html>
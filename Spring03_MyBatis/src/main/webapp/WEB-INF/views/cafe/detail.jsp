<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/detail.jsp</title>
<style>
	body{
		background-color: #F2E0D6;
	}
	.big_content{
		width:800px;
		margin:0 auto;
		background-color: #fff;
	}
	.content{
		width: 766px;
		border: 1px solid #888888;
	}
	.content img{
		max-width: 100%;
	}
	/* 댓글에 댓글을 다는 폼은 일단 숨겨 놓기 */
	#comment_comment{
		display: none;
	}
	#comment_update_comment{
		display:none;
	}
	
	.comment{
		position: relative;
	}
	/* 댓글의 댓글 이미지 배치*/
	.comment .reply_icon{
		width: 8px;
		height: 8px;
		position: absolute;
		top: 0px;
		left: -12px;
	}
	.muted{
		color:#888;
	}
	
	h3{
		 text-align: center;
	}
	
	#list_view{
		font-size:15px;
		background-color:#7092BE;
		color:#fff;
		padding:5px;
		border-radius:5px;
		font-weight:bold;
	}
	
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link href="https://afeld.github.io/emoji-css/emoji.css" rel="stylesheet">
</head>
<body>
<div class="container" >
	<div class="big_content" style = "margin-bottom:50px">
		<h3><i class="em em-face_with_raised_eyebrow"></i></i>Detail Page<i class="em em-face_with_hand_over_mouth"></i></i></h3>
		 <c:if test="${not empty keyword }">
			<p> 검색어 : <strong>${keyword }</strong> 에 대한 자세히 보기</p>
		</c:if>
		<div>
		
			<c:if test="${dto.prevNum ne 0}">
				<a href="detail.do?num=${dto.prevNum }"><i class="em em-arrow_up_small"></i>이전글</a> |
			</c:if>
			<c:if test="${dto.nextNum ne 0}">
				<a href="detail.do?num=${dto.nextNum }" style = "margin-bottom:30px"><i class="em em-arrow_down"></i></i>다음글</a>
			</c:if>
		</div>
		
		<c:if test="${sessionScope.id eq dto.writer }">
			<br />
			<a href="updateform.do?num=${dto.num }" class="btn btn-primary">수정</a>
			<a href="javascript:deleteConfirm()" class="btn btn-danger">삭제</a>
			<script>
				function deleteConfirm(){
					var isDelete=confirm("글을 삭제 하시겠습니까?");
					if(isDelete){
						location.href="delete.do?num=${dto.num}";
					}
				}
			</script>
		</c:if>
		<table class="table table-sm" style="margin-top :  20px">
			<tr>
				<th scope ="col">글번호</th>
				<td scope = "row">${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
		</table>
		<div class="content" style = "margin:15px 0">${dto.content }</div>
		<a href="list.do" id = "list_view">목록 보기</a>
		
		<!-- 댓글에 관련된 UI -->
		<div class="comments" style = "margin:15px 0">
			<c:forEach var="tmp" items="${CommentList }">
			
				<div class="comment" <c:if test="${tmp.num ne tmp.comment_group }">style="margin-left:50px;"</c:if>>
					<c:if test="${tmp.num ne tmp.comment_group }">
						<img class="reply_icon" src="${pageContext.request.contextPath }/resources/images/re.gif"/>
					</c:if>
					
					<img src="${pageContext.request.contextPath }/resources/images/user_image.gif"/>
					<span>${tmp.writer }</span>
					<span>${tmp.regdate }</span>
					<a href="javascript:" class="reply_link">답글</a> |
					<a href="javascript:" class="update_link">수정</a> |
					<a href="">신고</a> | 
					<a href="javascript:deleteAction(${tmp.num },${dto.num })" class="delete_link">삭제</a>
					<a href = "javascript:likeAction(${tmp.num },${dto.num })" class="glyphicon glyphicon-thumbs-up" style = "margin:0px 3px">${tmp.likeNum }</a>
					<a href = "javascript:dislikeAction(${tmp.num},${dto.num })" class="glyphicon glyphicon-thumbs-down" style = "margin:0px 3px"></a>${tmp.dislikeNum } 
					<c:if test="${tmp.num ne tmp.comment_group }">
						<br />
						  <i class="muted">To : ${tmp.target_id }</i>
					</c:if>
					
					<c:choose>
						<c:when test="${tmp.isDelete eq 1 }">
							<pre>삭제된 댓글</pre>
						</c:when>
						<c:otherwise>
							<pre>${tmp.content }</pre>
							
						</c:otherwise>
					</c:choose>
					
					<form action="comment_insert.do" method="post" class="form-inline form_class" id = "comment_comment" style = "margin:10px 0px">
						<!-- 덧글 작성자 -->
						<input type="hidden" name="writer" value="${id }"/>
						<!-- 덧글 그룹 -->
						<input type="hidden" name="ref_group" value="${dto.num }" />
						<!-- 덧글 대상 -->
						<input type="hidden" name="target_id" value="${tmp.writer }" />
						<input type="hidden" name="comment_group" value="${tmp.comment_group }" />
						<textarea name="content" class="form-control" cols="50" rows="1" ></textarea>
						<button type="submit" class="btn btn-warning">등록</button>
					</form>		
					
					<form action="comment_update.do?num=${tmp.num }" method="post" class="form-inline form_class" id = "comment_update_comment" style = "margin:10px 0px">
						<!-- 덧글 작성자 -->
						<input type="hidden" name="writer" value="${id }"/>
						<!-- 덧글 그룹 -->
						<input type="hidden" name="ref_group" value="${dto.num }" />
						<!-- 덧글 대상 -->
						<input type="hidden" name="target_id" value="${tmp.writer }" />
						<input type="hidden" name="comment_group" value="${tmp.comment_group }" />
						<textarea name="content" class="form-control" cols="50" rows="1" >${tmp.content }</textarea>
						<button type="submit" class="btn btn-warning">수정등록</button>
					</form>		
						
				</div>
			</c:forEach>
			<!-- 원글에 댓글을 작성할수 있는 폼 -->
			<div class="comment_form" >
				<form action="comment_insert.do" method="post" class="form-inline" id = "first_comment">
					<input type="hidden" name="writer" 
						value="${id }" />
					<input type="hidden" name="ref_group" 
						value="${dto.num }"/>
					<input type="hidden" name="target_id" 
						value="${dto.writer }"/>
					<textarea name="content" class="form-control" cols="50" rows="1" ></textarea>
					<button type="submit" class="btn btn-warning">등록</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script>
	//로그인 했는지 여부
	var isLogin=${isLogin};
	
	//댓글 전송 이벤트가 일어 났을때 실행할 함수 등록
	$("#comment_comment, .comment form, #first_comment").submit(function(){
		if(!isLogin){//로그인 하지 않았으면
			var isGoLogin=confirm("로그인이 필요 합니다.");
			if(isGoLogin){
				//로그인 페이지로 이동하기
				location.href="${pageContext.request.contextPath}"+
					"/users/loginform.do"+
					"?url=${pageContext.request.contextPath}"+
					"/cafe/detail.do?num=${dto.num}"; 
			}
			return false;//폼 전송 막기 
		}
	});
	
	// 답글 링크를 눌렀을때 실행할 함수 등록 
	$(".comment .reply_link").click(function(){
		$(".form_class").not("#comment_comment").slideUp();
		$(this)
		.parent()
		.find("#comment_comment")
		.slideToggle(200);
		
	});
	
	//수정 링크를 눌렀을때 실행할 함수 등록
	$(".comment .update_link").click(function(){
		$(".form_class").not("#comment_update_comment").slideUp();
		$(this)
		.parent()
		.find("#comment_update_comment")
		.slideToggle(200);
	});
	
	function deleteAction(num,num2){
		var isDelete=confirm("댓글을 삭제하시겠습니까?");
		if(isDelete){
			location.href="comment_delete.do?num="+num+"&num2="+num2;
		}
		
	}

	function likeAction(num,num2){
		
		if(!isLogin){//로그인 하지 않았으면
			var isGoLogin=confirm("로그인이 필요 합니다.");
			if(isGoLogin){
				//로그인 페이지로 이동하기
				location.href="${pageContext.request.contextPath}"+
					"/users/loginform.do"+
					"?url=${pageContext.request.contextPath}"+
					"/cafe/detail.do?num=${dto.num}"; 
			}
		}
		else{
			var reallyLike=confirm("댓글을 좋아요 하시겠습니까?");
			if(reallyLike){
				location.href="comment_like.do?num="+num+"&num2="+num2;
			}
		}
	}
	
	function dislikeAction(num,num2){
		
		if(!isLogin){//로그인 하지 않았으면
			var isGoLogin=confirm("로그인이 필요 합니다.");
			if(isGoLogin){
				//로그인 페이지로 이동하기
				location.href="${pageContext.request.contextPath}"+
					"/users/loginform.do"+
					"?url=${pageContext.request.contextPath}"+
					"/cafe/detail.do?num=${dto.num}"; 
			}
		}
		else{
			var reallydisLike=confirm("댓글을 싫어요 하시겠습니까?");
			if(reallydisLike){
				location.href="comment_dislike.do?num="+num+"&num2="+num2;
			}
		}
		
	}
	
	
</script>

</body>
</html>
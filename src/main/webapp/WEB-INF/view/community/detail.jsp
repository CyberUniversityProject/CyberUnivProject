<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 상세보기</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.breadcrumbs {
	background-color: #f9f9f9;
	padding: 20px 0;
}

.breadcrumbs h2 {
	margin-bottom: 0;
	color: #333;
}

.container {
	margin-top: 20px;
}

.list-group-item {
	border: none;
}

.list-group-item:nth-child(even) {
	background-color: #f9f9f9;
}

.btn--confirm {
	display: inline-block;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	cursor: pointer;
}

.btn--delete {
	display: inline-block;
	padding: 10px 20px;
	background-color: #bb2d3b;
	color: white;
	border: none;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	cursor: pointer;
}

.btn--update {
	display: inline-block;
	padding: 10px 20px;
	background-color: #0b5ed7;
	color: white;
	border: none;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	cursor: pointer;
}

.btn--confirm:hover {
	background-color: #45a049;
}

/* 숨김 처리 */
#commentForm {
	display: none;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h3>게시글 상세보기</h3>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2>${community.title}</h2>
				<p>
					<strong>작성자:</strong> ${community.userName}


				</p>
				<hr>
				<div style="height: 300px;">
					<p>${community.content}</p>
				</div>

				<hr>
				<div class="btn-group">
					<a href="/community/list"><button class="btn--confirm me-3">목록으로</button></a>
					<!-- 삭제 버튼 -->
					<%-- 삭제 버튼 표시 여부를 결정하는 조건문 --%>
					<c:if test="${community.userName eq principal.name or principal.userRole eq 'staff'}">
					<a href="/community/update/${community.id}"><button class="btn--update me-3">게시글 수정</button></a>
						<form id="deleteForm"
							action="/api/Community/delete/${community.id}" method="post">
							<button type="submit" class="btn--delete ">게시글 삭제</button>
						</form>
					
					</c:if>

				</div>

				<hr />
				<!-- 댓글 작성 버튼 -->
				<button id="showCommentFormBtn" class="btn--confirm">댓글 작성</button>

				<!-- 댓글 작성 폼 -->
				<form id="commentForm" action="/api/comment/create" method="post">
					<div class="form-group">
						<label for="commentContent">댓글 내용</label>
						<textarea class="form-control" id="commentContent"
							name="commentContent" rows="3"></textarea>
					</div>
					<!-- 숨은 필드로 userId와 communityId 추가 -->
					<input type="hidden" id="userId" name="userId"
						value="${principal.name}"> <input type="hidden"
						id="communityId" name="communityId" value="${community.id}">
					<button type="submit" class="btn btn-primary">댓글 등록</button>
				</form>

				<hr>
				<h4>댓글</h4>
				<c:if test="${not empty community.comments}">
					<ul class="list-group">
						<c:forEach items="${community.comments}" var="comment">
							<li class="list-group-item"><strong>작성자:</strong>
								${comment.userId}<br> <strong>작성 시간:</strong>
								${comment.appDateFormat()}<br> ${comment.content}</li>
						</c:forEach>
					</ul>
				</c:if>

				<c:if test="${empty community.comments}">
					<p>댓글이 없습니다.</p>
				</c:if>
			</div>
		</div>
	</div>


	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			// 댓글 작성 버튼 클릭 시 댓글 입력 폼 보이기
			$("#showCommentFormBtn").click(function() {
				$("#commentForm").toggle();
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			// 폼이 제출되면
			$("#commentForm").submit(function(event) {
				// 기본 동작인 폼의 제출을 막습니다.
				event.preventDefault();
				// AJAX를 사용하여 비동기적으로 댓글을 등록합니다.
				$.ajax({
					type : "POST",
					url : "/api/comment/create",
					data : $(this).serialize(), // 폼 데이터를 직렬화하여 전송합니다.
					success : function(response) {
						window.location.reload();
					},

					error : function(xhr, status, error) {
						console.error("Error:", error);
					}
				});
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			// 삭제 버튼 클릭 시 confirm 다이얼로그 표시
			$("#deleteForm").submit(function(event) {
				event.preventDefault(); // 폼의 기본 동작을 막음
				var confirmDelete = confirm("정말로 삭제하시겠습니까?");
				if (confirmDelete) {
					// 확인 버튼 클릭 시 폼 제출
					$.ajax({
						type : "DELETE",
						url : $(this).attr("action"), // form의 action 속성 값 (삭제 요청이 전송될 URL)
						success : function(response) {
							// 삭제 성공 시, 페이지 다시로드
							window.location.href = '/community/list';
						},
						error : function(xhr, status, error) {
							console.error("Error:", error);
						}
					});
				} else {
					// 취소 버튼 클릭 시 아무 동작도 하지 않음
					return false;
				}
			});
		});
	</script>
</body>
</html>

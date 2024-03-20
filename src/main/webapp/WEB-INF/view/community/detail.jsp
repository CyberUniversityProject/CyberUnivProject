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

.comment-actions {
	margin-top: 10px;
}

.comment-info {
	margin-bottom: 5px;
}

.comment-content {
	margin-bottom: 10px;
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
	<div class="container mt-5 mb-5  border border-1 rounded-2 pt-3 pb-5">
		<div class="row ">
			<div class="col-lg-12">
				<h2>${community.title}</h2>
				<p>
					<strong>작성자:</strong> ${community.userName}
					<br>
					<strong>작성일:</strong> ${community.appDateFormat()}


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
					<c:if
						test="${community.userName eq principal.name or principal.userRole eq 'staff'}">
						<a href="/community/update/${community.id}"><button
								class="btn--update me-3">게시글 수정</button></a>
						<form id="deleteForm"
							action="/api/Community/delete/${community.id}" method="post">
							<input type="hidden" name="_method" value="DELETE">
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
							<li class="list-group-item">
								<div class="row">
									<div class="col-md-9">
										<div class="comment-info">
											<strong>작성자:</strong> ${comment.userId}<br> <strong>작성
												시간:</strong> ${comment.appDateFormat()}
										</div>
										<div class="comment-content">${comment.content}</div>
										<!-- 수정 폼 -->
										<div class="comment-edit-form" style="display: none;">
											<form id="editCommentForm_${comment.id}" method="post">
											<input type="hidden" name="_method" value="put">
												<textarea class="form-control" name="editedContent">${comment.content}</textarea>
												<button type="button"
													class="btn btn-success btn-sm mt-2 save-edit-comment-btn"
													data-comment-id="${comment.id}">저장</button>
											</form>
										</div>

									</div>
									<div class="col-md-3">
										<c:if
											test="${comment.userId eq principal.name or principal.userRole eq 'staff'}">
											<div class="comment-actions text-right me-3">
												<!-- 수정 버튼 클릭시 수정 폼 보이도록 설정 -->
												<a href="#"
													class="btn btn-primary btn-sm edit-comment-btn mb-2">수정</a>
												<form action="/api/comment/delete/${comment.id}"
													method="post" class="delete-comment-form">
													<input type="hidden" name="_method" value="DELETE">
													<button type="submit"
														class="btn btn-danger btn-sm delete-comment-btn">삭제</button>
												</form>
											</div>
										</c:if>
									</div>
								</div>
							</li>
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
		$(document).ready(
				function() {
					// 댓글 작성 버튼 클릭 시 댓글 입력 폼 보이기
					$("#showCommentFormBtn").click(function() {
						$("#commentForm").toggle();
					});

					// 댓글 폼 제출 시
					$("#commentForm").submit(function(event) {
						event.preventDefault(); // 폼의 기본 동작을 막음
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
					
					
					// 삭제 버튼 클릭 시 confirm 다이얼로그 표시
					$(".btn--delete").click(function(event) {
					    event.preventDefault(); // 버튼의 기본 동작을 막음
					    var deleteConfirmed = confirm("정말로 삭제하시겠습니까?");
					    if (deleteConfirmed) {
					        // 확인 버튼 클릭 시 폼 제출
					        var form = $(this).closest("form");
					        $.ajax({
					            type: "DELETE",
					            url: form.attr("action"), // form의 action 속성 값 (삭제 요청이 전송될 URL)
					            success: function(response) {
					                window.location.href = "/community/list"; // 리스트 페이지로 이동
					            },
					            error: function(xhr, status, error) {
					                console.error("Error:", error);
					            }
					        });
					    } else {
					        // 취소 버튼 클릭 시 아무 동작도 하지 않음
					        return false;
					    }
					});


					
					// 삭제 버튼 클릭 시 confirm 다이얼로그 표시
					$(".delete-comment-btn").click(function(event) {
						event.preventDefault(); // 폼의 기본 동작을 막음
						var confirmDelete = confirm("정말로 삭제하시겠습니까?");
						if (confirmDelete) {
							// 확인 버튼 클릭 시 폼 제출
							var form = $(this).closest("form");
							$.ajax({
								type : "DELETE",
								url : form.attr("action"), // form의 action 속성 값 (삭제 요청이 전송될 URL)
								success : function(response) {
									window.location.reload();
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
					// 수정 버튼 클릭시 수정 폼 보이도록 설정
					$('.edit-comment-btn').click(function() {
					    $(this).closest('.list-group-item').find('.comment-content').hide();
					    $(this).closest('.list-group-item').find('.comment-edit-form').show();
					});

					// 저장 버튼 클릭시
					$('.save-edit-comment-btn').click(function() {
					    var commentId = $(this).data("comment-id");
					    var editedContent = $(this).closest('.comment-edit-form').find('textarea').val();
					    
					    // 데이터 확인
					    console.log("Comment ID:", commentId);
					    console.log("Edited Content:", editedContent);
					    
					    var data = JSON.stringify({ content: editedContent });
					    console.log("Data:", data);
					    
					    // AJAX를 사용하여 서버에 수정된 내용을 전송
					    $.ajax({
					        type: "PUT",
					        url: "/api/comment/update/" + commentId, // 수정 요청이 전송될 URL
					        contentType: "application/json",
					        data: JSON.stringify({ id: commentId, content: editedContent }), // 수정된 내용을 JSON 형식으로 전송
					        success: function(response) {
					            console.log(response); // 서버 응답 확인
					            window.location.reload();
					        },
					        error: function(xhr, status, error) {
					            console.error("Error:", error);
					        }
					    });
					});

				});
	</script>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학사관리시스템</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.table td, .table th {
	white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
}

.mainarea {
	padding: 50px;
}

.search--btn {
	background: #5fcf80;
	color: #fff;
	border-radius: 50px;
	padding: 8px 25px;
	white-space: nowrap;
	border: none;
}

.improvements-content {
	display: block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 200px;
	cursor: pointer;
}

.modal-body p {
	word-break: break-all;
}
</style>

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의 평가</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row" style="height: 632px;">
			<%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<main class="mainarea">
					<c:choose>
						<c:when test="${subjectName.isEmpty() == false}">
							<div class="sub--filter">
								<!-- 강의평가 과목 조회 -->
								<form action="/professor/readevaluation" method="post">
									<div>
										<select name="subjectId">
											<c:forEach var="dto" items="${subjectName}">
												<option value="${dto.name}">${dto.name}</option>
											</c:forEach>
										</select>
										<!-- 검색 버튼 -->
										<button type="submit" class="search--btn">조회</button>
									</div>
								</form>
							</div>
							<table class="table table-bordered mt-3">
								<thead>
									<tr>
										<th>과목 이름</th>
										<th>총 평가 점수</th>
										<th>건의 사항</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="eval" items="${eval}">
										<tr>
											<td>${eval.name}</td>
											<td>${eval.answerSum()}</td>
											<td>
												<div class="improvements-container">
													<!-- 건의 사항 내용을 클릭하면 모달로 이동 -->
													<div class="improvements-content">${eval.improvements}</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<p class="no--list--p">조회할 강의 평가가 존재하지 않습니다.</p>
						</c:otherwise>
					</c:choose>
				</main>

				<div style="display: flex; justify-content: center;">
					<c:if test="${totalPages > 1}">
						<nav aria-label="Page navigation" style="text-align: center;">
							<ul class="pagination">
								<c:if test="${currentPage > 1}">
									<li class="page-item"><a class="page-link"
										href="?page=1&size=${size}" aria-label="처음"> <span
											aria-hidden="true">&laquo;</span> <span class="sr-only">처음</span>
									</a></li>
									<li class="page-item"><a class="page-link"
										href="?page=${currentPage - 1}&size=${size}" aria-label="이전">
											<span aria-hidden="true">&lt;</span> <span class="sr-only">이전</span>
									</a></li>
								</c:if>

								<c:forEach var="i" begin="1" end="${totalPages}">
									<c:choose>
										<c:when test="${currentPage eq i}">
											<li class="page-item active"><span class="page-link">${i}</span></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="?page=${i}&size=${size}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${currentPage < totalPages}">
									<li class="page-item"><a class="page-link"
										href="?page=${currentPage + 1}&size=${size}" aria-label="다음">
											<span aria-hidden="true">&gt;</span> <span class="sr-only">다음</span>
									</a></li>
									<li class="page-item"><a class="page-link"
										href="?page=${totalPages}&size=${size}" aria-label="마지막">
											<span aria-hidden="true">&raquo;</span> <span class="sr-only">마지막</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
				</div>



				<!-- 모달 창 -->
				<div class="modal fade" id="improvementsModal" tabindex="-1"
					role="dialog" aria-labelledby="improvementsModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="improvementsModalLabel">건의 사항
									상세 내용</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p id="improvementsContent"></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="/js/evaluation.js"></script>
</body>
</html>

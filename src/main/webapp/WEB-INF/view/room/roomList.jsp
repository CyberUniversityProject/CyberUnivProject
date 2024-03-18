<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의실 리스트</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의실 리스트 페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<!-- 사이드바 메뉴 -->
			<aside class="sidebar col-md-2 mt-5">
				<div class="card">
					<div class="card-header">메뉴</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수명단 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">학생 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">직원 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">등록금 고지서 발송</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">휴학 처리</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">수강 신청 기간 설정</a></li>
					</ul>
				</div>
			</aside>

			<div class="col-md-10" style="text-align: center;">
				<h1 class="mt-5 mb-4"></h1>
				<!-- main 넣는 시작 부분 -->
				<div class="container">
					<table class="table table hover">
						<thead>
							<tr>
								<th scope="col">강의실명</th>
								<th scope="col">단과대학명</th>
							</tr>
						</thead>
						
						<tbody>
						<c:forEach var="room" items="${roomList}">
							<tr>
								<th>${room.id}</th> 
								<th>${room.collegeId}
								<a href="/room/delete/${room.id}">삭제</a>
								<a href="/room/roomUpdate/${room.id}">수정</a>
								</th>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					
  <!-- 페이지네이션 -->
<c:if test="${totalPages > 1}">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="?page=1&size=${size}" aria-label="처음">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">처음</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage - 1}&size=${size}" aria-label="이전">
                        <span aria-hidden="true">&lt;</span>
                        <span class="sr-only">이전</span>
                    </a>
                </li>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><span class="page-link">${i}</span></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage + 1}&size=${size}" aria-label="다음">
                        <span aria-hidden="true">&gt;</span>
                        <span class="sr-only">다음</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="?page=${totalPages}&size=${size}" aria-label="마지막">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">마지막</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</c:if>

    </div>

				</div>
		</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>


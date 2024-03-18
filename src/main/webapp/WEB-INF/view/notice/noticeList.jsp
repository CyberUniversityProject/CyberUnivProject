<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<style>
body > div{
	padding-top: 100px;
}
main {
	height: 500px;
}
.table {
	width: 1000px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>공지사항</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->

<!-- 세부 메뉴 + 메인 -->
<div class="container mb-5">
	<div class="row">
		<!-- 사이드바 메뉴 -->
		<aside class="sidebar col-md-2 mt-5">
			<div class="card">
					<div class="card-header">
						<h5 class="mb-0">학사정보</h5>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a href="/notice" class="text-black"><i
								class="fas fa-user mr-2"></i> 공지사항</a></li>
						<li class="list-group-item p-3"><a href="/schedule" class="text-black"><i
								class="fas fa-lock mr-2"></i> 학사일정</a></li>
						<c:if test="${principal.userRole.equals(\"staff\") }">
						<li class="list-group-item p-3"><a href="/schedule/list" class="text-black"><i
								class="fas fa-lock mr-2"></i> 학사일정 등록</a></li>
						</c:if>
					</ul>
				</div>
		</aside>
	<!-- 메인 div -->
	<div class="col-md-10">
		<div class="main-content">
			<h1>공지사항</h1>
			<hr />
			<div class="row">
			
			<!-- 공지 조회 -->
			<form action="/notice/search" method="get">
				<select class="input--box" name="type">
					<option value="title">제목</option>
					<option value="keyword">제목+내용</option>
				</select>
				<input type="text" name="keyword" class="input--box" placeholder="검색어를 입력하세요"> 
				<input type="submit" class="button" value="검색">
			</form>
			
			<table class="table">
				<tr class="first--tr">
					<td>번호</td>
					<td>말머리</td>
					<td>제목</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<c:forEach var="notice" items="${noticeList}">
					<tr class="second-tr" onclick="location.href='/notice/read?id=${notice.id}';">
						<td>${notice.id}</td>
						<td>${notice.category}</td>
						<td>${notice.title}</td>
						<td>${notice.timeFormat()}</td>
						<td>${notice.views}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="paging--container">
			<!-- 페이징 버튼 들어갈 자리 -->
				<button type="button" onclick="location.href='/notice/write'"
					class="btn btn-dark update--button">등록</button>
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
</body>
</html>
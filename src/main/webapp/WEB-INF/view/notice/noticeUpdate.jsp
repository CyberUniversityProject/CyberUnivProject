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
		
		<!-- 공지 수정 -->
		<div class="container">
			<form action="/notice/update" method="post">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="id" value="${notice.id}">
					<table class="table">
						<tr class="title">
							<td class="type">제목</td>
							<td>${notice.category} <input type="text" name="title" class="update--box" value="${notice.title}"></td>
						</tr>
						<tr class="content--container">
							<td class="type">내용</td>
							<td><textarea rows="20" cols="100" class="textarea" name="content">${notice.content}</textarea></td>
						</tr>
						
					</table>
				<div class="select--button">
					<input type="submit" value="수정" class="button">
				</div>
			</form>
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
<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
body > div{
	padding-top: 100px;
}
main {
	height: 1000px;
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
			
		<!-- 공지 등록 -->
			<div class="write--div">
				<form action="/notice/write" method="post" enctype="multipart/form-data">
					<div class="title--container">
						<div class="category">
							<select name="category" class="input--box">
								<option value="[일반]">[일반]</option>
								<option value="[학사]">[학사]</option>
								<option value="[장학]">[장학]</option>
							</select>
						</div>
						<div class="title">
							<input type="text" class="form-control form-control-sm" name="title" placeholder="제목을 입력하세요" required="required" style="width: 900px;">
						</div>
					</div>
					<div class="content--container">
						<textarea name="content" class="form-control" cols="100" rows="20" placeholder="내용을 입력하세요"></textarea>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="file" accept=".jpg, .jpeg, .png"> <label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<input type="submit" class="button" value="등록">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>교직원 마이페이지</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
}

.breadcrumbs {
	background-color: #343a40;
	color: #fff;
	padding: 20px 0;
	margin-bottom: 30px;
}

.breadcrumbs h2 {
	margin: 0;
}

.main-content {
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.profile-table th {
	width: 25%;
	background-color: #f8f9fa;
	color: #495057;
	border: none;
}

.profile-table td {
	width: 25%;
	border: none;
}

.update--button {
	margin-top: 20px;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>교직원 마이 페이지</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<!-- 사이드바 메뉴 -->
			<aside class="sidebar col-md-2 mt-5 mb-5" >
				<div class="card">
					<div class="card-header">
						<h5 class="mb-0">메뉴</h5>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a href="/info/staff" class="text-black"> 내 정보 조회</a></li>
						<li class="list-group-item p-3"><a href="/password" class="text-black">비밀번호 변경</a></li>
					</ul>
				</div>
			</aside>

			<div class="col-md-10 " style="margin-bottom: 400px;">
				<div class="main-content">
					<h1>내 정보 조회</h1>
					<hr />
					<div class="row">
						<div class="col-md-10">
							<table class="table table-bordered profile-table">
								<tr>
									<th>ID</th>
									<td>${staff.id}</td>
									<th>입사 날짜</th>
									<td>${staff.hireDate}</td>
								</tr>
								<tr>
									<th>성명</th>
									<td>${staff.name}</td>
									<th>생년월일</th>
									<td>${staff.birthDate}</td>
								</tr>
								<tr>
									<th>성별</th>
									<td>${staff.gender}</td>
									<th>주소</th>
									<td>${staff.address}</td>
								</tr>
								<tr>
									<th>연락처</th>
									<td>${staff.tel}</td>
									<th>Email</th>
									<td>${staff.email}</td>
								</tr>
							</table>
							<button type="button" onclick="location.href='/update'"
								class="btn btn-dark update--button">수정하기</button>
						</div>
					</div>
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
</body>
</html>

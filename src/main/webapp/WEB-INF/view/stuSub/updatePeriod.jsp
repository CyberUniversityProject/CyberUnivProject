<%@page import="com.cyber.university.controller.StuSubController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>수강 신청기간 설정</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
.table td, .table th {
	white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
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

.btn--confirm:hover {
	background-color: #45a049;
}

.create--tui {
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	cursor: pointer;
}

.create--tui:hover {
	background-color: #0056b3;
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

.btn--confirm:hover {
	background-color: #45a049;
}
</style>


</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>수강 신청기간 설정</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarStaff.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">

					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>

					<div class="container">
						<!-- 강의 목록 -->
						<div class="row">
							<div class="col-md-12 text-center">
								
								<div class="split--div"></div>
								<%
								if (StuSubController.SUGANG_PERIOD == 0) {
								%>
								<div class="alert alert-warning" role="alert">현재 예비 수강 신청
									기간입니다.</div>

								<br> <a href="/sugang/updatePeriod1"><button
										type="submit" class="btn--confirm">수강 신청 기간 시작</button></a>

								<%
								} else if (StuSubController.SUGANG_PERIOD == 1) {
								%>
								<div class="alert alert-info" role="alert">현재 수강 신청 기간입니다.
								</div>

								<br> <a href="/sugang/updatePeriod2"><button
										type="submit" class="btn--confirm">수강 신청 기간 종료</button></a>

								<%
								} else {
								%>

								<div class="alert alert-danger" role="alert">이번 학기 수강 신청
									기간이 종료되었습니다.</div>


								<%
								}
								%>
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

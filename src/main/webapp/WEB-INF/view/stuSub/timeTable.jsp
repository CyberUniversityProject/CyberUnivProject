<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의 시간표</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.table td, .table th {
	white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
}

.nav-item>.active {
	color: #5fcf80 !important;
}

.nav-item>.nav-link {
	color: #37423B;
}

link:focus, .nav-link:hover {
	color: #5fcf80 !important;
}
</style>


</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의 시간표 조회</h2>

		</div>
	</div>

	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarSugang.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<H3>OOO님의 OOOO년도 O학기 시간표 조회</H3>

					<div class="row">
						<div class="col-md-12"> 
							<hr>
						</div>
					</div>

					<div id="timeTable" class="">
						<div class="container mt-4">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th></th>
											<th>월</th>
											<th>화</th>
											<th>수</th>
											<th>목</th>
											<th>금</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>1교시 <br /> (09:00 ~ 09:50)
											</th>
											<td>장명근 교수의 스피치 강의</td>
											<td>장명근 교수의 게임 이론</td>
											<td>사회학</td>
											<td>프로그래밍 이론학</td>
											<td>자바</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

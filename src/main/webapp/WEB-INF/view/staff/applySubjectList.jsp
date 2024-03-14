<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의 신청 리스트</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.table td, .table th {
	white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
}
.table th {
	text-align: center;
}
</style>

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>교수 강의신청 리스트</h2>

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
					<h1>교수 강의신청 리스트</h1>
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>

					<c:choose>
						<c:when test="${!list.isEmpty()}">
							<h4>
								<span style="font-weight: 600;">교수 강의신청 목록</span>
							</h4>
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th>순번</th>
											<th>교수 아이디</th>
											<th>강의명</th>
											<th>교수이름</th>
											<th>총 강의시간</th>
											<th>전공/교양</th>
											<th>이수학점</th>
											<th>강의인원</th>
											<th>자세히보기</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="applysubject" items="${list}">
										
											<tr>
												<td>${applysubject.id}</td>
												<td>${applysubject.proId}</td>
												<td>${applysubject.subName}</td>
												<td>${applysubject.proName}</td>
												<td>${applysubject.subTime}</td>
												<td>${applysubject.type}</td>
												<td>${applysubject.subGrade}</td>
												<td>${applysubject.capacity}</td>
												<td style="text-align: center;"><a href="/applySubject/detail/${applysubject.id}"
													class="btn btn-primary">바로가기</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</c:when>
						<c:otherwise>
							<p class="no--list--p">신청 내역이 없습니다.</p>
						</c:otherwise>
					</c:choose>
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

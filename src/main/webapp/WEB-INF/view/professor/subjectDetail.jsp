<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<script src="/js/studentDetail.js"></script>
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
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<h4>
						<span style="font-weight: 600;">[${subjectName.subjectName}] 학생리스트</span>
					</h4>
					<div class="table-responsive" style="height: 546px;">
						<table class="table table-bordered">
							<thead class="thead-light">
								<tr>
									<th>학생 번호</th>
									<th>이름</th>
									<th>소속</th>
									<th>결석</th>
									<th>지각</th>
									<th>과제점수</th>
									<th>중간시험</th>
									<th>기말시험</th>
									<th>환산점수</th>
									<th>점수 기입</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="evaluation" items="${mySubEvaluation}">
									<tr>
										<td>${evaluation.studentId}</td>
										<td>${evaluation.studentName}</td>
										<td>${evaluation.departmentName}</td>
										<td>${evaluation.absent}</td>
										<td>${evaluation.lateness}</td>
										<td>${evaluation.homework}</td>
										<td>${evaluation.midExam}</td>
										<td>${evaluation.finalExam}</td>
										<td>${evaluation.convertedMark}</td>
										<td><a href="/professor/subject/${evaluation.subjectId}/${evaluation.studentId}">기입</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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

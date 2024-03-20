<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 강의 조회</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.table td, .table th {
	white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
}
	
ul	{
	list-style-type: none;
}

#mysub--btn {
   background: #5fcf80;
   border-radius: 50px;
   padding: 8px 25px;
   border: none;
   color: #fff;
}	
</style>

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>내 강의 조회</h2>

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
					<!-- 필터 및 검색 -->
					<div class="row mb-3">
						<div class="col-md-7">
							<form action="/professor/mysub" method="post">
								<div>
									<select name="period">
										<c:forEach items="${semesterList}" var="yearSemester">
											<option
												value="${yearSemester.subYear}year${yearSemester.semester}">${yearSemester.subYear}년도&nbsp;${yearSemester.semester}학기</option>
										</c:forEach>
									</select>
									<button type="submit" id="mysub--btn">조회</button>
								</div>
							</form>
						</div>
					</div>
					<c:choose>
						<c:when test="${!professorList.isEmpty()}">
							<h4>
								<span style="font-weight: 600;">강의 목록</span>
							</h4>
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th>학수번호</th>
											<th>강의명</th>
											<th>강의시간</th>
											<th style="text-align: center;">강의계획서</th>
											<th style="text-align: center;">학생 목록</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>
												<td>${subject.id}</td>
												<td>${subject.name}</td>

												<td>
													<c:choose>
														<c:when test="${subject.startTime < 10}">
															${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00&nbsp;(${subject.roomId})								
														</c:when>
														<c:otherwise>
															${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00&nbsp;(${subject.roomId})							
														</c:otherwise>
													</c:choose>
												</td>
												<td style="text-align: center; padding: 5px;">									
												    <ul class="d-flex justify-content-center sub--plan--view" style="margin: 0; padding: 0;">
												        <li style="height: 24px;"><a href="/professor/syllabus/${subject.id}" onclick="window.open(this.href, '_blank', 'width=1000, height=1000'); return false;">조회</a></li>														
												    </ul>
												</td>
												<td style="text-align: center; padding: 5px;">
												    <ul class="d-flex justify-content-center sub--plan--view" style="margin: 0; padding: 0;">
												        <li style="height: 24px;"><a href="/professor/subject/${subject.id}">조회</a></li>														
												    </ul>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>							
							<ul class="pagination">
								<c:forEach var="index" begin="1" end="${listCount}">
									<li class="page-item"><c:choose>
											<c:when test="${deptId != null && index != page}">
												<a class="page-link"
													href="/user/professorList/${index}?deptId=${deptId}">${index}</a>
											</c:when>
											<c:when test="${deptId != null && index == page}">
												<a class="page-link"
													href="/user/professorList/${index}?deptId=${deptId}">${index}</a>
											</c:when>
											<c:when test="${deptId == null && index == page}">
												<a class="page-link" href="/user/professorList/${index}">${index}</a>
											</c:when>
											<c:otherwise>
												<a class="page-link" href="/user/professorList/${index}">${index}</a>
											</c:otherwise>
										</c:choose></li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<p class="no--list--p">검색 결과가 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>		
</body>
</html>

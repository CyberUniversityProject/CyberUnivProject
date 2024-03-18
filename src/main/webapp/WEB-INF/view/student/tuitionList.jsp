<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>등록금 납부 리스트</title>
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
			<h2>등록금 납부 조회</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/mypageAsidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<h1>등록금 납부 조회</h1>
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					<!-- 필터 및 검색 -->
					<div class="row mb-3">
						<div class="col-md-7">
							<form action="/user/studentList" method="get" class="form-inline">
								<div class="form-group mr-2">
									<label for="deptId">학과 : ${student.department } </label>&nbsp;&nbsp;&nbsp;
									<label for="studentId">학번 :${student.studentId } </label>&nbsp;&nbsp;&nbsp;
									<label for="studentId">이름 :${student.name } </label>&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
								</div>

							</form>
						</div>
					</div>
					
					
					<c:choose>
						<c:when test="${!tuitionList.isEmpty()}">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th>등록 연도</th>
											<th>등록 학기</th>
											<th>장학금 유형</th>
											<th>등록금</th>
											<th>장학금</th>
											<th>납입금</th>
											<th>상태</th>
											<th>납부 확인</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tuition" items="${tuitionList}">
											<tr>
												<td>${tuition.tuiYear}</td>
												<td>${tuition.semester}</td>
												<td>${tuition.schType}</td>
												<td id="formatTuiAmount"><input type="hidden"
													value="${tuition.tuiAmount}" id="tuiAmount" /></td>
												<td id="formatSchAmount"><input type="hidden"
													value="${tuition.schAmount}" id="schAmount" /></td>
												<td id="totalAmount"></td>
												<c:choose>
													<c:when test="${tuition.status eq '0'}">
														<td>미납</td>
													</c:when>
													<c:otherwise>
														<td>납부 완료</td>
													</c:otherwise>
												</c:choose>




												<td><a
													href="/student/tuitionBill?tuiYear=${tuition.tuiYear }&semester=${tuition.semester}"
													onclick="window.open(this.href, '_blank', 'width=600, height=900'); return false;">
														<c:choose>
															<c:when test="${tuition.status eq '0'}">
																<button class="btn btn-primary" id="tuitionButton"
																	type="button">등록금 납부</button>

															</c:when>
															<c:otherwise>
																<button class="btn btn-primary" id="tuitionButton"
																	type="button">등록금 영수증</button>

															</c:otherwise>
														</c:choose>
												</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<ul class="pagination">
								<c:forEach var="index" begin="1" end="${listCount}">
									<li class="page-item">
										<c:choose>
											<c:when test="${deptId != null && index != page}">
												<a class="page-link"
													href="/user/studentList/${index}?deptId=${deptId}">${index}</a>
											</c:when>
											<c:when test="${deptId != null && index == page}">
												<a class="page-link"
													href="/user/studentList/${index}?deptId=${deptId}">${index}</a>
											</c:when>
											<c:when test="${deptId == null && index == page}">
												<a class="page-link" href="/user/studentList/${index}">${index}</a>
											</c:when>
											<c:otherwise>
												<a class="page-link" href="/user/studentList/${index}">${index}</a>
											</c:otherwise>
										</c:choose>
									</li>
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
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		let tuiAmount = document.getElementById('tuiAmount').value;
		let schAmount = document.getElementById('schAmount').value;
		let totalAmount = new Intl.NumberFormat().format(tuiAmount - schAmount)
				+ '원';

		document.getElementById('formatTuiAmount').textContent = new Intl.NumberFormat()
				.format(tuiAmount)
				+ '원';
		document.getElementById('formatSchAmount').textContent = new Intl.NumberFormat()
				.format(schAmount)
				+ '원';
		document.getElementById('totalAmount').textContent = totalAmount;
	</script>
</body>
</html>

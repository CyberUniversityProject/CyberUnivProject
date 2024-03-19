<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학점 내역</title>
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
			<h2>성적</h2>

		</div>
	</div>

	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/studentGradeSidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<H3>성적 조회</H3>

					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>

					<ul class="nav nav-underline nav-tabs">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#id1">금학기 성적</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#id2">학기별 성적 조회</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#id3">누계 성적</a></li>
					</ul>
					<div class="tab-content">



						<div id="id1" class="tab-pane fade  show active ">
							<c:choose>
								<c:when test="${!thisSemesterGradeList.isEmpty()}">
									<div class="container mt-4">
										<H5>금학기 성적 조회</H5>

										<div class="table-responsive">
											<table class="table table-bordered">
												<thead class="thead-light">
													<tr>
														<th>연도</th>
														<th>학기</th>
														<th>과목번호</th>
														<th>과목명</th>
														<th>강의 구분</th>
														<th>이수 학점</th>
														<th>성적</th>
														<th>강의 평가</th>
													</tr>
												</thead>
												<tbody>

													<c:forEach var="thisSemesterGrade"
														items="${thisSemesterGradeList}">
														<tr>
															<td>${thisSemesterGrade.subYear}</td>
															<td>${thisSemesterGrade.semester}</td>
															<td>${thisSemesterGrade.subjectId}</td>
															<td>${thisSemesterGrade.subjectName}</td>
															<td>${thisSemesterGrade.type}</td>
															<td>${thisSemesterGrade.grades}</td>
															<td>${thisSemesterGrade.grade}</td>
															<td>강의 평가
																<p>해당 강의평가가 insert된 상태여야만 성적 확인가능하게</p>
															</td>

														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<hr>
										</div>
									</div>

									<div class="container mt-4">
										<H5>금학기 총점 조회</H5>

										<div class="table-responsive">
											<table class="table table-bordered">
												<thead class="thead-light">
													<tr>
														<th>연도</th>
														<th>학기</th>
														<th>신청 학점</th>
														<th>취득 학점</th>
														<th>학점 평균</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>${thisSemesterTotalScore.subYear }</td>
														<td>${thisSemesterTotalScore.semester }</td>
														<td>${thisSemesterTotalScore.totalGrades}</td>
														<td>${thisSemesterTotalScore.totalCompleteGrade }</td>
														<td>${thisSemesterTotalScore.averageGrade }</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<p class="no--list--p">성적 조회 기간이 아닙니다.</p>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="id2" class="tab-pane fade">
							<div class="container mt-4">
								<H5>학기별 성적 조회</H5>
								<div class="row">
									<div class="col-md-12">
										<form action="/student/list/search" method="get" class="mb-4">
											<input type="hidden" name="gradeTab" value="${gradeList}">

											<div class="row">
												<div class="col-md-3">
													<label for="subYear">연도</label> <input type="number"
														class="form-control" value="<%=Define.CURRENT_YEAR%>"
														name="subYear" id="subYear" min="2005" max="2024">
												</div>
												<div class="col-md-3">
													<label for="subSemester">학기</label> <select
														class="form-control" name="semester" id="subSemester">
														<option value="1">1학기</option>
														<option value="2">2학기</option>
													</select>
												</div>
												<div class="col-md-3">
													<label for="type">분류</label> <select class="form-control"
														name="type" id="type">
														<option value="전체">전체</option>
														<option value="전공">전공</option>
														<option value="교양">교양</option>
													</select>
												</div>
											</div>
											<button type="submit" class=" btn--confirm mt-3">
												<i class="fas fa-search"></i> 검색
											</button>
										</form>
									</div>
								</div>
								<!-- 강의 목록 -->
								<div class="row">
									<div class="col-md-12">
										<c:choose>
											<c:when test="${gradeList.isEmpty() == false}">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th>연도/학기</th>
																<th>과목번호</th>
																<th style="width: 200px;">강의명</th>
																<th>강의구분</th>
																<th>이수 학점</th>
																<th>성적</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="allGrade" items="${gradeList}">
																<c:if test="${not empty allGrade.grade}">
																	<tr>
																		<td>${allGrade.subYear}-${allGrade.semester}학기</td>
																		<td>${allGrade.subjectId}</td>
																		<td class="sub--list--name">${allGrade.subjectName}</td>
																		<td>${allGrade.type}</td>
																		<td>${allGrade.grades}</td>
																		<td>${allGrade.grade}</td>
																	</tr>
																</c:if>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</c:when>
											<c:otherwise>
												<p class="no--list--p">검색 결과가 없습니다.</p>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
						<!-- id3 -->
						<div id="id3" class="tab-pane fade">

							<div class="container mt-4">
								<H5>학기별 총점 조회</H5>


								<c:choose>
									<c:when test="${!allSemesterTotalScoreList.isEmpty()}">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead class="thead-light">
													<tr>
														<th>연도</th>
														<th>학기</th>
														<th>신청학점</th>
														<th>취득학점</th>
														<th>학점 평균</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="allSemesterTotalScore"
														items="${allSemesterTotalScoreList}">
														<tr>
															<td>${allSemesterTotalScore.subYear }</td>
															<td>${allSemesterTotalScore.semester }</td>
															<td>${allSemesterTotalScore.totalGrades }</td>
															<td>${allSemesterTotalScore.totalCompleteGrade }</td>
															<td>${allSemesterTotalScore.averageGrade }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:when>
									<c:otherwise>
										<p class="no--list--p">확인 할 수 있는 성적 정보가 없습니다.</p>
									</c:otherwise>
								</c:choose>

								<div class="container mt-5">
									<H5>전체 총점 조회</H5>

									<div class="table-responsive">
										<table class="table table-bordered">
											<thead class="thead-light">
												<tr>
													<th>총 신청학점</th>
													<th>총 취득학점</th>
													<th>총 학점 평균</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${totalScore.totalGrades}</td>
													<td>${totalScore.totalCompleteGrade}</td>
													<td class="rounder average">${totalScore.averageGrade}</td>
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
		<script>
			document.addEventListener("DOMContentLoaded", function() {
				// HTML에서 클래스 이름이 "average"인 요소의 텍스트 내용을 가져오기
				var averageElement = document.querySelector('.average');

				// 가져온 텍스트 내용을 숫자로 변환하여 소수점 세 자리에서 반올림
				var averageGrade = parseFloat(averageElement.textContent);
				var roundedAverageGrade = Math.round(averageGrade * 100) / 100;

				// 반올림된 평균 등급을 HTML 요소에 할당하여 표시
				averageElement.textContent = roundedAverageGrade;

				// 검색 폼이 제출될 때 AJAX 요청을 보냄
				$('form').submit(function(event) {
					event.preventDefault(); // 기본 제출 동작을 막음
					var formData = $(this).serialize(); // 폼 데이터를 가져와서 시리얼라이즈
					$.ajax({
						type : 'GET',
						url : '/student/list/search',
						data : formData,
						success : function(response) {
							
							console.log(response[0].subYear);
							
						    var html = '';
						    if (response && response.length > 0) {
						        $.each(response, function(index, allGrade) {
						        	 if (allGrade.grade !== null) {
						                 html += '<tr>';
						                 html += '<td>' + allGrade.subYear + '-' + allGrade.semester + '학기</td>';
						                 html += '<td>' + allGrade.subjectId + '</td>';
						                 html += '<td class="sub--list--name">' + allGrade.subjectName + '</td>';
						                 html += '<td>' + allGrade.type + '</td>';
						                 html += '<td>' + allGrade.grades + '</td>';
						                 html += '<td>' + allGrade.grade + '</td>';
						                 html += '</tr>';
						             }
						        });
						    } else {
						        // 검색 결과가 없는 경우 메시지를 생성
						        html += '<tr><td colspan="6">검색 결과가 없습니다.</td></tr>';
						    }

				            // 생성된 HTML 코드를 해당 테이블의 tbody에 추가 또는 업데이트
				            $('div#id2 table.table-bordered tbody').html(html);
						},
						error : function(xhr, status, error) {
							console.error(xhr.responseText);
							// 오류 처리 로직 추가
						}
					});
				});
			});
		</script>
</body>
</html>

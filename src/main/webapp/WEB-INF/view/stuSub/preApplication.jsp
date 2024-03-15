<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예비 수강신청</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
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
			<h2>예비 수강신청</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarStaff.jsp"%>
			<div class="col-md-10">
				<h1 class="mt-5 mb-4">예비 수강신청</h1>
				<hr />
				<div class="row justify-content-center">
					<div class="col-md-12">
						<div class="split--div"></div>
						<div class="d-flex justify-content-between align-items-start">
							<!-- 필터 및 검색 -->
							<div class="sub--filter row">
								<form action="/sugang/pre/search" method="get" class="col-md-12">
									<div class="form-row">
										<div class="form-group col-sm-12 col-md-4">
											<!-- 강의구분 콤보박스 -->
											<label for="type">강의구분</label> <select class="form-control"
												name="type" id="type">
												<option value="전체">전체</option>
												<option value="전공">전공</option>
												<option value="교양">교양</option>
											</select>
										</div>
										<div class="form-group col-sm-12 col-md-4">
											<!-- 대상학과 콤보박스 -->
											<label for="deptId">개설학과</label> <select class="form-control"
												name="deptId" id="deptId">
												<option value="-1">전체</option>
												<c:forEach var="dept" items="${deptList}">
													<option value="${dept.id}">${dept.name}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group col-sm-12 col-md-4">
											<!-- 강의 검색 -->
											<label for="subName">강의명</label>
											<div class="input-group">
												<input type="text" name="name" list="subName"
													class="form-control" style="width: calc(100% - 90px);">
												<datalist id="subName">
													<c:forEach var="subName" items="${subNameList}">
														<option value="${subName}">
													</c:forEach>
												</datalist>
												<div class="input-group-append">
													<!-- 검색 버튼 -->
													<button type="submit" class="btn btn-primary">조회</button>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>



							<!-- 예비 수강 신청 내역으로 가기 -->
							<a href="/sugang/preAppList?type=0" class="btn btn-secondary">예비
								수강 신청 내역</a>
						</div>
						<c:choose>
							<c:when test="${subjectList.isEmpty() == false}">
								<h4>
									<span style="font-weight: 600;">강의 목록</span>&nbsp; <span
										style="color: gray; font-size: 18px;">[총
										${subjectCount}건]</span>
								</h4>
								<table class="table table-bordered">
									<thead class="thead-dark">
										<tr>
											<th>단과대학</th>
											<th>개설학과</th>
											<th>학수번호</th>
											<th>강의구분</th>
											<th style="width: 200px;">강의명</th>
											<th>담당교수</th>
											<th>학점</th>
											<th>요일시간 (강의실)</th>
											<th>현재인원</th>
											<th>정원</th>
											<th>수강신청</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>
												<td>${subject.collName}</td>
												<td>${subject.deptName}</td>
												<td>${subject.id}</td>
												<td>${subject.type}</td>
												<td class="sub--list--name">${subject.name}</td>
												<td>${subject.professorName}</td>
												<td>${subject.grades}</td>
												<td><c:choose>
														<c:when test="${subject.startTime < 10}">
                                                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00&nbsp;(${subject.roomId})
                                                        </c:when>
														<c:otherwise>
                                                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00&nbsp;(${subject.roomId})
                                                        </c:otherwise>
													</c:choose></td>
												<td>${subject.numOfStudent}</td>
												<td>${subject.capacity}</td>
												<td class="sub--list--button--row"><c:choose>
														<%-- 신청된 상태라면 --%>
														<c:when test="${subject.status == true}">
															<form action="/sugang/pre/${subject.id}?type=0"
																method="post">
																<input type="hidden" name="_method" value="delete">
																<button type="submit" class="btn btn-secondary"
																	onclick="return confirm('수강신청을 취소하시겠습니까?');">취소</button>
															</form>
														</c:when>
														<%-- 신청되지 않은 상태라면 --%>
														<c:otherwise>
															<form action="/sugang/pre/${subject.id}" method="post">
																<button type="submit" class="btn btn-primary"
																	onclick="return confirm('해당 강의를 수강신청하시겠습니까?');">신청</button>
															</form>
														</c:otherwise>
													</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<c:if test="${pageCount != null}">
									<ul class="pagination">
										<c:forEach var="i" begin="1" end="${pageCount}" step="1">
											<li class="page-item"><c:choose>
													<c:when test="${i == page}">
														<a class="page-link" href="/sugang/pre/${i}"
															style="font-weight: 700; color: #007bff">${i}</a>
													</c:when>
													<c:otherwise>
														<a class="page-link" href="/sugang/pre/${i}">${i}</a>
													</c:otherwise>
												</c:choose></li>
										</c:forEach>
									</ul>
								</c:if>
							</c:when>
							<c:otherwise>
								<p class="no--list--p">검색 결과가 없습니다.</p>
							</c:otherwise>
						</c:choose>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의 등록</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의 등록페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarRegistration.jsp"%>

			
<div class="col-md-10">



				
					<div class="card mt-5">
						<div class="card-header">📖 등록된 강의목록</div>
						<div class="card-body">
							<div class="form--container">
								<table class="table--container table table-striped">
									<thead>
										<tr class="first--tr">
											<th>ID</th>
											<th>강의명</th>
											<th>교수</th>
											<th>강의실</th>
											<th>학과ID</th>
											<th>구분</th>
											<th>연도</th>
											<th>학기</th>
											<th>시간</th>
											<th>이수학점</th>
											<th>정원</th>
											<th>신청인원</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>

												<td>${subject.id}</td>
												<td>${subject.name}</td>
												<td>${subject.professorId}(${subject.professorName} 교수)</td>
												<td>${subject.roomId}</td>
												<td>${subject.deptId}</td>
												<td>${subject.type}</td>
												<td>${subject.subYear}</td>
												<td>${subject.semester}</td>
												<td><c:choose>
														<c:when test="${subject.startTime < 10}">
                                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00
                                        </c:when>
														<c:otherwise>
                                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00
                                        </c:otherwise>
													</c:choose></td>
												<td>${subject.grades}</td>
												<td>${subject.capacity}</td>
												<td>${subject.numOfStudent}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div style="display: flex; justify-content: center;">
    <c:if test="${totalPages > 1}">
        <nav aria-label="Page navigation" style="text-align: center;">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=1&size=${size}" aria-label="처음">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">처음</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}&size=${size}" aria-label="이전">
                            <span aria-hidden="true">&lt;</span>
                            <span class="sr-only">이전</span>
                        </a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${totalPages <= 5}">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active"><span class="page-link">${i}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${currentPage le 3}">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:when test="${currentPage ge totalPages - 2}">
                                <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" begin="${currentPage - 2}" end="${currentPage + 2}">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&size=${size}" aria-label="다음">
                            <span aria-hidden="true">&gt;</span>
                            <span class="sr-only">다음</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${totalPages}&size=${size}" aria-label="마지막">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">마지막</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
					</div>
				

			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	
	



</body>
</html>

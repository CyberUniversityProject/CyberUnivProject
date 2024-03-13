<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>휴학신청내역조회</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.application-form {
	width: 100%; /* 수정된 부분: 테이블 폭을 더 넓게 조정 */
	margin: 0 auto;
	border-collapse: collapse;
}

.application-form th, .application-form td {
	border: 1px solid #dddddd;
	padding: 8px;
	text-align: left;
}

.application-form th {
	background-color: #f2f2f2;
}

.application-form p {
	margin: 0;
}

/* 수정된 부분: 버튼 가운데 정렬 */
.button-container {
	display: flex;
	justify-content: center;
	margin-top: 20px; /* 버튼과 위의 내용 사이 여백 설정 */
}
</style>

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>휴학신청내역조회</h2>

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
					<h1>휴학신청내역조회</h1>
					${breakApp }
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					<div class="split--div"></div>

					<div class=" flex-column align-items-center" style="width: 100%">
						<div class="document--layout">
							<h3>휴학 신청서</h3>
							<table class="application-form mb-3">
								<tr>
									<th>단 과 대</th>
									<td>${collName}</td>
									<th>학 과</th>
									<td>${deptName}</td>
								</tr>
								<tr>
									<th>학 번</th>
									<td>${student.id}</td>
									<th>학 년</th>
									<td>${breakApp.studentGrade}학년</td>
								</tr>
								<tr>
									<th>전 화 번 호</th>
									<td>${student.tel}</td>
									<th>성 명</th>
									<td>${student.name}</td>
								</tr>
								<tr>
									<th>주 소</th>
									<td colspan="3">${student.address}</td>
								</tr>
								<tr>
									<th>기 간</th>
									<td colspan="3">${breakApp.fromYear}년도
										${breakApp.fromSemester}학기부터&nbsp; ${breakApp.toYear}년도
										${breakApp.toSemester}학기까지</td>
								</tr>
								<tr>
									<th>휴 학 구 분</th>
									<td colspan="3">${breakApp.type}</td>
								</tr>
								<tr>
									<td colspan="4">
										<p>위와 같이 휴학하고자 하오니 허가하여 주시기 바랍니다.</p>
										<p>${breakApp.appDateFormat()}</p>
									</td>
								</tr>
							</table>
						</div>
<!-- 
						<c:if test="${breakApp.status.equals(\"처리중\")}">
							<c:choose>
								<c:when test="${principal.userRole.equals(\"student\")}">
									<form action="/break/delete/${breakApp.id}" method="post"
										class="d-flex flex-column align-items-center">
										<button type="submit" class="btn btn-dark"
											onclick="return confirm('신청을 취소하시겠습니까?')">취소하기</button>
									</form>
								</c:when>
								<c:when test="${principal.userRole.equals(\"staff\")}">
									<!-- 수정된 부분: 버튼 가운데 정렬 --><!-- 
                                <div class="button-container">
                                    <form action="/break/update/${breakApp.id}" method="post"
                                          class="d-flex flex-column align-items-center">
                                        <input type="hidden" name="status" value="승인">
                                        <button type="submit" class="btn btn-dark me-3"
                                                onclick="return confirm('해당 신청을 승인하시겠습니까?')">승인하기</button>
                                    </form>
                                    <form action="/break/update/${breakApp.id}" method="post"
                                          class="d-flex flex-column align-items-center">
                                        <input type="hidden" name="status" value="반려">
                                        <button type="submit" class="btn btn-dark"
                                                onclick="return confirm('해당 신청을 반려하시겠습니까?')">반려하기</button>
                                    </form>
                                </div>
								</c:when>
							</c:choose>
						</c:if>
 --><c:if test="${breakApp.status.equals(\"처리중\")}">
    <c:choose>
        <c:when test="${principal.userRole.equals(\"student\")}">
            <form id="cancelForm" action="/break/delete/${breakApp.id}" method="post"
                  class="d-flex flex-column align-items-center">
                <button type="submit" class="btn btn-dark"
                        onclick="return confirm('신청을 취소하시겠습니까?')">취소하기</button>
            </form>
        </c:when>
        <c:when test="${principal.userRole.equals(\"staff\")}">
            <div class="button-container">
                <form id="approveForm" action="/break/update/${breakApp.id}" method="post"
                      class="d-flex flex-column align-items-center">
                    <input type="hidden" name="status" value="승인">
                    <button id="approveButton" type="button" class="btn btn-dark me-3">
                        승인하기
                    </button>
                </form>
                <form id="rejectForm" action="/break/update/${breakApp.id}" method="post"
                      class="d-flex flex-column align-items-center">
                    <input type="hidden" name="status" value="반려">
                    <button id="rejectButton" type="button" class="btn btn-dark">
                        반려하기
                    </button>
                </form>
            </div>
        </c:when>
    </c:choose>
</c:if>
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
		
		
		<script type="text/javascript">

		document.addEventListener("DOMContentLoaded", function(){
		 // 취소하기 버튼 클릭 이벤트 처리
	    document.getElementById('cancelForm').addEventListener('submit', function (event) {
	        event.preventDefault(); // 기본 동작 방지
	        if (confirm('신청을 취소하시겠습니까?')) {
	            this.submit(); // 폼 제출
	        }
	    });

	    // 승인하기 버튼 클릭 이벤트 처리
	    let approveBtn = document.getElementById('approveButton')
	    
	    approveBtn.addEventListener('click', function () {
	        if (confirm('해당 신청을 승인하시겠습니까?')) {
	            document.getElementById('approveForm').submit(); // 폼 제출
	        }
	    });

	    // 반려하기 버튼 클릭 이벤트 처리
	    document.getElementById('rejectButton').addEventListener('click', function () {
	        if (confirm('해당 신청을 반려하시겠습니까?')) {
	            document.getElementById('rejectForm').submit(); // 폼 제출
	        }
	    });
		});
		
		</script>
</body>
</html>

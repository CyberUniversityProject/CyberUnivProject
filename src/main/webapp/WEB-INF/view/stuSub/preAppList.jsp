<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예비 수강신청 목록</title>
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
			<h1>
            							<c:choose>
            								<c:when test="${type == 0}">
            					예비 수강 신청 내역
            				</c:when>
            								<c:otherwise>
            					수강 신청 내역
            				</c:otherwise>
            							</c:choose>
            						</h1>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarSugang.jsp"%>
			<div class="col-md-10">
<h1 class="mt-5 mb-4"></h1>
				<div class="row justify-content-center">
					<div class="col-md-12">
						<div class="split--div"></div>

						<hr />
						<div class="split--div"></div>
						<!-- 여기에 내용 넣기 -->
						<div class="d-flex justify-content-between align-items-start" style="width: 100%">
                            <div>
                                <c:choose>
                                    <c:when test="${not empty stuSubList or not empty stuSubListC}">
                                        <c:if test="${not empty stuSubList}">
                                            <h4 class="font-weight-bold">
                                                <c:choose>
                                                    <c:when test="${type == 0}">
                                                        신청 내역&nbsp;
                                                        <span class="text-secondary font-size-18">[총 ${sumGrades}학점]</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        신청 미완료 강의 목록
                                                    </c:otherwise>
                                                </c:choose>
                                            </h4>
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>학수번호</th>
                                                        <th style="width: 250px;">강의명</th>
                                                        <th>담당교수</th>
                                                        <th>학점</th>
                                                        <th>요일시간 (강의실)</th>
                                                        <th>현재인원</th>
                                                        <th>정원</th>
                                                        <th>수강신청</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="stuSub" items="${stuSubList}">
                                                        <tr>
                                                            <td>${stuSub.subjectId}</td>
                                                            <td class="sub--list--name">${stuSub.subjectName}</td>
                                                            <td>${stuSub.professorName}</td>
                                                            <td>${stuSub.grades}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${stuSub.startTime < 10}">
                                                                        ${stuSub.subDay} 0${stuSub.startTime}:00-${stuSub.endTime}:00 (${stuSub.roomId})
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${stuSub.subDay} ${stuSub.startTime}:00-${stuSub.endTime}:00 (${stuSub.roomId})
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${stuSub.numOfStudent}</td>
                                                            <td>${stuSub.capacity}</td>
                                                            <td class="sub--list--button--row">
                                                                <c:choose>
                                                                    <c:when test="${type == 0}">
                                                                        <form action="/sugang/pre/${stuSub.subjectId}?type=1" method="post">
                                                                            <input type="hidden" name="_method" value="delete">
                                                                            <button type="submit" onclick="return confirm('예비수강신청을 취소하시겠습니까?');" class="btn btn-sm btn-secondary">취소</button>
                                                                        </form>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <form action="/sugang/insertApp/${stuSub.subjectId}?type=1" method="post">
                                                                            <button type="submit" class="btn btn-sm btn-primary">신청</button>
                                                                        </form>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                        <c:if test="${not empty stuSubListC}">
                                            <h4 class="font-weight-bold">
                                                신청 내역&nbsp; <span class="text-secondary font-size-18">[총 ${sumGrades}학점]</span>
                                            </h4>
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>과목번호</th>
                                                        <th style="width: 250px;">강의명</th>
                                                        <th>담당교수</th>
                                                        <th>학점</th>
                                                        <th>요일시간 (강의실)</th>
                                                        <th>현재인원</th>
                                                        <th>정원</th>
                                                        <th>수강신청</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="stuSub" items="${stuSubListC}">
                                                        <tr>
                                                            <td>${stuSub.subjectId}</td>
                                                            <td class="sub--list--name">${stuSub.subjectName}</td>
                                                            <td>${stuSub.professorName}</td>
                                                            <td>${stuSub.grades}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${stuSub.startTime < 10}">
                                                                        ${stuSub.subDay} 0${stuSub.startTime}:00-${stuSub.endTime}:00 (${stuSub.roomId})
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${stuSub.subDay} ${stuSub.startTime}:00-${stuSub.endTime}:00 (${stuSub.roomId})
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${stuSub.numOfStudent}</td>
                                                            <td>${stuSub.capacity}</td>
                                                            <td class="sub--list--button--row">
                                                                <form action="/sugang/deleteApp/${stuSub.subjectId}?type=1" method="post">
                                                                    <input type="hidden" name="_method" value="delete">
                                                                    <button type="submit" onclick="return confirm('수강신청을 취소하시겠습니까?');" class="btn btn-sm btn-secondary">취소</button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="no--list--p">예비 수강 신청 내역이 없습니다.</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <!-- 강의 검색으로 가기 -->
                            <c:choose>
                                <%-- 예비 수강 신청 --%>
                                <c:when test="${type == 0}">
                                    <a href="/sugang/pre/1" class="btn btn-primary">강의 검색하러 가기</a>
                                </c:when>
                                <%-- 수강 신청 --%>
                                <c:otherwise>
                                    <a href="/sugang/application/1" class="btn btn-primary">강의 검색하러 가기</a>
                                </c:otherwise>
                            </c:choose>
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

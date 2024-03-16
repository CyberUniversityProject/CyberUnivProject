<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>수강신청 목록</title>
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
			<h2>수강 신청 내역 조회</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarSugang.jsp"%>
			<div class="col-md-10">
				<h1 class="mt-5 mb-4"></h1>
				<hr />

                <div class="split--div"></div>
                <!-- 여기에 내용 넣기 -->
                <div class="container-fluid">
                    <div class="row">
                        <div class="col">
                            <c:choose>
                                <c:when test="${stuSubList.size() > 0}">
                                    <h4>
                                        <span style="font-weight: 600;">신청 내역</span>&nbsp;
                                        <span style="color:gray; font-size:18px;">[총 ${sumGrades}학점]</span>
                                    </h4>
                                    <div class="table-responsive">
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
                                                <c:forEach var="stuSub" items="${stuSubList}">
                                                    <tr>
                                                        <td>${stuSub.subjectId}</td>
                                                        <td class="sub--list--name">${stuSub.subjectName}</td>
                                                        <td>${stuSub.professorName}</td>
                                                        <td>${stuSub.grades}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${stuSub.startTime < 10}">
                                                                    ${stuSub.subDay} 0${stuSub.startTime}:00-${stuSub.endTime}:00&nbsp;(${stuSub.roomId})
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${stuSub.subDay} ${stuSub.startTime}:00-${stuSub.endTime}:00&nbsp;(${stuSub.roomId})
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>${stuSub.numOfStudent}</td>
                                                        <td>${stuSub.capacity}</td>
                                                        <td class="sub--list--button--row">
                                                            <form action="/sugang/deleteApp/${stuSub.subjectId}?type=1" method="post">
                                                                <input type="hidden" name="_method" value="delete">
                                                                <button type="submit" onclick="return confirm('수강신청을 취소하시겠습니까?');" class="btn btn-danger">취소</button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <p class="no--list--p">수강 신청 내역이 없습니다.</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
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

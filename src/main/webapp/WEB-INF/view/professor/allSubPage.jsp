<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>교수 명단 조회</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
 
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
        <h2>전체 강의 조회</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
<div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
	 <%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>

    <div class="col-md-10">
      
      <!-- 메인 div -->
	<div class="container mt-5">
    <h1>전체 강의 조회</h1>
    <div class="row">
        <div class="col-md-12">
            <hr>
        </div>
    </div>
    <!-- 필터 및 검색 -->
    <div class="row mb-3">
        <div class="col-md-7">
            <form action="" method="get" class="form-inline">
                <div class="form-group mr-2">
                    <label for="deptId">학과 번호 </label>&nbsp;&nbsp;&nbsp;
                    <input type="text" name="deptId" id="deptId" class="form-control">&nbsp;&nbsp;&nbsp;
                     &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-primary">조회하기</button>
               
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
                            <th>강의명</th>
							<th>교수 아이디</th>
							<th>강의실</th>
							<th>학과번호</th>
							<th>타입</th>
							<th>개강 년도</th>
							<th>개강 학기</th>
							<th>개강 요일</th>
							<th>시작 시간</th>
							<th>종료 시간</th>
							<th>이수 학점</th>
							<th>수강 정원</th>
							<th>현재 신청 인원</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="subInfo" items="${subInfoList}">
                            <tr>
								<td>${subInfo.name}</td>
								<td>${subInfo.professorId}</td>
								<td>${subInfo.roomId}</td>
								<td>${subInfo.departmentId}</td>
								<td>${subInfo.type}</td>
								<td>${subInfo.subYear}</td>
								<td>${subInfo.semester}</td>
								<td>${subInfo.subDay}</td>
								<td>${subInfo.startTime}</td>
								<td>${subInfo.endTime}</td>
								<td>${subInfo.grades}</td>
								<td>${subInfo.capacity}</td>
								<td>${subInfo.numOfStudent}</td>
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
                                <a class="page-link" href="/user/professorList/${index}?deptId=${deptId}">${index}</a>
                            </c:when>
                            <c:when test="${deptId != null && index == page}">
                                <a class="page-link" href="/user/professorList/${index}?deptId=${deptId}">${index}</a>
                            </c:when>
                            <c:when test="${deptId == null && index == page}">
                                <a class="page-link" href="/user/professorList/${index}">${index}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link" href="/user/professorList/${index}">${index}</a>
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
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

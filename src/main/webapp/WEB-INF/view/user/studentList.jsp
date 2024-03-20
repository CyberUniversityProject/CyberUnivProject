<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>학생 명단 조회</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
 
<style>
  .table td, .table th {
    white-space: nowrap; /* 텍스트의 줄바꿈을 막음 */
    overflow: hidden; /* 넘치는 내용 숨김 */
    text-overflow: ellipsis; /* 넘치는 내용을 줄임표로 표시 */
  }
  
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
        <h2>학생 명단 조회</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
<div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
     <%@ include file="/WEB-INF/view/layout/sidebarStaff.jsp"%>

    <div class="col-md-10">
      
      <!-- 메인 div -->
	<div class="container mt-5">
    <h1>학생 명단 조회</h1>
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
                    <label for="deptId">학과 번호 </label>&nbsp;&nbsp;&nbsp;
                    <input type="text" name="deptId" id="deptId" class="form-control">&nbsp;&nbsp;&nbsp;
                    <label for="studentId">학번 </label>&nbsp;&nbsp;&nbsp;
                    <input type="text" name="studentId" id="studentId" class="form-control">
                     &nbsp;&nbsp;&nbsp;<button type="submit" class="btn--confirm">조회하기</button>
                <button type="button" onclick="location.href='/user/student/update'" class="btn--confirm ml-3">새학기로 적용하기</button>
                </div>
               
            </form>
        </div>
    </div>
    <c:choose>
        <c:when test="${!studentList.isEmpty()}">
            <h4>
                <span style="font-weight: 600;">학생 목록</span>
            </h4>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>학번</th>
                            <th>이름</th>
                            <th>생년월일</th>
                            <th>성별</th>
                            <th>주소</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>학과번호</th>
                            <th>학년</th>
                            <th>입학일</th>
                            <th>졸업일(졸업예정일)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${studentList}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.birthDate}</td>
                                <td>${student.gender}</td>
                                <td>${student.address}</td>
                                <td>${student.tel}</td>
                                <td>${student.email}</td>
                                <td>${student.deptId}</td>
                                <td>${student.grade}</td>
                                <td>${student.entranceDate}</td>
                                <td>${student.graduationDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
<ul class="pagination">
    <c:forEach var="index" begin="1" end="${listCount}">
        <li class="page-item ${index == page ? 'active' : ''}">
            <c:choose>
                <c:when test="${deptId != null && index != page}">
                    <a class="page-link" href="/user/studentList/${index}?deptId=${deptId}">${index}</a>
                </c:when>
                <c:when test="${deptId != null && index == page}">
                    <a class="page-link" href="/user/studentList/${index}?deptId=${deptId}">${index}</a>
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
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

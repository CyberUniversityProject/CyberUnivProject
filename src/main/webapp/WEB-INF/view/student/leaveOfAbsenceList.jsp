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
</style>


</head>


<body>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
 <!-- ======= 상단 제목부분 ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>휴학 내역 조회</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
<div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
     <%@ include file="/WEB-INF/view/layout/studentAsidebar.jsp"%>

    <div class="col-md-10">
      
      <!-- 메인 div -->
	<div class="container mt-5">
    <h1>휴학 내역 조회</h1>
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
                    <label for="studentId">학번 :${student.studentId }  </label>&nbsp;&nbsp;&nbsp;
                    <label for="studentId">이름 :${student.name }  </label>&nbsp;&nbsp;&nbsp;
                     &nbsp;&nbsp;&nbsp;
                </div>
               
            </form>
        </div>
    </div>
    <c:choose>
        <c:when test="${!leaveOfAbsenceList.isEmpty()}">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>휴학 년도</th>
                            <th>휴학 학기</th>
                            <th>복학 년도</th>
                            <th>복학 학기</th>
                            <th>휴학 사유</th>
                            <th>휴학 신청일</th>
                            <th>결과</th>
                            <th>상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="leaveOfAbsence" items="${leaveOfAbsenceList}">
                            <tr>
                                <td>${leaveOfAbsence.fromYear}</td>
                                <td>${leaveOfAbsence.fromSemester}</td>
                                <td>${leaveOfAbsence.toYear}</td>
                                <td>${leaveOfAbsence.toSemester}</td>
                                <td>${leaveOfAbsence.type}</td>
                                <td>${leaveOfAbsence.appDate}</td>
                                <td>${leaveOfAbsence.status}</td>
								<c:if test="${leaveOfAbsence.status eq '처리중'}">
								    <td>
								    	<a href="/student/deleteLeaveApp/${leaveOfAbsence.id}">
								        	<button class="btn btn-primary" id="cancelButton" type="button">휴학 신청 취소</button>
								    	</a>
								    </td>
								</c:if>
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
  <script>
	document.addEventListener("DOMContentLoaded", function(){
	  	let cancelButton = document.getElementById("cancelButton");
	  	cancelButton.addEventListener("click", function(e){
	  		if(!confirm("휴학 신청을 취소하시겠습니까?")){
	  			e.preventDefault();
	  			return;
	  		}
	
	  		
	  	});
	});
  </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>전체 강의 조회</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
 
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
        <h2>전체 강의 조회</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
<div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
     <%@ include file="/WEB-INF/view/layout/subjectSidebar.jsp"%>

    <div class="col-md-10">
      
      <!-- 메인 div -->
	<div class="container mt-5">
    <h1>전체 강의 조회</h1>
    <div class="row">
        <div class="col-md-12">
            <hr>
        </div>
    </div>
   
   <div class="container">
    <!-- 필터 및 검색 -->
    <div class="row">
        <div class="col-md-12">
            <form action="/subject/list/search" method="get" class="mb-4">
                <div class="row">
                    <div class="col-md-3">
                        <label for="subYear">연도</label>
                        <input type="number" class="form-control" value="<%=Define.CURRENT_YEAR%>" name="subYear" id="subYear" min="2005" max="2024">
                    </div>
                    <div class="col-md-3">
                        <label for="subSemester">학기</label>
                        <select class="form-control" name="semester" id="subSemester">
                            <option value="1">1학기</option>
                            <option value="2">2학기</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="deptId">개설학과</label>
                        <select class="form-control" name="deptId" id="deptId">
                            <option value="-1">전체</option>
                            <c:forEach var="dept" items="${deptList}">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="subName">강의명</label>
                        <input type="text" class="form-control" name="name" list="subName">
                        <datalist id="subName">
                            <c:forEach var="subName" items="${subNameList}">
                                <option value="${subName}">
                            </c:forEach>
                        </datalist>
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
                <c:when test="${subjectList.isEmpty() == false}">
                    <h4 class="mb-4">강의 목록 <span style="color: gray; font-size: 18px;">[총 ${subjectCount}건]</span></h4>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>연도/학기</th>
                                    <th>단과대학</th>
                                    <th>개설학과</th>
                                    <th>학수번호</th>
                                    <th>강의구분</th>
                                    <th style="width: 200px;">강의명</th>
                                    <th>담당교수</th>
                                    <th>학점</th>
                                    <th>수강인원</th>
                                    <th>정원</th>
                                    <th>강의계획서</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="subject" items="${subjectList}">
                                    <tr>
                                        <td>${subject.subYear}-${subject.semester}학기</td>
                                        <td>${subject.collName}</td>
                                        <td>${subject.deptName}</td>
                                        <td>${subject.id}</td>
                                        <td>${subject.type}</td>
                                        <td class="sub--list--name">${subject.name}</td>
                                        <td>${subject.professorName}</td>
                                        <td>${subject.grades}</td>
                                        <td>${subject.numOfStudent}</td>
                                        <td>${subject.capacity}</td>
                                        <td style="text-align: center;">
                                            <a href="/subject/syllabus/${subject.id}" class="btn--confirm" target="_blank">조회</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <c:if test="${pageCount != null}">
                        <ul class="pagination justify-content-center">
                            <c:forEach var="i" begin="1" end="${pageCount}" step="1">
                                <li class="page-item"><a class="page-link" href="/subject/list/${i}">${i}</a></li>
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
  </div>
</div>

  <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

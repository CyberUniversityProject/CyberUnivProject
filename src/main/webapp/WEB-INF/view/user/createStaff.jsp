<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>직원 등록</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>


<body>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
 <!-- ======= 상단 제목부분 ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>교직원 추가페이지</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
<div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
   <%@ include file="/WEB-INF/view/layout/sidebar.jsp"%>

    <div class="col-md-10">
      <h1 class="mt-5 mb-4">직원 등록</h1>
      <div class="row justify-content-center">
        <div class="col-md-7">
          <div class="card">
            <div class="card-body">
              <form action="/user/staff" method="post">
                <div class="form-group">
                  <label for="name">이름</label>
                  <input type="text" name="name" id="name" class="form-control">
                </div>
                <div class="form-group">
                  <label for="birthDate">생년월일</label>
                  <input type="date" name="birthDate" id="birthDate" class="form-control">
                </div>
                <div class="form-group">
                  <label>성별</label><br>
                  <div class="form-check form-check-inline">
                    <input type="radio" value="남성" name="gender" id="male" class="form-check-input" checked>
                    <label for="male" class="form-check-label">남성</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input type="radio" value="여성" name="gender" id="female" class="form-check-input">
                    <label for="female" class="form-check-label">여성</label>
                  </div>
                </div>
                <div class="form-group">
                  <label for="address">주소</label>
                  <input type="text" name="address" id="address" class="form-control">
                </div>
                <div class="form-group">
                  <label for="tel">전화번호</label>
                  <input type="text" name="tel" id="tel" class="form-control">
                </div>
                <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="text" name="email" id="email" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">등록하기</button>
              </form>
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
  <script>
  $(document).ready(function() {
    $('form').submit(function() {
      alert("등록되었습니다.");
    });
  });
</script>
  
 
  
</body>
</html>

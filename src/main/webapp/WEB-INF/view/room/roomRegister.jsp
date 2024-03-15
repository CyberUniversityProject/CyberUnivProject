<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의실 등록</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의실 등록 페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<!-- 사이드바 메뉴 -->
			<aside class="sidebar col-md-2 mt-5">
				<div class="card">
					<div class="card-header">메뉴</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수명단 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">학생 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">직원 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">등록금 고지서 발송</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">휴학 처리</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">수강 신청 기간 설정</a></li>
					</ul>
				</div>
			</aside>

			<div class="col-md-10">
				<h1 class="mt-5 mb-4"></h1>
				<!-- main 넣는 시작 부분 -->
				
			 <section id="pricing" class="pricing">
      <div class="container" data-aos="fade-up">

        <div class="row">

          <div class="col-lg-3 col-md-6">
            <!-- <div class="box" style="width: 306px; height: 421.38px;"> -->
            <div class="box">
              <h3 style="text-align: center;">학과 등록</h3>
              <ul>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <input type="text" class="form-control" name="collegeId" id="collegeId"
                    placeholder="단과대학 번호">
                </div>
                <br></br>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <input type="text" class="form-control" name="collegeId" id="collegeId"
                    placeholder="단과대학 번호">
                </div>
              </ul>
              <div class="btn-wrap">
                <a href="#" class="btn-buy">등록하기</a>
              </div>
            </div>
          </div>
      </div>
    </section><!-- End Pricing Section -->
				<!-- test -->
				
				
				<form action="/room/roomRegister" method="post">
				<div class="col-lg-8 mt-5 mt-lg-0">
					<div class="row">
						<div class="col-md-6 form-group mt-3 mt-md-0">
							<input type="text" class="form-control" name="id" id="id"
								placeholder="강의실 id">
						</div>
						<div class="col-md-6 form-group mt-3 mt-md-0">
							<input type="text" class="form-control" name="collegeId" id="collegeId"
								placeholder="단과대학 번호">
						</div>
					</div>

					<div class="text-center">
						<button type="submit">등록하기</button>
					</div>
				</div>
				</form>
				<!-- main 끝 -->
				
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>


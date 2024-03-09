<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!-- ======= 헤더시작 ======= -->
<header id="header" class="fixed-top">
  <div class="container d-flex align-items-center">
    <h1 class="logo me-auto"><a href="index.html">Cyber University</a></h1>
    <!-- Uncomment below if you prefer to use an image logo -->
    <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

    <!-- user role에 따라 다르게 보이게 할것 -->
    <nav id="navbar" class="navbar order-last order-lg-0">
      <!-- role == student 일 경우-->
      <ul>
        <li><a class="active" href="index.html">Home</a></li>

        <li class="dropdown">
          <a href="#"
            ><span>마이페이지</span> <i class="bi bi-chevron-down"></i
          ></a>
          <ul>
            <li><a href="#">내 정보 조회</a></li>
            <li><a href="#">비밀번호 변경</a></li>
            <li><a href="#">휴학 신청</a></li>
            <li><a href="#">휴학 내역 조회</a></li>
            <li><a href="#">등록금 내역 조회</a></li>
            <li><a href="#">등록금 납부 고지서</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#"><span>수업</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
            <li><a href="#">전체 강의 조회</a></li>
          </ul>
        </li>

        <li class="dropdown">
          <a href="#"
            ><span>수강신청</span> <i class="bi bi-chevron-down"></i
          ></a>
          <ul>
            <li><a href="#">강의시간표 조회</a></li>
            <li><a href="#">예비 수강 신청</a></li>
            <li><a href="#">수강 신청</a></li>
            <li><a href="#">수강 신청 내역 조회</a></li>
          </ul>
        </li>

        <li class="dropdown">
          <a href="#"><span>성적</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
            <li><a href="#">금학기 성적 조회</a></li>
            <li><a href="#">학기별 성적 조회</a></li>
            <li><a href="#">누계 성적</a></li>
          </ul>
        </li>

        <li class="dropdown">
          <a href="#"
            ><span>학사정보</span> <i class="bi bi-chevron-down"></i
          ></a>
          <ul>
            <li><a href="#">공지사항</a></li>
            <li><a href="#">학사일정</a></li>
          </ul>
        </li>
      </ul>

      <!-- role == staff 일 경우
            <ul>
              <li><a class="active" href="index.html">Home</a></li>

              <li class="dropdown">
                <a href="#"
                  ><span>마이페이지</span> <i class="bi bi-chevron-down"></i
                ></a>
                <ul>
                  <li><a href="#">내 정보 조회</a></li>
                  <li><a href="#">비밀번호 변경</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#"><span>학사관리</span> <i class="bi bi-chevron-down"></i></a>
                <ul>
                  <li><a href="#">학생 명단 조회</a></li>
                  <li><a href="#">교수 명단 조회</a></li>
                  <li><a href="#">학생등록</a></li>
                  <li><a href="#">교수등록</a></li>
                  <li><a href="#">직원등록</a></li>
                  <li><a href="#">등록금 고지서 발송</a></li>
                  <li><a href="#">휴학 처리</a></li>
                  <li><a href="#">수강 신청기간 설정</a></li>
                </ul>
              </li>

              <li class="dropdown">
                <a href="#"
                  ><span>등록</span> <i class="bi bi-chevron-down"></i
                ></a>
                <ul>
                  <li><a href="#">단과대학 등록</a></li>
                  <li><a href="#">학과 등록</a></li>
                  <li><a href="#">강의실 등록</a></li>
                  <li><a href="#">강의 등록</a></li>
                  <li><a href="#">단대별 등록금 설정</a></li>
                </ul>
              </li>

              <li class="dropdown">
                <a href="#"><span>학사정보</span> <i class="bi bi-chevron-down"></i></a>
                <ul>
                  <li><a href="#">공지사항</a></li>
                  <li><a href="#">학사일정</a></li>
                  <li><a href="#">학사일정 등록</a></li>
                </ul>
              </li>


            </ul> -->


             <!-- role == professor 일 경우
                        <ul>
                          <li><a class="active" href="index.html">Home</a></li>

                          <li class="dropdown">
                            <a href="#"
                              ><span>마이페이지</span> <i class="bi bi-chevron-down"></i
                            ></a>
                            <ul>
                              <li><a href="#">내 정보 조회</a></li>
                              <li><a href="#">비밀번호 변경</a></li>
                            </ul>
                          </li>
                          <li class="dropdown">
                            <a href="#"><span>수업</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                              <li><a href="#">전체 강의 조회</a></li>
                              <li><a href="#">내 강의 조회</a></li>
                              <li><a href="#">내 강의 평가</a></li>
                            </ul>
                          </li>



                          <li class="dropdown">
                            <a href="#"><span>학사정보</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                              <li><a href="#">공지사항</a></li>
                              <li><a href="#">학사일정</a></li>
                            </ul>
                          </li>


                        </ul> -->
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav>
    <!-- .navbar -->

    <a href="courses.html" class="get-started-btn">로그인</a>
  </div>
</header>
<!-- 헤더 끝 -->



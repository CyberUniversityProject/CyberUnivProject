<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cyber.university.dto.response.PrincipalDto"%>
<%@page import="com.cyber.university.utils.Define"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Cyber University :: 학사관리시스템</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="/img/favicon.png" rel="icon">
<link href="/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="/vendor/aos/aos.css" rel="stylesheet">
<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="/css/style.css" rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>

<!-- 구글 웹폰트 Noto Sans KR 추가 -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">

<style>
body {
	font-family: 'Noto Sans KR', sans-serif; /* 웹폰트 적용 */
}
</style>


<!-- =======================================================
  * Template Name: Mentor
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>


<!-- ======= 헤더시작 ======= -->
<header id="header" class="fixed-top">
	<div class="container d-flex align-items-center">
		<h1 class="logo me-auto">
			<a href="/"> <img src="/img/logo.png" /></a>
		</h1>
		<!-- Uncomment below if you prefer to use an image logo -->
		<!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

		<!-- user role에 따라 다르게 보이게 할것 -->
		<nav id="navbar" class="navbar order-last order-lg-0">
			<!-- role == student 일 경우-->
			<c:choose>
				<c:when test="${principal.userRole.equals(\"student\")}">
					<ul>
						<li><a class="active" href="/">Home</a></li>

						<li class="dropdown"><a href="#"><span>마이페이지</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/student/myInfo">내 정보 조회</a></li>
								<li><a href="/password">비밀번호 변경</a></li>
								<li><a href="/student/leaveOfAbsence">휴학 신청</a></li>
								<li><a href="/student/leaveOfAbsenceList">휴학 내역 조회</a></li>
								<li><a href="/student/tuition">등록금 내역 조회</a></li>
							</ul></li>
						<li class="dropdown"><a href="#"><span>수업</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/subject/list/1">전체 강의 조회</a></li>
							</ul></li>

						<li class="dropdown"><a href="#"><span>수강신청</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="#">강의시간표 조회</a></li>
								<li><a href="/sugang/pre/1">예비 수강 신청</a></li>
								<li><a href="/sugang/preAppList?type=1">수강 신청</a></li>
								<li><a href="/sugang/list">수강 신청 내역 조회</a></li>
							</ul></li>

						<li class="dropdown"><a href="#"><span>성적</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/student/gradeDetailList">성적 조회</a></li>
							</ul></li>

						<li class="dropdown"><a href="#"><span>학사정보</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/notice">공지사항</a></li>
								<li><a href="#">학사일정</a></li>
							</ul></li>
					</ul>
				</c:when>
				<c:when test="${principal.userRole.equals(\"staff\")}">

					<!-- role == staff 일 경우 -->
					<ul>
						<li><a class="active" href="/">Home</a></li>

						<li class="dropdown"><a href="#"><span>마이페이지</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/info/staff">내 정보 조회</a></li>
								<li><a href="/password">비밀번호 변경</a></li>
							</ul></li>
						<li class="dropdown"><a href="#"><span>학사관리</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/user/studentList">학생 명단 조회</a></li>
								<li><a href="/user/professorList">교수 명단 조회</a></li>
								<li><a href="/user/student">학생등록</a></li>
								<li><a href="/user/professor">교수등록</a></li>
								<li><a href="/user/staff">직원등록</a></li>
								<li><a href="/tuition/bill">등록금 고지서 발송</a></li>
								<li><a href="/applySubject/list">교수 강의생성 요청내역</a></li>
								<li><a href="/break/list/staff">휴학 처리</a></li>
								<li><a href="/sugang/period">수강 신청기간 설정</a></li>
							</ul></li>

						<li class="dropdown"><a href="#"><span>등록</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/college/collegeRegister">단과대학 등록</a></li>
								<li><a href="/department/departmentRegister">학과 등록</a></li>
								<li><a href="/room/roomRegister">강의실 등록</a></li>
								<li><a href="/staff/subject?crud=insert">강의 등록</a></li>
								<li><a href="/staff/tuition?crud=insert">단대별 등록금 설정</a></li>
							</ul></li>

						<li class="dropdown"><a href="#"><span>학사정보</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/notice">공지사항</a></li>
								<li><a href="#">학사일정</a></li>
								<li><a href="#">학사일정 등록</a></li>
							</ul></li>


					</ul>
				</c:when>

				<c:when test="${principal.userRole.equals(\"professor\")}">
					<!-- role == professor 일 경우 -->
					<ul>
						<li><a class="active" href="/">Home</a></li>




						<li class="dropdown"><a href="#"><span>마이페이지</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/professor/info">내 정보 조회</a></li>
								<li><a href="/password">비밀번호 변경</a></li>
							</ul></li>
						<li class="dropdown"><a href="#"><span>수업</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/subject/list/1">전체 강의 조회</a></li>
								<li><a href="/professor/mysub">내 강의 조회</a></li>
								<li><a href="#">내 강의 평가</a></li>
								<li><a href="/professor/apply">강의 등록</a></li>
							</ul></li>




						<li class="dropdown"><a href="#"><span>학사정보</span> <i
								class="bi bi-chevron-down"></i></a>
							<ul>
								<li><a href="/notice">공지사항</a></li>
								<li><a href="#">학사일정</a></li>
							</ul></li>


					</ul>
				</c:when>

				<c:otherwise>
					<ul>
						<li><a href="/campusMap">캠퍼스 맵</a></li>
					</ul>
				</c:otherwise>
			</c:choose>

			<i class="bi bi-list mobile-nav-toggle"></i>
		</nav>
		<!-- .navbar -->

		<div class="user-info">
			<c:choose>
				<c:when test="${principal != null}">
					<div class="user-details">
						<c:choose>
							<c:when test="${principal.userRole.equals(\"student\")}">
								<a href="/student/myInfo" class="get-started-btn"
									data-bs-placement="bottom" data-bs-toggle="popover"
									data-bs-content="${popoverContent}">${principal.name}(${principal.id})
									님</a>
							</c:when>
							<c:when test="${principal.userRole.equals(\"staff\")}">
								<a href="/info/staff" class="get-started-btn"
									data-bs-placement="bottom" data-bs-toggle="popover"
									data-bs-content="${popoverContent}">${principal.name}(${principal.id})
									님</a>
							</c:when>
							<c:when test="${principal.userRole.equals(\"professor\")}">
								<a href="/professor/info" class="get-started-btn"
									data-bs-placement="bottom" data-bs-toggle="popover"
									data-bs-content="${popoverContent}">${principal.name}(${principal.id})
									님</a>
							</c:when>
						</c:choose>
						<a href="/logout" class="get-started-btn">로그아웃</a>
					</div>

				</c:when>
				<c:otherwise>
					<a href="/login" class="get-started-btn">로그인</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>
<!-- 헤더 끝 -->
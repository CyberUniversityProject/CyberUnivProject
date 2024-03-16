<%@page
	import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<script>
<!-- 비밀번호를 확인 하여 id와 비밀번호가 같으면 초기 생성된 계정임, 그래서 비밀번호 변경 팝업창을 띄우게 함 -->
	
<%PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
if (principal != null && new BCryptPasswordEncoder().matches(principal.getId().toString(), principal.getPassword())) {%>
	function pop() {
		window
				.open(
						"/guide",
						"비밀번호 변경 안내",
						"width=600,height=400,history=no,resizable=no,status=no,scrollbars=yes,menubar=no");
	}
<%}%>
	
</script>
<body onLoad="javascript:pop()">
	<!-- ======= Hero Section ======= -->
	<section id="hero"
		class="d-flex justify-content-center align-items-center">
		<div class="container position-relative" data-aos="zoom-in"
			data-aos-delay="100">
			<h1>
				오늘을 배우고,<br>내일을 선도합니다.
			</h1>
			<h2>Cyber University 학사관리시스템에 오신것을 환영합니다.</h2>
			<c:choose>
				<c:when test="${principal == null}">
					<a href="/login" class="btn-get-started">로그인하기</a>
				</c:when>
			</c:choose>
		</div>
	</section>
	<!-- End Hero -->

	<main id="main">
		<c:choose>
			<c:when test="${principal != null}">
				<!-- ======= User Information Section ======= -->
				<div class="container">
					<section id="user-info" class="user-info">
						<div class="container" data-aos="fade-up">
							<div class="section-title">
								<h2>User Information</h2>
								<p>
									<i class="bx bx-user"></i> ${userInfo.name}(${userInfo.id})님,
									환영합니다.
								</p>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<div class="info-box">
										<h3>
											<i class="bx bx-map"></i> 소속
										</h3>
										<p>
											<c:choose>
												<c:when test="${principal.userRole eq 'staff'}">교직원</c:when>
												<c:when test="${principal.userRole eq 'student'}">학생</c:when>
												<c:when test="${principal.userRole eq 'professor'}">교수</c:when>
											</c:choose>
										</p>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="info-box">
										<h3>
											<i class="bx bx-envelope"></i> 이메일
										</h3>
										<p>${userInfo.email}</p>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<div class="info-box">
										<h3>
											<i class="bx bx-bell"></i> 알림
										</h3>
										<p>1개의 업무사항이 있습니다.</p>
									</div>
								</div>
								<!-- My Page Button -->
								<c:choose>
									<c:when test="${principal.userRole eq 'staff'}">
										<div class="col-lg-3">
											<div class="info-box">
												<a href="/info/staff" class="btn--confirm ">마이페이지</a>
											</div>
										</div>
									</c:when>
									<c:when test="${principal.userRole eq 'student'}">
										<div class="col-lg-3">
											<div class="info-box">
												<a href="/student/myInfo" class="btn--confirm ">마이페이지</a>
											</div>
										</div>
									</c:when>
									<c:when test="${principal.userRole eq 'professor'}">
										<div class="col-lg-3">
											<div class="info-box">
												<a href="/professor/info" class="btn--confirm ">마이페이지</a>
											</div>
										</div>
									</c:when>

								</c:choose>
								<!-- End My Page Button -->
							</div>
						</div>
					</section>
				</div>
				<!-- End User Information Section -->
			</c:when>
		</c:choose>




		<!-- ======= Counts Section ======= -->
		<!-- <section id="counts" class="counts section-bg">
      <div class="container">

        <div class="row counters">

          <div class="col-lg-3 col-6 text-center">
            <span data-purecounter-start="0" data-purecounter-end="1232" data-purecounter-duration="1" class="purecounter"></span>
            <p>Students</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-purecounter-start="0" data-purecounter-end="64" data-purecounter-duration="1" class="purecounter"></span>
            <p>Courses</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-purecounter-start="0" data-purecounter-end="42" data-purecounter-duration="1" class="purecounter"></span>
            <p>Events</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-purecounter-start="0" data-purecounter-end="15" data-purecounter-duration="1" class="purecounter"></span>
            <p>Trainers</p>
          </div>

        </div>

      </div>
    </section> -->
		<!-- End Counts Section -->

		<!-- ======= Why Us Section ======= -->
		<c:choose>
        										<c:when test="${principal.userRole eq 'student'}">
		<section id="why-us" class="why-us">
			<div class="container" data-aos="fade-up">

				<div class="row">
					<div class="col-lg-5 d-flex align-items-stretch">
						<div class="content">
							<h3>One-Stop 서비스</h3>
							<p>해당 서비스를 클릭하시면 해당 서비스로 이동됩니다.</p>

						</div>
					</div>
					<div class="col-lg-7 d-flex align-items-stretch" data-aos="zoom-in"
						data-aos-delay="100">
						<div class="icon-boxes d-flex flex-column justify-content-center">
							<div class="row">
								<div class="col-xl-4 d-flex align-items-stretch">

											<div class="icon-box mt-4 mt-xl-0">
												<i class="bx bx-book"></i>

												<h4>수강신청</h4>
												<div class="text-center">
													<a href="about.html" class="more-btn">바로가기 <i
														class="bx bx-chevron-right"></i></a>
												</div>
											</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-bar-chart-alt"></i>

										<h4>성적조회</h4>
										<div class="text-center">
											<a href="about.html" class="more-btn">바로가기 <i
												class="bx bx-chevron-right"></i></a>
										</div>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-info-circle"></i>

										<h4>학사정보</h4>
										<div class="text-center">
											<a href="about.html" class="more-btn">바로가기 <i
												class="bx bx-chevron-right"></i></a>
										</div>
									</div>


								</div>
								</c:when>
								<c:when test="${principal.userRole eq 'professor'}">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-book-reader"></i>

										<h4>내 강의조회</h4>
										<div class="text-center">
											<a href="professor/mysub" class="more-btn">바로가기 <i
												class="bx bx-chevron-right"></i></a>
										</div>
									</div>
							</div>
							<div class="col-xl-4 d-flex align-items-stretch">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="bx bx-star"></i>

									<h4>내 강의평가</h4>
									<div class="text-center">
										<a href="about.html" class="more-btn">바로가기 <i
											class="bx bx-chevron-right"></i></a>
									</div>
								</div>
							</div>
							<div class="col-xl-4 d-flex align-items-stretch">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="bx bx-edit"></i>

									<h4>강의 등록</h4>
									<div class="text-center">
										<a href="professor/apply" class="more-btn">바로가기 <i
											class="bx bx-chevron-right"></i></a>
									</div>
								</div>
							</div>
							</c:when>
							<c:when test="${principal.userRole eq 'staff'}">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="bx bx-user-plus"></i>

									<h4>학생등록</h4>
									<div class="text-center">
										<a href="/user/student" class="more-btn">바로가기 <i
											class="bx bx-chevron-right"></i></a>
									</div>
								</div>
						</div>
						<div class="col-xl-4 d-flex align-items-stretch">
							<div class="icon-box mt-4 mt-xl-0">
								<i class="bx bx-user-plus"></i>

								<h4>교수등록</h4>
								<div class="text-center">
									<a href="/user/professor" class="more-btn">바로가기 <i
										class="bx bx-chevron-right"></i></a>
								</div>
							</div>
						</div>
						<div class="col-xl-4 d-flex align-items-stretch">
							<div class="icon-box mt-4 mt-xl-0">
								<i class="bx bx-user-plus"></i>

								<h4>직원등록</h4>
								<div class="text-center">
									<a href="/user/staff" class="more-btn">바로가기 <i
										class="bx bx-chevron-right"></i></a>
								</div>
							</div>
						</div>

					</div>
					<!-- End .content-->
				</div>
			</div>

			</div>
		</section>
			</c:when>
        						</c:choose>
		<!-- End Why Us Section -->

		<!-- ======= Features Section ======= -->
		<section id="features" class="features">
			<div class="container" data-aos="fade-up">

				<div class="row" data-aos="zoom-in" data-aos-delay="100">
					<c:choose>
						<c:when test="${principal.userRole eq 'student'}">
							<div class="col-lg-3 col-md-4">
								<div class="icon-box">
									<i class="ri-store-line" style="color: #ffbb2c;"></i>

									<h3>
										<a href="/student/leaveOfAbsence">휴학신청</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-bar-chart-box-line" style="color: #5578ff;"></i>

									<h3>
										<a href="/student/leaveOfAbsenceList">휴학 내역조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>

									<h3>
										<a href="/student/tuition">등록금 내역조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-lg-0">
								<div class="icon-box">
									<i class="ri-paint-brush-line" style="color: #e361ff;"></i>

									<h3>
										<a href="">등록금 납부고지서 출력</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-database-2-line" style="color: #47aeff;"></i>
									<h3>
										<a href="/subject/list/1">강의 조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-gradienter-line" style="color: #ffa76e;"></i>
									<h3>
										<a href="/sugang/pre/1">예비수강신청</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #11dbcf;"></i>
									<h3>
										<a href="/sugang/preAppList?type=1">수강신청</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-price-tag-2-line" style="color: #4233ff;"></i>
									<h3>
										<a href="">학기별 성적조회</a>
									</h3>
								</div>
							</div>
						</c:when>
						<c:when test="${principal.userRole eq 'professor'}">
							<div class="col-lg-3 col-md-4">
								<div class="icon-box">
									<i class="ri-store-line" style="color: #ffbb2c;"></i>
									<h3>
										<a href="/subject/list/1">전체 강의조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-bar-chart-box-line" style="color: #5578ff;"></i>
									<h3>
										<a href="/professor/apply">학사일정</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/notice">공지사항</a>
									</h3>
								</div>
							</div>

						</c:when>
						<c:when test="${principal.userRole eq 'staff'}">
							<div class="col-lg-3 col-md-4">
								<div class="icon-box">
									<i class="ri-store-line" style="color: #ffbb2c;"></i>
									<h3>
										<a href="/user/studentList">학생명단조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-bar-chart-box-line" style="color: #5578ff;"></i>
									<h3>
										<a href="/user/professorList">교수명단조회</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/tuition/bill">등록금 고지서발송</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/applySubject/list">교수 강의신청 목록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #4233ff;"></i>
									<h3>
										<a href="/break/list/staff">휴학처리</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/sugang/period">수강 신청기간 설정</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/college/collegeRegister">단과대 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
									<h3>
										<a href="/college/collegeRegister">단과대 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #e80368;"></i>

									<h3>
										<a href="/department/departmentRegister">학과 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #e80368;"></i>

									<h3>
										<a href="/room/roomRegister">강의실 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #e80368;"></i>

									<h3>
										<a href="/staff/subject?crud=insert">강의 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #e80368;"></i>

									<h3>
										<a href="/staff/tuition?crud=insert">단대별 등록금 등록</a>
									</h3>
								</div>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>

			</div>
		</section>
		<!-- End Features Section -->

		<!-- ======= 공지사항 ======= -->
		<section id="popular-courses" class="courses">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>Notice</h2>
					<p>공지사항</p>
				</div>



			</div>
		</section>
		<!-- End 공지사항 -->


		<!-- ======= 학사일정 ======= -->
		<section id="popular-courses" class="courses">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>Academic Calendar</h2>
					<p>학사일정</p>
				</div>



			</div>
		</section>
		<!-- End 학사일정 -->


		<!-- ======= 오늘의 학식 ======= -->
		<section id="popular-courses" class="courses">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>Menu</h2>
					<p>오늘의 학식</p>
				</div>



			</div>
		</section>
		<!-- End 학사일정 -->




	</main>
	<!-- End #main -->

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
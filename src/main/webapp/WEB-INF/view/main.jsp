<%@page
	import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- jQuery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

<style>
.schedule-table-wrapper {
	overflow-x: auto;
	margin-top: 10px;
}

#scheduleTable {
	width: 100%;
	border-collapse: collapse;
}

#scheduleTable th, #scheduleTable td {
	padding: 10px;
	text-align: center;
	border: 1px solid #ddd;
}

#scheduleTable th {
	background-color: #f2f2f2;
	color: #333;
}

#scheduleTable tbody tr:nth-child(even) {
	background-color: #f2f2f2;
}

#scheduleTable tbody tr:hover {
	background-color: #ddd;
}
</style>
<body onLoad="javascript:pop()">
	<!-- ======= Hero Section ======= -->
	<section id="hero"
		class="d-flex justify-content-center align-items-center">
		<div class="container position-relative" data-aos="zoom-in"
			data-aos-delay="100">
			<h1>
				오늘을 배우고,<br>내일을 선도합니다.
			</h1>
			<br>

			<h2>그린 사이버대학교에 오신것을 환영합니다.</h2>
			<!-- 로그인 버튼 코드 -->
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

			<c:when test="${periodNumber == 0}">
				<a href="/sugang/pre/1"><img src="/img/preapp.png" alt="예비수강신청"
					style="width: 70%; height: 70%; display: block; margin: auto;"
					class="mt-5"></a>
			</c:when>
			<c:when test="${periodNumber == 1}">
				<a href="/sugang/preAppList?type=1"><img src="/img/app.png"
					alt="수강신청"
					style="width: 70%; height: 70%; display: block; margin: auto;"
					class="mt-5"></a>

			</c:when>
			<c:otherwise>
				<img src="/img/apply.jpg" alt="신입생모집"
					style="width: 70%; height: 70%; display: block; margin: auto;"
					class="mt-5">
			</c:otherwise>

		</c:choose>
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

									<!-- 마이페이지 버튼을 님 환영합니다 글자 옆에 배치 -->
									<div class="text-end">
                                                        <c:choose>
                                                            <c:when test="${principal.userRole eq 'staff'}">
                                                                <a href="/info/staff" class="btn--confirm ">마이페이지</a>
                                                            </c:when>
                                                            <c:when test="${principal.userRole eq 'student'}">
                                                                <a href="/student/myInfo" class="btn--confirm ">마이페이지</a>
                                                            </c:when>
                                                            <c:when test="${principal.userRole eq 'professor'}">
                                                                <a href="/professor/info" class="btn--confirm ">마이페이지</a>
                                                            </c:when>
                                                        </c:choose>
</div>
								</p>
							</div>

							<div class="row">
							  <!-- 프로필 이미지 -->
							  <c:choose>
							  <c:when test="${principal.userRole eq 'student'}">
                                            <div class="col-lg-3">
                                                <div class="info-box">
                                                    <img src="${userInfo.setupProfilImage()}" width="200" height="200" alt="프로필 이미지">
                                                </div>
                                            </div>
                                            </c:when>
                                            </c:choose>

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
								<c:choose>
									<c:when test="${principal.userRole eq 'staff'}">
										<div class="col-lg-3">
											<div class="info-box">
												<h3>
													<i class="bx bx-bell"></i> 알림
												</h3>
												<c:choose>
													<c:when test="${breakSize > 0}">
														<div class="main--page--info">
															<p class="info-link">
																<a href="/break/list/staff">💡 처리되지 않은 휴학 신청이 존재합니다.</a>
															</p>

														</div>
													</c:when>
													<c:when test="${applySubjectSize > 0}">
														<div class="main--page--info">
															<p class="info-link">
																<a href="/applySubject/list">💡 처리되지 않은 강의 신청이
																	존재합니다.</a>
															</p>
														</div>
													</c:when>
													<c:when test="${breakSize == 0}">
														<div class="main--page--info">
															<p class="info-message">처리해야 할 업무가 없습니다.</p>
														</div>
													</c:when>
													<c:when test="${applySubjectSize == 0}">
														<div class="main--page--info">
															<p class="info-message">처리해야 할 업무가 없습니다.</p>
														</div>
													</c:when>

												</c:choose>

											</div>
										</div>
									</c:when>
								</c:choose>

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
							<div class="col-lg-7 d-flex align-items-stretch"
								data-aos="zoom-in" data-aos-delay="100">
								<div
									class="icon-boxes d-flex flex-column justify-content-center">
									<div class="row">
										<div class="col-xl-4 d-flex align-items-stretch">

											<div class="icon-box mt-4 mt-xl-0">
												<i class="bx bx-book"></i>

												<h4>수강신청</h4>
												<div class="text-center">
													<a href="/sugang/preAppList?type=1" class="more-btn">바로가기
														<i class="bx bx-chevron-right"></i>
													</a>
												</div>
											</div>
										</div>
										<div class="col-xl-4 d-flex align-items-stretch">
											<div class="icon-box mt-4 mt-xl-0">
												<i class="bx bx-bar-chart-alt"></i>

												<h4>성적조회</h4>
												<div class="text-center">
													<a href="/student/gradeDetailList" class="more-btn">바로가기
														<i class="bx bx-chevron-right"></i>
													</a>
												</div>
											</div>
										</div>
										<div class="col-xl-4 d-flex align-items-stretch">
											<div class="icon-box mt-4 mt-xl-0">
												<i class="bx bx-info-circle"></i>

												<h4>학사정보</h4>
												<div class="text-center">
													<a href="/notice" class="more-btn">바로가기 <i
														class="bx bx-chevron-right"></i></a>
												</div>
											</div>


										</div>
			</c:when>
			<c:when test="${principal.userRole eq 'professor'}">
				<section id="why-us" class="why-us">
					<div class="container" data-aos="fade-up">
						<div class="row">
							<div class="col-lg-5 d-flex align-items-stretch">
								<div class="content">
									<h3>One-Stop 서비스</h3>
									<p>해당 서비스를 클릭하시면 해당 서비스로 이동됩니다.</p>

								</div>
							</div>
							<div class="col-lg-7 d-flex align-items-stretch"
								data-aos="zoom-in" data-aos-delay="100">
								<div
									class="icon-boxes d-flex flex-column justify-content-center">
									<div class="row">
										<div class="col-xl-4 d-flex align-items-stretch">
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
													<a href="/professor/mysub" class="more-btn">바로가기 <i
														class="bx bx-chevron-right"></i></a>
												</div>
											</div>
										</div>
										<div class="col-xl-4 d-flex align-items-stretch">
											<div class="icon-box mt-4 mt-xl-0">
												<i class="bx bx-edit"></i>

												<h4>강의 등록</h4>
												<div class="text-center">
													<a href="/professor/apply" class="more-btn">바로가기 <i
														class="bx bx-chevron-right"></i></a>
												</div>
											</div>
										</div>
			</c:when>
			<c:when test="${principal.userRole eq 'staff'}">
				<section id="why-us" class="why-us">
					<div class="container" data-aos="fade-up">
						<div class="row">
							<div class="col-lg-5 d-flex align-items-stretch">
								<div class="content">
									<h3>One-Stop 서비스</h3>
									<p>해당 서비스를 클릭하시면 해당 서비스로 이동됩니다.</p>

								</div>
							</div>
							<div class="col-lg-7 d-flex align-items-stretch"
								data-aos="zoom-in" data-aos-delay="100">
								<div
									class="icon-boxes d-flex flex-column justify-content-center">
									<div class="row">
										<div class="col-xl-4 d-flex align-items-stretch">
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
			</c:when>
		</c:choose>
		</div>
		</section>

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
										<a href="/student/tuition">등록금 납부고지서 출력</a>
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
										<a href="/student/gradeDetailList">학기별 성적조회</a>
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
										<a href="/schedule">학사일정</a>
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
									<i class="ri-calendar-todo-line" style="color: #ffbb2c;"></i>
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
									<i class="ri-calendar-todo-line" style="color: #ffbb2c;"></i>
									<h3>
										<a href="/college/collegeRegister">단과대 등록</a>
									</h3>
								</div>
							</div>

							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #5fcf80;"></i>

									<h3>
										<a href="/department/departmentRegister">학과 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #585858;"></i>

									<h3>
										<a href="/room/roomRegister">강의실 등록</a>
									</h3>
								</div>
							</div>
							<div class="col-lg-3 col-md-4 mt-4">
								<div class="icon-box">
									<i class="ri-file-list-3-line" style="color: #FF8000;"></i>

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
					<!-- 버튼을 화면 오른쪽 끝에 위치시키기 -->
					<div style="position: relative;">
						<a href="/notice" class="btn "
							style="position: absolute; right: 0; background-color: #5fcf80; color: white;">공지사항
							바로가기</a>
					</div>
				</div>
				<!-- 여기에 공지사항 리스트 출력-->
				<div id="noticeList" class="table-responsive">
					<table class="table ">
						<thead>
							<tr>
								<th>번호</th>
								<th>분류</th>
								<th style="width: 800px;">제목</th>
								<th>게시일</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
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
				<div class="schedule-table-wrapper">
					<table id="scheduleTable">
						<thead>
							<tr>
								<th>시작일</th>
								<th>종료일</th>
								<th>내용</th>
							</tr>
						</thead>
						<tbody>
							<!-- Data will be inserted here dynamically -->
						</tbody>
					</table>
				</div>

			</div>
		</section>
		<!-- End 학사일정 -->
		<!-- End 학사일정 -->


		<!-- ======= 오늘의 학식 ======= -->
		<section id="popular-courses" class="courses">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>Menu</h2>
					<p>오늘의 학식</p>
				</div>
				<div class="row" id="dietList">

				</div>


			</div>
		</section>
		<!-- End 학식 -->




	</main>
	<!-- End #main -->

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>


	<script>
    
		$(document).ready(
				function() {
					// REST API를 호출하여 공지사항 목록을 가져옴
					$.ajax({
						url : "/api/notice/list",
						type : "GET",
						success : function(data) {
							// 가져온 공지사항 목록을 화면에 출력
							let noticeListHtml = "";
							$.each(data, function(index, notice) {
								noticeListHtml += "<tr><td>" + notice.id
										+ "</td><td>" + notice.category
										+ "</td><td><a href='/notice/read?id="
										+ notice.id + "'>" + notice.title
										+ "</a></td><td>"
										+ notice.createdTimeAsString
										+ "</td></tr>";
							});

							$("#noticeList tbody").html(noticeListHtml);
						},
						error : function(xhr, textStatus, errorThrown) {
							console.log("Error:", errorThrown);
						}
					});
				});
		
/* 식단 api */
/* 날짜 */     
 let today = new Date();

 let year = today.getFullYear(); // 년도
 let month = (today.getMonth() + 1).toString().padStart(2, '0');  // 월 (두 자리로 표시)
 let date = today.getDate();  // 날짜
 let day = today.getDay();  // 요일
 
 let dayOfWeek = today.getDay();
 let days = ["일", "월", "화", "수", "목", "금", "토"];
/* 문자열 가공 함수 */
function processMenu(menu) {
    // 괄호와 괄호 안의 내용을 정규식으로 제거합니다.
    menu = menu.replace(/\([^)]+\)/g, '');
    // 숫자와 .을 정규식으로 제거합니다.
    menu = menu.replace(/[0-9.]+/g, '');
    // 자음만 포함된 문자열을 제거합니다.
    menu = menu.replace(/[ㄱ-ㅎ]/g, '');
    // 양 끝의 공백을 제거합니다.
    menu = menu.trim();
    return menu;
}

/* 식단 API 호출 및 가공된 메뉴를 표시하는 함수 */
function displayMenu(menuArray) {
    // dietList 요소를 선택합니다.
    let dietListElement = document.getElementById('dietList');
    
    // dietList 내부의 기존 내용을 지웁니다.
    dietListElement.innerHTML = '';

    // 각 메뉴에 대해 카드를 생성하여 dietList에 추가합니다.
    for (let i = 0; i < menuArray.length; i++) {
    	let today = new Date();
    	let dayOfWeek = (today.getDay() + i) %7;
    	let menuToday = days[dayOfWeek]+'요일';
    	
        // 각 메뉴를 가공합니다.
        let processedMenu = processMenu(menuArray[i]);

        // 카드 HTML 생성
        let cardHTML = `
            <div class="col">
                <div class="card">
                    <div class="card-header">`+menuToday +`</div>
                    <div class="card-body" style="height : 250px;">
                        <blockquote class="blockquote mb-0">
                            <p>` +  processedMenu + `</p>
                        </blockquote>
                    </div>
                </div>
            </div>
        `;
        
        // 카드를 dietList에 추가합니다.
        dietListElement.innerHTML += cardHTML;
    }
}

/* API 콜백 함수 */
function ApiCallBack(json) {
    let menuArray = [];

    if (json.mealServiceDietInfo != null) {
        for (var i = 0; i < 5; i++) {
            $.each(json.mealServiceDietInfo[1], function(key, state) {
                let menu = json.mealServiceDietInfo[1].row[i].DDISH_NM;
                menuArray.push(menu);
            });
        }
        // 학식 정보를 HTML에 표시
        displayMenu(menuArray);
        console.log(menuArray);
    } else {
        console.log("json.schoolInfo == null")
    }
}

/* API 호출 함수 */
$(document).ready(function() {

    
    let todayDate = year + month + date;

    console.log("i'm here!!!!"+today);
    console.log("i'm here!!!!"+year);
    console.log("i'm here!!!!"+month);
    console.log("i'm here!!!!"+date);
    console.log(todayDate);
    
    let url = 'https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=bab543c0c3c6493dad08d3fba3cf73bd&Type=json&pIndex=1&pSize=7&ATPT_OFCDC_SC_CODE=C10&SD_SCHUL_CODE=7150089&MLSV_FROM_YMD=' + todayDate;
    let param = '';
    ajaxCallApi(url, param, ApiCallBack);
});

/* AJAX 호출 함수 */
function ajaxCallApi(url, param, callback) {
    console.log("ajaxCallApi in");

    $.ajax({
        url: url,
        async: true,
        type: "POST",
        data: param,
        dataType: 'json',
        success: callback,
        error: function(requqest, textStatus) {
            var format = new OpenLayers.Format.WFSDescribeFeatureType();
            var doc = request.responseXML;
            var describeFeatureType = format.read(doc);
            alert("describeFeatureType = " + describeFeatureType + " textStatus = " + textStatus);
        }
    });

}

	</script>
	
	
	<script>
        fetch('/api/schedule/selectAll')
            .then(response => response.json())
            .then(data => {
                const scheduleTable = document.getElementById('scheduleTable');
                data.forEach(schedule => {
                    const row = scheduleTable.insertRow();
                    const startDateCell = row.insertCell(0);
                    const endDateCell = row.insertCell(1);
                    const informationCell = row.insertCell(2);
                    
                    startDateCell.textContent = schedule.startDay;
                    endDateCell.textContent = schedule.endDay;
                    informationCell.textContent = schedule.information;
                });
            })
            .catch(error => console.error('Error fetching schedule:', error));
    </script>
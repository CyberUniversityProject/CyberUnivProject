<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학생 명단 조회</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

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
			<h2>휴학원</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/studentAsidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<h1>휴학원</h1>
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					<!-- 필터 및 검색 -->
					<div class="col-md-7 col-lg-8 mx-auto">
						<div class="card">
							<div class="card-body">
								<div class="">
									<form class="needs-validation" novalidate="">
										<div class="row g-3">


											<div class="col-sm-6">
												<label for="firstName" class="form-label">학과(부)</label> <input
													type="text" class="form-control" id="firstName"
													placeholder="" value="" required="">
												<div class="invalid-feedback">Valid first name is
													required.</div>
											</div>

											<div class="col-sm-6">
												<label for="lastName" class="form-label">학년</label> <input
													type="text" class="form-control" id="lastName"
													placeholder="" value="" required="">
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>



											<div class="col-sm-4">
												<label for="firstName" class="form-label">학번</label> <input
													type="text" class="form-control" id="firstName"
													placeholder="" value="" required="">
												<div class="invalid-feedback">Valid first name is
													required.</div>
											</div>

											<div class="col-sm-4">
												<label for="lastName" class="form-label">성명</label> <input
													type="text" class="form-control" id="lastName"
													placeholder="" value="" required="">
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>

											<div class="col-sm-4">
												<label for="lastName" class="form-label">생년월일</label> <input
													type="text" class="form-control" id="lastName"
													placeholder="" value="" required="">
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>


											<div class="my-3 row">
												<label for="lastName" class="form-label">휴학구분</label>

												<div class="col-sm-6">
													<div class="form-check">
														<input id="credit" name="paymentMethod" type="radio"
															class="form-check-input" checked="" required="">
														<label class="form-check-label" for="credit">일반휴학(개인사정,
															기타)</label>
													</div>
													<div class="form-check">
														<input id="debit" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="debit">병역휴학</label>
													</div>
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">질병휴학</label>
													</div>
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">임신휴학</label>
													</div>
												</div>

												<div class="col-sm-6">
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">출산육아휴학</label>
													</div>
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">창업휴학</label>
													</div>
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">외국어유학과 어학언수</label>
													</div>
													<div class="form-check">
														<input id="paypal" name="paymentMethod" type="radio"
															class="form-check-input" required=""> <label
															class="form-check-label" for="paypal">학기조정휴학</label>
													</div>
												</div>

											</div>


											<div class="my-3 row">
												<label for="lastName" class="form-label">휴학기간</label>

												<div class="col-md-6">
													<label for="state" class="form-label">휴학 시작일</label> <input
														id="paypal" name="paymentMethod" type="month"
														class="form-control" required=""> <label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> <select
														class="form-select" id="state" required="">
														<option value="">1학기</option>
														<option>2학기</option>
													</select>
													<div class="invalid-feedback">Please provide a valid
														state.</div>
												</div>




												<div class="col-md-6">
													<label for="state" class="form-label">휴학 종료일</label> <input
														id="paypal" name="paymentMethod" type="month"
														class="form-control" required=""> <label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> <select
														class="form-select" id="state" required="">
														<option value="">1학기</option>
														<option>2학기</option>
													</select>
													<div class="invalid-feedback">Please provide a valid
														state.</div>
												</div>

												<div class="col-md-12">
													<label for="state" class="form-label">총</label> <label
														for="state" class="form-label">3(변경값)</label> <label
														for="state" class="form-label">학기</label> </select>
													<div class="invalid-feedback">Please provide a valid
														state.</div>
												</div>
											</div>

										</div>

										<div class="col-12">
											<label for="email" class="form-label">이메일 </label> <input
												type="email" class="form-control" id="email"
												placeholder="you@example.com">
											<div class="invalid-feedback">Please enter a valid
												email address for shipping updates.</div>
										</div>

										<div class="col-12">
											<label for="address" class="form-label">주소</label> <input
												type="text" class="form-control" id="address"
												placeholder="1234 Main St" required="">
											<div class="invalid-feedback">Please enter your
												shipping address.</div>
										</div>

										<div class="col-12">
											<label for="address2" class="form-label">휴대폰</label> <input
												type="text" class="form-control" id="address2"
												placeholder="Apartment or suite">
										</div>


										<hr class="my-4">
										<div class="form-check">
											<input type="checkbox" class="form-check-input"
												id="same-address"> <a href="#"><label
												class="form-check-label" for="same-address">개인정보 수집
													및 활용 동의서 </label></a>
										</div>

										<div class="form-check">
											<input type="checkbox" class="form-check-input"
												id="save-info"> <a href="#"><label
												class="form-check-label" for="save-info">민감정보 수집·이용
													동의(해당자만 동의여부 체크하세요)</label></a>
										</div>


										<hr class="my-4">

										<div class="form-check">
											<label
												class="form-check-label" for="same-address">위와 같이 휴학하고자 하오니 허가하여 주시기 바랍니다. </label>
										</div>

										<div class="form-check">
													<label for="state" class="form-label">휴학 시작일</label> <input
														id="paypal" name="paymentMethod" type="month"
														class="form-control" required=""> <label
														class="form-label" for="paypal"></label>
										</div>

										<hr class="my-4">

										<button class="w-100 btn btn-primary btn-lg" type="submit">휴학 신청</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

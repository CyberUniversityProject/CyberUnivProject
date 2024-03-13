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
													type="text" class="form-control" id="department"
													placeholder="" value="${studentInfo.department }" disabled>
												<div class="invalid-feedback">Valid first name is
													required.</div>
											</div>

											<div class="col-sm-6">
												<label for="lastName" class="form-label">학년</label> <input
													type="text" class="form-control" id="studentGrade"
													placeholder="" value="${studentInfo.grade }" disabled>
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>



											<div class="col-sm-4">
												<label for="firstName" class="form-label">학번</label> <input
													type="text" class="form-control" id="studentId"
													placeholder="" value="${studentInfo.studentId }" disabled>
												<div class="invalid-feedback">Valid first name is
													required.</div>
											</div>

											<div class="col-sm-4">
												<label for="lastName" class="form-label">성명</label> <input
													type="text" class="form-control" id="name"
													placeholder="" value="${studentInfo.name }" disabled>
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>

											<div class="col-sm-4">
												<label for="lastName" class="form-label">생년월일</label> <input
													type="text" class="form-control" id="birthDate"
													placeholder="" value="${studentInfo.birthDate }" disabled>
												<div class="invalid-feedback">Valid last name is
													required.</div>
											</div>


											<div class="my-3 row">
												<label for="lastName" class="form-label">휴학구분</label>
												
												<input type="text" id="type" value="일반휴학(개인사정,기타)"></input>

												<div class="col-sm-6">
													<div class="form-check">
														<input id="credit" name="paymentMethod" type="radio"
															class="form-check-input" checked="" required="">
														<label class="form-check-label" for="credit">일반휴학(개인사정,기타)</label>
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
														id="fromYear" name="paymentMethod" type="month"
														class="form-control" required=""> <label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> <select
														class="form-select" id="state fromSemester" required="">
														<option value="">1학기</option>
														<option>2학기</option>
													</select>
													<div class="invalid-feedback">Please provide a valid
														state.</div>
												</div>




												<div class="col-md-6">
													<label for="state" class="form-label">휴학 종료일</label> <input
														id="toYear" name="paymentMethod" type="month"
														class="form-control" required=""> <label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> <select
														class="form-select" id="toSemester" required="">
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
												value="${studentInfo.email }" disabled>
											<div class="invalid-feedback">Please enter a valid
												email address for shipping updates.</div>
										</div>

										<div class="col-12">
											<label for="address" class="form-label">주소</label> <input
												type="text" class="form-control" id="address"
												value="${studentInfo.address }" disabled>
											<div class="invalid-feedback">Please enter your
												shipping address.</div>
										</div>

										<div class="col-12">
											<label for="address2" class="form-label">휴대폰</label> <input
												type="text" class="form-control" id="tel"
												value="${studentInfo.tel }" disabled>
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
										
														
													
										</div>
		<div class="form-check">
													<input type="text" id="appDate" value="${studentInfo.today }"></input>
											<label
												class="form-check-label" for="same-address">위와 같이 휴학하고자 하오니 허가하여 주시기 바랍니다. </label>
										</div>
										<hr class="my-4">

										<button class="w-100 btn btn-primary btn-lg" type="button" id="loa-btn">휴학 신청</button>
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
		
	<script>
	document.addEventListener("DOMContentLoaded", function(){
		let department = document.getElementById("department").value;
		let grade = document.getElementById("studentGrade").value;
		let studentId = document.getElementById("studentId").value;
		let name = document.getElementById("name").value;
		let birthDate = document.getElementById("birthDate").value;
		let email = document.getElementById("email").value;
		let address = document.getElementById("address").value;
		let tel = document.getElementById("tel").value;
		let appDate = document.getElementById("appDate").value;
		let type = document.getElementById("type").value;
		

		/* 라디오 버튼 클릭시 휴학 type value 값 변경  */
		const radioButtons = document.querySelectorAll('input[type="radio"]');
		const inputType = document.getElementById('type');

		// 라디오 버튼에 대한 이벤트 리스너 등록
		radioButtons.forEach(function(radioButton) {
		    radioButton.addEventListener('change', function() {
		        if (this.checked) {
		            selectedType = this.nextElementSibling.textContent.trim(); // 선택된 타입을 전역 변수에 할당
		            inputType.value = selectedType; // value 값을 선택된 라디오 버튼의 라벨로 변경
		            console.log("선택된 타입:", selectedType);
		            console.log('원래 type은 변경이 됐을까?', type.value); // 변경된 value 값 출력
		        }
		    });
		});
		
		

		
		const loaBtn = document.getElementById("loa-btn");
		
		loaBtn.addEventListener("click", async function(event){
			/*if(!confirm("휴학 신청을 하시겠습니까?")){
				event.preventDefault();
				return;
			}*/
			
			/* 휴학 정보 전송 */
			
			
			console.log("department : " + department);
			console.log("grade : " + grade);
			console.log("studentId : " + studentId);
			console.log("name : " + name);
			console.log("birthDate : " + birthDate);
			console.log("email : " + email);
			console.log("address : " + address);
			console.log("tel : " + tel);
			console.log("appDate : " + appDate);
			console.log("type : " + type);
			
	        let formData = {
	                "department": department,
	                "grade": grade,
	                "studentId": studentId,
	                "name": name,
	                "birthDate": birthDate,
	                "email": email,
	                "address": address,
	                "tel": tel,
	                "appDate": appDate,
	                "type" : type
	            };
	            
			try {
	            const response = await fetch("/student/leaveApp", {
	                method: "POST",
	                headers: {
	                    "Content-Type": "application/json; charset=UTF-8"
	                },
	                body: JSON.stringify(formData)
	            });
	            
	            if (!response.ok) {
	                throw new Error("휴학 신청 중 오류가 발생했습니다.");
	            }
	            
	            alert("휴학 신청에 성공했습니다.;");
			} catch (error){
				alert(error)
			}
					
		
			
		});
		
	});
	
	</script>
</body>
</html>

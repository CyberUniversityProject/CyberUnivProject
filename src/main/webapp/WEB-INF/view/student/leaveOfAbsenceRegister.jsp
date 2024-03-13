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
													<label for="state" class="form-label">휴학시작 년도</label>
													<select id="startYearBox" class="form-select" >
														<option value = "" selected>년도 선택</option>
													</select><label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> 
													
													<select id="fromSemester" class="form-select">
														<option value="" selected>학기 선택</option>
													</select>
													
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
													<div class="invalid-feedback">Please provide a valid
														state.</div>
												</div>



												<div class="col-md-6">
													<label for="state" class="form-label">휴학 종료 년도</label>
													<select id="endYearBox" class="form-select">
														
														<option value = "" selected>년도 선택</option>
													</select>
													
													<label
														class="form-label" for="paypal"></label>
												</div>

												<div class="col-md-6">
													<label for="state" class="form-label">학기</label> 
													
													<select class="form-select" id="toSemester">
														<option value="" selected>학기 선택</option>
													</select>
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

		let type = document.getElementById("type").value;
		
		/* 라디오 버튼 클릭시 휴학 type value 값 변경  */
		const radioButtons = document.querySelectorAll('input[type="radio"]');

		// 라디오 버튼에 대한 이벤트 리스너 등록
		radioButtons.forEach(function(radioButton) {
		    radioButton.addEventListener('change', function() {
		        if (this.checked) {
		            selectedType = this.nextElementSibling.textContent.trim(); // 선택된 타입을 전역 변수에 할당
		            type = selectedType; // value 값을 선택된 라디오 버튼의 라벨로 변경
		            console.log("선택된 타입:", selectedType);
		            console.log('원래 type은 변경이 됐을까?', type); // 변경된 value 값 출력
		        }
		    });
		});
		
		
		/* 휴학 기간 설정 */
		let startYearValue = ''; // 휴학 시작 년도 값 저장 변수
		let endYearValue = '';   // 휴학 종료 년도 값 저장 변수
		
		// 휴학 학기 기간 저장 변수
		let fromSemesterValue ='';
		let toSemesterValue ='';
		
		let date = new Date();
		let selYear = date.getFullYear();

		// 현재년도 기준으로 호출
		getYears(selYear);

		// 현재 년도를 select
		//document.getElementById('startYearBox').value = selYear;
		document.getElementById('startYearBox').value = '';
		document.getElementById('startYearBox').addEventListener('change', function(){
			startYearValue = this.value;
		});
		document.getElementById('endYearBox').addEventListener('change', function(){
			endYearValue = this.value;
		});

		function getYears(getY){
		    // 기존 option을 삭제함
		    let yearBox = document.getElementById('startYearBox');
		    yearBox.innerHTML = '';
		    let endYearBox = document.getElementById('endYearBox');
		    endYearBox.innerHTML = '';

		    // 올해 기준으로시작, +3년 까지보여줌
		    let stY = Number(getY);
		    let enY = Number(getY) + 3;
		    
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '년도 선택';
		    yearBox.appendChild(defaultOption.cloneNode(true));
		    endYearBox.appendChild(defaultOption.cloneNode(true));


		    for(let y = stY; y <= enY; y++){
		        let option = document.createElement('option');
		        option.value = y;
		        option.textContent = y + '년';
		        yearBox.appendChild(option);
		        

		        endYearBox.appendChild(option.cloneNode(true));
		    }
		    
		    yearBox.addEventListener('change', function() {
		    	startYearValue = this.value;
		        console.log(startYearValue);
		    	// 휴학 종료일 시작을 휴학시작년도 이후로 불러오는 함수
		    	startSemester();
		    });
		}
		
		
		function chageYear(){
			// endYearsBox 년도를 startYearsBox 년도로 select
			getYears2(document.getElementById('startYearBox').value);
			
			function getYears2(startYear) {
			    // 기존 option을 삭제함
			    let yearBox = document.getElementById('endYearBox');
			    yearBox.innerHTML = '';

			    // startYear를 기준으로 시작, +3년 까지 보여줌
			    let stY = Number(startYear);
			    let enY = Number(startYear) + 3;

			    for(let y = stY; y <= enY; y++) {
			        let option = document.createElement('option');
			        option.value = y;
			        option.textContent = y +"년";
			        yearBox.appendChild(option);
			    }
			    
			    yearBox.addEventListener('change', function() {
			    	endYearValue = this.value;
			        console.log(endYearValue);
			        
			    	// 휴학 종료일 시작을 휴학시작년도 이후로 불러오는 함수
			    	endSemester();
			    });
			}
		}
		


 
		function startSemester() {
		    let fromSemesterBox = document.getElementById('fromSemester');
		    fromSemesterBox.innerHTML = ''; // 기존의 옵션을 삭제합니다.

		    // "학기" 옵션을 생성합니다.
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '휴학 신청 학기';
		    fromSemesterBox.appendChild(defaultOption.cloneNode(true));

		    // "1학기"와 "2학기"를 옵션으로 추가합니다.
		    let option1 = document.createElement('option');
		    option1.value = '1';
		    option1.textContent = '1학기';
		    fromSemesterBox.appendChild(option1);

		    let option2 = document.createElement('option');
		    option2.value = '2';
		    option2.textContent = '2학기';
		    fromSemesterBox.appendChild(option2);

		    // 학기가 선택될 때 값을 저장합니다.
		    fromSemesterBox.addEventListener('change', function(){
		    	fromSemesterValue = this.value;
		        console.log(fromSemesterValue);
		        
		    	chageYear();
		    });
		}
		

		function endSemester() {
		    let toSemesterBox = document.getElementById('toSemester');
		    toSemesterBox.innerHTML = ''; // 기존의 옵션을 삭제합니다.

		    // "학기" 옵션을 생성합니다.
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '휴학 신청 학기';
		    toSemesterBox.appendChild(defaultOption.cloneNode(true));


		 // 시작년도와 종료년도가 같고 시작학기가 2학기인 경우
		    if (startYearValue === endYearValue && fromSemesterValue === '2') {
		        // "2학기" 옵션만 추가합니다.
		        let option2 = document.createElement('option');
		        option2.value = '2';
		        option2.textContent = '2학기';
		        toSemesterBox.appendChild(option2);
		    } else {
		        // 그 외의 경우에는 "1학기"와 "2학기" 옵션을 모두 추가합니다.
		        let option1 = document.createElement('option');
		        option1.value = '1';
		        option1.textContent = '1학기';
		        toSemesterBox.appendChild(option1);

		        let option2 = document.createElement('option');
		        option2.value = '2';
		        option2.textContent = '2학기';
		        toSemesterBox.appendChild(option2);
		    }
		 
		    // 학기가 선택될 때 값을 저장합니다.
		    toSemesterBox.addEventListener('change', function(){
		    	
		        toSemesterValue = this.value;
		        console.log(toSemesterValue);
		    });
		}
		
		/* 휴학 정보 전송 */
		const loaBtn = document.getElementById("loa-btn");
		
		loaBtn.addEventListener("click", async function(event){
			/*if(!confirm("휴학 신청을 하시겠습니까?")){
				event.preventDefault();
				return;
			}*/

			let studentId = document.getElementById("studentId").value;
			let studentGrade = document.getElementById("studentGrade").value;
			let appDate = document.getElementById("appDate").value;

			console.log("studentId : " + studentId);
			console.log("studentGrade : " + studentGrade);
			console.log("startYearValue" + startYearValue);
			console.log("endYearValue" + endYearValue);
			console.log("fromSemesterValue"+fromSemesterValue);
			console.log("toSemesterValue"+toSemesterValue);
			console.log("type : " + type);
			console.log("appDate : " + appDate);
			
	        let formData = {
	                "studentId": studentId,
	                "studentGrade": studentGrade,
	                "fromYear" : startYearValue,
	                "fromSemester" : fromSemesterValue,
	                "toYear": endYearValue,
	                "toSemester" : toSemesterValue,
	                "type" : type,
	                "appDate": appDate
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학생 마이페이지</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>학생 마이 페이지</h2>

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
							href="/student/myInfo">내 정보 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">비밀번호 변경</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">휴학 신청</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">휴학 내역 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">등록금 내역 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">등록금 납부 고지서</a></li>
					</ul>
				</div>
			</aside>

			<div class="col-md-10">
				<h1 class="mt-5 mb-4">내 정보 조회</h1>
				<div class="row justify-content-center">
					<div class="col-md-7">
						<div class="card">
							<div class="card-body">
								<form action="/student/modifyMyInfo" method="post">
									<div class="form-group">
										<label for="name">이름</label> <input type="text" name="name"
											id="name" class="form-control" value="${studentInfo.name }"
											disabled>
									</div>
									<div class="form-group">
										<label for="birthDate">생년월일</label> <input type="date"
											name="birthDate" id="birthDate" class="form-control"
											value="${studentInfo.birthDate }">
									</div>



									<div class="form-group">
										<label>성별</label><br>
										<div class="form-check form-check-inline">
											<input type="radio" value="남성" name="gender" id="남성"
												class="form-check-input" disabled
												<c:if test="${studentInfo.gender == '남성'}">checked</c:if>>
											<label for="남성" class="form-check-label">남성</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="radio" value="여성" name="gender" id="여성"
												class="form-check-input" disabled
												<c:if test="${studentInfo.gender == '여성'}">checked</c:if>>
											<label for="여성" class="form-check-label">여성</label>
										</div>
									</div>


									<p>${studentInfo.gender}</p>


									<div class="form-group">
										<label for="address">주소</label> <input type="text"
											name="address" id="address" class="form-control"
											value="${studentInfo.address }">
									</div>
									<div class="form-group">
										<label for="tel">전화번호</label> <input type="text" name="tel"
											id="tel" class="form-control" value="${studentInfo.tel }">
									</div>
									<div class="form-group">
										<label for="email">이메일</label> <input type="text" name="email"
											id="email" class="form-control" value="${studentInfo.email }">
									</div>
									<div class="form-group">
										<label for="college">단과대</label> <input type="text"
											name="college" id="college" class="form-control"
											value="${studentInfo.collegeName}" disabled>
									</div>
									<div class="form-group">
										<label for="department">학과</label> <input type="text"
											name="department" id="department" class="form-control"
											value="${studentInfo.deptName}" disabled>
									</div>
									<div class="form-group">
										<label for="semester">학기</label> <input type="text"
											name="semester" id="semester" class="form-control"
											value="${studentInfo.grade}학년 ${studentInfo.semester}학기"
											disabled>
									</div>
									<div class="form-group">
										<label for="entranceDate">입학일</label> <input type="date"
											name="entranceDate" id="entranceDate" class="form-control"
											value="${studentInfo.entranceDate}" disabled>
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
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<<<<<<< HEAD:src/main/webapp/WEB-INF/view/student/studentInfo.jsp
	
	<script>
	document.addEventListener("DOMContentLoaded", function(){
	    const updateBtn = document.getElementById("update-student-btn");
	    
	    updateBtn.addEventListener("click", function(event){
	        if (!confirm("회원 정보를 수정 하시겠습니까?")) {
	            event.preventDefault();
	            return;
	        }
	        
	        let address = document.getElementById("address").value;
	        let tel = document.getElementById("tel").value;
	        let email = document.getElementById("email").value;
	        
	        let formData = {
	            "address": address,
	            "tel": tel,
	            "email": email
	        };
	        
	        fetch("/student/updateInfo", {
	            method: "POST",
	            headers: {
	                "Content-Type": "application/json; charset=UTF-8"
	            },
	            body: JSON.stringify(formData)
	        })
	        .then(response => {
	            if (!response.ok) {
	                throw new Error("오류 발생");
	            }
	            alert("정보가 성공적으로 수정되었습니다.");
	            window.location.reload();
	        })
	        .catch(error => {
	            console.error(error);
	        });
	    });
	});
	</script>
		
		
		
=======
>>>>>>> dev:src/main/webapp/WEB-INF/view/user/studentInfo.jsp
</body>
</html>

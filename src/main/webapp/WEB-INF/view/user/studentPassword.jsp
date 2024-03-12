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
			<h2>학생 비밀번호 변경</h2>
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

					<h1>비밀번호 변경</h1>

					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>

					<!-- 비밀번호 변경 폼 end -->

					<div class="row mb-3">
						<div class="col-md-7">
							<form class="form-signin">
								<label for="inputEmail" class="sr-only"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;"></font></font></label> <input
									type="email" id="inputEmail" class="form-control"
									placeholder="이메일 주소" value="${userInfo.id }" disabled> <label
									for="inputPassword" class="sr-only"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">비밀번호</font></font></label> <input
									type="password" id="inputPassword" class="form-control"
									placeholder="비밀번호" value="${userInfo.password }">
								<button class="btn btn-lg btn-primary btn-block" type="submit">
									<font style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">비밀번호 변경</font></font>
								</button>
							</form>
						</div>
					</div>

					<!-- 비밀번호 변경 폼 end -->
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
	    const updateBtn = document.getElementById("update-student-btn");
	    
	    updateBtn.addEventListener("click", function(event){
	        event.preventDefault();
	        
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
	            console.log("업데이트 성공");
	        })
	        .catch(error => {
	            console.error(error);
	        });
	    });
	});
	
	</script>



</body>
</html>

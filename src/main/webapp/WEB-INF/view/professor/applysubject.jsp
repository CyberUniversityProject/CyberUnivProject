<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>강의 등록</title>
</head>
<body>

	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의 신청</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->


	<!-- 사이드바 메뉴 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>
			<div class="col-md-10 p-5">
				<form action="/professor/apply" method="post"
					class="form--container" onsubmit="return validateForm()">
					<div class="card">
						<div class="card-body">
							<div class="mb-3">
								<label for="name" class="form-label">강의명</label> <input
									type="text" class="form-control" id="name" name="name"
									placeholder="강의명을 입력하세요">
							</div>
							<div class="mb-3">
							<label for="roomId" class="form-label">강의실</label>
								<select name="roomId">
									<c:forEach items="${room}" var="room">
										<option value="${room.id}">${room.id}</option>
									</c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label for="deptId" class="form-label">학과</label>
								<select name="deptId">
									<c:forEach items="${departments}" var="departments">
										<option value="${departments.id}">${departments.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label class="form-label">강의 종류</label>
								<div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="type"
											id="major" value="전공" checked> <label
											class="form-check-label" for="major">전공</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="type"
											id="culture" value="교양"> <label
											class="form-check-label" for="culture">교양</label>
									</div>
								</div>
							</div>
							<div class="mb-3">
								<label for="time" class="form-label">시작 시간</label> <input
									type="text" class="form-control" id="startTime"
									name="startTime" placeholder="강의 시작 시간을 입력하세요">
							</div>
							<div class="mb-3">
								<label for="time" class="form-label">종료 시간</label> <input
									type="text" class="form-control" id="endTime" name="endTime"
									placeholder="강의 종료 시간을 입력하세요">
							</div>
							<div class="mb-3">
								<label for="subYear" class="form-label">년도</label> <input
									type="number" class="form-control" id="subYear" name="subYear"
									placeholder="강의 개설 년도를 입력하세요">
							</div>
							<div class="mb-3">
								<label for="semester" class="form-label">학기</label> <input
									type="number" class="form-control" id="semester"
									name="semester" placeholder="강의 개설 학기를 입력하세요">
							</div>
							<div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="subDay"
										id="mo" value="월" checked> <label
										class="form-check-label" for="mo">월</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="subDay"
										id="tu" value="화"> <label
										class="form-check-label" for="tu">화</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="subDay"
										id="we" value="수"> <label
										class="form-check-label" for="we">수</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="subDay"
										id="th" value="목"> <label
										class="form-check-label" for="th">목</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="subDay"
										id="fr" value="금"> <label
										class="form-check-label" for="fr">금</label>
								</div>
							</div>
							<div class="mb-3">
								<label for="grades" class="form-label">학점</label> <input
									type="number" class="form-control" id="grades" name="grades"
									placeholder="이수 학점을 입력하세요">
							</div>
							<div class="mb-3">
								<label for="capacity" class="form-label">정원</label> <input
									type="number" class="form-control" id="capacity"
									name="capacity" placeholder="강의 총 정원을 입력하세요">
							</div>
							<button type="submit" class="btn btn-primary">입력</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		function validateForm() {
			var name = document.getElementById("name").value.trim();
			var proName = document.getElementById("proName").value.trim();
			var subTime = document.getElementById("subTime").value.trim();
			var subGrade = document.getElementById("subGrade").value.trim();
			var capacity = document.getElementById("capacity").value.trim();
			var majorChecked = document.getElementById("major").checked;
			var cultureChecked = document.getElementById("culture").checked;
			var errorMessage = "";
			var numericExpression = /^[0-9]+$/;

			if (subName === "") {
				errorMessage += "강의명을 입력하세요.\n";
			}

			if (proName === "") {
				errorMessage += "교수이름을 입력하세요.\n";
			}

			if (!numericExpression.test(subTime)) {
				errorMessage += "강의 시간은 숫자만 입력하세요.\n";
			}

			if (subTime === "") {
				errorMessage += "강의 시간을 입력하세요.\n";
			}

			if (!numericExpression.test(subGrade)) {
				errorMessage += "학점은 숫자만 입력하세요.\n";
			}
			if (subGrade === "") {
				errorMessage += "학점을 입력하세요.\n";
			}

			if (!numericExpression.test(capacity)) {
				errorMessage += "정원은 숫자만 입력하세요.\n";
			}
			if (capacity === "") {
				errorMessage += "정원을 입력하세요.\n";
			}

			
			if (errorMessage !== "") {
				alert(errorMessage);
				return false;
			}

			return true;
		}
	</script>

</body>
</html>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
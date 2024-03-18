<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 변경</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<style>
.form--container {
	max-width: 600px;
	margin: 0 auto;
	padding: 40px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
}

.input--box {
	width: 100%;
	padding: 10px;
	border: 1px solid #ced4da;
	border-radius: 3px;
}

.button--container {
	text-align: center;
}

.update--button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	color: #fff;
	background-color: #343a40;
	cursor: pointer;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>비밀번호 변경</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/mypageAsidebar.jsp"%>

			<div class="col-md-10">
				<div class="container mt-5">
					<h1>비밀번호 변경</h1>
					<hr />
					<div class="split--div"></div>
					<form action="/password" method="post" class="form--container">
						<input type="hidden" name="_method" value="put" />
						<div class="form-group">
							<label for="beforePassword">현재 비밀번호</label> <input
								type="password" name="beforePassword"
								class="form-control input--box" id="beforePassword">
						</div>
						<div class="form-group">
							<label for="afterPassword">변경할 비밀번호</label> <input
								type="password" name="afterPassword"
								class="form-control input--box" id="afterPassword">
						</div>
						<div class="form-group">
							<label for="passwordCheck">변경할 비밀번호 확인</label> <input
								type="password" name="passwordCheck"
								class="form-control input--box" id="passwordCheck">
						</div>
						<c:choose>
							<c:when test="${principal.getUserRole().equals(\"professor\")}">
								<div class="button--container">
									<input type="submit" value="입력" class="btn btn-primary">
								</div>
							</c:when>
							<c:otherwise>
								<br>
								<div class="button--container">
									<button type="submit" class="btn btn-dark update--button">수정하기</button>
								</div>
							</c:otherwise>
						</c:choose>
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
	 <script>
//   $(document).ready(function() {
//     $('form').submit(function() {
//       alert("비밀번호가 변경되었습니다");
//     });
//   });
</script>
</body>
</html>

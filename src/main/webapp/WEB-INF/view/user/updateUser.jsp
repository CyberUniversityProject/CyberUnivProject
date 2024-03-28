<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보 변경</title>
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
			<h2>회원정보 변경</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<div class="row">
			<!-- 사이드바 메뉴 -->
				<%@ include file="/WEB-INF/view/layout/mypageAsidebar.jsp"%>

			<div class="col-md-10">
				<div class="container">
					
					<div class="row">
						<div class="col-md-12">
							<div class="split--div" style="margin-bottom: 50px;"></div>
							<form action="/update" method="post" class="info--update--form">
								<input type="hidden" name="_method" value="put" />
								<div class="form-group row">
									<label for="address" class="col-sm-2 col-form-label">주소</label>
									<div class="col-sm-10">
										<input type="text" name="address" id="address" class="form-control" value="<c:out value='${userInfo.address}' />">
									</div>
								</div>
								<div class="form-group row">
									<label for="tel" class="col-sm-2 col-form-label">전화번호</label>
									<div class="col-sm-10">
										<input type="text" name="tel" id="tel" class="form-control" value="<c:out value='${userInfo.tel}' />">
									</div>
								</div>
								<div class="form-group row">
									<label for="email" class="col-sm-2 col-form-label">이메일</label>
									<div class="col-sm-10">
										<input type="text" name="email" id="email" class="form-control" value="<c:out value='${userInfo.email}' />">
									</div>
								</div>
								<div class="form-group row">
									<label for="password" class="col-sm-2 col-form-label">비밀번호
										확인</label>
									<div class="col-sm-10">
										<input type="password" name="password" class="form-control" id="password">
									</div>
								</div>
								<c:choose>
									<c:when test="${principal.getUserRole().equals(\"staff\")}">
										<div class="form-group row">
											<div class="col-sm-10 offset-sm-2">
												<input type="submit" value="입력" class="btn btn-primary">
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-group row">
											<div class="col-sm-10 offset-sm-2">
												<button type="submit" class="btn btn-dark update--button">수정하기</button>
											</div>
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

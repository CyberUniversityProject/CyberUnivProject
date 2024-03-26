<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>단대별 등록금 등록</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>단대별 등록금 등록페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarRegistration.jsp"%>

			<div class="col-md-10">

				<div class="split--div"></div>
				<ul class="nav nav-tabs mt-5">
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("insert")) ? "active" : ""%>"
						href="/staff/tuition?crud=insert"> 단대별 등록금 등록
					</a></li>
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("update")) ? "active" : ""%>"
						href="/staff/tuition?crud=update"> 
							등록금 수정
					</a></li>
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("delete")) ? "active" : ""%>"
						href="/staff/tuition?crud=delete"> 
							등록금 삭제
					</a></li>
				</ul>




				<c:if test="${crud.equals(\"insert\")}">
					<div class="card mt-3">
						<div class="card-header">✏ 등록금 입력</div>
						<div class="card-body">
							<form action="/staff/tuition" method="post"
								class="form--container">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="collegeId">단과대</label> <select
												class="form-control" id="collegeId" name="collegeId">
												<c:forEach var="college" items="${collegeList}">
													<option value="${college.id}">${college.name}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="amount">금액</label> <input type="text"
												class="form-control" id="amount" name="amount"
												placeholder="금액을 입력하세요">
										</div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary">등록</button>
							</form>
						</div>
					</div>

					<!-- 등록금 목록 테이블 -->
					<div class="card mt-3">
						<div class="card-header">💰 등록금 목록</div>
						<div class="card-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>단과대</th>
										<th>금액</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="collTuit" items="${collTuitList}">
										<tr>
											<td>${collTuit.collegeId}</td>
											<td>${collTuit.name}</td>
											<td>${collTuit.amountFormat()}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>




				<!-- 등록금 수정 -->
				<c:if test="${crud.equals(\"update\")}">
					<div class="card mt-3">
						<div class="card-header">🏫 등록금 수정</div>
						<div class="card-body">
							<form action="/staff/tuitionUpdate" method="post"
								class="form--container">
								<input type="hidden" name="_method" value="put" />
								<div class="form-group">
									<label for="collegeId">단과대</label> <select name="collegeId"
										class="form-control" id="collegeId">
										<c:forEach var="college" items="${collegeList}">
											<option value="${college.id}">${college.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="amount">금액</label> <input type="text" name="amount"
										class="form-control" id="amount" placeholder="등록금을 입력하세요">
								</div>
								<button type="submit" class="btn btn-primary">수정</button>
							</form>
						</div>
					</div>

					<div class="card mt-3">
						<div class="card-header">📋 등록된 등록금 목록</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>단과대</th>
											<th>금액</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="collTuit" items="${collTuitList}">
											<tr>
												<td>${collTuit.collegeId}</td>
												<td>${collTuit.name}</td>
												<td>${collTuit.amountFormat()}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>



				<!-- 등록금 삭제 -->
				<c:if test="${crud.equals(\"delete\")}">
					<div class="card mt-3">
						<div class="card-header">🚫 등록금 삭제</div>
						<div class="card-body">
							<p class="delete">등록금을 삭제할 단과대학을 클릭해주세요</p>
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>단과대</th>
											<th>금액</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="collTuit" items="${collTuitList}">
											<tr>
												<td>${collTuit.collegeId}</td>
												<td><a href="#" onclick="confirmDelete('${collTuit.collegeId}', '${collTuit.name}')">${collTuit.name}</a></td>
												<td>${collTuit.amountFormat()}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>

				<!-- 등록금 조회 -->
				<c:if test="${crud.equals(\"select\")}">
					<div class="card mt-3">
						<div class="card-header">🔍 등록금 조회</div>
						<div class="card-body">
							<div class="form--container">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>ID</th>
												<th>단과대</th>
												<th>금액</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="collTuit" items="${collTuitList}">
												<tr>
													<td>${collTuit.collegeId}</td>
													<td>${collTuit.name}</td>
													<td>${collTuit.amountFormat()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:if>


			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
function confirmDelete(collegeId, name) {
    // 얼럿창을 띄워 사용자에게 확인 여부를 물어봅니다.
    var confirmation = confirm("'" + name + "' 항목을 삭제하시겠습니까?");

    // 만약 사용자가 확인을 선택했을 경우
    if (confirmation) {
        // 삭제를 진행하기 위해 해당 URL로 리다이렉트합니다.
        window.location.href = "/staff/tuitionDelete?collegeId=" + collegeId;
    }
    // 사용자가 취소를 선택했을 경우, 아무 작업도 수행되지 않습니다.
}
</script>
	


</body>
</html>

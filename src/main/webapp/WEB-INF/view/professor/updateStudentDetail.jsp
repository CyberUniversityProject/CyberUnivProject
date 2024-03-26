<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학사관리시스템</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">


</head>
<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- 상단 제목부분 -->
	<div class="container mt-4">
		<h2>학생 성적 기입</h2>
	</div>
	<!-- 상단 제목부분 끝 -->

	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>
			<div class="col-md-10 pt-5">
				<div class="split--div"></div>
				<div class="container-table">
					<div class="card mb-3 table-row">
						<div class="card-header">학생 정보</div>
						<div class="card-body">
							<table class="table">
								<thead>
									<tr>
										<th>학생 번호</th>
										<th>이름</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${studentInfo.id}</td>
										<td>${studentInfo.name}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="card table-row">
						<div class="card-header">성적 입력</div>
						<div class="card-body">
							<form action="/professor/subject/${subjectId}/${studentInfo.id}"
								method="post" onsubmit="return validateForm()">
								<table class="table">
									<tbody>
										<tr>
											<td><label>결석</label></td>
											<td><input type="number" name="absent" id="absent"
												autocomplete="off"> <span style="color: #888"><br>※
													결석 5회 이상시 F학점입니다.</span></td>
										</tr>
										<tr>
											<td><label>지각</label></td>
											<td><input type="number" name="lateness" id="lateness"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td><label>과제점수</label></td>
											<td><input type="number" name="homework" id="homework"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td><label>중간시험</label></td>
											<td><input type="number" name="midExam" id="midExam"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td><label>기말시험</label></td>
											<td><input type="number" name="finalExam" id="finalExam"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td><label>환산점수</label></td>
											<td><input type="number" name="convertedMark"
												id="convertedMark" autocomplete="off"></td>
										</tr>
										<tr>
											<td><label>등급</label></td>
											<td><select name="grade" class="form-control">
													<option value="A+">A+</option>
													<option value="A0">A0</option>
													<option value="B+">B+</option>
													<option value="B0">B0</option>
													<option value="C+">C+</option>
													<option value="C0">C0</option>
													<option value="D+">D+</option>
													<option value="D0">D0</option>
													<option value="F">F</option>
											</select></td>
										</tr>
										<tr>
											<td colspan="2">
												<button type="submit" class="btn btn-dark">제출하기</button>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
	
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="js/studentDetail.js"></script>
</body>
</html>

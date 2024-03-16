<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 강의 조회</title>
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
			<h2>학생 성적 기입</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/professorAsidebar.jsp"%>
			<div class="col-md-10">
				<div class="split--div"></div>
				<table>
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
				<br>
				<form action="/professor/subject/${subjectId}/${studentInfo.id}"
					method="post">
					<table class="form--table">
						<tbody>
							<tr>
								<td><label>결석</label></td>
								<td><input type="number" name="absent" id="absent"></td>
								<td><span style="color: #888">※ 결석 5회 이상시 F학점입니다.</span></td>
							</tr>
							<tr>
								<td><label>지각</label></td>
								<td><input type="number" name="lateness" id="lateness"></td>
							</tr>
							<tr>
								<td><label>과제점수</label></td>
								<td><input type="number" name="homework" id="homework"></td>
							</tr>
							<tr>
								<td><label>중간시험</label></td>
								<td><input type="number" name="midExam" id="midExam"></td>
							</tr>
							<tr>
								<td><label>기말시험</label></td>
								<td><input type="number" name="finalExam" id="finalExam"></td>
							</tr>
							<tr>
								<td><label>환산점수</label></td>
								<td><input type="number" name="convertedMark"
									id="convertedMark"></td>
							</tr>
							<tr>
								<td><label>등급</label></td>
								<td><select name="grade">
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
								<td colspan="2"><button type="submit"
										class="btn btn-dark update--button">제출하기</button></td>
							</tr>
						</tbody>
					</table>
				</form>
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

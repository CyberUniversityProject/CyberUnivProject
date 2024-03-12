<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Noto Sans KR', sans-serif;
}

body {
	background-color: #f8f9fa;
}

.header--top {
	height: 40px;
	background-color: #3ac162;
}

.section--header {
	margin-top: 30px;
	text-align: center;
}

.search--table {
	width: 400px;
	margin: 0 auto;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.search--table td {
	padding: 10px;
	text-align: left;
}

.col1 {
	width: 20%;
}

.col2 {
	width: 80%;
}

.button--container {
	text-align: center;
	margin-top: 20px;
}

.submit--button {
	padding: 10px 15px;
	border: none;
	border-radius: 10px;
	color: white;
	background-color: #3ac162;
	cursor: pointer;
}

.submit--button:hover {
	background-color: #0d1e3b;
}

.radio--container {
	text-align: center;
}

</style>
</head>

<body>
	<header>
		<div class="header--top"></div>
	</header>
	<section>
		<div class="section--header">
			<h2>비밀번호 찾기</h2>
			<br>
		</div>
		<form action="/find/password" method="post">
			<table class="search--table">
				<colgroup>
					<col class="col1">
					<col class="col2">
				</colgroup>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="radio--container">
							<label for="student">학생</label>
							<input type="radio" name="userRole" value="student" id="student">
							<label for="professor">교수</label>
							<input type="radio" name="userRole" value="professor" id="professor">
							<label for="staff">직원</label>
							<input type="radio" name="userRole" value="staff" id="staff">
						</div>
					</td>
				</tr>
			</table>
			<div class="button--container">
				<button type="submit" class="submit--button">임시 비밀번호 발급받기</button>
			</div>
		</form>
	</section>

</body>
</html>

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
	background-color: #5fcf80;
}

.section--header {
	margin-top: 30px;
	text-align: center;
}

.section--content {
	margin: 20px auto;
	padding: 20px;
	width: 450px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.result--message {
	font-size: 1.2rem;
	margin-bottom: 20px;
}

.result--password {
	font-size: 1.5rem;
	font-weight: bold;
	color: #142845;
}

.submit--button {
	display: block;
	width: 200px;
	margin: 20px auto;
	padding: 10px 15px;
	border: none;
	border-radius: 10px;
	color: white;
	background-color: #142845;
	cursor: pointer;
}

.submit--button:hover {
	background-color: #0d1e3b;
}

.close--button {
	display: block;
	width: 100px;
	margin: 20px auto;
	padding: 10px 15px;
	border: none;
	border-radius: 10px;
	color: white;
	background-color: #ff6b6b;
	cursor: pointer;
}

.close--button:hover {
	background-color: #e74c3c;
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
	</div>
	<div class="section--content">
		<p class="result--message">${name}님의 임시 비밀번호는</p>
		<p class="result--password">${password}</p>
		<p>입니다. 보안을 위해 로그인 후 비밀번호를 변경해주세요.</p>
		<button class="close--button" onclick="window.close()">닫기</button>
	</div>
</section>
</body>
</html>

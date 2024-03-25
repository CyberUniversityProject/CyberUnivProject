<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
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

.section--content {
	margin: 20px auto;
	padding: 20px;
	width: 400px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.result--message {
	font-size: 1.2rem;
	margin-bottom: 20px;
}

.result--message2 {
	font-size: 1.2rem;
	margin-bottom: 20px;
	text-align: end;
}

.result--id {
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

.result--id {
    text-align: center;
    font-weight: bold;
}

.close--button {
    display: block;
    width: 100px;
    margin: 20px auto;
    padding: 10px 15px;
    border: none;
    border-radius: 10px;
    color: white;
    background-color: #b32424;
    cursor: pointer;
}

.close--button:hover {
    background-color: #751b1b;
}
</style>
<script type="text/javascript">
function closePopup() {
    window.close();
}
</script>
</head>

<body>
<header>
	<div class="header--top"></div>
</header>
<section>
	<div class="section--header">
		<h2>아이디 찾기 결과</h2>
	</div>
	<div class="section--content">
		<p class="result--message">${name}님의 아이디는</p>
		<p class="result--id">${id}</p>
		<p class="result--message2">입니다.</p>
		<button class="close--button" onclick="closePopup()">닫기</button>
	</div>
</section>
</body>
</html>

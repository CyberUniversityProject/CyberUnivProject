<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 안내</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Noto Sans KR', sans-serif;
}

body {
	background-color: #f2f2f2;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

.header--top {
	width: 100%;
	height: 10px;
	background-color: #031734;
}

.section--header {
	text-align: center;
	margin-bottom: 20px;
}

.section--content {
	text-align: center;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 40px;
}

h2 {
	color: #142845;
	font-size: 28px;
	margin-bottom: 20px;
}

p {
	color: #555;
	font-size: 18px;
	margin-bottom: 20px;
}

.button-container {
	display: flex;
	justify-content: center;
}

.button {
	padding: 15px 30px;
	border: none;
	border-radius: 5px;
	color: #fff;
	background-color: #142845;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.button:hover {
	background-color: #0a1633;
}
</style>
<script language="JavaScript" type="text/JavaScript">
	function linkToOpener(url) {
		window.opener.location.href="/password";
		window.close();
	}
</script>
</head>

<body>
	<header>
		<div class="header--top"></div>
	</header>
	<div class="container">
		<section>
			<div class="section--header">
				<h2>비밀번호 변경 안내</h2>
			</div>
			<div class="section--content">
				<p>현재 계정이 신규로 생성되어 <br> 비밀번호가 초기 설정되어있는 비밀번호입니다.<br>비밀번호를 다시 설정해주세요.</p>
				<div class="button-container">
					<button class="button" onclick="linkToOpener()">비밀번호 변경하러 가기</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>

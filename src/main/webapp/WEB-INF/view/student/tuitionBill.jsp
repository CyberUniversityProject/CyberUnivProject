<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록금 고지서</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
	;

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
			<h2>등록금 고지서</h2>
			<br>
		</div>

		<!-- 등록금 고지서 헤더 -->
		<div class="container">

			<!-- 등록금 정보 테이블 -->
			<div class="table-responsive">
				<table class="table table-bordered">
					<tr>
						<th class="bg-light">항목</th>
						<th>내용</th>
					</tr>
					<tr>
						<td>학기</td>
						<td>2024년도 1학기</td>
					</tr>
					<tr>
						<td>학과</td>
						<td>컴퓨터 공학과</td>
					</tr>
					<tr>
						<td>학번</td>
						<td>2023123456</td>
					</tr>
				</table>
			</div>

			<!-- 수업별 등록금 정보 테이블 -->
			<div class="table-responsive">
				<table class="table table-bordered">
					<tr>
						<th class="bg-light">과목</th>
						<th>학점</th>
						<th>단가</th>
						<th>금액</th>
					</tr>
					<tr>
						<td>수학</td>
						<td>3</td>
						<td>150,000원</td>
						<td>450,000원</td>
					</tr>
					<tr>
						<td>영어</td>
						<td>3</td>
						<td>150,000원</td>
						<td>450,000원</td>
					</tr>
					<tr class="total">
						<td colspan="3">총액</td>
						<td>900,000원</td>
					</tr>
				</table>
			</div>

			<!-- 고지서 설명 -->
			<p class="mt-4">본 고지서는 등록금 안내 목적으로 발송되었습니다. 실제 결제는 지정된 기한 내에 수행하여
				주시기 바랍니다.</p>
				
		</div>
				<!-- 등록금 고지서 하단 -->
<!-- 등록금 고지서 하단 -->
<div class="container mt-4 text-center">
    <h2>Cyber University</h2>
    <!-- <img src="대학_직인_이미지_주소" alt="대학 직인 사진"> -->
</div>


<button class="print" onclick="printPage()">출력</button>
	</section>

</body>
</html>

<script>
	console.log("tuitionBill 페이지 진입 성공");
	function printPage(){
		window.print();
	}
</script>

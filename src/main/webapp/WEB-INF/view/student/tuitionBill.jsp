<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<c:choose>
	<c:when test="${tuitionBill.status eq '0'}">
		<title>등록금 고지서</title>
	</c:when>
	<c:otherwise>
		<title>등록금 납부 영수증</title>
	</c:otherwise>
</c:choose>


<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
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
@media print {
  .noprint { 
	display:none;
	}
}
</style>

</head>

<body>
	<header>
		<div class="header--top"></div>
	</header>
	<section>
		<c:choose>
			<c:when test="${tuitionBill.status eq '0'}">
				<div class="section--header">
					<h2>등록금 납입 고지서</h2>
					<br>
				</div>

			</c:when>
			<c:otherwise>
				<div class="section--header">
					<h2>등록금 납부 영수증</h2>
					<br>
				</div>


			</c:otherwise>
		</c:choose>



		<!-- 등록금 고지서 헤더 -->
		<div class="container">

			<!-- 등록금 정보 테이블 -->
			<div class="table-responsive">
				<table class="table table-bordered">
					<tr>
						<th class="bg-light">항목</th>
						<th colspan="3">내용</th>
					</tr>
					<tr>
						<td>학기</td>
						<td colspan="3">${tuitionBill.tuiYear}년도
							${tuitionBill.semester }학기</td>
					</tr>
					<tr>
						<td>학과</td>
						<td>${student.department }</td>
						<td>학년</td>
						<td>${student.grade }학년</td>
					</tr>
					<tr>
						<td>학번</td>
						<td>${student.studentId }</td>
						<td>이름</td>
						<td>${student.name }</td>
					</tr>
				</table>
			</div>

			<!-- 수업별 등록금 정보 테이블 -->
			<div class="table-responsive">
				<table class="table table-bordered">
					<tr>
						<th class="bg-light" colspan="2">납입금 내역</th>
						<th class="bg-light" colspan="2">장학금 내역</th>
					</tr>
					<tr>
						<th class="bg-light">구분</th>
						<th>금액</th>
						<th>구분</th>
						<th>금액</th>
					</tr>
					<tr>
						<td>수업료</td>
						<td>${tuitionBill.tuiFormat()}<input type="hidden"
							id="tuiAmount" value="${tuitionBill.tuiAmount }">
						</td>

						<td>장학금</td>
						<td>${tuitionBill.schFormat() }<input type="hidden"
							id="schAmount" value="${tuitionBill.schAmount }">

						</td>
					</tr>

					<tr class="total">
						<td colspan="2">총 납입 금액</td>
						<td colspan="2" id="totalAmount"></td>
					</tr>
				</table>

			</div>

			<c:choose>
				<c:when test="${tuitionBill.status eq '0'}">
					<!-- 고지서 설명 -->
					<p class="">납입기간 : 2022~ 2022~</p>
					<p class="">납입방법 : 신용카드 결제</p>

					<p class="mt-2">
						본 고지서는 등록금 안내 목적으로 발송되었습니다. 결제는 지정된 기한 내에 수행하여

						<!-- 등록금 고지서 하단 -->
<div class="container mt-4 text-center">
    <h4>2024년11월11일</h4>
    <div style="position: relative; display: inline-block;">
        <h2 id="universityTitle" style="display: inline-block;">Cyber University</h2>
        <img src="/img/greenUniversityDojang.png" style="position: absolute; top: -25px; right: -60px; width: 100px; height: 100px;" alt="대학 직인 사진">
    </div>
</div>
		<div class="container mt-4 text-center noprint">
					<button class="payment btn" style="border:2px solid #3ac162;" >
				<i class="bi bi-credit-card" style="width:20px; height:20px"></i> 결제하기
			</button>
			<button class="print btn" style="border:2px solid #3ac162;" onclick="printPage()">
				<i class="bi bi-printer" style="width:20px; height:20px"></i> 인쇄하기
			</button>
		</div>

				</c:when>
				<c:otherwise>
					<p class="mt-2">(여기는 결제가 이루어진 날짜가 출력될 곳) 납부가 완료되었습니다.</p>
		</div>
		<!-- 등록금 고지서 하단 -->
		<!-- 등록금 고지서 하단 -->
		<div class="container mt-4 text-center">
			<h4>2024년11월11일</h4>
			<h2>Cyber University</h2>
			<!-- <img src="대학_직인_이미지_주소" alt="대학 직인 사진"> -->
		</div>
		<div class="container mt-4 text-center noprint">
			<button class="print btn" style="border:2px solid #3ac162;" onclick="printPage()">
				<i class="bi bi-printer" style="width:20px; height:20px"></i> 인쇄하기
			</button>
		</div>
		</c:otherwise>
		</c:choose>




	</section>

</body>
</html>

<script>
	console.log("tuitionBill 페이지 진입 성공");
	function printPage() {
		window.print();
	}

	let tuiAmount = document.getElementById('tuiAmount').value;
	console.log(tuiAmount);
	let schAmount = document.getElementById('schAmount').value;
	console.log(schAmount);
	let totalAmount = new Intl.NumberFormat().format(tuiAmount - schAmount);
	console.log(totalAmount)
	document.getElementById('totalAmount').textContent = totalAmount;
</script>

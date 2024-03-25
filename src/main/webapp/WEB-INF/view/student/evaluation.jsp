<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>강의 평가</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>




<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
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
			<h2>강의 평가</h2>
			<br>
		</div>

		<!-- 강의평가 헤더 -->
		<div class="container">

			<div class="container mt-4 text-center">
				<p>본 강의 평가 설문지는 익명으로 작성되며, 이번 학기 교과목의 수업 진행에 대한 학생들의 솔직한 의견을
					파악하여, 앞으로 보다 나은 강의를 하기 위한 목적으로 실시하는 것입니다. 따라서 학생 여러분은 각 평가 문항에 대하여
					성실하고 진지하게 답변해 주시기 바랍니다.</p>
			</div>
			<!-- 강의평가 테이블 -->
			<div class="table-responsive">


				<table class="table table-bordered">
					<tr>
						<th>학기</th>
						<td>${evaluationInfo.subYear}년도${evaluationInfo.semester}학기</td>
						<th>학년</th>
						<td>${evaluationInfo.grade }학년</td>
					</tr>
					<tr>
						<th>학번</th>
						<td>${evaluationInfo.studentId }</td>
						<th>이름</th>
						<td>${evaluationInfo.studentName }</td>
					</tr>
					<tr>
						<th>과목명</th>
						<td>${evaluationInfo.subjectName }</td>
						<th>담당 교수</th>
						<td>${evaluationInfo.professorName}</td>
					</tr>
				</table>
			</div>

			<!-- 강의평가 체크 테이블 -->
			<div class="table-responsive">
				<form action="/student/evaluationPorc/${subjectId}"
					id="evaluationForm">
					<input type="hidden" name="studentId"
						value="${evaluationInfo.studentId}" /> <input type="hidden"
						name="subjectId" value="${evaluationInfo.subjectId}" />
					<table class="table table-bordered">
						<tr class="radio--container">
							<th></th>
							<th class="bg-light">강의 평가 내용</th>
							<th class="bg-light">매우그렇다</th>
							<th class="bg-light">그런편이다</th>
							<th class="bg-light">보통이다</th>
							<th class="bg-light">그렇지 않다</th>
							<th class="bg-light">전혀 그렇지 않다</th>
						</tr>

						<tr class="radio-container">
							<th class="bg-light">1</th>
							<td>${question.question1 }</td>
							<td><input type="radio" value="5" name="answer1" id="a1_5"></td>
							<td><input type="radio" value="4" name="answer1" id="a1_4"></td>
							<td><input type="radio" value="3" name="answer1" id="a1_3"></td>
							<td><input type="radio" value="2" name="answer1" id="a1_2"></td>
							<td><input type="radio" value="1" name="answer1" id="a1_1"></td>
						</tr>

						<tr class="radio-check">
							<th class="bg-light">2</th>
							<td>${question.question2 }</td>
							<td><input type="radio" value="5" name="answer2" id="a2_5"></td>
							<td><input type="radio" value="4" name="answer2" id="a2_4"></td>
							<td><input type="radio" value="3" name="answer2" id="a2_3"></td>
							<td><input type="radio" value="2" name="answer2" id="a2_2"></td>
							<td><input type="radio" value="1" name="answer2" id="a2_1"></td>
						</tr>

						<tr class="radio-check">
							<th class="bg-light">3</th>
							<td>${question.question3 }</td>
							<td><input type="radio" value="5" name="answer3" id="a3_5"></td>
							<td><input type="radio" value="4" name="answer3" id="a3_4"></td>
							<td><input type="radio" value="3" name="answer3" id="a3_3"></td>
							<td><input type="radio" value="2" name="answer3" id="a3_2"></td>
							<td><input type="radio" value="1" name="answer3" id="a3_1"></td>
						</tr>
						<tr class="radio-check">
							<th class="bg-light">4</th>
							<td>${question.question4 }</td>
							<td><input type="radio" value="5" name="answer4" id="a4_5"></td>
							<td><input type="radio" value="4" name="answer4" id="a4_4"></td>
							<td><input type="radio" value="3" name="answer4" id="a4_3"></td>
							<td><input type="radio" value="2" name="answer4" id="a4_2"></td>
							<td><input type="radio" value="1" name="answer4" id="a4_1"></td>
						</tr>
						<tr class="radio-check">
							<th class="bg-light">5</th>
							<td>${question.question5 }</td>
							<td><input type="radio" value="5" name="answer5" id="a5_5"></td>
							<td><input type="radio" value="4" name="answer5" id="a5_4"></td>
							<td><input type="radio" value="3" name="answer5" id="a5_3"></td>
							<td><input type="radio" value="2" name="answer5" id="a5_2"></td>
							<td><input type="radio" value="1" name="answer5" id="a5_1"></td>
						</tr>
						<tr class="radio-check">
							<th class="bg-light">6</th>
							<td>${question.question6 }</td>
							<td><input type="radio" value="5" name="answer6" id="a6_5"></td>
							<td><input type="radio" value="4" name="answer6" id="a6_4"></td>
							<td><input type="radio" value="3" name="answer6" id="a6_3"></td>
							<td><input type="radio" value="2" name="answer6" id="a6_2"></td>
							<td><input type="radio" value="1" name="answer6" id="a6_1"></td>
						</tr>
						<tr class="radio-check">
							<th class="bg-light">7</th>
							<td>${question.question7 }</td>
							<td><input type="radio" value="5" name="answer7" id="a7_5"></td>
							<td><input type="radio" value="4" name="answer7" id="a7_4"></td>
							<td><input type="radio" value="3" name="answer7" id="a7_3"></td>
							<td><input type="radio" value="2" name="answer7" id="a7_2"></td>
							<td><input type="radio" value="1" name="answer7" id="a7_1"></td>
						</tr>
						<tr>
							<td colspan="7"><textarea
									style="width: 100%; height: 150px; resize: none;"
									placeholder="해당 강의가 살려 나가야 할 장점과 개선이 필요한 점(또는 다루어 주었으면 하는 주제나 내용, 강의 진행 방식 등)을 구체적으로 기술하시오."
									name="improvements"></textarea></td>

						</tr>

					</table>
				</form>

			</div>

			<div class="container mt-4 text-center noprint">
				<button type="button" id="evaluationBtn" class="btn"
					style="border: 2px solid #3ac162;">등록</button>
			</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const evaluationBtn = document.getElementById('evaluationBtn');

			evaluationBtn.addEventListener('click', function(event) {
				const studentIdField = document.getElementsByName('studentId')[0];
				const subjectIdField = document.getElementsByName('subjectId')[0];
				const answer1Field = document.querySelector('input[name="answer1"]:checked');
				const answer2Field = document.querySelector('input[name="answer2"]:checked');
				const answer3Field = document.querySelector('input[name="answer3"]:checked');
				const answer4Field = document.querySelector('input[name="answer4"]:checked');
				const answer5Field = document.querySelector('input[name="answer5"]:checked');
				const answer6Field = document.querySelector('input[name="answer6"]:checked');
				const answer7Field = document.querySelector('input[name="answer7"]:checked');
				const improvementsField = document.getElementsByName('improvements')[0];

				// 필드가 null인지 확인
				if (!studentIdField || !subjectIdField || !answer1Field || !answer2Field || !answer3Field || !answer4Field || !answer5Field || !answer6Field || !answer7Field || !improvementsField) {
				    alert('누락된 체크 항목이 있습니다.');
				    return;
				}

				// 값이 존재하는지 확인 후 정수로 변환
				const studentId = parseInt(studentIdField.value);
				const subjectId = parseInt(subjectIdField.value);
				const answer1 = parseInt(answer1Field.value);
				const answer2 = parseInt(answer2Field.value);
				const answer3 = parseInt(answer3Field.value);
				const answer4 = parseInt(answer4Field.value);
				const answer5 = parseInt(answer5Field.value);
				const answer6 = parseInt(answer6Field.value);
				const answer7 = parseInt(answer7Field.value);
				const improvements = improvementsField.value;

				// 값이 유효한지 확인
				if (isNaN(studentId) || isNaN(subjectId) || isNaN(answer1) || isNaN(answer2) || isNaN(answer3) || isNaN(answer4) || isNaN(answer5) || isNaN(answer6) || isNaN(answer7)) {
				    alert('올바르지 않은 입력값이 있습니다.');
				    return;
				}


		        const formData = {
		        	    studentId: studentId,
		        	    subjectId: subjectId,
		        	    answer1: answer1,
		        	    answer2: answer2,
		        	    answer3: answer3,
		        	    answer4: answer4,
		        	    answer5: answer5,
		        	    answer6: answer6,
		        	    answer7: answer7,
		        	    improvements: improvements
		            };
		        console.log(formData);

		        $.ajax({
		            type: 'POST',
		            url: $('#evaluationForm').attr('action'),
		            data: JSON.stringify(formData),
		            headers: {
		                "Content-Type": "application/json"
		            },
		            success : function(response) {
						if(response === "success"){
							alert('강의 평가 등록에 성공했습니다.');
							window.close();
						} else {
							alert(response);
						}
					},

		            error: function(xhr, status, error) {
		                alert('강의평가 전송에 실패했습니다. 오류: ' + error);
		            }
		        });
			});

		    // 부모 창 새로고침 함수
		    function refreshParent() {
		        window.opener.location.reload(); // 부모 창 새로고침
		    }

		    // 자식 창 닫힐 때 부모 창 새로고침
		    window.onunload = function() {
		        refreshParent();
		    };
		});
	</script>
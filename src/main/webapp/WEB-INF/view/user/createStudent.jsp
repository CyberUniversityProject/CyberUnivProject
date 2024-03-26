<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학생 등록</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
.btn--confirm {
	display: inline-block;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	cursor: pointer;
}

.btn--confirm:hover {
	background-color: #45a049;

}

 #preview-image {
            max-width: 100%;
            max-height: 200px;
            margin-top: 10px;
            display: none; /* 이미지 미리보기를 기본적으로 숨김 */
        }
        #preview-label {
            display: none; /* 미리보기 텍스트를 기본적으로 숨김 */
        }
</style>
</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>학생 추가페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarStaff.jsp"%>

			<div class="col-md-10">
				<h1 class="mt-5 mb-4">학생 등록</h1>
				<hr />
				<div class="row justify-content-center">
					<div class="col-md-7">
						<div class="card">
							<div class="card-body">
								<form action="/user/student" method="post" enctype="multipart/form-data">
									<div class="form-group">
										<label for="name">이름</label> <input type="text" name="name"
											id="name" value="박명수" class="form-control">
									</div>
									<div class="form-group">
										<label for="birthDate">생년월일</label> <input type="date"
											name="birthDate" value="1994-08-22" id="birthDate" class="form-control">
									</div>
									<div class="form-group">
										<label>성별</label><br>
										<div class="form-check form-check-inline">
											<input type="radio" value="남성" name="gender" id="male"
												class="form-check-input" checked> <label for="male"
												class="form-check-label">남성</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="radio" value="여성" name="gender" id="female"
												class="form-check-input"> <label for="female"
												class="form-check-label">여성</label>
										</div>
									</div>
									<div class="form-group">
										<label for="address">주소</label> <input type="text"
											name="address" id="address" value="부산광역시 서구" class="form-control">
									</div>
									<div class="form-group">
										<label for="tel">전화번호</label> <input type="text" value="010-1234-5678" name="tel"
											id="tel" class="form-control">
									</div>
									<div class="form-group">
										<label for="email">이메일</label> <input type="text" value="green0822@co.kr" name="email"
											id="email" class="form-control">
									</div>
									<div class="form-group">
										<label for="deptId">학과</label> <select class="form-control"
											id="deptId" name="deptId" >
											<option >학과를 선택하세요</option>
										</select>
									</div>
									<div class="form-group">
										<label for="entranceDate">입학일</label> <input type="date"
											name="entranceDate" id="entranceDate" value="2024-03-01" class="form-control">
									</div>
									<div class="form-group">
                                                                    <label for="file">증명사진 업로드</label>
                                                                    <input type="file" class="form-control" id="file" name="profilImage" accept=".jpg, .jpeg, .png, .gif" required>
                                                                    <img id="preview-image" src="#" alt="미리보기"> <!-- 이미지 미리보기를 위한 img 요소 -->
                                                                    <label id="preview-label" for="file">이미지 미리보기</label> <!-- 이미지 미리보기 텍스트 -->
                                                                </div>
									<button type="submit" class="btn--confirm">등록하기</button>
								</form>
							</div>
						</div>
					</div>
				</div>
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
		$(document).ready(
				function() {
					$('form').submit(function() {
						alert("등록되었습니다.");
						window.location.href = "/user/studentList";
					});

					// 학과 목록을 가져오는 함수
					function getDepts() {
						$.ajax({
							url : '/api/department/findAll',
							type : 'GET',
							dataType : 'json',
							success : function(data) {
								var select = $('#deptId');
								console.log(data);

								$.each(data, function(index, dept) {
									select.append($('<option>', {
										value : dept.id,
										text : dept.id + ' (' + dept.name + '/'
												+ dept.collegeName + ')'
									}));
								});
							},
							error : function(xhr, status, error) {
								console.error(xhr.responseText);
							}
						});
					}
					// 파일 입력 변경 이벤트 핸들러
                                $('#file').change(function() {
                                    // 파일이 선택되었는지 확인
                                    if (this.files && this.files[0]) {
                                        var reader = new FileReader();
                                        reader.onload = function(e) {
                                            $('#preview-image').attr('src', e.target.result); // 이미지 미리보기 업데이트
                                            $('#preview-image').show(); // 이미지 미리보기를 보이도록 설정
                                            $('#preview-label').hide(); // 미리보기 텍스트를 숨김
                                        }
                                        reader.readAsDataURL(this.files[0]); // 파일을 읽어서 데이터 URL로 변환하여 이미지 미리보기에 삽입
                                    }
                                });
					getDepts();
				});
	</script>



</body>
</html>

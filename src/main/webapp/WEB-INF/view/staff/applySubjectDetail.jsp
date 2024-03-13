<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>교수 강의신청 상세조회</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.pro-card {
	width: 100%;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 15px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.pro-card-title {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

.pro-card-content {
	font-size: 16px;
	margin-bottom: 5px;
}

.table th {
	width: 20%; /* 원하는 너비로 조정하세요 */
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>교수 강의신청 상세조회</h2>
		</div>
	</div>
	<div class="container mb-5">
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarStaff.jsp"%>
			<div class="col-md-10">
				<div class="container mt-5">
					<div class="h1">교수 강의신청 상세조회</div>
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					<div class="split--div"></div>
					<div class="flex-column align-items-center" style="width: 100%">
						<div class="document--layout">
							<div class="pro-card">
								<div class="card-body">
									<table class="table table-bordered">
										<tbody>
											
											<tr>
												<th scope="row">강의명</th>
												<td>${subject.subName}</td>
											</tr>
											<tr>
												<th scope="row">강의 시간</th>
												<td>${subject.subTime}</td>
											</tr>
											<tr>
												<th scope="row">전공/교양</th>
												<td>${subject.type}</td>
											</tr>
											<tr>
												<th scope="row">학점</th>
												<td>${subject.subGrade}</td>
											</tr>
											<tr>
												<th scope="row">정원</th>
												<td>${subject.capacity}</td>
											</tr>
											<tr>
												<th scope="row">승인 여부</th>
												<td>${subject.approval}</td>
											</tr>
											<tr>
												<th scope="row">사유</th>
												<td><textarea class="form-control" rows="3" id="reason"
														name="reason" style="height: 300px">${subject.reason}</textarea></td>
											</tr>
										</tbody>
									</table>
									<div class="button-container d-flex justify-content-center">

										<button type="button" class="btn btn-primary" id="approveBtn">승인하기</button>
										</form>
										&nbsp; &nbsp; &nbsp;
										<form action="" method="post"
											class="d-flex flex-column align-items-center">
											<input type="hidden" name="status" value="반려">
											<button type="submit" class="btn btn-warning"
												onclick="return confirm('해당 신청을 반려하시겠습니까?')">반려하기</button>
										</form>
									</div>


								</div>
							</div>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
    $(document).ready(function() {
        // 승인하기 버튼 클릭 시
        $('#approveBtn').click(function() {
            // 확인 알림창 표시
            let confirmation = confirm('해당 신청을 승인하시겠습니까?');
            if (confirmation) {
                // AJAX 요청을 보냅니다.
                $.ajax({
                    type : 'PUT', // 업데이트를 위한 HTTP 메서드는 PUT을 사용합니다.
                    url : '/api/applySub/updateApproval/${subject.id}?approval=Y', // 해당 엔드포인트의 URL을 입력합니다.
                    success : function(response) {
                        // 성공 알림창 표시
                        alert('신청이 승인되었습니다.');
                        // 페이지 이동
                        window.location.href = '/applySubject/list';
                    },
                    error : function(xhr, status, error) {
                        // 요청이 실패하면 콘솔에 오류를 출력합니다.
                        console.error(error);
                    }
                });
            }
        });
    });
</script>



</body>
</html>
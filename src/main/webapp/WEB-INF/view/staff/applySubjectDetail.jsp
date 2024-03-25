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
												<td>${subject.name}</td>
											</tr>
											<tr>
												<th scope="row">강의실</th>
												<td>${subject.roomId}</td>
											</tr>
											<tr>
												<th scope="row">학과명</th>
												<td>${subject.deptId}</td>
											</tr>
											<tr>
												<th scope="row">전공/교양</th>
												<td>${subject.type}</td>
											</tr>
											<tr>
												<th scope="row">강의시작시간</th>
												<td>${subject.startTime}</td>
											</tr>
											<tr>
												<th scope="row">강의시작시간</th>
												<td>${subject.endTime}</td>
											</tr>
											<tr>
												<th scope="row">강의개설연도</th>
												<td>${subject.subYear}</td>
											</tr>
											<tr>
												<th scope="row">강의개설학기</th>
												<td>${subject.semester}</td>
											</tr>
											<tr>
												<th scope="row">강의요일</th>
												<td>${subject.subDay}</td>
											</tr>
											<tr>
												<th scope="row">이수학점</th>
												<td>${subject.grades}</td>
											</tr>
											<tr>
												<th scope="row">총인원</th>
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
										
										&nbsp; &nbsp; &nbsp;
										<button type="button" class="btn btn-primary" id="rejectBtn">반려하기</button>
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
	                type : 'POST', // 강의를 추가하기 위해 POST 메서드를 사용합니다.
	                url : '/api/applySub/postSubject', // 강의를 추가하는 엔드포인트의 URL을 입력합니다.
	                contentType: 'application/json', // 데이터 형식을 JSON으로 설정합니다.
	                data: JSON.stringify({  // 강의 정보를 JSON 형식으로 변환하여 전송합니다.
	                    name: '${subject.name}',
	                    professorId: '${subject.professorId}',
	                    roomId: '${subject.roomId}',
	                    deptId: '${subject.deptId}',
	                    type: '${subject.type}',
	                    subYear: '${subject.subYear}',
	                    semester: '${subject.semester}',
	                    subDay: '${subject.subDay}',
	                    startTime: '${subject.startTime}',
	                    endTime: '${subject.endTime}',
	                    grades: '${subject.grades}',
	                    capacity: '${subject.capacity}'
	                }),
	                success : function(response) {
	                    // AJAX 요청을 보냅니다.
	                    $.ajax({
	                        type : 'PUT', // 업데이트를 위한 HTTP 메서드는 PUT을 사용합니다.
	                        url : '/api/applySub/updateApproval/${subject.id}?approval=승인', // 해당 엔드포인트의 URL을 입력합니다.
	                        success : function(response) {
	                            // 성공 알림창 표시
	                            alert('신청이 승인되었습니다.');
	                            window.location.href = '/applySubject/list';
	                        },
	                        error : function(xhr, status, error) {
	                            // 요청이 실패하면 콘솔에 오류를 출력합니다.
	                            console.error(error);
	                        }
	                    });
	                    // 성공 알림창 표시
	                   
	                    // 페이지 이동
	                    
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
<script>
    $(document).ready(function() {
        // 반려하기 버튼 클릭 시
        $('#rejectBtn').click(function(event) {
            // 폼 전송 방지
            event.preventDefault();
            // 확인 알림창 표시
            let confirmation = confirm('해당 신청을 반려하시겠습니까?');
            if (confirmation) {
                // AJAX 요청을 보냅니다.
                $.ajax({
                    type : 'PUT', // 업데이트를 위한 HTTP 메서드는 PUT을 사용합니다.
                    url : '/api/applySub/updateReason/${subject.id}', // 해당 엔드포인트의 URL을 입력합니다.
                    data: {
                        reason: $('#reason').val() // 입력된 사유 값을 가져와 전송합니다.
                    },
                    success : function(response) {
                        // 성공 알림창 표시
                        alert('신청이 반려되었습니다.');
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

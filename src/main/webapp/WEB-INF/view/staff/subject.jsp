<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>강의 등록</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>강의 등록페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<%@ include file="/WEB-INF/view/layout/sidebarRegistration.jsp"%>

			<div class="col-md-10">

				<div class="split--div"></div>
				<ul class="nav nav-tabs mt-5">
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("insert")) ? "active" : ""%>"
						href="/staff/subject?crud=insert">  강의등록
					</a></li>
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("update")) ? "active" : ""%>"
						href="/staff/subject?crud=update">
							강의수정
					</a></li>
					<li class="nav-item"><a
						class="nav-link <%=(request.getParameter("crud") != null && request.getParameter("crud").equals("delete")) ? "active" : ""%>"
						href="/staff/subject?crud=delete"> 
							강의삭제
					</a></li>
				</ul>




				<!-- 강의 입력 -->
				<c:if test="${crud.equals(\"insert\")}">
					<div class="card mt-3">
						<div class="card-header">✏ 강의 입력</div>
						<div class="card-body">
							<form action="/staff/subject" method="post"
								class="form--container">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="name">강의명</label> <input type="text"
												class="form-control" id="name" name="name"
												placeholder="강의명을 입력하세요">
										</div>
										<div class="form-group">
											<label for="professorId">교수ID</label> <select
												class="form-control" id="professorId" name="professorId">
												<option value="">교수를 선택하세요</option>
											</select>
										</div>
										<div class="form-group">
											<label for="roomId">강의실</label> <select
												class="form-control" id="roomId" name="roomId">
												<option value="">강의실을 선택하세요</option>
											</select>
										</div>
										<div class="form-group">
											<label for="deptId">학과</label> <select
												class="form-control" id="deptId" name="deptId">
												<option value="">학과를 선택하세요</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>강의 구분</label>
											<div class="form-check">
												<input class="form-check-input" type="radio" id="major"
													name="type" value="전공"> <label
													class="form-check-label" for="major">전공</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="radio" id="culture"
													name="type" value="교양"> <label
													class="form-check-label" for="culture">교양</label>
											</div>
										</div>
										<div class="form-group">
											<label for="subYear">연도</label> <input type="text"
												class="form-control" id="subYear" name="subYear"
												placeholder="연도를 입력하세요">
										</div>
										<div class="form-group">
											<label for="semester">학기</label> <input type="text"
												class="form-control" id="semester" name="semester"
												placeholder="학기를 입력하세요">
										</div>
										<div class="form-group">
											<label for="subDay">요일</label> <select class="form-control"
												id="subDay" name="subDay">
												<option value="월">월</option>
												<option value="화">화</option>
												<option value="수">수</option>
												<option value="목">목</option>
												<option value="금">금</option>
											</select>
										</div>
										<div class="form-group">
											<label for="startTime">시작시간</label> <input type="text"
												class="form-control" id="startTime" name="startTime"
												placeholder="시작시간을 입력하세요">
										</div>
										<div class="form-group">
											<label for="endTime">종료시간</label> <input type="text"
												class="form-control" id="endTime" name="endTime"
												placeholder="종료시간을 입력하세요">
										</div>
										<div class="form-group">
											<label for="grades">학점</label> <input type="text"
												class="form-control" id="grades" name="grades"
												placeholder="학점을 입력하세요">
										</div>
										<div class="form-group">
											<label for="capacity">정원</label> <input type="text"
												class="form-control" id="capacity" name="capacity"
												placeholder="정원을 입력하세요">
										</div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary" id='submit'>입력</button>
							</form>
						</div>
					</div>
					<!-- 강의 테이블 -->
					<div class="card mt-3">
						<div class="card-header">📖 등록된 강의목록</div>
						<div class="card-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>강의명</th>
										<th>교수</th>
										<th>강의실</th>
										<th>학과ID</th>
										<th>구분</th>
										<th>연도</th>
										<th>학기</th>
										<th>시간</th>
										<th>이수학점</th>
										<th>정원</th>
										<th>신청인원</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="subject" items="${subjectList}">
										<tr>
											<td>${subject.id}</td>
											<td>${subject.name}</td>
											<td>${subject.professorId}</td>
											<td>${subject.roomId}</td>
											<td>${subject.deptId}</td>
											<td>${subject.type}</td>
											<td>${subject.subYear}</td>
											<td>${subject.semester}</td>
											<td><c:choose>
													<c:when test="${subject.startTime < 10}">
                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00
                        </c:when>
													<c:otherwise>
                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00
                        </c:otherwise>
												</c:choose></td>
											<td>${subject.grades}</td>
											<td>${subject.capacity}</td>
											<td>${subject.numOfStudent}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>




				<!-- 강의 수정 -->
				<c:if test="${crud.equals(\"update\")}">
					<div class="card mt-3">
						<div class="card-header">📑 강의 수정</div>
						<div class="card-body">
							<form action="/staff/subject" method="post"
								class="form--container">
								<input type="hidden" name="_method" value="put" />

								<div class="form-group">
									<label for="name">강의번호</label> <select name="id"
										class="form-control">
										<c:forEach var="subject" items="${subjectList}">
											<option value="${subject.id}">${subject.id} (${subject.name})</option>

										</c:forEach>
									</select> <label for="name">강의요일</label> <select name="subDay"
										class="form-control">
										<option value="월">월</option>
										<option value="화">화</option>
										<option value="수">수</option>
										<option value="목">목</option>
										<option value="금">금</option>
									</select><br>
								</div>
								<div class="form-row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="name">강의명</label> <input type="text"
												class="form-control" id="name" name="name"
												placeholder="강의명을 입력하세요">
										</div>
										<div class="form-group">
											<label for="roomId">강의실</label> <input type="text"
												class="form-control" id="roomId" name="roomId"
												placeholder="강의실을 입력하세요">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="startTime">변경 시작시간</label> <select
												name="startTime" class="form-control">
												<c:forEach var="time" begin="9" end="16">
													<option value="${time}">${time}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="endTime">변경 종료시간</label> <select name="endTime"
												class="form-control">
												<c:forEach var="time" begin="11" end="18">
													<option value="${time}">${time}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="capacity">정원</label> <input type="text"
												class="form-control" id="capacity" name="capacity"
												placeholder="정원 입력하세요">
										</div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary" id='update'>수정</button>
							</form>
						</div>
					</div>

					<div class="card mt-3">
						<div class="card-header">📖 등록된 강의목록</div>
						<div class="card-body">
							<div class="form--container">
								<table class="table--container table table-striped">
									<thead>
										<tr class="first--tr">
											<th>ID</th>
											<th>강의명</th>
											<th>교수</th>
											<th>강의실</th>
											<th>학과ID</th>
											<th>구분</th>
											<th>연도</th>
											<th>학기</th>
											<th>시간</th>
											<th>이수학점</th>
											<th>정원</th>
											<th>신청인원</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>
												<td>${subject.id}</td>
												<td>${subject.name}</td>
												<td>${subject.professorId}</td>
												<td>${subject.roomId}</td>
												<td>${subject.deptId}</td>
												<td>${subject.type}</td>
												<td>${subject.subYear}</td>
												<td>${subject.semester}</td>
												<td><c:choose>
														<c:when test="${subject.startTime < 10}">
                                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00
                                        </c:when>
														<c:otherwise>
                                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00
                                        </c:otherwise>
													</c:choose></td>
												<td>${subject.grades}</td>
												<td>${subject.capacity}</td>
												<td>${subject.numOfStudent}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>



				<!-- 강의 삭제 -->
				<c:if test="${crud.equals(\"delete\")}">
					<div class="card mt-3">
						<div class="card-header">🗑 강의 삭제</div>
						<div class="card-body">
							<span class="delete">삭제할 강의명을 클릭해주세요</span>
							<div class="form--container">
								<table class="table--container table table-striped">
									<thead>
										<tr class="first--tr">
											<th>ID</th>
											<th>강의명</th>
											<th>교수</th>
											<th>강의실</th>
											<th>학과ID</th>
											<th>구분</th>
											<th>연도</th>
											<th>학기</th>
											<th>시간</th>
											<th>이수학점</th>
											<th>정원</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>
												<td>${subject.id}</td>
												<td><a href="/staff/subjectDelete?id=${subject.id}">${subject.name}</a></td>
												<td>${subject.professorId}</td>
												<td>${subject.roomId}</td>
												<td>${subject.deptId}</td>
												<td>${subject.type}</td>
												<td>${subject.subYear}</td>
												<td>${subject.semester}</td>
												<td><c:choose>
														<c:when test="${subject.startTime < 10}">
                                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00
                                        </c:when>
														<c:otherwise>
                                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00
                                        </c:otherwise>
													</c:choose></td>
												<td>${subject.grades}</td>
												<td>${subject.capacity}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>

				<!-- 강의 조회 -->
				<c:if test="${crud.equals(\"select\")}">
					<div class="card mt-3">
						<div class="card-header">📖 등록된 강의목록</div>
						<div class="card-body">
							<div class="form--container">
								<table class="table--container table table-striped">
									<thead>
										<tr class="first--tr">
											<th>ID</th>
											<th>강의명</th>
											<th>교수</th>
											<th>강의실</th>
											<th>학과ID</th>
											<th>구분</th>
											<th>연도</th>
											<th>학기</th>
											<th>시간</th>
											<th>이수학점</th>
											<th>정원</th>
											<th>신청인원</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${subjectList}">
											<tr>
												<td>${subject.id}</td>
												<td>${subject.name}</td>
												<td>${subject.professorId}</td>
												<td>${subject.roomId}</td>
												<td>${subject.deptId}</td>
												<td>${subject.type}</td>
												<td>${subject.subYear}</td>
												<td>${subject.semester}</td>
												<td><c:choose>
														<c:when test="${subject.startTime < 10}">
                                            ${subject.subDay} 0${subject.startTime}:00-${subject.endTime}:00
                                        </c:when>
														<c:otherwise>
                                            ${subject.subDay} ${subject.startTime}:00-${subject.endTime}:00
                                        </c:otherwise>
													</c:choose></td>
												<td>${subject.grades}</td>
												<td>${subject.capacity}</td>
												<td>${subject.numOfStudent}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>

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
					// 교수 목록을 가져오는 함수
					function getProfessors() {
						$.ajax({
							url : '/api/professor/findAll',
							type : 'GET',
							dataType : 'json',
							success : function(data) {
								var select = $('#professorId');

								$.each(data, function(index, professor) {
									select.append($('<option>', {
										value : professor.id,
										text : professor.name + ' ('
												+ professor.deptName + ')'
									}));
								});
							},
							error : function(xhr, status, error) {
								console.error(xhr.responseText);
							}
						});
					}
					
					// 강의실 목록을 가져오는 함수
						function getRooms() {
						$.ajax({
							url : '/api/room/findAll',
							type : 'GET',
							dataType : 'json',
							success : function(data) {
								var select = $('#roomId');

								$.each(data, function(index, room) {
									select.append($('<option>', {
										value : room.id,
										text : room.id + ' ('
												+ room.name + ')'
									}));
								});
							},
							error : function(xhr, status, error) {
								console.error(xhr.responseText);
							}
						});
					}
					
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
										text : dept.id + ' (' + dept.name + '/' + dept.collegeName + ')'
									}));
								});
							},
							error : function(xhr, status, error) {
								console.error(xhr.responseText);
							}
						});
					}
					

					// 페이지 로드 시 교수 목록 가져오기
					getProfessors();
					getRooms();
					getDepts();
				});
	</script>



</body>
</html>

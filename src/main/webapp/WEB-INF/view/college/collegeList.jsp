<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>단과대학 리스트</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/cdr-list.css" rel="stylesheet">	
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>단과대학 리스트 페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<!-- 사이드바 메뉴 -->
	<%@ include file="/WEB-INF/view/layout/sidebarCdrList.jsp"%>

			<div class="col-md-10" style="text-align: center;">
				<h1 class="mt-5 mb-4">단과대학 리스트</h1>
				<!-- main 넣는 시작 부분 -->
				<div class="container">
					<table class="table table hover">
						<thead>
							<tr>
								<th scope="col">단과대학 번호</th>
								<th scope="col">단과대학명</th>
								<th scope="col">settings</th>
							</tr>
						</thead>
						
						<tbody>
						<c:forEach var="college" items="${collegeList}">
							<tr>
								<th>${college.id}</th> 
								<th>${college.name}</th>
								<th><a href="/college/collegeUpdate/${college.id}"><button type="button" style="border-color:#5FCF80;border-width:1px;font-size:15px;"class="btn btn-outline-update">수정</button></a>
								<a href="/college/delete/${college.id}"><button type="button" style="border-color:#A0307F;border-width:1px;font-size:15px;" class="btn btn-outline-delete">삭제</button></a></th>
								
							</tr>
						</c:forEach>
						</tbody>
					</table>
					
				</div>
				<!-- main 끝 -->
			
			</div>
			
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 세부 메뉴 + 메인 -->
<div class="container mb-5">
	<div class="row">
	<!-- 사이드바 메뉴 -->
    <aside class="sidebar col-md-2 mt-5">
      <div class="card">
        <div class="card-header">
          메뉴
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item p-3"><a style="color: black;" href="/professor/info">내 정보 조회</a></li>
          <li class="list-group-item p-3"><a style="color: black;" href="#">비밀번호 변경</a></li>
        </ul>
      </div>
    </aside>	

	<!-- 메인 div -->
	<main>
		<h1>내 정보 조회</h1>
		<div class="split--div"></div>
		<table border="1" class="input--table">
			<colgroup>
				<col class="col1">
				<col class="col2">
				<col class="col3">
				<col class="col4">
			</colgroup>
			<tr>
				<th>ID</th>
				<td>${professorInfo.id}</td>
				<th>소속</th>
				<td>${professorInfo.collegeName}&nbsp;${professorInfo.deptName}</td>
			</tr>
		</table>
		<table border="1" class="input--table">
			<colgroup>
				<col class="col1">
				<col class="col2">
				<col class="col3">
				<col class="col4">
			</colgroup>
			<tr>
				<th>성명</th>
				<td>${professorInfo.name}</td>
				<th>생년월일</th>
				<td>${professorInfo.birthDate}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${professorInfo.gender}</td>
				<th>주소</th>
				<td>${professorInfo.address}</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${professorInfo.tel}</td>
				<th>email</th>
				<td>${professorInfo.email}</td>
			</tr>
		</table>
		<button type="button" onclick="location.href='/professor/update'" class="btn btn-dark update--button">수정하기</button>
	</main>
	</div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

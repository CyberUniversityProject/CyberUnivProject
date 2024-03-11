<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
body > div{
	padding-top: 100px;
}
main {
	height: 500px;
}
.table {
	width: 1000px;
}
</style>

<!-- 세부 메뉴 + 메인 -->
<div class="d-flex justify-content-center align-items-start" style="min-width: 100em;">
	<!-- 세부 메뉴 div-->
	<div class="sub--menu">
		<div class="sub--menu--top">
			<h2>학사정보</h2>
		</div>
		<!-- 메뉴 -->
		<!-- 선택된 메뉴에 class="selected--menu" 추가해주세요 -->
		<div class="sub--menu--mid">
			<table class="sub--menu--table" border="1">
				<tr>
					<td><a href="/notice" class="selected--menu">공지사항</a></td>
				</tr>
				<tr>
					<td><a href="#">학사일정</a></td>				
				</tr>
				<!--  
				<c:if test="${principal.userRole.equals(\"staff\") }">
					<tr>
						<td><a href="/schedule/list"> 학사일정 등록</a></td>
					</tr>
				</c:if>
				-->
			</table>
		</div>
	</div>
	<!-- 메인 div -->
	<main>
		<h1>공지사항</h1>
		<div class="split--div"></div>
		
		<!-- 공지 검색 -->
		<form>
			<select class="input--box" name="type">
				<option value="title">제목</option>
				<option value="keyword">제목+내용</option>
			</select>
			<input type="text" name="keyword" class="input--box" placeholder="검색어를 입력하세요"> 
			<input type="submit" class="button" value="검색">
		</form>
		
		<table class="table">
			<tr class="first--tr">
				<td>번호</td>
				<td>말머리</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="notice" items="${noticeList}">
				<tr class="second--tr" onclick="location.href='/notice/read?id=${notice.id}';">
					<td>${notice.id}</td>
					<td>${notice.category}</td>
					<td>${notice.title}</td>
					<td>${notice.timeFormat()}</td>
					<td>${notice.views}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="paging--container">
		<!-- 페이징 버튼 들어갈 자리 -->
		</div>
	
</div>


<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
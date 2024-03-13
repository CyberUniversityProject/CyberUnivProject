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
				<c:if test="${principal.userRole.equals(\"staff\") }">
					<tr>
						<td><a href="/schedule/list"> 학사일정 등록</a></td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<!-- 메인 div -->
	<main>
		<h1>공지사항</h1>
		<div class="split--div"></div>
		
		<!-- 공지 수정 -->
		<div class="container">
			<form action="/notice/update" method="post">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="id" value="${notice.id}">
					<table class="table">
						<tr class="title">
							<td class="type">제목</td>
							<td>${notice.category} <input type="text" name="title" class="update--box" value="${notice.title}"></td>
						</tr>
						<tr class="content--container">
							<td class="type">내용</td>
							<td><textarea rows="20" cols="100" class="textarea" name="content">${notice.content}</textarea></td>
						</tr>
						
					</table>
				<div class="select--button">
					<input type="submit" value="수정" class="button">
				</div>
			</form>
		</div>

	
</div>


<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
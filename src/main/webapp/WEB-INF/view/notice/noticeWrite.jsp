<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
body > div{
	padding-top: 100px;
}
main {
	height: 1000px;
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
			
		<!-- 공지 등록 -->
			<div class="write--div">
				<form action="/notice/write" method="post" enctype="multipart/form-data">
					<div class="title--container">
						<div class="category">
							<select name="category" class="input--box">
								<option value="[일반]">[일반]</option>
								<option value="[학사]">[학사]</option>
								<option value="[장학]">[장학]</option>
							</select>
						</div>
						<div class="title">
							<input type="text" class="form-control form-control-sm" name="title" placeholder="제목을 입력하세요" required="required" style="width: 900px;">
						</div>
					</div>
					<div class="content--container">
						<textarea name="content" class="form-control" cols="100" rows="20" placeholder="내용을 입력하세요"></textarea>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="file" accept=".jpg, .jpeg, .png"> <label class="custom-file-label" for="customFile">Choose file</label>
					</div>
					<input type="submit" class="button" value="등록">
				</form>
			</div>
</div>


<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
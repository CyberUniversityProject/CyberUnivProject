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


</style>

<div class="container mb-5">
<div class="row">
	<div class="sub--menu">
		<div class="sub--menu--top">
			<h2>학사정보</h2>
		</div>
		<!--  aside -->
		<div class="sub--menu--mid">
			<table class="sub--menu--table" border="1">
				<tr>
					<td><a href="/notice">공지사항</a></td>
				</tr>
				<tr>
					<td><a href="/schedule" class="selected--menu">학사일정</a></td>
				</tr>
				<c:if test="${principal.userRole.equals(\"staff\") }">
				<tr>
					<td><a href="/schedule/list"> 학사일정 등록</a></td>
				</tr>
				</c:if>
			</table>
		</div>
		<!--  aside -->
	</div>

	<div class="container mt-5">
		<h1>학사일정</h1>
		<!-- todo row -> aside랑 본문 부분을 가로(row) 배치하기 -->
		<div class="row">
			<div class="col-md-12">
	            <hr>
	        </div>
		</div>	
		<div>
		<table  class="room--table">
			<tbody>
			<c:forEach var ="schedule" items ="${schedule}">
				<tr>
					<td class ="month" width ="100px;">${schedule.month}월</td>
					<td class = "line">${schedule.startDay}~${schedule.endDay}</td>
					<td class = "line" >${schedule.information}</td>
				</tr>
			</c:forEach>

			</tbody>
		</table>
		</div>
		</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>


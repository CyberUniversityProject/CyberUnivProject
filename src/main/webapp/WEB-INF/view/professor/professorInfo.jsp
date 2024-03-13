<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- ======= 상단 제목부분 ======= -->
<div class="breadcrumbs" data-aos="fade-in">
  <div class="container">
    <h2>마이페이지</h2>
  </div>
</div>
<!-- 상단 제목부분 끝 -->

<div class="container mt-5 mb-5">
  <div class="row">
    <!-- 사이드바 -->
    <aside class="sidebar col-lg-3">
      <div class="card">
        <div class="card-header">
          <h5 class="mb-0">메뉴</h5>
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item p-3">
            <a href="/professor/info" class="text-dark d-flex align-items-center">
              <i class="bi bi-person-lines-fill mr-2"></i> 내 정보 조회
            </a>
          </li>
          <li class="list-group-item p-3">
            <a href="/user/professorList" class="text-dark d-flex align-items-center">
              <i class="bi bi-person-lines-fill mr-2"></i> 비밀번호 변경
            </a>
          </li>
        </ul>
      </div>
    </aside>
    <!-- 사이드 바 끝 -->
    
    <!-- 메인 컨텐츠 -->
    <main class="col-lg-9" style="padding-top: 0;"> <!-- 여기에 스타일 추가 -->
      <div class="card">
        <div class="card-body">
          <h1 class="card-title">내 정보 조회</h1>
          <div class="split--div"></div>
          <table class="table table-bordered">
            <tr>
              <th>ID</th>
              <td>${professorInfo.id}</td>
              <th>소속</th>
              <td>${professorInfo.collegeName}&nbsp;${professorInfo.deptName}</td>
            </tr>
          </table>
          <table class="table table-bordered">
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
        </div>
      </div>
    </main>
    <!-- 메인 컨텐츠 끝 -->
  </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

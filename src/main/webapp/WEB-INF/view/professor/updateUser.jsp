<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <%@ include file="/WEB-INF/view/layout/professorMypageAsidebar.jsp"%>
    
    <!-- 메인 div -->
    <main class="col-lg-9">
      <div class="card">
        <div class="card-body">
          <h1 class="card-title">개인 정보 수정</h1>
          <div class="split--div" style="margin-bottom: 30px;"></div>
          <form action="/update" method="post" class="info--update--form">
          <input type="hidden" name="_method" value="put" />
            <div class="form-group row">
              <label for="address" class="col-sm-3 col-form-label">주소</label>
              <div class="col-sm-9">
                <input type="text" name="address" id="address" class="form-control" value="${professorInfo.address}">
              </div>
            </div>
            <div class="form-group row">
              <label for="tel" class="col-sm-3 col-form-label">전화번호</label>
              <div class="col-sm-9">
                <input type="text" name="tel" id="tel" class="form-control" value="${professorInfo.tel}">
              </div>
            </div>
            <div class="form-group row">
              <label for="email" class="col-sm-3 col-form-label">이메일</label>
              <div class="col-sm-9">
                <input type="text" name="email" id="email" class="form-control" value="${professorInfo.email}">
              </div>
            </div>
            <div class="form-group row">
              <label for="password" class="col-sm-3 col-form-label">비밀번호 확인</label>
              <div class="col-sm-9">
                <input type="password" name="password" class="form-control" id="password">
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-10 offset-sm-3">
                <button type="submit" class="btn btn-dark update--button">수정하기</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </main>
    <!-- 메인 div 끝 -->
  </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- 사이드바 메뉴 -->
<aside class="sidebar col-md-2 mt-5">
  <div class="card">
    <div class="card-header">
      <h5 class="mb-0">메뉴</h5>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item p-3">
        <a href="/user/studentList" class="text-dark d-flex align-items-center">
          <i class="bi bi-building mr-2"></i> 단과대 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professorList" class="text-dark d-flex align-items-center">
          <i class="bi bi-journal mr-2"></i> 학과 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/student" class="text-dark d-flex align-items-center">
          <i class="bi bi-door-open-fill mr-2"></i> 강의실 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professor" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 강의 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/staff" class="text-dark d-flex align-items-center">
          <i class="bi bi-currency-dollar mr-2"></i> 단대별 등록금 설정
        </a>
      </li>
      
    </ul>
  </div>
</aside>

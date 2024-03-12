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
          <i class="bi bi-person-lines-fill mr-2"></i> 학생명단 조회
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professorList" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-lines-fill mr-2"></i> 교수명단 조회
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/student" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 학생 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professor" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 교수 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/staff" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 직원 등록
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="#" class="text-dark d-flex align-items-center">
          <i class="bi bi-file-earmark-text mr-2"></i> 등록금 고지서 발송
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="#" class="text-dark d-flex align-items-center">
          <i class="bi bi-moon-stars-fill mr-2"></i> 휴학 처리
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="#" class="text-dark d-flex align-items-center">
          <i class="bi bi-calendar-plus-fill mr-2"></i> 수강신청 기간 설정
        </a>
      </li>
    </ul>
  </div>
</aside>

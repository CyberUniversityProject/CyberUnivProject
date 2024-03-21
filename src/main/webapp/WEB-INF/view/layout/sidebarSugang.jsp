<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 사이드바 메뉴 -->
<aside class="sidebar col-md-2 mt-5">
  <div class="card">
    <div class="card-header">
      <h5 class="mb-0">메뉴</h5>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item p-3">
        <a href="/sugang/timeTable" class="text-dark d-flex align-items-center">
          <i class="bi bi-calendar-check-fill mr-2"></i> 강의시간표 조회
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/sugang/pre/1" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 예비수강신청
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/sugang/preAppList?type=1" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 수강신청
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/sugang/list" class="text-dark d-flex align-items-center">
          <i class="bi bi-journal-text mr-2"></i> 수강신청내역 조회
        </a>
      </li>
    </ul>
  </div>
</aside>

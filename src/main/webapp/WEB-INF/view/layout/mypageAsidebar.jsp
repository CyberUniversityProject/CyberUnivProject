<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 사이드바 메뉴 -->
<aside class="sidebar col-md-2 mt-5">
    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">메뉴</h5>
        </div>
        <ul class="list-group list-group-flush">
            <c:choose>
                <c:when test="${principal.userRole.equals(\"student\")}">
                    <li class="list-group-item p-3">
                        <a href="/student/myInfo" class="text-dark d-flex align-items-center">
                            <i class="bi bi-person-check-fill mr-2"></i> 내 정보 조회
                        </a>
                    </li>
                </c:when>
                <c:when test="${principal.userRole.equals(\"professor\")}">
                    <li class="list-group-item p-3">
                        <a href="/professor/info" class="text-dark d-flex align-items-center">
                            <i class="bi bi-person-check-fill mr-2"></i> 내 정보 조회
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="list-group-item p-3">
                        <a href="/info/staff" class="text-dark d-flex align-items-center">
                            <i class="bi bi-person-check-fill mr-2"></i> 내 정보 조회
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="list-group-item p-3">
                <a href="/password" class="text-dark d-flex align-items-center">
                    <i class="bi bi-shield-lock-fill mr-2"></i> 비밀번호 변경
                </a>
            </li>
            <c:if test="${principal.userRole.equals(\"student\")}">
                <li class="list-group-item p-3">
                    <a href="/student/leaveOfAbsence" class="text-dark d-flex align-items-center">
                        <i class="bi bi-calendar-x-fill mr-2"></i> 휴학 신청
                    </a>
                </li>
                <li class="list-group-item p-3">
                    <a href="/student/leaveOfAbsenceList" class="text-dark d-flex align-items-center">
                        <i class="bi bi-calendar-check-fill mr-2"></i> 휴학 내역 조회
                    </a>
                </li>
                <li class="list-group-item p-3">
                    <a href="/student/tuition" class="text-dark d-flex align-items-center">
                        <i class="bi bi-currency-dollar mr-2"></i> 등록금 내역 조회
                    </a>
                </li>
                <li class="list-group-item p-3">
                    <a href="/tuition/payment" class="text-dark d-flex align-items-center">
                        <i class="bi bi-credit-card-fill mr-2"></i> 등록금 납부 고지서
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</aside>

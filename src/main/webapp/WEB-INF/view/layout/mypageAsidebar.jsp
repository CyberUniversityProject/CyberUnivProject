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
                             내 정보 조회
                        </a>
                    </li>
                </c:when>
                <c:when test="${principal.userRole.equals(\"professor\")}">
                    <li class="list-group-item p-3">
                        <a href="/professor/info" class="text-dark d-flex align-items-center">
                             내 정보 조회
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="list-group-item p-3">
                        <a href="/info/staff" class="text-dark d-flex align-items-center">
                            내 정보 조회
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="list-group-item p-3">
                <a href="/password" class="text-dark d-flex align-items-center">
                    비밀번호 변경
                </a>
            </li>
            <c:if test="${principal.userRole.equals(\"student\")}">
                <li class="list-group-item p-3">
                    <a href="/student/leaveOfAbsence" class="text-dark d-flex align-items-center">
                        휴학 신청
                    </a>
                </li>
                <li class="list-group-item p-3">
                    <a href="/student/leaveOfAbsenceList" class="text-dark d-flex align-items-center">
                       휴학 내역 조회
                    </a>
                </li>
                <li class="list-group-item p-3">
                    <a href="/student/tuition" class="text-dark d-flex align-items-center">
                        등록금 내역 조회
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</aside>

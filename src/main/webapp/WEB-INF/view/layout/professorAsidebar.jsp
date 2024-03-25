<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 사이드바 메뉴 -->
    <aside class="sidebar col-md-2 mt-5">
				<div class="card">
					<div class="card-header">
						<h5 class="mb-0">메뉴</h5>
					</div>
					<ul class="list-group list-group-flush">
					<c:if test="${principal.userRole.equals(\"professor\") }">
						<li class="list-group-item p-3"><a href="/subject/list/1"
							class="text-dark d-flex align-items-center"> <i
								class="bi bi-person-lines-fill mr-2"></i> 전체 강의 목록
						</a></li>
						</c:if>
						<c:if test="${principal.userRole.equals(\"professor\") }">
						<li class="list-group-item p-3"><a href="/professor/mysub"
							class="text-dark d-flex align-items-center"> <i
								class="bi bi-person-lines-fill mr-2"></i> 내 강의 목록
						</a></li>
						</c:if>
						<c:if test="${principal.userRole.equals(\"professor\") }">
						<li class="list-group-item p-3"><a href="/professor/readevaluation"
							class="text-dark d-flex align-items-center"> <i
								class="bi bi-person-plus-fill mr-2"></i> 내 강의 평가
						</a></li>
						</c:if>
						<c:if test="${principal.userRole.equals(\"professor\") }">
						<li class="list-group-item p-3"><a href="/professor/apply"
							class="text-dark d-flex align-items-center"> <i
								class="bi bi-person-plus-fill mr-2"></i> 강의 신청
						</a></li>
						</c:if>
						<c:if test="${principal.userRole.equals(\"professor\") }">
						<li class="list-group-item p-3"><a href="/professor/update-list"
							class="text-dark d-flex align-items-center"> <i
								class="bi bi-person-plus-fill mr-2"></i> 신청 강의 목록
						</a></li>
						</c:if>
					</ul>
				</div>
			</aside>
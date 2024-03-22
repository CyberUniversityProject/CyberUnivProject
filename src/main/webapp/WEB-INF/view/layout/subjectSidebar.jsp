<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 사이드바 메뉴 -->
<aside class="sidebar col-md-2 mt-5">
	<div class="card">
		<div class="card-header">
			<h5 class="mb-0">메뉴</h5>
		</div>
		<ul class="list-group list-group-flush">
			<c:choose>
				<c:when test="${principal.userRole.equals(\"student\")}">
					<li class="list-group-item p-3"><a href="/user/studentList"
						class="text-dark d-flex align-items-center"> <i
							class="bi bi-person-lines-fill mr-2"></i> 전체 강의조회
					</a></li>
				</c:when>

				<c:when test="${principal.userRole.equals(\"professor\")}">
					<li class="list-group-item p-3"><a href="/user/studentList"
						class="text-dark d-flex align-items-center"> <i
							class="bi bi-person-lines-fill mr-2"></i> 전체 강의조회
					</a></li>
					<li class="list-group-item p-3"><a href="/professor/mysub"
						class="text-dark d-flex align-items-center"> <i
							class="bi bi-person-lines-fill mr-2"></i> 내 강의 조회
					</a></li>
					<li class="list-group-item p-3"><a
						href="/professor/readevaluation"
						class="text-dark d-flex align-items-center"> <i
							class="bi bi-person-plus-fill mr-2"></i> 내 강의 평가
					</a></li>
					<li class="list-group-item p-3"><a href="/professor/apply"
						class="text-dark d-flex align-items-center"> <i
							class="bi bi-person-plus-fill mr-2"></i> 강의 신청
					</a></li>

				</c:when>
			</c:choose>
		</ul>
	</div>
</aside>

<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<style>
body > div{
	padding-top: 100px;
}
main {
	height: 500px;
}
.table {
	width: 1000px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>공지사항</h2>
		</div>
	</div>
	<!-- 상단 제목부분 끝 -->

<!-- 세부 메뉴 + 메인 -->
<div class="container mb-5">
	<div class="row">
		<!-- 사이드바 메뉴 -->
		<aside class="sidebar col-md-2 mt-5">
			<div class="card">
					<div class="card-header">
						<h5 class="mb-0">학사정보</h5>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a href="/notice" class="text-black"><i
								class="fas fa-user mr-2"></i> 공지사항</a></li>
						<li class="list-group-item p-3"><a href="/schedule" class="text-black"><i
								class="fas fa-lock mr-2"></i> 학사일정</a></li>
						<c:if test="${principal.userRole.equals(\"staff\") }">
						<li class="list-group-item p-3"><a href="/schedule/list" class="text-black"><i
								class="fas fa-lock mr-2"></i> 학사일정 등록</a></li>
						</c:if>
					</ul>
				</div>
		</aside>
	<!-- 메인 div -->
	
	<div class="col-md-10">
	<h1>공지사항</h1>
		<div class="split--div"></div>


		<!-- 공지 검색 -->
		<c:if test="${crud.equals(\"selectKeyword\")}">
		<form action="/notice/search" method="get" class="form--container">
				<select class="input--box" name="type">
					<option value="title">제목</option>
					<option value="keyword">제목+내용</option>
				</select>
				<input type="text" name="keyword" class="input--box" placeholder="검색어를 입력하세요"> 
				<input type="submit" class="button" value="검색">
			</form>
			
			<table class="table table-striped">
    <c:choose>
        <c:when test="${fn:length(noticeList) != 0}">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">말머리</th>
                    <th scope="col" style="width: 450px">제목</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="notice" items="${noticeList}">
                    <tr class="table-row" onclick="location.href='/notice/read?id=${notice.id}';">
                        <td>${notice.id}</td>
                        <td>${notice.category}</td>
                        <td>${notice.title}</td>
                        <td>${notice.timeFormat()}</td>
                        <td>${notice.views}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5">
                    <h5>해당 키워드로 작성된 공지글이 없습니다.</h5>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>

			<div class="pagination">
    <ul class="pagination">
        <c:forEach var="index" begin="1" end="${listCount}">
            <c:choose>
                <c:when test="${keyword != null}">
                    <li class="page-item"><a class="page-link" href="/notice/search/${index}?keyword=${keyword}">${index}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/notice/list/${index}">${index}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>

			
		</c:if>


		<!-- 공지 조회 -->
		<c:if test="${crud.equals(\"select\")}">
			<form action="/notice/search" method="get" class="form--container">
				<select class="input--box" name="type">
					<option value="title">제목</option>
					<option value="keyword">제목+내용</option>
				</select>
				<input type="text" name="keyword" class="input--box" placeholder="검색어를 입력하세요"> 
				<input type="submit" class="button" value="검색">
			</form>
			<c:if test="${principal.userRole.equals('staff')}">
			<div class="text-end">
        <a href="/notice?crud=write" class="btn btn-primary">등록</a>
        </div>
    </c:if>
			<table class="table table-hover">
    <c:choose>
        <c:when test="${fn:length(noticeList) != 0}">
            <thead>
                <tr class="first--tr">
                    <th scope="col">번호</th>
                    <th scope="col">말머리</th>
                    <th scope="col" style="width: 450px">제목</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="notice" items="${noticeList}">
                    <tr class="second--tr" onclick="location.href='/notice/read?id=${notice.id}';">
                        <td>${notice.id}</td>
                        <td>${notice.category}</td>
                        <td>${notice.title}</td>
                        <td>${notice.timeFormat()}</td>
                        <td>${notice.views}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:when>
        <c:otherwise>
            <tbody>
                <tr>
                    <td colspan="5" class="text-center">공지사항이 없습니다. 작성해주세요.</td>
                </tr>
            </tbody>
        </c:otherwise>
    </c:choose>
</table>

			
			<div class="pagination">
    <ul class="pagination">
        <c:forEach var="index" begin="1" end="${listCount}">
            <c:choose>
                <c:when test="${keyword != null}">
                    <li class="page-item"><a class="page-link" href="/notice/search/${index}?keyword=${keyword}">${index}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/notice/list/${index}">${index}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
      
    </ul>
      
</div>


		</c:if>



		<!-- 공지 상세 조회 -->
		<c:if test="${crud.equals(\"read\")}">
			<div class="container">
				<table class="table">
					<tr class="title">
						<td class="type">제목</td>
						<td>${notice.category} ${notice.title}</td>
					</tr>
					<tr class="content--container">
						<td class="type">내용</td>
						<td>${notice.content}<br><br><img alt="" src="${notice.setUpImage()}" width="600" height="800" onerror="this.style.display='none'"></td>
					</tr>
				</table>

				<div class="btn-group">
    <a href="/notice" class="btn btn-primary">목록</a>
    <c:if test="${principal.userRole.equals('staff')}">
        <a href="/notice/update?id=${notice.id}" class="btn btn-secondary">수정</a>
        <a href="/notice/delete?id=${notice.id}" class="btn btn-danger">삭제</a>
    </c:if>
</div>

			</div>
		</c:if>


		<!-- 공지 수정 -->
		<c:if test="${crud.equals(\"update\")}">
		<div class="container">
			<form action="/notice/update" method="post">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="id" value="${notice.id}">
					<table class="table">
						<tr class="title">
							<td class="type">제목</td>
							<td>${notice.category} <input type="text" name="title" class="update--box" value="${notice.title}"></td>
						</tr>
						<tr class="content--container">
							<td class="type">내용</td>
							<td><textarea rows="20" cols="100" class="textarea" name="content">${notice.content}</textarea></td>
						</tr>
						
					</table>
				<div class="select--button">
					<input type="submit" value="수정" class="btn btn-danger">
				</div>
			</form>
		</div>
		</c:if>


		<!-- 공지 등록 -->
		<c:if test="${crud.equals(\"write\")}">
			<div class="write--div">
				<form action="/notice/write" method="post" enctype="multipart/form-data">
					<div class="title--container">
						<div class="category">
							<select name="category" class="input--box">
								<option value="[일반]">[일반]</option>
								<option value="[학사]">[학사]</option>
								<option value="[장학]">[장학]</option>
							</select>
						</div>
						<div class="title">
							<input type="text" class="form-control form-control-sm" name="title" placeholder="제목을 입력하세요" required="required" style="width: 900px;">
						</div>
					</div>
					<div class="content--container">
						<textarea name="content" class="form-control" cols="100" rows="20" placeholder="내용을 입력하세요"></textarea>
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="customFile" name="file" accept=".jpg, .jpeg, .png"> <label class="custom-file-label" for="customFile"></label>
					</div>
					<div class="text-start mt-3">
					<a href="/notice" class="btn btn-warning">공지사항 목록</a> 
					<input type="submit" class="btn btn-primary" value="등록">
					</div>
					
				</form>
				<script>
					$(".custom-file-input").on(
							"change",
							function() {
								var fileName = $(this).val().split("\\").pop();
								$(this).siblings(".custom-file-label")
										.addClass("selected").html(fileName);
							});
				</script>
			</div>
		</c:if>
		</div>
</div>
</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
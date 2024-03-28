<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>커뮤니티</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .community-list-heading {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 20px;
    }

    .community-list-table {
      margin-bottom: 50px;
    }

    .community-list-table th,
    .community-list-table td {
      vertical-align: middle;
    }

    .community-list-table th {
      font-size: 18px;
      font-weight: bold;
    }

    .community-list-table td {
      font-size: 16px;
    }

    .community-list-table td:nth-child(3) {
      width: 150px;
    }

    .community-list-table td:nth-child(4) {
      width: 200px;
    }

    .community-list-table td a {
      color: inherit;
      text-decoration: none;
    }

    .community-list-table td a:hover {
      color: #007bff;
    }

    .write-button {
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
  <%@ include file="/WEB-INF/view/layout/header.jsp"%>
   <!-- ======= 상단 제목부분 ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>커뮤니티</h2>
      </div>
    </div>
    <!-- 상단 제목부분 끝 -->

  <div class="container mt-5">
    <div class="row">
      <div class="col-lg-12">
        <a href="/community/write" class="btn btn-primary write-button">글쓰기</a>
        <c:if test="${empty communityList}">
          <p>게시글이 존재하지 않습니다.</p>
        </c:if>
        <c:if test="${not empty communityList}">
          <table class="table table-bordered community-list-table">
            <thead class="thead-dark">
              <tr>
                <th style="width: 5%;">No</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${communityList}" var="community">
                <tr>
                  <td>${community.id}</td>
                  <td>
                    <a href="/community/${community.id}">
                      ${community.title} [<span id="commentCount_${community.id}">로딩 중...</span>] <!-- 댓글 수를 표시할 위치 -->
                    </a>
                  </td>
                  <td>${community.userName}</td>
                  <td>${community.appDateFormat()}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
    </div>
  </div>
  
  <div style="display: flex; justify-content: center;">
    <c:if test="${totalPages > 1}">
        <nav aria-label="Page navigation" style="text-align: center;">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=1&size=${size}" aria-label="처음">
                            <span aria-hidden="true">처음&laquo;</span>
                            <span class="sr-only">처음</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}&size=${size}" aria-label="이전">
                            <span aria-hidden="true">이전&lt;</span>
                            <span class="sr-only">이전</span>
                        </a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${totalPages <= 5}">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active"><span class="page-link">${i}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${currentPage le 3}">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:when test="${currentPage ge totalPages - 2}">
                                <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" begin="${currentPage - 2}" end="${currentPage + 2}">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${size}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&size=${size}" aria-label="다음">
                            <span aria-hidden="true">다음&gt;</span>
                            <span class="sr-only">다음</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${totalPages}&size=${size}" aria-label="마지막">
                            <span aria-hidden="true">마지막&raquo;</span>
                            <span class="sr-only">마지막</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>

					


  <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <script>
    $(document).ready(function() {
      // 각 게시글의 댓글 개수를 비동기적으로 가져와서 표시합니다.
      $('.community-list-table tbody tr').each(function() {
        var communityId = $(this).find('td:first-child').text(); // 게시글의 ID를 가져옵니다.
      
        var commentCountSpan = $('#commentCount_' + communityId); // 댓글 수를 표시할 위치를 가져옵니다.

        // AJAX를 사용하여 댓글 개수를 가져옵니다.
        $.ajax({
          type: "GET",
          url: "/api/comment/count/" + communityId,
          success: function(response) {
            commentCountSpan.text(response); // 댓글 수를 표시합니다.
          },
          error: function(xhr, status, error) {
            console.error("Error:", error);
            commentCountSpan.text("불러오기 실패");
          }
        });
      });
    });
  </script>
</body>
</html>

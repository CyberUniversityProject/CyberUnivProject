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
                <th style="width: 5%;">ID</th>
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


  <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <script>
    $(document).ready(function() {
      // 각 게시글의 댓글 개수를 비동기적으로 가져와서 표시합니다.
      $('.community-list-table tbody tr').each(function() {
        var communityId = $(this).find('td:first-child').text(); // 게시글의 ID를 가져옵니다.
        console.log(communityId);
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>커뮤니티</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
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

    /* 스타일 수정 */
    .form-group {
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

<div class="container mt-5 mb-5  border border-1 rounded-2">
    <div class="row">
        <div class="col-lg-12 mt-5 mb-5">
            <form action="/community/create" method="post">
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="userName">작성자</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${principal.name}" readonly>

                </div>
                <div class="form-group">
                    <label for="content">내용</label>
                    <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">등록</button>
            </form>
        </div>
    </div>
</div>


  <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트 스크립트 추가 -->
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

  <script>
    $(document).ready(function() {
      $('#content').summernote({
        tabsize: 2,
        height: 300,
        toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'underline', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['table', ['table']],
          ['insert', ['link', 'picture', 'video']],
          ['view', ['fullscreen', 'codeview', 'help']]
        ]
      });
    });
  </script>
</body>
</html>

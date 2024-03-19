<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>게시글 상세보기</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .breadcrumbs {
      background-color: #f9f9f9;
      padding: 20px 0;
    }
    .breadcrumbs h2 {
      margin-bottom: 0;
      color: #333;
    }
    .container {
      margin-top: 20px;
    }
    .list-group-item {
      border: none;
    }
    .list-group-item:nth-child(even) {
      background-color: #f9f9f9;
    }
    
      .btn--confirm {
        display: inline-block;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        text-align: center;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
    }

    .btn--confirm:hover {
        background-color: #45a049;
    }
  </style>
</head>
<body>
  <%@ include file="/WEB-INF/view/layout/header.jsp"%>
   <!-- ======= 상단 제목부분 ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>게시글 상세보기</h2>
      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <h2>${community.title}</h2>
        <p><strong>작성자:</strong> ${community.userName}</p>
        <hr>
        <div style="height: 500px;">
        <p>${community.content}</p>
        </div>
        
        <hr>
        <a href="/community/list"><button class="btn--confirm">목록으로</button></a>
        <hr/>
        <h4>댓글</h4>
        <c:if test="${not empty community.comments}">
          <ul class="list-group">
            <c:forEach items="${community.comments}" var="comment">
            <h3>${comment }</h3>
              <li class="list-group-item">${comment.content}</li>
            </c:forEach>
          </ul>
        </c:if>
        <c:if test="${empty community.comments}">
          <p>댓글이 없습니다.</p>
        </c:if>
      </div>
    </div>
  </div>

  <%@ include file="/WEB-INF/view/layout/footer.jsp"%>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

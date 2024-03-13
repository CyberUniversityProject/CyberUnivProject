<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/layout/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>강의 등록</title>
</head>
<body>

 <!-- ======= 상단 제목부분 ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>강의 신청</h2>

      </div>
    </div>
    <!-- 상단 제목부분 끝 -->
    
    
    <!-- 사이드바 메뉴 -->
    <div class="container mb-5"> <!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
  <div class="row">
<aside class="sidebar col-md-2 mt-5">
  <div class="card">
    <div class="card-header">
      <h5 class="mb-0">메뉴</h5>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item p-3">
        <a href="/user/studentList" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-lines-fill mr-2"></i> 전체 강의 조회
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professorList" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-lines-fill mr-2"></i> 내 강의 조회
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/student" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 내 강의 평가
        </a>
      </li>
      <li class="list-group-item p-3">
        <a href="/user/professor" class="text-dark d-flex align-items-center">
          <i class="bi bi-person-plus-fill mr-2"></i> 강의 등록
        </a>
      </li>
     
    </ul>
  </div>
</aside>
    <div class="col-md-10 p-5">
	<form action="/professor/apply" method="post" class="form--container">
    <div class="card">
        <div class="card-body">
            <div class="mb-3">
                <label for="subName" class="form-label">강의명</label>
                <input type="text" class="form-control" id="subName" name="subName" placeholder="강의명을 입력하세요">
            </div>
            <div class="mb-3">
                <label for="proName" class="form-label">교수이름</label>
                <input type="text" class="form-control" id="proName" name="proName" placeholder="교수이름을 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">강의 종류</label>
                <div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" id="major" value="전공" checked>
                        <label class="form-check-label" for="major">전공</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" id="culture" value="교양">
                        <label class="form-check-label" for="culture">교양</label>
                    </div>
                </div>
            </div>
            <div class="mb-3">
                <label for="time" class="form-label">강의 시간</label>
                <input type="text" class="form-control" id="time" name="time" placeholder="강의 시간을 입력하세요">
            </div>
            <div class="mb-3">
                <label for="subGrade" class="form-label">학점</label>
                <input type="text" class="form-control" id="subGrade" name="subGrade" placeholder="학점을 입력하세요">
            </div>
            <div class="mb-3">
                <label for="capacity" class="form-label">정원</label>
                <input type="text" class="form-control" id="capacity" name="capacity" placeholder="정원을 입력하세요">
            </div>
            <button type="submit" class="btn btn-primary">입력</button>
        </div>
    </div>
</form>
</div>
</div>
</div>

<script>
    function validateForm() {
        var subName = document.getElementById("subName").value.trim();
        var proName = document.getElementById("proName").value.trim();
        var time = document.getElementById("time").value.trim();
        var subGrade = document.getElementById("subGrade").value.trim();
        var capacity = document.getElementById("capacity").value.trim();
        var majorChecked = document.getElementById("major").checked;
        var cultureChecked = document.getElementById("culture").checked;
        var errorMessage = "";
        var numericExpression = /^[0-9]+$/;
        
        if (subName === "") {
            errorMessage += "강의명을 입력하세요.\n";
        }
        
        if (proName === "") {
            errorMessage += "교수이름을 입력하세요.\n";
        }
       
        if (!numericExpression.test(time)) {
            errorMessage += "강의 시간은 숫자만 입력하세요.\n";
        }
        if (time === "") {
            errorMessage += "강의 시간을 입력하세요.\n";
        }
        
        if (!numericExpression.test(subGrade)) {
            errorMessage += "학점은 숫자만 입력하세요.\n";
        }
        if (subGrade === "") {
            errorMessage += "학점을 입력하세요.\n";
        }
        
        if (!numericExpression.test(capacity)) {
            errorMessage += "정원은 숫자만 입력하세요.\n";
        }
        if (capacity === "") {
            errorMessage += "정원을 입력하세요.\n";
        }
        
        if (!majorChecked && !cultureChecked) {
            errorMessage += "전공 또는 교양을 선택하세요.\n";
        }
        
        if (errorMessage !== "") {
            alert(errorMessage);
            return false;
        }

        return true;
    }
</script>
		
</body>
</html>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
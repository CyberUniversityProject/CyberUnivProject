<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/layout/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<style>
.subject--form {
	margin-top: 100px;
}


</style>
<meta charset="UTF-8">
<title>강의 등록</title>
</head>
<body>
	<form action="/professor/apply" method="post" class="form--container">		
			<div class="subject--form">
			<input type="text" class="input--box" id="subName" name="subName" placeholder="강의명을 입력하세요"><br>
			<input type="text" class="input--box" id="proName" name="proName" placeholder="교수이름을 입력하세요"><br>
				<label for="major">전공</label> 
				<input type="radio" id="major" name="type" value="전공">
				<label for="culture">교양</label> 
				<input type="radio" id="culture" name="type" value="교양"><br>					
				<input type="text" class="input--box" id="time" name="time" placeholder="강의 시간을 입력하세요"><br>
				<input type="text" class="input--box" id="subGrade" name="subGrade" placeholder="학점을 입력하세요"><br>
				<input type="text" class="input--box" name="capacity" name="capacity" placeholder="정원 입력하세요"><br>
			<input type="submit" class="button" value="입력">
			</div>
		</form>
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
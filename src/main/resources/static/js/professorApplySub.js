function isValidYearFormat(year) {
	// 정규 표현식을 사용하여 패턴 검사
	var pattern = /^(19|20)\d{2}$/; // 1900년 이후부터 2099년까지의 패턴
	return pattern.test(year);
}

function validateForm() {
	var name = document.getElementById("name").value.trim();
	var startTime = document.getElementById("startTime").value.trim();
	var endTime = document.getElementById("endTime").value.trim();
	var subYear = document.getElementById("subYear").value.trim();
	var grades = document.getElementById("grades").value.trim();
	var capacity = document.getElementById("capacity").value.trim();
	var errorMessage = "";

	// 강의명이 비어 있는지 확인
	if (name === "") {
		errorMessage += "강의명을 입력하세요.\n";
	}

	// 강의 시작 시간과 종료 시간이 비어 있는지 확인
	 if (isNaN(startTime) || isNaN(endTime)) {
        errorMessage += "강의 시작 시간과 종료 시간을 모두 입력하세요.\n";
    } else {
        // 시간 범위가 올바른지 확인
        if (startTime < 0 || startTime > 24 || endTime < 0 || endTime > 24) {
            errorMessage += "올바른 시간 범위(0부터 24까지)로 입력하세요.\n";
        }
    }

	if (subYear === "") {
		errorMessage += "개설 년도를 입력하세요.\n";
	} else {
		// 개설 년도가 패턴에 맞는지 확인
		if (!isValidYearFormat(subYear)) {
			errorMessage += "올바른 년도 형식(2024)으로 입력하세요.\n";
		}
	}


	if (grades === "") {
		errorMessage += "이수 학점을 입력하세요.\n";
	}

	if (capacity === "") {
		errorMessage += "강의 총 정원을 입력하세요.\n";
	}

	// 에러 메시지가 있는 경우 경고창 표시 및 폼 제출 방지
	if (errorMessage !== "") {
		alert(errorMessage);
		return false;
	}

	return true;
}
function validateForm() {

    let absent = parseInt(document.getElementById("absent").value);
    let lateness = parseInt(document.getElementById("lateness").value);
    let homework = parseInt(document.getElementById("homework").value);
    let midExam = parseInt(document.getElementById("midExam").value);
    let finalExam = parseInt(document.getElementById("finalExam").value);
    let convertedMark = parseInt(document.getElementById("convertedMark").value);
    let sumScore = midExam + finalExam + homework;
    let errorMessage = "";

    if (absent < 0 || absent > 72 || isNaN(absent)) {
        errorMessage += "결석 횟수를 0부터 72까지 입력하세요.\n";
    } else if (absent >= 5) {
        grade = "F";
    }

    if (lateness < 0 || lateness > 72 || isNaN(lateness)) {
        errorMessage += "지각 횟수를 0부터 72까지 입력하세요.\n";
    }

    if (homework < 0 || homework > 100 || isNaN(homework)) {
        errorMessage += "과제 점수를 0부터 100까지 입력하세요.\n";
    }

    if (midExam < 0 || midExam > 100 || isNaN(midExam)) {
        errorMessage += "중간 고사 점수를 0부터 100까지 입력하세요.\n";
    }

    if (finalExam < 0 || finalExam > 100 || isNaN(finalExam)) {
        errorMessage += "기말 고사 점수를 0부터 100까지 입력하세요.\n";
    }

    if (convertedMark < 0 || convertedMark > 100 || isNaN(convertedMark)) {
        errorMessage += "환산 점수를 0부터 100까지 입력하세요.\n";
    }

    if (convertedMark !== sumScore) {
        errorMessage += "잘못된 환산 점수입니다. 중간 고사 점수와 기말 고사 점수의 합과 동일해야 합니다.\n";
    }

    if (errorMessage !== "") {
        alert(errorMessage);
        return false;
    }

    return true;
}
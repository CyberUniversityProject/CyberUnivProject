<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의계획서</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

body {
    font-family: 'Noto Sans KR', sans-serif;
}

.header--top {
    width: 100%;
    height: 40px;
    background-color: #031734;
}

.section {
    padding: 20px;
}

.form-control {
    font-size: 16px;
    resize: none;
}

.submit--button {
    margin-top: 20px;
    padding: 10px 15px;
    border: none;
    border-radius: 10px;
    color: white;
    background-color: #142845;
    cursor: pointer;
}
</style>
</head>
<body>
    <header>
        <div class="header--top"></div>
    </header>
    <div class="container">
        <div class="section">
            <h2>강의 계획서 수정</h2>
            <form action="/professor/syllabus/update/${syllabus.subjectId}" method="post">
                <input type="hidden" name="_method" value="put"/>
                <div class="form-group">
                    <label>강의 개요</label>
                    <textarea class="form-control" rows="5" name="overview" maxlength="255">${syllabus.overview}</textarea>
                </div>
                <div class="form-group">
                    <label>수업 목표</label>
                    <textarea class="form-control" rows="5" name="objective" maxlength="255">${syllabus.objective}</textarea>
                </div>
                <div class="form-group">
                    <label>교재</label>
                    <input type="text" class="form-control" name="textbook" maxlength="30" value="${syllabus.textbook}">
                </div>
                <div class="form-group">
                    <label>주별 계획</label>
                    <textarea class="form-control" rows="10" name="program">${syllabus.program}</textarea>
                </div>
                <button type="submit" class="btn btn-primary submit--button">제출</button>
            </form>
        </div>
    </div>
</body>
</html>

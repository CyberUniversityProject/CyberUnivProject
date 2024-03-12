<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Noto Sans KR', sans-serif;
}

body {
    background-color: #f8f9fa;
}

.header--top {
    height: 40px;
    background-color: #3ac162;
}

.section--header {
    margin-top: 30px;
    text-align: center;
}

.search--table {
    width: 400px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.search--table td {
    padding: 10px;
}

.submit--button {
    display: block;
    width: 200px;
    margin: 20px auto;
    padding: 10px 15px;
    border: none;
    border-radius: 10px;
    color: white;
    background-color: #3ac162;
    cursor: pointer;
}

.submit--button:hover {
    background-color: #0d1e3b;
}

.center {
    text-align: center;
}

</style>
</head>

<body>
    <header>
        <div class="header--top"></div>
    </header>
    <section>
        <div class="section--header">
            <h2 class="mb-5">아이디 찾기</h2>
            <br/>
        </div>
        <form action="/find/id" method="post">
            <table class="search--table">
                <tr>
                    <td><label for="name">이름</label></td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td><label for="email">이메일</label></td>
                    <td><input type="text" name="email" id="email"></td>
                </tr>
                <tr>
                    <td colspan="2" class="center">
                        <label for="student">학생</label>
                        <input type="radio" name="userRole" value="student" id="student">
                        <label for="professor">교수</label>
                        <input type="radio" name="userRole" value="professor" id="professor">
                        <label for="staff">직원</label>
                        <input type="radio" name="userRole" value="staff" id="staff">
                    </td>
                </tr>
            </table>
            <button type="submit" class="submit--button">아이디 찾기</button>
        </form>
    </section>
</body>
</html>

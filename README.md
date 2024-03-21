# 📖 학사관리 웹 서비스 CyberUniversity


<br>

## 프로젝트 소개

이 프로젝트는 5명의 개발자가 스프링부트(Spring Boot), MyBatis, JSP, 그리고 MySQL을 이용하여 개발된 웹 기반의 학사관리시스템입니다. 
학사 업무를 체계적으로 관리하고 학사 정보를 효율적으로 처리할 수 있도록 개발하였습니다. 
사용자 친화적인 UI와 다양한 기능을 제공하여 학사 관리 업무를 간편하게 수행할 수 있습니다. 


<br>

## 팀원 구성
![제목을-입력해주세요_-005](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/bd57bd12-a6fa-4998-b25d-331979c947b7)


<br>

## 개발환경
- 개발 툴 : Spring Tools 4 (4.21.1)
- Backend : JAVA 17, SpringBoot 3.1.8, MyBatis, JSP, MySQL 8.0.26, lombok, BCrypt HASH, Apache Tomcat : 9.0
- Frontend : bootstrap : 4.6.2, HTML5, CSS3, JavaScript : 1.16.1, JQuery : 3.6.4
- 버전/이슈관리 : GitHub, GitBash
- 협업 툴 : Discord, Notion, Slack
- 외부 API : 포트원 결제 API, 식단 API
- 커뮤니티 게시판 : Summernote editor

<br>

## 브랜치 전략
Git-flow 전략을 기반으로 main, develop 브랜치와 feature 보조 브랜치를 운용했습니다.
- main, develop, Feat 브랜치로 나누어 개발을 하였습니다.
- main 브랜치는 배포 단계에서만 사용하는 브랜치입니다.
- develop 브랜치는 개발 단계에서 git-flow의 master 역할을 하는 브랜치입니다.
- Feat 브랜치는 기능 단위로 독립적인 개발 환경을 위하여 사용하고 merge 후 각 브랜치를 삭제해주었습니다.

<br>

## ⚙ 의존성
```java
        implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation group: 'org.glassfish.web', name: 'jakarta.servlet.jsp.jstl', version: '2.0.0'
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'
	implementation 'com.mysql:mysql-connector-j'
	runtimeOnly 'com.h2database:h2'
	implementation 'org.springframework.security:spring-security-crypto'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
```
<br>

## 1️⃣ 프로젝트 구조

<details>
    <summary>⚡️ 구조 자세히 살펴보기</summary>
    
        📦src
         ┣ 📂main
         ┃ ┣ 📂generated
         ┃ ┣ 📂java
         ┃ ┃ ┗ 📂com
         ┃ ┃ ┃ ┗ 📂cyber
         ┃ ┃ ┃ ┃ ┗ 📂university
         ┃ ┃ ┃ ┃ ┃ ┣ 📂config
         ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
         ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
         ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
         ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
         ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
         ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂interfaces
         ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂model
         ┃ ┃ ┃ ┃ ┃ ┣ 📂service
         ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
         ┃ ┣ 📂resources
         ┃ ┃ ┣ 📂db
         ┃ ┃ ┣ 📂mapper
         ┃ ┃ ┣ 📂static
         ┃ ┃ ┃ ┣ 📂css
         ┃ ┃ ┃ ┣ 📂img
         ┃ ┃ ┃ ┣ 📂js
         ┃ ┃ ┃ ┣ 📂vendor
         ┃ ┣ 📂webapp
         ┃ ┃ ┗ 📂WEB-INF
         ┃ ┃ ┃ ┗ 📂view
         ┃ ┃ ┃ ┃ ┣ 📂break
         ┃ ┃ ┃ ┃ ┣ 📂college
         ┃ ┃ ┃ ┃ ┣ 📂department
         ┃ ┃ ┃ ┃ ┣ 📂error
         ┃ ┃ ┃ ┃ ┣ 📂layout
         ┃ ┃ ┃ ┃ ┣ 📂notice
         ┃ ┃ ┃ ┃ ┣ 📂professor
         ┃ ┃ ┃ ┃ ┣ 📂room
         ┃ ┃ ┃ ┃ ┣ 📂schedule
         ┃ ┃ ┃ ┃ ┣ 📂staff
         ┃ ┃ ┃ ┃ ┣ 📂student
         ┃ ┃ ┃ ┃ ┣ 📂stuSub
         ┃ ┃ ┃ ┃ ┣ 📂subject
         ┃ ┃ ┃ ┃ ┣ 📂tuition
         ┃ ┃ ┃ ┃ ┣ 📂user

    
</details>
    
<br>

## 2️⃣ 프로젝트 개요
- 본 프로젝트는 학사관리를 목적으로 하는 웹 사이트입니다.
- 스프링부트를 기반으로 구축되었으며, MyBatis를 활용하여 데이터베이스 관리를 하고, JSP를 통해 사용자 인터페이스를 구성하였습니다.
- 이 프로젝트는 학사 업무를 효율적으로 관리하기 위해 설계되었으며, 다양한 기능과 편의성을 제공하여 사용자들이 학사 관련 업무를 간편하게 처리할 수 있도록 개발하였습니다.

<br>

## 3️⃣ ERD
![cyber_uni_erd](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/b5008958-8fb9-4df2-a968-49abb9dcd250)


## 4️⃣ SiteMap

1) 교직원
![사이트맵_교직원로그인](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/0ff75d8f-edc3-4204-9851-c50e6c20c46b)

2) 학생
![사이트맵_학생로그인](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/407180e9-239c-4b57-ab2b-643967f14b48)

3) 교수
![사이트맵_교수로그인](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/a80ed1dd-4f1a-4d33-a691-dd3da73651d0)


## 역할 분담
#😎 이준혁(팀장)
- 프로젝트 구조 설계
- 프로젝트 기본 Front 디자인 설계
- 로그인/로그아웃/ID/PW찾기 Back/Front 구현
- 교직원 기능
  1) 학생, 교수, 교직원 등록
  2) 등록금 고지서 발송
  3) 휴학 처리
  4) 수강 신청 기간 설정
  5) 강의 등록/수정/삭제
  6) 단대별 등록금 설정
- 학생 기능
  1) 수강신청
- 공통 기능
  1) 사이트맵
  2) 커뮤니티

#😎 김수현
- 프로젝트 일정 관리
- 교직원 기능
  1) 단과대 등록/수정/삭제
  2) 학과 등록/수정/삭제
  3) 강의실 등록/수정/삭제
 
#😎 박경진
- 학생 기능
  1) 휴학 신청
  2) 휴학 내역 조회
  3) 등록금 내역 조회
  4) 성적 조회
- 공통 기능
  1) 식단 API 활용한 오늘의 학식 기능
 
#😎 조유빈
- DB 스키마 작업
- 공통 기능
  1) 공지사항 등록/수정/삭제
  2) 학사일정 등록/수정/삭제
 
#😎 장명근
- 교수 기능
  1) 내 강의 조회
  2) 내 강의 평가
  3) 강의 등록
- 학생 기능
  1) 포트원 결제 API를 활용한 등록금 결제 구


## 개발기간/작업관리
개발기간
- 2024.03.11 ~ 2024.03.26 (12일)

일정관리




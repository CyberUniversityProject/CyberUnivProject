# 📖 학사관리 웹 서비스 CyberUniversity
![제목을 입력해주세요_-001](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/dc016ff0-294c-4e7e-8615-63b9d330f433)


<br>

## 🏫 프로젝트 소개

이 프로젝트는 5명의 개발자가 스프링부트(Spring Boot), MyBatis, JSP, 그리고 MySQL을 이용하여 개발된 웹 기반의 학사관리시스템입니다. 
학사 업무를 체계적으로 관리하고 학사 정보를 효율적으로 처리할 수 있도록 개발하였습니다. 
사용자 친화적인 UI와 다양한 기능을 제공하여 학사 관리 업무를 간편하게 수행할 수 있습니다. 


<br>

## 📆 개발기간/일정관리
1) 개발기간
- 2024.03.11 ~ 2024.03.26 (12일)

2) 일정관리
![image](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/71ac3b7b-84b4-48b5-b3f2-38acaf54ed5b)



## 👨‍👩‍👧‍👦 팀원 구성
![제목을-입력해주세요_-005](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/bd57bd12-a6fa-4998-b25d-331979c947b7)


<br>

## 🔧 개발환경
- 개발 툴 : Spring Tools 4 (4.21.1)
- Backend : JAVA 17, SpringBoot 3.1.8, MyBatis, JSP, MySQL 8.0.26, lombok, Apache Tomcat : 10.1.19
- Frontend : bootstrap : 4.6.2, HTML5, CSS3, JavaScript : 1.16.1, JQuery : 3.6.4
- 버전/이슈관리 : GitHub, GitBash
- 협업 툴 : Discord, Notion, Slack
- 외부 API : 포트원 결제 API, 식단 API
- 커뮤니티 게시판 : Summernote editor

<br>

## 📱 브랜치 전략
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
![cyber_uni_erd](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/e2e4b351-6681-42bb-ae53-36613bdbc35f)



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
  1) 포트원 결제 API를 활용한 등록금 결제 구현


## 5️⃣ API 목록

> 모든 API(주소) 목록
> 

| NO | Method | URI | 설명 |
| --- | --- | --- | --- |
| 1 | GET | /applySubject/list | 관리자 교수 강의 신청 리스트 페이지 |
| 2 | GET | /applySubject/detail/{id} | 관리자 교수 강의 신청 상세조회 페이지 |
| 3 | POST | /updateApproval/{id} | 관리자 교수 신청 강의 등록,반려 기능 |
| 4 | PUT | /api/applySub/updateApproval/{id} | 강의 개설 승인 기능 |
| 5 | PUT | /api/applySub/updateReason/{id} | 강의 개설 반려 기능 |
| 6 | GET | /break/list/staff | 휴학, 복학 처리되지 않은 신청내역 페이지 |
| 7 | POST | /break/update/{id} | 교직원 휴학 신청 처리 페이지 |
| 8 | GET | /break/detail/{id} | 휴학 신청서 자세히보기 페이지 |
| 9 | GET | /college/collegeRegister | 단과 대학 등록 화면 띄우기 |
| 10 | POST | /college/collegeRegister | 단과 대학 등록 페이지 |
| 11 | GET | /college/collgeList | 단과 대학 목록 페이지 |
| 12 | GET | /college/delete/{id} | 단과 대학 삭제 페이지 |
| 13 | GET | /college/collegeUpdate/{id} | 단과 대학 수정 화면 띄우기 |
| 14 | POST | /college/collegeUpdate/{id} | 단과 대학 수정 페이지 |
| 15 | POST | /comment/create | 댓글 생성 페이지 |
| 16 | GET | /comment/community/{communityId} | 특정 게시글 해당 댓글 |
| 17 | GET | /comment/{id} | 댓글 수정 페이지 |
| 18 | PUT | /comment/{id} | 댓글 수정 기능 |
| 19 | DELETE | /comment/{id} | 댓글 삭제 기능 |
| 20 | POST | /api/comment/create | 댓글 생성 기능 |
| 21 | GET | /api/comment/count/{communityId} | 댓글 수 가져오기 기능 |
| 22 | PUT | /api/comment/update/{id} | 댓글 수정 기능 |
| 23 | DELETE | /api/comment/delete/{id} | 댓글 삭제 기능 |
| 24 | GET | /api/comment/{id} | 댓글 한 건 조회하기 기능 |
| 25 | GET | /community/list | 전체 게시글 목록 페이지 |
| 26 | GET | /community/write | 게시글 작성 페이지 |
| 27 | POST | /community/create | 게시글 게재하기 기능 |
| 28 | GET | /community/all | 전체 게시글 페이지 |
| 29 | GET | /community/{id} | 특정 게시글 상세보기 페이지 |
| 30 | GET | /community/update/{id} | 특정 게시글 수정 페이지 |
| 31 | PUT | /community/{id} | 게시글 수정 기능 |
| 32 | DELETE | /community/delete/{id} | 게시글 삭제 기능 |
| 33 | DELETE | /api/community/delete/{id} | 게시글 연관 댓글 삭제 기능 |
| 34 | PUT  | /api/community/update/{id} | 게시글 연관 댓글 수정 기능 |
| 35 | GET | /error/ | 에러 페이지 |
| 36 | GET | /department/departmentRegister | 학과 등록 페이지 띄우기  |
| 37 | POST | /department/departmentRegister | 학과 등록 페이지 |
| 38 | GET | /department/departmentList | 학과 리스트 페이지 |
| 39 | GET | /department/delete/{id} | 학과 삭제 기능 |
| 40 | GET | /department/departmentUpdate/{id} | 학과 수정페이지 띄우기 |
| 41 | POST | /department/departmentUpdate/{id} | 학과 수정하기 기능 |
| 42 | GET | /api/department/findAll | 학과 전체 조회 기능 |
| 43 | GET | /notice | 공지사항 페이지 |
| 44 | POST | /notice/write | 공지사항 입력기능 |
| 45 | GET | /notice/read | 공지사항 상세 조회 기능 |
| 46 | GET | /notice/list/{page} | 공지사항 페이지 이동 기능 |
| 47 | GET | /notice/search | 공지사항 검색 기능 |
| 48 | GET | /notice/search/{page} | 공지사항 키워드 검색 , 페이징 기능 |
| 49 | GET | /notice/update | 공지사항 수정 페이지 |
| 50 | PUT | /notice/update | 공지사항 수정 기능 |
| 51 | GET | /notice/delete | 공지사항 삭제 조회 기능 |
| 52 | GET | /api/notice/list | 공지사항 리스트 페이지 |
| 53 | GET | /api/notice/{id} | 공지사항 상세조회 페이지 |
| 54 | GET | /buy/{tuiYear}/{semester} | 학생 해당학기 등록금 납부 페이지 |
| 55 | POST | /buy/{tuiYear}/{semseter} | 학생 해당학기 등록금 납부 기능 |
| 56 | GET | / | 메인 홈페이지  |
| 57 | GET | /login | 로그인 페이지 |
| 58 | POST | /login | 로그인 기능 |
| 59 | GET | /logout | 로그아웃 기능 |
| 60 | GET | /error | 에러페이지 |
| 61 | GET | /guide | 비밀번호 변경 안내 페이지 |
| 62 | GET | /info/staff | 교직원 마이페이지 |
| 63 | GET | /password | 비밀번호 변경 페이지 |
| 64 | PUT | /password | 비밀번호 수정 기능 |
| 65 | GET | /find/id | 아이디 찾기 페이지 |
| 66 | POST | /find/id | 아이디 찾기 결과 페이지 |
| 67 | GET | /find/password | 비밀번호 찾기 페이지 |
| 68 | POST | /find/password | 비밀번호 찾기 결과 페이지 |
| 69 | GET | /update | 개인정보 수정 페이지 |
| 70 | PUT | /update | 개인정보 수정 기능 |
| 71 | GET | /campusMap | 캠퍼스 지도 페이지 |
| 72 | GET | /professor/info | 교수 정보 조회 페이지  |
| 73 | GET | /professor/udpate | 교수 정보 수정 페이지  |
| 74 | GET | /professor/apply | 강의 등록 페이지  |
| 75 | POST | /professor/apply | 강의 신청 기능 |
| 76 | GET | /professor/mysub | 교수 본인 강의 조회 페이지 |
| 77 | POST | /professor/mysub | 교수 본인 강의 조회 기능 |
| 78 | GET | /professor/updatepw | 교수 비밀번호 변경 페이지 |
| 79 | GET | /professor/subject/{subjectId} | 교수 강의 성적 평가 페이지 |
| 80 | GET | /professor/subject/{subjectId}/{studentId} | 성적 입력 페이지  |
| 81 | POST | /professor/subject/{subjectId}/{studentId} | 성적 입력 기능 |
| 82 | GET | /professor/readvaluation | 교수 강의 평가 페이지 |
| 83 | POST | /professor/readvaluation | 교수 과목별 강의 평가 조회 페이지 |
| 84 | GET | /professor/syllabus/{subjectId} | 교수 강의계획서 조회 페이지 |
| 85 | GET | /professor/syllabus/update/{subjectId} | 교수 강의계획서 수정 페이지 |
| 86 | POST | /professor/syllabus/update/{subjectId} | 교수 강의계획서 수정 기능 |
| 87 | GET | /api/professor/findAll | 교수 전체 조회 기능 |
| 88 | GET | /room/roomRegister | 강의실 등록 페이지 |
| 89 | POST | /room/roomRegister | 강의실 등록 기능 |
| 90 | GET | /room/roomList | 강의실 리스트 페이지 |
| 91 | GET | /room/delete/{id} | 강의실 삭제 기능 |
| 92 | GET | /room/roomUpdate/{id} | 강의실 수정 페이지 |
| 93 | POST | /room/roomUpdate/{id} | 강의실 수정 기능 |
| 94 | GET | /api/room/findAll | 강의실 전체 조회 기능 |
| 95 | GET | /schedule | 학사일정 페이지 |
| 96 | GET | /schedule/list | 학사일정 리스트 페이지 |
| 97 | GET | /schedule/detail | 학사일정 상세보기 페이지 |
| 98 | GET | /schedule/write | 학사일정 등록 페이지 |
| 99 | POST | /schedule/write | 학사일정 등록 기능 |
| 100 | POST | /schedule/update | 학사일정 수정 기능 |
| 101 | GET | /schedule/delete | 학사일정 삭제 기능 |
| 102 | GET | /api/schedule/selectAll | 학사일정 전체 조회 기능 |
| 103 | GET | /staff/subject | 교직원 강의 등록 페이지 |
| 104 | GET | /staff/subjectList | 교직원 강의 등록 리스트 페이지 |
| 105 | POST | /staff/subject | 교직원 강의 입력 기능 |
| 106 | GET | /staff/subjectDelete | 교직원 강의 삭제 기능 |
| 107 | PUT | /staff/subject | 교직원 강의 수정 기능 |
| 108 | GET | /staff/tuition | 교직원 단과대 별 등록금 페이지 |
| 109 | POST | /staff/tuition | 교직원 단과대 별 등록금 입력 기능 |
| 110 | GET | /staff/tuitionDelete | 교직원 단과대 등록금 삭제 기능 |
| 111 | PUT | /staff/tuitionUpdate | 교직원 단과대 등록금 수정 기능 |
| 112 | GET | /student/myInfo | 학생 내 정보 조회 페이지 |
| 113 | POST | /student/updateInfo | 학생 내 정보 수정 기능 |
| 114 | GET | /student/password | 학생 비밀번호 수정 페이지 |
| 115 | POST | /student/updatePass | 학생 비밀번호 수정 기능 |
| 116 | GET | /student/leaveOfAbsence | 휴학 신청 페이지 |
| 117 | POST | /student/leaveApp | 휴학 신청서 제출 기능 |
| 118 | GET | /student/leaveOfAbsenceList | 학생 휴학 신청 내역 페이지 |
| 119 | GET | /student/deleteLeaveApp/{id} | 학생 휴학 신청 취소 페이지 |
| 120 | GET | /student/tuition | 학생 등록금 페이지 |
| 121 | GET | /student/tuitionBill | 학생 등록금 고지서 페이지 |
| 122 | GET | /student/gradeDetailList | 학생 성적 상세 조회 페이지 |
| 123 | GET | /student/list/search | 학생 전체 성적 목록 필터링 페이지 |
| 124 | GET | /student/evaluation/{subjectId} | 학생 강의 평가 페이지 |
| 125 | POST | /student/evaluation/{subjectId} | 학생 강의 평가 등록 페이지 |
| 126 | GET | /sugang/period | 교직원 수강신청 기간 변경 페이지 |
| 127 | GET | /sugang/updatePeriod1 | 교직원 예비 수강 신청에서 수강 신청 기간 변경 기능 |
| 128 | GET | /sugang/updatePeriod2 | 교직원 수강 신청 기간에서 종료로 변경 기능 |
| 129 | GET | /sugang/updatePeriod0 | 교직원 수강 신청 종료기간을 예비 수강 신청 기간으로 변경하는 기능 |
| 130 | GET | /sugang/subjectList/{page} | 현재 학기 과목 조회 페이지 |
| 131 | GET | /sugang/pre/{page} | 학생 예비 수강 신청 페이지 |
| 132 | POST | /sugang/pre/{subjectId} | 학생 예비 수강 신청 기능 |
| 133 | DELETE | /sugang/pre/{subjectId} | 예비 수강 신청 취소 기능 |
| 134 | GET | /sugang/pre/search | 예비 수강 신청 검색 기능 |
| 135 | GET | /sugang/application/{page} | 학생 수강 신청 기능 |
| 136 | GET | /sugang/applicationa/search | 수강 신청 강의 검색 기능 |
| 137 | POST | /sugang/insertApp/{subjectId} | 수강 신청 처리 기능 |
| 138 | DELETE | /sugang/deleteApp/{subjectId} | 수강 신청 취소 기능 |
| 139 | GET | /sugang/preAppList | 학생 예비 수강 신청 내역 페이지 |
| 140 | GET | /sugang/list | 학생 수강 신청 내역 페이지  |
| 141 | GET | /subject/list/{page} | 모든 강의 조회 페이지 |
| 142 | GET | /subject/list/search | 강의 검색 페이지 |
| 143 | GET | /subject/syllabus/{subjectId} | 강의 계획서 페이지 |
| 144 | GET | /tuition/list | 납부 된 등록금 내역 조회 페이지 |
| 145 | GET | /tuition/payment | 등록금 납부 고지서 조회 페이지 |
| 146 | POST | /tuition/payment | 등록금 납부 완료 페이지 |
| 147 | GET | /tuition/bill | 장학금 유형 설정, 등록금 납부 고지서 생성 페이지 |
| 148 | GET | /tuition/create | 등록금 납부 고지서 생성 페이지 |
| 149 | GET | /user/staff | 교직원 등록 페이지 |
| 150 | POST | /user/staff | 교직원 등록 기능 |
| 151 | GET | /user/studentList | 교직원 학생 조회 기능 |
| 152 | GET | /user/studentList/{page} | 교직원 학생 조회 페이지 페이징 기능 |
| 153 | GET | /user/professorList | 교직원 교수 조회 페이지 |
| 154 | GET | /user/professorList/{page} | 교직원 교수 조회 페이지 페이징 기능 |
| 155 | GET | /user/professor | 교직원 교수 입력 페이지 |
| 156 | POST | /user/professor | 교직원 교수 등록 기능 |
| 157 | GET | /user/student | 교직원 학생 입력 페이지 |
| 158 | POST | /user/student | 교직원 학생 입력 기능 |
| 159 | GET | /user/student/update | 교직원 학생 학년, 학기 업데이트 기능 |




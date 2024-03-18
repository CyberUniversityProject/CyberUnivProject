# 📖 학사관리 웹 서비스 CyberUniversity


<br>

## 프로젝트 소개


<br>

## 팀원 구성


<br>

## 개발환경
- 개발 툴 : Spring Tools 4 (4.21.1)
- Backend : JAVA 17, SpringBoot 3.1.8, MyBatis, JSP
- 버전/이슈관리 : GitHub, GitBash
- 협업 툴 : Discord, Notion
- 외부 API :

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



<br>

## 3️⃣ ERD
![cyber_uni_erd](https://github.com/CyberUniversityProject/CyberUnivProject/assets/126323071/b5008958-8fb9-4df2-a968-49abb9dcd250)


## 4️⃣ SiteMap
<br>



<br>

## 역할 분담
#😎 이준혁(팀장)
-

#😎 김수현
- 
#😎 박경진
-
#😎 조유빈
- 
#😎 장명근
- 


## 개발기간/작업관리
개발기간
- 2024.03.11 ~ 2024.03.
작업관리
- 
# CyberUnivProject

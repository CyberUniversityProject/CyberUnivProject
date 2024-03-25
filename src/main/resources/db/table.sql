-- 단과대
CREATE TABLE cu_college
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR (10) NOT NULL UNIQUE
);

-- 학과
CREATE TABLE cu_department
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR (10) NOT NULL UNIQUE,
   college_id INT NOT NULL COMMENT '단과대 id',
   FOREIGN KEY (college_id) REFERENCES cu_college (id) ON DELETE CASCADE
);
ALTER TABLE cu_department AUTO_INCREMENT = 101;

-- 사용자
CREATE TABLE cu_user
(
   id INT PRIMARY KEY,
   password VARCHAR (255) NOT NULL,
   user_role VARCHAR (10) NOT NULL
);

-- 학생
CREATE TABLE `cu_student` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '학번',
  `name` varchar(30) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` char(2) NOT NULL COMMENT '남성, 여성',
  `address` varchar(100) NOT NULL,
  `tel` varchar(13) NOT NULL,
  `email` varchar(30) NOT NULL,
  `dept_id` int NOT NULL COMMENT '학과',
  `grade` int NOT NULL DEFAULT '1' COMMENT '학년',
  `semester` int NOT NULL DEFAULT '1' COMMENT '학기',
  `entrance_date` date NOT NULL,
  `graduation_date` date DEFAULT NULL,
  `origin_file_name` varchar(255) DEFAULT NULL,
  `upload_file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `cu_student_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `cu_department` (`id`) ON DELETE CASCADE
);
ALTER TABLE cu_student AUTO_INCREMENT = 2024000001;

-- 교직원
CREATE TABLE cu_staff
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR (30) NOT NULL,
   birth_date DATE NOT NULL,
   gender CHAR (2) NOT NULL COMMENT '남성, 여성',
   address VARCHAR (100) NOT NULL,
   tel VARCHAR (13) NOT NULL,
   email VARCHAR (30) NOT NULL,
   hire_date DATE DEFAULT (current_date)
);
ALTER TABLE cu_staff AUTO_INCREMENT = 240001;

-- 교수
CREATE TABLE cu_professor
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR (30) NOT NULL,
   birth_date DATE NOT NULL,
   gender CHAR (2) NOT NULL COMMENT '남성, 여성',
   address VARCHAR (100) NOT NULL,
   tel VARCHAR (13) NOT NULL,
   email VARCHAR (30) NOT NULL,
   dept_id INT NOT NULL,
   hire_date DATE DEFAULT (current_date),
   FOREIGN KEY (dept_id) REFERENCES cu_department (id) ON DELETE CASCADE
);
ALTER TABLE cu_professor AUTO_INCREMENT = 24000001;

-- 강의실
CREATE TABLE cu_room
(
   id VARCHAR (5) PRIMARY KEY,
   college_id INT NOT NULL,
   FOREIGN KEY (college_id) REFERENCES cu_college (id) ON DELETE CASCADE
);

-- 강의
CREATE TABLE cu_subject
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR (20) NOT NULL,
   professor_id INT NOT NULL,
   room_id VARCHAR (5),
   dept_id INT NOT NULL,
   type VARCHAR (2) NOT NULL COMMENT '강의 구분 (전공, 교양)',
   sub_year INT NOT NULL COMMENT '연도',
   semester INT NOT NULL COMMENT '학기',
   sub_day VARCHAR (1) NOT NULL COMMENT '요일',
   start_time INT NOT NULL COMMENT '시작 시간',
   end_time INT NOT NULL COMMENT '종료 시간',
   grades INT NOT NULL COMMENT '이수 학점',
   capacity INT NOT NULL COMMENT '수강 정원',
   num_of_student INT NOT NULL DEFAULT 0 COMMENT '현재 신청 인원',
   FOREIGN KEY (professor_id) REFERENCES cu_professor (id) ON DELETE CASCADE,
   FOREIGN KEY (room_id) REFERENCES cu_room (id) ON DELETE CASCADE,
   FOREIGN KEY (dept_id) REFERENCES cu_department (id) ON DELETE CASCADE
);
-- 과목 id 10000부터
ALTER TABLE cu_subject AUTO_INCREMENT = 10000;

-- 환산 점수
CREATE TABLE cu_grade
(
   grade VARCHAR (2) PRIMARY KEY COMMENT '학점 (평점)',
   grade_value FLOAT NOT NULL COMMENT '환산 점수'
);

-- 수강 신청
CREATE TABLE cu_pre_stu_sub
(
   student_id INT,
   subject_id INT,
   PRIMARY KEY
   (
      student_id,
      subject_id
   ),
   FOREIGN KEY (student_id) REFERENCES cu_student (id) ON DELETE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES cu_subject (id) ON DELETE CASCADE
);

-- 수강 내역
CREATE TABLE `cu_stu_sub` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `grade` varchar(2) DEFAULT NULL COMMENT '신청 학점 (평점)',
  `complete_grade` int DEFAULT NULL COMMENT '이수 학점',
  `evaluation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `subject_id` (`subject_id`),
  KEY `grade` (`grade`),
  CONSTRAINT `cu_stu_sub_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `cu_student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `cu_stu_sub_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `cu_subject` (`id`) ON DELETE CASCADE,
  CONSTRAINT `cu_stu_sub_ibfk_3` FOREIGN KEY (`grade`) REFERENCES `cu_grade` (`grade`)
);

-- 단과대별 등록금
CREATE TABLE cu_coll_tuit
(
   college_id INT PRIMARY KEY,
   amount INT NOT NULL,
   FOREIGN KEY (college_id) REFERENCES cu_college (id) ON DELETE CASCADE
);

-- 장학금
CREATE TABLE cu_scholarship
(
   type INT PRIMARY KEY COMMENT '장학금 유형',
   max_amount INT NOT NULL COMMENT '최대 지원 금액'
);

-- 학생별 장학금 유형
CREATE TABLE cu_stu_sch
(
   student_id INT NOT NULL,
   sch_year INT NOT NULL COMMENT '지원 연도',
   semester INT NOT NULL COMMENT '지원 학기',
   sch_type INT COMMENT '장학금 유형',
   PRIMARY KEY
   (
      student_id,
      sch_year,
      semester
   ),
   FOREIGN KEY (sch_type) REFERENCES cu_scholarship (type)
);

-- 등록금
CREATE TABLE cu_tuition
(
   student_id INT,
   tui_year INT NOT NULL COMMENT '등록 연도',
   semester INT NOT NULL COMMENT '등록 학기',
   tui_amount INT NOT NULL COMMENT '등록금',
   sch_type INT COMMENT '장학금 유형',
   sch_amount INT COMMENT '장학금',
   status BOOLEAN DEFAULT false COMMENT '납부 여부',
   PRIMARY KEY
   (
      student_id,
      tui_year,
      semester
   ),
   FOREIGN KEY (student_id) REFERENCES cu_student (id) ON DELETE CASCADE,
   FOREIGN KEY (sch_type) REFERENCES cu_scholarship (type)
);

-- 공지사항
CREATE TABLE cu_notice
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   category VARCHAR (10) NOT NULL,
   title VARCHAR (255) NOT NULL,
   content TEXT NOT NULL,
   created_time TIMESTAMP DEFAULT now(),
   views INT NOT NULL DEFAULT 0 COMMENT '조회수'
);

-- 공지사항 첨부 파일
CREATE TABLE cu_notice_file
(
   notice_id INT NOT NULL,
   origin_filename VARCHAR (100) COMMENT '기존 파일명' NOT NULL,
   uuid_filename VARCHAR (255) COMMENT '랜덤 문자열 포함 파일명' NOT NULL,
   FOREIGN KEY (notice_id) REFERENCES cu_notice (id) ON DELETE CASCADE
);

-- 휴학 신청 내역
CREATE TABLE cu_break
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   student_id INT NOT NULL,
   student_grade INT NOT NULL,
   from_year INT NOT NULL,
   from_semester INT NOT NULL,
   to_year INT NOT NULL,
   to_semester INT NOT NULL,
   type VARCHAR (10) NOT NULL COMMENT '일반, 임신·출산·육아, 질병, 창업, 군입대',
   app_date DATE DEFAULT (current_date) NOT NULL COMMENT '신청 일자',
   status VARCHAR (3) NOT NULL DEFAULT '처리중' COMMENT '처리중, 승인, 거부',
   FOREIGN KEY (student_id) REFERENCES cu_student (id) ON DELETE CASCADE
);

-- 학적 상태
CREATE TABLE cu_stu_stat
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   student_id INT NOT NULL,
   status VARCHAR (3) NOT NULL DEFAULT '재학', -- 재학, 휴학, 졸업, 자퇴
   from_date DATE,
   to_date DATE, -- 현재 속한 상태인 경우 '9999-01-01'
   break_app_id INT,
   FOREIGN KEY (student_id) REFERENCES cu_student (id) ON DELETE CASCADE,
   FOREIGN KEY (break_app_id) REFERENCES cu_break (id) ON DELETE CASCADE
);

CREATE TABLE cu_stu_sub_detail
(
   id INT PRIMARY KEY,
   student_id INT NOT NULL,
   subject_id INT NOT NULL,
   absent INT COMMENT '결석 횟수',
   lateness INT COMMENT '지각 횟수',
   homework INT COMMENT '과제 점수',
   mid_exam INT COMMENT '중간고사 점수',
   final_exam INT COMMENT '기말고사 점수',
   converted_mark INT COMMENT '환산점수',
   FOREIGN KEY (id) REFERENCES cu_stu_sub(id) ON DELETE CASCADE,
   FOREIGN KEY (student_id) REFERENCES cu_student(id) ON DELETE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES cu_subject(id) ON DELETE CASCADE
);

CREATE TABLE cu_syllabus
(
	subject_id INT PRIMARY KEY ,
	overview VARCHAR(255) COMMENT '수업 개요',
	objective VARCHAR(255) COMMENT '강의 목표',
	textbook VARCHAR(30) COMMENT '교재',
	program TEXT COMMENT '주별 계획',
	FOREIGN KEY (subject_id) REFERENCES cu_subject(id) ON DELETE CASCADE
);

-- 강의 평가 테이블
CREATE TABLE cu_evaluation
(
evaluation_id INT AUTO_INCREMENT,
student_id INT,
subject_id INT,
PRIMARY KEY
(
evaluation_id,
student_id,
subject_id
),
answer1 INT NOT NULL COMMENT '응답',
answer2 INT NOT NULL,
answer3 INT NOT NULL,
answer4 INT NOT NULL,
answer5 INT NOT NULL,
answer6 INT NOT NULL,
answer7 INT NOT NULL,
improvements VARCHAR(255) COMMENT '건의사항',
FOREIGN KEY (student_id) REFERENCES cu_student (id) ON DELETE CASCADE,
FOREIGN KEY (subject_id) REFERENCES cu_subject (id) ON DELETE CASCADE
);

-- 질문지 내용 테이블
CREATE TABLE cu_question
(
id INT PRIMARY KEY AUTO_INCREMENT,
question1 VARCHAR(100) NOT NULL COMMENT '질문 내용',
question2 VARCHAR(100) NOT NULL,
question3 VARCHAR(100) NOT NULL,
question4 VARCHAR(100) NOT NULL,
question5 VARCHAR(100) NOT NULL,
question6 VARCHAR(100) NOT NULL,
question7 VARCHAR(100) NOT NULL,
sug_content VARCHAR(255) NOT NULL
);

-- 학사일정
CREATE TABLE cu_schedule(
id INT PRIMARY KEY AUTO_INCREMENT,
staff_id INT,
start_day DATE NOT NULL,
end_day DATE NOT NULL,
information VARCHAR(50) NOT NULL,
FOREIGN KEY (staff_id) REFERENCES cu_staff(id) 
);


CREATE TABLE `cu_apply_sub` (
  `id` int NOT NULL AUTO_INCREMENT,
  `professor_id` int NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '강의 명',
  `room_id` varchar(5) NOT NULL COMMENT '강의실 명',
  `dept_id` int NOT NULL COMMENT '학과 명',
  `type` char(2) NOT NULL COMMENT '전공/교양',
  `start_time` int NOT NULL COMMENT '강의 시작 시간',
  `end_time` int NOT NULL COMMENT '강의 끝나는 시간',
  `sub_year` int NOT NULL COMMENT '강의 개설 년도',
  `semester` int NOT NULL COMMENT '강의 개설 학기',
  `sub_day` varchar(1) NOT NULL COMMENT '강의 요일',
  `grades` int NOT NULL COMMENT '이수 학점',
  `capacity` int NOT NULL COMMENT '강의 정원 수',
  `approval` char(10) NOT NULL DEFAULT '미승인',
  `reason` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE cu_community (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  content varchar(1000) DEFAULT NULL,
  userName varchar(100) NOT NULL,
  createDate datetime NOT NULL,
  updateDate datetime DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE cu_comment (
  id int NOT NULL AUTO_INCREMENT,
  community_id int DEFAULT NULL,
  content text,
  createDate datetime NOT NULL,
  user_id varchar(20) DEFAULT NULL,
  role varchar(10) DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_community_id(community_id),
  CONSTRAINT fk_community_comment FOREIGN KEY (community_id) REFERENCES cu_community (id) ON DELETE CASCADE

);


create table cu_payment (
 id int NOT NULL AUTO_INCREMENT,
 u_id varchar(20) NOT NULL,
 m_id varchar(20) NOT NULL,
 stu_id int NOT NULL,
 buyer_name varchar(20) not null,
 total_price int not null,
 payment_date timestamp DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id)
 

); 



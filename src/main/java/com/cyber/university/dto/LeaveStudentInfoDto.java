package com.cyber.university.dto;

import java.sql.Date;

import lombok.Data;

/**
  * @FileName : LeaveStudentInfoDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 휴학 신청 할 학생 정보 DTO
  */
@Data
public class LeaveStudentInfoDto {
//	 학과(department 테이블)
	private String department;
//	 학년(student 테이블)
	private Integer grade;
//	 학번(student 테이블)
	private Integer studentId;
//	 이름(student 테이블)
	private String name;
//	 생일(student테이블)
	private Date birthDate;
//	 이메일(student테이블)
	private String email; 
//	 주소(student 테이블)
	private String address;
//	 휴대폰(student 테이블)
	private String tel;
//	 현재 날짜
	private Date today;
	 
}

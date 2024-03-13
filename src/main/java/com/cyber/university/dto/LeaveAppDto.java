package com.cyber.university.dto;

import java.sql.Date;

import lombok.Data;

/**
  * @FileName : LeaveStudentDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 휴학 신청 정보 DTO
  */
@Data
public class LeaveAppDto {
	
//	 학생 학번
	private Integer studentId;
//	 학생 학년
	private Integer studentGrade;
//	 시작 년도
	private Integer fromYear;
//	 시작 학기
	private Integer fromSemester;
//	 종료 년도
	private Integer toYear;
//	 종료 학기
	private Integer toSemester;
//	 휴학 유형
	private String type;
//	 신청 일자
	private Date appDate;
	 
	 
}

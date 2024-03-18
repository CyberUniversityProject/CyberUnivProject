package com.cyber.university.dto;

import java.sql.Date;

import lombok.Data;

/**
  * @FileName : ThisSemesterGradeDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 18. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 성적 조회를 위한 DTO
  */
@Data
public class SemesterGradeDto {

	// cu_stu_sub table 
	private Integer subjectId;		// 과목 번호
	private String grade;			// 성적(A+,A,A-,...)
	private Integer grades;	// 학점
	
	// cu_subject
	private Integer subYear;		// 연도
	private Integer semester;		// 학기
	private String subjectName;		// 과목명
	private String type;			// 강의 구분(전공, 교양)
	
}

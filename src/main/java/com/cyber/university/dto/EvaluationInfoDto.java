package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : EvaluationInfoDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 19. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 강의 평가 과목 및 학생 관련 정보 dto
  */
@Data
public class EvaluationInfoDto {
	// cu_subject table
	private Integer subYear;		// 해당 과목 강의 년도
	private Integer semester;		// 학기
	private Integer subjectId;		// 과목번호
	private String subjectName;		// 과목명
	// cu_student table
	private Integer studentId;		// 학번
	private Integer grade;			// 학년
	private String studentName;		// 학생 이름
	// cu_professor table;
	private String professorName;	// 교수 이름
}

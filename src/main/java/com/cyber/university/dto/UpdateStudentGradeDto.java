package com.cyber.university.dto;

import lombok.Data;

/**
 * 
  * @FileName : UpdateStudentGradeDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생 출결 및 성적 기입 폼
 */
@Data
public class UpdateStudentGradeDto {
	
	
	private Integer studentId;
	private Integer subjectId;

	// 결석 횟수
	private Integer absent;
	// 지각 횟수
	private Integer lateness;
	// 과제 점수
	private Integer homework;
	// 중간고사 점수
	private Integer midExam;
	// 기말고사 점수
	private Integer finalExam;
	// 총합 환산 점수
	private Integer convertedMark;
	// 등급
	private String grade;

}

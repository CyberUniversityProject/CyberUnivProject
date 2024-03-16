package com.cyber.university.dto.professor;

import lombok.Data;

/**
  * @FileName : UpdateStudentSubDetailDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 16. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 학생 성적 업데이트
  */
@Data
public class UpdateStudentSubDetailDto {
	
	private Integer studentId; // 학생 아이디
	private Integer subjectId; // 강의 아이디
	private Integer absent; // 결석 횟수
	private Integer lateness; // 지각 횟수
	private Integer homework; // 과제 점수
	private Integer midExam; // 중간 점수
	private Integer finalExam; // 기말 점수
	private Integer convertedMark; // 합 점수
	private String grade; // 신청학점 (평점) A, B ...
	private Integer grades; // 이수 학점
	private Integer completeGrade; // 이수 학점
	
}

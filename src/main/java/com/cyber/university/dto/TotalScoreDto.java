package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : TotalScoreDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 18. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 학점 조회 DTO
  */
@Data
public class TotalScoreDto {
	
	//  cu_subject table
	private Integer subYear;			// 연도
	private Integer semester;			// 학기
	private Integer totalGrades;		// 총 신청 학점
	
	// cu_stu_sub table
	private Integer totalCompleteGrade;	// 총 취득 학점
	
	// cu_grade table
	private Float averageGrade;		// 평균 학점

}

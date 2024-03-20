package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : EvaluationDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 20. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 강의평가 등록 dto
  */
@Data
public class EvaluationDto {
	private Integer studentId;
	private Integer subjectId;
	private Integer answer1;
	private Integer answer2;
	private Integer answer3;
	private Integer answer4;
	private Integer answer5;
	private Integer answer6;
	private Integer answer7;
	private String improvements;
}

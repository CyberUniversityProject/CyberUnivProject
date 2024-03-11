package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Tuition.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 :등록금
  */
@Data
public class Tuition {
	
	private Integer studentId; 	// 학생 id
	private Integer tuiYear;	// 등록 연도
	private Integer semester; 	// 등록학기
	private Integer tuiAmount;	// 등록금
	private Integer schType;	// 장학금 유형
	private Integer schAmount;	// 장학금
	private Boolean status;		// 납부여부
	
	

}

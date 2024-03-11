package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : StuSch.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생의 학기별 장학금 유형
  */

@Data
public class StuSch {
	
	private Integer studentId;
	private Integer schYear;
	private Integer semester;
	private Integer schType;

}

package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Schedule.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학사일정
  */
@Data
public class Schedule {
	
	private Integer id;
	private Integer staffId;
	private String startDay;
	private String endDay;
	private String information;
	private Integer year;
	private Integer month;

}

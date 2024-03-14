package com.cyber.university.dto;

import lombok.Data;

	
/**
  * @FileName : ScheduleDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Dto (삭제될 수도 있음)
  */

@Data
public class ScheduleDto {
	private Integer months;
	private Integer sum;
	private Integer id;
	private Integer staffId;
	private Integer years;
	private String startMday;
	private String endMday;
	private String startDay;
	private String endDay;
	private String information;
}

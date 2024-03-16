package com.cyber.university.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleFormDto {
	private Integer id;
	private Integer staffId;
	private String startDay;
	private String endDay;
	private String information;

}

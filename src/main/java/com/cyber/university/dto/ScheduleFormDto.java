package com.cyber.university.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleFormDto {
	private Integer id;
	private Integer staffId;
	private Date startDay;
	private Date endDay;
	private String information;

}

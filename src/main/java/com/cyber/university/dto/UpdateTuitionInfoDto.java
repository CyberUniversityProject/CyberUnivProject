package com.cyber.university.dto;

import lombok.Data;

@Data
public class UpdateTuitionInfoDto {
	
	private Integer status;
	private Integer studentId;
	private Integer tuiYear;
	private Integer semester;
	
}

package com.cyber.university.dto;

import lombok.Data;

@Data
public class PaymentInfoDto {
	
	private Integer studentId;
	private Integer tuiYear;
	private Integer semester;
	private Integer schAmount;
	private String buyerName;
}

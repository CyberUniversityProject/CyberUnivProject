package com.cyber.university.dto;

import lombok.Data;

@Data
public class PaymentDto {
	private String uId;
	private String mId;
	private String buyerName;
	private Long schAmount;
}

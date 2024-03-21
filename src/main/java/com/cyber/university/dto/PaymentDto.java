package com.cyber.university.dto;

import lombok.Data;

@Data
public class PaymentDto {
	private String impUid;
	private String merchantUid;
	private String buyerName;
	private Long schAmount;
}

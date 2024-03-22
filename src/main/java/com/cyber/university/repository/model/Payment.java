package com.cyber.university.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Payment {
	private Integer id;
	private String uId;
	private String mId;
	private Integer studentId;
	private String buyerName;
	private Integer totalPrice;
	private Timestamp paymentDate;
}

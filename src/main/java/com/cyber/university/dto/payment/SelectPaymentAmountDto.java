package com.cyber.university.dto.payment;

import lombok.Data;

/**
  * @FileName : SelectPaymentAmount.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 22. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제할 금액 정보 Dto
  */
@Data
public class SelectPaymentAmountDto {
	private Integer studentId;
	private Integer tuiYear;
	private Integer semester;
	private Integer tuiAmount;
	private Integer schAmount;
	private Integer totalPrice;
}

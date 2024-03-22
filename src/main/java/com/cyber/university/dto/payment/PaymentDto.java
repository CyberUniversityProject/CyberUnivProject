package com.cyber.university.dto.payment;

import lombok.Data;

/**
  * @FileName : PaymentDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 22. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제 기록 Dto
  */
@Data
public class PaymentDto {
	private String impUid;
	private String merchantUid;
	private String buyerName;
	private Integer totalPrice;
}

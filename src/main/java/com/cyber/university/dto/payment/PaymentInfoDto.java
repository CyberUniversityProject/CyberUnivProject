package com.cyber.university.dto.payment;

import lombok.Data;

/**
  * @FileName : PaymentInfoDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 22. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제할 대상의 정보 Dto
  */
@Data
public class PaymentInfoDto {
	
	private Integer studentId;
	private Integer tuiYear;
	private Integer semester;
	private Integer tuiAmount;
	private Integer schAmount;
	private String buyerName;
}

package com.cyber.university.dto.payment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Data;

/**
  * @FileName : SelectPaymentDateDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 22. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제 시간 정보 Dto
  */
@Data
public class SelectPaymentDateDto {
	private Integer studentId;
	private Timestamp paymentDate;
	
	public String formatDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		String fomaterDate = dateFormat.format(paymentDate);
		
		return fomaterDate;
	}
}

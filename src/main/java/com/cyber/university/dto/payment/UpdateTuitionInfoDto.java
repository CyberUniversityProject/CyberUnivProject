package com.cyber.university.dto.payment;

import lombok.Data;

/**
  * @FileName : UpdateTuitionInfoDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 22. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 고지서 정보 Dto
  */
@Data
public class UpdateTuitionInfoDto {
	
	private Integer status;
	private Integer studentId;
	private Integer tuiYear;
	private Integer semester;
	
}

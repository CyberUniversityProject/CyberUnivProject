package com.cyber.university.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
  * @FileName : BreakFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 휴복학신청DTO
 */
@Data
public class BreakFormDto {
	
	private Integer studentId;
	private Integer studentGrade;
	private Integer fromYear;
	private Integer fromSemester;
	@NotNull
	private Integer toYear;
	@NotNull
	private Integer toSemester;
	@NotNull
	private String type;

}

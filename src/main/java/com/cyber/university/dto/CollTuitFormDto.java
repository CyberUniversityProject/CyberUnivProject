package com.cyber.university.dto;

import com.cyber.university.utils.NumberUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 
  * @FileName : CollTuitFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 단대별 등록금 설정 DTO
 */
@Data
public class CollTuitFormDto {

	@NotBlank
	private Integer collegeId;
	private String 	name;
	@NotEmpty
	private Integer amount;

	public String amountFormat() {
		return NumberUtil.numberFormat(amount);
	}
}

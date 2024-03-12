package com.cyber.university.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 
  * @FileName : FindPasswordFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 비밀번호 찾기 폼 DTO
 */
@Data
public class FindPasswordFormDto {
	
	@NotBlank
	private String name;
	@Min(100000)
	private Integer id;
	@Email
	private String email;
	private String userRole;
	

}

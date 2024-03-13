package com.cyber.university.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * 
  * @FileName : UserUpdateDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 회원정보수정 DTO
 */
@Data
public class UserUpdateDto {
	
	private Integer userId;
	@NotEmpty
	private String address;
	@NotBlank
	private String tel;
	@Email
	private String email;

}

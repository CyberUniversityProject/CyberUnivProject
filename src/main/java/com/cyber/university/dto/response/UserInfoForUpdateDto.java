package com.cyber.university.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * 
  * @FileName : UserInfoForUpdateDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 회원정보업데이트 DTO
 */
@Data
public class UserInfoForUpdateDto {
	
	
	@NotBlank
	private String address;
	@Size(min = 11, max = 13)
	private String tel;
	@Email
	private String email;

}

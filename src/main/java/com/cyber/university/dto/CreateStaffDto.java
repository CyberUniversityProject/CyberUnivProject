package com.cyber.university.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
  * @FileName : CreateStaffDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 직원 생성 DTO
  */
@Data
public class CreateStaffDto {
	
	@NotEmpty
	@Size(min = 2, max= 30)
	private String name;
	private Date birthDate;
	private String gender;
	@NotEmpty
	private String address;
	@Size(min = 11, max = 13)
	private String tel;
	@Email
	private String email;

}

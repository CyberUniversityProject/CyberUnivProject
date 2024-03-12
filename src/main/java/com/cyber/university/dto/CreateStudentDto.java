package com.cyber.university.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
  * @FileName : CreateStudentDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생등록 DTO
  */
@Data
public class CreateStudentDto {
	@NotEmpty
	@Size(min = 2, max= 30)
	private String name;
	private Date birthDate;
	private String gender;
	@NotEmpty
	private String address;
	@NotBlank
	private String tel;
	@Min(100)
	@Max(999)
	private Integer deptId;
	private Date entranceDate;
	@Email
	private String email;
	

}

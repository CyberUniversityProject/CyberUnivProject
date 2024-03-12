package com.cyber.university.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
  * @FileName : StudentPasswordDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 학생 password DTO 
  */
@Data
public class UserInfoDto {
	private Integer id;
    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String password;
	
}
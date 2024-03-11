package com.cyber.university.dto.professor;

import lombok.Data;

@Data
public class UpdateProfessorInfoDto {
	private Integer id;
	private String address;
	private String tel;
	private String email;
	private String password;
}

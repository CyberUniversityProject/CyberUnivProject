package com.cyber.university.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @FileName : StudentInfoDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 내 정보 조회를 위한 DTO
  */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentInfoDto {
	private Integer id;
	private String name;
	private Date birthDate;
	private String gender;
	private String address;
	private String tel;
	private String email;
	private Integer grade;			// 학년
	private Integer semester;		// 학기
	private Date entranceDate;		// 입학일
	private Date graduationDate;	// 졸업예정일
	private String deptName; 		// 학과명
	private String collegeName;		// 단과대명
	
}

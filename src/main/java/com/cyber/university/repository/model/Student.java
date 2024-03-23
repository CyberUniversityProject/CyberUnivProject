package com.cyber.university.repository.model;

import java.sql.Date;

import lombok.Data;

/**
  * @FileName : Student.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 : 
  * @프로그램 설명 : 학생
  */
@Data
public class Student {
	
	private Integer id;
	private String name;
	private Date birthDate;
	private String gender;
	private String address;
	private String tel;
	private String email;
	private Integer deptId;			// 학과
	private Integer grade;			// 학년
	private Integer semester;		// 학기
	private Date entranceDate;		// 입학일
	private Date graduationDate;	// 졸업예정일
	private String originFileName;
	private String uploadFileName;

	public String setupProfilImage(){
		return uploadFileName == null ? "/img/profil.png" : "/images/uploads/" + uploadFileName;
	}

}

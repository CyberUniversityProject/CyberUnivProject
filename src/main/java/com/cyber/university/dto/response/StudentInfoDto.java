package com.cyber.university.dto.response;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentInfoDto {
	
	private Integer id;
	private String name;
	private Date birthDate;
	private String gender;
	private String address;
	private String tel;
	private String email;
	private Integer deptId;
	private Integer grade;
	private Integer semester;
	private Date entranceDate;
	private Date graduationDate;
	private String deptName;
	private String collegeName;
	private String originFileName;
	private String uploadFileName;


	public String setupProfilImage(){
		return uploadFileName == null ? "이미지가 없습니다." : "/images/uploads/" + uploadFileName;
	}

}

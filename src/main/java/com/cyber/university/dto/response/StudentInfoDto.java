package com.cyber.university.dto.response;

import java.sql.Date;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
	private MultipartFile profilImage;


	public String setupProfilImage(){
		return uploadFileName == null ? "/img/profil.png" : "/images/uploads/" + uploadFileName;
	}

}

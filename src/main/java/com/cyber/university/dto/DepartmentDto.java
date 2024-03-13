package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : DepartmentDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 김수현
  * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
  * @프로그램 설명 : 학과 dto
  */
@Data
public class DepartmentDto {
	
		private Integer id;
		private String name;
		private Integer collegeId;

	}



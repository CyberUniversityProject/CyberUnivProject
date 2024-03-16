package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Department.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학과
  */
@Data
public class Department {

	private Integer id;
	private String name;
	private Integer collegeId;

}

package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : StudentListForm.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생 리스트보기 폼
  */
@Data
public class StudentListForm {
	
	private Integer deptId;
	private Integer studentId;
	private Integer page;

}

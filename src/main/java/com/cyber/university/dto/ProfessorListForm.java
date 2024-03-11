package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : ProfessorListForm.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수 리스트 보기
  */
@Data
public class ProfessorListForm {
	private Integer deptId;
	private Integer professorId;
	private Integer page;
	

}

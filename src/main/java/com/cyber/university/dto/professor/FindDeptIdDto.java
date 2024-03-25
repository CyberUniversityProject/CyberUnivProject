package com.cyber.university.dto.professor;

import lombok.Data;

/**
  * @FileName : FindDeptIdDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 23. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 단과대 아이디 조회
  */
@Data
public class FindDeptIdDto {
	private Integer professorId;
	private Integer deptId;
	private Integer colId;
	private String deptName;
	private String colName;
}

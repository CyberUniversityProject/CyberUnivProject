package com.cyber.university.dto;

import lombok.Data;

/**
 * 
  * @FileName : AllSubjectSearchFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 전체 강의조회 검색폼 DTO
 */
@Data
public class AllSubjectSearchFormDto {
	

	private Integer subYear;
	
	private Integer semester;
	
	private Integer deptId;
	
	private String name;
	
	private Integer page;

}

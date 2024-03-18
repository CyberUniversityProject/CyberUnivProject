package com.cyber.university.dto;

import com.cyber.university.utils.Define;

import lombok.Data;
/**
 * 
  * @FileName : CurrentSemesterSubjectSearchFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 현재 학기의 강의 검색 DTO
 */
@Data
public class CurrentSemesterSubjectSearchFormDto {

	private String type;
	private Integer deptId;
	private String name;
	
	private Integer subYear = Define.CURRENT_YEAR;
	private Integer semester = Define.CURRENT_SEMESTER;
	
	private Integer page;
}

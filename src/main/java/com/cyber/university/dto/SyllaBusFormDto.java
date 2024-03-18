package com.cyber.university.dto;

import lombok.Data;

/**
 * 
  * @FileName : SyllaBusFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의계획서 폼 DTO
 */
@Data
public class SyllaBusFormDto {
	
	
	private Integer subjectId;
	private String overview;
	private String objective;
	private String textbook;
	private String program;

}

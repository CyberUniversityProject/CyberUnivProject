package com.cyber.university.dto;

import lombok.Data;

/**
  * @FileName : AllGradeSearchFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 19. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 전체 성적 조회 검색 폼 DTO
  */
@Data
public class AllGradeSearchFormDto {	

	private Integer subYear;
	private Integer semester;
	private String type;

	// paging 처리 할 때 사용 -> 일단 보류
	private Integer page;
}

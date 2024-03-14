package com.cyber.university.dto.response;

import lombok.Data;
/**
 * 
  * @FileName : SubjectForProfessorDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의 정보 교수용
 */
@Data
public class SubjectForProfessorDto {
	
	private Integer id;
	private String name;
	private String subDay;
	private Integer startTime;
	private Integer endTime;
	private String roomId;

}

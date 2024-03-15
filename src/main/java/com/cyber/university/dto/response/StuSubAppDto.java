package com.cyber.university.dto.response;

import lombok.Data;

/**
 * 
  * @FileName : StuSubAppDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생이 수강한 과목에 관한 정보를 저장할 DTO
 */
@Data
public class StuSubAppDto {
	
	
	private Integer studentId;
	private Integer subjectId;
	private String subjectName;
	private String professorName;
	private Integer grades;

	private String subDay;
	private Integer startTime;
	private Integer endTime;
	private Integer numOfStudent;
	private Integer capacity;
	private String roomId;
	
	private Boolean status;

}

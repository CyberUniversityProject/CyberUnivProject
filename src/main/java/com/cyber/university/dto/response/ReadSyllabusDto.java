package com.cyber.university.dto.response;

import lombok.Data;
/**
 * 
  * @FileName : ReadSyllabusDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의계획서 조회
 */
@Data
public class ReadSyllabusDto {
	
	
	private Integer subjectId;
	private Integer professorId;
	private String name;
	private String subYear;
	private String semester;
	private Integer grades;
	private String type;
	private String subDay;
	private Integer startTime;
	private Integer endTime;
	private String roomId;
	private String collegeName;
	private String professorName;
	private String deptName;
	private String tel;
	private String email;
	private String overview;
	private String objective;
	private String textbook;
	private String program;

}

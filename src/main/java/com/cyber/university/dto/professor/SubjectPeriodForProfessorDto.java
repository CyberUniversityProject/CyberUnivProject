package com.cyber.university.dto.professor;

import lombok.Data;
/**
 * 
  * @FileName : SubjectPeriodForProfessorDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수의 과목 학기별 조회
 */
@Data
public class SubjectPeriodForProfessorDto {
	
	private Integer id;
	private Integer subYear;
	private Integer semester;

}

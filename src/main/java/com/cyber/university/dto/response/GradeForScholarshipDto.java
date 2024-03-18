package com.cyber.university.dto.response;

import lombok.Data;
/**
 * 
  * @FileName : GradeForScholarshipDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 장학금 유형 결정을 위해 성적 가져오는 DTO
 */

@Data
public class GradeForScholarshipDto {
	
	

	private Integer studentId;
	private Integer subYear;
	private Integer semester;
	private Double avgGrade;

}

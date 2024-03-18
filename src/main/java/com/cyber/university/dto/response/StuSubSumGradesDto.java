package com.cyber.university.dto.response;

import lombok.Data;
/**
 * 
  * @FileName : StuSubSumGradesDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생 총 성적 DTO
 */
@Data
public class StuSubSumGradesDto {
	

	private Integer studentId;
	private Integer sumGrades;

}

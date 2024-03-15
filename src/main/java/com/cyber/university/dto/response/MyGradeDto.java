package com.cyber.university.dto.response;

import lombok.Data;
/**
 * 
  * @FileName : MyGradeDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 내 성적 확인 DTO
 */
@Data
public class MyGradeDto {
	
	private Integer studentId;
	private Integer subYear;
	private Integer semester;
	private Integer sumGrades;  //이수 해야할 학점
	private Integer myGrades;	//내가 이수한 학점
	private float average;

	public String average() {
		String result = String.format("%.2f", average);
		return result;
	}

}

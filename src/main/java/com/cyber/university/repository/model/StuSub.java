package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : StuSub.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 수강내역
  */
@Data
public class StuSub {
	private Integer id;
	private Integer studentId;
	private Integer subjectId;
	private String grade;			// 학점
	private Integer completeGrade;	// 취득학점
}

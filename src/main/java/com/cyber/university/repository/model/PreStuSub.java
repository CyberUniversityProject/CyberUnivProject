package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : PreStuSub.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 예비 수강신청
  */

@Data
public class PreStuSub {
	
	private Integer studentId;
	private Integer subjectId;
	
	public PreStuSub(Integer studentId, Integer subjectId) {
		this.studentId = studentId;
		this.subjectId = subjectId;
	}

	public PreStuSub() {
	}

}

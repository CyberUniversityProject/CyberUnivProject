package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : SyllaBus.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 :강의계획서
  */

@Data
public class SyllaBus {
	private int subjectId;		// 과목 id
	private String overview;	// 수업개요
	private String objective;	// 강의 목표
	private String textbook;	// 교재
	private String program;		// 주별계획
}

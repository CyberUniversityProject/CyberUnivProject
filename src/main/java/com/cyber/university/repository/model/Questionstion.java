package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Questionstion.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 질문내용
  */
@Data
public class Questionstion {
	
	private Integer id;
	private String question1;
	private String question2;
	private String question3;
	private String question4;
	private String question5;
	private String question6;
	private String question7;
	private String sug_content;

}

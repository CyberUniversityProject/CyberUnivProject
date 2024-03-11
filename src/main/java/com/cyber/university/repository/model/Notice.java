package com.cyber.university.repository.model;

import java.sql.Timestamp;

import lombok.Data;

/**
  * @FileName : Notice.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 공지사항
  */

@Data
public class Notice {
	
	private Integer id;
	private String category;
	private String title;
	private String content;
	private Integer views;
	private Timestamp createdTime;
	
	private String uuidFilename;
	private String originFilename;
	
	public String setUpImage() {
		return "/images/uploads/" + uuidFilename;
	} 

}

package com.cyber.university.dto;

import java.sql.Timestamp;

import lombok.Data;


/**
  * @FileName : NoticeDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 공지사항 Dto
  */

@Data
public class NoticeDto {

	private Integer id;
	private String category;
	private String title;
	private String content;
	private Integer views;
	private Timestamp createdTime;
}

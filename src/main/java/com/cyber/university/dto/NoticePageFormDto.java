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
public class NoticePageFormDto {

	private Integer page;
	private String keyword;
	private String type;
}

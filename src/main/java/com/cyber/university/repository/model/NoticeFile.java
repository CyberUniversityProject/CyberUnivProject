package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : NoticeFile.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 공지 첨부파일
  */
@Data
public class NoticeFile {
	

	private Integer noticeId;
	private String originFilename;
	private String uuidFilename;

}

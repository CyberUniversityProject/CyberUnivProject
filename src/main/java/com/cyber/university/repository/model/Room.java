package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Room.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의실
  */

@Data
public class Room {
	private String id;
	private Integer collegeId;
}

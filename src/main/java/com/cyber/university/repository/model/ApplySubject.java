package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : ApplySubject.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 강의 등록
  */
@Data
public class ApplySubject {
	private Integer id;
	private Integer proId; // 교수 아이디
	private String subName; // 강의 이름
	private String proName; // 교수 이름
	private Integer subTime; // 총 강의 시간
	private String type; // 전공/교양
	private Integer subGrade; // 이수 학점
	private Integer capacity; // 총 인원
	private String approval; // 승인 여부
	private String reason; // 미승인 이유
}

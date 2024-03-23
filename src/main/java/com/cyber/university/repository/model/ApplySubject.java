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
	private Integer professorId; // 교수 아이디
	private String name; // 강의 이름
	private String roomId; // 강의실 명
	private Integer deptId; // 학과 명
	private String type; // 전공/교양
	private Integer startTime; // 강의 시작 시간
	private Integer endTime; // 강의 끝나는 시간
	private Integer subYear; // 강의 개설 년도
	private Integer semester; // 강의 개설 학기
	private String subDay; // 강의 요일
	private Integer grades; // 이수 학점
	private Integer capacity; // 총 인원
	private String approval; // 승인 여부
	private String reason; // 미승인 이유
}

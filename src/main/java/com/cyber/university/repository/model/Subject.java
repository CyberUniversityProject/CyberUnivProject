package com.cyber.university.repository.model;

import lombok.Data;

/**
  * @FileName : Subject.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 :강의
  */
@Data
public class Subject {
	private Integer id;
	private String name;
	private Integer professorId;
	private String roomId;
	private Integer deptId;
	private String type;			// 강의구분
	private Integer subYear;		// 개설연도
	private Integer semester;		// 개설학기
	private String subDay;			// 요일
	private Integer startTime;		// 강의 시작시간
	private Integer endTime;		// 강의 종료시간
	private Integer grades;			// 이수학점
	private Integer capacity;		// 수강 정원
	private Integer numOfStudent;	// 현재 신청 인원

}

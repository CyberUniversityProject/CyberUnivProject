package com.cyber.university.dto.professor;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Data;

/**
  * @FileName : MysubDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 교수 내 강의 조회 Dto
  */
@Data
public class SubInfoDto {
	
	private Integer id;
	private String name; // 강의 명
	private Integer professorId; // 교수 아이디
	private Integer roomId; // 강의실
	private Integer departmentId; // 학과번호
	private String type; // 타입
	private Integer subYear; // 개강 년도
	private Integer semester; // 개강 학기
	private String subDay; // 개강 요일
	private Integer startTime; // 시작 시간
	private Integer endTime; // 종료 시간
	private Integer grades; // 이수 학점
	private Integer capacity; // 수강 정원
	private Integer numOfStudent; // 현재 신청 인원
	
	
	public String formatStartTime() {
		SimpleDateFormat dateFormate = new SimpleDateFormat("HH:mm");
		String fomaterDate = dateFormate.format(startTime);
		
		return fomaterDate;
	}
	
	public String formatendTime() {
		SimpleDateFormat dateFormate = new SimpleDateFormat("HH:mm");
		String fomaterDate = dateFormate.format(startTime);
		
		return fomaterDate;
	}
	
}
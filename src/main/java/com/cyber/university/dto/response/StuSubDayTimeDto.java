package com.cyber.university.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
  * @FileName : StuSubDayTimeDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생이 수강하는 강의의 요일과 시간 DTO
 */
@Data
public class StuSubDayTimeDto {
	private Integer subjectId;
	private String subjectName;
	private String subDay;
	private Integer startTime;
	private Integer endTime;
	
	// startTime ~ endTime을 정수형 배열로 생성
	public List<Integer> timeList() {
		List<Integer> resultList = new ArrayList<>();
		
		for (int i = startTime; i <= endTime; i++) {
			resultList.add(i);
		}
		return resultList;
	}
	

}

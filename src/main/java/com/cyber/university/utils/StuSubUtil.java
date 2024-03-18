package com.cyber.university.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.cyber.university.dto.response.StuSubDayTimeDto;
import com.cyber.university.dto.response.StuSubSumGradesDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Subject;

/**
 * ]
  * @FileName : StuSubUtil.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 예비 수강신청 관련 유틸
 */
public class StuSubUtil {
	
	// 최대 수강 가능 학점을 초과하는지 확인하는 메서드
	public static void checkMaxGrades(Subject targetSubject, StuSubSumGradesDto stuSubSumGradesDto) {
		if (stuSubSumGradesDto != null) {
			int sumGrades = stuSubSumGradesDto.getSumGrades();
			int subGrades = targetSubject.getGrades();
			int totalGrades = sumGrades + subGrades;
			if (totalGrades > Define.MAX_GRADES) {
				throw new CustomRestfullException("신청 가능한 최대 학점을 초과했습니다.", HttpStatus.BAD_REQUEST);
			}
		}
	}
		
	// 신청하려는 강의와 현재 학생의 시간표가 겹치지 않는지 확인하는 메서드
	public static void checkNoTimeConflict(Subject targetSubject, List<StuSubDayTimeDto> dayTimeList) {
		for (StuSubDayTimeDto dayTimeDto : dayTimeList) {
			if (dayTimeDto.getSubDay().equals(targetSubject.getSubDay())) {
				if (isTimeConflict(dayTimeDto, targetSubject)) {
					throw new CustomRestfullException("이전에 신청한 강의와 시간이 중복됩니다.", HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
	
	// 시간이 겹치는지 확인하는 메서드
	private static boolean isTimeConflict(StuSubDayTimeDto dayTimeDto, Subject targetSubject) {
		if (dayTimeDto.getStartTime() >= targetSubject.getEndTime() ||
		    dayTimeDto.getEndTime() <= targetSubject.getStartTime()) {
			return false;
		}
		return true;
	}
}

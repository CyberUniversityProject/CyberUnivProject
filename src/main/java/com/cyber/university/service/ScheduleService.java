package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.ScheduleDto;
import com.cyber.university.dto.ScheduleFormDto;
import com.cyber.university.repository.interfaces.ScheduleRepository;
import com.cyber.university.repository.model.Schedule;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ScheduleService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Service
  */

@Slf4j
@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
/**
  * @Method Name : readSchedule
  * @작성일 : 2024. 3. 13.
  * @작성자 : 조유빈
  * @변경이력 : 3.13 생성
  * @Method 설명 : 학사일정 보기
  * @return
  */
	// 학사 일정 조회
	public List<Schedule> readSchedule() {
		List<Schedule> schedule = scheduleRepository.selectSchedule();
		log.info("schedule1 : " + schedule); // year=null, month=null
		return schedule;
	}
	
/**
  * @Method Name : createSchedule
  * @작성일 : 2024. 3. 14.
  * @작성자 : 조유빈
  * @변경이력 : 3.14 생성
  * @Method 설명 : 학사일정 생성
  * @param staffId
  * @param scheduleFormDto
  */
	public void createSchedule(Integer staffId, ScheduleFormDto scheduleFormDto) {
		// 생성자 + set~  안하고도 일단 등록은 잘됨
		
		int resultRowCount = scheduleRepository.insertScheduleForm(scheduleFormDto);
		if(resultRowCount != 1) {
			System.out.println("요청을 처리하지 못했습니다.");
		}	
	}
	
	// 학사일정 월에 있는 일정 조회
	public List<ScheduleDto> selectScheduleInMonth(){
		
		List<ScheduleDto> scheduleDto = scheduleRepository.selectScheduleInMonth();
		return scheduleDto;
	}
	
	// 월별 학사일정 조회
	public List<Schedule> readScheduleListByMonth(Integer month) {
		List<Schedule> scheduleList = scheduleRepository.selectListByMonth(month);
		return scheduleList;
	}
	
	// 학사일정 수정
	public int updateSchedule(ScheduleFormDto scheduleFormDto) {
		int resultRowCount = scheduleRepository.updateSchedule(scheduleFormDto);
		return resultRowCount;
	}
	
	// 학사일정 삭제
	public int deleteSchedule(Integer id) {
		int resultRowCount = scheduleRepository.deleteSchedule(id);
		return resultRowCount;
	}
	
	// 학사일정 상세페이지
	public ScheduleDto readScheduleById(Integer id) {
		ScheduleDto schedule = scheduleRepository.selectScheduleById(id);
		return schedule;
	}
}
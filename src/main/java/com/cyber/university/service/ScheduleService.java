package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.ScheduleDto;
import com.cyber.university.dto.ScheduleFormDto;
import com.cyber.university.repository.interfaces.ScheduleRepository;
import com.cyber.university.repository.model.Schedule;

/**
  * @FileName : ScheduleService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Service
  */

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
	public List<Schedule> readSchedule() {
		List<Schedule> schedule = scheduleRepository.selectSchedule();
		return schedule;
	}
	
	public ScheduleDto readScheduleById(Integer id){
		ScheduleDto schedule = scheduleRepository.selectScheduleById(id);
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
}

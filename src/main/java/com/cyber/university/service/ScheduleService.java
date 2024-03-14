package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	ScheduleRepository scheduleRepository;
	
	public List<Schedule> readSchedule() {
		List<Schedule> schedule = scheduleRepository.selectSchedule();
		return schedule;
	}
}

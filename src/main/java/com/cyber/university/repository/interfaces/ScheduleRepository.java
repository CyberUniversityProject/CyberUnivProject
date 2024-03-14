package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.repository.model.Schedule;

/**
  * @FileName : ScheduleRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Repository
  */

@Mapper
public interface ScheduleRepository {
	
	public List<Schedule> selectSchedule();
}

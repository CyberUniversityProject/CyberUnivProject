package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.ScheduleDto;
import com.cyber.university.dto.ScheduleFormDto;
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
	
	// 학사일정 등록 / Dto? Model?
	public int insertScheduleForm(ScheduleFormDto scheduleFormDto);
	// 학사일정 목록
	public List<Schedule> selectSchedule();
	// 학사일정 월에 있는 일정 조회
	public List<ScheduleDto> selectScheduleInMonth();
	// 월별 학사일정 조회
	public List<Schedule> selectListByMonth(Integer month);
	// 학사일정 수정
	public int updateSchedule(ScheduleFormDto scheduleFormDto);
	// 학사일정 삭제
	public int deleteSchedule(Integer id);
	
	
	
	
	
}

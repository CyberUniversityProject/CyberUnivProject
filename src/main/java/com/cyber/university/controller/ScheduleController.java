package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.repository.model.Schedule;
import com.cyber.university.service.ScheduleService;

/**
  * @FileName : ScheduleController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Controller
  */

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping("/")
	public String schedule(Model model) {
		List<Schedule> schedule = scheduleService.readSchedule();
		return "/schedule/schedule";
	}
	
	@GetMapping("/list")
	public String scheduleList(Model model) {
		List<Schedule> schedule = scheduleService.readSchedule();
		model.addAttribute("schedule", schedule);
		return "/schedule/scheduleList";
	}

}

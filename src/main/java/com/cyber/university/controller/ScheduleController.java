package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.ScheduleDto;
import com.cyber.university.dto.ScheduleFormDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.repository.model.Schedule;
import com.cyber.university.service.ScheduleService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ScheduleController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 조유빈
  * @변경이력 :
  * @프로그램 설명 : 학사일정 Controller
  */

@Slf4j
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ScheduleService scheduleService;
	
/**
  * @Method Name : schedule
  * @작성일 : 2024. 3. 13.
  * @작성자 : 조유빈
  * @변경이력 : 3.13 생성
  * @Method 설명 : 학사일정 페이지
  * @param model
  * @return
  */
	@GetMapping("")
	
	public String schedule(Model model) {
		List<Schedule> schedule = scheduleService.readSchedule();
		model.addAttribute("schedule", schedule); 
		return "/schedule/schedule";
	}
	
	@GetMapping("/list")
	public String scheduleList(Model model) {
		List<Schedule> schedule = scheduleService.readSchedule(); 
		model.addAttribute("schedule", schedule);
		return "/schedule/scheduleList";
	}
	
	@GetMapping("/detail")
	public String detailSchedule(Model model, @RequestParam("id") Integer id) {
		ScheduleDto schedule = scheduleService.readScheduleById(id);
		log.info("id:" + id);
		model.addAttribute("schedule", schedule);
		return "/schedule/scheduleDetail";
		
	}
	
	
/**
  * @Method Name : insertNotice
  * @작성일 : 2024. 3. 14.
  * @작성자 : 조유빈
  * @변경이력 : 3.14 생성
  * @Method 설명 : 학사일정 등록
  * @return
  */
	@GetMapping("/write")
	public String insertSchedule() {
		return "/schedule/scheduleWrite";
	}
	
	
	@PostMapping("/write")
	// ScheduleProc -> insertNotice
	public String insertSchedule(Model model, ScheduleFormDto scheduleFormDto) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		log.info("scheduleFormDto : " + scheduleFormDto);
		scheduleService.createSchedule(principal.getId(), scheduleFormDto);
		
		return "redirect:/schedule/list";
	}
	
	
/**
  * @Method Name : updateSchedule
  * @작성일 : 2024. 3. 15.
  * @작성자 : 조유빈
  * @변경이력 : 3.15 생성 
  * @Method 설명 : 학사일정 수정, 삭제
  * @return
  */
	// 수정
	@PostMapping("/update")
	public String updateSchedule(ScheduleFormDto scheduleFormDto) {
		scheduleService.updateSchedule(scheduleFormDto);
		
		return "redirect:/schedule/list";
	}
	
	// 삭제
	@GetMapping("/delete")
	public String deleteSchedule(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("id", id);
		scheduleService.deleteSchedule(id);
		
		return "redirect:/schedule/list";
	}
	

}

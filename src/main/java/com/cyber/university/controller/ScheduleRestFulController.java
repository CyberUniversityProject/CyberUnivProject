package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.repository.model.Schedule;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleRestFulController {
	
	@Autowired
	private final ScheduleService scheduleService;
	
	@GetMapping("/selectAll")
	public List<Schedule> selectAllSchedule(){
		
		
		return scheduleService.readSchedule();
	}

}

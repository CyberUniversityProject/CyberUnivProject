package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.dto.RoomWithCollegeDto;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomRestFulCountroller {
	
	@Autowired
	private final RoomService roomService;
	
	
	@GetMapping("/findAll")
	public List<RoomWithCollegeDto> findAll(){
		
		return roomService.findAll();
	}

}

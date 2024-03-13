package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.RoomDto;
import com.cyber.university.repository.model.Room;
import com.cyber.university.service.RoomService;

import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : RoomController.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 강의실 등록 controller
 * 
 */
@Slf4j
@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	// 화면 띄우기
	@GetMapping("/roomRegister")
	public String roomRegisterPage() {
		return "/room/roomRegister";
	}

	// 화면에서 자바로 데이터값 받아오기
	@PostMapping("/roomRegister")
	public String space(RoomDto inputData) {
		
		roomService.insert(inputData);
		
		return "redirect:/room/roomList";
	}

}

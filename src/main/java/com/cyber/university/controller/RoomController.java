package com.cyber.university.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Autowired
	private HttpSession httpSession;
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
	/**
	  * @FileName : RoomController.java
	  * @Project : CyberUniversity
	  * @Date : 2024. 3. 14. 
	  * @작성자 : 김수현
	  * @변경이력 :
	  * @프로그램 설명 : 강의실 List,삭제,수정
	  */
	@GetMapping("/roomList")
	public String roomList(Model model,
	                       @RequestParam(name = "page" ,defaultValue = "1") int page,
	                       @RequestParam(name = "size" ,defaultValue = "5") int size) {
	    // 페이지 번호와 페이지 크기를 이용하여 페이징된 강의실 목록 가져오기
	    List<Room> roomList = roomService.findAllRooms(page, size);
	    // 전체 페이지 수 계산
	    int totalPages = roomService.getTotalPages(size);
	    // 현재 페이지 번호와 전체 페이지 수를 모델에 추가
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    // 강의실 목록을 모델에 추가
	    model.addAttribute("roomList", roomList);
	    return "/room/roomList";
	}

    
    
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") String id,Model model) {
		roomService.deleteById(id);
		return "redirect:/room/roomList";
	}
	// 강의실 수정 페이지 띄우기
	@GetMapping("/roomUpdate/{id}")
	public String updateById(@PathVariable("id") String id,Model model) {
		Room room = roomService.findById(id);
		model.addAttribute("room", room);
		return "/room/roomUpdate";
	}
	// 강의실 수정 기능
	@PostMapping("/roomUpdate/{id}")
	public String updateRoomProc(@PathVariable("id") String id,Room room, Model model) {
		room.setId(id);
		roomService.updateById(room);
		return "redirect:/room/roomList";
	}

	// page뜨는지 확인
	@GetMapping("/checkPolicy")
	public void chekcPolicyScreen() {
		
	}
}

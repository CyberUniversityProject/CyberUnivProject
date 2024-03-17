package com.cyber.university.controller;

import java.util.List;

import com.cyber.university.dto.PageRequestDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Staff;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UserService userService;
	// 화면 띄우기
	@GetMapping("/roomRegister")
	public String roomRegisterPage(Model model) {

		PrincipalDto principal = (PrincipalDto) httpSession.getAttribute(Define.PRINCIPAL);

 		if (principal != null) {
			 if (principal.getUserRole().equals("staff")) {
				 Staff staffInfo = userService.readStaff(principal.getId());
				 model.addAttribute("userInfo", staffInfo);

			 }
		}
		/*User principal = (User)httpSession.getAttribute(Define.PRINCIPAL);

		if(principal == null) {
			throw new CustomRestfullException(Define.STAFF_PATHS, HttpStatus status);
		}*/
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
	// 강의실 전체 리스트 불러오기
	@GetMapping("/roomList")
	public String roomList(PageRequestDto pageRequest,Model model,@PathVariable String id) {

		if (pageRequest.getPage() <= 0) {
			pageRequest.setPage(1);  // 페이지가 0 이하일 경우 첫 페이지로 설정한다.
		}

		if (pageRequest.getSize() <= 0) {
			pageRequest.setSize(10); // 페이지 당 보여줄 개수
		}

		/*PageRequestDto<RoomDto> pageRequest = roomService.getfindwithallpaging(pageRequest, id);
		List<RoomDto> roomList = pageRequest.();*/

		List<Room> list = roomService.roomList();
		/*model.addAttribute(roomList);*/

		/*model.addAttribute("page", pageRequest.getPage());
		model.addAttribute("size", pageResonse.getSize());
		model.addAttribute("totalPages", pageResonse.getTotalPages());
		model.addAttribute("startPage", pageResonse.getStartPage());
		model.addAttribute("endPage", pageResonse.getEndPage());*/
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
	
}

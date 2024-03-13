package com.cyber.university.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.repository.model.Break;
import com.cyber.university.service.StuStatService;
import com.cyber.university.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * 
  * @FileName : BrackController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 휴학 신청 관련 컨트롤러
 */

@Controller
@RequestMapping("/break")
public class BreakController {
	
	
	@Autowired
	private HttpSession session;
	
	
//	@Autowired
//	private BreakService breakService;
	
	@Autowired
	private StuStatService stuStatService;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private CollegeService collegeService;
	
	
	
//	// 휴, 복학 처리되지 않은 신청내역 페이지
//	@GetMapping("/list/staff")
//	public String breakListByState(Model model) {
//		List<Break> breakList = 
//	}

}

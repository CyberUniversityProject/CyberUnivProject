package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyber.university.dto.LeaveAppDto;
import com.cyber.university.dto.LeaveStudentInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Break;
import com.cyber.university.repository.model.Student;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.BreakService;
import com.cyber.university.service.CollegeService;
import com.cyber.university.service.StuStatService;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@RequiredArgsConstructor
public class BreakController {

	@Autowired
	private final HttpSession session;

	@Autowired
	private final BreakService breakService;

	@Autowired
	private final StuStatService stuStatService;

	@Autowired
	private final UserService userService;

	@Autowired
	private final CollegeService collegeService;

	// 휴, 복학 처리되지 않은 신청내역 페이지
	@GetMapping("/list/staff")
	public String breakListByState(Model model) {
		List<Break> breakList = breakService.readByStatus("처리중");

		model.addAttribute("breakAppList", breakList);

		return "break/listStaff";
	}

	/**
	 * 휴학 신청 처리 (교직원)
	 */
	@PostMapping("/update/{id}")
	public String updateBreakApp(@PathVariable(name = "id") Integer id, @RequestParam(name = "status") String status) {

		System.out.println("id값" + id);
		System.out.println("상태값" + status);
		breakService.updateById(id, status);

		return "redirect:/break/list/staff";
	}

	/**
	 * 
	 * @Method Name : breakDetail
	 * @작성일 : 2024. 3. 13.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 휴학 신청서 자세히보기
	 */
	@GetMapping("/detail/{id}")
	public String breakDetail(@PathVariable(name = "id", required = false) Integer id, Model model) {

		Break breakApp = breakService.readById(id);
		model.addAttribute("breakApp", breakApp);

		Student studentInfo = userService.readStudent(breakApp.getStudentId());
		model.addAttribute("student", studentInfo);

		// 학과 이름
		String deptName = collegeService.readDeptById(studentInfo.getDeptId()).getName();
		model.addAttribute("deptName", deptName);

		// 단과대 이름
		String collName = collegeService
				.readCollById(collegeService.readDeptById(studentInfo.getDeptId()).getCollegeId()).getName();
		model.addAttribute("collName", collName);

		return "break/breakDetail";
	}

}

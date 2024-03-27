package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.CollegeDto;
import com.cyber.university.dto.DepartmentDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.repository.model.Department;
import com.cyber.university.repository.model.Staff;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.CollegeService;
import com.cyber.university.service.DepartmentService;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : DepartmentController.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 김수현
 * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
 * @프로그램 설명 : 학과 등록 controller
 * 
 */
@Slf4j
@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
	
	@Autowired
	private final DepartmentService departmentService;
	
	@Autowired
	private final CollegeService collegeService;
	
	@Autowired
	private final HttpSession httpSession;
	
	@Autowired
	private final UserService userService;
	
	// 화면 띄우기
	@GetMapping("/departmentRegister")
	public String departmentRegisterPage(Model model) {
		// 관리자 session
		PrincipalDto principal = (PrincipalDto) httpSession.getAttribute(Define.PRINCIPAL);
		Staff staff = userService.readStaff(principal.getId());
		model.addAttribute("staff",staff);
		
		return "/department/departmentRegister";
	}
	
	// 화면에서 자바로 데이터값 받아오기
	@PostMapping("/departmentRegister")
	public String inputData(DepartmentDto inputData) {	
		departmentService.insert(inputData);
		return "redirect:/department/departmentList";
	}
	/**
	 * @FileName : DepartmentController.java
	 * @Project : CyberUniversity
	 * @Date : 2024. 3. 19.
	 * @작성자 : 김수현
	 * @변경이력 : 
	 * @프로그램 설명 : 학과리스트 페이징
	 * 
	 */
	@GetMapping("/departmentList")
	public String departmentList(Model model,
								@RequestParam(name = "page"  , defaultValue = "1") int page,
								@RequestParam(name = "size" , defaultValue = "5") int size) {
		// 관리자 session
		PrincipalDto principal = (PrincipalDto) httpSession.getAttribute(Define.PRINCIPAL);
		Staff staff = userService.readStaff(principal.getId());
		model.addAttribute("staff",staff);
		
		// 페이지 번호와 페이지 크기를 이용하여 페이징 된 학과목록 가져오기
		List<Department> list = departmentService.findAllDepartments(page, size);
		// 전체 페이지 수 계산
		int totalPages = departmentService.getTotalPages(size);
		// 현재 페이지 번호와 전체 페이지 수를 모델에 추가
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		// 학과 목록을 모델에 추가
		model.addAttribute("departmentList", list);
		
		return "/department/departmentList";
	}
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id,Model model) {
		
		// 관리자 session
		PrincipalDto principal = (PrincipalDto) httpSession.getAttribute(Define.PRINCIPAL);
		Staff staff = userService.readStaff(principal.getId());
		model.addAttribute("staff",staff);
		
		departmentService.deleteById(id);
		return "redirect:/department/departmentList";
	}
	// 학과 수정 페이지 띄우기
	@GetMapping("/departmentUpdate/{id}")
	public String updateById(@PathVariable("id") Integer id, Model model) {
		// 관리자 session
		PrincipalDto principal = (PrincipalDto) httpSession.getAttribute(Define.PRINCIPAL);
		Staff staff = userService.readStaff(principal.getId());
		model.addAttribute("staff",staff);

		Department department = departmentService.findById(id);
		model.addAttribute("department", department);
		return "/department/departmentUpdate";
	}
	// 학과 수정
	@PostMapping("/departmentUpdate/{id}")
	public String updateByIdProc(@PathVariable("id") Integer id, Department department, Model model) {
		department.setId(id);
		
		departmentService.updateById(department);
		return "redirect:/department/departmentList";
	}
	
	
}

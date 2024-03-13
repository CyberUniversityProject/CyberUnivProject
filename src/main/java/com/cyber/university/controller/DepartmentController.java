package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.DepartmentDto;
import com.cyber.university.repository.model.Department;
import com.cyber.university.service.DepartmentService;


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
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	// 화면 띄우기
	@GetMapping("/departmentRegister")
	public String departmentRegisterPage() {
		return "/department/departmentRegister";
	}
	
	// 화면에서 자바로 데이터값 받아오기
	@PostMapping("/departmentRegister")
	public String inputData(DepartmentDto inputData) {
		
		log.info("insert" + toString());
		departmentService.insert(inputData);
		
		return "redirect:/department/departmentList";
	}

	@GetMapping("/departmentList")
	public String departmentList(Model model) {
		
		List<Department> list = departmentService.departmentList();
		model.addAttribute("departmentList", list); 
		
		return "/department/departmentList";
	}
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		departmentService.deleteById(id);
		return "redirect:/department/departmentList";
	}
	// 학과 수정 페이지 띄우기
	@GetMapping("/departmentUpdate/{id}")
	public String updateById(@PathVariable("id") Integer id, Model model) {
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

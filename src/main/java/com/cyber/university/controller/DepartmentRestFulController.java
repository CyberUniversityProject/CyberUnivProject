package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.dto.DepartmentWithCollegeDto;
import com.cyber.university.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentRestFulController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/findAll")
	public List<DepartmentWithCollegeDto> findAll (){
		return departmentService.findAll();
	}

}

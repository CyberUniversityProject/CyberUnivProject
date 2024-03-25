package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.dto.DepartmentWithCollegeDto;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentRestFulController {
	
	@Autowired
	private final DepartmentService departmentService;
	
	@GetMapping("/findAll")
	public List<DepartmentWithCollegeDto> findAll (){
		return departmentService.findAll();
	}

}

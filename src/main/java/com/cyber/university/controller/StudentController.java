package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.service.StudentService;

/**
  * @FileName : StudentController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 학생 Controller
  */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	

	/**
	  * @Method Name : myInfoPage
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 내 정보 조회 페이지
	  */
	@GetMapping("/myInfo")
	private String myInfoPage() {
		
		return "/student/myInfo";

	}
	
	
}

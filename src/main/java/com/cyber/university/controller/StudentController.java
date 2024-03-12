package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.service.StudentService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/**
	  * @Method Name : myInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 내 정보 조회 페이지
	  */
	@GetMapping("/myInfo")
	public String myInfo(@CookieValue(name="id", required = false)Integer userId, Model model ) {
		
		log.info(userId + "userId");
		
		if(userId == null) {
			// TODO: NOT_FOUND_ID -> 얘기해보고 로그인을 해주세요 하고 메인으로 돌려보낼까?
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		StudentInfoDto studentInfoDto = studentService.findByStudentId(userId);
		
		log.info("controller studentInfo : "+studentInfoDto);
		model.addAttribute("studentInfo",studentInfoDto);
		
		return "/user/studentInfo";
	}
	
	
}

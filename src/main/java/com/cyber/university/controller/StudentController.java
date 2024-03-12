package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyber.university.dto.StudentInfoDto;
import com.cyber.university.dto.UserInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Student;
import com.cyber.university.service.StudentService;
import com.cyber.university.service.UserService;
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
	@Autowired
	private UserService userService;
	
	/**
	  * @Method Name : myInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 내 정보 조회 페이지
	  */
	@GetMapping("/myInfo")
	public String myInfo(@CookieValue(name="id", required = false)Integer userId, Model model ) {
		
		// TODO: ?? 쿠키가 저장이안됏는지 아이디가 안뜰때가있음 왜그럴까? 어떻게 처리해야할까?
		
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		StudentInfoDto studentInfoDto = studentService.findByStudentId(userId);
		
		log.info("controller studentInfo : "+studentInfoDto);
		model.addAttribute("studentInfo",studentInfoDto);
		
		return "/user/studentInfo";
	}
	
	/**
	  * @Method Name : updateInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 내 정보 업데이트
	  */
	@PostMapping("/updateInfo")
	@ResponseBody
	public String updateInfo(@CookieValue(name="id", required = false)Integer userId, @RequestBody StudentInfoDto studentInfoDto) {
		
		log.info("controller in!");
		
		if (userId == null) {

			log.info("controller userId null!");
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("controller service before!");
			studentService.updateStudentInfo(userId,studentInfoDto);

			log.info("controller service after!");
		return "redirect:/user/studentInfo";
	}
	
	/**
	  * @Method Name : studentPasswordPage
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : studentPassword page
	  */
	// TODO: USER SERVICE 유효성 검사 할 게 있을까? -> user service selectById ? principal DTO ?? 뭔지 쭈녁한테 확인
	@GetMapping("/password")
	private String studentPasswordPage(@CookieValue(name="id", required = false) Integer userId, Model model ) {

		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserInfoDto userInfoDto = userService.findById(userId);
		
		log.info("student controller password : " + userInfoDto);
		
		model.addAttribute("userInfo",userInfoDto);
		
		return"/user/studentPassword";
	}
	
	
}

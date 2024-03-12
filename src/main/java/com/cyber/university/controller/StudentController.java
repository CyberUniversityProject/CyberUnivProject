package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.ChangePasswordDto;
import com.cyber.university.dto.UserInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.service.StudentService;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
	@Autowired
	private HttpSession session;
	@Autowired
	private PasswordEncoder passwordEncoder; 

	
	/**
	  * @Method Name : myInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 내 정보 조회 페이지
	  */
	@GetMapping("/myInfo")

	public String myInfo(Model model ) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();

		log.info(userId + "userId");
		
		if(userId == null) {
			// TODO: NOT_FOUND_ID -> 얘기해보고 로그인을 해주세요 하고 메인으로 돌려보낼까?
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		StudentInfoDto studentInfoDto = studentService.findByStudentId(userId);
		
		log.info("controller studentInfo : "+studentInfoDto);
		model.addAttribute("studentInfo",studentInfoDto);
		
		return "/student/studentInfo";
	}
	

	/**
	  * @Method Name : updateInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 내 정보 업데이트
	  */
	@PostMapping("/updateInfo")
	public String updateInfo(@RequestBody StudentInfoDto studentInfoDto) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info("controller in!");
		
		if (userId == null) {

			log.info("controller userId null!");
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("controller service before!");
			studentService.updateStudentInfo(userId,studentInfoDto);

			log.info("controller service after!");
		return "redirect:/student/studentInfo";
	}
	
	/**
	  * @Method Name : studentPasswordPage
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : studentPassword page
	  */
	@GetMapping("/password")
	private String studentPasswordPage(Model model ) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserInfoDto userInfoDto = userService.findById(userId);
		
		log.info("student controller password : " + userInfoDto);
		
		model.addAttribute("userInfo",userInfoDto);
		
		
		return"/student/studentPassword";
	}
	
	/**
	  * @Method Name : updatePassword
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : student password 수정
	  */
	@PostMapping("/updatePass")
	private String updatePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto, BindingResult bindingResult) {

		log.info("student controller updatePass start , changePasswordDTO" + changePasswordDto);
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		StringBuilder sb = new StringBuilder();

		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("애초에 왜 자꾸 유효성 검사 결과만 나오는거같죠?");
		log.info("박경진~~" + bindingResult.getGlobalErrors().toString());
		log.info("박경진~~" + bindingResult.getGlobalErrors());
		
		// 비밀번호 유효성 검사 결과 확인
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error ->{
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		log.info("유효성 검사 이후로 넘어왔을까요? ");
		log.info("beforepass : "+changePasswordDto.getBeforePassword());
		log.info("principalpass : "+principal.getPassword());
		// 현재 로그인된 비밀번호와 변경 전 비밀번호를 비교하여 일치하는지 확인
		if (!passwordEncoder.matches(changePasswordDto.getBeforePassword(), principal.getPassword())) {
			throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		
		log.info("로그인된 비밀번호와 변경전 비밀번호 이후로 넘어왔을까요? ");
		
		// 변경 비밀번호와 체크 비밀번호가 같지 않을 경우 
		if(!changePasswordDto.getAfterPassword().equals(changePasswordDto.getPasswordCheck())) {
			throw new CustomRestfullException(Define.WRONG_CHECK_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		log.info("변경 비밀번호와 체크비밀번호 다른치 체크가 됐을까요? ");
		
		changePasswordDto.setId(principal.getId());
		changePasswordDto.setAfterPassword(passwordEncoder.encode(changePasswordDto.getAfterPassword()));
		
		log.info("student controller changePasswordDto",changePasswordDto);
		
		userService.updatePassword(changePasswordDto);
		
		return "redirect:/student/password";
	}
	
	/**
	  * @Method Name : leaveOfAbsenceRegisterPage
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학 신청 페이지
	  */
	@GetMapping("/leaveOfAbsenceRegister")
	private String leaveOfAbsenceRegisterPage() {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return "/student/leaveOfAbsenceRegister";
	}
	
}

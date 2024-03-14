package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyber.university.dto.ChangePasswordDto;
import com.cyber.university.dto.LeaveAppDto;
import com.cyber.university.dto.LeaveStudentInfoDto;
import com.cyber.university.dto.UserInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Break;
import com.cyber.university.service.BreakService;
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
	@Autowired
	private BreakService breakService;

	
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
	
	// TODO : 준혁 코드로 변경 -> 프론트도 확인
	@PostMapping("/updatePass")
	private String updatePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto, BindingResult bindingResult) {

		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		
		StringBuilder sb = new StringBuilder();

		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// 비밀번호 유효성 검사 결과 확인
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error ->{
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인된 비밀번호와 변경 전 비밀번호를 비교하여 일치하는지 확인
		if (!passwordEncoder.matches(changePasswordDto.getBeforePassword(), principal.getPassword())) {
			throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		
		
		// 변경 비밀번호와 체크 비밀번호가 같지 않을 경우 
		if(!changePasswordDto.getAfterPassword().equals(changePasswordDto.getPasswordCheck())) {
			throw new CustomRestfullException(Define.WRONG_CHECK_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		
		changePasswordDto.setId(principal.getId());
		changePasswordDto.setAfterPassword(passwordEncoder.encode(changePasswordDto.getAfterPassword()));
		
		
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
	@GetMapping("/leaveOfAbsence")
	private String leaveOfAbsenceRegisterPage(Model model) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	    // 휴학 횟수 조회(count해서 front에서 3회이상이면 main으로 화면전환)
		int overLeaveCount = studentService.getLeaveCount(userId);
		model.addAttribute("overLeaveCount", overLeaveCount);
		
		int hasPendingLeave = studentService.hasPendingLeave(userId);
		model.addAttribute("pendingLeaveCount",hasPendingLeave);
		
		LeaveStudentInfoDto leaveStudentInfoDto = studentService.findLeaveStudentById(userId);
		
		model.addAttribute("studentInfo", leaveStudentInfoDto);
		log.info("controller leavestudentdto : " + leaveStudentInfoDto);
		return "/student/leaveOfAbsenceRegister";
	}
	
	/**
	  * @Method Name : leaveOfAbsenceApplication
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학신청서 제출
	  */
	@PostMapping("/leaveApp")
	@ResponseBody
	private ResponseEntity<?> createleaveOfAbsence(@RequestBody LeaveAppDto leaveAppDto) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(userId == leaveAppDto.getStudentId()) {
			throw new CustomRestfullException(Define.SUBMIT_CHECK_ID, HttpStatus.BAD_REQUEST);
		}

		if(leaveAppDto.getFromYear() == null ||leaveAppDto.getFromSemester() == null
				|| leaveAppDto.getToYear() ==null || leaveAppDto.getToSemester() == null) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("입력되지 않은 정보를 확인해주세요");
		}
		
		int result = studentService.createLeaveApp(userId,leaveAppDto);
		
		
		if(result == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("휴학 신청 중 오류가 발생했습니다.");
		} else { return ResponseEntity.ok("휴학 신청에 성공 했습니다.");}
	}
	
	/**
	  * @Method Name : leaveOfAbsenceList
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학 신청 내역(학생)
	  */
	@GetMapping("/leaveOfAbsenceList")
	private String leaveOfAbsenceList(Model model) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LeaveStudentInfoDto studentInfoDto= studentService.findLeaveStudentById(userId);
		model.addAttribute("student",studentInfoDto);
		
		// 휴학 정보
		List<Break> breakList= breakService.findBreakByStudentId(userId);
		log.info("controller user id로 조회한 break :", breakList.toString());
		
		model.addAttribute("leaveOfAbsenceList", breakList);
		
		
		return "/student/leaveOfAbsenceList";
	}
	
	/**
	  * @Method Name : deleteLeaveOfAbsence
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학 신청 취소
	  */
	@GetMapping("/deleteLeaveApp/{id}")
	private String deleteLeaveOfAbsence(@PathVariable(name = "id", required=false) Integer id) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		breakService.deleteLeaveAppById(userId,id);
		
		return "redirect:/student/leaveOfAbsenceList";
	}
	
	
	/**
	  * @Method Name : tuitionPage
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 등록금 페이지
	  */
	@GetMapping("/tuition")
	private String tuitionPage(Model model) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = principal.getId();
		log.info(userId + "userId");
		
		if(userId == null) {
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LeaveStudentInfoDto studentInfoDto= studentService.findLeaveStudentById(userId);
		model.addAttribute("student",studentInfoDto);
		return "/student/tuitionList";
	}
}

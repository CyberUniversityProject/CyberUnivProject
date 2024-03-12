package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.repository.model.User;
import com.cyber.university.service.ProfessorService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : ProfessorController
 * author         : 장명근
 * date           : 2024/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/11         장명근          최초 생성
 */
@Controller
@RequestMapping("/professor")
@Slf4j
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	  * @Method Name : professerInfoPage
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 정보 조회 페이지 요청
	  */
	@GetMapping("/info")
	public String professerInfoPage(@CookieValue(name = "id", required = false)Integer userId , Model model) {
		
		log.info("controller cookie id : "+ userId);
			
		ProfessorInfoDto professorInfo = professorService.selectProfessorInfoWithCollegeAndDepartment(userId);
		model.addAttribute("professorInfo", professorInfo);
		
		return "/professor/professorInfo";
	}
	
	/**
	  * @Method Name : updateUserPage
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 정보 수정 페이지 요청
	  */
	@GetMapping("/update")
	public String updateUserPage(@CookieValue(name = "id", required = false)Integer userId, Model model) {
	
		UpdateProfessorInfoDto professorInfo = professorService.selectProfessorInfo(userId);
		model.addAttribute("professorInfo", professorInfo);
		
		
		return "/professor/updateUser";
	}
	
	/**
	  * @Method Name : updateUserProc
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 정보 수정 처리
	  */
	@PostMapping("/update")
	public String updateUserProc(@CookieValue(name = "id", required = false)Integer userId, User user) {
	    
		if (userId != null) {
			professorService.updateProfessorInfo(userId, user);			
		}
	    
		return "redirect:/professor/Info";
	}
}

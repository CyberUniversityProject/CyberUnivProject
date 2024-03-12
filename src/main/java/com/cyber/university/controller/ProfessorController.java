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
import com.cyber.university.handler.exception.CustomRestfullException;
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
	public String professerInfoPage(Model model) {
		Object principalObject = session.getAttribute(Define.PRINCIPAL);

	    // PrincipalDto가 아닌 경우에는 로그인이 되어 있지 않은 것으로 처리
	    if (!(principalObject instanceof PrincipalDto)) {
	        // 로그인 페이지로 리다이렉트 또는 로그인이 필요한 메시지를 보여줄 수 있음
	        return "redirect:/login"; // 또는 다른 로그인이 필요한 페이지로 리다이렉트
	    }

	    // PrincipalDto인 경우에는 User 객체로 형변환하여 아이디를 가져옴
	    PrincipalDto principal = (PrincipalDto) principalObject;
	    
	    int userId = principal.getId();
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
	public String updateUserPage(Model model) {
		Object principalObject = session.getAttribute(Define.PRINCIPAL);

	    // PrincipalDto가 아닌 경우에는 로그인이 되어 있지 않은 것으로 처리
	    if (!(principalObject instanceof PrincipalDto)) {
	        // 로그인 페이지로 리다이렉트 또는 로그인이 필요한 메시지를 보여줄 수 있음
	        return "redirect:/login"; // 또는 다른 로그인이 필요한 페이지로 리다이렉트
	    }

	    // PrincipalDto인 경우에는 User 객체로 형변환하여 아이디를 가져옴
	    PrincipalDto principal = (PrincipalDto) principalObject;
	    
	    int userId = principal.getId();
	    
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
	public String updateUserProc(UpdateProfessorInfoDto dto) {
		
		professorService.updateProfessorInfo(dto); 	
		
	    
		return "redirect:/professor/info";
	}
}

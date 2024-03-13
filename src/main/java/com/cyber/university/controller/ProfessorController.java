package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.SubInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.handler.exception.CustomPageException;
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
	    
		professorService.updateProfessorInfo(userId, user);
	    
		return "redirect:/professor/professorInfo";
	}
	
	/**
	  * @Method Name : applySubjectPage
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 등록 페이지 요청
	  */
	@GetMapping("/apply")
	public String applySubjectPage() {
		
		return "/professor/applysubject";
	}
	
	/**
	  * @Method Name : applySubjectProc
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청
	  */
	@PostMapping("/apply")
	public String applySubjectProc(ApplySubjectDto dto, @RequestParam("type") String type) {
		
		Object principalObject = session.getAttribute(Define.PRINCIPAL);

	    if (!(principalObject instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    PrincipalDto principal = (PrincipalDto) principalObject;
	    int userId = principal.getId();
		
		dto.setProId(userId);

		if (dto.getSubName() == null || dto.getSubName().isEmpty()) {
			throw new CustomRestfullException("강의 명을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getProName() == null || dto.getProName().isEmpty()) {
			throw new CustomRestfullException("교수 명을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getTime() == null || dto.getTime() < 0) {
			throw new CustomRestfullException("잘못된 강의 시간입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getSubName() == null || dto.getSubName().isEmpty()) {
			throw new CustomRestfullException("강의 명을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getType() == null || dto.getType().isEmpty()) {
			throw new CustomRestfullException("전공 또는 교양을 선택하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if (!"전공".equals(type) && !"교양".equals(type)) {
	        throw new CustomRestfullException("전공 또는 교양을 선택하세요.", HttpStatus.BAD_REQUEST);
	    }
		
		if (dto.getSubGrade() == null || dto.getSubGrade() < 0) {
			throw new CustomRestfullException("잘못된 이수 학점입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getCapacity() == null || dto.getCapacity() < 0) {
			throw new CustomRestfullException("잘못된 인원 수 입니다.", HttpStatus.BAD_REQUEST);
		}
		
		professorService.insertApplySubject(dto, userId);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/mysub")
	public String mySubPage(Model model) {
		Object principalObject = session.getAttribute(Define.PRINCIPAL);
		System.out.println("principalObject" + principalObject);

	    if (!(principalObject instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    PrincipalDto principal = (PrincipalDto) principalObject;
		System.out.println("principal" + principal);
	    int professorId = principal.getId();
	    System.out.println("professorId" + professorId);
	    List<SubInfoDto> subInfoList = professorService.selectMysub(professorId);
	    System.out.println("모델 정보 : " + subInfoList);
		model.addAttribute("subInfoList", subInfoList);
		
	    
		return "/professor/mySubPage";
	}
	
	
	
	
	
	
}

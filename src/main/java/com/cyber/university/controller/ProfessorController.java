package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.MysubjectDetailDto;
import com.cyber.university.dto.professor.SubInfoDto;
import com.cyber.university.dto.professor.SubjectNameDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateStudentSubDetailDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Student;
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
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
			
		ProfessorInfoDto professorInfo = professorService.selectProfessorInfoWithCollegeAndDepartment(principal.getId());
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
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		
		UpdateProfessorInfoDto professorInfo = professorService.selectProfessorInfo(principal.getId());
		model.addAttribute("professorInfo", professorInfo);
		
		
		return "/professor/updateUser";
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
				
		
		if (dto.getSubGrade() == null || dto.getSubGrade() < 0) {
			throw new CustomRestfullException("잘못된 이수 학점입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getCapacity() == null || dto.getCapacity() < 0) {
			throw new CustomRestfullException("잘못된 인원 수 입니다.", HttpStatus.BAD_REQUEST);
		}
		
		professorService.insertApplySubject(dto, userId);
		
		return "redirect:/";
	}
	
	
	/**
	  * @Method Name : mySubPage
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 조회
	  */
	@GetMapping("/mysub")
	public String mySubPage(Model model) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

	    if (!(principal instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    int professorId = principal.getId();
	    List<SubInfoDto> subInfoList = professorService.selectMySub(professorId);
		model.addAttribute("subInfoList", subInfoList);		
	    
		return "/professor/mySubPage";
	}
		
	
	/**
	  * @Method Name : updatePwPage
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 비밀번호 변경 페이지 조회
	  */
	@GetMapping("/updatepw")
	public String updatePwPage() {
		return "/professor/updatePW";
	}
	
	/**
	  * @Method Name : subjectPage
	  * @작성일 : 2024. 3. 15.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 성적 평가 페이지 요청
	  */
	@GetMapping("/subject/{subjectId}")
	public String subjectPage(@PathVariable("subjectId") Integer subjectId, Model model) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

	    if (!(principal instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    List<MysubjectDetailDto> mySubEvaluation = professorService.selectMySubDetailList(subjectId);
	    model.addAttribute("mySubEvaluation", mySubEvaluation);
		
	    SubjectNameDto subjectName = professorService.selectSubjectName(subjectId);
	    model.addAttribute("subjectName", subjectName);
	    
		return "/professor/subjectDetail";
	}
	
	@GetMapping("/subject/{subjectId}/{studentId}")
	public String updateStudentSubjdectPage(@PathVariable("subjectId") Integer subjectId, 
											@PathVariable("studentId") Integer studentId, 
											Model model) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

	    if (!(principal instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
		
	    Student studentInfo = professorService.selectByStudentId(studentId);
	    model.addAttribute("studentInfo", studentInfo);
	    
		
		return "/professor/updateStudentDetail";
	}
	
	@PostMapping("/subject/{subjectId}/{studentId}")
	public String updateStudentSubjdectProc(@PathVariable("subjectId") Integer subjectId, 
											@PathVariable("studentId") Integer studentId,
											@RequestParam("grade") String grade,
											UpdateStudentSubDetailDto dto) {
		
		
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

	    if (!(principal instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    professorService.updateStudentSubDetail(studentId, subjectId, dto);
		
		return "redirect:/professor/subject/{subjectId}";
	}
}

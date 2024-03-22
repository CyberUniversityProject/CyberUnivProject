package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.SyllaBusFormDto;
import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.MyEvaluationDto;
import com.cyber.university.dto.professor.MysubjectDetailDto;
import com.cyber.university.dto.professor.SubjectNameDto;
import com.cyber.university.dto.professor.SubjectPeriodForProfessorDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateStudentSubDetailDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.dto.response.ReadSyllabusDto;
import com.cyber.university.dto.response.SubjectForProfessorDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.PageReq;
import com.cyber.university.repository.model.PageRes;
import com.cyber.university.repository.model.Student;
import com.cyber.university.service.ProfessorService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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
		
		if (dto.getSubTime() == null || dto.getSubTime() < 0) {
			throw new CustomRestfullException("올바른 강의 시간입니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		if (dto.getSubGrade() == null || dto.getSubGrade() < 0) {
			throw new CustomRestfullException("올바른 이수 학점입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getCapacity() == null || dto.getCapacity() < 0) {
			throw new CustomRestfullException("올바른 인원 수 입니다.", HttpStatus.BAD_REQUEST);
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
	    List<SubjectPeriodForProfessorDto> semesterList = professorService.selectSemester(principal.getId());
	    SubjectPeriodForProfessorDto subjectPeriodForProfessorDto = new SubjectPeriodForProfessorDto();
	    subjectPeriodForProfessorDto.setSubYear(Define.CURRENT_YEAR);
	    subjectPeriodForProfessorDto.setSemester(Define.CURRENT_SEMESTER);
	    subjectPeriodForProfessorDto.setId(principal.getId());
	    model.addAttribute("semesterList", semesterList);
	    
	    List<SubjectForProfessorDto> subjectList = professorService.selectSubjectBySemester(subjectPeriodForProfessorDto);
	    model.addAttribute("subjectList", subjectList);

	    return "/professor/mySubPage";
	}
	
	@PostMapping("/mysub")
	public String subjectListProc(Model model, @RequestParam ("period") String period) {
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		List<SubjectPeriodForProfessorDto> semesterList = professorService.selectSemester(principal.getId());
		String[] strs = period.split("year");
		SubjectPeriodForProfessorDto subjectPeriodForProfessorDto = new SubjectPeriodForProfessorDto();
		subjectPeriodForProfessorDto.setSubYear(Integer.parseInt(strs[0]));
	    subjectPeriodForProfessorDto.setSemester(Integer.parseInt(strs[1]));
	    subjectPeriodForProfessorDto.setId(principal.getId());
	    List<SubjectForProfessorDto> subjectList = professorService.selectSubjectBySemester(subjectPeriodForProfessorDto);
	    model.addAttribute("semesterList", semesterList);
	    model.addAttribute("subjectList", subjectList);
	    
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
	
	/**
	  * @Method Name : updateStudentSubjdectPage
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 성적 입력 페이지 요청
	  */
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
	
	/**
	  * @Method Name : updateStudentSubjdectProc
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 성적 입력 기능
	  */
	@PostMapping("/subject/{subjectId}/{studentId}")
	public String updateStudentSubjdectProc(@PathVariable("subjectId") Integer subjectId, 
											@PathVariable("studentId") Integer studentId,
											@RequestParam("grade") String grade,
											UpdateStudentSubDetailDto dto) {
		
		if (dto.getAbsent() == null || dto.getAbsent() < 0) {
	    	throw new CustomRestfullException("결석 횟수를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
	    
	    if (dto.getLateness() == null || dto.getLateness() < 0) {
	    	throw new CustomRestfullException("지각 횟수를 입력해주세요", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (dto.getHomework() == null || dto.getHomework() < 0) {
	    	throw new CustomRestfullException("과제 점수를 입력해주세요", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (dto.getMidExam() == null || dto.getMidExam() < 0) {
	    	throw new CustomRestfullException("중간고사 점수를 입력해주세요", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (dto.getFinalExam() == null || dto.getFinalExam() < 0) {
	    	throw new CustomRestfullException("기말고사 점수를 입력해주세요", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (dto.getConvertedMark() == null || dto.getConvertedMark() < 0) {
	    	throw new CustomRestfullException("환산 점수를 입력해주세요", HttpStatus.BAD_REQUEST);
	    }
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

	    if (!(principal instanceof PrincipalDto)) {
	    	
	        return "redirect:/login";
	    }
	    
	    Integer sumScore = dto.getMidExam() + dto.getFinalExam();
	    System.out.println("sumScore : " + sumScore);
	    
	    
	    
	    if (dto.getConvertedMark() != sumScore) {
			throw new CustomRestfullException("잘못된 환산 점수 입니다", HttpStatus.BAD_REQUEST);
	    }	    
	    
	    if (dto.getLateness() >= 5) {
	        grade = "F";
	    }
	    
	    professorService.updateStudentSubDetail(studentId, subjectId, dto);
		
		return "redirect:/professor/subject/{subjectId}";
	}
	
	/**
	  * @Method Name : readEvaluationPage
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가 화면 요청
	  */
	@GetMapping("/readevaluation")
	public String readEvaluationPage(Model model, PageReq pageReq) {
		
		if (pageReq.getPage() <= 0) {
			pageReq.setPage(1); // 페이지가 0 이하일 경우 첫 페이지로 설정한다
		}

		if (pageReq.getSize() <= 0) {
			pageReq.setSize(5); // 페이지 당 보여줄 개수
		}
		
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);		
		List<MyEvaluationDto> subjectName = professorService.readSubjectName(principal.getId());
		model.addAttribute("subjectName", subjectName);
		
		PageRes<MyEvaluationDto> pageRes = professorService.readEvaluationByProfessorId(pageReq, principal.getId());
		List<MyEvaluationDto> eval = pageRes.getContent();
		model.addAttribute("eval", eval);
		
		model.addAttribute("page", pageReq.getPage());
		model.addAttribute("size", pageRes.getSize());
		model.addAttribute("totalPages", pageRes.getTotalPages());
		model.addAttribute("startPage", pageRes.getStartPage());
		model.addAttribute("endPage", pageRes.getEndPage());
		return "/professor/myEvaluation";
	}
	
	/**
	  * @Method Name : readEvaluation
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 과목별 강의 평가 조회
	  */
	@PostMapping("/readevaluation")
	public String readEvaluation(Model model, HttpServletRequest httpServletRequest, PageReq pageReq, 
								@RequestParam("subjectId") String subjectId) {
		
		if (pageReq.getPage() <= 0) {
			pageReq.setPage(1); // 페이지가 0 이하일 경우 첫 페이지로 설정한다
		}

		if (pageReq.getSize() <= 0) {
			pageReq.setSize(5); // 페이지 당 보여줄 개수
		}
				
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		String name = httpServletRequest.getParameter("subjectId");
		
		List<MyEvaluationDto> subjectName = professorService.readSubjectName(principal.getId());
		model.addAttribute("subjectName", subjectName);
		
		PageRes<MyEvaluationDto> pageRes = professorService.readEvaluationByProfessorIdAndName(pageReq, principal.getId(), name);		
		List<MyEvaluationDto> eval = pageRes.getContent();		
		model.addAttribute("eval", eval);
		
		model.addAttribute("page", pageReq.getPage());
		model.addAttribute("size", pageRes.getSize());
		model.addAttribute("totalPages", pageRes.getTotalPages());
		model.addAttribute("startPage", pageRes.getStartPage());
		model.addAttribute("endPage", pageRes.getEndPage());
		
		return "/professor/myEvaluation";
		
	}
	
	/**
	  * @Method Name : syllabusPage
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의계획서 조회
	  */
	@GetMapping("/syllabus/{subjectId}")
	public String syllabusPage(Model model, @PathVariable("subjectId") Integer subjectId) {
		ReadSyllabusDto readSyllabusDto = professorService.selectSyllabusBySubjectId(subjectId);
	
		 
		model.addAttribute("syllabus", readSyllabusDto);
		return "professor/syllabus";
	}
	
	/**
	  * @Method Name : updateSyllabusPage
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의계획서 수정 페이지 조회
	  */
	@GetMapping("/syllabus/update/{subjectId}")
	public String updateSyllabusPage(Model model, @PathVariable("subjectId") Integer subjectId) {
		
		ReadSyllabusDto readSyllabusDto = professorService.selectSyllabusBySubjectId(subjectId);
		System.out.println("readSyll"); 
		model.addAttribute("syllabus", readSyllabusDto);
		return "professor/updateSyllabus";
	}
	
	/**
	  * @Method Name : createSyllabusProc
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의계획서 수정 기능
	  */
	@PutMapping("/syllabus/update/{subjectId}")
	public String createSyllabusProc(SyllaBusFormDto dto, @PathVariable("subjectId") Integer subjectId) {
		professorService.updateSyllabus(dto);
		
		return "redirect:/professor/syllabus/" + subjectId;
	}
	
}

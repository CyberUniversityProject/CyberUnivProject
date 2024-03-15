package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.CollTuitFormDto;
import com.cyber.university.dto.SubjectFormDto;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.service.StaffService;

/**
 * 
 * @FileName : StaffController.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 14.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 교직원(admin 역할) 컨트롤러
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@GetMapping("/subject")
	public String subject(Model model, @RequestParam(name = "crud", required = false) String crud) {
		model.addAttribute("crud", crud);
		List<Subject> subjectList = staffService.readSubject();
		List<College> collegeList = staffService.readCollege();
		if (collegeList.isEmpty()) {
			model.addAttribute("collegeList", null);
		} else {
			model.addAttribute("collegeList", collegeList);
		}
		if (subjectList.isEmpty()) {
			model.addAttribute("subjectList", null);
		} else {
			model.addAttribute("subjectList", subjectList);
		}
		return "/staff/subject";
	}

	/**
	 * 
	 * @return 강의 입력 기능
	 */
	@PostMapping("/subject")
	public String insertSubject(SubjectFormDto subjectFormDto) {
		staffService.createSubjectAndSyllabus(subjectFormDto);
		return "redirect:/staff/subject?crud=select";
	}

	/**
	 * 
	 * @return 강의 삭제 기능
	 */
	@GetMapping("/subjectDelete")
	public String deleteSubject(Model model, @RequestParam(name = "id") Integer id) {
		model.addAttribute("id", id);
		staffService.deleteSubject(id);
		return "redirect:/staff/subject?crud=select";
	}

	/**
	 * 
	 * @return 강의 수정 기능
	 */
	@PutMapping("/subject")
	public String updateSubject(SubjectFormDto subjectFormDto) {
		staffService.updateSubject(subjectFormDto);
		return "redirect:/staff/subject?crud=select";
	}
	
	
	
	
	/**
	 * 
	 * @return 단과대별 등록금 페이지
	 */
	@GetMapping("/tuition")
	public String collTuit(Model model, @RequestParam(name = "crud", required = false) String crud) {
		model.addAttribute("crud", crud);
		List<CollTuitFormDto> collTuitList = staffService.readCollTuit();
		List<College> collegeList = staffService.readCollege();
		if (collegeList.isEmpty()) {
			model.addAttribute("collegeList", null);
		} else {
			model.addAttribute("collegeList", collegeList);
		}
		if (collTuitList.isEmpty()) {
			model.addAttribute("collTuitList", null);
		} else {
			model.addAttribute("collTuitList", collTuitList);
		}
		return "/staff/collTuit";
	}

	/**
	 * 
	 * @return 단과대별 등록금 입력 기능
	 */
	@PostMapping("/tuition")
	public String insertcollTuit(CollTuitFormDto collTuitFormDto) {
		staffService.createCollTuit(collTuitFormDto);
		return "redirect:/staff/tuition?crud=insert";
	}

	/**
	 * 
	 * @return 단과대 등록금 삭제 기능
	 */
	@GetMapping("/tuitionDelete")
	public String deleteCollTuit(Model model, @RequestParam(name = "collegeId") Integer collegeId) {
		model.addAttribute("collegeId", collegeId);
		staffService.deleteCollTuit(collegeId);
		return "redirect:/staff/tuition?crud=insert";
	}

	/**
	 * 
	 * @return 단과대 등록금 수정 기능
	 */
	@PutMapping("/tuitionUpdate")
	public String updateCollTuit(CollTuitFormDto collTuitFormDto) {
		staffService.updateCollTuit(collTuitFormDto);
		return "redirect:/staff/tuition?crud=insert";
	}

}

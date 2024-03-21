package com.cyber.university.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.AllSubjectSearchFormDto;
import com.cyber.university.dto.response.ReadSyllabusDto;
import com.cyber.university.dto.response.SubjectDto;
import com.cyber.university.repository.model.Department;
import com.cyber.university.service.CollegeService;
import com.cyber.university.service.ProfessorService;
import com.cyber.university.service.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private ProfessorService professorService;

	// 모든 강의 조회 (모든 연도-학기에 대해서)
	@GetMapping("/list/{page}")
	public String readSubjectList(Model model, @PathVariable(name = "page") Integer page) {

		// 강의 리스트 (전체)
		List<SubjectDto> subjectList = subjectService.readSubjectList();

		int subjectCount = subjectList.size();
		model.addAttribute("subjectCount", subjectCount);
		// 총 페이지 수
		int pageCount = (int) Math.ceil(subjectCount / 20.0);
		model.addAttribute("pageCount", pageCount);
		// 현재 페이지
		model.addAttribute("page", page);

		List<SubjectDto> subjectListLimit = subjectService.readSubjectListPage((page - 1) * 20);
		model.addAttribute("subjectList", subjectListLimit);

		// 필터에 사용할 전체 학과 정보
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거)
		List<String> subNameList = new ArrayList<>();
		for (SubjectDto subject : subjectList) {
			if (subNameList.contains(subject.getName()) == false) {
				subNameList.add(subject.getName());
			}
		}
		model.addAttribute("subNameList", subNameList);

		return "/subject/allSubList";
	}

	// 전체 강의 목록에서 필터링
	@GetMapping("/list/search")
	public String readSubjectListSearch(Model model, @Validated AllSubjectSearchFormDto allSubjectSearchFormDto) {

		// 강의 리스트
		List<SubjectDto> subjectList = subjectService.readSubjectListSearch(allSubjectSearchFormDto);
		int subjectCount = subjectList.size();
		model.addAttribute("subjectCount", subjectCount);
		model.addAttribute("subjectList", subjectList);

		// 필터에 사용할 전체 학과 정보
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거)
		List<String> subNameList = new ArrayList<>();
		for (SubjectDto subject : subjectService.readSubjectList()) {
			if (subNameList.contains(subject.getName()) == false) {
				subNameList.add(subject.getName());
			}
		}
		model.addAttribute("subNameList", subNameList);

		return "/subject/allSubList";
	}
	
	
	// 강의계획서
	@GetMapping("/syllabus/{subjectId}")
	public String readSyllabus(Model model, @PathVariable(name = "subjectId") Integer subjectId) {
		ReadSyllabusDto readSyllabusDto = professorService.readSyllabus(subjectId);
		if (readSyllabusDto.getOverview() != null) {
			readSyllabusDto.setOverview(readSyllabusDto.getOverview().replace("\r\n", "<br>"));
		}
		if (readSyllabusDto.getObjective() != null) {
			readSyllabusDto.setObjective(readSyllabusDto.getObjective().replace("\r\n", "<br>"));
		}
		if (readSyllabusDto.getProgram() != null) {
			readSyllabusDto.setProgram(readSyllabusDto.getProgram().replace("\r\n", "<br>"));
		}
		model.addAttribute("syllabus", readSyllabusDto);

		return "/professor/syllabus";
	}

}

package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.CollegeDto;
import com.cyber.university.repository.model.College;
import com.cyber.university.service.CollegeService;
import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : CollegeController.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 김수현
 * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
 * @프로그램 설명 : 단과대학 등록 controller
 * 
 */
@Slf4j
@Controller
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	// 화면띄우기
	@GetMapping("/collegeRegister")
	public String collegeRegisterPage() {
		return "/college/collegeRegister";
	}

	// 화면에서 자바로 데이터값 받아오기
	@PostMapping("/collegeRegister")
	public String inputData(CollegeDto inputData) {
		College college = new College();
		college.setName(inputData.getName());
		
		String name = collegeService.insert(college);
		return "redirect:/college/collegeList";
	}
	// 단과대학 목록 불러오기
	@GetMapping("/collegeList")
	public String collegeList(Model model) {
		List<College> list; 
		model.addAttribute("collegeList", collegeService.collegeList());
		System.out.println("collegeList" + model);
		return "/college/collegeList";
	}
	/**
	  * @FileName : CollegeController.java
	  * @Project : CyberUniversity
	  * @Date : 2024. 3. 12. 
	  * @작성자 : 김수현
	  * @변경이력 :
	  * @프로그램 설명 : 단과대학 삭제,수정
	  */
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		System.out.println("delete" + id);
		collegeService.deleteById(id);
		System.out.println("delete" + id);
		return "redirect:/college/collegeList";
	}
	// 단과대학 수정 페이지띄우기
	@GetMapping("/collegeUpdate/{id}")
	public String updateById(@PathVariable("id") Integer id,Model model) {
		log.info("수정페이지");
		College college = collegeService.findById(id);
		log.info("수정페이지");
		model.addAttribute("college", college);
		log.info("수정페이지");
		return "/college/collegeUpdate";
	}
	// 단과대학 수정 기능
	@PostMapping("/collegeUpdate/{id}")
	public String updateByIdProc(@PathVariable("id") Integer id,College college,Model model) {
		college.setId(id);
		
		collegeService.updateById(college);
		return "redirect:/college/collegeList";
	}

}

package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.repository.model.ApplySubject;
import com.cyber.university.service.ApplySubjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/applySubject")
@RequiredArgsConstructor
public class ApplySubjectController {
	
	
	@Autowired
	private final ApplySubjectService applySubjectService;
	
	
	@GetMapping("/list")
	public String applySubjectList(Model model){
		
		List<ApplySubject> list = applySubjectService.findAllList();
		
		model.addAttribute("list", list);
		
		return "/staff/applySubjectList";
	}
	
	
	 // 상세조회
    @GetMapping("/detail/{id}")
    public String applySubjectDetail(@PathVariable(name = "id") int id, Model model) {
    	// 서비스 상세조회 호출
    	
    	ApplySubject applySubject = applySubjectService.findById(id);
    	
    	model.addAttribute("subject", applySubject);
    	
    	return "staff/applySubjectDetail";
    }
    
    
    
    @PostMapping("/updateApproval/{id}")
    public void updateApproval(@PathVariable(name = "id") int id, @RequestParam(name = "approval") String approval) {
    	 applySubjectService.updateApproval(id, approval);
    }

}

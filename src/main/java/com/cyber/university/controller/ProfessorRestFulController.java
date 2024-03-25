package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.dto.professor.ProfessorAndSubjectFormDto;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
public class ProfessorRestFulController {
	
	
	@Autowired
	private final ProfessorService professorService;
	
	
	@GetMapping("/findAll")
	public List<ProfessorAndSubjectFormDto> findAllProfessor(){
		
		return professorService.findAllProfessor();
	}

}

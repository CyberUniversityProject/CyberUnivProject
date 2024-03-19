package com.cyber.university.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.EvaluationInfoDto;
import com.cyber.university.repository.interfaces.EvaluationRepository;
import com.cyber.university.repository.model.Question;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : EvaluationService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 19. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 강의평가 서비스
  */
@Service
@Slf4j
public class EvaluationService {
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	
	
	/**
	  * @Method Name : findEvaluationInfoByStudentId
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가를 위한 강의 및 교수, 학생 정보 조회 
	  */
	public EvaluationInfoDto findEvaluationInfoByStudentId(Integer subjectId,Integer studentId) {
		
		log.info("evaluation service in");

		Map<String, Object> map = new HashMap<>();
		map.put("subjectId", subjectId);
		map.put("studentId", studentId);
		
		EvaluationInfoDto evaluationInfoDto = evaluationRepository.selectEvaluationInfoByStudentId(map);

		log.info("evaluation service evaluation info dto : " + evaluationInfoDto);
		return evaluationInfoDto;
	}



	/**
	  * @Method Name : findAllQuestion
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 강의평가 질문 항목
	  */
	public Question findQuestion() {
		Question question = evaluationRepository.selectQuestion();
		return question;
	}

}

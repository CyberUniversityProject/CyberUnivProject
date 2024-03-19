package com.cyber.university.repository.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.EvaluationInfoDto;
import com.cyber.university.repository.model.Question;

/**
  * @FileName : EvaluationRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 19. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 강의평가 레파지토리
  */

@Mapper
public interface EvaluationRepository {

	/**
	  * @Method Name : selectEvaluationInfoByStudentId
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가를 위한 강의 관련 정보 조회
	  */
	public EvaluationInfoDto selectEvaluationInfoByStudentId(Map<String, Object> params);

	/**
	  * @Method Name : selectAllQuestion
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 강의평가 질문 항목 리스트 조회
	  */
	public Question selectQuestion();

}

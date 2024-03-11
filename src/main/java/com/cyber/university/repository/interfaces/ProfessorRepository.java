package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.repository.model.Professor;


/**
  * @FileName : ProfessorRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수 레파지토리
  */
@Mapper
public interface ProfessorRepository {
	
	/**
	 * 
	  * @Method Name : selectByProfessorId
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : id로 교수 조회
	 */
	public List<Professor> selectByProfessorId(ProfessorListForm professorListForm);
	
	/**
	 * 
	  * @Method Name : selectByDepartmentId
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 페이지별, 학과별 교수조회
	 */
	public List<Professor> selectByDepartmentId(ProfessorListForm professorListForm);
	
	
	/**
	 * 
	  * @Method Name : selectProfessorList
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 페이지별 교수 조회
	 */
	public List<Professor> selectProfessorList(ProfessorListForm professorListForm);
	
	/**
	 * 
	  * @Method Name : selectProfessorAmountByDeptId
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 페이징 처리 학과별 교수 조회
	 */
	public Integer selectProfessorAmountByDeptId(Integer deptId);
	
	/**
	  * @Method Name : selectProfessorAmount
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 전체 교수 조회
	 */
	public Integer selectProfessorAmount();

}

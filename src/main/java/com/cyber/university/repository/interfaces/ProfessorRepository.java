package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

<<<<<<< HEAD

import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import java.util.List;

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

	// 24.03.11 교수 내 정보 조회
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id);

	// 24.03.11 교수 개인 정보 조회
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id);

	// 24.03.11 교수 정보 수정
	public UpdateProfessorInfoDto updateProfessorInfo(UpdateProfessorInfoDto updateDto);

	// 24.03.11 비밀번호 찾기
	public String selectPassword(Integer id);

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
=======
import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;

/**
  * @FileName : ProfessorRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 교수 Repository
  */
@Mapper
public interface ProfessorRepository {
	
	// 24.03.11 교수 내 정보 조회
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id);
	
	// 24.03.11 교수 개인 정보 조회
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id);
	
	// 24.03.11 교수 정보 수정
	public UpdateProfessorInfoDto updateProfessorInfo(UpdateProfessorInfoDto updateDto);
	
	// 24.03.11 비밀번호 찾기
	public String selectPassword(Integer id);
}
>>>>>>> myounggeun

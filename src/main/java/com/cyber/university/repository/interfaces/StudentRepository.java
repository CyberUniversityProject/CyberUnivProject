package com.cyber.university.repository.interfaces;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.CreateStudentDto;
import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;

import com.cyber.university.dto.StudentListForm;
import com.cyber.university.dto.UserUpdateDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.dto.response.UserInfoForUpdateDto;
import com.cyber.university.repository.model.Student;

/**
 * @FileName : StudentRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 :학생 레파지토리
 */

@Mapper
public interface StudentRepository {

	/**
	 * 
	 * @Method Name : selectByStudentId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 : 학번으로 학생 조회
	 * @Method 설명 :
	 */
	public List<Student> selectByStudentId(StudentListForm studentListForm);

	/**
	 * 
	 * @Method Name : selectIdList
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 전체 학생의 id만 가져오기
	 */
	public List<Integer> selectIdList();

	/**
	 * 
	 * @Method Name : selectByDepartmentId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 :페이지, 과별 학생조회
	 */
	public List<Student> selectByDepartmentId(StudentListForm studentListForm);

	/**
	 * 
	 * @Method Name : selectStudentAmountByDeptId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 :페이징 처리 위한 과 학생 수 조회
	 */
	public Integer selectStudentAmountByDeptId(Integer deptId);

	/**
	 * 
	 * @Method Name : selectStudentAmount
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 :페이징 처리 위한 전체 학생 수 조회
	 */
	public Integer selectStudentAmount();

	/**
	 * 
	 * @Method Name : selectStudentList
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 :페이지별 학생 조회
	 */
	public List<Student> selectStudentList(StudentListForm studentListForm);
	
	
	
	/**
	  * @Method Name : findStudentById
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : id로 student 조회 
	  */
	public StudentInfoDto findStudentById(Integer userId);
	
	/**
	  * @Method Name : updateStudentInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 정보 수정
	  */
	public int updateStudentInfo(Map<String, Object> params);
	

	
	/**
	 * 
	  * @Method Name : insertToStudent
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : cu_student insert
	 */
	public int insertToStudent(CreateStudentDto createStudentDto);
	
	
	/**
	 * 
	  * @Method Name : selectIdByCreateStudentDto
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : cu_student에서 id 값 받아오기
	 */
	public Integer selectIdByCreateStudentDto(CreateStudentDto createStudentDto);
	
	/**
	 * 
	  * @Method Name : selectIdByNameAndEmail
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : id 찾기
	 */
	public Integer selectIdByNameAndEmail(FindIdFormDto findIdFormDto);
	
	
	
	/**
	 * 
	  * @Method Name : selectStudentByIdAndNameAndEmail
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 비밀번호 발급 Model
	 */
	public Integer selectStudentByIdAndNameAndEmail(FindPasswordFormDto findPasswordFormDto);
	
	
	
	/**
	 * 
	  * @Method Name : selectStudentInfoById
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생정보 단건 조회
	 */
	public StudentInfoDto selectStudentInfoById(Integer id);
	
	
	/**
	 * 
	  * @Method Name : selectByUserId
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생정보 가져오기
	 */
	public UserInfoForUpdateDto selectByUserId(Integer userId);
	
	
	/**
	 * 
	  * @Method Name : updateStudent
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생정보 업데이트
	 */
	public int updateStudent(UserUpdateDto userUpdateDto);
	
	
	/**
	 * 
	  * @Method Name : selectByStudentId
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 특정학생 정보 가져오기
	 */
	public Student selectByStudentId(Integer studentId);



}
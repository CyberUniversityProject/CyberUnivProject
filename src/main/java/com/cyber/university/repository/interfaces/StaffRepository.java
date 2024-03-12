package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.CreateStaffDto;
import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;
import com.cyber.university.repository.model.Staff;


/**
  * @FileName : StaffRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교직원 레파지토리
  */
@Mapper
public interface StaffRepository {
	
	/**
	 * 
	  * @Method Name : insertToStaff
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : cu_staff에 insert
	 */
	public int insertToStaff(CreateStaffDto createStaffDto);
	
	
	/**
	 * 
	  * @Method Name : selectIdByCreateStaffDto
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : cu_staff 에서 자동 생성된 id 받아오기
	 */
	public Integer selectIdByCreateStaffDto(CreateStaffDto createStaffDto);
	
	
	/**
	 * 
	  * @Method Name : selectStaffById
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교직원 단건조회
	 */
	public Staff selectStaffById(Integer Id);
	
	
	/**
	 * 
	  * @Method Name : selectIdByNameAndEmail
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 아이디 찾기
	 */
	public Integer selectIdByNameAndEmail(FindIdFormDto findIdFormDto);
	
	
	
	/**
	 * 
	  * @Method Name : selectStaffByIdAndNameAndEmail
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 비밀번호 발급용 Model
	 */
	public Integer selectStaffByIdAndNameAndEmail(FindPasswordFormDto findPasswordFormDto);

}

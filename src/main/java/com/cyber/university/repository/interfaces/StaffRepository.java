package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.CreateStaffDto;


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

}

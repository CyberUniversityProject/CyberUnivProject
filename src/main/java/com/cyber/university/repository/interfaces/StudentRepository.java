package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.StudentInfoDto;

/**
  * @FileName : StudentRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 학생 Repository
  */
@Mapper
public interface StudentRepository {
	
	/**
	  * @Method Name : findStudentById
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : id로 student 조회 
	  */
	public StudentInfoDto findStudentById(Integer userId);
	
}

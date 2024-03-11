package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.repository.interfaces.StudentRepository;

/**
  * @FileName : StudentService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 박경진
  * @변경이력 :
  * @프로그램 설명 : 학생 Service
  */
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	  * @Method Name : findByStudentId
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : studnetId로 학생 정보 조회
	  */
	public void findByStudentId() {
		// TODO Auto-generated method stub

	}
}

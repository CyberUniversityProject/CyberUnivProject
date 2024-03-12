package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.StuStatRepository;
import com.cyber.university.repository.interfaces.StudentRepository;

/**
  * @FileName : stuStatService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학적 상태변경 서비스
  */
@Service
public class StuStatService {
	
	@Autowired
	private StuStatRepository stuStatRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Transactional
	public void createFirstStatus(Integer studentId) {

		int resultRowCount = stuStatRepository.insert(studentId, "재학", "9999-01-01", null);

		if (resultRowCount != 1) {
			throw new CustomRestfullException("학적 상태 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}

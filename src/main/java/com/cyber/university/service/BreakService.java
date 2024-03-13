package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.repository.interfaces.BreakRepository;
import com.cyber.university.repository.model.Break;

/**
 * 
  * @FileName : BreakService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 휴, 복학 서비스
 */

@Service
public class BreakService {
	
	
	@Autowired
	private BreakRepository breakRepository;
	
	@Autowired
	private StuStatService statService;
	
	
	
	// 교직원용 _ 처리되지 않은 휴학 신청 내역 조회
	@Transactional
	public List<Break> readByStatus(String status){
		List<Break> breakList = breakRepository.selectByStatus(status);
		
		return breakList;
	}
	
	
	// 특정 휴학 신청서 조회
	@Transactional
	public Break readById(Integer id) {
		Break breakEntity = breakRepository.selectById(id);
		
		return breakEntity;
	}
	

}

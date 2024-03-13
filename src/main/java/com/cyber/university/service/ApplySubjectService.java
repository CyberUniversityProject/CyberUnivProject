package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.repository.interfaces.ApplySubjectRepository;
import com.cyber.university.repository.model.ApplySubject;


/**
 * 
  * @FileName : ApplySubjectService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수 강의 신청 서비스
 */
@Service
public class ApplySubjectService {
	
	@Autowired
	private ApplySubjectRepository applySubjectRepository;
	
	
	// 전체조회
	@Transactional
	public List<ApplySubject> findAllList(){
		return applySubjectRepository.findAll();
	}
	
	
	// 상세조회
	@Transactional
    public ApplySubject findById(int id) {
    	// db 상세조회 호출
    	ApplySubject applySubject = applySubjectRepository.findById(id);
    	
    	return applySubject;
    }
	
	
	// 강의 개설 승인
	@Transactional
	public void updateApproval(Integer id, String approval) {
		applySubjectRepository.updateApproval(id, approval);
	}
	
	
	// 강의 개설 반려
	@Transactional
	public void updateReason(Integer id, String reason) {
		applySubjectRepository.updateReason(id, reason);
	}
}

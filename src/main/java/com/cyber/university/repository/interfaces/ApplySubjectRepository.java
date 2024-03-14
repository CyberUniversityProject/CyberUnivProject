package com.cyber.university.repository.interfaces;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.repository.model.ApplySubject;

/**
 * 
  * @FileName : ApplySubjectRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수 강의 신청 레파지토리
 */
@Mapper
public interface ApplySubjectRepository {
	
	
	// 교수들이 신청한 강의 전체조회
	public List<ApplySubject> findAll();
	
	
	 // 상세조회(1건조회)
    public ApplySubject findById(int id);
    
    
    // 강의 개설 승인
    public void updateApproval(@Param("id") Integer id, @Param("approval") String approval);
    
    
    // 강의 개설 거절(이유작성)
    public void updateReason(@Param("id") Integer id, @Param("reason") String reason);

}

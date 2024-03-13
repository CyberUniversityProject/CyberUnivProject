package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.BreakFormDto;
import com.cyber.university.repository.model.Break;

/**
 * 
  * @FileName : BreakRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 휴 복학 레파지토리
 */
@Mapper
public interface BreakRepository {
	
	
	// 등록 - 학생 휴학신청
	public int insert(BreakFormDto breakFormDto);
	
	
	// 교직원 전용 - 처리되지 않은 휴학 신청 내역 조회
	public List<Break> selectByStatus(String status);
	
	
	// 휴학신청내역 단건조회
	public Break selectById(Integer id);
	
	
	// 교직원 전용 - 휴학처리
	public int updateById(@Param("id") Integer id, @Param("status") String status);
	
	

}

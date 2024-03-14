package com.cyber.university.repository.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.BreakFormDto;
import com.cyber.university.dto.LeaveStudentInfoDto;
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



	// 학생의 휴학 신청 조회하기
	public List<Break> selectByStudentId(Integer studentId);

	// 처리되지 않은 휴학 신청 조회하기 (교직원용)
	public List<Break> selectByStatus(String status);

	// 특정 휴학 신청서 조회하기
	public Break selectById(Integer id);

	// 휴학 신청 취소하기 (학생용)
	public int deleteById(Integer id);

	// 휴학 신청 처리하기 (교직원용)
	public int updateById(@Param("id") Integer id, @Param("status") String status);

	/**
	  * @Method Name : countByStatusAndUserId
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : id로 조회해서 status가 처리중인 것을 카운팅 
	  */
	public int countByStatusAndUserId(Integer userId);

}

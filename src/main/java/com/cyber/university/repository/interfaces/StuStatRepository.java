package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName : StuStatRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 12.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 학적 상태 레파지토리
 */
@Mapper
public interface StuStatRepository {

	// 학생의 학적 상태 생성
	public int insert(@Param("studentId") Integer studentId, @Param("status") String status,
			@Param("toDate") String toDate, @Param("breakAppId") Integer breakAppId);

}

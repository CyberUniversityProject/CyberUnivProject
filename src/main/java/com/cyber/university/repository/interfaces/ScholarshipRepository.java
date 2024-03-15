package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.repository.model.Scholarship;
import com.cyber.university.repository.model.StuSch;

/**
 * 
 * @FileName : ScholarshipRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 15.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 장학금 레파지토리
 */
@Mapper
public interface ScholarshipRepository {

	// 학생의 특정 학기 장학금 유형에 따른 최대 지원 금액
	public Scholarship selectByStudentIdAndSemester(@Param("studentId") Integer studentId,
			@Param("schYear") Integer schYear, @Param("semester") Integer semester);

	// 이번 학기 장학금 유형 결정
	public int insertCurrentSchType(StuSch stuSch);

}

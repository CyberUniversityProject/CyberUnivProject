package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.response.StudentInfoStatListDto;
import com.cyber.university.repository.model.StuStat;

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

	// 해당 학생의 모든 학적 변동 내역 조회
	public List<StuStat> selectByStudentIdOrderbyIdDesc(Integer studentId);

	// 학생의 기존 학적 상태의 to_date를 now()로 변경
	public int updateOldStatus(Integer id);

	// 학생 내정보 조회에 학적변동리스트
	public List<StudentInfoStatListDto> selectStuStatListBystudentId(Integer studentId);

}

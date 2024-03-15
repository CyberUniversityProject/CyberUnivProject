package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.response.GradeDto;
import com.cyber.university.dto.response.GradeForScholarshipDto;
import com.cyber.university.dto.response.MyGradeDto;

/**
 * 
 * @FileName : GradeRespository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 15.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 성적 레파지토리
 */
@Mapper
public interface GradeRespository {

	public List<GradeDto> selectSubYearByStudentId(Integer studentId);

	// 학생이 수강한 학기 조회
	public List<GradeDto> selectSemesterByStudentId(Integer studentId);

	// 금학기 성적 조회
	public List<GradeDto> selectGradeDtoBySemester(@Param("studentId") Integer studentId,
			@Param("semester") Integer semester, @Param("subYear") Integer subYear);

	// 학기별 성적조회 (전체 조회)
	public List<GradeDto> selectGradeDtoByStudentId(Integer studentId);

	// 학기별 성적조회 (선택 조회)
	public List<GradeDto> selectGradeDtoBytype(@Param("studentId") Integer studentId, @Param("subYear") Integer subYear,
			@Param("semester") Integer semester, @Param("type") String type);

	// 전체 찾는거
	public List<GradeDto> selectGradeDtoByStudentIdAndSubYear(@Param("studentId") Integer studentId,
			@Param("subYear") Integer subYear, @Param("semester") Integer semester);

	// 누계성적 조회
	public MyGradeDto selectMyGradeDtoBySemester(@Param("studentId") Integer studentId,
			@Param("subYear") Integer subYear, @Param("semester") Integer semester);

	// 성적 평균
	public GradeForScholarshipDto findAvgGradeByStudentIdAndSemester(@Param("studentId") Integer studentId,
			@Param("subYear") Integer subYear, @Param("semester") Integer semester);

	// 전체 누계성적 조회
	public List<MyGradeDto> selectMyGradeDtoByStudentId(Integer studentId);

	// 연도 누계성적 조회
	public List<MyGradeDto> gradeinquiryBysubYear(@Param("studentId") Integer studentId,
			@Param("subYear") Integer subYear);

}

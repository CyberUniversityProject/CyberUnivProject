package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.AllSubjectSearchFormDto;
import com.cyber.university.dto.CurrentSemesterSubjectSearchFormDto;
import com.cyber.university.dto.SubjectFormDto;
import com.cyber.university.dto.professor.SubjectPeriodForProfessorDto;
import com.cyber.university.dto.response.ReadSyllabusDto;
import com.cyber.university.dto.response.SubjectDto;
import com.cyber.university.dto.response.SubjectForProfessorDto;
import com.cyber.university.repository.model.Subject;

/**
 * 
  * @FileName : SubjectRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의 레파지토리
 */
@Mapper
public interface SubjectRepository {
	
	// 기본 CRUD
	public Integer insert(SubjectFormDto subjectFormDto);
	public int deleteById(Integer id);
	public int updateBySubjectDto(SubjectFormDto subjectFormDto);
	
	
	// 강의 추가시 같은 강의실, 요일, 연도, 학기 정보 조회
	public List<Subject> findSubjectsByRoomIdAndDayAndYearAndSemester(SubjectFormDto subjectFormDto);
	
	// 최근강의 id 조회
	public Integer findIdByOrderById(SubjectFormDto subjectFormDto);
	
	/**
	 * @return 수강 신청에 사용할 강의 정보
	 */
	public List<SubjectDto> selectDtoBySemester(@Param("subYear") Integer subYear, @Param("semester") Integer semester);
	public List<SubjectDto> selectDtoBySemesterLimit(@Param("subYear") Integer subYear, @Param("semester") Integer semester, @Param("page") Integer page);
	
	/**
	 * @return 전체 강의 정보
	 */
	public List<SubjectDto> selectDtoAll();
	public List<SubjectDto> selectDtoAllLimit(Integer page);
	
	/**
	 * @param 교수 id
	 * @return 교수 본인의 수업이 있는 년도-학기
	 */
	public List<SubjectPeriodForProfessorDto> selectSemester(Integer id);
	
	/**
	 * @return 그 학기의 본인 수업 정보들
	 */
	public List<SubjectForProfessorDto> selectSubjectBySemester(SubjectPeriodForProfessorDto subjectPeriodForProfessorDto);
	
	/**
	 * @return 연도-학기-개설학과-강의명 검색을 조건으로 한 강의 정보
	 */
	public List<SubjectDto> selectDtoBySemesterAndDeptAndName(AllSubjectSearchFormDto allSubjectSearchFormDto);
	
	/**
	 * @param currentSemesterSubjectSearchFormDto
	 * @return 연도-학기-강의구분-개설학과-강의명 검색을 조건으로 한 강의 정보
	 */
	public List<SubjectDto> selectDtoBySemesterAndAndTypeAndDeptAndName(CurrentSemesterSubjectSearchFormDto currentSemesterSubjectSearchFormDto);
	
	/*
	 * @param id
	 * @return
	 */
	public Subject selectSubjectById(Integer id);
	
	public List<Subject> selectAll();

	/**
	 * 현재 인원 수정 (1명 추가 or 삭제 or 0으로 초기화)
	 */
	public int updateNumOfStudent(@Param("id") Integer id, @Param("type") String type);
	
	
	public ReadSyllabusDto selectSyllabusBySubjectId(Integer subjectId);
	
	/**
	 * 신청인원인 강의의 id 리스트
	 */
	public List<Integer> selectIdByLessNumOfStudent();
	
	
	public List<Integer> selectIdByMoreNumOfStudent();
	

}

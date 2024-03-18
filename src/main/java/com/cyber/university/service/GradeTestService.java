package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.response.GradeDto;
import com.cyber.university.dto.response.GradeForScholarshipDto;
import com.cyber.university.dto.response.MyGradeDto;
import com.cyber.university.repository.interfaces.GradeRespository;
import com.cyber.university.utils.Define;

/**
 * 
  * @FileName : GradeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 성적 서비스
 */
@Service
public class GradeTestService {
	
	
	@Autowired
	private GradeRespository gradeRespository;

	// 학생이 수강 신청한 연도 조회
	@Transactional
	public List<GradeDto> readGradeYearByStudentId(Integer StudentId) {
		List<GradeDto> yearEntityList = gradeRespository.selectSubYearByStudentId(StudentId);
		return yearEntityList;
	}

	// 학생이 수강 신청한 학기 조회
	@Transactional
	public List<GradeDto> readGradeSemesterByStudentId(Integer StudentId) {
		List<GradeDto> semesterEntityList = gradeRespository.selectSemesterByStudentId(StudentId);
		return semesterEntityList;
	}

	// 금학기 성적 조회
	@Transactional
	public List<GradeDto> readThisSemesterByStudentId(Integer studentId) {
		List<GradeDto> gradeEntityList = gradeRespository.selectGradeDtoBySemester(studentId, Define.CURRENT_SEMESTER,
				Define.CURRENT_YEAR);
		return gradeEntityList;
	}

	// 금학기 누계성적 조회
	@Transactional
	public MyGradeDto readMyGradeByStudentId(Integer studentId) {
		MyGradeDto mygradeEntity = gradeRespository.selectMyGradeDtoBySemester(studentId, Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		return mygradeEntity;
	}

	// 전체 누계성적 조회
	public List<MyGradeDto> readgradeinquiryList(Integer studentId) {
		List<MyGradeDto> myAllgradeEntity = gradeRespository.selectMyGradeDtoByStudentId(studentId);
		return myAllgradeEntity;
	}

	// 학기별 성적조회 (전체 조회)
	@Transactional
	public List<GradeDto> readAllGradeByStudentId(Integer studentId) {
		List<GradeDto> gradeEntityAllList = gradeRespository.selectGradeDtoByStudentId(studentId);
		return gradeEntityAllList;
	}

	// 학기별 성적조회 조회 (선택 조회)
	@Transactional
	public List<GradeDto> readGradeByType(Integer studentId, Integer subYear, Integer semeter, String type) {
		List<GradeDto> selectgradeList = gradeRespository.selectGradeDtoBytype(studentId, subYear, semeter, type);
		return selectgradeList;
	}

	// 전체일때 조회
	@Transactional
	public List<GradeDto> readGradeByStudentId(Integer studentId, Integer subYear, Integer semester) {
		List<GradeDto> selectgradeList = gradeRespository.selectGradeDtoByStudentIdAndSubYear(studentId, subYear,
				semester);
		return selectgradeList;
	}

	// 성적 평균 가져오기
	public GradeForScholarshipDto readAvgGrade(Integer studentId, Integer subYear, Integer semester) {
		GradeForScholarshipDto gradeEntity = gradeRespository.findAvgGradeByStudentIdAndSemester(studentId, subYear,
				semester);
		return gradeEntity;
	}

}
